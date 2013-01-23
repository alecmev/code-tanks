package com.google.gson.internal;

public final class $Gson$Preconditions
{
  public static Object checkNotNull(Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException();
    return paramObject;
  }

  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal..Gson.Preconditions
 * JD-Core Version:    0.6.2
 */