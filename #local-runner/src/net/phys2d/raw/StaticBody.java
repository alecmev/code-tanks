package net.phys2d.raw;

import net.phys2d.raw.shapes.Shape;

public class StaticBody extends Body
{
  public strictfp StaticBody(String paramString, Shape paramShape)
  {
    super(paramString, paramShape, 3.4028235E+38F);
  }

  public strictfp boolean isRotatable()
  {
    return false;
  }

  public strictfp boolean isMoveable()
  {
    return false;
  }

  public strictfp boolean isStatic()
  {
    return true;
  }

  public strictfp boolean isResting()
  {
    return true;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.StaticBody
 * JD-Core Version:    0.6.2
 */