package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@Beta
public final class Futures
{
  private static final Ordering WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function()
  {
    public Boolean apply(Constructor paramAnonymousConstructor)
    {
      return Boolean.valueOf(Arrays.asList(paramAnonymousConstructor.getParameterTypes()).contains(String.class));
    }
  }).reverse();

  public static CheckedFuture makeChecked(ListenableFuture paramListenableFuture, Function paramFunction)
  {
    return new MappingCheckedFuture((ListenableFuture)Preconditions.checkNotNull(paramListenableFuture), paramFunction);
  }

  public static ListenableFuture immediateFuture(Object paramObject)
  {
    SettableFuture localSettableFuture = SettableFuture.create();
    localSettableFuture.set(paramObject);
    return localSettableFuture;
  }

  public static CheckedFuture immediateCheckedFuture(Object paramObject)
  {
    SettableFuture localSettableFuture = SettableFuture.create();
    localSettableFuture.set(paramObject);
    return makeChecked(localSettableFuture, new Function()
    {
      public Exception apply(Exception paramAnonymousException)
      {
        throw new AssertionError("impossible");
      }
    });
  }

  public static ListenableFuture immediateFailedFuture(Throwable paramThrowable)
  {
    Preconditions.checkNotNull(paramThrowable);
    SettableFuture localSettableFuture = SettableFuture.create();
    localSettableFuture.setException(paramThrowable);
    return localSettableFuture;
  }

  public static CheckedFuture immediateFailedCheckedFuture(Exception paramException)
  {
    Preconditions.checkNotNull(paramException);
    return makeChecked(immediateFailedFuture(paramException), new Function()
    {
      public Exception apply(Exception paramAnonymousException)
      {
        return this.val$exception;
      }
    });
  }

  public static ListenableFuture transform(ListenableFuture paramListenableFuture, AsyncFunction paramAsyncFunction)
  {
    return transform(paramListenableFuture, paramAsyncFunction, MoreExecutors.sameThreadExecutor());
  }

  public static ListenableFuture transform(ListenableFuture paramListenableFuture, AsyncFunction paramAsyncFunction, Executor paramExecutor)
  {
    ChainingListenableFuture localChainingListenableFuture = new ChainingListenableFuture(paramAsyncFunction, paramListenableFuture, null);
    paramListenableFuture.addListener(localChainingListenableFuture, paramExecutor);
    return localChainingListenableFuture;
  }

  public static ListenableFuture transform(ListenableFuture paramListenableFuture, Function paramFunction)
  {
    return transform(paramListenableFuture, paramFunction, MoreExecutors.sameThreadExecutor());
  }

  public static ListenableFuture transform(ListenableFuture paramListenableFuture, Function paramFunction, Executor paramExecutor)
  {
    Preconditions.checkNotNull(paramFunction);
    AsyncFunction local3 = new AsyncFunction()
    {
      public ListenableFuture apply(Object paramAnonymousObject)
      {
        Object localObject = this.val$function.apply(paramAnonymousObject);
        return Futures.immediateFuture(localObject);
      }
    };
    return transform(paramListenableFuture, local3, paramExecutor);
  }

  @Beta
  public static Future lazyTransform(Future paramFuture, final Function paramFunction)
  {
    Preconditions.checkNotNull(paramFuture);
    Preconditions.checkNotNull(paramFunction);
    return new Future()
    {
      public boolean cancel(boolean paramAnonymousBoolean)
      {
        return this.val$input.cancel(paramAnonymousBoolean);
      }

      public boolean isCancelled()
      {
        return this.val$input.isCancelled();
      }

      public boolean isDone()
      {
        return this.val$input.isDone();
      }

      public Object get()
        throws InterruptedException, ExecutionException
      {
        return applyTransformation(this.val$input.get());
      }

      public Object get(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ExecutionException, TimeoutException
      {
        return applyTransformation(this.val$input.get(paramAnonymousLong, paramAnonymousTimeUnit));
      }

      private Object applyTransformation(Object paramAnonymousObject)
        throws ExecutionException
      {
        try
        {
          return paramFunction.apply(paramAnonymousObject);
        }
        catch (Throwable localThrowable)
        {
          throw new ExecutionException(localThrowable);
        }
      }
    };
  }

  @Beta
  public static ListenableFuture allAsList(ListenableFuture[] paramArrayOfListenableFuture)
  {
    return new ListFuture(ImmutableList.copyOf(paramArrayOfListenableFuture), true, MoreExecutors.sameThreadExecutor());
  }

  @Beta
  public static ListenableFuture allAsList(Iterable paramIterable)
  {
    return new ListFuture(ImmutableList.copyOf(paramIterable), true, MoreExecutors.sameThreadExecutor());
  }

  @Beta
  public static ListenableFuture successfulAsList(ListenableFuture[] paramArrayOfListenableFuture)
  {
    return new ListFuture(ImmutableList.copyOf(paramArrayOfListenableFuture), false, MoreExecutors.sameThreadExecutor());
  }

  @Beta
  public static ListenableFuture successfulAsList(Iterable paramIterable)
  {
    return new ListFuture(ImmutableList.copyOf(paramIterable), false, MoreExecutors.sameThreadExecutor());
  }

  public static void addCallback(ListenableFuture paramListenableFuture, FutureCallback paramFutureCallback)
  {
    addCallback(paramListenableFuture, paramFutureCallback, MoreExecutors.sameThreadExecutor());
  }

  public static void addCallback(ListenableFuture paramListenableFuture, final FutureCallback paramFutureCallback, Executor paramExecutor)
  {
    Preconditions.checkNotNull(paramFutureCallback);
    Runnable local5 = new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject = Uninterruptibles.getUninterruptibly(this.val$future);
          paramFutureCallback.onSuccess(localObject);
        }
        catch (ExecutionException localExecutionException)
        {
          paramFutureCallback.onFailure(localExecutionException.getCause());
        }
        catch (RuntimeException localRuntimeException)
        {
          paramFutureCallback.onFailure(localRuntimeException);
        }
        catch (Error localError)
        {
          paramFutureCallback.onFailure(localError);
        }
      }
    };
    paramListenableFuture.addListener(local5, paramExecutor);
  }

  @Beta
  public static Object get(Future paramFuture, Class paramClass)
    throws Exception
  {
    Preconditions.checkNotNull(paramFuture);
    Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(paramClass), "Futures.get exception type (%s) must not be a RuntimeException", new Object[] { paramClass });
    try
    {
      return paramFuture.get();
    }
    catch (InterruptedException localInterruptedException)
    {
      Thread.currentThread().interrupt();
      throw newWithCause(paramClass, localInterruptedException);
    }
    catch (ExecutionException localExecutionException)
    {
      wrapAndThrowExceptionOrError(localExecutionException.getCause(), paramClass);
    }
    throw new AssertionError();
  }

  @Beta
  public static Object get(Future paramFuture, long paramLong, TimeUnit paramTimeUnit, Class paramClass)
    throws Exception
  {
    Preconditions.checkNotNull(paramFuture);
    Preconditions.checkNotNull(paramTimeUnit);
    Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(paramClass), "Futures.get exception type (%s) must not be a RuntimeException", new Object[] { paramClass });
    try
    {
      return paramFuture.get(paramLong, paramTimeUnit);
    }
    catch (InterruptedException localInterruptedException)
    {
      Thread.currentThread().interrupt();
      throw newWithCause(paramClass, localInterruptedException);
    }
    catch (TimeoutException localTimeoutException)
    {
      throw newWithCause(paramClass, localTimeoutException);
    }
    catch (ExecutionException localExecutionException)
    {
      wrapAndThrowExceptionOrError(localExecutionException.getCause(), paramClass);
    }
    throw new AssertionError();
  }

  private static void wrapAndThrowExceptionOrError(Throwable paramThrowable, Class paramClass)
    throws Exception
  {
    if ((paramThrowable instanceof Error))
      throw new ExecutionError((Error)paramThrowable);
    if ((paramThrowable instanceof RuntimeException))
      throw new UncheckedExecutionException(paramThrowable);
    throw newWithCause(paramClass, paramThrowable);
  }

  @Beta
  public static Object getUnchecked(Future paramFuture)
  {
    Preconditions.checkNotNull(paramFuture);
    try
    {
      return Uninterruptibles.getUninterruptibly(paramFuture);
    }
    catch (ExecutionException localExecutionException)
    {
      wrapAndThrowUnchecked(localExecutionException.getCause());
    }
    throw new AssertionError();
  }

  private static void wrapAndThrowUnchecked(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof Error))
      throw new ExecutionError((Error)paramThrowable);
    throw new UncheckedExecutionException(paramThrowable);
  }

  private static Exception newWithCause(Class paramClass, Throwable paramThrowable)
  {
    List localList = Arrays.asList(paramClass.getConstructors());
    Iterator localIterator = preferringStrings(localList).iterator();
    while (localIterator.hasNext())
    {
      Constructor localConstructor = (Constructor)localIterator.next();
      Exception localException = (Exception)newFromConstructor(localConstructor, paramThrowable);
      if (localException != null)
      {
        if (localException.getCause() == null)
          localException.initCause(paramThrowable);
        return localException;
      }
    }
    throw new IllegalArgumentException("No appropriate constructor for exception of type " + paramClass + " in response to chained exception", paramThrowable);
  }

  private static List preferringStrings(List paramList)
  {
    return WITH_STRING_PARAM_FIRST.sortedCopy(paramList);
  }

  private static Object newFromConstructor(Constructor paramConstructor, Throwable paramThrowable)
  {
    Class[] arrayOfClass = paramConstructor.getParameterTypes();
    Object[] arrayOfObject = new Object[arrayOfClass.length];
    for (int i = 0; i < arrayOfClass.length; i++)
    {
      Class localClass = arrayOfClass[i];
      if (localClass.equals(String.class))
        arrayOfObject[i] = paramThrowable.toString();
      else if (localClass.equals(Throwable.class))
        arrayOfObject[i] = paramThrowable;
      else
        return null;
    }
    try
    {
      return paramConstructor.newInstance(arrayOfObject);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      return null;
    }
    catch (InstantiationException localInstantiationException)
    {
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    return null;
  }

  private static class MappingCheckedFuture extends AbstractCheckedFuture
  {
    final Function mapper;

    MappingCheckedFuture(ListenableFuture paramListenableFuture, Function paramFunction)
    {
      super();
      this.mapper = ((Function)Preconditions.checkNotNull(paramFunction));
    }

    protected Exception mapException(Exception paramException)
    {
      return (Exception)this.mapper.apply(paramException);
    }
  }

  private static class ListFuture extends AbstractFuture
  {
    ImmutableList futures;
    final boolean allMustSucceed;
    final AtomicInteger remaining;
    List values;

    ListFuture(ImmutableList paramImmutableList, boolean paramBoolean, Executor paramExecutor)
    {
      this.futures = paramImmutableList;
      this.values = Lists.newArrayListWithCapacity(paramImmutableList.size());
      this.allMustSucceed = paramBoolean;
      this.remaining = new AtomicInteger(paramImmutableList.size());
      init(paramExecutor);
    }

    private void init(Executor paramExecutor)
    {
      addListener(new Runnable()
      {
        public void run()
        {
          Futures.ListFuture.this.values = null;
          Futures.ListFuture.this.futures = null;
        }
      }
      , MoreExecutors.sameThreadExecutor());
      if (this.futures.isEmpty())
      {
        set(Lists.newArrayList(this.values));
        return;
      }
      for (int i = 0; i < this.futures.size(); i++)
        this.values.add(null);
      ImmutableList localImmutableList = this.futures;
      for (int j = 0; j < localImmutableList.size(); j++)
      {
        final ListenableFuture localListenableFuture = (ListenableFuture)localImmutableList.get(j);
        final int k = j;
        localListenableFuture.addListener(new Runnable()
        {
          public void run()
          {
            Futures.ListFuture.this.setOneValue(k, localListenableFuture);
          }
        }
        , paramExecutor);
      }
    }

    private void setOneValue(int paramInt, Future paramFuture)
    {
      List localList = this.values;
      if ((isDone()) || (localList == null))
      {
        Preconditions.checkState(this.allMustSucceed, "Future was done before all dependencies completed");
        return;
      }
      try
      {
        Preconditions.checkState(paramFuture.isDone(), "Tried to set value from future which is not done");
        localList.set(paramInt, Uninterruptibles.getUninterruptibly(paramFuture));
      }
      catch (CancellationException localCancellationException)
      {
        int i;
        if (this.allMustSucceed)
          cancel(false);
      }
      catch (ExecutionException localExecutionException)
      {
        int j;
        if (this.allMustSucceed)
          setException(localExecutionException.getCause());
      }
      catch (RuntimeException localRuntimeException)
      {
        int k;
        if (this.allMustSucceed)
          setException(localRuntimeException);
      }
      catch (Error localError)
      {
        int m;
        setException(localError);
      }
      finally
      {
        int n;
        int i1 = this.remaining.decrementAndGet();
        Preconditions.checkState(i1 >= 0, "Less than 0 remaining futures");
        if (i1 == 0)
        {
          localList = this.values;
          if (localList != null)
            set(Lists.newArrayList(localList));
          else
            Preconditions.checkState(isDone());
        }
      }
    }
  }

  private static class ChainingListenableFuture extends AbstractFuture
    implements Runnable
  {
    private AsyncFunction function;
    private ListenableFuture inputFuture;
    private volatile ListenableFuture outputFuture;
    private final BlockingQueue mayInterruptIfRunningChannel = new LinkedBlockingQueue(1);
    private final CountDownLatch outputCreated = new CountDownLatch(1);

    private ChainingListenableFuture(AsyncFunction paramAsyncFunction, ListenableFuture paramListenableFuture)
    {
      this.function = ((AsyncFunction)Preconditions.checkNotNull(paramAsyncFunction));
      this.inputFuture = ((ListenableFuture)Preconditions.checkNotNull(paramListenableFuture));
    }

    public boolean cancel(boolean paramBoolean)
    {
      if (super.cancel(paramBoolean))
      {
        Uninterruptibles.putUninterruptibly(this.mayInterruptIfRunningChannel, Boolean.valueOf(paramBoolean));
        cancel(this.inputFuture, paramBoolean);
        cancel(this.outputFuture, paramBoolean);
        return true;
      }
      return false;
    }

    private void cancel(Future paramFuture, boolean paramBoolean)
    {
      if (paramFuture != null)
        paramFuture.cancel(paramBoolean);
    }

    public void run()
    {
      try
      {
        Object localObject1;
        final ListenableFuture localListenableFuture;
        return;
      }
      catch (ExecutionException localExecutionException)
      {
      }
      finally
      {
        this.function = null;
        this.inputFuture = null;
        this.outputCreated.countDown();
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.util.concurrent.Futures
 * JD-Core Version:    0.6.2
 */