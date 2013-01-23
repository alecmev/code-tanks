package org.apache.http;

import java.io.Serializable;

public class ProtocolVersion
  implements Serializable, Cloneable
{
  protected final String protocol;
  protected final int major;
  protected final int minor;

  public ProtocolVersion(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Protocol name must not be null.");
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Protocol major version number must not be negative.");
    if (paramInt2 < 0)
      throw new IllegalArgumentException("Protocol minor version number may not be negative");
    this.protocol = paramString;
    this.major = paramInt1;
    this.minor = paramInt2;
  }

  public final String getProtocol()
  {
    return this.protocol;
  }

  public final int getMajor()
  {
    return this.major;
  }

  public final int getMinor()
  {
    return this.minor;
  }

  public ProtocolVersion forVersion(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == this.major) && (paramInt2 == this.minor))
      return this;
    return new ProtocolVersion(this.protocol, paramInt1, paramInt2);
  }

  public final int hashCode()
  {
    return this.protocol.hashCode() ^ this.major * 100000 ^ this.minor;
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof ProtocolVersion))
      return false;
    ProtocolVersion localProtocolVersion = (ProtocolVersion)paramObject;
    return (this.protocol.equals(localProtocolVersion.protocol)) && (this.major == localProtocolVersion.major) && (this.minor == localProtocolVersion.minor);
  }

  public boolean isComparable(ProtocolVersion paramProtocolVersion)
  {
    return (paramProtocolVersion != null) && (this.protocol.equals(paramProtocolVersion.protocol));
  }

  public int compareToVersion(ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("Protocol version must not be null.");
    if (!this.protocol.equals(paramProtocolVersion.protocol))
      throw new IllegalArgumentException("Versions for different protocols cannot be compared. " + this + " " + paramProtocolVersion);
    int i = getMajor() - paramProtocolVersion.getMajor();
    if (i == 0)
      i = getMinor() - paramProtocolVersion.getMinor();
    return i;
  }

  public final boolean lessEquals(ProtocolVersion paramProtocolVersion)
  {
    return (isComparable(paramProtocolVersion)) && (compareToVersion(paramProtocolVersion) <= 0);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.protocol);
    localStringBuilder.append('/');
    localStringBuilder.append(Integer.toString(this.major));
    localStringBuilder.append('.');
    localStringBuilder.append(Integer.toString(this.minor));
    return localStringBuilder.toString();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.ProtocolVersion
 * JD-Core Version:    0.6.2
 */