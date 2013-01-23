package javax.vecmath;

import java.io.Serializable;

public class Vector2d extends Tuple2d
  implements Serializable
{
  public Vector2d(double paramDouble1, double paramDouble2)
  {
    super(paramDouble1, paramDouble2);
  }

  public Vector2d(Vector2d paramVector2d)
  {
    super(paramVector2d);
  }

  public Vector2d(Tuple2d paramTuple2d)
  {
    super(paramTuple2d);
  }

  public Vector2d()
  {
  }

  public final double length()
  {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     javax.vecmath.Vector2d
 * JD-Core Version:    0.6.2
 */