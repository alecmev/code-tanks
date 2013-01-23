package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible(emulated=true)
public abstract class GenericMapMaker
{

  @GwtIncompatible("To be supported")
  MapMaker.RemovalListener removalListener;

  @GwtIncompatible("To be supported")
  abstract GenericMapMaker keyEquivalence(Equivalence paramEquivalence);

  @GwtIncompatible("To be supported")
  abstract GenericMapMaker valueEquivalence(Equivalence paramEquivalence);

  public abstract GenericMapMaker initialCapacity(int paramInt);

  abstract GenericMapMaker maximumSize(int paramInt);

  abstract GenericMapMaker strongKeys();

  public abstract GenericMapMaker concurrencyLevel(int paramInt);

  @GwtIncompatible("java.lang.ref.WeakReference")
  public abstract GenericMapMaker weakKeys();

  abstract GenericMapMaker strongValues();

  @GwtIncompatible("java.lang.ref.SoftReference")
  @Deprecated
  public abstract GenericMapMaker softKeys();

  @GwtIncompatible("java.lang.ref.WeakReference")
  public abstract GenericMapMaker weakValues();

  @GwtIncompatible("java.lang.ref.SoftReference")
  public abstract GenericMapMaker softValues();

  @Deprecated
  public abstract GenericMapMaker expiration(long paramLong, TimeUnit paramTimeUnit);

  abstract GenericMapMaker expireAfterWrite(long paramLong, TimeUnit paramTimeUnit);

  @GwtIncompatible("To be supported")
  abstract GenericMapMaker expireAfterAccess(long paramLong, TimeUnit paramTimeUnit);

  @GwtIncompatible("To be supported")
  MapMaker.RemovalListener getRemovalListener()
  {
    return (MapMaker.RemovalListener)Objects.firstNonNull(this.removalListener, NullListener.INSTANCE);
  }

  public abstract ConcurrentMap makeMap();

  @GwtIncompatible("MapMakerInternalMap")
  abstract MapMakerInternalMap makeCustomMap();

  @Deprecated
  public abstract ConcurrentMap makeComputingMap(Function paramFunction);

  @GwtIncompatible("To be supported")
  static enum NullListener
    implements MapMaker.RemovalListener
  {
    INSTANCE;

    public void onRemoval(MapMaker.RemovalNotification paramRemovalNotification)
    {
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.GenericMapMaker
 * JD-Core Version:    0.6.2
 */