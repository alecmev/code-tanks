package net.phys2d.raw;

import java.util.ArrayList;

public class ArbiterList
{
  private ArrayList elements = new ArrayList();

  void add(Arbiter paramArbiter)
  {
    this.elements.add(paramArbiter);
  }

  public int size()
  {
    return this.elements.size();
  }

  public int indexOf(Arbiter paramArbiter)
  {
    return this.elements.indexOf(paramArbiter);
  }

  void remove(Arbiter paramArbiter)
  {
    if (!this.elements.contains(paramArbiter))
      return;
    this.elements.set(this.elements.indexOf(paramArbiter), this.elements.get(this.elements.size() - 1));
    this.elements.remove(this.elements.size() - 1);
  }

  public Arbiter get(int paramInt)
  {
    return (Arbiter)this.elements.get(paramInt);
  }

  public boolean contains(Arbiter paramArbiter)
  {
    return this.elements.contains(paramArbiter);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.ArbiterList
 * JD-Core Version:    0.6.2
 */