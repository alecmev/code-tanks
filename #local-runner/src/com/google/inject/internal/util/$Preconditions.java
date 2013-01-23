package com.google.inject.internal.util;

import java.util.Collection;
import java.util.Iterator;

public final class $Preconditions
{
  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }

  public static void checkArgument(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.valueOf(paramObject));
  }

  public static void checkArgument(boolean paramBoolean, String paramString, Object[] paramArrayOfObject)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(format(paramString, paramArrayOfObject));
  }

  public static void checkState(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalStateException();
  }

  public static void checkState(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean)
      throw new IllegalStateException(String.valueOf(paramObject));
  }

  public static void checkState(boolean paramBoolean, String paramString, Object[] paramArrayOfObject)
  {
    if (!paramBoolean)
      throw new IllegalStateException(format(paramString, paramArrayOfObject));
  }

  public static Object checkNotNull(Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException();
    return paramObject;
  }

  public static Object checkNotNull(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      throw new NullPointerException(String.valueOf(paramObject2));
    return paramObject1;
  }

  public static Object checkNotNull(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      throw new NullPointerException(format(paramString, paramArrayOfObject));
    return paramObject;
  }

  public static Iterable checkContentsNotNull(Iterable paramIterable)
  {
    if (containsOrIsNull(paramIterable))
      throw new NullPointerException();
    return paramIterable;
  }

  public static Iterable checkContentsNotNull(Iterable paramIterable, Object paramObject)
  {
    if (containsOrIsNull(paramIterable))
      throw new NullPointerException(String.valueOf(paramObject));
    return paramIterable;
  }

  public static Iterable checkContentsNotNull(Iterable paramIterable, String paramString, Object[] paramArrayOfObject)
  {
    if (containsOrIsNull(paramIterable))
      throw new NullPointerException(format(paramString, paramArrayOfObject));
    return paramIterable;
  }

  private static boolean containsOrIsNull(Iterable paramIterable)
  {
    if (paramIterable == null)
      return true;
    if ((paramIterable instanceof Collection))
    {
      localObject1 = (Collection)paramIterable;
      try
      {
        return ((Collection)localObject1).contains(null);
      }
      catch (NullPointerException localNullPointerException)
      {
        return false;
      }
    }
    Object localObject1 = paramIterable.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((Iterator)localObject1).next();
      if (localObject2 == null)
        return true;
    }
    return false;
  }

  public static void checkElementIndex(int paramInt1, int paramInt2)
  {
    checkElementIndex(paramInt1, paramInt2, "index");
  }

  public static void checkElementIndex(int paramInt1, int paramInt2, String paramString)
  {
    checkArgument(paramInt2 >= 0, "negative size: %s", new Object[] { Integer.valueOf(paramInt2) });
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException(format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) }));
    if (paramInt1 >= paramInt2)
      throw new IndexOutOfBoundsException(format("%s (%s) must be less than size (%s)", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }

  public static void checkPositionIndex(int paramInt1, int paramInt2)
  {
    checkPositionIndex(paramInt1, paramInt2, "index");
  }

  public static void checkPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    checkArgument(paramInt2 >= 0, "negative size: %s", new Object[] { Integer.valueOf(paramInt2) });
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException(format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) }));
    if (paramInt1 > paramInt2)
      throw new IndexOutOfBoundsException(format("%s (%s) must not be greater than size (%s)", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }

  public static void checkPositionIndexes(int paramInt1, int paramInt2, int paramInt3)
  {
    checkPositionIndex(paramInt1, paramInt3, "start index");
    checkPositionIndex(paramInt2, paramInt3, "end index");
    if (paramInt2 < paramInt1)
      throw new IndexOutOfBoundsException(format("end index (%s) must not be less than start index (%s)", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) }));
  }

  static String format(String paramString, Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 16 * paramArrayOfObject.length);
    int i = 0;
    int j = 0;
    while (j < paramArrayOfObject.length)
    {
      int k = paramString.indexOf("%s", i);
      if (k == -1)
        break;
      localStringBuilder.append(paramString.substring(i, k));
      localStringBuilder.append(paramArrayOfObject[(j++)]);
      i = k + 2;
    }
    localStringBuilder.append(paramString.substring(i));
    if (j < paramArrayOfObject.length)
    {
      localStringBuilder.append(" [");
      localStringBuilder.append(paramArrayOfObject[(j++)]);
      while (j < paramArrayOfObject.length)
      {
        localStringBuilder.append(", ");
        localStringBuilder.append(paramArrayOfObject[(j++)]);
      }
      localStringBuilder.append("]");
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Preconditions
 * JD-Core Version:    0.6.2
 */