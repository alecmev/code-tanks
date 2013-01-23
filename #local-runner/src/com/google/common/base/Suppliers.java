package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@GwtCompatible
public final class Suppliers
{
  public static Supplier compose(Function paramFunction, Supplier paramSupplier)
  {
    Preconditions.checkNotNull(paramFunction);
    Preconditions.checkNotNull(paramSupplier);
    return new SupplierComposition(paramFunction, paramSupplier);
  }

  public static Supplier memoize(Supplier paramSupplier)
  {
    return (paramSupplier instanceof MemoizingSupplier) ? paramSupplier : new MemoizingSupplier((Supplier)Preconditions.checkNotNull(paramSupplier));
  }

  public static Supplier memoizeWithExpiration(Supplier paramSupplier, long paramLong, TimeUnit paramTimeUnit)
  {
    return new ExpiringMemoizingSupplier(paramSupplier, paramLong, paramTimeUnit);
  }

  public static Supplier ofInstance(Object paramObject)
  {
    return new SupplierOfInstance(paramObject);
  }

  public static Supplier synchronizedSupplier(Supplier paramSupplier)
  {
    return new ThreadSafeSupplier((Supplier)Preconditions.checkNotNull(paramSupplier));
  }

  @Beta
  public static Function supplierFunction()
  {
    return SupplierFunction.INSTANCE;
  }

  private static enum SupplierFunction
    implements Function
  {
    INSTANCE;

    public Object apply(Supplier paramSupplier)
    {
      return paramSupplier.get();
    }
  }

  private static class ThreadSafeSupplier
    implements Supplier, Serializable
  {
    final Supplier delegate;
    private static final long serialVersionUID = 0L;

    ThreadSafeSupplier(Supplier paramSupplier)
    {
      this.delegate = paramSupplier;
    }

    public Object get()
    {
      synchronized (this.delegate)
      {
        return this.delegate.get();
      }
    }
  }

  private static class SupplierOfInstance
    implements Supplier, Serializable
  {
    final Object instance;
    private static final long serialVersionUID = 0L;

    SupplierOfInstance(Object paramObject)
    {
      this.instance = paramObject;
    }

    public Object get()
    {
      return this.instance;
    }
  }

  @VisibleForTesting
  static class ExpiringMemoizingSupplier
    implements Supplier, Serializable
  {
    final Supplier delegate;
    final long durationNanos;
    volatile transient Object value;
    volatile transient long expirationNanos;
    private static final long serialVersionUID = 0L;

    ExpiringMemoizingSupplier(Supplier paramSupplier, long paramLong, TimeUnit paramTimeUnit)
    {
      this.delegate = ((Serializable)Preconditions.checkNotNull(paramSupplier));
      this.durationNanos = paramTimeUnit.toNanos(paramLong);
      Preconditions.checkArgument(paramLong > 0L);
    }

    public Object get()
    {
      long l1 = this.expirationNanos;
      long l2 = Platform.systemNanoTime();
      if ((l1 == 0L) || (l2 - l1 >= 0L))
        synchronized (this)
        {
          if (l1 == this.expirationNanos)
          {
            Object localObject1 = this.delegate.get();
            this.value = localObject1;
            l1 = l2 + this.durationNanos;
            this.expirationNanos = (l1 == 0L ? 1L : l1);
            return localObject1;
          }
        }
      return this.value;
    }
  }

  @VisibleForTesting
  static class MemoizingSupplier
    implements Supplier, Serializable
  {
    final Supplier delegate;
    volatile transient boolean initialized;
    transient Object value;
    private static final long serialVersionUID = 0L;

    MemoizingSupplier(Supplier paramSupplier)
    {
      this.delegate = paramSupplier;
    }

    public Object get()
    {
      if (!this.initialized)
        synchronized (this)
        {
          if (!this.initialized)
          {
            Object localObject1 = this.delegate.get();
            this.value = localObject1;
            this.initialized = true;
            return localObject1;
          }
        }
      return this.value;
    }
  }

  private static class SupplierComposition
    implements Supplier, Serializable
  {
    final Function function;
    final Supplier supplier;
    private static final long serialVersionUID = 0L;

    SupplierComposition(Function paramFunction, Supplier paramSupplier)
    {
      this.function = paramFunction;
      this.supplier = paramSupplier;
    }

    public Object get()
    {
      return this.function.apply(this.supplier.get());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Suppliers
 * JD-Core Version:    0.6.2
 */