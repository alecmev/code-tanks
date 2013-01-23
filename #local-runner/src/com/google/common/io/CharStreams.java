package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Beta
public final class CharStreams
{
  private static final int BUF_SIZE = 2048;

  public static InputSupplier newReaderSupplier(String paramString)
  {
    Preconditions.checkNotNull(paramString);
    return new InputSupplier()
    {
      public StringReader getInput()
      {
        return new StringReader(this.val$value);
      }
    };
  }

  public static InputSupplier newReaderSupplier(InputSupplier paramInputSupplier, final Charset paramCharset)
  {
    Preconditions.checkNotNull(paramInputSupplier);
    Preconditions.checkNotNull(paramCharset);
    return new InputSupplier()
    {
      public InputStreamReader getInput()
        throws IOException
      {
        return new InputStreamReader((InputStream)this.val$in.getInput(), paramCharset);
      }
    };
  }

  public static OutputSupplier newWriterSupplier(OutputSupplier paramOutputSupplier, final Charset paramCharset)
  {
    Preconditions.checkNotNull(paramOutputSupplier);
    Preconditions.checkNotNull(paramCharset);
    return new OutputSupplier()
    {
      public OutputStreamWriter getOutput()
        throws IOException
      {
        return new OutputStreamWriter((OutputStream)this.val$out.getOutput(), paramCharset);
      }
    };
  }

  public static void write(CharSequence paramCharSequence, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    Preconditions.checkNotNull(paramCharSequence);
    boolean bool = true;
    Appendable localAppendable = (Appendable)paramOutputSupplier.getOutput();
    try
    {
      localAppendable.append(paramCharSequence);
      bool = false;
    }
    finally
    {
      Closeables.close((Closeable)localAppendable, bool);
    }
  }

  public static long copy(InputSupplier paramInputSupplier, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    int i = 0;
    Readable localReadable = (Readable)paramInputSupplier.getInput();
    try
    {
      Appendable localAppendable = (Appendable)paramOutputSupplier.getOutput();
      try
      {
        long l1 = copy(localReadable, localAppendable);
        i++;
        long l2 = l1;
        Closeables.close((Closeable)localAppendable, i < 1);
        i++;
        Closeables.close((Closeable)localReadable, i < 2);
        return l2;
      }
      finally
      {
        Closeables.close((Closeable)localAppendable, i < 1);
        i++;
      }
    }
    finally
    {
      Closeables.close((Closeable)localReadable, i < 2);
    }
  }

  public static long copy(InputSupplier paramInputSupplier, Appendable paramAppendable)
    throws IOException
  {
    boolean bool = true;
    Readable localReadable = (Readable)paramInputSupplier.getInput();
    try
    {
      long l1 = copy(localReadable, paramAppendable);
      bool = false;
      long l2 = l1;
      return l2;
    }
    finally
    {
      Closeables.close((Closeable)localReadable, bool);
    }
  }

  public static long copy(Readable paramReadable, Appendable paramAppendable)
    throws IOException
  {
    CharBuffer localCharBuffer = CharBuffer.allocate(2048);
    int i;
    for (long l = 0L; ; l += i)
    {
      i = paramReadable.read(localCharBuffer);
      if (i == -1)
        break;
      localCharBuffer.flip();
      paramAppendable.append(localCharBuffer, 0, i);
    }
    return l;
  }

  public static String toString(Readable paramReadable)
    throws IOException
  {
    return toStringBuilder(paramReadable).toString();
  }

  public static String toString(InputSupplier paramInputSupplier)
    throws IOException
  {
    return toStringBuilder(paramInputSupplier).toString();
  }

  private static StringBuilder toStringBuilder(Readable paramReadable)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    copy(paramReadable, localStringBuilder);
    return localStringBuilder;
  }

  private static StringBuilder toStringBuilder(InputSupplier paramInputSupplier)
    throws IOException
  {
    boolean bool = true;
    Readable localReadable = (Readable)paramInputSupplier.getInput();
    try
    {
      StringBuilder localStringBuilder1 = toStringBuilder(localReadable);
      bool = false;
      StringBuilder localStringBuilder2 = localStringBuilder1;
      return localStringBuilder2;
    }
    finally
    {
      Closeables.close((Closeable)localReadable, bool);
    }
  }

  public static String readFirstLine(InputSupplier paramInputSupplier)
    throws IOException
  {
    boolean bool = true;
    Readable localReadable = (Readable)paramInputSupplier.getInput();
    try
    {
      String str1 = new LineReader(localReadable).readLine();
      bool = false;
      String str2 = str1;
      return str2;
    }
    finally
    {
      Closeables.close((Closeable)localReadable, bool);
    }
  }

  public static List readLines(InputSupplier paramInputSupplier)
    throws IOException
  {
    boolean bool = true;
    Readable localReadable = (Readable)paramInputSupplier.getInput();
    try
    {
      List localList1 = readLines(localReadable);
      bool = false;
      List localList2 = localList1;
      return localList2;
    }
    finally
    {
      Closeables.close((Closeable)localReadable, bool);
    }
  }

  public static List readLines(Readable paramReadable)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    LineReader localLineReader = new LineReader(paramReadable);
    String str;
    while ((str = localLineReader.readLine()) != null)
      localArrayList.add(str);
    return localArrayList;
  }

  public static Object readLines(InputSupplier paramInputSupplier, LineProcessor paramLineProcessor)
    throws IOException
  {
    boolean bool = true;
    Readable localReadable = (Readable)paramInputSupplier.getInput();
    try
    {
      LineReader localLineReader = new LineReader(localReadable);
      String str;
      while ((str = localLineReader.readLine()) != null)
        if (!paramLineProcessor.processLine(str))
          break;
      bool = false;
    }
    finally
    {
      Closeables.close((Closeable)localReadable, bool);
    }
    return paramLineProcessor.getResult();
  }

  public static InputSupplier join(Iterable paramIterable)
  {
    return new InputSupplier()
    {
      public Reader getInput()
        throws IOException
      {
        return new MultiReader(this.val$suppliers.iterator());
      }
    };
  }

  public static InputSupplier join(InputSupplier[] paramArrayOfInputSupplier)
  {
    return join(Arrays.asList(paramArrayOfInputSupplier));
  }

  public static void skipFully(Reader paramReader, long paramLong)
    throws IOException
  {
    while (paramLong > 0L)
    {
      long l = paramReader.skip(paramLong);
      if (l == 0L)
      {
        if (paramReader.read() == -1)
          throw new EOFException();
        paramLong -= 1L;
      }
      else
      {
        paramLong -= l;
      }
    }
  }

  public static Writer asWriter(Appendable paramAppendable)
  {
    if ((paramAppendable instanceof Writer))
      return (Writer)paramAppendable;
    return new AppendableWriter(paramAppendable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.CharStreams
 * JD-Core Version:    0.6.2
 */