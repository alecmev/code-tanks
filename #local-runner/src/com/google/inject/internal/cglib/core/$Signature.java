package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..Type;

public class $Signature
{
  private String name;
  private String desc;

  public $Signature(String paramString1, String paramString2)
  {
    if (paramString1.indexOf('(') >= 0)
      throw new IllegalArgumentException("Name '" + paramString1 + "' is invalid");
    this.name = paramString1;
    this.desc = paramString2;
  }

  public $Signature(String paramString, .Type paramType, .Type[] paramArrayOfType)
  {
    this(paramString, .Type.getMethodDescriptor(paramType, paramArrayOfType));
  }

  public String getName()
  {
    return this.name;
  }

  public String getDescriptor()
  {
    return this.desc;
  }

  public .Type getReturnType()
  {
    return .Type.getReturnType(this.desc);
  }

  public .Type[] getArgumentTypes()
  {
    return .Type.getArgumentTypes(this.desc);
  }

  public String toString()
  {
    return this.name + this.desc;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (!(paramObject instanceof Signature))
      return false;
    Signature localSignature = (Signature)paramObject;
    return (this.name.equals(localSignature.name)) && (this.desc.equals(localSignature.desc));
  }

  public int hashCode()
  {
    return this.name.hashCode() ^ this.desc.hashCode();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..Signature
 * JD-Core Version:    0.6.2
 */