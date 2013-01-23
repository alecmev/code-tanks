package org.slf4j.impl;

import java.io.Serializable;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.spi.LocationAwareLogger;

public final class Log4jLoggerAdapter extends MarkerIgnoringBase
  implements Serializable, LocationAwareLogger
{
  final transient Logger logger;
  static final String FQCN = Log4jLoggerAdapter.class.getName();
  final boolean traceCapable;

  Log4jLoggerAdapter(Logger paramLogger)
  {
    this.logger = paramLogger;
    this.name = paramLogger.getName();
    this.traceCapable = isTraceCapable();
  }

  private boolean isTraceCapable()
  {
    try
    {
      this.logger.isTraceEnabled();
      return true;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
    }
    return false;
  }

  public void debug(String paramString)
  {
    this.logger.log(FQCN, Level.DEBUG, paramString, null);
  }

  public void info(String paramString)
  {
    this.logger.log(FQCN, Level.INFO, paramString, null);
  }

  public void warn(String paramString)
  {
    this.logger.log(FQCN, Level.WARN, paramString, null);
  }

  public void warn(String paramString, Throwable paramThrowable)
  {
    this.logger.log(FQCN, Level.WARN, paramString, paramThrowable);
  }

  public void error(String paramString)
  {
    this.logger.log(FQCN, Level.ERROR, paramString, null);
  }

  public void error(String paramString, Throwable paramThrowable)
  {
    this.logger.log(FQCN, Level.ERROR, paramString, paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.impl.Log4jLoggerAdapter
 * JD-Core Version:    0.6.2
 */