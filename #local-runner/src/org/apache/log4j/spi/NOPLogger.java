package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public final class NOPLogger extends Logger
{
  public NOPLogger(NOPLoggerRepository paramNOPLoggerRepository, String paramString)
  {
    super(paramString);
    this.repository = paramNOPLoggerRepository;
    this.level = Level.OFF;
    this.parent = this;
  }

  public void addAppender(Appender paramAppender)
  {
  }

  public void callAppenders(LoggingEvent paramLoggingEvent)
  {
  }

  void closeNestedAppenders()
  {
  }

  public Enumeration getAllAppenders()
  {
    return new Vector().elements();
  }

  public Level getEffectiveLevel()
  {
    return Level.OFF;
  }

  public void log(String paramString, Priority paramPriority, Object paramObject, Throwable paramThrowable)
  {
  }

  public void removeAllAppenders()
  {
  }

  public void setLevel(Level paramLevel)
  {
  }

  public void setResourceBundle(ResourceBundle paramResourceBundle)
  {
  }

  public boolean isTraceEnabled()
  {
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.NOPLogger
 * JD-Core Version:    0.6.2
 */