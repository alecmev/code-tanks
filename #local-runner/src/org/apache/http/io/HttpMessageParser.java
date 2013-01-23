package org.apache.http.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

public abstract interface HttpMessageParser
{
  public abstract HttpMessage parse()
    throws IOException, HttpException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.io.HttpMessageParser
 * JD-Core Version:    0.6.2
 */