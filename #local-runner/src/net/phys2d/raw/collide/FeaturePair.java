package net.phys2d.raw.collide;

public class FeaturePair
{
  int inEdge1;
  int outEdge1;
  int inEdge2;
  int outEdge2;

  public FeaturePair()
  {
  }

  public FeaturePair(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.inEdge1 = paramInt1;
    this.inEdge2 = paramInt2;
    this.outEdge1 = paramInt3;
    this.outEdge2 = paramInt4;
  }

  FeaturePair(int paramInt)
  {
    this.inEdge1 = paramInt;
  }

  int getKey()
  {
    return this.inEdge1 + (this.outEdge1 << 8) + (this.inEdge2 << 16) + (this.outEdge2 << 24);
  }

  public int hashCode()
  {
    return getKey();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof FeaturePair))
      return ((FeaturePair)paramObject).getKey() == getKey();
    return false;
  }

  public void set(FeaturePair paramFeaturePair)
  {
    this.inEdge1 = paramFeaturePair.inEdge1;
    this.inEdge2 = paramFeaturePair.inEdge2;
    this.outEdge1 = paramFeaturePair.outEdge1;
    this.outEdge2 = paramFeaturePair.outEdge2;
  }

  public String toString()
  {
    return "((" + this.inEdge1 + "," + this.inEdge2 + "),(" + this.outEdge1 + "," + this.outEdge2 + "))";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.FeaturePair
 * JD-Core Version:    0.6.2
 */