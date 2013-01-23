package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

public class SPNegoScheme extends GGSSchemeBase
{
  public SPNegoScheme(boolean paramBoolean)
  {
    super(paramBoolean);
  }

  public SPNegoScheme()
  {
    super(false);
  }

  public String getSchemeName()
  {
    return "Negotiate";
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return super.authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
  }

  protected byte[] generateToken(byte[] paramArrayOfByte, String paramString)
    throws GSSException
  {
    return generateGSSToken(paramArrayOfByte, new Oid("1.3.6.1.5.5.2"), paramString);
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
 * Qualified Name:     org.apache.http.impl.auth.SPNegoScheme
 * JD-Core Version:    0.6.2
 */