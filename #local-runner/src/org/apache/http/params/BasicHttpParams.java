package org.apache.http.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class BasicHttpParams extends AbstractHttpParams
  implements Serializable, Cloneable
{
  private final HashMap parameters = new HashMap();

  public Object getParameter(String paramString)
  {
    return this.parameters.get(paramString);
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    this.parameters.put(paramString, paramObject);
    return this;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicHttpParams localBasicHttpParams = (BasicHttpParams)super.clone();
    copyParams(localBasicHttpParams);
    return localBasicHttpParams;
  }

  public void copyParams(HttpParams paramHttpParams)
  {
    Iterator localIterator = this.parameters.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getKey() instanceof String))
        paramHttpParams.setParameter((String)localEntry.getKey(), localEntry.getValue());
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.params.BasicHttpParams
 * JD-Core Version:    0.6.2
 */