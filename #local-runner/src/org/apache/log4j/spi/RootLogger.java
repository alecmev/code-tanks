package org.apache.log4j.spi;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

public final class RootLogger extends Logger
{
  public RootLogger(Level paramLevel)
  {
    super("root");
    setLevel(paramLevel);
  }

  public final void setLevel(Level paramLevel)
  {
    if (paramLevel == null)
      LogLog.error("You have tried to set a null level to root.", new Throwable());
    else
      this.level = paramLevel;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.RootLogger
 * JD-Core Version:    0.6.2
 */