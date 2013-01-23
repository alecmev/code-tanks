package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator
{
  public abstract Object newInstance(Class paramClass)
    throws Exception;

  public static UnsafeAllocator create()
  {
    try
    {
      Class localClass = Class.forName("sun.misc.Unsafe");
      Field localField = localClass.getDeclaredField("theUnsafe");
      localField.setAccessible(true);
      localObject = localField.get(null);
      Method localMethod3 = localClass.getMethod("allocateInstance", new Class[] { Class.class });
      return new UnsafeAllocator()
      {
        public Object newInstance(Class paramAnonymousClass)
          throws Exception
        {
          return this.val$allocateInstance.invoke(localObject, new Object[] { paramAnonymousClass });
        }
      };
    }
    catch (Exception localException1)
    {
      try
      {
        Method localMethod1 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Class.class });
        localMethod1.setAccessible(true);
        return new UnsafeAllocator()
        {
          public Object newInstance(Class paramAnonymousClass)
            throws Exception
          {
            return this.val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Object.class });
          }
        };
      }
      catch (Exception localException2)
      {
        try
        {
          Method localMethod2 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[] { Class.class });
          localMethod2.setAccessible(true);
          final int i = ((Integer)localMethod2.invoke(null, new Object[] { Object.class })).intValue();
          final Object localObject = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Integer.TYPE });
          ((Method)localObject).setAccessible(true);
          return new UnsafeAllocator()
          {
            public Object newInstance(Class paramAnonymousClass)
              throws Exception
            {
              return this.val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Integer.valueOf(i) });
            }
          };
        }
        catch (Exception localException3)
        {
        }
      }
    }
    return new UnsafeAllocator()
    {
      public Object newInstance(Class paramAnonymousClass)
      {
        throw new UnsupportedOperationException("Cannot allocate " + paramAnonymousClass);
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.UnsafeAllocator
 * JD-Core Version:    0.6.2
 */