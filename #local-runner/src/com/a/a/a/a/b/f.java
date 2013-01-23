package com.a.a.a.a.b;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class f
  implements ThreadFactory
{
  private final AtomicInteger b = new AtomicInteger();

  f(b paramb)
  {
  }

  public Thread newThread(Runnable paramRunnable)
  {
    Thread localThread = new Thread(paramRunnable);
    localThread.setDaemon(true);
    localThread.setName(b.class.getSimpleName() + "#StrategyThread-" + this.b.incrementAndGet());
    return localThread;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.b.f
 * JD-Core Version:    0.6.2
 */