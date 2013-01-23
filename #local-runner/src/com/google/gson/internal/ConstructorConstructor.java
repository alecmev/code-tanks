package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class ConstructorConstructor
{
  private final Map instanceCreators;

  public ConstructorConstructor(Map paramMap)
  {
    this.instanceCreators = paramMap;
  }

  public ConstructorConstructor()
  {
    this(Collections.emptyMap());
  }

  public ObjectConstructor get(TypeToken paramTypeToken)
  {
    final Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    final InstanceCreator localInstanceCreator = (InstanceCreator)this.instanceCreators.get(localType);
    if (localInstanceCreator != null)
      return new ObjectConstructor()
      {
        public Object construct()
        {
          return localInstanceCreator.createInstance(localType);
        }
      };
    ObjectConstructor localObjectConstructor1 = newDefaultConstructor(localClass);
    if (localObjectConstructor1 != null)
      return localObjectConstructor1;
    ObjectConstructor localObjectConstructor2 = newDefaultImplementationConstructor(localClass);
    if (localObjectConstructor2 != null)
      return localObjectConstructor2;
    return newUnsafeAllocator(localType, localClass);
  }

  private ObjectConstructor newDefaultConstructor(Class paramClass)
  {
    try
    {
      final Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor.isAccessible())
        localConstructor.setAccessible(true);
      return new ObjectConstructor()
      {
        public Object construct()
        {
          try
          {
            Object[] arrayOfObject = null;
            return localConstructor.newInstance(arrayOfObject);
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    return null;
  }

  private ObjectConstructor newDefaultImplementationConstructor(Class paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public Object construct()
          {
            return new TreeSet();
          }
        };
      if (Set.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public Object construct()
          {
            return new LinkedHashSet();
          }
        };
      if (Queue.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public Object construct()
          {
            return new LinkedList();
          }
        };
      return new ObjectConstructor()
      {
        public Object construct()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
      return new ObjectConstructor()
      {
        public Object construct()
        {
          return new LinkedHashMap();
        }
      };
    return null;
  }

  private ObjectConstructor newUnsafeAllocator(final Type paramType, final Class paramClass)
  {
    return new ObjectConstructor()
    {
      private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();

      public Object construct()
      {
        try
        {
          Object localObject = this.unsafeAllocator.newInstance(paramClass);
          return localObject;
        }
        catch (Exception localException)
        {
          throw new RuntimeException("Unable to invoke no-args constructor for " + paramType + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }

  public String toString()
  {
    return this.instanceCreators.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.ConstructorConstructor
 * JD-Core Version:    0.6.2
 */