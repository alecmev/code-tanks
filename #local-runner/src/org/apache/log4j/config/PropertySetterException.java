package org.apache.log4j.config;

public class PropertySetterException extends Exception
{
  protected Throwable rootCause;

  public PropertySetterException(String paramString)
  {
    super(paramString);
  }

  public PropertySetterException(Throwable paramThrowable)
  {
    this.rootCause = paramThrowable;
  }

  public String getMessage()
  {
    String str = super.getMessage();
    if ((str == null) && (this.rootCause != null))
      str = this.rootCause.getMessage();
    return str;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.config.PropertySetterException
 * JD-Core Version:    0.6.2
 */