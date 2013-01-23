package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;

@GwtCompatible
final class Absent extends Optional
{
  static final Absent INSTANCE = new Absent();
  private static final long serialVersionUID = 0L;

  public boolean isPresent()
  {
    return false;
  }

  public Object get()
  {
    throw new IllegalStateException("value is absent");
  }

  public Object or(Object paramObject)
  {
    return Preconditions.checkNotNull(paramObject, "use orNull() instead of or(null)");
  }

  public Optional or(Optional paramOptional)
  {
    return (Optional)Preconditions.checkNotNull(paramOptional);
  }

  public Object or(Supplier paramSupplier)
  {
    return Preconditions.checkNotNull(paramSupplier.get(), "use orNull() instead of a Supplier that returns null");
  }

  public Object orNull()
  {
    return null;
  }

  public Set asSet()
  {
    return Collections.emptySet();
  }

  public Optional transform(Function paramFunction)
  {
    Preconditions.checkNotNull(paramFunction);
    return Optional.absent();
  }

  public boolean equals(Object paramObject)
  {
    return paramObject == this;
  }

  public int hashCode()
  {
    return 1502476572;
  }

  public String toString()
  {
    return "Optional.absent()";
  }

  private Object readResolve()
  {
    return INSTANCE;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Absent
 * JD-Core Version:    0.6.2
 */