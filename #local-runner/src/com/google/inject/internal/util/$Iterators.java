package com.google.inject.internal.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class $Iterators
{
  static final Iterator EMPTY_ITERATOR = new $UnmodifiableIterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }
  };
  private static final ListIterator EMPTY_LIST_ITERATOR = new ListIterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public boolean hasPrevious()
    {
      return false;
    }

    public int nextIndex()
    {
      return 0;
    }

    public int previousIndex()
    {
      return -1;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }

    public Object previous()
    {
      throw new NoSuchElementException();
    }

    public void set(Object paramAnonymousObject)
    {
      throw new UnsupportedOperationException();
    }

    public void add(Object paramAnonymousObject)
    {
      throw new UnsupportedOperationException();
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  };

  public static .UnmodifiableIterator emptyIterator()
  {
    return ($UnmodifiableIterator)EMPTY_ITERATOR;
  }

  public static ListIterator emptyListIterator()
  {
    return EMPTY_LIST_ITERATOR;
  }

  public static .UnmodifiableIterator unmodifiableIterator(Iterator paramIterator)
  {
    $Preconditions.checkNotNull(paramIterator);
    return new $UnmodifiableIterator()
    {
      public boolean hasNext()
      {
        return this.val$iterator.hasNext();
      }

      public Object next()
      {
        return this.val$iterator.next();
      }
    };
  }

  public static String toString(Iterator paramIterator)
  {
    if (!paramIterator.hasNext())
      return "[]";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[').append(paramIterator.next());
    while (paramIterator.hasNext())
      localStringBuilder.append(", ").append(paramIterator.next());
    return ']';
  }

  public static Object getOnlyElement(Iterator paramIterator)
  {
    Object localObject = paramIterator.next();
    if (!paramIterator.hasNext())
      return localObject;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("expected one element but was: <" + localObject);
    for (int i = 0; (i < 4) && (paramIterator.hasNext()); i++)
      localStringBuilder.append(", " + paramIterator.next());
    if (paramIterator.hasNext())
      localStringBuilder.append(", ...");
    localStringBuilder.append(">");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public static Iterator concat(Iterator paramIterator)
  {
    $Preconditions.checkNotNull(paramIterator);
    return new Iterator()
    {
      Iterator current = $Iterators.emptyIterator();
      Iterator removeFrom;

      public boolean hasNext()
      {
        while ((!this.current.hasNext()) && (this.val$inputs.hasNext()))
          this.current = ((Iterator)this.val$inputs.next());
        return this.current.hasNext();
      }

      public Object next()
      {
        if (!hasNext())
          throw new NoSuchElementException();
        this.removeFrom = this.current;
        return this.current.next();
      }

      public void remove()
      {
        $Preconditions.checkState(this.removeFrom != null, "no calls to next() since last call to remove()");
        this.removeFrom.remove();
        this.removeFrom = null;
      }
    };
  }

  public static Iterator transform(Iterator paramIterator, final .Function paramFunction)
  {
    $Preconditions.checkNotNull(paramIterator);
    $Preconditions.checkNotNull(paramFunction);
    return new Iterator()
    {
      public boolean hasNext()
      {
        return this.val$fromIterator.hasNext();
      }

      public Object next()
      {
        Object localObject = this.val$fromIterator.next();
        return paramFunction.apply(localObject);
      }

      public void remove()
      {
        this.val$fromIterator.remove();
      }
    };
  }

  public static .UnmodifiableIterator forArray(Object[] paramArrayOfObject)
  {
    return new $UnmodifiableIterator()
    {
      final int length = this.val$array.length;
      int i = 0;

      public boolean hasNext()
      {
        return this.i < this.length;
      }

      public Object next()
      {
        try
        {
          Object localObject = this.val$array[this.i];
          this.i += 1;
          return localObject;
        }
        catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
        {
        }
        throw new NoSuchElementException();
      }
    };
  }

  public static .UnmodifiableIterator forArray(final Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    $Preconditions.checkArgument(paramInt2 >= 0);
    final int i = paramInt1 + paramInt2;
    $Preconditions.checkPositionIndexes(paramInt1, i, paramArrayOfObject.length);
    return new $UnmodifiableIterator()
    {
      int i = this.val$offset;

      public boolean hasNext()
      {
        return this.i < i;
      }

      public Object next()
      {
        if (!hasNext())
          throw new NoSuchElementException();
        return paramArrayOfObject[(this.i++)];
      }
    };
  }

  public static .UnmodifiableIterator singletonIterator(@$Nullable Object paramObject)
  {
    return new $UnmodifiableIterator()
    {
      boolean done;

      public boolean hasNext()
      {
        return !this.done;
      }

      public Object next()
      {
        if (this.done)
          throw new NoSuchElementException();
        this.done = true;
        return this.val$value;
      }
    };
  }

  public static Enumeration asEnumeration(Iterator paramIterator)
  {
    $Preconditions.checkNotNull(paramIterator);
    return new Enumeration()
    {
      public boolean hasMoreElements()
      {
        return this.val$iterator.hasNext();
      }

      public Object nextElement()
      {
        return this.val$iterator.next();
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Iterators
 * JD-Core Version:    0.6.2
 */