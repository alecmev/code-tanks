package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible
final class WellBehavedMap extends ForwardingMap
{
  private final Map delegate;
  private Set entrySet;

  private WellBehavedMap(Map paramMap)
  {
    this.delegate = paramMap;
  }

  static WellBehavedMap wrap(Map paramMap)
  {
    return new WellBehavedMap(paramMap);
  }

  protected Map delegate()
  {
    return this.delegate;
  }

  public Set entrySet()
  {
    Set localSet = this.entrySet;
    if (localSet != null)
      return localSet;
    return this.entrySet = new EntrySet(null);
  }

  private final class EntrySet extends Maps.EntrySet
  {
    private EntrySet()
    {
    }

    Map map()
    {
      return WellBehavedMap.this;
    }

    public Iterator iterator()
    {
      return new TransformedIterator(WellBehavedMap.this.keySet().iterator())
      {
        Map.Entry transform(final Object paramAnonymousObject)
        {
          return new AbstractMapEntry()
          {
            public Object getKey()
            {
              return paramAnonymousObject;
            }

            public Object getValue()
            {
              return WellBehavedMap.this.get(paramAnonymousObject);
            }

            public Object setValue(Object paramAnonymous2Object)
            {
              return WellBehavedMap.this.put(paramAnonymousObject, paramAnonymous2Object);
            }
          };
        }
      };
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.WellBehavedMap
 * JD-Core Version:    0.6.2
 */