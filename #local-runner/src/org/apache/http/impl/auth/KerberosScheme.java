package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

public class KerberosScheme extends GGSSchemeBase
{
  public KerberosScheme(boolean paramBoolean)
  {
    super(paramBoolean);
  }

  public KerberosScheme()
  {
    super(false);
  }

  public String getSchemeName()
  {
    return "Kerberos";
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return super.authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
  }

  protected byte[] generateToken(byte[] paramArrayOfByte, String paramString)
    throws GSSException
  {
    return generateGSSToken(paramArrayOfByte, new Oid("1.2.840.113554.1.2.2"), paramString);
  }

  public String getRealm()
  {
    return null;
  }

  public boolean isConnectionBased()
  {
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.KerberosScheme
 * JD-Core Version:    0.6.2
 */