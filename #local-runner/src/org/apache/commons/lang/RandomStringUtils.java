package org.apache.commons.lang;

import java.util.Random;

public class RandomStringUtils
{
  private static final Random RANDOM = new Random();

  public static String randomAlphanumeric(int paramInt)
  {
    return random(paramInt, true, true);
  }

  public static String random(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    return random(paramInt, 0, 0, paramBoolean1, paramBoolean2);
  }

  public static String random(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    return random(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, null, RANDOM);
  }

  public static String random(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, char[] paramArrayOfChar, Random paramRandom)
  {
    if (paramInt1 == 0)
      return "";
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Requested random string length " + paramInt1 + " is less than 0.");
    if ((paramInt2 == 0) && (paramInt3 == 0))
    {
      paramInt3 = 123;
      paramInt2 = 32;
      if ((!paramBoolean1) && (!paramBoolean2))
      {
        paramInt2 = 0;
        paramInt3 = 2147483647;
      }
    }
    char[] arrayOfChar = new char[paramInt1];
    int i = paramInt3 - paramInt2;
    while (paramInt1-- != 0)
    {
      int j;
      if (paramArrayOfChar == null)
        j = (char)(paramRandom.nextInt(i) + paramInt2);
      else
        j = paramArrayOfChar[(paramRandom.nextInt(i) + paramInt2)];
      if (((paramBoolean1) && (Character.isLetter(j))) || ((paramBoolean2) && (Character.isDigit(j))) || ((!paramBoolean1) && (!paramBoolean2)))
      {
        if ((j >= 56320) && (j <= 57343))
        {
          if (paramInt1 == 0)
          {
            paramInt1++;
          }
          else
          {
            arrayOfChar[paramInt1] = j;
            paramInt1--;
            arrayOfChar[paramInt1] = ((char)(55296 + paramRandom.nextInt(128)));
          }
        }
        else if ((j >= 55296) && (j <= 56191))
        {
          if (paramInt1 == 0)
          {
            paramInt1++;
          }
          else
          {
            arrayOfChar[paramInt1] = ((char)(56320 + paramRandom.nextInt(128)));
            paramInt1--;
            arrayOfChar[paramInt1] = j;
          }
        }
        else if ((j >= 56192) && (j <= 56319))
          paramInt1++;
        else
          arrayOfChar[paramInt1] = j;
      }
      else
        paramInt1++;
    }
    return new String(arrayOfChar);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.lang.RandomStringUtils
 * JD-Core Version:    0.6.2
 */