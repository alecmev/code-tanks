package com.google.inject.internal;

final class Initializables
{
  static Initializable of(Object paramObject)
  {
    return new Initializable()
    {
      public Object get(Errors paramAnonymousErrors)
        throws ErrorsException
      {
        return this.val$instance;
      }

      public String toString()
      {
        return String.valueOf(this.val$instance);
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.Initializables
 * JD-Core Version:    0.6.2
 */