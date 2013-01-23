package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.util.LangUtils;

public class BasicHeaderElement
  implements Cloneable, HeaderElement
{
  private final String name;
  private final String value;
  private final NameValuePair[] parameters;

  public BasicHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
    if (paramArrayOfNameValuePair != null)
      this.parameters = paramArrayOfNameValuePair;
    else
      this.parameters = new NameValuePair[0];
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public NameValuePair[] getParameters()
  {
    return (NameValuePair[])this.parameters.clone();
  }

  public NameValuePair getParameterByName(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    Object localObject = null;
    for (int i = 0; i < this.parameters.length; i++)
    {
      NameValuePair localNameValuePair = this.parameters[i];
      if (localNameValuePair.getName().equalsIgnoreCase(paramString))
      {
        localObject = localNameValuePair;
        break;
      }
    }
    return localObject;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof HeaderElement))
    {
      BasicHeaderElement localBasicHeaderElement = (BasicHeaderElement)paramObject;
      return (this.name.equals(localBasicHeaderElement.name)) && (LangUtils.equals(this.value, localBasicHeaderElement.value)) && (LangUtils.equals(this.parameters, localBasicHeaderElement.parameters));
    }
    return false;
  }

  public int hashCode()
  {
    int i = 17;
    i = LangUtils.hashCode(i, this.name);
    i = LangUtils.hashCode(i, this.value);
    for (int j = 0; j < this.parameters.length; j++)
      i = LangUtils.hashCode(i, this.parameters[j]);
    return i;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    if (this.value != null)
    {
      localStringBuilder.append("=");
      localStringBuilder.append(this.value);
    }
    for (int i = 0; i < this.parameters.length; i++)
    {
      localStringBuilder.append("; ");
      localStringBuilder.append(this.parameters[i]);
    }
    return localStringBuilder.toString();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicHeaderElement
 * JD-Core Version:    0.6.2
 */