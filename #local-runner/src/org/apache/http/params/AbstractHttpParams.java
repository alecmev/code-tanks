package org.apache.http.params;

public abstract class AbstractHttpParams
  implements HttpParams
{
  public int getIntParameter(String paramString, int paramInt)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramInt;
    return ((Integer)localObject).intValue();
  }

  public HttpParams setIntParameter(String paramString, int paramInt)
  {
    setParameter(paramString, new Integer(paramInt));
    return this;
  }

  public boolean getBooleanParameter(String paramString, boolean paramBoolean)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramBoolean;
    return ((Boolean)localObject).booleanValue();
  }

  public HttpParams setBooleanParameter(String paramString, boolean paramBoolean)
  {
    setParameter(paramString, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
    return this;
  }

  public boolean isParameterTrue(String paramString)
  {
    return getBooleanParameter(paramString, false);
  }

  public boolean isParameterFalse(String paramString)
  {
    return !getBooleanParameter(paramString, false);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.params.AbstractHttpParams
 * JD-Core Version:    0.6.2
 */