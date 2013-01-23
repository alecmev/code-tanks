package com.google.inject.internal.util;

import java.util.ArrayList;
import java.util.List;

public final class $SourceProvider
{
  public static final Object UNKNOWN_SOURCE = "[unknown source]";
  private final .ImmutableSet classNamesToSkip;
  public static final SourceProvider DEFAULT_INSTANCE = new SourceProvider($ImmutableSet.of(SourceProvider.class.getName()));

  private $SourceProvider(Iterable paramIterable)
  {
    this.classNamesToSkip = $ImmutableSet.copyOf(paramIterable);
  }

  public SourceProvider plusSkippedClasses(Class[] paramArrayOfClass)
  {
    return new SourceProvider($Iterables.concat(this.classNamesToSkip, asStrings(paramArrayOfClass)));
  }

  private static List asStrings(Class[] paramArrayOfClass)
  {
    ArrayList localArrayList = $Lists.newArrayList();
    for (Class localClass : paramArrayOfClass)
      localArrayList.add(localClass.getName());
    return localArrayList;
  }

  public StackTraceElement get()
  {
    for (StackTraceElement localStackTraceElement : new Throwable().getStackTrace())
    {
      String str = localStackTraceElement.getClassName();
      if (!this.classNamesToSkip.contains(str))
        return localStackTraceElement;
    }
    throw new AssertionError();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..SourceProvider
 * JD-Core Version:    0.6.2
 */