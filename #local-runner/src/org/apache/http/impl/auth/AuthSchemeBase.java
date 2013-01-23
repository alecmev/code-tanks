package org.apache.http.impl.auth;

import java.util.Locale;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.ContextAwareAuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;

public abstract class AuthSchemeBase
  implements ContextAwareAuthScheme
{
  private ChallengeState challengeState;

  public AuthSchemeBase(ChallengeState paramChallengeState)
  {
    this.challengeState = paramChallengeState;
  }

  public AuthSchemeBase()
  {
    this(null);
  }

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    String str1 = paramHeader.getName();
    if (str1.equalsIgnoreCase("WWW-Authenticate"))
      this.challengeState = ChallengeState.TARGET;
    else if (str1.equalsIgnoreCase("Proxy-Authenticate"))
      this.challengeState = ChallengeState.PROXY;
    else
      throw new MalformedChallengeException("Unexpected header name: " + str1);
    CharArrayBuffer localCharArrayBuffer;
    if ((paramHeader instanceof FormattedHeader))
    {
      localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
      str2 = ((FormattedHeader)paramHeader).getValuePos();
    }
    else
    {
      str3 = paramHeader.getValue();
      if (str3 == null)
        throw new MalformedChallengeException("Header value is null");
      localCharArrayBuffer = new CharArrayBuffer(str3.length());
      localCharArrayBuffer.append(str3);
    }
    for (String str2 = 0; (str2 < localCharArrayBuffer.length()) && (HTTP.isWhitespace(localCharArrayBuffer.charAt(str2))); str2++);
    String str3 = str2;
    while ((str2 < localCharArrayBuffer.length()) && (!HTTP.isWhitespace(localCharArrayBuffer.charAt(str2))))
      str2++;
    int i = str2;
    String str4 = localCharArrayBuffer.substring(str3, i);
    if (!str4.equalsIgnoreCase(getSchemeName()))
      throw new MalformedChallengeException("Invalid scheme identifier: " + str4);
    parseChallenge(localCharArrayBuffer, str2, localCharArrayBuffer.length());
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest);
  }

  protected abstract void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException;

  public boolean isProxy()
  {
    return (this.challengeState != null) && (this.challengeState == ChallengeState.PROXY);
  }

  public String toString()
  {
    String str = getSchemeName();
    if (str != null)
      return str.toUpperCase(Locale.US);
    return super.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.AuthSchemeBase
 * JD-Core Version:    0.6.2
 */