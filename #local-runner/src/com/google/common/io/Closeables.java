package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public final class Closeables
{

  @VisibleForTesting
  static final Logger logger = Logger.getLogger(Closeables.class.getName());

  public static void close(Closeable paramCloseable, boolean paramBoolean)
    throws IOException
  {
    if (paramCloseable == null)
      return;
    try
    {
      paramCloseable.close();
    }
    catch (IOException localIOException)
    {
      if (paramBoolean)
        logger.log(Level.WARNING, "IOException thrown while closing Closeable.", localIOException);
      else
        throw localIOException;
    }
  }

  public static void closeQuietly(Closeable paramCloseable)
  {
    try
    {
      close(paramCloseable, true);
    }
    catch (IOException localIOException)
    {
      logger.log(Level.SEVERE, "IOException should not have been thrown.", localIOException);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.Closeables
 * JD-Core Version:    0.6.2
 */