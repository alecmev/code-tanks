package org.apache.log4j;

class CategoryKey
{
  String name;
  int hashCache;

  CategoryKey(String paramString)
  {
    this.name = paramString;
    this.hashCache = paramString.hashCode();
  }

  public final int hashCode()
  {
    return this.hashCache;
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject != null) && (CategoryKey.class == paramObject.getClass()))
      return this.name.equals(((CategoryKey)paramObject).name);
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.CategoryKey
 * JD-Core Version:    0.6.2
 */