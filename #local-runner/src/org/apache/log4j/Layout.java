package org.apache.log4j;

import org.apache.log4j.spi.OptionHandler;

public abstract class Layout
  implements OptionHandler
{
  public static final String LINE_SEP = System.getProperty("line.separator");
  public static final int LINE_SEP_LEN = LINE_SEP.length();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.Layout
 * JD-Core Version:    0.6.2
 */