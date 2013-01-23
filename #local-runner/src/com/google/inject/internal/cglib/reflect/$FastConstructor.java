package com.google.inject.internal.cglib.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class $FastConstructor extends $FastMember
{
  $FastConstructor($FastClass paramFastClass, Constructor paramConstructor)
  {
    super(paramFastClass, paramConstructor, paramFastClass.getIndex(paramConstructor.getParameterTypes()));
  }

  public Class[] getParameterTypes()
  {
    return ((Constructor)this.member).getParameterTypes();
  }

  public Class[] getExceptionTypes()
  {
    return ((Constructor)this.member).getExceptionTypes();
  }

  public Object newInstance()
    throws InvocationTargetException
  {
    return this.fc.newInstance(this.index, null);
  }

  public Object newInstance(Object[] paramArrayOfObject)
    throws InvocationTargetException
  {
    return this.fc.newInstance(this.index, paramArrayOfObject);
  }

  public Constructor getJavaConstructor()
  {
    return (Constructor)this.member;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.reflect..FastConstructor
 * JD-Core Version:    0.6.2
 */