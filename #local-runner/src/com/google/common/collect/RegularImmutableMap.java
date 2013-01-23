package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.Map.Entry;

@GwtCompatible(serializable=true, emulated=true)
final class RegularImmutableMap extends ImmutableMap
{
  private final transient LinkedEntry[] entries;
  private final transient LinkedEntry[] table;
  private final transient int mask;
  private final transient int keySetHashCode;
  private static final long serialVersionUID = 0L;

  RegularImmutableMap(Map.Entry[] paramArrayOfEntry)
  {
    int i = paramArrayOfEntry.length;
    this.entries = createEntryArray(i);
    int j = chooseTableSize(i);
    this.table = createEntryArray(j);
    this.mask = (j - 1);
    int k = 0;
    for (int m = 0; m < i; m++)
    {
      Map.Entry localEntry = paramArrayOfEntry[m];
      Object localObject = localEntry.getKey();
      int n = localObject.hashCode();
      k += n;
      int i1 = Hashing.smear(n) & this.mask;
      LinkedEntry localLinkedEntry1 = this.table[i1];
      LinkedEntry localLinkedEntry2 = newLinkedEntry(localObject, localEntry.getValue(), localLinkedEntry1);
      this.table[i1] = localLinkedEntry2;
      this.entries[m] = localLinkedEntry2;
      while (localLinkedEntry1 != null)
      {
        Preconditions.checkArgument(!localObject.equals(localLinkedEntry1.getKey()), "duplicate key: %s", new Object[] { localObject });
        localLinkedEntry1 = localLinkedEntry1.next();
      }
    }
    this.keySetHashCode = k;
  }

  private static int chooseTableSize(int paramInt)
  {
    int i = Integer.highestOneBit(paramInt) << 1;
    Preconditions.checkArgument(i > 0, "table too large: %s", new Object[] { Integer.valueOf(paramInt) });
    return i;
  }

  private LinkedEntry[] createEntryArray(int paramInt)
  {
    return new LinkedEntry[paramInt];
  }

  private static LinkedEntry newLinkedEntry(Object paramObject1, Object paramObject2, LinkedEntry paramLinkedEntry)
  {
    return paramLinkedEntry == null ? new TerminalEntry(paramObject1, paramObject2) : new NonTerminalEntry(paramObject1, paramObject2, paramLinkedEntry);
  }

  public Object get(Object paramObject)
  {
    if (paramObject == null)
      return null;
    int i = Hashing.smear(paramObject.hashCode()) & this.mask;
    for (LinkedEntry localLinkedEntry = this.table[i]; localLinkedEntry != null; localLinkedEntry = localLinkedEntry.next())
    {
      Object localObject = localLinkedEntry.getKey();
      if (paramObject.equals(localObject))
        return localLinkedEntry.getValue();
    }
    return null;
  }

  public int size()
  {
    return this.entries.length;
  }

  public boolean isEmpty()
  {
    return false;
  }

  public boolean containsValue(Object paramObject)
  {
    if (paramObject == null)
      return false;
    for (LinkedEntry localLinkedEntry : this.entries)
      if (localLinkedEntry.getValue().equals(paramObject))
        return true;
    return false;
  }

  boolean isPartialView()
  {
    return false;
  }

  ImmutableSet createEntrySet()
  {
    return new EntrySet(null);
  }

  ImmutableSet createKeySet()
  {
    return new ImmutableMap.KeySet(this, this.keySetHashCode);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = Collections2.newStringBuilderForCollection(size()).append('{');
    Collections2.STANDARD_JOINER.appendTo(localStringBuilder, this.entries);
    return '}';
  }

  private class EntrySet extends ImmutableMap.EntrySet
  {
    private EntrySet()
    {
      super();
    }

    public UnmodifiableIterator iterator()
    {
      return asList().iterator();
    }

    ImmutableList createAsList()
    {
      return new RegularImmutableList(RegularImmutableMap.this.entries);
    }
  }

  private static final class TerminalEntry extends ImmutableEntry
    implements RegularImmutableMap.LinkedEntry
  {
    TerminalEntry(Object paramObject1, Object paramObject2)
    {
      super(paramObject2);
    }

    public RegularImmutableMap.LinkedEntry next()
    {
      return null;
    }
  }

  private static final class NonTerminalEntry extends ImmutableEntry
    implements RegularImmutableMap.LinkedEntry
  {
    final RegularImmutableMap.LinkedEntry next;

    NonTerminalEntry(Object paramObject1, Object paramObject2, RegularImmutableMap.LinkedEntry paramLinkedEntry)
    {
      super(paramObject2);
      this.next = paramLinkedEntry;
    }

    public RegularImmutableMap.LinkedEntry next()
    {
      return this.next;
    }
  }

  private static abstract interface LinkedEntry extends Map.Entry
  {
    public abstract LinkedEntry next();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.RegularImmutableMap
 * JD-Core Version:    0.6.2
 */