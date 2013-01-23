package com.google.inject.internal.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class $Join
{
  public static String join(String paramString, Iterable paramIterable)
  {
    return join(paramString, paramIterable.iterator());
  }

  public static String join(String paramString, Object[] paramArrayOfObject)
  {
    return join(paramString, Arrays.asList(paramArrayOfObject));
  }

  public static String join(String paramString, @$Nullable Object paramObject, Object[] paramArrayOfObject)
  {
    $Preconditions.checkNotNull(paramArrayOfObject);
    return join(paramString, .Lists.newArrayList(paramObject, paramArrayOfObject));
  }

  public static String join(String paramString, Iterator paramIterator)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    join(localStringBuilder, paramString, paramIterator);
    return localStringBuilder.toString();
  }

  public static String join(String paramString1, String paramString2, Map paramMap)
  {
    return ((StringBuilder)join(new StringBuilder(), paramString1, paramString2, paramMap)).toString();
  }

  public static Appendable join(Appendable paramAppendable, String paramString, Iterable paramIterable)
  {
    return join(paramAppendable, paramString, paramIterable.iterator());
  }

  public static Appendable join(Appendable paramAppendable, String paramString, Object[] paramArrayOfObject)
  {
    return join(paramAppendable, paramString, Arrays.asList(paramArrayOfObject));
  }

  public static Appendable join(Appendable paramAppendable, String paramString, @$Nullable Object paramObject, Object[] paramArrayOfObject)
  {
    $Preconditions.checkNotNull(paramArrayOfObject);
    return join(paramAppendable, paramString, .Lists.newArrayList(paramObject, paramArrayOfObject));
  }

  public static Appendable join(Appendable paramAppendable, String paramString, Iterator paramIterator)
  {
    $Preconditions.checkNotNull(paramAppendable);
    $Preconditions.checkNotNull(paramString);
    if (paramIterator.hasNext())
      try
      {
        appendOneToken(paramAppendable, paramIterator.next());
        while (paramIterator.hasNext())
        {
          paramAppendable.append(paramString);
          appendOneToken(paramAppendable, paramIterator.next());
        }
      }
      catch (IOException localIOException)
      {
        throw new JoinException(localIOException, null);
      }
    return paramAppendable;
  }

  public static Appendable join(Appendable paramAppendable, String paramString1, String paramString2, Map paramMap)
  {
    $Preconditions.checkNotNull(paramAppendable);
    $Preconditions.checkNotNull(paramString1);
    $Preconditions.checkNotNull(paramString2);
    Iterator localIterator = paramMap.entrySet().iterator();
    if (localIterator.hasNext())
      try
      {
        appendOneEntry(paramAppendable, paramString1, (Map.Entry)localIterator.next());
        while (localIterator.hasNext())
        {
          paramAppendable.append(paramString2);
          appendOneEntry(paramAppendable, paramString1, (Map.Entry)localIterator.next());
        }
      }
      catch (IOException localIOException)
      {
        throw new JoinException(localIOException, null);
      }
    return paramAppendable;
  }

  private static void appendOneEntry(Appendable paramAppendable, String paramString, Map.Entry paramEntry)
    throws IOException
  {
    appendOneToken(paramAppendable, paramEntry.getKey());
    paramAppendable.append(paramString);
    appendOneToken(paramAppendable, paramEntry.getValue());
  }

  private static void appendOneToken(Appendable paramAppendable, Object paramObject)
    throws IOException
  {
    paramAppendable.append(toCharSequence(paramObject));
  }

  private static CharSequence toCharSequence(Object paramObject)
  {
    return (paramObject instanceof CharSequence) ? (CharSequence)paramObject : String.valueOf(paramObject);
  }

  public static class JoinException extends RuntimeException
  {
    private static final long serialVersionUID = 1L;

    private JoinException(IOException paramIOException)
    {
      super();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Join
 * JD-Core Version:    0.6.2
 */