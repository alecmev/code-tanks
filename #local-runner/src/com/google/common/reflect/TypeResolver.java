package com.google.common.reflect;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class TypeResolver
{
  private final ImmutableMap typeTable;

  static TypeResolver accordingTo(Type paramType)
  {
    return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(paramType));
  }

  TypeResolver()
  {
    this.typeTable = ImmutableMap.of();
  }

  private TypeResolver(ImmutableMap paramImmutableMap)
  {
    this.typeTable = paramImmutableMap;
  }

  final TypeResolver where(Map paramMap)
  {
    ImmutableMap.Builder localBuilder = ImmutableMap.builder();
    localBuilder.putAll(this.typeTable);
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TypeVariable localTypeVariable = (TypeVariable)localEntry.getKey();
      Type localType = (Type)localEntry.getValue();
      Preconditions.checkArgument(!localTypeVariable.equals(localType), "Type variable %s bound to itself", new Object[] { localTypeVariable });
      localBuilder.put(localTypeVariable, localType);
    }
    return new TypeResolver(localBuilder.build());
  }

  final TypeResolver where(Type paramType1, Type paramType2)
  {
    HashMap localHashMap = Maps.newHashMap();
    populateTypeMappings(localHashMap, paramType1, paramType2);
    return where(localHashMap);
  }

  private static void populateTypeMappings(Map paramMap, Type paramType1, Type paramType2)
  {
    if ((paramType1 instanceof TypeVariable))
    {
      paramMap.put((TypeVariable)paramType1, paramType2);
    }
    else if ((paramType1 instanceof GenericArrayType))
    {
      populateTypeMappings(paramMap, ((GenericArrayType)paramType1).getGenericComponentType(), Types.getComponentType(paramType2));
    }
    else if ((paramType1 instanceof ParameterizedType))
    {
      Type[] arrayOfType1 = ((ParameterizedType)paramType1).getActualTypeArguments();
      Type[] arrayOfType2 = ((ParameterizedType)paramType2).getActualTypeArguments();
      Preconditions.checkArgument(arrayOfType1.length == arrayOfType2.length);
      for (int i = 0; i < arrayOfType1.length; i++)
        populateTypeMappings(paramMap, arrayOfType1[i], arrayOfType2[i]);
    }
  }

  final Type resolve(Type paramType)
  {
    if ((paramType instanceof TypeVariable))
      return resolveTypeVariable((TypeVariable)paramType);
    if ((paramType instanceof ParameterizedType))
      return resolveParameterizedType((ParameterizedType)paramType);
    if ((paramType instanceof GenericArrayType))
      return resolveGenericArrayType((GenericArrayType)paramType);
    if ((paramType instanceof WildcardType))
    {
      WildcardType localWildcardType = (WildcardType)paramType;
      return new Types.WildcardTypeImpl(resolve(localWildcardType.getLowerBounds()), resolve(localWildcardType.getUpperBounds()));
    }
    return paramType;
  }

  private Type[] resolve(Type[] paramArrayOfType)
  {
    Type[] arrayOfType = new Type[paramArrayOfType.length];
    for (int i = 0; i < paramArrayOfType.length; i++)
      arrayOfType[i] = resolve(paramArrayOfType[i]);
    return arrayOfType;
  }

  private Type resolveGenericArrayType(GenericArrayType paramGenericArrayType)
  {
    Type localType = resolve(paramGenericArrayType.getGenericComponentType());
    return Types.newArrayType(localType);
  }

  private Type resolveTypeVariable(final TypeVariable paramTypeVariable)
  {
    final TypeResolver localTypeResolver = this;
    TypeResolver local1 = new TypeResolver(this.typeTable, paramTypeVariable)
    {
      Type resolveTypeVariable(TypeVariable paramAnonymousTypeVariable, TypeResolver paramAnonymousTypeResolver)
      {
        if (paramAnonymousTypeVariable.getGenericDeclaration().equals(paramTypeVariable.getGenericDeclaration()))
          return paramAnonymousTypeVariable;
        return localTypeResolver.resolveTypeVariable(paramAnonymousTypeVariable, paramAnonymousTypeResolver);
      }
    };
    return resolveTypeVariable(paramTypeVariable, local1);
  }

  Type resolveTypeVariable(TypeVariable paramTypeVariable, TypeResolver paramTypeResolver)
  {
    Type localType = (Type)this.typeTable.get(paramTypeVariable);
    if (localType == null)
    {
      Type[] arrayOfType = paramTypeVariable.getBounds();
      if (arrayOfType.length == 0)
        return paramTypeVariable;
      return Types.newTypeVariable(paramTypeVariable.getGenericDeclaration(), paramTypeVariable.getName(), paramTypeResolver.resolve(arrayOfType));
    }
    return paramTypeResolver.resolve(localType);
  }

  private ParameterizedType resolveParameterizedType(ParameterizedType paramParameterizedType)
  {
    Type localType1 = paramParameterizedType.getOwnerType();
    Type localType2 = localType1 == null ? null : resolve(localType1);
    Type localType3 = resolve(paramParameterizedType.getRawType());
    Type[] arrayOfType1 = paramParameterizedType.getActualTypeArguments();
    Type[] arrayOfType2 = new Type[arrayOfType1.length];
    for (int i = 0; i < arrayOfType1.length; i++)
      arrayOfType2[i] = resolve(arrayOfType1[i]);
    return Types.newParameterizedTypeWithOwner(localType2, (Class)localType3, arrayOfType2);
  }

  private static final class WildcardCapturer
  {
    private final AtomicInteger id = new AtomicInteger();

    Type capture(Type paramType)
    {
      Preconditions.checkNotNull(paramType);
      if ((paramType instanceof Class))
        return paramType;
      if ((paramType instanceof TypeVariable))
        return paramType;
      Object localObject;
      if ((paramType instanceof GenericArrayType))
      {
        localObject = (GenericArrayType)paramType;
        return Types.newArrayType(capture(((GenericArrayType)localObject).getGenericComponentType()));
      }
      if ((paramType instanceof ParameterizedType))
      {
        localObject = (ParameterizedType)paramType;
        return Types.newParameterizedTypeWithOwner(captureNullable(((ParameterizedType)localObject).getOwnerType()), (Class)((ParameterizedType)localObject).getRawType(), capture(((ParameterizedType)localObject).getActualTypeArguments()));
      }
      if ((paramType instanceof WildcardType))
      {
        localObject = (WildcardType)paramType;
        Type[] arrayOfType1 = ((WildcardType)localObject).getLowerBounds();
        if (arrayOfType1.length == 0)
        {
          Type[] arrayOfType2 = ((WildcardType)localObject).getUpperBounds();
          String str = "capture#" + this.id.incrementAndGet() + "-of ? extends " + Joiner.on('&').join(arrayOfType2);
          return Types.newTypeVariable(WildcardCapturer.class, str, ((WildcardType)localObject).getUpperBounds());
        }
        return paramType;
      }
      throw new AssertionError("must have been one of the known types");
    }

    private Type captureNullable(Type paramType)
    {
      if (paramType == null)
        return null;
      return capture(paramType);
    }

    private Type[] capture(Type[] paramArrayOfType)
    {
      Type[] arrayOfType = new Type[paramArrayOfType.length];
      for (int i = 0; i < paramArrayOfType.length; i++)
        arrayOfType[i] = capture(paramArrayOfType[i]);
      return arrayOfType;
    }
  }

  private static final class TypeMappingIntrospector
  {
    private static final TypeResolver.WildcardCapturer wildcardCapturer = new TypeResolver.WildcardCapturer(null);
    private final Map mappings = Maps.newHashMap();
    private final Set introspectedTypes = Sets.newHashSet();

    static ImmutableMap getTypeMappings(Type paramType)
    {
      TypeMappingIntrospector localTypeMappingIntrospector = new TypeMappingIntrospector();
      localTypeMappingIntrospector.introspect(wildcardCapturer.capture(paramType));
      return ImmutableMap.copyOf(localTypeMappingIntrospector.mappings);
    }

    private void introspect(Type paramType)
    {
      if (!this.introspectedTypes.add(paramType))
        return;
      if ((paramType instanceof ParameterizedType))
      {
        introspectParameterizedType((ParameterizedType)paramType);
      }
      else if ((paramType instanceof Class))
      {
        introspectClass((Class)paramType);
      }
      else
      {
        Type localType;
        if ((paramType instanceof TypeVariable))
          for (localType : ((TypeVariable)paramType).getBounds())
            introspect(localType);
        else if ((paramType instanceof WildcardType))
          for (localType : ((WildcardType)paramType).getUpperBounds())
            introspect(localType);
      }
    }

    private void introspectClass(Class paramClass)
    {
      introspect(paramClass.getGenericSuperclass());
      for (Type localType : paramClass.getGenericInterfaces())
        introspect(localType);
    }

    private void introspectParameterizedType(ParameterizedType paramParameterizedType)
    {
      Class localClass = (Class)paramParameterizedType.getRawType();
      TypeVariable[] arrayOfTypeVariable = localClass.getTypeParameters();
      Type[] arrayOfType = paramParameterizedType.getActualTypeArguments();
      Preconditions.checkState(arrayOfTypeVariable.length == arrayOfType.length);
      for (int i = 0; i < arrayOfTypeVariable.length; i++)
        map(arrayOfTypeVariable[i], arrayOfType[i]);
      introspectClass(localClass);
      introspect(paramParameterizedType.getOwnerType());
    }

    private void map(TypeVariable paramTypeVariable, Type paramType)
    {
      if (this.mappings.containsKey(paramTypeVariable))
        return;
      for (Type localType1 = paramType; localType1 != null; localType1 = (Type)this.mappings.get(localType1))
        if (paramTypeVariable.equals(localType1))
        {
          for (Type localType2 = paramType; localType2 != null; localType2 = (Type)this.mappings.remove(localType2));
          return;
        }
      this.mappings.put(paramTypeVariable, paramType);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.reflect.TypeResolver
 * JD-Core Version:    0.6.2
 */