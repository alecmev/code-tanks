package org.apache.http.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

public class HeaderGroup
  implements Serializable, Cloneable
{
  private final List headers = new ArrayList(16);

  public void clear()
  {
    this.headers.clear();
  }

  public void addHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    this.headers.add(paramHeader);
  }

  public void updateHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    for (int i = 0; i < this.headers.size(); i++)
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramHeader.getName()))
      {
        this.headers.set(i, paramHeader);
        return;
      }
    }
    this.headers.add(paramHeader);
  }

  public void setHeaders(Header[] paramArrayOfHeader)
  {
    clear();
    if (paramArrayOfHeader == null)
      return;
    for (int i = 0; i < paramArrayOfHeader.length; i++)
      this.headers.add(paramArrayOfHeader[i]);
  }

  public Header[] getHeaders(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < this.headers.size(); i++)
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        localArrayList.add(localHeader);
    }
    return (Header[])localArrayList.toArray(new Header[localArrayList.size()]);
  }

  public Header getFirstHeader(String paramString)
  {
    for (int i = 0; i < this.headers.size(); i++)
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        return localHeader;
    }
    return null;
  }

  public Header[] getAllHeaders()
  {
    return (Header[])this.headers.toArray(new Header[this.headers.size()]);
  }

  public boolean containsHeader(String paramString)
  {
    for (int i = 0; i < this.headers.size(); i++)
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        return true;
    }
    return false;
  }

  public HeaderIterator iterator()
  {
    return new BasicListHeaderIterator(this.headers, null);
  }

  public HeaderIterator iterator(String paramString)
  {
    return new BasicListHeaderIterator(this.headers, paramString);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    HeaderGroup localHeaderGroup = (HeaderGroup)super.clone();
    localHeaderGroup.headers.clear();
    localHeaderGroup.headers.addAll(this.headers);
    return localHeaderGroup;
  }

  public String toString()
  {
    return this.headers.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.HeaderGroup
 * JD-Core Version:    0.6.2
 */