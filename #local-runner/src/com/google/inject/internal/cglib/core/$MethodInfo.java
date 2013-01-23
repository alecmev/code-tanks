package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..Type;

public abstract class $MethodInfo
{
  public abstract $ClassInfo getClassInfo();

  public abstract int getModifiers();

  public abstract $Signature getSignature();

  public abstract $Type[] getExceptionTypes();

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (!(paramObject instanceof MethodInfo))
      return false;
    return getSignature().equals(((MethodInfo)paramObject).getSignature());
  }

  public int hashCode()
  {
    return getSignature().hashCode();
  }

  public String toString()
  {
    return getSignature().toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..MethodInfo
 * JD-Core Version:    0.6.2
 */