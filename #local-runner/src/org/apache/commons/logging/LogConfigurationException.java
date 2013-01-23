package org.apache.commons.logging;

public class LogConfigurationException extends RuntimeException
{
  protected Throwable cause = null;

  public LogConfigurationException()
  {
  }

  public LogConfigurationException(String paramString)
  {
    super(paramString);
  }

  public LogConfigurationException(Throwable paramThrowable)
  {
    this(paramThrowable == null ? null : paramThrowable.toString(), paramThrowable);
  }

  public LogConfigurationException(String paramString, Throwable paramThrowable)
  {
    super(paramString + " (Caused by " + paramThrowable + ")");
    this.cause = paramThrowable;
  }

  public Throwable getCause()
  {
    return this.cause;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.logging.LogConfigurationException
 * JD-Core Version:    0.6.2
 */