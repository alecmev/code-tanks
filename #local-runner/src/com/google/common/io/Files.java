package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Checksum;

@Beta
public final class Files
{
  private static final int TEMP_DIR_ATTEMPTS = 10000;

  public static BufferedReader newReader(File paramFile, Charset paramCharset)
    throws FileNotFoundException
  {
    return new BufferedReader(new InputStreamReader(new FileInputStream(paramFile), paramCharset));
  }

  public static BufferedWriter newWriter(File paramFile, Charset paramCharset)
    throws FileNotFoundException
  {
    return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(paramFile), paramCharset));
  }

  public static InputSupplier newInputStreamSupplier(File paramFile)
  {
    Preconditions.checkNotNull(paramFile);
    return new InputSupplier()
    {
      public FileInputStream getInput()
        throws IOException
      {
        return new FileInputStream(this.val$file);
      }
    };
  }

  public static OutputSupplier newOutputStreamSupplier(File paramFile)
  {
    return newOutputStreamSupplier(paramFile, false);
  }

  public static OutputSupplier newOutputStreamSupplier(File paramFile, final boolean paramBoolean)
  {
    Preconditions.checkNotNull(paramFile);
    return new OutputSupplier()
    {
      public FileOutputStream getOutput()
        throws IOException
      {
        return new FileOutputStream(this.val$file, paramBoolean);
      }
    };
  }

  public static InputSupplier newReaderSupplier(File paramFile, Charset paramCharset)
  {
    return CharStreams.newReaderSupplier(newInputStreamSupplier(paramFile), paramCharset);
  }

  public static OutputSupplier newWriterSupplier(File paramFile, Charset paramCharset)
  {
    return newWriterSupplier(paramFile, paramCharset, false);
  }

  public static OutputSupplier newWriterSupplier(File paramFile, Charset paramCharset, boolean paramBoolean)
  {
    return CharStreams.newWriterSupplier(newOutputStreamSupplier(paramFile, paramBoolean), paramCharset);
  }

  public static byte[] toByteArray(File paramFile)
    throws IOException
  {
    Preconditions.checkArgument(paramFile.length() <= 2147483647L);
    if (paramFile.length() == 0L)
      return ByteStreams.toByteArray(newInputStreamSupplier(paramFile));
    byte[] arrayOfByte = new byte[(int)paramFile.length()];
    boolean bool = true;
    FileInputStream localFileInputStream = new FileInputStream(paramFile);
    try
    {
      ByteStreams.readFully(localFileInputStream, arrayOfByte);
      bool = false;
    }
    finally
    {
      Closeables.close(localFileInputStream, bool);
    }
    return arrayOfByte;
  }

  public static String toString(File paramFile, Charset paramCharset)
    throws IOException
  {
    return new String(toByteArray(paramFile), paramCharset.name());
  }

  public static void copy(InputSupplier paramInputSupplier, File paramFile)
    throws IOException
  {
    ByteStreams.copy(paramInputSupplier, newOutputStreamSupplier(paramFile));
  }

  public static void write(byte[] paramArrayOfByte, File paramFile)
    throws IOException
  {
    ByteStreams.write(paramArrayOfByte, newOutputStreamSupplier(paramFile));
  }

  public static void copy(File paramFile, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    ByteStreams.copy(newInputStreamSupplier(paramFile), paramOutputSupplier);
  }

  public static void copy(File paramFile, OutputStream paramOutputStream)
    throws IOException
  {
    ByteStreams.copy(newInputStreamSupplier(paramFile), paramOutputStream);
  }

  public static void copy(File paramFile1, File paramFile2)
    throws IOException
  {
    Preconditions.checkArgument(!paramFile1.equals(paramFile2), "Source %s and destination %s must be different", new Object[] { paramFile1, paramFile2 });
    copy(newInputStreamSupplier(paramFile1), paramFile2);
  }

  public static void copy(InputSupplier paramInputSupplier, File paramFile, Charset paramCharset)
    throws IOException
  {
    CharStreams.copy(paramInputSupplier, newWriterSupplier(paramFile, paramCharset));
  }

  public static void write(CharSequence paramCharSequence, File paramFile, Charset paramCharset)
    throws IOException
  {
    write(paramCharSequence, paramFile, paramCharset, false);
  }

  public static void append(CharSequence paramCharSequence, File paramFile, Charset paramCharset)
    throws IOException
  {
    write(paramCharSequence, paramFile, paramCharset, true);
  }

  private static void write(CharSequence paramCharSequence, File paramFile, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    CharStreams.write(paramCharSequence, newWriterSupplier(paramFile, paramCharset, paramBoolean));
  }

  public static void copy(File paramFile, Charset paramCharset, OutputSupplier paramOutputSupplier)
    throws IOException
  {
    CharStreams.copy(newReaderSupplier(paramFile, paramCharset), paramOutputSupplier);
  }

  public static void copy(File paramFile, Charset paramCharset, Appendable paramAppendable)
    throws IOException
  {
    CharStreams.copy(newReaderSupplier(paramFile, paramCharset), paramAppendable);
  }

  public static boolean equal(File paramFile1, File paramFile2)
    throws IOException
  {
    if ((paramFile1 == paramFile2) || (paramFile1.equals(paramFile2)))
      return true;
    long l1 = paramFile1.length();
    long l2 = paramFile2.length();
    if ((l1 != 0L) && (l2 != 0L) && (l1 != l2))
      return false;
    return ByteStreams.equal(newInputStreamSupplier(paramFile1), newInputStreamSupplier(paramFile2));
  }

  public static File createTempDir()
  {
    File localFile1 = new File(System.getProperty("java.io.tmpdir"));
    String str = System.currentTimeMillis() + "-";
    for (int i = 0; i < 10000; i++)
    {
      File localFile2 = new File(localFile1, str + i);
      if (localFile2.mkdir())
        return localFile2;
    }
    throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + str + "0 to " + str + 9999 + ')');
  }

  public static void touch(File paramFile)
    throws IOException
  {
    if ((!paramFile.createNewFile()) && (!paramFile.setLastModified(System.currentTimeMillis())))
      throw new IOException("Unable to update modification time of " + paramFile);
  }

  public static void createParentDirs(File paramFile)
    throws IOException
  {
    File localFile = paramFile.getCanonicalFile().getParentFile();
    if (localFile == null)
      return;
    localFile.mkdirs();
    if (!localFile.isDirectory())
      throw new IOException("Unable to create parent directories of " + paramFile);
  }

  public static void move(File paramFile1, File paramFile2)
    throws IOException
  {
    Preconditions.checkNotNull(paramFile2);
    Preconditions.checkArgument(!paramFile1.equals(paramFile2), "Source %s and destination %s must be different", new Object[] { paramFile1, paramFile2 });
    if (!paramFile1.renameTo(paramFile2))
    {
      copy(paramFile1, paramFile2);
      if (!paramFile1.delete())
      {
        if (!paramFile2.delete())
          throw new IOException("Unable to delete " + paramFile2);
        throw new IOException("Unable to delete " + paramFile1);
      }
    }
  }

  public static String readFirstLine(File paramFile, Charset paramCharset)
    throws IOException
  {
    return CharStreams.readFirstLine(newReaderSupplier(paramFile, paramCharset));
  }

  public static List readLines(File paramFile, Charset paramCharset)
    throws IOException
  {
    return CharStreams.readLines(newReaderSupplier(paramFile, paramCharset));
  }

  public static Object readLines(File paramFile, Charset paramCharset, LineProcessor paramLineProcessor)
    throws IOException
  {
    return CharStreams.readLines(newReaderSupplier(paramFile, paramCharset), paramLineProcessor);
  }

  public static Object readBytes(File paramFile, ByteProcessor paramByteProcessor)
    throws IOException
  {
    return ByteStreams.readBytes(newInputStreamSupplier(paramFile), paramByteProcessor);
  }

  public static long getChecksum(File paramFile, Checksum paramChecksum)
    throws IOException
  {
    return ByteStreams.getChecksum(newInputStreamSupplier(paramFile), paramChecksum);
  }

  @Deprecated
  public static byte[] getDigest(File paramFile, MessageDigest paramMessageDigest)
    throws IOException
  {
    return ByteStreams.getDigest(newInputStreamSupplier(paramFile), paramMessageDigest);
  }

  public static HashCode hash(File paramFile, HashFunction paramHashFunction)
    throws IOException
  {
    return ByteStreams.hash(newInputStreamSupplier(paramFile), paramHashFunction);
  }

  public static MappedByteBuffer map(File paramFile)
    throws IOException
  {
    return map(paramFile, FileChannel.MapMode.READ_ONLY);
  }

  public static MappedByteBuffer map(File paramFile, FileChannel.MapMode paramMapMode)
    throws IOException
  {
    if (!paramFile.exists())
      throw new FileNotFoundException(paramFile.toString());
    return map(paramFile, paramMapMode, paramFile.length());
  }

  public static MappedByteBuffer map(File paramFile, FileChannel.MapMode paramMapMode, long paramLong)
    throws FileNotFoundException, IOException
  {
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramFile, paramMapMode == FileChannel.MapMode.READ_ONLY ? "r" : "rw");
    boolean bool = true;
    try
    {
      MappedByteBuffer localMappedByteBuffer1 = map(localRandomAccessFile, paramMapMode, paramLong);
      bool = false;
      MappedByteBuffer localMappedByteBuffer2 = localMappedByteBuffer1;
      return localMappedByteBuffer2;
    }
    finally
    {
      Closeables.close(localRandomAccessFile, bool);
    }
  }

  private static MappedByteBuffer map(RandomAccessFile paramRandomAccessFile, FileChannel.MapMode paramMapMode, long paramLong)
    throws IOException
  {
    FileChannel localFileChannel = paramRandomAccessFile.getChannel();
    boolean bool = true;
    try
    {
      MappedByteBuffer localMappedByteBuffer1 = localFileChannel.map(paramMapMode, 0L, paramLong);
      bool = false;
      MappedByteBuffer localMappedByteBuffer2 = localMappedByteBuffer1;
      return localMappedByteBuffer2;
    }
    finally
    {
      Closeables.close(localFileChannel, bool);
    }
  }

  public static String simplifyPath(String paramString)
  {
    if (paramString.length() == 0)
      return ".";
    Iterable localIterable = Splitter.on('/').omitEmptyStrings().split(paramString);
    ArrayList localArrayList = new ArrayList();
    Object localObject = localIterable.iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!str.equals("."))
        if (str.equals(".."))
        {
          if ((localArrayList.size() > 0) && (!((String)localArrayList.get(localArrayList.size() - 1)).equals("..")))
            localArrayList.remove(localArrayList.size() - 1);
          else
            localArrayList.add("..");
        }
        else
          localArrayList.add(str);
    }
    localObject = Joiner.on('/').join(localArrayList);
    if (paramString.charAt(0) == '/');
    for (localObject = "/" + (String)localObject; ((String)localObject).startsWith("/../"); localObject = ((String)localObject).substring(3));
    if (((String)localObject).equals("/.."))
      localObject = "/";
    else if ("".equals(localObject))
      localObject = ".";
    return localObject;
  }

  public static String getFileExtension(String paramString)
  {
    Preconditions.checkNotNull(paramString);
    int i = paramString.lastIndexOf('.');
    return i == -1 ? "" : paramString.substring(i + 1);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.Files
 * JD-Core Version:    0.6.2
 */