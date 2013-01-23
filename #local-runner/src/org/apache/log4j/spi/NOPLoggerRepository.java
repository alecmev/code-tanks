package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public final class NOPLoggerRepository
  implements LoggerRepository
{
  public boolean isDisabled(int paramInt)
  {
    return true;
  }

  public void setThreshold(Level paramLevel)
  {
  }

  public void emitNoAppenderWarning(Category paramCategory)
  {
  }

  public Level getThreshold()
  {
    return Level.OFF;
  }

  public Logger getLogger(String paramString)
  {
    return new NOPLogger(this, paramString);
  }

  public Logger getLogger(String paramString, LoggerFactory paramLoggerFactory)
  {
    return new NOPLogger(this, paramString);
  }

  public Logger getRootLogger()
  {
    return new NOPLogger(this, "root");
  }

  public void fireAddAppenderEvent(Category paramCategory, Appender paramAppender)
  {
  }

  public void resetConfiguration()
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.NOPLoggerRepository
 * JD-Core Version:    0.6.2
 */