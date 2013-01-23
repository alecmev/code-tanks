package com.google.inject.name;

import com.google.inject.internal.util..Preconditions;
import java.io.Serializable;

class NamedImpl
  implements Named, Serializable
{
  private final String value;
  private static final long serialVersionUID = 0L;

  public NamedImpl(String paramString)
  {
    this.value = ((String).Preconditions.checkNotNull(paramString, "name"));
  }

  public String value()
  {
    return this.value;
  }

  public int hashCode()
  {
    return 127 * "value".hashCode() ^ this.value.hashCode();
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Serializable))
      return false;
    Named localNamed = (Serializable)paramObject;
    return this.value.equals(localNamed.value());
  }

  public String toString()
  {
    return "@" + Serializable.class.getName() + "(value=" + this.value + ")";
  }

  public Class annotationType()
  {
    return Serializable.class;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.name.NamedImpl
 * JD-Core Version:    0.6.2
 */