package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public abstract interface LoggerRepository
{
  public abstract boolean isDisabled(int paramInt);

  public abstract void setThreshold(Level paramLevel);

  public abstract void emitNoAppenderWarning(Category paramCategory);

  public abstract Level getThreshold();

  public abstract Logger getLogger(String paramString);

  public abstract Logger getLogger(String paramString, LoggerFactory paramLoggerFactory);

  public abstract Logger getRootLogger();

  public abstract void fireAddAppenderEvent(Category paramCategory, Appender paramAppender);

  public abstract void resetConfiguration();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.LoggerRepository
 * JD-Core Version:    0.6.2
 */