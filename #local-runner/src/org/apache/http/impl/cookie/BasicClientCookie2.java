package org.apache.http.impl.cookie;

import java.io.Serializable;
import java.util.Date;
import org.apache.http.cookie.SetCookie2;

public class BasicClientCookie2 extends BasicClientCookie
  implements Serializable, SetCookie2
{
  private String commentURL;
  private int[] ports;
  private boolean discard;

  public BasicClientCookie2(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public int[] getPorts()
  {
    return this.ports;
  }

  public void setPorts(int[] paramArrayOfInt)
  {
    this.ports = paramArrayOfInt;
  }

  public void setCommentURL(String paramString)
  {
    this.commentURL = paramString;
  }

  public void setDiscard(boolean paramBoolean)
  {
    this.discard = paramBoolean;
  }

  public boolean isExpired(Date paramDate)
  {
    return (this.discard) || (super.isExpired(paramDate));
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicClientCookie2 localBasicClientCookie2 = (BasicClientCookie2)super.clone();
    if (this.ports != null)
      localBasicClientCookie2.ports = ((int[])this.ports.clone());
    return localBasicClientCookie2;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.BasicClientCookie2
 * JD-Core Version:    0.6.2
 */