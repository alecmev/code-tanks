package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@GwtCompatible
public class Joiner
{
  private final String separator;

  public static Joiner on(String paramString)
  {
    return new Joiner(paramString);
  }

  public static Joiner on(char paramChar)
  {
    return new Joiner(String.valueOf(paramChar));
  }

  private Joiner(String paramString)
  {
    this.separator = ((String)Preconditions.checkNotNull(paramString));
  }

  private Joiner(Joiner paramJoiner)
  {
    this.separator = paramJoiner.separator;
  }

  @Beta
  @Deprecated
  public final Appendable appendTo(Appendable paramAppendable, Object paramObject)
    throws IOException
  {
    return appendTo(paramAppendable, (Iterator)paramObject);
  }

  public Appendable appendTo(Appendable paramAppendable, Iterable paramIterable)
    throws IOException
  {
    return appendTo(paramAppendable, paramIterable.iterator());
  }

  @Beta
  public Appendable appendTo(Appendable paramAppendable, Iterator paramIterator)
    throws IOException
  {
    Preconditions.checkNotNull(paramAppendable);
    if (paramIterator.hasNext())
    {
      paramAppendable.append(toString(paramIterator.next()));
      while (paramIterator.hasNext())
      {
        paramAppendable.append(this.separator);
        paramAppendable.append(toString(paramIterator.next()));
      }
    }
    return paramAppendable;
  }

  public final Appendable appendTo(Appendable paramAppendable, Object[] paramArrayOfObject)
    throws IOException
  {
    return appendTo(paramAppendable, Arrays.asList(paramArrayOfObject));
  }

