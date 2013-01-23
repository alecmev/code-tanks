package org.apache.http.util;

public final class LangUtils
{
  public static int hashCode(int paramInt1, int paramInt2)
  {
    return paramInt1 * 37 + paramInt2;
  }

  public static int hashCode(int paramInt, boolean paramBoolean)
  {
    return hashCode(paramInt, paramBoolean ? 1 : 0);
  }

  public static int hashCode(int paramInt, Object paramObject)
  {
    return hashCode(paramInt, paramObject != null ? paramObject.hashCode() : 0);
  }

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    return paramObject1 == null ? false : paramObject2 == null ? true : paramObject1.equals(paramObject2);
  }

  public static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    if (paramArrayOfObject1 == null)
      return paramArrayOfObject2 == null;
    if ((paramArrayOfObject2 != null) && (paramArrayOfObject1.length == paramArrayOfObject2.length))
    {
      for (int i = 0; i < paramArrayOfObject1.length; i++)
        if (!equals(paramArrayOfObject1[i], paramArrayOfObject2[i]))
          return false;
      return true;
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.util.LangUtils
 * JD-Core Version:    0.6.2
 */