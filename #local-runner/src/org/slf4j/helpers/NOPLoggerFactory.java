package org.slf4j.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class NOPLoggerFactory
  implements ILoggerFactory
{
  public Logger getLogger(String paramString)
  {
    return NOPLogger.NOP_LOGGER;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.helpers.NOPLoggerFactory
 * JD-Core Version:    0.6.2
 */