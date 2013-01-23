package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@Beta
@GwtCompatible
public final class Equivalences
{
  public static Equivalence equals()
  {
    return Equals.INSTANCE;
  }

  public static Equivalence identity()
  {
    return Identity.INSTANCE;
  }

  private static final class Identity extends Equivalence
    implements Serializable
  {
    static final Identity INSTANCE = new Identity();
    private static final long serialVersionUID = 1L;

    protected boolean doEquivalent(Object paramObject1, Object paramObject2)
    {
      return false;
    }

    protected int doHash(Object paramObject)
    {
      return System.identityHashCode(paramObject);
    }

    private Object readResolve()
    {
      return INSTANCE;
    }
  }

  private static final class Equals extends Equivalence
    implements Serializable
  {
    static final Equals INSTANCE = new Equals();
    private static final long serialVersionUID = 1L;

    protected boolean doEquivalent(Object paramObject1, Object paramObject2)
    {
      return paramObject1.equals(paramObject2);
    }

    public int doHash(Object paramObject)
    {
      return paramObject.hashCode();
    }

    private Object readResolve()
    {
      return INSTANCE;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Equivalences
 * JD-Core Version:    0.6.2
 */