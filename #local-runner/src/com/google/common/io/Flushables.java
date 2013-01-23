package com.google.common.io;

import com.google.common.annotations.Beta;
import java.io.Flushable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public final class Flushables
{
  private static final Logger logger = Logger.getLogger(Flushables.class.getName());

  public static void flush(Flushable paramFlushable, boolean paramBoolean)
    throws IOException
  {
    try
    {
      paramFlushable.flush();
    }
    catch (IOException localIOException)
    {
      if (paramBoolean)
        logger.log(Level.WARNING, "IOException thrown while flushing Flushable.", localIOException);
      else
        throw localIOException;
    }
  }

  public static void flushQuietly(Flushable paramFlushable)
  {
    try
    {
      flush(paramFlushable, true);
    }
    catch (IOException localIOException)
    {
      logger.log(Level.SEVERE, "IOException should not have been thrown.", localIOException);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.Flushables
 * JD-Core Version:    0.6.2
 */