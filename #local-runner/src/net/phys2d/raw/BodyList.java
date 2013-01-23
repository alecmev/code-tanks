package net.phys2d.raw;

import java.util.ArrayList;

public class BodyList
{
  private ArrayList elements = new ArrayList();

  public void add(Body paramBody)
  {
    this.elements.add(paramBody);
  }

  public int size()
  {
    return this.elements.size();
  }

  public void remove(Body paramBody)
  {
    this.elements.remove(paramBody);
  }

  public Body get(int paramInt)
  {
    return (Body)this.elements.get(paramInt);
  }

  public void clear()
  {
    this.elements.clear();
  }

  public boolean contains(Body paramBody)
  {
    return this.elements.contains(paramBody);
  }

  public String toString()
  {
    String str = "[BodyList ";
    for (int i = 0; i < this.elements.size(); i++)
      str = str + get(i) + ",";
    str = str + "]";
    return str;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.BodyList
 * JD-Core Version:    0.6.2
 */