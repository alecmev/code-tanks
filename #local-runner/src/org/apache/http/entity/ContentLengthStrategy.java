package org.apache.http.entity;

import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

public abstract interface ContentLengthStrategy
{
  public abstract long determineLength(HttpMessage paramHttpMessage)
    throws HttpException;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.entity.ContentLengthStrategy
 * JD-Core Version:    0.6.2
 */