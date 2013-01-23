package net.phys2d.raw.shapes;

public abstract class AbstractShape
  implements Shape
{
  protected AABox bounds;

  protected strictfp AbstractShape()
  {
  }

  public strictfp AbstractShape(AABox paramAABox)
  {
    this.bounds = paramAABox;
  }

  public strictfp AABox getBounds()
  {
    return this.bounds;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.shapes.AbstractShape
 * JD-Core Version:    0.6.2
 */