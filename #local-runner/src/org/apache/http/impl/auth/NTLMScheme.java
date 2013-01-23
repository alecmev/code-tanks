package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.NTCredentials;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

public class NTLMScheme extends AuthSchemeBase
{
  private final NTLMEngine engine;
  private State state;
  private String challenge;

  public NTLMScheme(NTLMEngine paramNTLMEngine)
  {
    if (paramNTLMEngine == null)
      throw new IllegalArgumentException("NTLM engine may not be null");
    this.engine = paramNTLMEngine;
    this.state = State.UNINITIATED;
    this.challenge = null;
  }

  public String getSchemeName()
  {
    return "ntlm";
  }

  public String getRealm()
  {
    return null;
  }

  public boolean isConnectionBased()
  {
    return true;
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    String str = paramCharArrayBuffer.substringTrimmed(paramInt1, paramInt2);
    if (str.length() == 0)
    {
      if (this.state == State.UNINITIATED)
        this.state = State.CHALLENGE_RECEIVED;
      else
        this.state = State.FAILED;
      this.challenge = null;
    }
    else
    {
      this.state = State.MSG_TYPE2_RECEVIED;
      this.challenge = str;
    }
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    NTCredentials localNTCredentials = null;
    try
    {
      localNTCredentials = (NTCredentials)paramCredentials;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + paramCredentials.getClass().getName());
    }
    String str = null;
    if ((this.state == State.CHALLENGE_RECEIVED) || (this.state == State.FAILED))
    {
      str = this.engine.generateType1Msg(localNTCredentials.getDomain(), localNTCredentials.getWorkstation());
      this.state = State.MSG_TYPE1_GENERATED;
    }
    else if (this.state == State.MSG_TYPE2_RECEVIED)
    {
      str = this.engine.generateType3Msg(localNTCredentials.getUserName(), localNTCredentials.getPassword(), localNTCredentials.getDomain(), localNTCredentials.getWorkstation(), this.challenge);
      this.state = State.MSG_TYPE3_GENERATED;
    }
    else
    {
      throw new AuthenticationException("Unexpected state: " + this.state);
    }
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(32);
    if (isProxy())
      localCharArrayBuffer.append("Proxy-Authorization");
    else
      localCharArrayBuffer.append("Authorization");
    localCharArrayBuffer.append(": NTLM ");
    localCharArrayBuffer.append(str);
    return new BufferedHeader(localCharArrayBuffer);
  }

  public boolean isComplete()
  {
    return (this.state == State.MSG_TYPE3_GENERATED) || (this.state == State.FAILED);
  }

  static enum State
  {
    UNINITIATED, CHALLENGE_RECEIVED, MSG_TYPE1_GENERATED, MSG_TYPE2_RECEVIED, MSG_TYPE3_GENERATED, FAILED;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.NTLMScheme
 * JD-Core Version:    0.6.2
 */