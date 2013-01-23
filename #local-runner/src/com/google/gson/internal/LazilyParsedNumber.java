package com.google.gson.internal;

import java.math.BigInteger;

public final class LazilyParsedNumber extends Number
{
  private final String value;

  public LazilyParsedNumber(String paramString)
  {
    this.value = paramString;
  }

  public int intValue()
  {
    try
    {
      return Integer.parseInt(this.value);
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        return (int)Long.parseLong(this.value);
      }
      catch (NumberFormatException localNumberFormatException2)
      {
      }
    }
    return new BigInteger(this.value).intValue();
  }

  public long longValue()
  {
    try
    {
      return Long.parseLong(this.value);
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return new BigInteger(this.value).longValue();
  }

  public float floatValue()
  {
    return Float.parseFloat(this.value);
  }

  public double doubleValue()
  {
    return Double.parseDouble(this.value);
  }

  public String toString()
  {
    return this.value;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.internal.LazilyParsedNumber
 * JD-Core Version:    0.6.2
 */