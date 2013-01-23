package com.google.inject.internal.cglib.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class $MethodInfoTransformer
  implements .Transformer
{
  private static final MethodInfoTransformer INSTANCE = new MethodInfoTransformer();

  public static MethodInfoTransformer getInstance()
  {
    return INSTANCE;
  }

  public Object transform(Object paramObject)
  {
    if ((paramObject instanceof Method))
      return .ReflectUtils.getMethodInfo((Method)paramObject);
    if ((paramObject instanceof Constructor))
      return .ReflectUtils.getMethodInfo((Constructor)paramObject);
    throw new IllegalArgumentException("cannot get method info for " + paramObject);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..MethodInfoTransformer
 * JD-Core Version:    0.6.2
 */