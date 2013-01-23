package org.apache.http.conn.scheme;

import java.util.Locale;
import org.apache.http.util.LangUtils;

public final class Scheme
{
  private final String name;
  private final SchemeSocketFactory socketFactory;
  private final int defaultPort;
  private final boolean layered;
  private String stringRep;

  public Scheme(String paramString, int paramInt, SchemeSocketFactory paramSchemeSocketFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Scheme name may not be null");
    if ((paramInt <= 0) || (paramInt > 65535))
      throw new IllegalArgumentException("Port is invalid: " + paramInt);
    if (paramSchemeSocketFactory == null)
      throw new IllegalArgumentException("Socket factory may not be null");
    this.name = paramString.toLowerCase(Locale.ENGLISH);
    this.defaultPort = paramInt;
    if ((paramSchemeSocketFactory instanceof SchemeLayeredSocketFactory))
    {
      this.layered = true;
      this.socketFactory = paramSchemeSocketFactory;
    }
    else if ((paramSchemeSocketFactory instanceof LayeredSchemeSocketFactory))
    {
      this.layered = true;
      this.socketFactory = new SchemeLayeredSocketFactoryAdaptor2((LayeredSchemeSocketFactory)paramSchemeSocketFactory);
    }
    else
    {
      this.layered = false;
      this.socketFactory = paramSchemeSocketFactory;
    }
  }

  public final int getDefaultPort()
  {
    return this.defaultPort;
  }

  public final SchemeSocketFactory getSchemeSocketFactory()
  {
    return this.socketFactory;
  }

  public final String getName()
  {
    return this.name;
  }

  public final boolean isLayered()
  {
    return this.layered;
  }

  public final int resolvePort(int paramInt)
  {
    return paramInt <= 0 ? this.defaultPort : paramInt;
  }

  public final String toString()
  {
    if (this.stringRep == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.name);
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(this.defaultPort));
      this.stringRep = localStringBuilder.toString();
    }
    return this.stringRep;
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof Scheme))
    {
      Scheme localScheme = (Scheme)paramObject;
      return (this.name.equals(localScheme.name)) && (this.defaultPort == localScheme.defaultPort) && (this.layered == localScheme.layered);
    }
    return false;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.defaultPort);
    i = LangUtils.hashCode(i, this.name);
    i = LangUtils.hashCode(i, this.layered);
    return i;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.scheme.Scheme
 * JD-Core Version:    0.6.2
 */