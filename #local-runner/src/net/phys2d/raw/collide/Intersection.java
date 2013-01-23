package net.phys2d.raw.collide;

import net.phys2d.math.Vector2f;

public class Intersection
{
  public int edgeA;
  public int edgeB;
  public Vector2f position;
  public boolean isIngoing;

  public Intersection(int paramInt1, int paramInt2, Vector2f paramVector2f, boolean paramBoolean)
  {
    this.edgeA = paramInt1;
    this.edgeB = paramInt2;
    this.position = paramVector2f;
    this.isIngoing = paramBoolean;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.Intersection
 * JD-Core Version:    0.6.2
 */