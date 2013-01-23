package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader
  implements Closeable
{
  private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
  private static final String TRUE = "true";
  private static final String FALSE = "false";
  private final StringPool stringPool = new StringPool();
  private final Reader in;
  private boolean lenient = false;
  private final char[] buffer = new char[1024];
  private int pos = 0;
  private int limit = 0;
  private int bufferStartLine = 1;
  private int bufferStartColumn = 1;
  private JsonScope[] stack = new JsonScope[32];
  private int stackSize = 0;
  private JsonToken token;
  private String name;
  private String value;
  private int valuePos;
  private int valueLength;
  private boolean skipping;

  public JsonReader(Reader paramReader)
  {
    push(JsonScope.EMPTY_DOCUMENT);
    this.skipping = false;
    if (paramReader == null)
      throw new NullPointerException("in == null");
    this.in = paramReader;
  }

  public final void setLenient(boolean paramBoolean)
  {
    this.lenient = paramBoolean;
  }

  public final boolean isLenient()
  {
    return this.lenient;
  }

  public void beginArray()
    throws IOException
  {
    expect(JsonToken.BEGIN_ARRAY);
  }

  public void endArray()
    throws IOException
  {
    expect(JsonToken.END_ARRAY);
  }

  public void beginObject()
    throws IOException
  {
    expect(JsonToken.BEGIN_OBJECT);
  }

  public void endObject()
    throws IOException
  {
    expect(JsonToken.END_OBJECT);
  }

  private void expect(JsonToken paramJsonToken)
    throws IOException
  {
    peek();
    if (this.token != paramJsonToken)
      throw new IllegalStateException("Expected " + paramJsonToken + " but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
  }

  public boolean hasNext()
    throws IOException
  {
    peek();
    return (this.token != JsonToken.END_OBJECT) && (this.token != JsonToken.END_ARRAY);
  }

  public JsonToken peek()
    throws IOException
  {
    if (this.token != null)
      return this.token;
    switch (2.$SwitchMap$com$google$gson$stream$JsonScope[this.stack[(this.stackSize - 1)].ordinal()])
    {
    case 1:
      if (this.lenient)
        consumeNonExecutePrefix();
      this.stack[(this.stackSize - 1)] = JsonScope.NONEMPTY_DOCUMENT;
      JsonToken localJsonToken = nextValue();
      if ((!this.lenient) && (this.token != JsonToken.BEGIN_ARRAY) && (this.token != JsonToken.BEGIN_OBJECT))
        throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
      return localJsonToken;
    case 2:
      return nextInArray(true);
    case 3:
      return nextInArray(false);
    case 4:
      return nextInObject(true);
    case 5:
      return objectValue();
    case 6:
      return nextInObject(false);
    case 7:
      int i = nextNonWhitespace(false);
      if (i == -1)
        return JsonToken.END_DOCUMENT;
      this.pos -= 1;
      if (!this.lenient)
        throw syntaxError("Expected EOF");
      return nextValue();
    case 8:
      throw new IllegalStateException("JsonReader is closed");
    }
    throw new AssertionError();
  }

  private void consumeNonExecutePrefix()
    throws IOException
  {
    nextNonWhitespace(true);
    this.pos -= 1;
    if ((this.pos + NON_EXECUTE_PREFIX.length > this.limit) && (!fillBuffer(NON_EXECUTE_PREFIX.length)))
      return;
    for (int i = 0; i < NON_EXECUTE_PREFIX.length; i++)
      if (this.buffer[(this.pos + i)] != NON_EXECUTE_PREFIX[i])
        return;
    this.pos += NON_EXECUTE_PREFIX.length;
  }

  private JsonToken advance()
    throws IOException
  {
    peek();
    JsonToken localJsonToken = this.token;
    this.token = null;
    this.value = null;
    this.name = null;
    return localJsonToken;
  }

  public String nextName()
    throws IOException
  {
    peek();
    if (this.token != JsonToken.NAME)
      throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    String str = this.name;
    advance();
    return str;
  }

  public String nextString()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    String str = this.value;
    advance();
    return str;
  }

  public boolean nextBoolean()
    throws IOException
  {
    peek();
    if (this.token != JsonToken.BOOLEAN)
      throw new IllegalStateException("Expected a boolean but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    boolean bool = this.value == "true";
    advance();
    return bool;
  }

  public void nextNull()
    throws IOException
  {
    peek();
    if (this.token != JsonToken.NULL)
      throw new IllegalStateException("Expected null but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
  }

  public double nextDouble()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected a double but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    double d = Double.parseDouble(this.value);
    if ((d >= 1.0D) && (this.value.startsWith("0")))
      throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    if ((!this.lenient) && ((Double.isNaN(d)) || (Double.isInfinite(d))))
      throw new MalformedJsonException("JSON forbids NaN and infinities: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
    return d;
  }

  public long nextLong()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected a long but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    long l;
    try
    {
      l = Long.parseLong(this.value);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      double d = Double.parseDouble(this.value);
      l = ()d;
      if (l != d)
        throw new NumberFormatException("Expected a long but was " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    }
    if ((l >= 1L) && (this.value.startsWith("0")))
      throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
    return l;
  }

  public int nextInt()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected an int but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    int i;
    try
    {
      i = Integer.parseInt(this.value);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      double d = Double.parseDouble(this.value);
      i = (int)d;
      if (i != d)
        throw new NumberFormatException("Expected an int but was " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    }
    if ((i >= 1L) && (this.value.startsWith("0")))
      throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
    return i;
  }

  public void close()
    throws IOException
  {
    this.value = null;
    this.token = null;
    this.stack[0] = JsonScope.CLOSED;
    this.stackSize = 1;
    this.in.close();
  }

  public void skipValue()
    throws IOException
  {
    this.skipping = true;
    try
    {
      int i = 0;
      do
      {
        JsonToken localJsonToken = advance();
        if ((localJsonToken == JsonToken.BEGIN_ARRAY) || (localJsonToken == JsonToken.BEGIN_OBJECT))
          i++;
        else if ((localJsonToken == JsonToken.END_ARRAY) || (localJsonToken == JsonToken.END_OBJECT))
          i--;
      }
      while (i != 0);
    }
    finally
    {
      this.skipping = false;
    }
  }

  private void push(JsonScope paramJsonScope)
  {
    if (this.stackSize == this.stack.length)
    {
      JsonScope[] arrayOfJsonScope = new JsonScope[this.stackSize * 2];
      System.arraycopy(this.stack, 0, arrayOfJsonScope, 0, this.stackSize);
      this.stack = arrayOfJsonScope;
    }
    this.stack[(this.stackSize++)] = paramJsonScope;
  }

  private JsonToken nextInArray(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      this.stack[(this.stackSize - 1)] = JsonScope.NONEMPTY_ARRAY;
    else
      switch (nextNonWhitespace(true))
      {
      case 93:
        this.stackSize -= 1;
        return this.token = JsonToken.END_ARRAY;
      case 59:
        checkLenient();
      case 44:
        break;
      default:
        throw syntaxError("Unterminated array");
      }
    switch (nextNonWhitespace(true))
    {
    case 93:
      if (paramBoolean)
      {
        this.stackSize -= 1;
        return this.token = JsonToken.END_ARRAY;
      }
    case 44:
    case 59:
      checkLenient();
      this.pos -= 1;
      this.value = "null";
      return this.token = JsonToken.NULL;
    }
    this.pos -= 1;
    return nextValue();
  }

  private JsonToken nextInObject(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      switch (nextNonWhitespace(true))
      {
      case 125:
        this.stackSize -= 1;
        return this.token = JsonToken.END_OBJECT;
      }
      this.pos -= 1;
    }
    else
    {
      switch (nextNonWhitespace(true))
      {
      case 125:
        this.stackSize -= 1;
        return this.token = JsonToken.END_OBJECT;
      case 44:
      case 59:
        break;
      default:
        throw syntaxError("Unterminated object");
      }
    }
    int i = nextNonWhitespace(true);
    switch (i)
    {
    case 39:
      checkLenient();
    case 34:
      this.name = nextString((char)i);
      break;
    default:
      checkLenient();
      this.pos -= 1;
      this.name = nextLiteral(false);
      if (this.name.length() == 0)
        throw syntaxError("Expected name");
      break;
    }
    this.stack[(this.stackSize - 1)] = JsonScope.DANGLING_NAME;
    return this.token = JsonToken.NAME;
  }

  private JsonToken objectValue()
    throws IOException
  {
    switch (nextNonWhitespace(true))
    {
    case 58:
      break;
    case 61:
      checkLenient();
      if (((this.pos < this.limit) || (fillBuffer(1))) && (this.buffer[this.pos] == '>'))
        this.pos += 1;
      break;
    default:
      throw syntaxError("Expected ':'");
    }
    this.stack[(this.stackSize - 1)] = JsonScope.NONEMPTY_OBJECT;
    return nextValue();
  }

  private JsonToken nextValue()
    throws IOException
  {
    int i = nextNonWhitespace(true);
    switch (i)
    {
    case 123:
      push(JsonScope.EMPTY_OBJECT);
      return this.token = JsonToken.BEGIN_OBJECT;
    case 91:
      push(JsonScope.EMPTY_ARRAY);
      return this.token = JsonToken.BEGIN_ARRAY;
    case 39:
      checkLenient();
    case 34:
      this.value = nextString((char)i);
      return this.token = JsonToken.STRING;
    }
    this.pos -= 1;
    return readLiteral();
  }

  private boolean fillBuffer(int paramInt)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.bufferStartLine;
    int j = this.bufferStartColumn;
    int k = 0;
    int m = this.pos;
    while (k < m)
    {
      if (arrayOfChar[k] == '\n')
      {
        i++;
        j = 1;
      }
      else
      {
        j++;
      }
      k++;
    }
    this.bufferStartLine = i;
    this.bufferStartColumn = j;
    if (this.limit != this.pos)
    {
      this.limit -= this.pos;
      System.arraycopy(arrayOfChar, this.pos, arrayOfChar, 0, this.limit);
    }
    else
    {
      this.limit = 0;
    }
    this.pos = 0;
    while ((k = this.in.read(arrayOfChar, this.limit, arrayOfChar.length - this.limit)) != -1)
    {
      this.limit += k;
      if ((this.bufferStartLine == 1) && (this.bufferStartColumn == 1) && (this.limit > 0) && (arrayOfChar[0] == 65279))
      {
        this.pos += 1;
        this.bufferStartColumn -= 1;
      }
      if (this.limit >= paramInt)
        return true;
    }
    return false;
  }

  private int getLineNumber()
  {
    int i = this.bufferStartLine;
    for (int j = 0; j < this.pos; j++)
      if (this.buffer[j] == '\n')
        i++;
    return i;
  }

  private int getColumnNumber()
  {
    int i = this.bufferStartColumn;
    for (int j = 0; j < this.pos; j++)
      if (this.buffer[j] == '\n')
        i = 1;
      else
        i++;
    return i;
  }

  private int nextNonWhitespace(boolean paramBoolean)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    int j = this.limit;
    int k;
    while (true)
    {
      if (i == j)
      {
        this.pos = i;
        if (!fillBuffer(1))
          break;
        i = this.pos;
        j = this.limit;
      }
      k = arrayOfChar[(i++)];
      switch (k)
      {
      case 9:
      case 10:
      case 13:
      case 32:
        break;
      case 47:
        this.pos = i;
        if (i == j)
        {
          this.pos -= 1;
          boolean bool = fillBuffer(2);
          this.pos += 1;
          if (!bool)
            return k;
        }
        checkLenient();
        int m = arrayOfChar[this.pos];
        switch (m)
        {
        case 42:
          this.pos += 1;
          if (!skipTo("*/"))
            throw syntaxError("Unterminated comment");
          i = this.pos + 2;
          j = this.limit;
          break;
        case 47:
          this.pos += 1;
          skipToEndOfLine();
          i = this.pos;
          j = this.limit;
          break;
        default:
          return k;
        }
        break;
      case 35:
        this.pos = i;
        checkLenient();
        skipToEndOfLine();
        i = this.pos;
        j = this.limit;
      }
    }
    this.pos = i;
    return k;
    if (paramBoolean)
      throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
    return -1;
  }

  private void checkLenient()
    throws IOException
  {
    if (!this.lenient)
      throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
  }

  private void skipToEndOfLine()
    throws IOException
  {
    while ((this.pos < this.limit) || (fillBuffer(1)))
    {
      int i = this.buffer[(this.pos++)];
      if ((i == 13) || (i == 10))
        break;
    }
  }

  private boolean skipTo(String paramString)
    throws IOException
  {
    while ((this.pos + paramString.length() <= this.limit) || (fillBuffer(paramString.length())))
    {
      for (int i = 0; i < paramString.length(); i++)
        if (this.buffer[(this.pos + i)] != paramString.charAt(i))
          break label67;
      return true;
      label67: this.pos += 1;
    }
    return false;
  }

  private String nextString(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    StringBuilder localStringBuilder = null;
    while (true)
    {
      int i = this.pos;
      int j = this.limit;
      int k = i;
      while (i < j)
      {
        char c = arrayOfChar[(i++)];
        if (c == paramChar)
        {
          this.pos = i;
          if (this.skipping)
            return "skipped!";
          if (localStringBuilder == null)
            return this.stringPool.get(arrayOfChar, k, i - k - 1);
          localStringBuilder.append(arrayOfChar, k, i - k - 1);
          return localStringBuilder.toString();
        }
        if (c == '\\')
        {
          this.pos = i;
          if (localStringBuilder == null)
            localStringBuilder = new StringBuilder();
          localStringBuilder.append(arrayOfChar, k, i - k - 1);
          localStringBuilder.append(readEscapeCharacter());
          i = this.pos;
          j = this.limit;
          k = i;
        }
      }
      if (localStringBuilder == null)
        localStringBuilder = new StringBuilder();
      localStringBuilder.append(arrayOfChar, k, i - k);
      this.pos = i;
      if (!fillBuffer(1))
        throw syntaxError("Unterminated string");
    }
  }

  private String nextLiteral(boolean paramBoolean)
    throws IOException
  {
    StringBuilder localStringBuilder = null;
    this.valuePos = -1;
    this.valueLength = 0;
    int i = 0;
    while (true)
      if (this.pos + i < this.limit)
      {
        switch (this.buffer[(this.pos + i)])
        {
        case '#':
        case '/':
        case ';':
        case '=':
        case '\\':
          checkLenient();
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
        case ',':
        case ':':
        case '[':
        case ']':
        case '{':
        case '}':
          break;
        default:
          i++;
          break;
        }
      }
      else if (i < this.buffer.length)
      {
        if (!fillBuffer(i + 1))
          this.buffer[this.limit] = '\000';
      }
      else
      {
        if (localStringBuilder == null)
          localStringBuilder = new StringBuilder();
        localStringBuilder.append(this.buffer, this.pos, i);
        this.valueLength += i;
        this.pos += i;
        i = 0;
        if (!fillBuffer(1))
          break;
      }
    String str;
    if ((paramBoolean) && (localStringBuilder == null))
    {
      this.valuePos = this.pos;
      str = null;
    }
    else if (this.skipping)
    {
      str = "skipped!";
    }
    else if (localStringBuilder == null)
    {
      str = this.stringPool.get(this.buffer, this.pos, i);
    }
    else
    {
      localStringBuilder.append(this.buffer, this.pos, i);
      str = localStringBuilder.toString();
    }
    this.valueLength += i;
    this.pos += i;
    return str;
  }

  public String toString()
  {
    return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
  }

  private char readEscapeCharacter()
    throws IOException
  {
    if ((this.pos == this.limit) && (!fillBuffer(1)))
      throw syntaxError("Unterminated escape sequence");
    char c = this.buffer[(this.pos++)];
    switch (c)
    {
    case 'u':
      if ((this.pos + 4 > this.limit) && (!fillBuffer(4)))
        throw syntaxError("Unterminated escape sequence");
      int i = 0;
      int j = this.pos;
      int k = j + 4;
      while (j < k)
      {
        int m = this.buffer[j];
        i = (char)(i << 4);
        if ((m >= 48) && (m <= 57))
          i = (char)(i + (m - 48));
        else if ((m >= 97) && (m <= 102))
          i = (char)(i + (m - 97 + 10));
        else if ((m >= 65) && (m <= 70))
          i = (char)(i + (m - 65 + 10));
        else
          throw new NumberFormatException("\\u" + this.stringPool.get(this.buffer, this.pos, 4));
        j++;
      }
      this.pos += 4;
      return i;
    case 't':
      return '\t';
    case 'b':
      return '\b';
    case 'n':
      return '\n';
    case 'r':
      return '\r';
    case 'f':
      return '\f';
    case '"':
    case '\'':
    case '\\':
    }
    return c;
  }

  private JsonToken readLiteral()
    throws IOException
  {
    this.value = nextLiteral(true);
    if (this.valueLength == 0)
      throw syntaxError("Expected literal value");
    this.token = decodeLiteral();
    if (this.token == JsonToken.STRING)
      checkLenient();
    return this.token;
  }

  private JsonToken decodeLiteral()
    throws IOException
  {
    if (this.valuePos == -1)
      return JsonToken.STRING;
    if ((this.valueLength == 4) && (('n' == this.buffer[this.valuePos]) || ('N' == this.buffer[this.valuePos])) && (('u' == this.buffer[(this.valuePos + 1)]) || ('U' == this.buffer[(this.valuePos + 1)])) && (('l' == this.buffer[(this.valuePos + 2)]) || ('L' == this.buffer[(this.valuePos + 2)])) && (('l' == this.buffer[(this.valuePos + 3)]) || ('L' == this.buffer[(this.valuePos + 3)])))
    {
      this.value = "null";
      return JsonToken.NULL;
    }
    if ((this.valueLength == 4) && (('t' == this.buffer[this.valuePos]) || ('T' == this.buffer[this.valuePos])) && (('r' == this.buffer[(this.valuePos + 1)]) || ('R' == this.buffer[(this.valuePos + 1)])) && (('u' == this.buffer[(this.valuePos + 2)]) || ('U' == this.buffer[(this.valuePos + 2)])) && (('e' == this.buffer[(this.valuePos + 3)]) || ('E' == this.buffer[(this.valuePos + 3)])))
    {
      this.value = "true";
      return JsonToken.BOOLEAN;
    }
    if ((this.valueLength == 5) && (('f' == this.buffer[this.valuePos]) || ('F' == this.buffer[this.valuePos])) && (('a' == this.buffer[(this.valuePos + 1)]) || ('A' == this.buffer[(this.valuePos + 1)])) && (('l' == this.buffer[(this.valuePos + 2)]) || ('L' == this.buffer[(this.valuePos + 2)])) && (('s' == this.buffer[(this.valuePos + 3)]) || ('S' == this.buffer[(this.valuePos + 3)])) && (('e' == this.buffer[(this.valuePos + 4)]) || ('E' == this.buffer[(this.valuePos + 4)])))
    {
      this.value = "false";
      return JsonToken.BOOLEAN;
    }
    this.value = this.stringPool.get(this.buffer, this.valuePos, this.valueLength);
    return decodeNumber(this.buffer, this.valuePos, this.valueLength);
  }

  private JsonToken decodeNumber(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int j = paramArrayOfChar[i];
    if (j == 45)
      j = paramArrayOfChar[(++i)];
    if (j == 48)
    {
      j = paramArrayOfChar[(++i)];
    }
    else
    {
      if ((j >= 49) && (j <= 57))
        j = paramArrayOfChar[(++i)];
      while ((j >= 48) && (j <= 57))
      {
        j = paramArrayOfChar[(++i)];
        continue;
        return JsonToken.STRING;
      }
    }
    if (j == 46)
      for (j = paramArrayOfChar[(++i)]; (j >= 48) && (j <= 57); j = paramArrayOfChar[(++i)]);
    if ((j == 101) || (j == 69))
    {
      j = paramArrayOfChar[(++i)];
      if ((j == 43) || (j == 45))
        j = paramArrayOfChar[(++i)];
      if ((j >= 48) && (j <= 57))
        j = paramArrayOfChar[(++i)];
      while ((j >= 48) && (j <= 57))
      {
        j = paramArrayOfChar[(++i)];
        continue;
        return JsonToken.STRING;
      }
    }
    if (i == paramInt1 + paramInt2)
      return JsonToken.NUMBER;
    return JsonToken.STRING;
  }

  private IOException syntaxError(String paramString)
    throws IOException
  {
    throw new MalformedJsonException(paramString + " at line " + getLineNumber() + " column " + getColumnNumber());
  }

  static
  {
    JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess()
    {
      public void promoteNameToValue(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if ((paramAnonymousJsonReader instanceof JsonTreeReader))
        {
          ((JsonTreeReader)paramAnonymousJsonReader).promoteNameToValue();
          return;
        }
        paramAnonymousJsonReader.peek();
        if (paramAnonymousJsonReader.token != JsonToken.NAME)
          throw new IllegalStateException("Expected a name but was " + paramAnonymousJsonReader.peek() + " " + " at line " + paramAnonymousJsonReader.getLineNumber() + " column " + paramAnonymousJsonReader.getColumnNumber());
        paramAnonymousJsonReader.value = paramAnonymousJsonReader.name;
        paramAnonymousJsonReader.name = null;
        paramAnonymousJsonReader.token = JsonToken.STRING;
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.stream.JsonReader
 * JD-Core Version:    0.6.2
 */