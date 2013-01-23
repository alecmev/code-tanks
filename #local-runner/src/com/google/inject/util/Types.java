package com.google.inject.util;

import com.google.inject.Provider;
import com.google.inject.internal.MoreTypes;
import com.google.inject.internal.MoreTypes.GenericArrayTypeImpl;
import com.google.inject.internal.MoreTypes.ParameterizedTypeImpl;
import com.google.inject.internal.MoreTypes.WildcardTypeImpl;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Types
{
  public static ParameterizedType newParameterizedType(Type paramType, Type[] paramArrayOfType)
  {
    return newParameterizedTypeWithOwner(null, paramType, paramArrayOfType);
  }

  public static ParameterizedType newParameterizedTypeWithOwner(Type paramType1, Type paramType2, Type[] paramArrayOfType)
  {
    return new MoreTypes.ParameterizedTypeImpl(paramType1, paramType2, paramArrayOfType);
  }

  public static GenericArrayType arrayOf(Type paramType)
  {
    return new MoreTypes.GenericArrayTypeImpl(paramType);
  }

  public static WildcardType subtypeOf(Type paramType)
  {
    return new MoreTypes.WildcardTypeImpl(new Type[] { paramType }, MoreTypes.EMPTY_TYPE_ARRAY);
  }

  public static WildcardType supertypeOf(Type paramType)
  {
    return new MoreTypes.WildcardTypeImpl(new Type[] { Object.class }, new Type[] { paramType });
  }

  public static ParameterizedType listOf(Type paramType)
  {
    return newParameterizedType(List.class, new Type[] { paramType });
  }

  public static ParameterizedType setOf(Type paramType)
  {
    return newParameterizedType(Set.class, new Type[] { paramType });
  }

  public static ParameterizedType mapOf(Type paramType1, Type paramType2)
  {
    return newParameterizedType(Map.class, new Type[] { paramType1, paramType2 });
  }

  public static ParameterizedType providerOf(Type paramType)
  {
    return newParameterizedType(Provider.class, new Type[] { paramType });
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.util.Types
 * JD-Core Version:    0.6.2
 */