  public final Appendable appendTo(Appendable paramAppendable, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
    throws IOException
  {
    return appendTo(paramAppendable, iterable(paramObject1, paramObject2, paramArrayOfObject));
  }

  @Beta
  @Deprecated
  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Object paramObject)
  {
    return appendTo(paramStringBuilder, (Iterator)paramObject);
  }

  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Iterable paramIterable)
  {
    return appendTo(paramStringBuilder, paramIterable.iterator());
  }

  @Beta
  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Iterator paramIterator)
  {
    try
    {
      appendTo(paramStringBuilder, paramIterator);
    }
    catch (IOException localIOException)
    {
      throw new AssertionError(localIOException);
    }
    return paramStringBuilder;
  }

  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Object[] paramArrayOfObject)
  {
    return appendTo(paramStringBuilder, Arrays.asList(paramArrayOfObject));
  }

  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    return appendTo(paramStringBuilder, iterable(paramObject1, paramObject2, paramArrayOfObject));
  }

  @Beta
  @Deprecated
  public final String join(Object paramObject)
  {
    return join((Iterator)paramObject);
  }

  public final String join(Iterable paramIterable)
  {
    return join(paramIterable.iterator());
  }

  @Beta
  public final String join(Iterator paramIterator)
  {
    return appendTo(new StringBuilder(), paramIterator).toString();
  }

  public final String join(Object[] paramArrayOfObject)
  {
    return join(Arrays.asList(paramArrayOfObject));
  }

  public final String join(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    return join(iterable(paramObject1, paramObject2, paramArrayOfObject));
  }

  public Joiner useForNull(final String paramString)
  {
    Preconditions.checkNotNull(paramString);
    return new Joiner(this, paramString)
    {
      CharSequence toString(Object paramAnonymousObject)
      {
        return paramAnonymousObject == null ? paramString : Joiner.this.toString(paramAnonymousObject);
      }

      public Joiner useForNull(String paramAnonymousString)
      {
        Preconditions.checkNotNull(paramAnonymousString);
        throw new UnsupportedOperationException("already specified useForNull");
      }

      public Joiner skipNulls()
      {
        throw new UnsupportedOperationException("already specified useForNull");
      }
    };
  }

  public Joiner skipNulls()
  {
    // Byte code:
    //   0: new 3	com/google/common/base/Joiner$2
    //   3: dup
    //   4: aload_0
    //   5: aload_0
    //   6: invokespecial 29	com/google/common/base/Joiner$2:<init>	(Lcom/google/common/base/Joiner;Lcom/google/common/base/Joiner;)V
    //   9: areturn
  }

  public MapJoiner withKeyValueSeparator(String paramString)
  {
    return new MapJoiner(this, paramString, null);
  }

  CharSequence toString(Object paramObject)
  {
    Preconditions.checkNotNull(paramObject);
    return (paramObject instanceof CharSequence) ? (CharSequence)paramObject : paramObject.toString();
  }

  private static Iterable iterable(final Object paramObject1, final Object paramObject2, Object[] paramArrayOfObject)
  {
    Preconditions.checkNotNull(paramArrayOfObject);
    return new AbstractList()
    {
      public int size()
      {
        return this.val$rest.length + 2;
      }

      public Object get(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        case 0:
          return paramObject1;
        case 1:
          return paramObject2;
        }
        return this.val$rest[(paramAnonymousInt - 2)];
      }
    };
  }

  public static final class MapJoiner
  {
    private final Joiner joiner;
    private final String keyValueSeparator;

    private MapJoiner(Joiner paramJoiner, String paramString)
    {
      this.joiner = paramJoiner;
      this.keyValueSeparator = ((String)Preconditions.checkNotNull(paramString));
    }

    public Appendable appendTo(Appendable paramAppendable, Map paramMap)
      throws IOException
    {
      return appendTo(paramAppendable, paramMap.entrySet());
    }

    public StringBuilder appendTo(StringBuilder paramStringBuilder, Map paramMap)
    {
      return appendTo(paramStringBuilder, paramMap.entrySet());
    }

    public String join(Map paramMap)
    {
      return join(paramMap.entrySet());
    }

    @Beta
    @Deprecated
    public Appendable appendTo(Appendable paramAppendable, Object paramObject)
      throws IOException
    {
      Iterator localIterator = (Iterator)paramObject;
      return appendTo(paramAppendable, localIterator);
    }

    @Beta
    public Appendable appendTo(Appendable paramAppendable, Iterable paramIterable)
      throws IOException
    {
      return appendTo(paramAppendable, paramIterable.iterator());
    }

    @Beta
    public Appendable appendTo(Appendable paramAppendable, Iterator paramIterator)
      throws IOException
    {
      Preconditions.checkNotNull(paramAppendable);
      if (paramIterator.hasNext())
      {
        Map.Entry localEntry1 = (Map.Entry)paramIterator.next();
        paramAppendable.append(this.joiner.toString(localEntry1.getKey()));
        paramAppendable.append(this.keyValueSeparator);
        paramAppendable.append(this.joiner.toString(localEntry1.getValue()));
        while (paramIterator.hasNext())
        {
          paramAppendable.append(this.joiner.separator);
          Map.Entry localEntry2 = (Map.Entry)paramIterator.next();
          paramAppendable.append(this.joiner.toString(localEntry2.getKey()));
          paramAppendable.append(this.keyValueSeparator);
          paramAppendable.append(this.joiner.toString(localEntry2.getValue()));
        }
      }
      return paramAppendable;
    }

    @Beta
    @Deprecated
    public StringBuilder appendTo(StringBuilder paramStringBuilder, Object paramObject)
      throws IOException
    {
      Iterator localIterator = (Iterator)paramObject;
      return appendTo(paramStringBuilder, localIterator);
    }

    @Beta
    public StringBuilder appendTo(StringBuilder paramStringBuilder, Iterable paramIterable)
    {
      return appendTo(paramStringBuilder, paramIterable.iterator());
    }

    @Beta
    public StringBuilder appendTo(StringBuilder paramStringBuilder, Iterator paramIterator)
    {
      try
      {
        appendTo(paramStringBuilder, paramIterator);
      }
      catch (IOException localIOException)
      {
        throw new AssertionError(localIOException);
      }
      return paramStringBuilder;
    }

    @Beta
    @Deprecated
    public String join(Object paramObject)
      throws IOException
    {
      Iterator localIterator = (Iterator)paramObject;
      return join(localIterator);
    }

    @Beta
    public String join(Iterable paramIterable)
    {
      return join(paramIterable.iterator());
    }

    @Beta
    public String join(Iterator paramIterator)
    {
      return appendTo(new StringBuilder(), paramIterator).toString();
    }

    public MapJoiner useForNull(String paramString)
    {
      return new MapJoiner(this.joiner.useForNull(paramString), this.keyValueSeparator);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Joiner
 * JD-Core Version:    0.6.2
 */