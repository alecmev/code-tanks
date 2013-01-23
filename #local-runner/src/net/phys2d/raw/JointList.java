package net.phys2d.raw;

import java.util.ArrayList;

public class JointList
{
  private ArrayList elements = new ArrayList();

  public int size()
  {
    return this.elements.size();
  }

  public Joint get(int paramInt)
  {
    return (Joint)this.elements.get(paramInt);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.JointList
 * JD-Core Version:    0.6.2
 */