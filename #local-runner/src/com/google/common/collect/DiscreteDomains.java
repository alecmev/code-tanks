package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.math.BigInteger;

@GwtCompatible
@Beta
public final class DiscreteDomains
{
  public static DiscreteDomain integers()
  {
    return IntegerDomain.INSTANCE;
  }

  public static DiscreteDomain longs()
  {
    return LongDomain.INSTANCE;
  }

  static DiscreteDomain bigIntegers()
  {
    return BigIntegerDomain.INSTANCE;
  }

  private static final class BigIntegerDomain extends DiscreteDomain
    implements Serializable
  {
    private static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
    private static final BigInteger MIN_LONG = BigInteger.valueOf(-9223372036854775808L);
    private static final BigInteger MAX_LONG = BigInteger.valueOf(9223372036854775807L);
    private static final long serialVersionUID = 0L;

    public BigInteger next(BigInteger paramBigInteger)
    {
      return paramBigInteger.add(BigInteger.ONE);
    }

    public BigInteger previous(BigInteger paramBigInteger)
    {
      return paramBigInteger.subtract(BigInteger.ONE);
    }

    public long distance(BigInteger paramBigInteger1, BigInteger paramBigInteger2)
    {
      return paramBigInteger1.subtract(paramBigInteger2).max(MIN_LONG).min(MAX_LONG).longValue();
    }

    private Object readResolve()
    {
      return INSTANCE;
    }
  }

  private static final class LongDomain extends DiscreteDomain
    implements Serializable
  {
    private static final LongDomain INSTANCE = new LongDomain();
    private static final long serialVersionUID = 0L;

    public Long next(Long paramLong)
    {
      long l = paramLong.longValue();
      return l == 9223372036854775807L ? null : Long.valueOf(l + 1L);
    }

    public Long previous(Long paramLong)
    {
      long l = paramLong.longValue();
      return l == -9223372036854775808L ? null : Long.valueOf(l - 1L);
    }

    public long distance(Long paramLong1, Long paramLong2)
    {
      long l = paramLong2.longValue() - paramLong1.longValue();
      if ((paramLong2.longValue() > paramLong1.longValue()) && (l < 0L))
        return 9223372036854775807L;
      if ((paramLong2.longValue() < paramLong1.longValue()) && (l > 0L))
        return -9223372036854775808L;
      return l;
    }

    public Long minValue()
    {
      return Long.valueOf(-9223372036854775808L);
    }

    public Long maxValue()
    {
      return Long.valueOf(9223372036854775807L);
    }

    private Object readResolve()
    {
      return INSTANCE;
    }
  }

  private static final class IntegerDomain extends DiscreteDomain
    implements Serializable
  {
    private static final IntegerDomain INSTANCE = new IntegerDomain();
    private static final long serialVersionUID = 0L;

    public Integer next(Integer paramInteger)
    {
      int i = paramInteger.intValue();
      return i == 2147483647 ? null : Integer.valueOf(i + 1);
    }

    public Integer previous(Integer paramInteger)
    {
      int i = paramInteger.intValue();
      return i == -2147483648 ? null : Integer.valueOf(i - 1);
    }

    public long distance(Integer paramInteger1, Integer paramInteger2)
    {
      return paramInteger2.intValue() - paramInteger1.intValue();
    }

    public Integer minValue()
    {
      return Integer.valueOf(-2147483648);
    }

    public Integer maxValue()
    {
      return Integer.valueOf(2147483647);
    }

    private Object readResolve()
    {
      return INSTANCE;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.DiscreteDomains
 * JD-Core Version:    0.6.2
 */