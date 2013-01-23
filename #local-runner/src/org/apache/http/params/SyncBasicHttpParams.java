package org.apache.http.params;

public class SyncBasicHttpParams extends BasicHttpParams
{
  public synchronized HttpParams setParameter(String paramString, Object paramObject)
  {
    return super.setParameter(paramString, paramObject);
  }

  public synchronized Object getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }

  public synchronized Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.params.SyncBasicHttpParams
 * JD-Core Version:    0.6.2
 */