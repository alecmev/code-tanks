package org.apache.http.impl.auth;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HeaderElement;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.HeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public abstract class RFC2617Scheme extends AuthSchemeBase
{
  private final Map params = new HashMap();

  public RFC2617Scheme(ChallengeState paramChallengeState)
  {
    super(paramChallengeState);
  }

  public RFC2617Scheme()
  {
    this(null);
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    BasicHeaderValueParser localBasicHeaderValueParser = BasicHeaderValueParser.DEFAULT;
    ParserCursor localParserCursor = new ParserCursor(paramInt1, paramCharArrayBuffer.length());
    HeaderElement[] arrayOfHeaderElement1 = localBasicHeaderValueParser.parseElements(paramCharArrayBuffer, localParserCursor);
    if (arrayOfHeaderElement1.length == 0)
      throw new MalformedChallengeException("Authentication challenge is empty");
    this.params.clear();
    for (HeaderElement localHeaderElement : arrayOfHeaderElement1)
      this.params.put(localHeaderElement.getName(), localHeaderElement.getValue());
  }

  protected Map getParameters()
  {
    return this.params;
  }

  public String getParameter(String paramString)
  {
    if (paramString == null)
      return null;
    return (String)this.params.get(paramString.toLowerCase(Locale.ENGLISH));
  }

  public String getRealm()
  {
    return getParameter("realm");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.RFC2617Scheme
 * JD-Core Version:    0.6.2
 */