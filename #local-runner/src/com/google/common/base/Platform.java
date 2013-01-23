package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated=true)
final class Platform
{
  private static final ThreadLocal DEST_TL = new ThreadLocal()
  {
    protected char[] initialValue()
    {
      return new char[1024];
    }
  };

  static char[] charBufferFromThreadLocal()
  {
    return (char[])DEST_TL.get();
  }

  static long systemNanoTime()
  {
    return System.nanoTime();
  }

  static CharMatcher precomputeCharMatcher(CharMatcher paramCharMatcher)
  {
    return paramCharMatcher.precomputedInternal();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Platform
 * JD-Core Version:    0.6.2
 */