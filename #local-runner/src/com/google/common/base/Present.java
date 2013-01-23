package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;

@GwtCompatible
final class Present extends Optional
{
  private final Object reference;
  private static final long serialVersionUID = 0L;

  Present(Object paramObject)
  {
    this.reference = paramObject;
  }

  public boolean isPresent()
  {
    return true;
  }

  public Object get()
  {
    return this.reference;
  }

  public Object or(Object paramObject)
  {
    Preconditions.checkNotNull(paramObject, "use orNull() instead of or(null)");
    return this.reference;
  }

  public Optional or(Optional paramOptional)
  {
    Preconditions.checkNotNull(paramOptional);
    return this;
  }

  public Object or(Supplier paramSupplier)
  {
    Preconditions.checkNotNull(paramSupplier);
    return this.reference;
  }

  public Object orNull()
  {
    return this.reference;
  }

  public Set asSet()
  {
    return Collections.singleton(this.reference);
  }

  public Optional transform(Function paramFunction)
  {
    return new Present(Preconditions.checkNotNull(paramFunction.apply(this.reference), "Transformation function cannot return null."));
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Present))
    {
      Present localPresent = (Present)paramObject;
      return this.reference.equals(localPresent.reference);
    }
    return false;
  }

  public int hashCode()
  {
    return 1502476572 + this.reference.hashCode();
  }

  public String toString()
  {
    return "Optional.of(" + this.reference + ")";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Present
 * JD-Core Version:    0.6.2
 */