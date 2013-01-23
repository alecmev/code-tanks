package org.apache.commons.lang.text;

public class StrBuilder
  implements Cloneable
{
  protected char[] buffer;
  protected int size;
  private String nullText;

  public StrBuilder()
  {
    this(32);
  }

  public StrBuilder(int paramInt)
  {
    if (paramInt <= 0)
      paramInt = 32;
    this.buffer = new char[paramInt];
  }

  public int length()
  {
    return this.size;
  }

  public StrBuilder ensureCapacity(int paramInt)
  {
    if (paramInt > this.buffer.length)
    {
      char[] arrayOfChar = this.buffer;
      this.buffer = new char[paramInt * 2];
      System.arraycopy(arrayOfChar, 0, this.buffer, 0, this.size);
    }
    return this;
  }

  public StrBuilder appendNull()
  {
    if (this.nullText == null)
      return this;
    return append(this.nullText);
  }

  public StrBuilder append(String paramString)
  {
    if (paramString == null)
      return appendNull();
    int i = paramString.length();
    if (i > 0)
    {
      int j = length();
      ensureCapacity(j + i);
      paramString.getChars(0, i, this.buffer, j);
      this.size += i;
    }
    return this;
  }

  public boolean equals(StrBuilder paramStrBuilder)
  {
    if (this == paramStrBuilder)
      return true;
    if (this.size != paramStrBuilder.size)
      return false;
    char[] arrayOfChar1 = this.buffer;
    char[] arrayOfChar2 = paramStrBuilder.buffer;
    for (int i = this.size - 1; i >= 0; i--)
      if (arrayOfChar1[i] != arrayOfChar2[i])
        return false;
    return true;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof StrBuilder))
      return equals((StrBuilder)paramObject);
    return false;
  }

  public int hashCode()
  {
    char[] arrayOfChar = this.buffer;
    int i = 0;
    for (int j = this.size - 1; j >= 0; j--)
      i = 31 * i + arrayOfChar[j];
    return i;
  }

  public String toString()
  {
    return new String(this.buffer, 0, this.size);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    StrBuilder localStrBuilder = (StrBuilder)super.clone();
    localStrBuilder.buffer = new char[this.buffer.length];
    System.arraycopy(this.buffer, 0, localStrBuilder.buffer, 0, this.buffer.length);
    return localStrBuilder;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.lang.text.StrBuilder
 * JD-Core Version:    0.6.2
 */