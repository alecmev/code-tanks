package net.phys2d.raw.shapes;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;

public class Line extends AbstractShape
  implements DynamicShape
{
  private ROVector2f start;
  private ROVector2f end;
  private Vector2f vec;
  private float lenSquared;
  private Vector2f loc = new Vector2f(0.0F, 0.0F);
  private Vector2f v = new Vector2f(0.0F, 0.0F);
  private Vector2f v2 = new Vector2f(0.0F, 0.0F);
  private Vector2f proj = new Vector2f(0.0F, 0.0F);
  private Vector2f closest = new Vector2f(0.0F, 0.0F);
  private Vector2f other = new Vector2f(0.0F, 0.0F);
  private boolean outerEdge = true;
  private boolean innerEdge = true;

  public strictfp Line(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this(new Vector2f(paramFloat1, paramFloat2), new Vector2f(paramFloat3, paramFloat4));
  }

  public strictfp Line(ROVector2f paramROVector2f1, ROVector2f paramROVector2f2)
  {
    float f = Math.max(paramROVector2f1.length(), paramROVector2f2.length());
    this.bounds = new AABox(0.0F, 0.0F, f * 2.0F, f * 2.0F);
    set(paramROVector2f1, paramROVector2f2);
  }

  public strictfp boolean blocksInnerEdge()
  {
    return this.innerEdge;
  }

  public strictfp boolean blocksOuterEdge()
  {
    return this.outerEdge;
  }

  public strictfp ROVector2f getStart()
  {
    return this.start;
  }

  public strictfp ROVector2f getEnd()
  {
    return this.end;
  }

  public strictfp float lengthSquared()
  {
    return this.vec.lengthSquared();
  }

  public strictfp void set(ROVector2f paramROVector2f1, ROVector2f paramROVector2f2)
  {
    this.start = paramROVector2f1;
    this.end = paramROVector2f2;
    this.vec = new Vector2f(paramROVector2f2);
    this.vec.sub(paramROVector2f1);
    this.lenSquared = this.vec.length();
    this.lenSquared *= this.lenSquared;
  }

  public strictfp float getDX()
  {
    return this.end.getX() - this.start.getX();
  }

  public strictfp float getDY()
  {
    return this.end.getY() - this.start.getY();
  }

  public strictfp float getX1()
  {
    return this.start.getX();
  }

  public strictfp float getY1()
  {
    return this.start.getY();
  }

  public strictfp float getX2()
  {
    return this.end.getX();
  }

  public strictfp float getY2()
  {
    return this.end.getY();
  }

  public strictfp float distanceSquared(ROVector2f paramROVector2f)
  {
    getClosestPoint(paramROVector2f, this.closest);
    this.closest.sub(paramROVector2f);
    float f = this.closest.lengthSquared();
    return f;
  }

  public strictfp void getClosestPoint(ROVector2f paramROVector2f, Vector2f paramVector2f)
  {
    this.loc.set(paramROVector2f);
    this.loc.sub(this.start);
    this.v.set(this.vec);
    this.v2.set(this.vec);
    this.v2.scale(-1.0F);
    this.v.normalise();
    this.loc.projectOntoUnit(this.v, this.proj);
    if (this.proj.lengthSquared() > this.vec.lengthSquared())
    {
      paramVector2f.set(this.end);
      return;
    }
    this.proj.add(this.start);
    this.other.set(this.proj);
    this.other.sub(this.end);
    if (this.other.lengthSquared() > this.vec.lengthSquared())
    {
      paramVector2f.set(this.start);
      return;
    }
    paramVector2f.set(this.proj);
  }

  public strictfp float getSurfaceFactor()
  {
    return lengthSquared() / 2.0F;
  }

  public strictfp Vector2f[] getVertices(ROVector2f paramROVector2f, float paramFloat)
  {
    float f1 = (float)Math.cos(paramFloat);
    float f2 = (float)Math.sin(paramFloat);
    Vector2f[] arrayOfVector2f = new Vector2f[2];
    arrayOfVector2f[0] = new Vector2f(getX1() * f1 - getY1() * f2, getY1() * f1 + getX1() * f2);
    arrayOfVector2f[0].add(paramROVector2f);
    arrayOfVector2f[1] = new Vector2f(getX2() * f1 - getY2() * f2, getY2() * f1 + getX2() * f2);
    arrayOfVector2f[1].add(paramROVector2f);
    return arrayOfVector2f;
  }

  public strictfp void move(ROVector2f paramROVector2f)
  {
    Vector2f localVector2f = new Vector2f(this.start);
    localVector2f.add(paramROVector2f);
    this.start = localVector2f;
    localVector2f = new Vector2f(this.end);
    localVector2f.add(paramROVector2f);
    this.end = localVector2f;
  }

  public strictfp String toString()
  {
    return "[Line " + this.start + "," + this.end + "]";
  }

  public strictfp Vector2f intersect(Line paramLine)
  {
    float f1 = this.end.getX() - this.start.getX();
    float f2 = paramLine.end.getX() - paramLine.start.getX();
    float f3 = this.end.getY() - this.start.getY();
    float f4 = paramLine.end.getY() - paramLine.start.getY();
    float f5 = f4 * f1 - f2 * f3;
    if (f5 == 0.0F)
      return null;
    float f6 = f2 * (this.start.getY() - paramLine.start.getY()) - f4 * (this.start.getX() - paramLine.start.getX());
    f6 /= f5;
    float f7 = f1 * (this.start.getY() - paramLine.start.getY()) - f3 * (this.start.getX() - paramLine.start.getX());
    f7 /= f5;
    float f8 = f6;
    float f9 = this.start.getX() + f8 * (this.end.getX() - this.start.getX());
    float f10 = this.start.getY() + f8 * (this.end.getY() - this.start.getY());
    return new Vector2f(f9, f10);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.shapes.Line
 * JD-Core Version:    0.6.2
 */