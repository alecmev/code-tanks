package net.phys2d.raw;

import net.phys2d.math.ROVector2f;

public class CollisionEvent
{
  private float time;
  private Body body1;
  private Body body2;
  private ROVector2f point;
  private ROVector2f normal;
  private float depth;

  public CollisionEvent(float paramFloat1, Body paramBody1, Body paramBody2, ROVector2f paramROVector2f1, ROVector2f paramROVector2f2, float paramFloat2)
  {
    this.time = paramFloat1;
    this.body1 = paramBody1;
    this.body2 = paramBody2;
    this.point = paramROVector2f1;
    this.normal = paramROVector2f2;
    this.depth = paramFloat2;
  }

  public Body getBodyA()
  {
    return this.body1;
  }

  public Body getBodyB()
  {
    return this.body2;
  }

  public ROVector2f getNormal()
  {
    return this.normal;
  }

  public ROVector2f getPoint()
  {
    return this.point;
  }

  public String toString()
  {
    return "[Collision \r\n body A: " + this.body1 + "\r\n" + " body B: " + this.body2 + "\r\n" + " contact: " + this.point + "\r\n" + " normal: " + this.normal + "\r\n" + " penetration: " + this.depth + "\r\n";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.CollisionEvent
 * JD-Core Version:    0.6.2
 */