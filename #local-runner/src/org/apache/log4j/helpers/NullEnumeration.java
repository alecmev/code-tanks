package org.apache.log4j.helpers;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class NullEnumeration
  implements Enumeration
{
  private static final NullEnumeration instance = new NullEnumeration();

  public static NullEnumeration getInstance()
  {
    return instance;
  }

  public boolean hasMoreElements()
  {
    return false;
  }

  public Object nextElement()
  {
    throw new NoSuchElementException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.helpers.NullEnumeration
 * JD-Core Version:    0.6.2
 */