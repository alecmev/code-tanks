package com.google.protobuf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList
  implements LazyStringList, RandomAccess
{
  private final LazyStringList list;

  public UnmodifiableLazyStringList(LazyStringList paramLazyStringList)
  {
    this.list = paramLazyStringList;
  }

  public String get(int paramInt)
  {
    return (String)this.list.get(paramInt);
  }

  public int size()
  {
    return this.list.size();
  }

  public ByteString getByteString(int paramInt)
  {
    return this.list.getByteString(paramInt);
  }

  public void add(ByteString paramByteString)
  {
    throw new UnsupportedOperationException();
  }

  public ListIterator listIterator(final int paramInt)
  {
    return new ListIterator()
    {
      ListIterator iter = UnmodifiableLazyStringList.this.list.listIterator(paramInt);

      public boolean hasNext()
      {
        return this.iter.hasNext();
      }

      public String next()
      {
        return (String)this.iter.next();
      }

      public boolean hasPrevious()
      {
        return this.iter.hasPrevious();
      }

      public String previous()
      {
        return (String)this.iter.previous();
      }

      public int nextIndex()
      {
        return this.iter.nextIndex();
      }

      public int previousIndex()
      {
        return this.iter.previousIndex();
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }

      public void set(String paramAnonymousString)
      {
        throw new UnsupportedOperationException();
      }

      public void add(String paramAnonymousString)
      {
        throw new UnsupportedOperationException();
      }
    };
  }

  public Iterator iterator()
  {
    return new Iterator()
    {
      Iterator iter = UnmodifiableLazyStringList.this.list.iterator();

      public boolean hasNext()
      {
        return this.iter.hasNext();
      }

      public String next()
      {
        return (String)this.iter.next();
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.UnmodifiableLazyStringList
 * JD-Core Version:    0.6.2
 */