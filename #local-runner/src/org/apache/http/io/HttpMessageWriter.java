package org.apache.http.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

public abstract interface HttpMessageWriter
{
  public abstract void write(HttpMessage paramHttpMessage)
    throws IOException, HttpException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.io.HttpMessageWriter
 * JD-Core Version:    0.6.2
 */