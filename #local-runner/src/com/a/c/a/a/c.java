package com.a.c.a.a;

import javax.vecmath.Vector2d;

public class c
{
  private c()
  {
    throw new UnsupportedOperationException();
  }

  public static Vector2d[] a(Vector2d paramVector2d1, Vector2d paramVector2d2, double paramDouble)
  {
    Vector2d[] arrayOfVector2d = new Vector2d[4];
    arrayOfVector2d[0] = a(new Vector2d(-paramVector2d2.x * 0.5D, -paramVector2d2.y * 0.5D), paramDouble);
    arrayOfVector2d[0].add(paramVector2d1);
    arrayOfVector2d[1] = a(new Vector2d(paramVector2d2.x * 0.5D, -paramVector2d2.y * 0.5D), paramDouble);
    arrayOfVector2d[1].add(paramVector2d1);
    arrayOfVector2d[2] = a(new Vector2d(paramVector2d2.x * 0.5D, paramVector2d2.y * 0.5D), paramDouble);
    arrayOfVector2d[2].add(paramVector2d1);
    arrayOfVector2d[3] = a(new Vector2d(-paramVector2d2.x * 0.5D, paramVector2d2.y * 0.5D), paramDouble);
    arrayOfVector2d[3].add(paramVector2d1);
    return arrayOfVector2d;
  }

  public static Vector2d a(Vector2d paramVector2d, double paramDouble)
  {
    return new Vector2d(paramVector2d.x * StrictMath.cos(paramDouble) - paramVector2d.y * StrictMath.sin(paramDouble), paramVector2d.x * StrictMath.sin(paramDouble) + paramVector2d.y * StrictMath.cos(paramDouble));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.c.a.a.c
 * JD-Core Version:    0.6.2
 */