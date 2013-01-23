package com.a.b.a.a;

import com.a.b.d;
import javax.vecmath.Vector2d;
import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;

class a
  implements CollisionListener
{
  a(b paramb, com.a.b.a parama)
  {
  }

  public void collisionOccured(CollisionEvent paramCollisionEvent)
  {
    this.a.a(new d(b.a(this.b, paramCollisionEvent.getBodyA().getID()), b.a(this.b, paramCollisionEvent.getBodyB().getID()), new Vector2d(paramCollisionEvent.getPoint().getX(), paramCollisionEvent.getPoint().getY()), new Vector2d(paramCollisionEvent.getNormal().getX(), paramCollisionEvent.getNormal().getY())));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.b.a.a.a
 * JD-Core Version:    0.6.2
 */