package org.slf4j.helpers;

import org.slf4j.Logger;

public abstract class MarkerIgnoringBase extends NamedLoggerBase
  implements Logger
{
  public String toString()
  {
    return getClass().getName() + "(" + getName() + ")";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.helpers.MarkerIgnoringBase
 * JD-Core Version:    0.6.2
 */