package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@GwtCompatible
public final class Objects
{
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static int hashCode(Object[] paramArrayOfObject)
  {
    return Arrays.hashCode(paramArrayOfObject);
  }

  public static ToStringHelper toStringHelper(Object paramObject)
  {
    return new ToStringHelper(simpleName(paramObject.getClass()), null);
  }

  public static ToStringHelper toStringHelper(Class paramClass)
  {
    return new ToStringHelper(simpleName(paramClass), null);
  }

  public static ToStringHelper toStringHelper(String paramString)
  {
    return new ToStringHelper(paramString, null);
  }

  private static String simpleName(Class paramClass)
  {
    String str = paramClass.getName();
    str = str.replaceAll("\\$[0-9]+", "\\$");
    int i = str.lastIndexOf('$');
    if (i == -1)
      i = str.lastIndexOf('.');
    return str.substring(i + 1);
  }

  public static Object firstNonNull(Object paramObject1, Object paramObject2)
  {
    return paramObject1 != null ? paramObject1 : Preconditions.checkNotNull(paramObject2);
  }

  public static final class ToStringHelper
  {
    private final String className;
    private final List valueHolders = new LinkedList();
    private boolean omitNullValues = false;

    private ToStringHelper(String paramString)
    {
      this.className = ((String)Preconditions.checkNotNull(paramString));
    }

    @Beta
    public ToStringHelper omitNullValues()
    {
      this.omitNullValues = true;
      return this;
    }

    public ToStringHelper add(String paramString, Object paramObject)
    {
      Preconditions.checkNotNull(paramString);
      addHolder(paramObject).builder.append(paramString).append('=').append(paramObject);
      return this;
    }

    public ToStringHelper add(String paramString, boolean paramBoolean)
    {
      checkNameAndAppend(paramString).append(paramBoolean);
      return this;
    }

    public ToStringHelper add(String paramString, char paramChar)
    {
      checkNameAndAppend(paramString).append(paramChar);
      return this;
    }

    public ToStringHelper add(String paramString, double paramDouble)
    {
      checkNameAndAppend(paramString).append(paramDouble);
      return this;
    }

    public ToStringHelper add(String paramString, float paramFloat)
    {
      checkNameAndAppend(paramString).append(paramFloat);
      return this;
    }

    public ToStringHelper add(String paramString, int paramInt)
    {
      checkNameAndAppend(paramString).append(paramInt);
      return this;
    }

    public ToStringHelper add(String paramString, long paramLong)
    {
      checkNameAndAppend(paramString).append(paramLong);
      return this;
    }

    private StringBuilder checkNameAndAppend(String paramString)
    {
      Preconditions.checkNotNull(paramString);
      return addHolder().builder.append(paramString).append('=');
    }

    public ToStringHelper addValue(Object paramObject)
    {
      addHolder(paramObject).builder.append(paramObject);
      return this;
    }

    public ToStringHelper addValue(boolean paramBoolean)
    {
      addHolder().builder.append(paramBoolean);
      return this;
    }

    public ToStringHelper addValue(char paramChar)
    {
      addHolder().builder.append(paramChar);
      return this;
    }

    public ToStringHelper addValue(double paramDouble)
    {
      addHolder().builder.append(paramDouble);
      return this;
    }

    public ToStringHelper addValue(float paramFloat)
    {
      addHolder().builder.append(paramFloat);
      return this;
    }

    public ToStringHelper addValue(int paramInt)
    {
      addHolder().builder.append(paramInt);
      return this;
    }

    public ToStringHelper addValue(long paramLong)
    {
      addHolder().builder.append(paramLong);
      return this;
    }

    public String toString()
    {
      boolean bool = this.omitNullValues;
      int i = 0;
      StringBuilder localStringBuilder1 = new StringBuilder(32).append(this.className).append('{');
      Iterator localIterator = this.valueHolders.iterator();
      while (localIterator.hasNext())
      {
        ValueHolder localValueHolder = (ValueHolder)localIterator.next();
        if ((!bool) || (!localValueHolder.isNull))
        {
          if (i != 0)
            localStringBuilder1.append(", ");
          else
            i = 1;
          StringBuilder localStringBuilder2 = localValueHolder.builder;
          localStringBuilder1.append(localStringBuilder2);
        }
      }
      return '}';
    }

    private ValueHolder addHolder()
    {
      ValueHolder localValueHolder = new ValueHolder(null);
      this.valueHolders.add(localValueHolder);
      return localValueHolder;
    }

    private ValueHolder addHolder(Object paramObject)
    {
      ValueHolder localValueHolder = addHolder();
      localValueHolder.isNull = (paramObject == null);
      return localValueHolder;
    }

    private static final class ValueHolder
    {
      final StringBuilder builder = new StringBuilder();
      boolean isNull;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Objects
 * JD-Core Version:    0.6.2
 */