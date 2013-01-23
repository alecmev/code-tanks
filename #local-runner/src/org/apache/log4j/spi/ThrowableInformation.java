package org.apache.log4j.spi;

import java.io.Serializable;
import org.apache.log4j.Category;

public class ThrowableInformation
  implements Serializable
{
  private transient Throwable throwable;
  private transient Category category;

  public ThrowableInformation(Throwable paramThrowable, Category paramCategory)
  {
    this.throwable = paramThrowable;
    this.category = paramCategory;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.spi.ThrowableInformation
 * JD-Core Version:    0.6.2
 */