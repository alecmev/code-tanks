package org.apache.commons.lang;

import org.apache.commons.lang.text.StrBuilder;

public class StringUtils
{
  public static boolean isBlank(String paramString)
  {
    int i;
    if ((paramString == null) || ((i = paramString.length()) == 0))
      return true;
    for (int j = 0; j < i; j++)
      if (!Character.isWhitespace(paramString.charAt(j)))
        return false;
    return true;
  }

  public static String repeat(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    if (paramInt <= 0)
      return "";
    int i = paramString.length();
    if ((paramInt == 1) || (i == 0))
      return paramString;
    if ((i == 1) && (paramInt <= 8192))
      return padding(paramInt, paramString.charAt(0));
    int j = i * paramInt;
    int m;
    switch (i)
    {
    case 1:
      int k = paramString.charAt(0);
      char[] arrayOfChar1 = new char[j];
      for (m = paramInt - 1; m >= 0; m--)
        arrayOfChar1[m] = k;
      return new String(arrayOfChar1);
    case 2:
      m = paramString.charAt(0);
      int n = paramString.charAt(1);
      char[] arrayOfChar2 = new char[j];
      for (int i1 = paramInt * 2 - 2; i1 >= 0; i1--)
      {
        arrayOfChar2[i1] = m;
        arrayOfChar2[(i1 + 1)] = n;
        i1--;
      }
      return new String(arrayOfChar2);
    }
    StrBuilder localStrBuilder = new StrBuilder(j);
    for (int i2 = 0; i2 < paramInt; i2++)
      localStrBuilder.append(paramString);
    return localStrBuilder.toString();
  }

  private static String padding(int paramInt, char paramChar)
    throws IndexOutOfBoundsException
  {
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + paramInt);
    char[] arrayOfChar = new char[paramInt];
    for (int i = 0; i < arrayOfChar.length; i++)
      arrayOfChar[i] = paramChar;
    return new String(arrayOfChar);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.lang.StringUtils
 * JD-Core Version:    0.6.2
 */