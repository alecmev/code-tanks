package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.Array;

@GwtCompatible(emulated=true)
class Platform
{
  static Object[] clone(Object[] paramArrayOfObject)
  {
    return (Object[])paramArrayOfObject.clone();
  }

  @GwtIncompatible("Array.newInstance(Class, int)")
  static Object[] newArray(Class paramClass, int paramInt)
  {
    return (Object[])Array.newInstance(paramClass, paramInt);
  }

  static Object[] newArray(Object[] paramArrayOfObject, int paramInt)
  {
    Class localClass = paramArrayOfObject.getClass().getComponentType();
    Object[] arrayOfObject = (Object[])Array.newInstance(localClass, paramInt);
    return arrayOfObject;
  }

  static MapMaker tryWeakKeys(MapMaker paramMapMaker)
  {
    return paramMapMaker.weakKeys();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Platform
 * JD-Core Version:    0.6.2
 */