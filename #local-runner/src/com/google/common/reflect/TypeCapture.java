package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class TypeCapture
{
  final Type capture()
  {
    Type localType = getClass().getGenericSuperclass();
    Preconditions.checkArgument(localType instanceof ParameterizedType, "%s isn't parameterized", new Object[] { localType });
    return ((ParameterizedType)localType).getActualTypeArguments()[0];
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.reflect.TypeCapture
 * JD-Core Version:    0.6.2
 */