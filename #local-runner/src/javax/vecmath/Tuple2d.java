package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2d
  implements Serializable, Cloneable
{
  public double x;
  public double y;

  public Tuple2d(double paramDouble1, double paramDouble2)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
  }

  public Tuple2d(Tuple2d paramTuple2d)
  {
    this.x = paramTuple2d.x;
    this.y = paramTuple2d.y;
  }

  public Tuple2d()
  {
    this.x = 0.0D;
    this.y = 0.0D;
  }

  public final void add(Tuple2d paramTuple2d)
  {
    this.x += paramTuple2d.x;
    this.y += paramTuple2d.y;
  }

  public final void scale(double paramDouble)
  {
    this.x *= paramDouble;
    this.y *= paramDouble;
  }

  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + Double.doubleToLongBits(this.x);
    l = 31L * l + Double.doubleToLongBits(this.y);
    return (int)(l ^ l >> 32);
  }

  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple2d localTuple2d = (Tuple2d)paramObject;
      return (this.x == localTuple2d.x) && (this.y == localTuple2d.y);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return false;
  }

  public String toString()
  {
    return "(" + this.x + ", " + this.y + ")";
  }

  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    throw new InternalError();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     javax.vecmath.Tuple2d
 * JD-Core Version:    0.6.2
 */