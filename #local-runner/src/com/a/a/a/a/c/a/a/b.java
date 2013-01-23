package com.a.a.a.a.c.a.a;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

final class b
  implements Runnable
{
  b(InputStream paramInputStream, File paramFile, Process paramProcess)
  {
  }

  public void run()
  {
    try
    {
      FileUtils.copyInputStreamToFile(this.a, this.b);
    }
    catch (IOException localIOException)
    {
    }
    e.b().debug("Completed to write stream from " + this.c + " to " + this.b + '.');
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.a.b
 * JD-Core Version:    0.6.2
 */