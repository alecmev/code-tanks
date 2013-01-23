package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Equivalences;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.concurrent.ConcurrentMap;

@Beta
public final class Interners
{
  public static Interner newStrongInterner()
  {
    ConcurrentMap localConcurrentMap = new MapMaker().makeMap();
    return new Interner()
    {
      public Object intern(Object paramAnonymousObject)
      {
        Object localObject = this.val$map.putIfAbsent(Preconditions.checkNotNull(paramAnonymousObject), paramAnonymousObject);
        return localObject == null ? paramAnonymousObject : localObject;
      }
    };
  }

  @GwtIncompatible("java.lang.ref.WeakReference")
  public static Interner newWeakInterner()
  {
    return new WeakInterner(null);
  }

  public static Function asFunction(Interner paramInterner)
  {
    return new InternerFunction((Interner)Preconditions.checkNotNull(paramInterner));
  }

  private static class InternerFunction
    implements Function
  {
    private final Interner interner;

    public InternerFunction(Interner paramInterner)
    {
      this.interner = paramInterner;
    }

    public Object apply(Object paramObject)
    {
      return this.interner.intern(paramObject);
    }

    public int hashCode()
    {
      return this.interner.hashCode();
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof InternerFunction))
      {
        InternerFunction localInternerFunction = (InternerFunction)paramObject;
        return this.interner.equals(localInternerFunction.interner);
      }
      return false;
    }
  }

  private static class WeakInterner
    implements Interner
  {
    private final MapMakerInternalMap map = new MapMaker().weakKeys().keyEquivalence(Equivalences.equals()).makeCustomMap();

    public Object intern(Object paramObject)
    {
      while (true)
      {
        MapMakerInternalMap.ReferenceEntry localReferenceEntry = this.map.getEntry(paramObject);
        if (localReferenceEntry != null)
        {
          localObject = localReferenceEntry.getKey();
          if (localObject != null)
            return localObject;
        }
        Object localObject = (Dummy)this.map.putIfAbsent(paramObject, Dummy.VALUE);
        if (localObject == null)
          return paramObject;
      }
    }

    private static enum Dummy
    {
      VALUE;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.Interners
 * JD-Core Version:    0.6.2
 */