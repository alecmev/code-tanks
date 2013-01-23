package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

@Beta
public abstract class TypeToken extends TypeCapture
  implements Serializable
{
  private final Type runtimeType;
  private transient TypeResolver typeResolver;

  protected TypeToken()
  {
    this.runtimeType = capture();
    Preconditions.checkState(!(this.runtimeType instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", new Object[] { this.runtimeType });
  }

  protected TypeToken(Class paramClass)
  {
    Type localType = super.capture();
    if ((localType instanceof Class))
      this.runtimeType = localType;
    else
      this.runtimeType = of(paramClass).resolveType(localType).runtimeType;
  }

  private TypeToken(Type paramType)
  {
    this.runtimeType = ((Type)Preconditions.checkNotNull(paramType));
  }

  public static TypeToken of(Class paramClass)
  {
    return new SimpleTypeToken(paramClass);
  }

  public static TypeToken of(Type paramType)
  {
    return new SimpleTypeToken(paramType);
  }

  public final Class getRawType()
  {
    Class localClass1 = getRawType(this.runtimeType);
    Class localClass2 = localClass1;
    return localClass2;
  }

  public final Type getType()
  {
    return this.runtimeType;
  }

  public final TypeToken where(TypeParameter paramTypeParameter, TypeToken paramTypeToken)
  {
    TypeResolver localTypeResolver = new TypeResolver().where(ImmutableMap.of(paramTypeParameter.typeVariable, paramTypeToken.runtimeType));
    return new SimpleTypeToken(localTypeResolver.resolve(this.runtimeType));
  }

  public final TypeToken where(TypeParameter paramTypeParameter, Class paramClass)
  {
    return where(paramTypeParameter, of(paramClass));
  }

  public final TypeToken resolveType(Type paramType)
  {
    Preconditions.checkNotNull(paramType);
    TypeResolver localTypeResolver = this.typeResolver;
    if (localTypeResolver == null)
      localTypeResolver = this.typeResolver = TypeResolver.accordingTo(this.runtimeType);
    return of(localTypeResolver.resolve(paramType));
  }

  private TypeToken resolveSupertype(Type paramType)
  {
    TypeToken localTypeToken = resolveType(paramType);
    localTypeToken.typeResolver = this.typeResolver;
    return localTypeToken;
  }

  final TypeToken getGenericSuperclass()
  {
    if ((this.runtimeType instanceof TypeVariable))
      return boundAsSuperclass(((TypeVariable)this.runtimeType).getBounds()[0]);
    if ((this.runtimeType instanceof WildcardType))
      return boundAsSuperclass(((WildcardType)this.runtimeType).getUpperBounds()[0]);
    Type localType = getRawType().getGenericSuperclass();
    if (localType == null)
      return null;
    TypeToken localTypeToken = resolveSupertype(localType);
    return localTypeToken;
  }

  private TypeToken boundAsSuperclass(Type paramType)
  {
    TypeToken localTypeToken1 = of(paramType);
    if (localTypeToken1.getRawType().isInterface())
      return null;
    TypeToken localTypeToken2 = localTypeToken1;
    return localTypeToken2;
  }

  final ImmutableList getGenericInterfaces()
  {
    if ((this.runtimeType instanceof TypeVariable))
      return boundsAsInterfaces(((TypeVariable)this.runtimeType).getBounds());
    if ((this.runtimeType instanceof WildcardType))
      return boundsAsInterfaces(((WildcardType)this.runtimeType).getUpperBounds());
    ImmutableList.Builder localBuilder = ImmutableList.builder();
    for (Type localType : getRawType().getGenericInterfaces())
    {
      TypeToken localTypeToken = resolveSupertype(localType);
      localBuilder.add(localTypeToken);
    }
    return localBuilder.build();
  }

  private ImmutableList boundsAsInterfaces(Type[] paramArrayOfType)
  {
    ImmutableList.Builder localBuilder = ImmutableList.builder();
    for (Type localType : paramArrayOfType)
    {
      TypeToken localTypeToken = of(localType);
      if (localTypeToken.getRawType().isInterface())
        localBuilder.add(localTypeToken);
    }
    return localBuilder.build();
  }

  public final TypeSet getTypes()
  {
    return new TypeSet();
  }

  public final TypeToken getSupertype(Class paramClass)
  {
    Preconditions.checkArgument(paramClass.isAssignableFrom(getRawType()), "%s is not a super class of %s", new Object[] { paramClass, this });
    if ((this.runtimeType instanceof TypeVariable))
      return getSupertypeFromUpperBounds(paramClass, ((TypeVariable)this.runtimeType).getBounds());
    if ((this.runtimeType instanceof WildcardType))
      return getSupertypeFromUpperBounds(paramClass, ((WildcardType)this.runtimeType).getUpperBounds());
    if (paramClass.isArray())
      return getArraySupertype(paramClass);
    TypeToken localTypeToken = resolveSupertype(toGenericType(paramClass).runtimeType);
    return localTypeToken;
  }

  public final TypeToken getSubtype(Class paramClass)
  {
    Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", new Object[] { this });
    if ((this.runtimeType instanceof WildcardType))
      return getSubtypeFromLowerBounds(paramClass, ((WildcardType)this.runtimeType).getLowerBounds());
    Preconditions.checkArgument(getRawType().isAssignableFrom(paramClass), "%s isn't a subclass of %s", new Object[] { paramClass, this });
    if (isArray())
      return getArraySubtype(paramClass);
    TypeToken localTypeToken = of(resolveTypeArgsForSubclass(paramClass));
    return localTypeToken;
  }

  public final boolean isAssignableFrom(TypeToken paramTypeToken)
  {
    return isAssignableFrom(paramTypeToken.runtimeType);
  }

  public final boolean isAssignableFrom(Type paramType)
  {
    return isAssignable((Type)Preconditions.checkNotNull(paramType), this.runtimeType);
  }

  public final boolean isArray()
  {
    return getComponentType() != null;
  }

  public final TypeToken getComponentType()
  {
    Type localType = Types.getComponentType(this.runtimeType);
    if (localType == null)
      return null;
    return of(localType);
  }

  private SortedSet findAllTypes()
  {
    HashMap localHashMap = Maps.newHashMap();
    collectTypes(localHashMap);
    return sortKeysByValue(localHashMap, Ordering.natural().reverse());
  }

  private int collectTypes(Map paramMap)
  {
    Integer localInteger = (Integer)paramMap.get(this);
    if (localInteger != null)
      return localInteger.intValue();
    int i = getRawType().isInterface() ? 1 : 0;
    Object localObject = getGenericInterfaces().iterator();
    while (((Iterator)localObject).hasNext())
    {
      TypeToken localTypeToken = (TypeToken)((Iterator)localObject).next();
      i = Math.max(i, localTypeToken.collectTypes(paramMap));
    }
    localObject = getGenericSuperclass();
    if (localObject != null)
      i = Math.max(i, ((TypeToken)localObject).collectTypes(paramMap));
    paramMap.put(this, Integer.valueOf(i + 1));
    return i + 1;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof TypeToken))
    {
      TypeToken localTypeToken = (TypeToken)paramObject;
      return this.runtimeType.equals(localTypeToken.runtimeType);
    }
    return false;
  }

  public int hashCode()
  {
    return this.runtimeType.hashCode();
  }

  public String toString()
  {
    return Types.toString(this.runtimeType);
  }

  protected Object writeReplace()
  {
    return of(new TypeResolver().resolve(this.runtimeType));
  }

  private static boolean isAssignable(Type paramType1, Type paramType2)
  {
    if (paramType2.equals(paramType1))
      return true;
    if ((paramType2 instanceof WildcardType))
      return isAssignableToWildcardType(paramType1, (WildcardType)paramType2);
    if ((paramType1 instanceof TypeVariable))
      return isAssignableFromAny(((TypeVariable)paramType1).getBounds(), paramType2);
    if ((paramType1 instanceof WildcardType))
      return isAssignableFromAny(((WildcardType)paramType1).getUpperBounds(), paramType2);
    if ((paramType1 instanceof GenericArrayType))
      return isAssignableFromGenericArrayType((GenericArrayType)paramType1, paramType2);
    if ((paramType2 instanceof Class))
      return isAssignableToClass(paramType1, (Class)paramType2);
    if ((paramType2 instanceof ParameterizedType))
      return isAssignableToParameterizedType(paramType1, (ParameterizedType)paramType2);
    if ((paramType2 instanceof GenericArrayType))
      return isAssignableToGenericArrayType(paramType1, (GenericArrayType)paramType2);
    return false;
  }

  private static boolean isAssignableFromAny(Type[] paramArrayOfType, Type paramType)
  {
    for (Type localType : paramArrayOfType)
      if (isAssignable(localType, paramType))
        return true;
    return false;
  }

  private static boolean isAssignableToClass(Type paramType, Class paramClass)
  {
    return paramClass.isAssignableFrom(getRawType(paramType));
  }

  private static boolean isAssignableToWildcardType(Type paramType, WildcardType paramWildcardType)
  {
    return (isAssignable(paramType, supertypeBound(paramWildcardType))) && (isAssignableBySubtypeBound(paramType, paramWildcardType));
  }

  private static boolean isAssignableBySubtypeBound(Type paramType, WildcardType paramWildcardType)
  {
    Type localType1 = subtypeBound(paramWildcardType);
    if (localType1 == null)
      return true;
    Type localType2 = subtypeBound(paramType);
    if (localType2 == null)
      return false;
    return isAssignable(localType1, localType2);
  }

  private static boolean isAssignableToParameterizedType(Type paramType, ParameterizedType paramParameterizedType)
  {
    Class localClass = getRawType(paramParameterizedType);
    if (!localClass.isAssignableFrom(getRawType(paramType)))
      return false;
    TypeVariable[] arrayOfTypeVariable = localClass.getTypeParameters();
    Type[] arrayOfType = paramParameterizedType.getActualTypeArguments();
    TypeToken localTypeToken = of(paramType);
    for (int i = 0; i < arrayOfTypeVariable.length; i++)
    {
      Type localType = localTypeToken.resolveType(arrayOfTypeVariable[i]).runtimeType;
      if (!matchTypeArgument(localType, arrayOfType[i]))
        return false;
    }
    return true;
  }

  private static boolean isAssignableToGenericArrayType(Type paramType, GenericArrayType paramGenericArrayType)
  {
    Object localObject;
    if ((paramType instanceof Class))
    {
      localObject = (Class)paramType;
      if (!((Class)localObject).isArray())
        return false;
      return isAssignable(((Class)localObject).getComponentType(), paramGenericArrayType.getGenericComponentType());
    }
    if ((paramType instanceof GenericArrayType))
    {
      localObject = (GenericArrayType)paramType;
      return isAssignable(((GenericArrayType)localObject).getGenericComponentType(), paramGenericArrayType.getGenericComponentType());
    }
    return false;
  }

  private static boolean isAssignableFromGenericArrayType(GenericArrayType paramGenericArrayType, Type paramType)
  {
    Object localObject;
    if ((paramType instanceof Class))
    {
      localObject = (Class)paramType;
      if (!((Class)localObject).isArray())
        return localObject == Object.class;
      return isAssignable(paramGenericArrayType.getGenericComponentType(), ((Class)localObject).getComponentType());
    }
    if ((paramType instanceof GenericArrayType))
    {
      localObject = (GenericArrayType)paramType;
      return isAssignable(paramGenericArrayType.getGenericComponentType(), ((GenericArrayType)localObject).getGenericComponentType());
    }
    return false;
  }

  private static boolean matchTypeArgument(Type paramType1, Type paramType2)
  {
    if (paramType1.equals(paramType2))
      return true;
    if ((paramType2 instanceof WildcardType))
      return isAssignableToWildcardType(paramType1, (WildcardType)paramType2);
    return false;
  }

  private static Type supertypeBound(Type paramType)
  {
    if ((paramType instanceof WildcardType))
      return supertypeBound((WildcardType)paramType);
    return paramType;
  }

  private static Type supertypeBound(WildcardType paramWildcardType)
  {
    Type[] arrayOfType = paramWildcardType.getUpperBounds();
    if (arrayOfType.length == 1)
      return supertypeBound(arrayOfType[0]);
    if (arrayOfType.length == 0)
      return Object.class;
    throw new AssertionError("There should be at most one upper bound for wildcard type: " + paramWildcardType);
  }

  private static Type subtypeBound(Type paramType)
  {
    if ((paramType instanceof WildcardType))
      return subtypeBound((WildcardType)paramType);
    return paramType;
  }

  private static Type subtypeBound(WildcardType paramWildcardType)
  {
    Type[] arrayOfType = paramWildcardType.getLowerBounds();
    if (arrayOfType.length == 1)
      return subtypeBound(arrayOfType[0]);
    if (arrayOfType.length == 0)
      return null;
    throw new AssertionError("Wildcard should have at most one lower bound: " + paramWildcardType);
  }

  @VisibleForTesting
  static Class getRawType(Type paramType)
  {
    if ((paramType instanceof Class))
      return (Class)paramType;
    Object localObject;
    if ((paramType instanceof ParameterizedType))
    {
      localObject = (ParameterizedType)paramType;
      return (Class)((ParameterizedType)localObject).getRawType();
    }
    if ((paramType instanceof GenericArrayType))
    {
      localObject = (GenericArrayType)paramType;
      return Types.getArrayClass(getRawType(((GenericArrayType)localObject).getGenericComponentType()));
    }
    if ((paramType instanceof TypeVariable))
      return getRawType(((TypeVariable)paramType).getBounds()[0]);
    if ((paramType instanceof WildcardType))
      return getRawType(((WildcardType)paramType).getUpperBounds()[0]);
    throw new AssertionError(paramType + " unsupported");
  }

  @VisibleForTesting
  static TypeToken toGenericType(Class paramClass)
  {
    TypeToken localTypeToken;
    if (paramClass.isArray())
    {
      localObject = Types.newArrayType(toGenericType(paramClass.getComponentType()).runtimeType);
      localTypeToken = of((Type)localObject);
      return localTypeToken;
    }
    Object localObject = paramClass.getTypeParameters();
    if (localObject.length > 0)
    {
      localTypeToken = of(Types.newParameterizedType(paramClass, (Type[])localObject));
      return localTypeToken;
    }
    return of(paramClass);
  }

  private TypeToken getSupertypeFromUpperBounds(Class paramClass, Type[] paramArrayOfType)
  {
    for (Type localType : paramArrayOfType)
    {
      TypeToken localTypeToken1 = of(localType);
      if (of(paramClass).isAssignableFrom(localTypeToken1))
      {
        TypeToken localTypeToken2 = localTypeToken1.getSupertype(paramClass);
        return localTypeToken2;
      }
    }
    throw new IllegalArgumentException(paramClass + " isn't a super type of " + this);
  }

  private TypeToken getSubtypeFromLowerBounds(Class paramClass, Type[] paramArrayOfType)
  {
    Type[] arrayOfType = paramArrayOfType;
    int i = arrayOfType.length;
    int j = 0;
    if (j < i)
    {
      Type localType = arrayOfType[j];
      TypeToken localTypeToken = of(localType);
      return localTypeToken.getSubtype(paramClass);
    }
    throw new IllegalArgumentException(paramClass + " isn't a subclass of " + this);
  }

  private TypeToken getArraySupertype(Class paramClass)
  {
    TypeToken localTypeToken1 = (TypeToken)Preconditions.checkNotNull(getComponentType(), "%s isn't a super type of %s", new Object[] { paramClass, this });
    TypeToken localTypeToken2 = localTypeToken1.getSupertype(paramClass.getComponentType());
    TypeToken localTypeToken3 = of(newArrayClassOrGenericArrayType(localTypeToken2.runtimeType));
    return localTypeToken3;
  }

  private TypeToken getArraySubtype(Class paramClass)
  {
    TypeToken localTypeToken1 = getComponentType().getSubtype(paramClass.getComponentType());
    TypeToken localTypeToken2 = of(newArrayClassOrGenericArrayType(localTypeToken1.runtimeType));
    return localTypeToken2;
  }

  private Type resolveTypeArgsForSubclass(Class paramClass)
  {
    if ((this.runtimeType instanceof Class))
      return paramClass;
    TypeToken localTypeToken = toGenericType(paramClass);
    Type localType = localTypeToken.getSupertype(getRawType()).runtimeType;
    return new TypeResolver().where(localType, this.runtimeType).resolve(localTypeToken.runtimeType);
  }

  private static Type newArrayClassOrGenericArrayType(Type paramType)
  {
    return Types.JavaVersion.JAVA7.newArrayType(paramType);
  }

  private static ImmutableSortedSet sortKeysByValue(final Map paramMap, Comparator paramComparator)
  {
    Comparator local1 = new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        return this.val$valueComparator.compare(paramMap.get(paramAnonymousObject1), paramMap.get(paramAnonymousObject2));
      }
    };
    return ImmutableSortedSet.copyOf(local1, paramMap.keySet());
  }

  private static final class SimpleTypeToken extends TypeToken
  {
    private static final long serialVersionUID = 0L;

    SimpleTypeToken(Type paramType)
    {
      super(null);
    }
  }

  private static abstract enum TypeFilter
    implements Predicate
  {
    IGNORE_TYPE_VARIABLE_OR_WILDCARD, INTERFACE_ONLY;
  }

  private final class ClassSet extends TypeToken.TypeSet
  {
    private final transient ImmutableSet classes = ImmutableSet.copyOf(Iterators.filter(new AbstractSequentialIterator(TypeToken.this.getRawType().isInterface() ? null : TypeToken.this)
    {
      protected TypeToken computeNext(TypeToken paramAnonymousTypeToken)
      {
        return paramAnonymousTypeToken.getGenericSuperclass();
      }
    }
    , TypeToken.TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD));
    private static final long serialVersionUID = 0L;

    private ClassSet()
    {
      super();
    }

    protected Set delegate()
    {
      return this.classes;
    }

    public TypeToken.TypeSet classes()
    {
      return this;
    }

    public TypeToken.TypeSet interfaces()
    {
      throw new UnsupportedOperationException("classes().interfaces() not supported.");
    }

    private Object readResolve()
    {
      return TypeToken.this.getTypes().classes();
    }
  }

  private final class InterfaceSet extends TypeToken.TypeSet
  {
    private final transient ImmutableSet interfaces;
    private static final long serialVersionUID = 0L;

    InterfaceSet(Iterable arg2)
    {
      super();
      Iterable localIterable;
      this.interfaces = ImmutableSet.copyOf(Iterables.filter(localIterable, TypeToken.TypeFilter.INTERFACE_ONLY));
    }

    protected Set delegate()
    {
      return this.interfaces;
    }

    public TypeToken.TypeSet interfaces()
    {
      return this;
    }

    public TypeToken.TypeSet classes()
    {
      throw new UnsupportedOperationException("interfaces().classes() not supported.");
    }

    private Object readResolve()
    {
      return TypeToken.this.getTypes().interfaces();
    }
  }

  public class TypeSet extends ForwardingSet
    implements Serializable
  {
    private transient ImmutableSet types;
    private static final long serialVersionUID = 0L;

    TypeSet()
    {
    }

    public TypeSet interfaces()
    {
      return new TypeToken.InterfaceSet(TypeToken.this, this);
    }

    public TypeSet classes()
    {
      return new TypeToken.ClassSet(TypeToken.this, null);
    }

    protected Set delegate()
    {
      ImmutableSet localImmutableSet = this.types;
      if (localImmutableSet == null)
        return this.types = ImmutableSet.copyOf(Sets.filter(TypeToken.this.findAllTypes(), TypeToken.TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD));
      return localImmutableSet;
    }

    public final Set rawTypes()
    {
      ImmutableSet.Builder localBuilder = ImmutableSet.builder();
      Iterator localIterator = iterator();
      while (localIterator.hasNext())
      {
        TypeToken localTypeToken = (TypeToken)localIterator.next();
        localBuilder.add(localTypeToken.getRawType());
      }
      return localBuilder.build();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.reflect.TypeToken
 * JD-Core Version:    0.6.2
 */