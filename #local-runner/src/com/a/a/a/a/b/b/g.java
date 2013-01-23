package com.a.a.a.a.b.b;

import com.a.a.a.a.a.i;
import com.a.a.a.a.b.h;
import com.a.a.a.a.c.a.a;
import com.a.a.a.a.c.a.a.c;
import com.a.a.a.a.c.a.b;
import com.a.a.a.a.c.a.f;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class g
{
  private static final Logger a = LoggerFactory.getLogger(g.class);

  private g()
  {
    throw new UnsupportedOperationException();
  }

  public static h a(Map paramMap, int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    a(paramString1, paramString2);
    try
    {
      if (paramString2.endsWith(".class"))
        return new h(paramString1, new b(paramString2, paramInt2));
      if ("#LocalTestPlayer".equals(paramString2))
      {
        localObject = a.b(paramMap, paramInt1, paramString2, paramInt2);
        return a(paramString1, (a)localObject);
      }
      if (c.a(paramString2) != null)
      {
        localObject = a.a(paramMap, paramInt1, paramString2, paramInt2);
        return a(paramString1, (a)localObject);
      }
      Object localObject = String.format("Unsupported player definition: '%s'.", new Object[] { paramString2 });
      a.error((String)localObject);
      throw new IllegalArgumentException((String)localObject);
    }
    catch (RuntimeException localRuntimeException)
    {
      String str = String.format("Can't load player defined by '%s'.", new Object[] { paramString2 });
      a.error(str, localRuntimeException);
      h localh = new h(paramString1, new f(paramInt2));
      localh.e();
      return localh;
    }
  }

  private static h a(String paramString, a parama)
  {
    try
    {
      parama.a();
      return new h(paramString, parama);
    }
    catch (RuntimeException localRuntimeException)
    {
      localRuntimeException.printStackTrace();
      parama.close();
      throw localRuntimeException;
    }
  }

  private static void a(String paramString1, String paramString2)
  {
    if (StringUtils.isBlank(paramString1))
      throw new IllegalArgumentException("Argument 'name' is 'null' or blank.");
    if (StringUtils.isBlank(paramString2))
      throw new IllegalArgumentException("Argument 'playerDefinition' is 'null' or blank.");
  }

  public static i a(h paramh)
  {
    return new i(paramh.a(), paramh.b(), paramh.f(), paramh.d());
  }

  public static i[] a(List paramList)
  {
    int i = paramList.size();
    i[] arrayOfi = new i[i];
    for (int j = 0; j < i; j++)
      arrayOfi[j] = a((h)paramList.get(j));
    return arrayOfi;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.b.g
 * JD-Core Version:    0.6.2
 */