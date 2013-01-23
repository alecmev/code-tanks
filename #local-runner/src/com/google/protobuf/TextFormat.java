package com.google.protobuf;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat
{
  private static final Printer DEFAULT_PRINTER = new Printer(false, null);
  private static final Printer SINGLE_LINE_PRINTER = new Printer(true, null);
  private static final int BUFFER_SIZE = 4096;

  public static void print(Message paramMessage, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.print(paramMessage, new TextGenerator(paramAppendable, null));
  }

  public static void print(UnknownFieldSet paramUnknownFieldSet, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(paramAppendable, null));
  }

  public static String shortDebugString(Message paramMessage)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SINGLE_LINE_PRINTER.print(paramMessage, new TextGenerator(localStringBuilder, null));
      return localStringBuilder.toString().trim();
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }

  public static String shortDebugString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SINGLE_LINE_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(localStringBuilder, null));
      return localStringBuilder.toString().trim();
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }

  public static String printToString(Message paramMessage)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      print(paramMessage, localStringBuilder);
      return localStringBuilder.toString();
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }

  public static String printToString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      print(paramUnknownFieldSet, localStringBuilder);
      return localStringBuilder.toString();
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }

  public static void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printField(paramFieldDescriptor, paramObject, new TextGenerator(paramAppendable, null));
  }

  public static String printFieldToString(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      printField(paramFieldDescriptor, paramObject, localStringBuilder);
      return localStringBuilder.toString();
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }

  public static void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printFieldValue(paramFieldDescriptor, paramObject, new TextGenerator(paramAppendable, null));
  }

  public static void printUnknownFieldValue(int paramInt, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    printUnknownFieldValue(paramInt, paramObject, new TextGenerator(paramAppendable, null));
  }

  private static void printUnknownFieldValue(int paramInt, Object paramObject, TextGenerator paramTextGenerator)
    throws IOException
  {
    switch (WireFormat.getTagWireType(paramInt))
    {
    case 0:
      paramTextGenerator.print(unsignedToString(((Long)paramObject).longValue()));
      break;
    case 5:
      paramTextGenerator.print(String.format((Locale)null, "0x%08x", new Object[] { (Integer)paramObject }));
      break;
    case 1:
      paramTextGenerator.print(String.format((Locale)null, "0x%016x", new Object[] { (Long)paramObject }));
      break;
    case 2:
      paramTextGenerator.print("\"");
      paramTextGenerator.print(escapeBytes((ByteString)paramObject));
      paramTextGenerator.print("\"");
      break;
    case 3:
      DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet)paramObject, paramTextGenerator);
      break;
    case 4:
    default:
      throw new IllegalArgumentException("Bad tag: " + paramInt);
    }
  }

  private static String unsignedToString(int paramInt)
  {
    if (paramInt >= 0)
      return Integer.toString(paramInt);
    return Long.toString(paramInt & 0xFFFFFFFF);
  }

  private static String unsignedToString(long paramLong)
  {
    if (paramLong >= 0L)
      return Long.toString(paramLong);
    return BigInteger.valueOf(paramLong & 0xFFFFFFFF).setBit(63).toString();
  }

  public static void merge(Readable paramReadable, Message.Builder paramBuilder)
    throws IOException
  {
    merge(paramReadable, ExtensionRegistry.getEmptyRegistry(), paramBuilder);
  }

  public static void merge(CharSequence paramCharSequence, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    merge(paramCharSequence, ExtensionRegistry.getEmptyRegistry(), paramBuilder);
  }

  public static void merge(Readable paramReadable, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws IOException
  {
    merge(toStringBuilder(paramReadable), paramExtensionRegistry, paramBuilder);
  }

  private static StringBuilder toStringBuilder(Readable paramReadable)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    CharBuffer localCharBuffer = CharBuffer.allocate(4096);
    while (true)
    {
      int i = paramReadable.read(localCharBuffer);
      if (i == -1)
        break;
      localCharBuffer.flip();
      localStringBuilder.append(localCharBuffer, 0, i);
    }
    return localStringBuilder;
  }

  public static void merge(CharSequence paramCharSequence, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    Tokenizer localTokenizer = new Tokenizer(paramCharSequence, null);
    while (!localTokenizer.atEnd())
      mergeField(localTokenizer, paramExtensionRegistry, paramBuilder);
  }

  private static void mergeField(Tokenizer paramTokenizer, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    Descriptors.Descriptor localDescriptor = paramBuilder.getDescriptorForType();
    ExtensionRegistry.ExtensionInfo localExtensionInfo = null;
    Descriptors.FieldDescriptor localFieldDescriptor;
    Object localObject2;
    if (paramTokenizer.tryConsume("["))
    {
      localObject1 = new StringBuilder(paramTokenizer.consumeIdentifier());
      while (paramTokenizer.tryConsume("."))
      {
        ((StringBuilder)localObject1).append('.');
        ((StringBuilder)localObject1).append(paramTokenizer.consumeIdentifier());
      }
      localExtensionInfo = paramExtensionRegistry.findExtensionByName(((StringBuilder)localObject1).toString());
      if (localExtensionInfo == null)
        throw paramTokenizer.parseExceptionPreviousToken("Extension \"" + localObject1 + "\" not found in the ExtensionRegistry.");
      if (localExtensionInfo.descriptor.getContainingType() != localDescriptor)
        throw paramTokenizer.parseExceptionPreviousToken("Extension \"" + localObject1 + "\" does not extend message type \"" + localDescriptor.getFullName() + "\".");
      paramTokenizer.consume("]");
      localFieldDescriptor = localExtensionInfo.descriptor;
    }
    else
    {
      localObject1 = paramTokenizer.consumeIdentifier();
      localFieldDescriptor = localDescriptor.findFieldByName((String)localObject1);
      if (localFieldDescriptor == null)
      {
        localObject2 = ((String)localObject1).toLowerCase(Locale.US);
        localFieldDescriptor = localDescriptor.findFieldByName((String)localObject2);
        if ((localFieldDescriptor != null) && (localFieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.GROUP))
          localFieldDescriptor = null;
      }
      if ((localFieldDescriptor != null) && (localFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) && (!localFieldDescriptor.getMessageType().getName().equals(localObject1)))
        localFieldDescriptor = null;
      if (localFieldDescriptor == null)
        throw paramTokenizer.parseExceptionPreviousToken("Message type \"" + localDescriptor.getFullName() + "\" has no field named \"" + (String)localObject1 + "\".");
    }
    Object localObject1 = null;
    if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
    {
      paramTokenizer.tryConsume(":");
      if (paramTokenizer.tryConsume("<"))
      {
        localObject2 = ">";
      }
      else
      {
        paramTokenizer.consume("{");
        localObject2 = "}";
      }
      Message.Builder localBuilder;
      if (localExtensionInfo == null)
        localBuilder = paramBuilder.newBuilderForField(localFieldDescriptor);
      else
        localBuilder = localExtensionInfo.defaultInstance.newBuilderForType();
      while (!paramTokenizer.tryConsume((String)localObject2))
      {
        if (paramTokenizer.atEnd())
          throw paramTokenizer.parseException("Expected \"" + (String)localObject2 + "\".");
        mergeField(paramTokenizer, paramExtensionRegistry, localBuilder);
      }
      localObject1 = localBuilder.build();
    }
    else
    {
      paramTokenizer.consume(":");
      switch (1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[localFieldDescriptor.getType().ordinal()])
      {
      case 1:
      case 2:
      case 3:
        localObject1 = Integer.valueOf(paramTokenizer.consumeInt32());
        break;
      case 4:
      case 5:
      case 6:
        localObject1 = Long.valueOf(paramTokenizer.consumeInt64());
        break;
      case 10:
      case 11:
        localObject1 = Integer.valueOf(paramTokenizer.consumeUInt32());
        break;
      case 12:
      case 13:
        localObject1 = Long.valueOf(paramTokenizer.consumeUInt64());
        break;
      case 8:
        localObject1 = Float.valueOf(paramTokenizer.consumeFloat());
        break;
      case 9:
        localObject1 = Double.valueOf(paramTokenizer.consumeDouble());
        break;
      case 7:
        localObject1 = Boolean.valueOf(paramTokenizer.consumeBoolean());
        break;
      case 14:
        localObject1 = paramTokenizer.consumeString();
        break;
      case 15:
        localObject1 = paramTokenizer.consumeByteString();
        break;
      case 16:
        localObject2 = localFieldDescriptor.getEnumType();
        if (paramTokenizer.lookingAtInteger())
        {
          int i = paramTokenizer.consumeInt32();
          localObject1 = ((Descriptors.EnumDescriptor)localObject2).findValueByNumber(i);
          if (localObject1 == null)
            throw paramTokenizer.parseExceptionPreviousToken("Enum type \"" + ((Descriptors.EnumDescriptor)localObject2).getFullName() + "\" has no value with number " + i + '.');
        }
        else
        {
          String str = paramTokenizer.consumeIdentifier();
          localObject1 = ((Descriptors.EnumDescriptor)localObject2).findValueByName(str);
          if (localObject1 == null)
            throw paramTokenizer.parseExceptionPreviousToken("Enum type \"" + ((Descriptors.EnumDescriptor)localObject2).getFullName() + "\" has no value named \"" + str + "\".");
        }
        break;
      case 17:
      case 18:
        throw new RuntimeException("Can't get here.");
      }
    }
    if (localFieldDescriptor.isRepeated())
      paramBuilder.addRepeatedField(localFieldDescriptor, localObject1);
    else
      paramBuilder.setField(localFieldDescriptor, localObject1);
  }

  static String escapeBytes(ByteString paramByteString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramByteString.size());
    for (int i = 0; i < paramByteString.size(); i++)
    {
      int j = paramByteString.byteAt(i);
      switch (j)
      {
      case 7:
        localStringBuilder.append("\\a");
        break;
      case 8:
        localStringBuilder.append("\\b");
        break;
      case 12:
        localStringBuilder.append("\\f");
        break;
      case 10:
        localStringBuilder.append("\\n");
        break;
      case 13:
        localStringBuilder.append("\\r");
        break;
      case 9:
        localStringBuilder.append("\\t");
        break;
      case 11:
        localStringBuilder.append("\\v");
        break;
      case 92:
        localStringBuilder.append("\\\\");
        break;
      case 39:
        localStringBuilder.append("\\'");
        break;
      case 34:
        localStringBuilder.append("\\\"");
        break;
      default:
        if (j >= 32)
        {
          localStringBuilder.append((char)j);
        }
        else
        {
          localStringBuilder.append('\\');
          localStringBuilder.append((char)(48 + (j >>> 6 & 0x3)));
          localStringBuilder.append((char)(48 + (j >>> 3 & 0x7)));
          localStringBuilder.append((char)(48 + (j & 0x7)));
        }
        break;
      }
    }
    return localStringBuilder.toString();
  }

  static ByteString unescapeBytes(CharSequence paramCharSequence)
    throws TextFormat.InvalidEscapeSequenceException
  {
    ByteString localByteString = ByteString.copyFromUtf8(paramCharSequence.toString());
    byte[] arrayOfByte = new byte[localByteString.size()];
    int i = 0;
    for (int j = 0; j < localByteString.size(); j++)
    {
      byte b = localByteString.byteAt(j);
      if (b == 92)
      {
        if (j + 1 < localByteString.size())
        {
          j++;
          b = localByteString.byteAt(j);
          int k;
          if (isOctal(b))
          {
            k = digitValue(b);
            if ((j + 1 < localByteString.size()) && (isOctal(localByteString.byteAt(j + 1))))
            {
              j++;
              k = k * 8 + digitValue(localByteString.byteAt(j));
            }
            if ((j + 1 < localByteString.size()) && (isOctal(localByteString.byteAt(j + 1))))
            {
              j++;
              k = k * 8 + digitValue(localByteString.byteAt(j));
            }
            arrayOfByte[(i++)] = ((byte)k);
          }
          else
          {
            switch (b)
            {
            case 97:
              arrayOfByte[(i++)] = 7;
              break;
            case 98:
              arrayOfByte[(i++)] = 8;
              break;
            case 102:
              arrayOfByte[(i++)] = 12;
              break;
            case 110:
              arrayOfByte[(i++)] = 10;
              break;
            case 114:
              arrayOfByte[(i++)] = 13;
              break;
            case 116:
              arrayOfByte[(i++)] = 9;
              break;
            case 118:
              arrayOfByte[(i++)] = 11;
              break;
            case 92:
              arrayOfByte[(i++)] = 92;
              break;
            case 39:
              arrayOfByte[(i++)] = 39;
              break;
            case 34:
              arrayOfByte[(i++)] = 34;
              break;
            case 120:
              k = 0;
              if ((j + 1 < localByteString.size()) && (isHex(localByteString.byteAt(j + 1))))
              {
                j++;
                k = digitValue(localByteString.byteAt(j));
              }
              else
              {
                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
              }
              if ((j + 1 < localByteString.size()) && (isHex(localByteString.byteAt(j + 1))))
              {
                j++;
                k = k * 16 + digitValue(localByteString.byteAt(j));
              }
              arrayOfByte[(i++)] = ((byte)k);
              break;
            default:
              throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)b + '\'');
            }
          }
        }
        else
        {
          throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
        }
      }
      else
        arrayOfByte[(i++)] = b;
    }
    return ByteString.copyFrom(arrayOfByte, 0, i);
  }

  static String escapeText(String paramString)
  {
    return escapeBytes(ByteString.copyFromUtf8(paramString));
  }

  static String unescapeText(String paramString)
    throws TextFormat.InvalidEscapeSequenceException
  {
    return unescapeBytes(paramString).toStringUtf8();
  }

  private static boolean isOctal(byte paramByte)
  {
    return (48 <= paramByte) && (paramByte <= 55);
  }

  private static boolean isHex(byte paramByte)
  {
    return ((48 <= paramByte) && (paramByte <= 57)) || ((97 <= paramByte) && (paramByte <= 102)) || ((65 <= paramByte) && (paramByte <= 70));
  }

  private static int digitValue(byte paramByte)
  {
    if ((48 <= paramByte) && (paramByte <= 57))
      return paramByte - 48;
    if ((97 <= paramByte) && (paramByte <= 122))
      return paramByte - 97 + 10;
    return paramByte - 65 + 10;
  }

  static int parseInt32(String paramString)
    throws NumberFormatException
  {
    return (int)parseInteger(paramString, true, false);
  }

  static int parseUInt32(String paramString)
    throws NumberFormatException
  {
    return (int)parseInteger(paramString, false, false);
  }

  static long parseInt64(String paramString)
    throws NumberFormatException
  {
    return parseInteger(paramString, true, true);
  }

  static long parseUInt64(String paramString)
    throws NumberFormatException
  {
    return parseInteger(paramString, false, true);
  }

  private static long parseInteger(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws NumberFormatException
  {
    int i = 0;
    int j = 0;
    if (paramString.startsWith("-", i))
    {
      if (!paramBoolean1)
        throw new NumberFormatException("Number must be positive: " + paramString);
      i++;
      j = 1;
    }
    int k = 10;
    if (paramString.startsWith("0x", i))
    {
      i += 2;
      k = 16;
    }
    else if (paramString.startsWith("0", i))
    {
      k = 8;
    }
    String str = paramString.substring(i);
    long l = 0L;
    if (str.length() < 16)
    {
      l = Long.parseLong(str, k);
      if (j != 0)
        l = -l;
      if (!paramBoolean2)
        if (paramBoolean1)
        {
          if ((l > 2147483647L) || (l < -2147483648L))
            throw new NumberFormatException("Number out of range for 32-bit signed integer: " + paramString);
        }
        else if ((l >= 4294967296L) || (l < 0L))
          throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + paramString);
    }
    else
    {
      BigInteger localBigInteger = new BigInteger(str, k);
      if (j != 0)
        localBigInteger = localBigInteger.negate();
      if (!paramBoolean2)
      {
        if (paramBoolean1)
        {
          if (localBigInteger.bitLength() > 31)
            throw new NumberFormatException("Number out of range for 32-bit signed integer: " + paramString);
        }
        else if (localBigInteger.bitLength() > 32)
          throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + paramString);
      }
      else if (paramBoolean1)
      {
        if (localBigInteger.bitLength() > 63)
          throw new NumberFormatException("Number out of range for 64-bit signed integer: " + paramString);
      }
      else if (localBigInteger.bitLength() > 64)
        throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + paramString);
      l = localBigInteger.longValue();
    }
    return l;
  }

  static class InvalidEscapeSequenceException extends IOException
  {
    private static final long serialVersionUID = -8164033650142593304L;

    InvalidEscapeSequenceException(String paramString)
    {
      super();
    }
  }

  public static class ParseException extends IOException
  {
    private static final long serialVersionUID = 3196188060225107702L;

    public ParseException(String paramString)
    {
      super();
    }
  }

  private static final class Tokenizer
  {
    private final CharSequence text;
    private final Matcher matcher;
    private String currentToken;
    private int pos = 0;
    private int line = 0;
    private int column = 0;
    private int previousLine = 0;
    private int previousColumn = 0;
    private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
    private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
    private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
    private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
    private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

    private Tokenizer(CharSequence paramCharSequence)
    {
      this.text = paramCharSequence;
      this.matcher = WHITESPACE.matcher(paramCharSequence);
      skipWhitespace();
      nextToken();
    }

    public boolean atEnd()
    {
      return this.currentToken.length() == 0;
    }

    public void nextToken()
    {
      this.previousLine = this.line;
      this.previousColumn = this.column;
      while (this.pos < this.matcher.regionStart())
      {
        if (this.text.charAt(this.pos) == '\n')
        {
          this.line += 1;
          this.column = 0;
        }
        else
        {
          this.column += 1;
        }
        this.pos += 1;
      }
      if (this.matcher.regionStart() == this.matcher.regionEnd())
      {
        this.currentToken = "";
      }
      else
      {
        this.matcher.usePattern(TOKEN);
        if (this.matcher.lookingAt())
        {
          this.currentToken = this.matcher.group();
          this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
        }
        else
        {
          this.currentToken = String.valueOf(this.text.charAt(this.pos));
          this.matcher.region(this.pos + 1, this.matcher.regionEnd());
        }
        skipWhitespace();
      }
    }

    private void skipWhitespace()
    {
      this.matcher.usePattern(WHITESPACE);
      if (this.matcher.lookingAt())
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
    }

    public boolean tryConsume(String paramString)
    {
      if (this.currentToken.equals(paramString))
      {
        nextToken();
        return true;
      }
      return false;
    }

    public void consume(String paramString)
      throws TextFormat.ParseException
    {
      if (!tryConsume(paramString))
        throw parseException("Expected \"" + paramString + "\".");
    }

    public boolean lookingAtInteger()
    {
      if (this.currentToken.length() == 0)
        return false;
      int i = this.currentToken.charAt(0);
      return ((48 <= i) && (i <= 57)) || (i == 45) || (i == 43);
    }

    public String consumeIdentifier()
      throws TextFormat.ParseException
    {
      for (int i = 0; i < this.currentToken.length(); i++)
      {
        int j = this.currentToken.charAt(i);
        if (((97 > j) || (j > 122)) && ((65 > j) || (j > 90)) && ((48 > j) || (j > 57)) && (j != 95) && (j != 46))
          throw parseException("Expected identifier.");
      }
      String str = this.currentToken;
      nextToken();
      return str;
    }

    public int consumeInt32()
      throws TextFormat.ParseException
    {
      try
      {
        int i = TextFormat.parseInt32(this.currentToken);
        nextToken();
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public int consumeUInt32()
      throws TextFormat.ParseException
    {
      try
      {
        int i = TextFormat.parseUInt32(this.currentToken);
        nextToken();
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public long consumeInt64()
      throws TextFormat.ParseException
    {
      try
      {
        long l = TextFormat.parseInt64(this.currentToken);
        nextToken();
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public long consumeUInt64()
      throws TextFormat.ParseException
    {
      try
      {
        long l = TextFormat.parseUInt64(this.currentToken);
        nextToken();
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public double consumeDouble()
      throws TextFormat.ParseException
    {
      if (DOUBLE_INFINITY.matcher(this.currentToken).matches())
      {
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        return bool ? (-1.0D / 0.0D) : (1.0D / 0.0D);
      }
      if (this.currentToken.equalsIgnoreCase("nan"))
      {
        nextToken();
        return (0.0D / 0.0D);
      }
      try
      {
        double d = Double.parseDouble(this.currentToken);
        nextToken();
        return d;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw floatParseException(localNumberFormatException);
      }
    }

    public float consumeFloat()
      throws TextFormat.ParseException
    {
      if (FLOAT_INFINITY.matcher(this.currentToken).matches())
      {
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        return bool ? (1.0F / -1.0F) : (1.0F / 1.0F);
      }
      if (FLOAT_NAN.matcher(this.currentToken).matches())
      {
        nextToken();
        return (0.0F / 0.0F);
      }
      try
      {
        float f = Float.parseFloat(this.currentToken);
        nextToken();
        return f;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw floatParseException(localNumberFormatException);
      }
    }

    public boolean consumeBoolean()
      throws TextFormat.ParseException
    {
      if ((this.currentToken.equals("true")) || (this.currentToken.equals("t")) || (this.currentToken.equals("1")))
      {
        nextToken();
        return true;
      }
      if ((this.currentToken.equals("false")) || (this.currentToken.equals("f")) || (this.currentToken.equals("0")))
      {
        nextToken();
        return false;
      }
      throw parseException("Expected \"true\" or \"false\".");
    }

    public String consumeString()
      throws TextFormat.ParseException
    {
      return consumeByteString().toStringUtf8();
    }

    public ByteString consumeByteString()
      throws TextFormat.ParseException
    {
      ArrayList localArrayList = new ArrayList();
      consumeByteString(localArrayList);
      while ((this.currentToken.startsWith("'")) || (this.currentToken.startsWith("\"")))
        consumeByteString(localArrayList);
      return ByteString.copyFrom(localArrayList);
    }

    private void consumeByteString(List paramList)
      throws TextFormat.ParseException
    {
      int i = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : 0;
      if ((i != 34) && (i != 39))
        throw parseException("Expected string.");
      if ((this.currentToken.length() < 2) || (this.currentToken.charAt(this.currentToken.length() - 1) != i))
        throw parseException("String missing ending quote.");
      try
      {
        String str = this.currentToken.substring(1, this.currentToken.length() - 1);
        ByteString localByteString = TextFormat.unescapeBytes(str);
        nextToken();
        paramList.add(localByteString);
      }
      catch (TextFormat.InvalidEscapeSequenceException localInvalidEscapeSequenceException)
      {
        throw parseException(localInvalidEscapeSequenceException.getMessage());
      }
    }

    public TextFormat.ParseException parseException(String paramString)
    {
      return new TextFormat.ParseException(this.line + 1 + ":" + (this.column + 1) + ": " + paramString);
    }

    public TextFormat.ParseException parseExceptionPreviousToken(String paramString)
    {
      return new TextFormat.ParseException(this.previousLine + 1 + ":" + (this.previousColumn + 1) + ": " + paramString);
    }

    private TextFormat.ParseException integerParseException(NumberFormatException paramNumberFormatException)
    {
      return parseException("Couldn't parse integer: " + paramNumberFormatException.getMessage());
    }

    private TextFormat.ParseException floatParseException(NumberFormatException paramNumberFormatException)
    {
      return parseException("Couldn't parse number: " + paramNumberFormatException.getMessage());
    }
  }

  private static final class TextGenerator
  {
    private final Appendable output;
    private final StringBuilder indent = new StringBuilder();
    private boolean atStartOfLine = true;

    private TextGenerator(Appendable paramAppendable)
    {
      this.output = paramAppendable;
    }

    public void indent()
    {
      this.indent.append("  ");
    }

    public void outdent()
    {
      int i = this.indent.length();
      if (i == 0)
        throw new IllegalArgumentException(" Outdent() without matching Indent().");
      this.indent.delete(i - 2, i);
    }

    public void print(CharSequence paramCharSequence)
      throws IOException
    {
      int i = paramCharSequence.length();
      int j = 0;
      for (int k = 0; k < i; k++)
        if (paramCharSequence.charAt(k) == '\n')
        {
          write(paramCharSequence.subSequence(j, i), k - j + 1);
          j = k + 1;
          this.atStartOfLine = true;
        }
      write(paramCharSequence.subSequence(j, i), i - j);
    }

    private void write(CharSequence paramCharSequence, int paramInt)
      throws IOException
    {
      if (paramInt == 0)
        return;
      if (this.atStartOfLine)
      {
        this.atStartOfLine = false;
        this.output.append(this.indent);
      }
      this.output.append(paramCharSequence);
    }
  }

  private static final class Printer
  {
    final boolean singleLineMode;

    private Printer(boolean paramBoolean)
    {
      this.singleLineMode = paramBoolean;
    }

    private void print(Message paramMessage, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator = paramMessage.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        printField((Descriptors.FieldDescriptor)localEntry.getKey(), localEntry.getValue(), paramTextGenerator);
      }
      printUnknownFields(paramMessage.getUnknownFields(), paramTextGenerator);
    }

    private void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      if (paramFieldDescriptor.isRepeated())
      {
        Iterator localIterator = ((List)paramObject).iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          printSingleField(paramFieldDescriptor, localObject, paramTextGenerator);
        }
      }
      else
      {
        printSingleField(paramFieldDescriptor, paramObject, paramTextGenerator);
      }
    }

    private void printSingleField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      if (paramFieldDescriptor.isExtension())
      {
        paramTextGenerator.print("[");
        if ((paramFieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat()) && (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (paramFieldDescriptor.isOptional()) && (paramFieldDescriptor.getExtensionScope() == paramFieldDescriptor.getMessageType()))
          paramTextGenerator.print(paramFieldDescriptor.getMessageType().getFullName());
        else
          paramTextGenerator.print(paramFieldDescriptor.getFullName());
        paramTextGenerator.print("]");
      }
      else if (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP)
      {
        paramTextGenerator.print(paramFieldDescriptor.getMessageType().getName());
      }
      else
      {
        paramTextGenerator.print(paramFieldDescriptor.getName());
      }
      if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
      {
        if (this.singleLineMode)
        {
          paramTextGenerator.print(" { ");
        }
        else
        {
          paramTextGenerator.print(" {\n");
          paramTextGenerator.indent();
        }
      }
      else
        paramTextGenerator.print(": ");
      printFieldValue(paramFieldDescriptor, paramObject, paramTextGenerator);
      if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
      {
        if (this.singleLineMode)
        {
          paramTextGenerator.print("} ");
        }
        else
        {
          paramTextGenerator.outdent();
          paramTextGenerator.print("}\n");
        }
      }
      else if (this.singleLineMode)
        paramTextGenerator.print(" ");
      else
        paramTextGenerator.print("\n");
    }

    private void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      switch (TextFormat.1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[paramFieldDescriptor.getType().ordinal()])
      {
      case 1:
      case 2:
      case 3:
        paramTextGenerator.print(((Integer)paramObject).toString());
        break;
      case 4:
      case 5:
      case 6:
        paramTextGenerator.print(((Long)paramObject).toString());
        break;
      case 7:
        paramTextGenerator.print(((Boolean)paramObject).toString());
        break;
      case 8:
        paramTextGenerator.print(((Float)paramObject).toString());
        break;
      case 9:
        paramTextGenerator.print(((Double)paramObject).toString());
        break;
      case 10:
      case 11:
        paramTextGenerator.print(TextFormat.unsignedToString(((Integer)paramObject).intValue()));
        break;
      case 12:
      case 13:
        paramTextGenerator.print(TextFormat.unsignedToString(((Long)paramObject).longValue()));
        break;
      case 14:
        paramTextGenerator.print("\"");
        paramTextGenerator.print(TextFormat.escapeText((String)paramObject));
        paramTextGenerator.print("\"");
        break;
      case 15:
        paramTextGenerator.print("\"");
        paramTextGenerator.print(TextFormat.escapeBytes((ByteString)paramObject));
        paramTextGenerator.print("\"");
        break;
      case 16:
        paramTextGenerator.print(((Descriptors.EnumValueDescriptor)paramObject).getName());
        break;
      case 17:
      case 18:
        print((Message)paramObject, paramTextGenerator);
      }
    }

    private void printUnknownFields(UnknownFieldSet paramUnknownFieldSet, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator1 = paramUnknownFieldSet.asMap().entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        int i = ((Integer)localEntry.getKey()).intValue();
        UnknownFieldSet.Field localField = (UnknownFieldSet.Field)localEntry.getValue();
        printUnknownField(i, 0, localField.getVarintList(), paramTextGenerator);
        printUnknownField(i, 5, localField.getFixed32List(), paramTextGenerator);
        printUnknownField(i, 1, localField.getFixed64List(), paramTextGenerator);
        printUnknownField(i, 2, localField.getLengthDelimitedList(), paramTextGenerator);
        Iterator localIterator2 = localField.getGroupList().iterator();
        while (localIterator2.hasNext())
        {
          UnknownFieldSet localUnknownFieldSet = (UnknownFieldSet)localIterator2.next();
          paramTextGenerator.print(((Integer)localEntry.getKey()).toString());
          if (this.singleLineMode)
          {
            paramTextGenerator.print(" { ");
          }
          else
          {
            paramTextGenerator.print(" {\n");
            paramTextGenerator.indent();
          }
          printUnknownFields(localUnknownFieldSet, paramTextGenerator);
          if (this.singleLineMode)
          {
            paramTextGenerator.print("} ");
          }
          else
          {
            paramTextGenerator.outdent();
            paramTextGenerator.print("}\n");
          }
        }
      }
    }

    private void printUnknownField(int paramInt1, int paramInt2, List paramList, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        paramTextGenerator.print(String.valueOf(paramInt1));
        paramTextGenerator.print(": ");
        TextFormat.printUnknownFieldValue(paramInt2, localObject, paramTextGenerator);
        paramTextGenerator.print(this.singleLineMode ? " " : "\n");
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.TextFormat
 * JD-Core Version:    0.6.2
 */