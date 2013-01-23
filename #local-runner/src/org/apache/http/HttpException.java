package org.apache.http;

public class HttpException extends Exception
{
  public HttpException()
  {
  }

  public HttpException(String paramString)
  {
    super(paramString);
  }

  public HttpException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HttpException
 * JD-Core Version:    0.6.2
 */