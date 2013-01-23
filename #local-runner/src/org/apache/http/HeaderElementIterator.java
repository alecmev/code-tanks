package org.apache.http;

import java.util.Iterator;

public abstract interface HeaderElementIterator extends Iterator
{
  public abstract boolean hasNext();

  public abstract HeaderElement nextElement();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HeaderElementIterator
 * JD-Core Version:    0.6.2
 */