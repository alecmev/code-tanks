package org.apache.log4j.spi;

import java.net.URL;

public abstract interface Configurator
{
  public abstract void doConfigure(URL paramURL, LoggerRepository paramLoggerRepository);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.Configurator
 * JD-Core Version:    0.6.2
 */