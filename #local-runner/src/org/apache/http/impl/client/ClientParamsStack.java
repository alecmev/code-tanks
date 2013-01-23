package org.apache.http.impl.client;

import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;

public class ClientParamsStack extends AbstractHttpParams
{
  protected final HttpParams applicationParams;
  protected final HttpParams clientParams;
  protected final HttpParams requestParams;
  protected final HttpParams overrideParams;

  public ClientParamsStack(HttpParams paramHttpParams1, HttpParams paramHttpParams2, HttpParams paramHttpParams3, HttpParams paramHttpParams4)
  {
    this.applicationParams = paramHttpParams1;
    this.clientParams = paramHttpParams2;
    this.requestParams = paramHttpParams3;
    this.overrideParams = paramHttpParams4;
  }

  public Object getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name must not be null.");
    Object localObject = null;
    if (this.overrideParams != null)
      localObject = this.overrideParams.getParameter(paramString);
    if ((localObject == null) && (this.requestParams != null))
      localObject = this.requestParams.getParameter(paramString);
    if ((localObject == null) && (this.clientParams != null))
      localObject = this.clientParams.getParameter(paramString);
    if ((localObject == null) && (this.applicationParams != null))
      localObject = this.applicationParams.getParameter(paramString);
    return localObject;
  }

  public HttpParams setParameter(String paramString, Object paramObject)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.client.ClientParamsStack
 * JD-Core Version:    0.6.2
 */