package org.apache.http;

import java.util.Iterator;

public abstract interface TokenIterator extends Iterator
{
  public abstract boolean hasNext();

  public abstract String nextToken();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.TokenIterator
 * JD-Core Version:    0.6.2
 */