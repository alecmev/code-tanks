package net.phys2d.raw.shapes;

public class Circle extends AbstractShape
  implements DynamicShape
{
  private final float radius;

  public strictfp Circle(float paramFloat)
  {
    super(new AABox(paramFloat * 2.0F, paramFloat * 2.0F));
    this.radius = paramFloat;
  }

  public strictfp float getRadius()
  {
    return this.radius;
  }

  public strictfp float getSurfaceFactor()
  {
    float f = (float)(6.283185307179586D * this.radius);
    f /= 2.0F;
    return f * f;
  }

  public strictfp boolean touches(float paramFloat1, float paramFloat2, Circle paramCircle, float paramFloat3, float paramFloat4)
  {
    float f1 = this.radius + paramCircle.radius;
    if (Math.abs(paramFloat3 - paramFloat1) > f1)
      return false;
    if (Math.abs(paramFloat4 - paramFloat2) > f1)
      return false;
    f1 *= f1;
    float f2 = Math.abs(paramFloat3 - paramFloat1);
    float f3 = Math.abs(paramFloat4 - paramFloat2);
    return f1 >= f2 * f2 + f3 * f3;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.shapes.Circle
 * JD-Core Version:    0.6.2
 */