package com.google.common.hash;

import com.google.common.annotations.Beta;

@Beta
public final class Funnels
{
  public static Funnel byteArrayFunnel()
  {
    return ByteArrayFunnel.INSTANCE;
  }

  public static Funnel stringFunnel()
  {
    return StringFunnel.INSTANCE;
  }

  private static enum StringFunnel
    implements Funnel
  {
    INSTANCE;

    public void funnel(CharSequence paramCharSequence, PrimitiveSink paramPrimitiveSink)
    {
      paramPrimitiveSink.putString(paramCharSequence);
    }

    public String toString()
    {
      return "Funnels.stringFunnel()";
    }
  }

  private static enum ByteArrayFunnel
    implements Funnel
  {
    INSTANCE;

    public void funnel(byte[] paramArrayOfByte, PrimitiveSink paramPrimitiveSink)
    {
      paramPrimitiveSink.putBytes(paramArrayOfByte);
    }

    public String toString()
    {
      return "Funnels.byteArrayFunnel()";
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.Funnels
 * JD-Core Version:    0.6.2
 */