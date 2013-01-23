package org.apache.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileUtils
{
  public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
  public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
  public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
  public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
  public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
  public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
  public static final BigInteger ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
  public static final BigInteger ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
  public static final File[] EMPTY_FILE_ARRAY = new File[0];
  private static final Charset UTF8 = Charset.forName("UTF-8");

  public static FileOutputStream openOutputStream(File paramFile)
    throws IOException
  {
    return openOutputStream(paramFile, false);
  }

  public static FileOutputStream openOutputStream(File paramFile, boolean paramBoolean)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory())
        throw new IOException("File '" + paramFile + "' exists but is a directory");
      if (!paramFile.canWrite())
        throw new IOException("File '" + paramFile + "' cannot be written to");
    }
    else
    {
      File localFile = paramFile.getParentFile();
      if ((localFile != null) && (!localFile.mkdirs()) && (!localFile.isDirectory()))
        throw new IOException("Directory '" + localFile + "' could not be created");
    }
    return new FileOutputStream(paramFile, paramBoolean);
  }

  public static void copyFile(File paramFile1, File paramFile2)
    throws IOException
  {
    copyFile(paramFile1, paramFile2, true);
  }

  public static void copyFile(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    if (paramFile1.isDirectory())
      throw new IOException("Source '" + paramFile1 + "' exists but is a directory");
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath()))
      throw new IOException("Source '" + paramFile1 + "' and destination '" + paramFile2 + "' are the same");
    File localFile = paramFile2.getParentFile();
    if ((localFile != null) && (!localFile.mkdirs()) && (!localFile.isDirectory()))
      throw new IOException("Destination '" + localFile + "' directory cannot be created");
    if ((paramFile2.exists()) && (!paramFile2.canWrite()))
      throw new IOException("Destination '" + paramFile2 + "' exists but is read-only");
    doCopyFile(paramFile1, paramFile2, paramBoolean);
  }

  private static void doCopyFile(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if ((paramFile2.exists()) && (paramFile2.isDirectory()))
      throw new IOException("Destination '" + paramFile2 + "' exists but is a directory");
    FileInputStream localFileInputStream = null;
    FileOutputStream localFileOutputStream = null;
    FileChannel localFileChannel1 = null;
    FileChannel localFileChannel2 = null;
    try
    {
      localFileInputStream = new FileInputStream(paramFile1);
      localFileOutputStream = new FileOutputStream(paramFile2);
      localFileChannel1 = localFileInputStream.getChannel();
      localFileChannel2 = localFileOutputStream.getChannel();
      long l1 = localFileChannel1.size();
      long l2 = 0L;
      long l3 = 0L;
      while (l2 < l1)
      {
        l3 = l1 - l2 > 31457280L ? 31457280L : l1 - l2;
        l2 += localFileChannel2.transferFrom(localFileChannel1, l2, l3);
      }
    }
    finally
    {
      IOUtils.closeQuietly(localFileChannel2);
      IOUtils.closeQuietly(localFileOutputStream);
      IOUtils.closeQuietly(localFileChannel1);
      IOUtils.closeQuietly(localFileInputStream);
    }
    if (paramFile1.length() != paramFile2.length())
      throw new IOException("Failed to copy full contents from '" + paramFile1 + "' to '" + paramFile2 + "'");
    if (paramBoolean)
      paramFile2.setLastModified(paramFile1.lastModified());
  }

  public static void copyInputStreamToFile(InputStream paramInputStream, File paramFile)
    throws IOException
  {
    try
    {
      FileOutputStream localFileOutputStream = openOutputStream(paramFile);
      try
      {
        IOUtils.copy(paramInputStream, localFileOutputStream);
        localFileOutputStream.close();
      }
      finally
      {
      }
    }
    finally
    {
      IOUtils.closeQuietly(paramInputStream);
    }
  }

  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte)
    throws IOException
  {
    writeByteArrayToFile(paramFile, paramArrayOfByte, false);
  }

  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      localFileOutputStream.write(paramArrayOfByte);
      localFileOutputStream.close();
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
  }

  public static void forceMkdir(File paramFile)
    throws IOException
  {
    String str;
    if (paramFile.exists())
    {
      if (!paramFile.isDirectory())
      {
        str = "File " + paramFile + " exists and is " + "not a directory. Unable to create directory.";
        throw new IOException(str);
      }
    }
    else if ((!paramFile.mkdirs()) && (!paramFile.isDirectory()))
    {
      str = "Unable to create directory " + paramFile;
      throw new IOException(str);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.io.FileUtils
 * JD-Core Version:    0.6.2
 */