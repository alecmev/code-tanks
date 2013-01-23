package org.slf4j.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.slf4j.ILoggerFactory;

public class Log4jLoggerFactory
  implements ILoggerFactory
{
  Map loggerMap = new HashMap();

  public org.slf4j.Logger getLogger(String paramString)
  {
    Object localObject1 = null;
    synchronized (this)
    {
      localObject1 = (org.slf4j.Logger)this.loggerMap.get(paramString);
      if (localObject1 == null)
      {
        org.apache.log4j.Logger localLogger;
        if (paramString.equalsIgnoreCase("ROOT"))
          localLogger = LogManager.getRootLogger();
        else
          localLogger = LogManager.getLogger(paramString);
        localObject1 = new Log4jLoggerAdapter(localLogger);
        this.loggerMap.put(paramString, localObject1);
      }
    }
    return localObject1;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.impl.Log4jLoggerFactory
 * JD-Core Version:    0.6.2
 */