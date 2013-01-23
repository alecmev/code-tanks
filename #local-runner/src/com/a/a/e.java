package com.a.a;

import org.slf4j.Logger;

class e
  implements Thread.UncaughtExceptionHandler
{
  e(h paramh)
  {
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    h.a().error("Got unexpected exception in thread '" + paramThread + "'.", paramThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.e
 * JD-Core Version:    0.6.2
 */