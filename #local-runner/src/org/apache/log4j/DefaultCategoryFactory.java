package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

class DefaultCategoryFactory
  implements LoggerFactory
{
  public Logger makeNewLoggerInstance(String paramString)
  {
    return new Logger(paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.DefaultCategoryFactory
 * JD-Core Version:    0.6.2
 */