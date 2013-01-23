package com.google.inject.internal.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class $ToStringBuilder
{
  final Map map = new LinkedHashMap();
  final String name;

  public $ToStringBuilder(Class paramClass)
  {
    this.name = paramClass.getSimpleName();
  }

  public ToStringBuilder add(String paramString, Object paramObject)
  {
    if (this.map.put(paramString, paramObject) != null)
      throw new RuntimeException("Duplicate names: " + paramString);
    return this;
  }

  public String toString()
  {
    return this.name + this.map.toString().replace('{', '[').replace('}', ']');
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..ToStringBuilder
 * JD-Core Version:    0.6.2
 */