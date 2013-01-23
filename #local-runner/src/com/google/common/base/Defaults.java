package com.google.common.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Defaults
{
  private static final Map DEFAULTS = Collections.unmodifiableMap(localHashMap);

  private static void put(Map paramMap, Class paramClass, Object paramObject)
  {
    paramMap.put(paramClass, paramObject);
  }

  public static Object defaultValue(Class paramClass)
  {
    return DEFAULTS.get(paramClass);
  }

  static
  {
    HashMap localHashMap = new HashMap();
    put(localHashMap, Boolean.TYPE, Boolean.valueOf(false));
    put(localHashMap, Character.TYPE, Character.valueOf('\000'));
    put(localHashMap, Byte.TYPE, Byte.valueOf((byte)0));
    put(localHashMap, Short.TYPE, Short.valueOf((short)0));
    put(localHashMap, Integer.TYPE, Integer.valueOf(0));
    put(localHashMap, Long.TYPE, Long.valueOf(0L));
    put(localHashMap, Float.TYPE, Float.valueOf(0.0F));
    put(localHashMap, Double.TYPE, Double.valueOf(0.0D));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Defaults
 * JD-Core Version:    0.6.2
 */