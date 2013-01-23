package com.google.inject.internal.util;

import java.util.logging.Logger;

public final class $Stopwatch
{
  private static final Logger logger = Logger.getLogger(Stopwatch.class.getName());
  private long start = System.currentTimeMillis();

  public long reset()
  {
    long l1 = System.currentTimeMillis();
    try
    {
      long l2 = l1 - this.start;
      return l2;
    }
    finally
    {
      this.start = l1;
    }
  }

  public void resetAndLog(String paramString)
  {
    logger.fine(paramString + ": " + reset() + "ms");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Stopwatch
 * JD-Core Version:    0.6.2
 */