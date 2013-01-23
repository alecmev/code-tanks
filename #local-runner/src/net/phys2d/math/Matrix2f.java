package net.phys2d.math;

public class Matrix2f
{
  public Vector2f col1 = new Vector2f();
  public Vector2f col2 = new Vector2f();

  public strictfp Matrix2f()
  {
  }

  public strictfp Matrix2f(float paramFloat)
  {
    float f1 = (float)Math.cos(paramFloat);
    float f2 = (float)Math.sin(paramFloat);
    this.col1.x = f1;
    this.col2.x = (-f2);
    this.col1.y = f2;
    this.col2.y = f1;
  }

  public strictfp Matrix2f(Vector2f paramVector2f1, Vector2f paramVector2f2)
  {
    this.col1.set(paramVector2f1);
    this.col2.set(paramVector2f2);
  }

  public strictfp Matrix2f transpose()
  {
    return new Matrix2f(new Vector2f(this.col1.x, this.col2.x), new Vector2f(this.col1.y, this.col2.y));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.math.Matrix2f
 * JD-Core Version:    0.6.2
 */