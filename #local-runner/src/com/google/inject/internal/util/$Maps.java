package com.google.inject.internal.util;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class $Maps
{
  public static HashMap newHashMap()
  {
    return new HashMap();
  }

  public static LinkedHashMap newLinkedHashMap()
  {
    return new LinkedHashMap();
  }

  public static TreeMap newTreeMap()
  {
    return new TreeMap();
  }

  public static IdentityHashMap newIdentityHashMap()
  {
    return new IdentityHashMap();
  }

  public static Map.Entry immutableEntry(@$Nullable Object paramObject1, @$Nullable Object paramObject2)
  {
    return new $ImmutableEntry(paramObject1, paramObject2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Maps
 * JD-Core Version:    0.6.2
 */