package com.google.inject.internal.util;

import java.util.Arrays;

public final class $Objects
{
  public static boolean equal(@$Nullable Object paramObject1, @$Nullable Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static int hashCode(Object[] paramArrayOfObject)
  {
    return Arrays.hashCode(paramArrayOfObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Objects
 * JD-Core Version:    0.6.2
 */