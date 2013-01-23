package org.apache.commons.io;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;

public class IOUtils
{
  public static final char DIR_SEPARATOR = File.separatorChar;
  public static final String LINE_SEPARATOR;

  public static void closeQuietly(InputStream paramInputStream)
  {
    closeQuietly(paramInputStream);
  }

  public static void closeQuietly(OutputStream paramOutputStream)
  {
    closeQuietly(paramOutputStream);
  }

  public static void closeQuietly(Closeable paramCloseable)
  {
    try
    {
      if (paramCloseable != null)
        paramCloseable.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null)
      try
      {
        paramSocket.close();
      }
      catch (IOException localIOException)
      {
      }
  }

  public static byte[] toByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Size must be equal or greater than zero: " + paramInt);
    if (paramInt == 0)
      return new byte[0];
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    int j;
    while ((i < paramInt) && ((j = paramInputStream.read(arrayOfByte, i, paramInt - i)) != -1))
      i += j;
    if (i != paramInt)
      throw new IOException("Unexpected readed size. current: " + i + ", excepted: " + paramInt);
    return arrayOfByte;
  }

  public static void write(byte[] paramArrayOfByte, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramArrayOfByte != null)
      paramOutputStream.write(paramArrayOfByte);
  }

  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    long l = copyLarge(paramInputStream, paramOutputStream);
    if (l > 2147483647L)
      return -1;
    return (int)l;
  }

  public static long copyLarge(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    return copyLarge(paramInputStream, paramOutputStream, new byte[4096]);
  }

  public static long copyLarge(InputStream paramInputStream, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    long l = 0L;
    int i = 0;
    while (-1 != (i = paramInputStream.read(paramArrayOfByte)))
    {
      paramOutputStream.write(paramArrayOfByte, 0, i);
      l += i;
    }
    return l;
  }

  static
  {
    StringBuilderWriter localStringBuilderWriter = new StringBuilderWriter(4);
    PrintWriter localPrintWriter = new PrintWriter(localStringBuilderWriter);
    localPrintWriter.println();
    LINE_SEPARATOR = localStringBuilderWriter.toString();
    localPrintWriter.close();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.io.IOUtils
 * JD-Core Version:    0.6.2
 */