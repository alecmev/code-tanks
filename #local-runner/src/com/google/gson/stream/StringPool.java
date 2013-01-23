package com.google.gson.stream;

final class StringPool
{
  private final String[] pool = new String[512];

  public String get(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = 0;
    for (int j = paramInt1; j < paramInt1 + paramInt2; j++)
      i = i * 31 + paramArrayOfChar[j];
    i ^= i >>> 20 ^ i >>> 12;
    i ^= i >>> 7 ^ i >>> 4;
    j = i & this.pool.length - 1;
    String str1 = this.pool[j];
    if ((str1 == null) || (str1.length() != paramInt2))
    {
      String str2 = new String(paramArrayOfChar, paramInt1, paramInt2);
      this.pool[j] = str2;
      return str2;
    }
    for (int k = 0; k < paramInt2; k++)
      if (str1.charAt(k) != paramArrayOfChar[(paramInt1 + k)])
      {
        String str3 = new String(paramArrayOfChar, paramInt1, paramInt2);
        this.pool[j] = str3;
        return str3;
      }
    return str1;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.stream.StringPool
 * JD-Core Version:    0.6.2
 */