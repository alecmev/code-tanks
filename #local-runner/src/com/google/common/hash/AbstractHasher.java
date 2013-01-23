package com.google.common.hash;

import com.google.common.base.Charsets;
import java.nio.charset.Charset;

abstract class AbstractHasher
  implements Hasher
{
  public final Hasher putBoolean(boolean paramBoolean)
  {
    return putByte((byte)(paramBoolean ? 1 : 0));
  }

  public final Hasher putDouble(double paramDouble)
  {
    return putLong(Double.doubleToRawLongBits(paramDouble));
  }

  public final Hasher putFloat(float paramFloat)
  {
    return putInt(Float.floatToRawIntBits(paramFloat));
  }

  public Hasher putString(CharSequence paramCharSequence)
  {
    return putString(paramCharSequence, Charsets.UTF_16LE);
  }

  public Hasher putString(CharSequence paramCharSequence, Charset paramCharset)
  {
    return putBytes(paramCharSequence.toString().getBytes(paramCharset));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.AbstractHasher
 * JD-Core Version:    0.6.2
 */