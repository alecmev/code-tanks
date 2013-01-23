package org.slf4j.helpers;

public class NOPLogger extends MarkerIgnoringBase
{
  public static final NOPLogger NOP_LOGGER = new NOPLogger();

  public String getName()
  {
    return "NOP";
  }

  public final void debug(String paramString)
  {
  }

  public final void info(String paramString)
  {
  }

  public final void warn(String paramString)
  {
  }

  public final void warn(String paramString, Throwable paramThrowable)
  {
  }

  public final void error(String paramString)
  {
  }

  public final void error(String paramString, Throwable paramThrowable)
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.helpers.NOPLogger
 * JD-Core Version:    0.6.2
 */