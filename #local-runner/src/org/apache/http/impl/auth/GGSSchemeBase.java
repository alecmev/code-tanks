package org.apache.http.impl.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public abstract class GGSSchemeBase extends AuthSchemeBase
{
  private final Log log = LogFactory.getLog(getClass());
  private final boolean stripPort;
  private final Base64 base64codec = new Base64();
  private State state = State.UNINITIATED;
  private byte[] token;

  GGSSchemeBase(boolean paramBoolean)
  {
    this.stripPort = paramBoolean;
  }

  GGSSchemeBase()
  {
    this(false);
  }

  protected GSSManager getManager()
  {
    return GSSManager.getInstance();
  }

  protected byte[] generateGSSToken(byte[] paramArrayOfByte, Oid paramOid, String paramString)
    throws GSSException
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (arrayOfByte == null)
      arrayOfByte = new byte[0];
    GSSManager localGSSManager = getManager();
    GSSName localGSSName = localGSSManager.createName("HTTP@" + paramString, GSSName.NT_HOSTBASED_SERVICE);
    GSSContext localGSSContext = localGSSManager.createContext(localGSSName.canonicalize(paramOid), paramOid, null, 0);
    localGSSContext.requestMutualAuth(true);
    localGSSContext.requestCredDeleg(true);
    return localGSSContext.initSecContext(arrayOfByte, 0, arrayOfByte.length);
  }

  protected abstract byte[] generateToken(byte[] paramArrayOfByte, String paramString)
    throws GSSException;

  public boolean isComplete()
  {
    return (this.state == State.TOKEN_GENERATED) || (this.state == State.FAILED);
  }

  @Deprecated
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest, null);
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    switch (1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[this.state.ordinal()])
    {
    case 1:
      throw new AuthenticationException(getSchemeName() + " authentication has not been initiated");
    case 2:
      throw new AuthenticationException(getSchemeName() + " authentication has failed");
    case 3:
      try
      {
        String str1 = null;
        if (isProxy())
          str1 = "http.proxy_host";
        else
          str1 = "http.target_host";
        HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute(str1);
        if (localHttpHost == null)
          throw new AuthenticationException("Authentication host is not set in the execution context");
        String str3;
        if ((!this.stripPort) && (localHttpHost.getPort() > 0))
          str3 = localHttpHost.toHostString();
        else
          str3 = localHttpHost.getHostName();
        if (this.log.isDebugEnabled())
          this.log.debug("init " + str3);
        this.token = generateToken(this.token, str3);
        this.state = State.TOKEN_GENERATED;
      }
      catch (GSSException localGSSException)
      {
        this.state = State.FAILED;
        if ((localGSSException.getMajor() == 9) || (localGSSException.getMajor() == 8))
          throw new InvalidCredentialsException(localGSSException.getMessage(), localGSSException);
        if (localGSSException.getMajor() == 13)
          throw new InvalidCredentialsException(localGSSException.getMessage(), localGSSException);
        if ((localGSSException.getMajor() == 10) || (localGSSException.getMajor() == 19) || (localGSSException.getMajor() == 20))
          throw new AuthenticationException(localGSSException.getMessage(), localGSSException);
        throw new AuthenticationException(localGSSException.getMessage());
      }
    case 4:
      String str2 = new String(this.base64codec.encode(this.token));
      if (this.log.isDebugEnabled())
        this.log.debug("Sending response '" + str2 + "' back to the auth server");
      return new BasicHeader("Authorization", "Negotiate " + str2);
    }
    throw new IllegalStateException("Illegal state: " + this.state);
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    String str = paramCharArrayBuffer.substringTrimmed(paramInt1, paramInt2);
    if (this.log.isDebugEnabled())
      this.log.debug("Received challenge '" + str + "' from the auth server");
    if (this.state == State.UNINITIATED)
    {
      this.token = this.base64codec.decode(str.getBytes());
      this.state = State.CHALLENGE_RECEIVED;
    }
    else
    {
      this.log.debug("Authentication already attempted");
      this.state = State.FAILED;
    }
  }

  static enum State
  {
    UNINITIATED, CHALLENGE_RECEIVED, TOKEN_GENERATED, FAILED;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.GGSSchemeBase
 * JD-Core Version:    0.6.2
 */