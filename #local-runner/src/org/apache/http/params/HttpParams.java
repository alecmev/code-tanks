package org.apache.http.params;

public abstract interface HttpParams
{
  public abstract Object getParameter(String paramString);

  public abstract HttpParams setParameter(String paramString, Object paramObject);

  public abstract int getIntParameter(String paramString, int paramInt);

  public abstract HttpParams setIntParameter(String paramString, int paramInt);

  public abstract boolean getBooleanParameter(String paramString, boolean paramBoolean);

  public abstract HttpParams setBooleanParameter(String paramString, boolean paramBoolean);

  public abstract boolean isParameterTrue(String paramString);

  public abstract boolean isParameterFalse(String paramString);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.params.HttpParams
 * JD-Core Version:    0.6.2
 */