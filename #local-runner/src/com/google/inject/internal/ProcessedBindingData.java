package com.google.inject.internal;

import com.google.inject.internal.util..Lists;
import java.util.Iterator;
import java.util.List;

class ProcessedBindingData
{
  private final List creationListeners = $Lists.newArrayList();
  private final List uninitializedBindings = $Lists.newArrayList();

  void addCreationListener(CreationListener paramCreationListener)
  {
    this.creationListeners.add(paramCreationListener);
  }

  void addUninitializedBinding(Runnable paramRunnable)
  {
    this.uninitializedBindings.add(paramRunnable);
  }

  void initializeBindings()
  {
    Iterator localIterator = this.uninitializedBindings.iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      localRunnable.run();
    }
  }

  void runCreationListeners(Errors paramErrors)
  {
    Iterator localIterator = this.creationListeners.iterator();
    while (localIterator.hasNext())
    {
      CreationListener localCreationListener = (CreationListener)localIterator.next();
      localCreationListener.notify(paramErrors);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.ProcessedBindingData
 * JD-Core Version:    0.6.2
 */