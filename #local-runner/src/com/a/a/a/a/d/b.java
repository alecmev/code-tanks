package com.a.a.a.a.d;

import com.a.a.a.a.a.g;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.commons.io.FileUtils;

public class b
  implements h
{
  private final Writer a;

  public b(File paramFile)
    throws IOException
  {
    File localFile = paramFile.getParentFile();
    if (localFile != null)
      FileUtils.forceMkdir(localFile);
    this.a = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(paramFile, false)), "UTF-8");
  }

  public void a(g paramg)
    throws IOException
  {
    this.a.write(com.a.a.a.a.b.b.b.a(paramg));
    this.a.write(10);
  }

  public void close()
    throws IOException
  {
    this.a.close();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.d.b
 * JD-Core Version:    0.6.2
 */