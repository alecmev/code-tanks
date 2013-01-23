package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..Type;

public abstract class $ClassInfo
{
  public abstract $Type getType();

  public abstract $Type getSuperType();

  public abstract $Type[] getInterfaces();

  public abstract int getModifiers();

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (!(paramObject instanceof ClassInfo))
      return false;
    return getType().equals(((ClassInfo)paramObject).getType());
  }

  public int hashCode()
  {
    return getType().hashCode();
  }

  public String toString()
  {
    return getType().getClassName();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..ClassInfo
 * JD-Core Version:    0.6.2
 */