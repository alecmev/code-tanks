package com.a.c.a.a;

import java.util.Random;

public class b
{
  private static final Random a = new Random(c());

  private b()
  {
    throw new UnsupportedOperationException();
  }

  private static long c()
  {
    return System.nanoTime() ^ Thread.currentThread().getId() + Runtime.getRuntime().maxMemory() * Runtime.getRuntime().freeMemory() & Runtime.getRuntime().totalMemory();
  }

  public static Random a()
  {
    return a;
  }

  public static void a(long paramLong)
  {
    a.setSeed(paramLong);
  }

  public static int a(int paramInt)
  {
    return a.nextInt(paramInt);
  }

  public static double b()
  {
    return a.nextDouble();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.c.a.a.b
 * JD-Core Version:    0.6.2
 */