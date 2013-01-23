package com.google.inject.internal.util;

public class $Strings
{
  public static String capitalize(String paramString)
  {
    if (paramString.length() == 0)
      return paramString;
    char c1 = paramString.charAt(0);
    char c2 = Character.toUpperCase(c1);
    return c2 + paramString.substring(1);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Strings
 * JD-Core Version:    0.6.2
 */