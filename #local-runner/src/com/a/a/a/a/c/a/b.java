package com.a.a.a.a.c.a;

import com.a.a.a.a.a.m;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;
import com.a.a.a.a.c.a;
import java.lang.reflect.Constructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b
  implements e
{
  private static final Logger a = LoggerFactory.getLogger(b.class);
  private static final String b = a.class.getPackage().getName();
  private final com.a.a.a.a.c.b[] c;
  private final int d;

  public b(String paramString, int paramInt)
  {
    if (!paramString.endsWith(".class"))
      throw new IllegalArgumentException(String.format("Illegal player definition: '%s'.", new Object[] { paramString }));
    paramString = paramString.substring(0, paramString.length() - ".class".length());
    if (paramString.indexOf('.') == -1)
      paramString = b + '.' + paramString;
    Constructor localConstructor;
    Object localObject1;
    try
    {
      localConstructor = Class.forName(paramString).getConstructor(new Class[0]);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localObject1 = String.format("Class '%s' does not exist.", new Object[] { paramString });
      a.error((String)localObject1, localClassNotFoundException);
      throw new IllegalArgumentException((String)localObject1, localClassNotFoundException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localObject1 = String.format("Class '%s' hasn't default constructor.", new Object[] { paramString });
      a.error((String)localObject1, localNoSuchMethodException);
      throw new IllegalArgumentException((String)localObject1, localNoSuchMethodException);
    }
    this.c = new com.a.a.a.a.c.b[paramInt];
    for (int i = 0; i < paramInt; i++)
    {
      try
      {
        Object localObject2 = localConstructor.newInstance(new Object[0]);
        if ((localObject2 instanceof com.a.a.a.a.c.b))
        {
          localObject1 = (com.a.a.a.a.c.b)localObject2;
        }
        else
        {
          a.error(String.format("Instance of class '%s' is not a strategy.", new Object[] { paramString }));
          localObject1 = new a();
        }
      }
      catch (Exception localException)
      {
        a.error(String.format("Can't create instance of class '%s'.", new Object[] { paramString }), localException);
        localObject1 = new a();
      }
      this.c[i] = localObject1;
    }
    this.d = paramInt;
  }

  public m[] a(o[] paramArrayOfo, n paramn)
  {
    if (paramArrayOfo.length != this.d)
      throw new IllegalArgumentException(String.format("Strategy adapter '%s' got %d tanks while team size is %d.", new Object[] { getClass().getSimpleName(), Integer.valueOf(paramArrayOfo.length), Integer.valueOf(this.d) }));
    m[] arrayOfm = new m[this.d];
    for (int i = 0; i < this.d; i++)
    {
      arrayOfm[i] = new m();
      this.c[i].a(paramArrayOfo[i], paramn, arrayOfm[i]);
    }
    return arrayOfm;
  }

  public com.a.a.a.a.a.b[] b()
  {
    com.a.a.a.a.a.b[] arrayOfb = new com.a.a.a.a.a.b[this.d];
    for (int i = 0; i < this.d; i++)
      arrayOfb[i] = this.c[i].a(i, this.d);
    return arrayOfb;
  }

  public void close()
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.b
 * JD-Core Version:    0.6.2
 */