package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

public abstract interface ErrorHandler extends OptionHandler
{
  public abstract void setLogger(Logger paramLogger);

  public abstract void setBackupAppender(Appender paramAppender);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.ErrorHandler
 * JD-Core Version:    0.6.2
 */