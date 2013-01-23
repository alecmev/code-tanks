package org.apache.http.impl.auth;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;

public class NTLMSchemeFactory
  implements AuthSchemeFactory
{
  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new NTLMScheme(new NTLMEngineImpl());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.NTLMSchemeFactory
 * JD-Core Version:    0.6.2
 */