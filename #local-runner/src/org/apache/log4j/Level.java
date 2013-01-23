package org.apache.log4j;

import java.io.Serializable;

public class Level extends Priority
  implements Serializable
{
  public static final Level OFF = new Level(2147483647, "OFF", 0);
  public static final Level FATAL = new Level(50000, "FATAL", 0);
  public static final Level ERROR = new Level(40000, "ERROR", 3);
  public static final Level WARN = new Level(30000, "WARN", 4);
  public static final Level INFO = new Level(20000, "INFO", 6);
  public static final Level DEBUG = new Level(10000, "DEBUG", 7);
  public static final Level TRACE = new Level(5000, "TRACE", 7);
  public static final Level ALL = new Level(-2147483648, "ALL", 7);

  protected Level(int paramInt1, String paramString, int paramInt2)
  {
    super(paramInt1, paramString, paramInt2);
  }

  public static Level toLevel(String paramString, Level paramLevel)
  {
    if (paramString == null)
      return paramLevel;
    String str = paramString.toUpperCase();
    if (str.equals("ALL"))
      return ALL;
    if (str.equals("DEBUG"))
      return DEBUG;
    if (str.equals("INFO"))
      return INFO;
    if (str.equals("WARN"))
      return WARN;
    if (str.equals("ERROR"))
      return ERROR;
    if (str.equals("FATAL"))
      return FATAL;
    if (str.equals("OFF"))
      return OFF;
    if (str.equals("TRACE"))
      return TRACE;
    if (str.equals("İNFO"))
      return INFO;
    return paramLevel;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.Level
 * JD-Core Version:    0.6.2
 */