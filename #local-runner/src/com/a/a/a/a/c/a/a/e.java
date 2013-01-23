package com.a.a.a.a.c.a.a;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class e
{
  private static final Logger a = LoggerFactory.getLogger(e.class);
  private final String b;
  private final Process c;
  private final File d;
  private final AtomicBoolean e = new AtomicBoolean();

  private e(String paramString, Process paramProcess, File paramFile)
  {
    this.b = paramString;
    this.c = paramProcess;
    this.d = paramFile;
  }

  public void a(long paramLong)
  {
    Thread localThread = new Thread(new a(this));
    localThread.start();
    try
    {
      localThread.join(paramLong);
    }
    catch (InterruptedException localInterruptedException)
    {
      localThread.interrupt();
    }
  }

  public void a()
  {
    if (!this.e.compareAndSet(false, true))
      return;
    this.c.destroy();
    try
    {
      this.c.waitFor();
      a.info("Process finished with exit code '" + this.c.exitValue() + "'.");
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }

  protected void finalize()
    throws Throwable
  {
    if (!this.e.get())
      a.error(String.format("Process '%s' in directory '%s' has not been destroyed.", new Object[] { this.b, this.d.getAbsolutePath() }));
    a();
    super.finalize();
  }

  public static e a(String paramString, Map paramMap, String[] paramArrayOfString)
    throws IOException
  {
    File localFile1 = new File(paramString);
    File localFile2 = a(localFile1.getParentFile());
    String str = FilenameUtils.getExtension(localFile1.getName());
    g localg;
    if ("zip".equalsIgnoreCase(str))
    {
      com.a.c.a.a.a.a(localFile1, localFile2);
      localg = c.a(localFile1.getName().substring(0, localFile1.getName().length() - ".zip".length()));
    }
    else
    {
      localObject = new File(localFile2, localFile1.getName());
      FileUtils.copyFile(localFile1, (File)localObject);
      localg = c.a(paramString);
    }
    a(localg, localFile2, paramMap);
    Object localObject = localg.a(FilenameUtils.getName(paramString), paramMap);
    ArrayList localArrayList = new ArrayList(Arrays.asList(a(localFile2, (String)localObject)));
    Collections.addAll(localArrayList, paramArrayOfString);
    if (!new File((String)localArrayList.get(0)).isAbsolute())
      localArrayList.set(0, new File(localFile2, (String)localArrayList.get(0)).getAbsolutePath());
    Process localProcess = new ProcessBuilder(localArrayList).directory(localFile2).start();
    a(localProcess, localProcess.getInputStream(), new File(localFile2, "runexe.output"));
    a(localProcess, localProcess.getErrorStream(), new File(localFile2, "runexe.error"));
    a(localArrayList, localFile2);
    return new e((String)localObject, localProcess, localFile2);
  }

  private static void a(Process paramProcess, InputStream paramInputStream, File paramFile)
  {
    new Thread(new b(paramInputStream, paramFile, paramProcess)).start();
  }

  private static void a(List paramList, File paramFile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append(' ');
      localStringBuilder.append('"').append(str).append('"');
    }
    a.info("Running '" + localStringBuilder + "' in the '" + paramFile + "'.");
  }

  private static void a(g paramg, File paramFile, Map paramMap)
    throws IOException
  {
    Iterator localIterator1 = paramg.a().iterator();
    while (localIterator1.hasNext())
    {
      String str1 = (String)localIterator1.next();
      String str2 = FilenameUtils.getName(str1);
      BufferedOutputStream localBufferedOutputStream = null;
      InputStream localInputStream = null;
      try
      {
        File localFile = new File(paramFile, str2);
        localInputStream = e.class.getResourceAsStream(str1);
        localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile));
        if ((paramMap != null) && (paramg.a(str1)))
        {
          String str3 = new String(IOUtils.toByteArray(localInputStream), "UTF-8");
          Iterator localIterator2 = paramMap.entrySet().iterator();
          while (localIterator2.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator2.next();
            str3 = str3.replace("${" + (String)localEntry.getKey() + '}', (CharSequence)localEntry.getValue());
          }
          IOUtils.write(str3.getBytes("UTF-8"), localBufferedOutputStream);
        }
        else
        {
          IOUtils.copy(localInputStream, localBufferedOutputStream);
        }
      }
      finally
      {
        IOUtils.closeQuietly(localInputStream);
        IOUtils.closeQuietly(localBufferedOutputStream);
      }
    }
  }

  private static File a(File paramFile)
    throws IOException
  {
    File localFile = new File(paramFile, RandomStringUtils.randomAlphanumeric(10));
    if (!localFile.mkdirs())
      throw new IOException("Can't create temporary directory '" + localFile + "'.");
    return localFile;
  }

  private static String[] a(File paramFile, String paramString)
  {
    if (new File(paramFile, paramString).exists())
      return new String[] { paramString };
    paramString = paramString + " ";
    int i = 0;
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < paramString.length(); k++)
    {
      char c1 = paramString.charAt(k);
      if (c1 == '\\')
      {
        i ^= 1;
        if (i == 0)
          localStringBuilder.append('\\');
      }
      else
      {
        if (c1 == '"')
        {
          if (i == 0)
            j = j == 0 ? 1 : 0;
          else
            localStringBuilder.append('"');
        }
        else
        {
          if (i == 1)
            localStringBuilder.append('\\');
          if ((c1 <= ' ') && (j == 0))
          {
            if (localStringBuilder.length() > 0)
            {
              localArrayList.add(localStringBuilder.toString());
              localStringBuilder.setLength(0);
            }
          }
          else
            localStringBuilder.append(c1);
        }
        i = 0;
      }
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.a.e
 * JD-Core Version:    0.6.2
 */