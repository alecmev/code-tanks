package org.apache.log4j;

import org.apache.log4j.spi.LoggerRepository;

public class Logger extends Category
{
  private static final String FQCN = Logger.class.getName();

  protected Logger(String paramString)
  {
    super(paramString);
  }

  public static Logger getLogger(Class paramClass)
  {
    return LogManager.getLogger(paramClass.getName());
  }

  public static Logger getRootLogger()
  {
    return LogManager.getRootLogger();
  }

  public boolean isTraceEnabled()
  {
    if (this.repository.isDisabled(5000))
      return false;
    return Level.TRACE.isGreaterOrEqual(getEffectiveLevel());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.Logger
 * JD-Core Version:    0.6.2
 */