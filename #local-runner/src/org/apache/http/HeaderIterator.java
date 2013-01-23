package org.apache.http;

import java.util.Iterator;

public abstract interface HeaderIterator extends Iterator
{
  public abstract boolean hasNext();

  public abstract Header nextHeader();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.HeaderIterator
 * JD-Core Version:    0.6.2
 */