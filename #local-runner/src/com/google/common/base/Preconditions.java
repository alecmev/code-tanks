package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;

@GwtCompatible
public final class Preconditions
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

  public static int checkElementIndex(int paramInt1, int paramInt2)
  {
    return checkElementIndex(paramInt1, paramInt2, "index");
  }

  public static int checkElementIndex(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 < 0) || (paramInt1 >= paramInt2))
      throw new IndexOutOfBoundsException(badElementIndex(paramInt1, paramInt2, paramString));
    return paramInt1;
  }

  private static String badElementIndex(int paramInt1, int paramInt2, String paramString)
  {
    if (paramInt1 < 0)
      return format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) });
    if (paramInt2 < 0)
      throw new IllegalArgumentException("negative size: " + paramInt2);
    return format("%s (%s) must be less than size (%s)", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }

  public static int checkPositionIndex(int paramInt1, int paramInt2)
  {
    return checkPositionIndex(paramInt1, paramInt2, "index");
  }

  public static int checkPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramInt2))
      throw new IndexOutOfBoundsException(badPositionIndex(paramInt1, paramInt2, paramString));
    return paramInt1;
  }

  private static String badPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    if (paramInt1 < 0)
      return format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) });
    if (paramInt2 < 0)
      throw new IllegalArgumentException("negative size: " + paramInt2);
    return format("%s (%s) must not be greater than size (%s)", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }

  public static void checkPositionIndexes(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < 0) || (paramInt2 < paramInt1) || (paramInt2 > paramInt3))
      throw new IndexOutOfBoundsException(badPositionIndexes(paramInt1, paramInt2, paramInt3));
  }

  private static String badPositionIndexes(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramInt3))
      return badPositionIndex(paramInt1, paramInt3, "start index");
    if ((paramInt2 < 0) || (paramInt2 > paramInt3))
      return badPositionIndex(paramInt2, paramInt3, "end index");
    return format("end index (%s) must not be less than start index (%s)", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) });
  }

  @VisibleForTesting
  static String format(String paramString, Object[] paramArrayOfObject)
  {
    paramString = String.valueOf(paramString);
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
      localStringBuilder.append(']');
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Preconditions
 * JD-Core Version:    0.6.2
 */