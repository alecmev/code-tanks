package org.apache.log4j;

class NameValue
{
  String key;
  String value;

  public NameValue(String paramString1, String paramString2)
  {
    this.key = paramString1;
    this.value = paramString2;
  }

  public String toString()
  {
    return this.key + "=" + this.value;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.NameValue
 * JD-Core Version:    0.6.2
 */