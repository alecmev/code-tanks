package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible(emulated=true)
public final class Stopwatch
{
  private final Ticker ticker;
  private boolean isRunning;
  private long elapsedNanos;
  private long startTick;

  public Stopwatch()
  {
    this(Ticker.systemTicker());
  }

  public Stopwatch(Ticker paramTicker)
  {
    this.ticker = ((Ticker)Preconditions.checkNotNull(paramTicker));
  }

  public boolean isRunning()
  {
    return this.isRunning;
  }

  public Stopwatch start()
  {
    Preconditions.checkState(!this.isRunning);
    this.isRunning = true;
    this.startTick = this.ticker.read();
    return this;
  }

  public Stopwatch stop()
  {
    long l = this.ticker.read();
    Preconditions.checkState(this.isRunning);
    this.isRunning = false;
    this.elapsedNanos += l - this.startTick;
    return this;
  }

  public Stopwatch reset()
  {
    this.elapsedNanos = 0L;
    this.isRunning = false;
    return this;
  }

  private long elapsedNanos()
  {
    return this.isRunning ? this.ticker.read() - this.startTick + this.elapsedNanos : this.elapsedNanos;
  }

  public long elapsedTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(elapsedNanos(), TimeUnit.NANOSECONDS);
  }

  public long elapsedMillis()
  {
    return elapsedTime(TimeUnit.MILLISECONDS);
  }

  @GwtIncompatible("String.format()")
  public String toString()
  {
    return toString(4);
  }

  @GwtIncompatible("String.format()")
  public String toString(int paramInt)
  {
    long l = elapsedNanos();
    TimeUnit localTimeUnit = chooseUnit(l);
    double d = l / TimeUnit.NANOSECONDS.convert(1L, localTimeUnit);
    return String.format("%." + paramInt + "g %s", new Object[] { Double.valueOf(d), abbreviate(localTimeUnit) });
  }

  private static TimeUnit chooseUnit(long paramLong)
  {
    if (TimeUnit.SECONDS.convert(paramLong, TimeUnit.NANOSECONDS) > 0L)
      return TimeUnit.SECONDS;
    if (TimeUnit.MILLISECONDS.convert(paramLong, TimeUnit.NANOSECONDS) > 0L)
      return TimeUnit.MILLISECONDS;
    if (TimeUnit.MICROSECONDS.convert(paramLong, TimeUnit.NANOSECONDS) > 0L)
      return TimeUnit.MICROSECONDS;
    return TimeUnit.NANOSECONDS;
  }

  private static String abbreviate(TimeUnit paramTimeUnit)
  {
    switch (1.$SwitchMap$java$util$concurrent$TimeUnit[paramTimeUnit.ordinal()])
    {
    case 1:
      return "ns";
    case 2:
      return "μs";
    case 3:
      return "ms";
    case 4:
      return "s";
    }
    throw new AssertionError();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Stopwatch
 * JD-Core Version:    0.6.2
 */