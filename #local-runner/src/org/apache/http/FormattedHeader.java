package org.apache.http;

import org.apache.http.util.CharArrayBuffer;

public abstract interface FormattedHeader extends Header
{
  public abstract CharArrayBuffer getBuffer();

  public abstract int getValuePos();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.FormattedHeader
 * JD-Core Version:    0.6.2
 */