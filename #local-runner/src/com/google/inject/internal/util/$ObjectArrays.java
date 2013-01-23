package com.google.inject.internal.util;

import java.lang.reflect.Array;

public final class $ObjectArrays
{
  public static Object[] newArray(Object[] paramArrayOfObject, int paramInt)
  {
    Class localClass = paramArrayOfObject.getClass().getComponentType();
    Object[] arrayOfObject = (Object[])Array.newInstance(localClass, paramInt);
    return arrayOfObject;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ObjectArrays
 * JD-Core Version:    0.6.2
 */