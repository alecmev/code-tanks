package com.a.a.a.a.b.b;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class c
{
  private static final Logger a = LoggerFactory.getLogger(c.class);
  private static final Pattern b = Pattern.compile("[1-9][0-9]{0,4}");
  private static final Pattern c = Pattern.compile("[1-5]");

  private c()
  {
    throw new UnsupportedOperationException();
  }

  public static int a(Map paramMap)
  {
    a.debug("Parsing tick count.");
    String str = (String)paramMap.get("tick-count");
    if (StringUtils.isBlank(str))
      str = "5000";
    if (!b.matcher(str).matches())
      throw new IllegalArgumentException("Illegal tick count value: '" + str + "'.");
    return Integer.parseInt(str);
  }

  public static int a(Map paramMap, int paramInt)
  {
    a.debug("Parsing team size for player #" + (paramInt + 1) + '.');
    String str = (String)paramMap.get("p" + (paramInt + 1) + "-team-size");
    if (StringUtils.isBlank(str))
      return 1;
    if (!c.matcher(str).matches())
      throw new IllegalArgumentException("Illegal team size value: '" + str + "'.");
    return Integer.parseInt(str);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.c
 * JD-Core Version:    0.6.2
 */