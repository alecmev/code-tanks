package net.phys2d.raw.shapes;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Matrix2f;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;

public class Box extends AbstractShape
  implements DynamicShape
{
  private final Vector2f size = new Vector2f();

  public strictfp Box(float paramFloat1, float paramFloat2)
  {
    this.size.set(paramFloat1, paramFloat2);
    this.bounds = new AABox(this.size.length(), this.size.length());
  }

  public strictfp ROVector2f getSize()
  {
    return this.size;
  }

  public strictfp float getSurfaceFactor()
  {
    float f1 = this.size.getX();
    float f2 = this.size.getY();
    return f1 * f1 + f2 * f2;
  }

  public strictfp Vector2f[] getPoints(ROVector2f paramROVector2f, float paramFloat)
  {
    Matrix2f localMatrix2f = new Matrix2f(paramFloat);
    Vector2f[] arrayOfVector2f = new Vector2f[4];
    Vector2f localVector2f = MathUtil.scale(getSize(), 0.5F);
    arrayOfVector2f[0] = MathUtil.mul(localMatrix2f, new Vector2f(-localVector2f.getX(), -localVector2f.getY()));
    arrayOfVector2f[0].add(paramROVector2f);
    arrayOfVector2f[1] = MathUtil.mul(localMatrix2f, new Vector2f(localVector2f.getX(), -localVector2f.getY()));
    arrayOfVector2f[1].add(paramROVector2f);
    arrayOfVector2f[2] = MathUtil.mul(localMatrix2f, new Vector2f(localVector2f.getX(), localVector2f.getY()));
    arrayOfVector2f[2].add(paramROVector2f);
    arrayOfVector2f[3] = MathUtil.mul(localMatrix2f, new Vector2f(-localVector2f.getX(), localVector2f.getY()));
    arrayOfVector2f[3].add(paramROVector2f);
    return arrayOfVector2f;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.shapes.Box
 * JD-Core Version:    0.6.2
 */