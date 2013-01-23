package net.phys2d.math;

public final class MathUtil
{
  public static strictfp Vector2f scale(ROVector2f paramROVector2f, float paramFloat)
  {
    Vector2f localVector2f = new Vector2f(paramROVector2f);
    localVector2f.scale(paramFloat);
    return localVector2f;
  }

  public static strictfp Vector2f sub(ROVector2f paramROVector2f1, ROVector2f paramROVector2f2)
  {
    Vector2f localVector2f = new Vector2f(paramROVector2f1);
    localVector2f.sub(paramROVector2f2);
    return localVector2f;
  }

  public static strictfp float sign(float paramFloat)
  {
    return paramFloat < 0.0F ? -1.0F : 1.0F;
  }

  public static strictfp Vector2f mul(Matrix2f paramMatrix2f, ROVector2f paramROVector2f)
  {
    return new Vector2f(paramMatrix2f.col1.x * paramROVector2f.getX() + paramMatrix2f.col2.x * paramROVector2f.getY(), paramMatrix2f.col1.y * paramROVector2f.getX() + paramMatrix2f.col2.y * paramROVector2f.getY());
  }

  public static strictfp Matrix2f mul(Matrix2f paramMatrix2f1, Matrix2f paramMatrix2f2)
  {
    return new Matrix2f(mul(paramMatrix2f1, paramMatrix2f2.col1), mul(paramMatrix2f1, paramMatrix2f2.col2));
  }

  public static strictfp Matrix2f abs(Matrix2f paramMatrix2f)
  {
    return new Matrix2f(abs(paramMatrix2f.col1), abs(paramMatrix2f.col2));
  }

  public static strictfp Vector2f abs(Vector2f paramVector2f)
  {
    return new Vector2f(Math.abs(paramVector2f.x), Math.abs(paramVector2f.y));
  }

  public static strictfp float cross(Vector2f paramVector2f1, Vector2f paramVector2f2)
  {
    return paramVector2f1.x * paramVector2f2.y - paramVector2f1.y * paramVector2f2.x;
  }

  public static strictfp Vector2f cross(float paramFloat, Vector2f paramVector2f)
  {
    return new Vector2f(-paramFloat * paramVector2f.y, paramFloat * paramVector2f.x);
  }

  public static strictfp Vector2f cross(Vector2f paramVector2f, float paramFloat)
  {
    return new Vector2f(paramFloat * paramVector2f.y, -paramFloat * paramVector2f.x);
  }

  public static strictfp float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.max(paramFloat2, Math.min(paramFloat1, paramFloat3));
  }

  public static strictfp Vector2f getNormal(ROVector2f paramROVector2f1, ROVector2f paramROVector2f2)
  {
    Vector2f localVector2f = new Vector2f(paramROVector2f2);
    localVector2f.sub(paramROVector2f1);
    localVector2f = new Vector2f(localVector2f.y, -localVector2f.x);
    localVector2f.normalise();
    return localVector2f;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.math.MathUtil
 * JD-Core Version:    0.6.2
 */