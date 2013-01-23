package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.NameValuePair;
import org.apache.http.util.LangUtils;

public class BasicNameValuePair
  implements Serializable, Cloneable, NameValuePair
{
  private final String name;
  private final String value;

  public BasicNameValuePair(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public String toString()
  {
    if (this.value == null)
      return this.name;
    int i = this.name.length() + 1 + this.value.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    localStringBuilder.append(this.name);
    localStringBuilder.append("=");
    localStringBuilder.append(this.value);
    return localStringBuilder.toString();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NameValuePair))
    {
      BasicNameValuePair localBasicNameValuePair = (BasicNameValuePair)paramObject;
      return (this.name.equals(localBasicNameValuePair.name)) && (LangUtils.equals(this.value, localBasicNameValuePair.value));
    }
    return false;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.name);
    i = LangUtils.hashCode(i, this.value);
    return i;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicNameValuePair
 * JD-Core Version:    0.6.2
 */