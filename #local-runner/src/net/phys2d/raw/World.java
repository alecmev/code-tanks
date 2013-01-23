package net.phys2d.raw;

import java.util.ArrayList;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.forcesource.ForceSource;
import net.phys2d.raw.strategies.BruteCollisionStrategy;

public class World extends CollisionSpace
{
  private JointList joints = new JointList();
  private Vector2f gravity = new Vector2f(0.0F, 0.0F);
  private int iterations;
  private float damping = 1.0F;
  private boolean restingBodyDetection = false;
  private float hitTolerance;
  private float rotationTolerance;
  private float positionTolerance;
  private ArrayList sources = new ArrayList();

  public strictfp World(Vector2f paramVector2f, int paramInt)
  {
    this(paramVector2f, paramInt, new BruteCollisionStrategy());
  }

  public strictfp World(Vector2f paramVector2f, int paramInt, BroadCollisionStrategy paramBroadCollisionStrategy)
  {
    super(paramBroadCollisionStrategy);
    this.gravity = paramVector2f;
    this.iterations = paramInt;
  }

  public strictfp void clearArbiters(Body paramBody)
  {
    for (int i = 0; i < this.arbiters.size(); i++)
      if (this.arbiters.get(i).concerns(paramBody))
      {
        this.arbiters.remove(this.arbiters.get(i));
        i--;
      }
  }

  public strictfp void step()
  {
    step(0.01666667F);
  }

  protected strictfp BodyList getActiveBodies()
  {
    return this.bodies;
  }

  protected strictfp JointList getActiveJoints()
  {
    return this.joints;
  }

  public strictfp void step(float paramFloat)
  {
    for (int i = 0; i < this.bodies.size(); i++)
      for (int j = 0; j < this.sources.size(); j++)
        ((ForceSource)this.sources.get(j)).apply(this.bodies.get(i), paramFloat);
    BodyList localBodyList = getActiveBodies();
    JointList localJointList = getActiveJoints();
    float f = paramFloat > 0.0F ? 1.0F / paramFloat : 0.0F;
    Object localObject1;
    if (this.restingBodyDetection)
    {
      for (k = 0; k < localBodyList.size(); k++)
      {
        localObject1 = localBodyList.get(k);
        ((Body)localObject1).startFrame();
      }
      for (k = 0; k < localJointList.size(); k++)
      {
        localObject1 = localJointList.get(k);
        ((Joint)localObject1).getBody1().setIsResting(false);
        ((Joint)localObject1).getBody2().setIsResting(false);
      }
    }
    broadPhase(paramFloat);
    Object localObject2;
    for (int k = 0; k < localBodyList.size(); k++)
    {
      localObject1 = localBodyList.get(k);
      if ((((Body)localObject1).getInvMass() != 0.0F) && ((!((Body)localObject1).isResting()) || (!this.restingBodyDetection)))
      {
        localObject2 = new Vector2f(((Body)localObject1).getForce());
        ((Vector2f)localObject2).scale(((Body)localObject1).getInvMass());
        if (((Body)localObject1).getGravityEffected())
          ((Vector2f)localObject2).add(this.gravity);
        ((Vector2f)localObject2).scale(paramFloat);
        ((Body)localObject1).adjustVelocity((Vector2f)localObject2);
        Vector2f localVector2f = new Vector2f(((Body)localObject1).getVelocity());
        localVector2f.scale(-((Body)localObject1).getDamping() * ((Body)localObject1).getInvMass());
        ((Body)localObject1).adjustVelocity(localVector2f);
        ((Body)localObject1).adjustAngularVelocity(paramFloat * ((Body)localObject1).getInvI() * ((Body)localObject1).getTorque());
        ((Body)localObject1).adjustAngularVelocity(-((Body)localObject1).getAngularVelocity() * ((Body)localObject1).getInvI() * ((Body)localObject1).getRotDamping());
      }
    }
    for (k = 0; k < this.arbiters.size(); k++)
    {
      localObject1 = this.arbiters.get(k);
      if ((!this.restingBodyDetection) || (!((Arbiter)localObject1).hasRestingPair()))
        ((Arbiter)localObject1).preStep(f, paramFloat, this.damping);
    }
    for (k = 0; k < localJointList.size(); k++)
    {
      localObject1 = localJointList.get(k);
      ((Joint)localObject1).preStep(f);
    }
    for (k = 0; k < this.iterations; k++)
    {
      for (int m = 0; m < this.arbiters.size(); m++)
      {
        localObject2 = this.arbiters.get(m);
        if ((!this.restingBodyDetection) || (!((Arbiter)localObject2).hasRestingPair()))
        {
          ((Arbiter)localObject2).applyImpulse();
        }
        else
        {
          ((Arbiter)localObject2).getBody1().collided(((Arbiter)localObject2).getBody2());
          ((Arbiter)localObject2).getBody2().collided(((Arbiter)localObject2).getBody1());
        }
      }
      for (m = 0; m < localJointList.size(); m++)
      {
        localObject2 = localJointList.get(m);
        ((Joint)localObject2).applyImpulse();
      }
    }
    Body localBody;
    for (k = 0; k < localBodyList.size(); k++)
    {
      localBody = localBodyList.get(k);
      if ((localBody.getInvMass() != 0.0F) && ((!this.restingBodyDetection) || (!localBody.isResting())))
      {
        localBody.adjustPosition(localBody.getVelocity(), paramFloat);
        localBody.adjustPosition(localBody.getBiasedVelocity(), paramFloat);
        localBody.adjustRotation(paramFloat * localBody.getAngularVelocity());
        localBody.adjustRotation(paramFloat * localBody.getBiasedAngularVelocity());
        localBody.resetBias();
        localBody.setForce(0.0F, 0.0F);
        localBody.setTorque(0.0F);
      }
    }
    if (this.restingBodyDetection)
      for (k = 0; k < localBodyList.size(); k++)
      {
        localBody = localBodyList.get(k);
        localBody.endFrame();
      }
    cleanUpArbiters();
  }

  private strictfp void cleanUpArbiters()
  {
    for (int i = 0; i < this.arbiters.size(); i++)
    {
      Arbiter localArbiter = this.arbiters.get(i);
      if ((!localArbiter.getBody1().added()) || (!localArbiter.getBody2().added()))
      {
        this.arbiters.remove(localArbiter);
        i--;
      }
    }
  }

  strictfp void broadPhase(float paramFloat)
  {
    collide(paramFloat);
  }

  public strictfp void add(Body paramBody)
  {
    paramBody.configureRestingBodyDetection(this.hitTolerance, this.rotationTolerance, this.positionTolerance);
    super.add(paramBody);
  }

  public strictfp void remove(Body paramBody)
  {
    clearArbiters(paramBody);
    super.remove(paramBody);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.World
 * JD-Core Version:    0.6.2
 */