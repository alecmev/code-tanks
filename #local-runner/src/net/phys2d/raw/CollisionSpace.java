package net.phys2d.raw;

import java.util.ArrayList;
import net.phys2d.math.ROVector2f;
import net.phys2d.raw.shapes.AABox;
import net.phys2d.raw.shapes.Shape;

public class CollisionSpace
  implements CollisionContext
{
  protected BodyList bodies = new BodyList();
  protected ArbiterList arbiters = new ArbiterList();
  protected BroadCollisionStrategy collisionStrategy;
  protected ArrayList listeners = new ArrayList();
  protected float totalTime;
  private long bitmask = -1L;

  public CollisionSpace(BroadCollisionStrategy paramBroadCollisionStrategy)
  {
    this.collisionStrategy = paramBroadCollisionStrategy;
  }

  public void addListener(CollisionListener paramCollisionListener)
  {
    this.listeners.add(paramCollisionListener);
  }

  public void collide(float paramFloat)
  {
    this.totalTime += paramFloat;
    this.collisionStrategy.collideBodies(this, this.bodies, paramFloat);
  }

  public void add(Body paramBody)
  {
    paramBody.setAdded(true);
    this.bodies.add(paramBody);
  }

  public void remove(Body paramBody)
  {
    paramBody.setAdded(false);
    this.bodies.remove(paramBody);
  }

  private void notifyCollision(Body paramBody1, Body paramBody2, ROVector2f paramROVector2f1, ROVector2f paramROVector2f2, float paramFloat)
  {
    if (this.listeners.size() == 0)
      return;
    CollisionEvent localCollisionEvent = new CollisionEvent(this.totalTime, paramBody1, paramBody2, paramROVector2f1, paramROVector2f2, paramFloat);
    for (int i = 0; i < this.listeners.size(); i++)
      ((CollisionListener)this.listeners.get(i)).collisionOccured(localCollisionEvent);
  }

  public void resolve(BodyList paramBodyList, float paramFloat)
  {
    for (int i = 0; i < paramBodyList.size(); i++)
    {
      Body localBody1 = paramBodyList.get(i);
      if (!localBody1.disabled())
        for (int j = i + 1; j < paramBodyList.size(); j++)
        {
          Body localBody2 = paramBodyList.get(j);
          if ((!localBody2.disabled()) && ((localBody1.getBitmask() & localBody2.getBitmask()) == 0L) && (!localBody1.getExcludedList().contains(localBody2)) && ((localBody1.getInvMass() != 0.0F) || (localBody2.getInvMass() != 0.0F)))
            if (!localBody1.getShape().getBounds().touches(localBody1.getPosition().getX(), localBody1.getPosition().getY(), localBody2.getShape().getBounds(), localBody2.getPosition().getX(), localBody2.getPosition().getY()))
            {
              this.arbiters.remove(new Arbiter(localBody1, localBody2));
            }
            else
            {
              Arbiter localArbiter1 = new Arbiter(localBody1, localBody2);
              localArbiter1.collide(paramFloat);
              if (localArbiter1.getNumContacts() > 0)
              {
                localBody1.collided(localBody2);
                localBody2.collided(localBody1);
                if (this.arbiters.contains(localArbiter1))
                {
                  int k = this.arbiters.indexOf(localArbiter1);
                  Arbiter localArbiter2 = this.arbiters.get(k);
                  localArbiter2.update(localArbiter1.getContacts(), localArbiter1.getNumContacts());
                }
                else
                {
                  Contact localContact = localArbiter1.getContact(0);
                  notifyCollision(localBody1, localBody2, localContact.getPosition(), localContact.getNormal(), localContact.getSeparation());
                  this.arbiters.add(localArbiter1);
                  localArbiter1.init();
                }
              }
              else
              {
                this.arbiters.remove(localArbiter1);
              }
            }
        }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.CollisionSpace
 * JD-Core Version:    0.6.2
 */