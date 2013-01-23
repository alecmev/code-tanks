package com.google.inject.internal.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public final class $MapMaker
{
  private Strength keyStrength = Strength.STRONG;
  private Strength valueStrength = Strength.STRONG;
  private long expirationNanos = 0L;
  private boolean useCustomMap;
  private final .CustomConcurrentHashMap.Builder builder = new $CustomConcurrentHashMap.Builder();
  private static final ValueReference COMPUTING = new ValueReference()
  {
    public Object get()
    {
      return null;
    }

    public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramAnonymousReferenceEntry)
    {
      throw new AssertionError();
    }

    public Object waitForValue()
    {
      throw new AssertionError();
    }
  };

  public MapMaker initialCapacity(int paramInt)
  {
    this.builder.initialCapacity(paramInt);
    return this;
  }

  public MapMaker loadFactor(float paramFloat)
  {
    this.builder.loadFactor(paramFloat);
    return this;
  }

  public MapMaker concurrencyLevel(int paramInt)
  {
    this.builder.concurrencyLevel(paramInt);
    return this;
  }

  public MapMaker weakKeys()
  {
    return setKeyStrength(Strength.WEAK);
  }

  public MapMaker softKeys()
  {
    return setKeyStrength(Strength.SOFT);
  }

  private MapMaker setKeyStrength(Strength paramStrength)
  {
    if (this.keyStrength != Strength.STRONG)
      throw new IllegalStateException("Key strength was already set to " + this.keyStrength + ".");
    this.keyStrength = paramStrength;
    this.useCustomMap = true;
    return this;
  }

  public MapMaker weakValues()
  {
    return setValueStrength(Strength.WEAK);
  }

  public MapMaker softValues()
  {
    return setValueStrength(Strength.SOFT);
  }

  private MapMaker setValueStrength(Strength paramStrength)
  {
    if (this.valueStrength != Strength.STRONG)
      throw new IllegalStateException("Value strength was already set to " + this.valueStrength + ".");
    this.valueStrength = paramStrength;
    this.useCustomMap = true;
    return this;
  }

  public MapMaker expiration(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.expirationNanos != 0L)
      throw new IllegalStateException("expiration time of " + this.expirationNanos + " ns was already set");
    if (paramLong <= 0L)
      throw new IllegalArgumentException("invalid duration: " + paramLong);
    this.expirationNanos = paramTimeUnit.toNanos(paramLong);
    this.useCustomMap = true;
    return this;
  }

  public ConcurrentMap makeMap()
  {
    return this.useCustomMap ? new StrategyImpl(this).map : new ConcurrentHashMap(this.builder.initialCapacity, this.builder.loadFactor, this.builder.concurrencyLevel);
  }

  public ConcurrentMap makeComputingMap($Function paramFunction)
  {
    return new StrategyImpl(this, paramFunction).map;
  }

  private static ValueReference computing()
  {
    return COMPUTING;
  }

  private static class StrongValueReference
    implements .MapMaker.ValueReference
  {
    final Object referent;

    StrongValueReference(Object paramObject)
    {
      this.referent = paramObject;
    }

    public Object get()
    {
      return this.referent;
    }

    public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return this;
    }

    public Object waitForValue()
    {
      return get();
    }
  }

  private static class SoftValueReference extends $FinalizableSoftReference
    implements .MapMaker.ValueReference
  {
    final .MapMaker.ReferenceEntry entry;

    SoftValueReference(Object paramObject, .MapMaker.ReferenceEntry paramReferenceEntry)
    {
      super($MapMaker.QueueHolder.queue);
      this.entry = paramReferenceEntry;
    }

    public void finalizeReferent()
    {
      this.entry.valueReclaimed();
    }

    public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return new SoftValueReference(get(), paramReferenceEntry);
    }

    public Object waitForValue()
    {
      return get();
    }
  }

  private static class WeakValueReference extends $FinalizableWeakReference
    implements .MapMaker.ValueReference
  {
    final .MapMaker.ReferenceEntry entry;

    WeakValueReference(Object paramObject, .MapMaker.ReferenceEntry paramReferenceEntry)
    {
      super($MapMaker.QueueHolder.queue);
      this.entry = paramReferenceEntry;
    }

    public void finalizeReferent()
    {
      this.entry.valueReclaimed();
    }

    public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return new WeakValueReference(get(), paramReferenceEntry);
    }

    public Object waitForValue()
      throws InterruptedException
    {
      return get();
    }
  }

  private static class LinkedWeakEntry extends $MapMaker.WeakEntry
  {
    final .MapMaker.ReferenceEntry next;

    LinkedWeakEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt, .MapMaker.ReferenceEntry paramReferenceEntry)
    {
      super(paramObject, paramInt);
      this.next = paramReferenceEntry;
    }

    public .MapMaker.ReferenceEntry getNext()
    {
      return this.next;
    }
  }

  private static class WeakEntry extends $FinalizableWeakReference
    implements .MapMaker.ReferenceEntry
  {
    final .CustomConcurrentHashMap.Internals internals;
    final int hash;
    volatile .MapMaker.ValueReference valueReference = $MapMaker.access$600();

    WeakEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt)
    {
      super($MapMaker.QueueHolder.queue);
      this.internals = paramInternals;
      this.hash = paramInt;
    }

    public Object getKey()
    {
      return get();
    }

    public void finalizeReferent()
    {
      this.internals.removeEntry(this);
    }

    public .MapMaker.ValueReference getValueReference()
    {
      return this.valueReference;
    }

    public void setValueReference($MapMaker.ValueReference paramValueReference)
    {
      this.valueReference = paramValueReference;
    }

    public void valueReclaimed()
    {
      this.internals.removeEntry(this, null);
    }

    public .MapMaker.ReferenceEntry getNext()
    {
      return null;
    }

    public int getHash()
    {
      return this.hash;
    }
  }

  private static class LinkedSoftEntry extends $MapMaker.SoftEntry
  {
    final .MapMaker.ReferenceEntry next;

    LinkedSoftEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt, .MapMaker.ReferenceEntry paramReferenceEntry)
    {
      super(paramObject, paramInt);
      this.next = paramReferenceEntry;
    }

    public .MapMaker.ReferenceEntry getNext()
    {
      return this.next;
    }
  }

  private static class SoftEntry extends $FinalizableSoftReference
    implements .MapMaker.ReferenceEntry
  {
    final .CustomConcurrentHashMap.Internals internals;
    final int hash;
    volatile .MapMaker.ValueReference valueReference = $MapMaker.access$600();

    SoftEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt)
    {
      super($MapMaker.QueueHolder.queue);
      this.internals = paramInternals;
      this.hash = paramInt;
    }

    public Object getKey()
    {
      return get();
    }

    public void finalizeReferent()
    {
      this.internals.removeEntry(this);
    }

    public .MapMaker.ValueReference getValueReference()
    {
      return this.valueReference;
    }

    public void setValueReference($MapMaker.ValueReference paramValueReference)
    {
      this.valueReference = paramValueReference;
    }

    public void valueReclaimed()
    {
      this.internals.removeEntry(this, null);
    }

    public .MapMaker.ReferenceEntry getNext()
    {
      return null;
    }

    public int getHash()
    {
      return this.hash;
    }
  }

  private static class LinkedStrongEntry extends $MapMaker.StrongEntry
  {
    final .MapMaker.ReferenceEntry next;

    LinkedStrongEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt, .MapMaker.ReferenceEntry paramReferenceEntry)
    {
      super(paramObject, paramInt);
      this.next = paramReferenceEntry;
    }

    public .MapMaker.ReferenceEntry getNext()
    {
      return this.next;
    }
  }

  private static class StrongEntry
    implements .MapMaker.ReferenceEntry
  {
    final Object key;
    final .CustomConcurrentHashMap.Internals internals;
    final int hash;
    volatile .MapMaker.ValueReference valueReference = $MapMaker.access$600();

    StrongEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt)
    {
      this.internals = paramInternals;
      this.key = paramObject;
      this.hash = paramInt;
    }

    public Object getKey()
    {
      return this.key;
    }

    public .MapMaker.ValueReference getValueReference()
    {
      return this.valueReference;
    }

    public void setValueReference($MapMaker.ValueReference paramValueReference)
    {
      this.valueReference = paramValueReference;
    }

    public void valueReclaimed()
    {
      this.internals.removeEntry(this, null);
    }

    public .MapMaker.ReferenceEntry getNext()
    {
      return null;
    }

    public int getHash()
    {
      return this.hash;
    }
  }

  private static abstract interface ReferenceEntry
  {
    public abstract $MapMaker.ValueReference getValueReference();

    public abstract void setValueReference($MapMaker.ValueReference paramValueReference);

    public abstract void valueReclaimed();

    public abstract ReferenceEntry getNext();

    public abstract int getHash();

    public abstract Object getKey();
  }

  private static class QueueHolder
  {
    static final .FinalizableReferenceQueue queue = new $FinalizableReferenceQueue();
  }

  private static class ComputationExceptionReference
    implements .MapMaker.ValueReference
  {
    final Throwable t;

    ComputationExceptionReference(Throwable paramThrowable)
    {
      this.t = paramThrowable;
    }

    public Object get()
    {
      return null;
    }

    public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return this;
    }

    public Object waitForValue()
    {
      throw new $AsynchronousComputationException(this.t);
    }
  }

  private static class NullOutputExceptionReference
    implements .MapMaker.ValueReference
  {
    final String message;

    NullOutputExceptionReference(String paramString)
    {
      this.message = paramString;
    }

    public Object get()
    {
      return null;
    }

    public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return this;
    }

    public Object waitForValue()
    {
      throw new $NullOutputException(this.message);
    }
  }

  private static abstract interface ValueReference
  {
    public abstract Object get();

    public abstract ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry);

    public abstract Object waitForValue()
      throws InterruptedException;
  }

  private static class StrategyImpl
    implements .CustomConcurrentHashMap.ComputingStrategy, Serializable
  {
    final .MapMaker.Strength keyStrength;
    final .MapMaker.Strength valueStrength;
    final ConcurrentMap map;
    final long expirationNanos;
    $CustomConcurrentHashMap.Internals internals;
    private static final long serialVersionUID = 0L;

    StrategyImpl($MapMaker paramMapMaker)
    {
      this.keyStrength = paramMapMaker.keyStrength;
      this.valueStrength = paramMapMaker.valueStrength;
      this.expirationNanos = paramMapMaker.expirationNanos;
      this.map = paramMapMaker.builder.buildMap(this);
    }

    StrategyImpl($MapMaker paramMapMaker, .Function paramFunction)
    {
      this.keyStrength = paramMapMaker.keyStrength;
      this.valueStrength = paramMapMaker.valueStrength;
      this.expirationNanos = paramMapMaker.expirationNanos;
      this.map = paramMapMaker.builder.buildComputingMap(this, paramFunction);
    }

    public void setValue($MapMaker.ReferenceEntry paramReferenceEntry, Object paramObject)
    {
      setValueReference(paramReferenceEntry, this.valueStrength.referenceValue(paramReferenceEntry, paramObject));
      if (this.expirationNanos > 0L)
        scheduleRemoval(paramReferenceEntry.getKey(), paramObject);
    }

    void scheduleRemoval(Object paramObject1, Object paramObject2)
    {
      final WeakReference localWeakReference1 = new WeakReference(paramObject1);
      final WeakReference localWeakReference2 = new WeakReference(paramObject2);
      $ExpirationTimer.instance.schedule(new TimerTask()
      {
        public void run()
        {
          Object localObject = localWeakReference1.get();
          if (localObject != null)
            $MapMaker.StrategyImpl.this.map.remove(localObject, localWeakReference2.get());
        }
      }
      , TimeUnit.NANOSECONDS.toMillis(this.expirationNanos));
    }

    public boolean equalKeys(Object paramObject1, Object paramObject2)
    {
      return this.keyStrength.equal(paramObject1, paramObject2);
    }

    public boolean equalValues(Object paramObject1, Object paramObject2)
    {
      return this.valueStrength.equal(paramObject1, paramObject2);
    }

    public int hashKey(Object paramObject)
    {
      return this.keyStrength.hash(paramObject);
    }

    public Object getKey($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return paramReferenceEntry.getKey();
    }

    public int getHash($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return paramReferenceEntry.getHash();
    }

    public .MapMaker.ReferenceEntry newEntry(Object paramObject, int paramInt, .MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return this.keyStrength.newEntry(this.internals, paramObject, paramInt, paramReferenceEntry);
    }

    public .MapMaker.ReferenceEntry copyEntry(Object paramObject, .MapMaker.ReferenceEntry paramReferenceEntry1, .MapMaker.ReferenceEntry paramReferenceEntry2)
    {
      $MapMaker.ValueReference localValueReference = paramReferenceEntry1.getValueReference();
      if (localValueReference == $MapMaker.COMPUTING)
      {
        localReferenceEntry = newEntry(paramObject, paramReferenceEntry1.getHash(), paramReferenceEntry2);
        localReferenceEntry.setValueReference(new FutureValueReference(paramReferenceEntry1, localReferenceEntry));
        return localReferenceEntry;
      }
      $MapMaker.ReferenceEntry localReferenceEntry = newEntry(paramObject, paramReferenceEntry1.getHash(), paramReferenceEntry2);
      localReferenceEntry.setValueReference(localValueReference.copyFor(localReferenceEntry));
      return localReferenceEntry;
    }

    public Object waitForValue($MapMaker.ReferenceEntry paramReferenceEntry)
      throws InterruptedException
    {
      $MapMaker.ValueReference localValueReference = paramReferenceEntry.getValueReference();
      if (localValueReference == $MapMaker.COMPUTING)
        synchronized (paramReferenceEntry)
        {
          while ((localValueReference = paramReferenceEntry.getValueReference()) == $MapMaker.COMPUTING)
            paramReferenceEntry.wait();
        }
      return localValueReference.waitForValue();
    }

    public Object getValue($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      $MapMaker.ValueReference localValueReference = paramReferenceEntry.getValueReference();
      return localValueReference.get();
    }

    public Object compute(Object paramObject, .MapMaker.ReferenceEntry paramReferenceEntry, .Function paramFunction)
    {
      Object localObject;
      try
      {
        localObject = paramFunction.apply(paramObject);
      }
      catch (Throwable localThrowable)
      {
        setValueReference(paramReferenceEntry, new $MapMaker.ComputationExceptionReference(localThrowable));
        throw new $ComputationException(localThrowable);
      }
      if (localObject == null)
      {
        String str = paramFunction + " returned null for key " + paramObject + ".";
        setValueReference(paramReferenceEntry, new $MapMaker.NullOutputExceptionReference(str));
        throw new $NullOutputException(str);
      }
      setValue(paramReferenceEntry, localObject);
      return localObject;
    }

    void setValueReference($MapMaker.ReferenceEntry paramReferenceEntry, .MapMaker.ValueReference paramValueReference)
    {
      int i = paramReferenceEntry.getValueReference() == $MapMaker.COMPUTING ? 1 : 0;
      paramReferenceEntry.setValueReference(paramValueReference);
      if (i != 0)
        synchronized (paramReferenceEntry)
        {
          paramReferenceEntry.notifyAll();
        }
    }

    public .MapMaker.ReferenceEntry getNext($MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return paramReferenceEntry.getNext();
    }

    public void setInternals($CustomConcurrentHashMap.Internals paramInternals)
    {
      this.internals = paramInternals;
    }

    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeObject(this.keyStrength);
      paramObjectOutputStream.writeObject(this.valueStrength);
      paramObjectOutputStream.writeLong(this.expirationNanos);
      paramObjectOutputStream.writeObject(this.internals);
      paramObjectOutputStream.writeObject(this.map);
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      try
      {
        Fields.keyStrength.set(this, paramObjectInputStream.readObject());
        Fields.valueStrength.set(this, paramObjectInputStream.readObject());
        Fields.expirationNanos.set(this, Long.valueOf(paramObjectInputStream.readLong()));
        Fields.internals.set(this, paramObjectInputStream.readObject());
        Fields.map.set(this, paramObjectInputStream.readObject());
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
    }

    private static class Fields
    {
      static final Field keyStrength = findField("keyStrength");
      static final Field valueStrength = findField("valueStrength");
      static final Field expirationNanos = findField("expirationNanos");
      static final Field internals = findField("internals");
      static final Field map = findField("map");

      static Field findField(String paramString)
      {
        try
        {
          Field localField = $MapMaker.StrategyImpl.class.getDeclaredField(paramString);
          localField.setAccessible(true);
          return localField;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          throw new AssertionError(localNoSuchFieldException);
        }
      }
    }

    private class FutureValueReference
      implements .MapMaker.ValueReference
    {
      final .MapMaker.ReferenceEntry original;
      final .MapMaker.ReferenceEntry newEntry;

      FutureValueReference($MapMaker.ReferenceEntry paramReferenceEntry1, .MapMaker.ReferenceEntry arg3)
      {
        this.original = paramReferenceEntry1;
        Object localObject;
        this.newEntry = localObject;
      }

      public Object get()
      {
        int i = 0;
        try
        {
          Object localObject1 = this.original.getValueReference().get();
          i = 1;
          Object localObject2 = localObject1;
          return localObject2;
        }
        finally
        {
          if (i == 0)
            removeEntry();
        }
      }

      public .MapMaker.ValueReference copyFor($MapMaker.ReferenceEntry paramReferenceEntry)
      {
        return new FutureValueReference($MapMaker.StrategyImpl.this, this.original, paramReferenceEntry);
      }

      public Object waitForValue()
        throws InterruptedException
      {
        int i = 0;
        try
        {
          Object localObject1 = $MapMaker.StrategyImpl.this.waitForValue(this.original);
          i = 1;
          Object localObject2 = localObject1;
          return localObject2;
        }
        finally
        {
          if (i == 0)
            removeEntry();
        }
      }

      void removeEntry()
      {
        $MapMaker.StrategyImpl.this.internals.removeEntry(this.newEntry);
      }
    }
  }

  private static abstract enum Strength
  {
    WEAK, SOFT, STRONG;

    abstract boolean equal(Object paramObject1, Object paramObject2);

    abstract int hash(Object paramObject);

    abstract $MapMaker.ValueReference referenceValue($MapMaker.ReferenceEntry paramReferenceEntry, Object paramObject);

    abstract $MapMaker.ReferenceEntry newEntry($CustomConcurrentHashMap.Internals paramInternals, Object paramObject, int paramInt, .MapMaker.ReferenceEntry paramReferenceEntry);

    abstract $MapMaker.ReferenceEntry copyEntry(Object paramObject, .MapMaker.ReferenceEntry paramReferenceEntry1, .MapMaker.ReferenceEntry paramReferenceEntry2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..MapMaker
 * JD-Core Version:    0.6.2
 */