package net.phys2d.math;

public class Vector2f
  implements ROVector2f
{
  public float x;
  public float y;

  public strictfp Vector2f()
  {
  }

  public strictfp float getX()
  {
    return this.x;
  }

  public strictfp float getY()
  {
    return this.y;
  }

  public strictfp Vector2f(ROVector2f paramROVector2f)
  {
    this(paramROVector2f.getX(), paramROVector2f.getY());
  }

  public strictfp Vector2f(float paramFloat1, float paramFloat2)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }

  public strictfp void set(ROVector2f paramROVector2f)
  {
    set(paramROVector2f.getX(), paramROVector2f.getY());
  }

  public strictfp float dot(ROVector2f paramROVector2f)
  {
    return this.x * paramROVector2f.getX() + this.y * paramROVector2f.getY();
  }

  public strictfp void set(float paramFloat1, float paramFloat2)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }

  public strictfp void add(ROVector2f paramROVector2f)
  {
    this.x += paramROVector2f.getX();
    this.y += paramROVector2f.getY();
  }

  public strictfp void sub(ROVector2f paramROVector2f)
  {
    this.x -= paramROVector2f.getX();
    this.y -= paramROVector2f.getY();
  }

  public strictfp void scale(float paramFloat)
  {
    this.x *= paramFloat;
    this.y *= paramFloat;
  }

  public strictfp void normalise()
  {
    float f = length();
    if (f == 0.0F)
      return;
    this.x /= f;
    this.y /= f;
  }

  public strictfp float lengthSquared()
  {
    return this.x * this.x + this.y * this.y;
  }

  public strictfp float length()
  {
    return (float)Math.sqrt(lengthSquared());
  }

  public strictfp void projectOntoUnit(ROVector2f paramROVector2f, Vector2f paramVector2f)
  {
    float f = paramROVector2f.dot(this);
    paramVector2f.x = (f * paramROVector2f.getX());
    paramVector2f.y = (f * paramROVector2f.getY());
  }

  public strictfp String toString()
  {
    return "[Vec " + this.x + "," + this.y + " (" + length() + ")]";
  }

  public strictfp float distance(ROVector2f paramROVector2f)
  {
    return (float)Math.sqrt(distanceSquared(paramROVector2f));
  }

  public strictfp float distanceSquared(ROVector2f paramROVector2f)
  {
    float f1 = paramROVector2f.getX() - getX();
    float f2 = paramROVector2f.getY() - getY();
    return f1 * f1 + f2 * f2;
  }

  public strictfp boolean equalsDelta(ROVector2f paramROVector2f, float paramFloat)
  {
    return (paramROVector2f.getX() - paramFloat < this.x) && (paramROVector2f.getX() + paramFloat > this.x) && (paramROVector2f.getY() - paramFloat < this.y) && (paramROVector2f.getY() + paramFloat > this.y);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.math.Vector2f
 * JD-Core Version:    0.6.2
 */