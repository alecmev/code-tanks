package com.google.inject.internal.util;

import java.util.Collection;
import java.util.Set;

public final class $Collections2
{
  static Collection toCollection(Iterable paramIterable)
  {
    return (paramIterable instanceof Collection) ? (Collection)paramIterable : .Lists.newArrayList(paramIterable);
  }

  static boolean setEquals(Set paramSet, @$Nullable Object paramObject)
  {
    if (paramObject == paramSet)
      return true;
    if ((paramObject instanceof Set))
    {
      Set localSet = (Set)paramObject;
      return (paramSet.size() == localSet.size()) && (paramSet.containsAll(localSet));
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Collections2
 * JD-Core Version:    0.6.2
 */