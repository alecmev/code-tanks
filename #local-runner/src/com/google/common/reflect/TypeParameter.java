package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

@Beta
public abstract class TypeParameter extends TypeCapture
{
  final TypeVariable typeVariable;

  private TypeParameter(TypeVariable paramTypeVariable)
  {
    this.typeVariable = ((TypeVariable)Preconditions.checkNotNull(paramTypeVariable));
  }

  protected TypeParameter()
  {
    Type localType = capture();
    Preconditions.checkArgument(localType instanceof TypeVariable, "%s should be a type variable.", new Object[] { localType });
    this.typeVariable = ((TypeVariable)localType);
  }

  public final int hashCode()
  {
    return this.typeVariable.hashCode();
  }

  public final boolean equals(Object paramObject)
  {
    if ((paramObject instanceof TypeParameter))
    {
      TypeParameter localTypeParameter = (TypeParameter)paramObject;
      return this.typeVariable.equals(localTypeParameter.typeVariable);
    }
    return false;
  }

  public String toString()
  {
    return this.typeVariable.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.reflect.TypeParameter
 * JD-Core Version:    0.6.2
 */