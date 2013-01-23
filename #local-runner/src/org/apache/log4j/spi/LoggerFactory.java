package org.apache.log4j.spi;

import org.apache.log4j.Logger;

public abstract interface LoggerFactory
{
  public abstract Logger makeNewLoggerInstance(String paramString);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.LoggerFactory
 * JD-Core Version:    0.6.2
 */