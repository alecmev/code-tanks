package net.phys2d.math;

public abstract interface ROVector2f
{
  public abstract float getX();

  public abstract float getY();

  public abstract float length();

  public abstract float dot(ROVector2f paramROVector2f);

  public abstract void projectOntoUnit(ROVector2f paramROVector2f, Vector2f paramVector2f);

  public abstract float lengthSquared();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.math.ROVector2f
 * JD-Core Version:    0.6.2
 */