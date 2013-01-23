package com.google.inject.internal;

import com.google.inject.internal.util..Function;
import com.google.inject.internal.util..MapMaker;
import com.google.inject.internal.util..Nullable;
import java.util.Map;

public abstract class FailableCache
{
  private final Map delegate = new $MapMaker().makeComputingMap(new $Function()
  {
    public Object apply(@$Nullable Object paramAnonymousObject)
    {
      Errors localErrors = new Errors();
      Object localObject = null;
      try
      {
        localObject = FailableCache.this.create(paramAnonymousObject, localErrors);
      }
      catch (ErrorsException localErrorsException)
      {
        localErrors.merge(localErrorsException.getErrors());
      }
      return localErrors.hasErrors() ? localErrors : localObject;
    }
  });

  protected abstract Object create(Object paramObject, Errors paramErrors)
    throws ErrorsException;

  public Object get(Object paramObject, Errors paramErrors)
    throws ErrorsException
  {
    Object localObject1 = this.delegate.get(paramObject);
    if ((localObject1 instanceof Errors))
    {
      paramErrors.merge((Errors)localObject1);
      throw paramErrors.toException();
    }
    Object localObject2 = localObject1;
    return localObject2;
  }

  boolean remove(Object paramObject)
  {
    return this.delegate.remove(paramObject) != null;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.FailableCache
 * JD-Core Version:    0.6.2
 */