package com.a.c.a.a;

import com.google.common.primitives.Ints;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class a
{
  private static final int a = Ints.checkedCast(1048576L);

  private a()
  {
    throw new UnsupportedOperationException();
  }

  private static void a(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    try
    {
      byte[] arrayOfByte = new byte[a];
      int i;
      while ((i = paramInputStream.read(arrayOfByte)) >= 0)
        paramOutputStream.write(arrayOfByte, 0, i);
    }
    finally
    {
      paramInputStream.close();
      paramOutputStream.close();
    }
  }

  public static void a(File paramFile1, File paramFile2)
    throws IOException
  {
    ZipFile localZipFile = null;
    try
    {
      localZipFile = new ZipFile(paramFile1);
      Enumeration localEnumeration = localZipFile.entries();
      for (int i = 0; (localEnumeration.hasMoreElements()) && (i < 25000L); i++)
      {
        ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
        File localFile;
        if (localZipEntry.isDirectory())
        {
          localFile = new File(paramFile2, localZipEntry.getName());
          if ((!localFile.isDirectory()) && (!localFile.mkdirs()))
            throw new IOException("Can't create directory while unzipping archive.");
        }
        else if ((localZipEntry.getSize() <= 67108864L) && (localZipEntry.getCompressedSize() <= 67108864L))
        {
          localFile = new File(paramFile2, localZipEntry.getName());
          if ((!localFile.getParentFile().isDirectory()) && (!localFile.getParentFile().mkdirs()))
            throw new IOException("Can't create directory '" + localFile.getParentFile() + "'.");
          a(localZipFile.getInputStream(localZipEntry), new BufferedOutputStream(new FileOutputStream(localFile)));
        }
        else
        {
          long l = Math.max(localZipEntry.getSize(), localZipEntry.getCompressedSize());
          throw new IOException("File '" + localZipEntry.getName() + "' is too large: " + l + " B.");
        }
      }
    }
    finally
    {
      if (localZipFile != null)
        localZipFile.close();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.c.a.a.a
 * JD-Core Version:    0.6.2
 */