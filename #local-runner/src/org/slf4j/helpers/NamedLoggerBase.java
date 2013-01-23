package org.slf4j.helpers;

import java.io.Serializable;
import org.slf4j.Logger;

abstract class NamedLoggerBase
  implements Serializable, Logger
{
  protected String name;

  public String getName()
  {
    return this.name;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.helpers.NamedLoggerBase
 * JD-Core Version:    0.6.2
 */