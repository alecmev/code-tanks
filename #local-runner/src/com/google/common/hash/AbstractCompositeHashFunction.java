package com.google.common.hash;

import java.nio.charset.Charset;

abstract class AbstractCompositeHashFunction extends AbstractStreamingHashFunction
{
  final HashFunction[] functions;
  private static final long serialVersionUID = 0L;

  AbstractCompositeHashFunction(HashFunction[] paramArrayOfHashFunction)
  {
    this.functions = paramArrayOfHashFunction;
  }

  abstract HashCode makeHash(Hasher[] paramArrayOfHasher);

  public Hasher newHasher()
  {
    final Hasher[] arrayOfHasher = new Hasher[this.functions.length];
    for (int i = 0; i < arrayOfHasher.length; i++)
      arrayOfHasher[i] = this.functions[i].newHasher();
    return new Hasher()
    {
      public Hasher putByte(byte paramAnonymousByte)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putByte(paramAnonymousByte);
        return this;
      }

      public Hasher putBytes(byte[] paramAnonymousArrayOfByte)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putBytes(paramAnonymousArrayOfByte);
        return this;
      }

      public Hasher putBytes(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putBytes(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        return this;
      }

      public Hasher putShort(short paramAnonymousShort)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putShort(paramAnonymousShort);
        return this;
      }

      public Hasher putInt(int paramAnonymousInt)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putInt(paramAnonymousInt);
        return this;
      }

      public Hasher putLong(long paramAnonymousLong)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putLong(paramAnonymousLong);
        return this;
      }

      public Hasher putFloat(float paramAnonymousFloat)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putFloat(paramAnonymousFloat);
        return this;
      }

      public Hasher putDouble(double paramAnonymousDouble)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putDouble(paramAnonymousDouble);
        return this;
      }

      public Hasher putBoolean(boolean paramAnonymousBoolean)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putBoolean(paramAnonymousBoolean);
        return this;
      }

      public Hasher putChar(char paramAnonymousChar)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putChar(paramAnonymousChar);
        return this;
      }

      public Hasher putString(CharSequence paramAnonymousCharSequence)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putString(paramAnonymousCharSequence);
        return this;
      }

      public Hasher putString(CharSequence paramAnonymousCharSequence, Charset paramAnonymousCharset)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putString(paramAnonymousCharSequence, paramAnonymousCharset);
        return this;
      }

      public Hasher putObject(Object paramAnonymousObject, Funnel paramAnonymousFunnel)
      {
        for (Hasher localHasher : arrayOfHasher)
          localHasher.putObject(paramAnonymousObject, paramAnonymousFunnel);
        return this;
      }

      public HashCode hash()
      {
        return AbstractCompositeHashFunction.this.makeHash(arrayOfHasher);
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.hash.AbstractCompositeHashFunction
 * JD-Core Version:    0.6.2
 */