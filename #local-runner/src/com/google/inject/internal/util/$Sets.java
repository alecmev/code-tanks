package com.google.inject.internal.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class $Sets
{
  public static HashSet newHashSet()
  {
    return new HashSet();
  }

  public static LinkedHashSet newLinkedHashSet()
  {
    return new LinkedHashSet();
  }

  public static Set newSetFromMap(Map paramMap)
  {
    return new SetFromMap(paramMap);
  }

  static int hashCodeImpl(Set paramSet)
  {
    int i = 0;
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      i += (localObject != null ? localObject.hashCode() : 0);
    }
    return i;
  }

  private static class SetFromMap extends AbstractSet
    implements Serializable, Set
  {
    private final Map m;
    private transient Set s;
    static final long serialVersionUID = 0L;

    SetFromMap(Map paramMap)
    {
      $Preconditions.checkArgument(paramMap.isEmpty(), "Map is non-empty");
      this.m = paramMap;
      this.s = paramMap.keySet();
    }

    public void clear()
    {
      this.m.clear();
    }

    public int size()
    {
      return this.m.size();
    }

    public boolean isEmpty()
    {
      return this.m.isEmpty();
    }

    public boolean contains(Object paramObject)
    {
      return this.m.containsKey(paramObject);
    }

    public boolean remove(Object paramObject)
    {
      return this.m.remove(paramObject) != null;
    }

    public boolean add(Object paramObject)
    {
      return this.m.put(paramObject, Boolean.TRUE) == null;
    }

    public Iterator iterator()
    {
      return this.s.iterator();
    }

    public Object[] toArray()
    {
      return this.s.toArray();
    }

    public Object[] toArray(Object[] paramArrayOfObject)
    {
      return this.s.toArray(paramArrayOfObject);
    }

    public String toString()
    {
      return this.s.toString();
    }

    public int hashCode()
    {
      return this.s.hashCode();
    }

    public boolean equals(@$Nullable Object paramObject)
    {
      return (this == paramObject) || (this.s.equals(paramObject));
    }

    public boolean containsAll(Collection paramCollection)
    {
      return this.s.containsAll(paramCollection);
    }

    public boolean removeAll(Collection paramCollection)
    {
      return this.s.removeAll(paramCollection);
    }

    public boolean retainAll(Collection paramCollection)
    {
      return this.s.retainAll(paramCollection);
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      paramObjectInputStream.defaultReadObject();
      this.s = this.m.keySet();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Sets
 * JD-Core Version:    0.6.2
 */