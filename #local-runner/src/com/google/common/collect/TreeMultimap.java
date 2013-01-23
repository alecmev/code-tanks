package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

@GwtCompatible(serializable=true, emulated=true)
public class TreeMultimap extends AbstractSortedSetMultimap
{
  private transient Comparator keyComparator;
  private transient Comparator valueComparator;

  @GwtIncompatible("not needed in emulated source")
  private static final long serialVersionUID = 0L;

  public static TreeMultimap create()
  {
    return new TreeMultimap(Ordering.natural(), Ordering.natural());
  }

  public static TreeMultimap create(Comparator paramComparator1, Comparator paramComparator2)
  {
    return new TreeMultimap((Comparator)Preconditions.checkNotNull(paramComparator1), (Comparator)Preconditions.checkNotNull(paramComparator2));
  }

  public static TreeMultimap create(Multimap paramMultimap)
  {
    return new TreeMultimap(Ordering.natural(), Ordering.natural(), paramMultimap);
  }

  TreeMultimap(Comparator paramComparator1, Comparator paramComparator2)
  {
    super(new TreeMap(paramComparator1));
    this.keyComparator = paramComparator1;
    this.valueComparator = paramComparator2;
  }

  private TreeMultimap(Comparator paramComparator1, Comparator paramComparator2, Multimap paramMultimap)
  {
    this(paramComparator1, paramComparator2);
    putAll(paramMultimap);
  }

  SortedSet createCollection()
  {
    return new TreeSet(this.valueComparator);
  }

  public Comparator keyComparator()
  {
    return this.keyComparator;
  }

  public Comparator valueComparator()
  {
    return this.valueComparator;
  }

  public SortedSet keySet()
  {
    return (SortedSet)super.keySet();
  }

  public SortedMap asMap()
  {
    return (SortedMap)super.asMap();
  }

  @GwtIncompatible("java.io.ObjectOutputStream")
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeObject(keyComparator());
    paramObjectOutputStream.writeObject(valueComparator());
    Serialization.writeMultimap(this, paramObjectOutputStream);
  }

  @GwtIncompatible("java.io.ObjectInputStream")
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.keyComparator = ((Comparator)Preconditions.checkNotNull((Comparator)paramObjectInputStream.readObject()));
    this.valueComparator = ((Comparator)Preconditions.checkNotNull((Comparator)paramObjectInputStream.readObject()));
    setMap(new TreeMap(this.keyComparator));
    Serialization.populateMultimap(this, paramObjectInputStream);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.TreeMultimap
 * JD-Core Version:    0.6.2
 */