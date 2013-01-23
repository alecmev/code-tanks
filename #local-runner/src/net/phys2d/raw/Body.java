package net.phys2d.raw;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Shape;

public class Body
{
  private static final AtomicInteger idGenerator = new AtomicInteger();
  private final Vector2f position = new Vector2f();
  private final Vector2f lastPosition = new Vector2f();
  private float rotation;
  private final Vector2f velocity = new Vector2f();
  private float angularVelocity;
  private final Vector2f lastVelocity = new Vector2f();
  private float lastAngularVelocity;
  private final Vector2f biasedVelocity = new Vector2f();
  private float biasedAngularVelocity;
  private final Vector2f force = new Vector2f();
  private float torque;
  private Shape shape;
  private float surfaceFriction;
  private float damping;
  private float rotDamping;
  private float mass;
  private float invMass;
  private float I;
  private float invI;
  private final String name;
  private final int id;
  private float restitution;
  private final BodyList excluded = new BodyList();
  private boolean gravity = true;
  private long bitmask;
  private Vector2f oldPosition;
  private Vector2f newPosition;
  private boolean hitByAnother;
  private boolean isResting;
  private final float originalMass;
  private int hitCount;
  private boolean restingBodyDetection;
  private float hitTolerance;
  private float rotationTolerance;
  private float positionTolerance;
  private final BodyList touching = new BodyList();
  private boolean touchingStatic;
  private int touchingCount;
  private boolean canRest = true;
  private boolean rotatable = true;
  private boolean moveable = true;
  private boolean enabled = true;
  private boolean added;
  private Vector2f maxVelocity;

  public strictfp boolean disabled()
  {
    return !this.enabled;
  }

  public strictfp Body(String paramString, DynamicShape paramDynamicShape, float paramFloat)
  {
    this(paramString, paramDynamicShape, paramFloat);
  }

  protected strictfp Body(String paramString, Shape paramShape, float paramFloat)
  {
    this.name = paramString;
    this.id = idGenerator.incrementAndGet();
    this.position.set(0.0F, 0.0F);
    this.lastPosition.set(0.0F, 0.0F);
    this.rotation = 0.0F;
    this.velocity.set(0.0F, 0.0F);
    this.angularVelocity = 0.0F;
    this.force.set(0.0F, 0.0F);
    this.torque = 0.0F;
    this.surfaceFriction = 0.2F;
    this.mass = 3.4028235E+38F;
    this.invMass = 0.0F;
    this.I = 3.4028235E+38F;
    this.invI = 0.0F;
    this.originalMass = paramFloat;
    set(paramShape, paramFloat);
  }

  public strictfp String getName()
  {
    return this.name;
  }

  public strictfp int getID()
  {
    return this.id;
  }

  public strictfp boolean isRotatable()
  {
    if (disabled())
      return false;
    return this.rotatable;
  }

  public strictfp boolean isMoveable()
  {
    if (disabled())
      return false;
    return this.moveable;
  }

  strictfp void configureRestingBodyDetection(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.hitTolerance = paramFloat1;
    this.rotationTolerance = paramFloat2;
    this.positionTolerance = paramFloat3;
    this.restingBodyDetection = true;
  }

  public strictfp boolean canRest()
  {
    return this.canRest;
  }

  strictfp void startFrame()
  {
    if (!canRest())
      return;
    this.oldPosition = new Vector2f(getPosition());
    this.hitByAnother = false;
    this.hitCount = 0;
    this.touching.clear();
  }

  public strictfp void collided(Body paramBody)
  {
    if (!this.restingBodyDetection)
      return;
    if (!this.touching.contains(paramBody))
      this.touching.add(paramBody);
    if ((isResting()) && (!paramBody.isResting()) && (paramBody.getVelocity().lengthSquared() > this.hitTolerance))
    {
      this.hitByAnother = true;
      setMass(this.originalMass);
    }
    this.hitCount += 1;
  }

  public strictfp void endFrame()
  {
    if (!canRest())
      return;
    if ((this.hitCount == 0) || (this.touchingCount != this.touching.size()))
    {
      this.isResting = false;
      setMass(this.originalMass);
      this.touchingStatic = false;
      this.touchingCount = this.touching.size();
    }
    else
    {
      this.newPosition = new Vector2f(getPosition());
      if (this.hitByAnother)
      {
        this.isResting = false;
        setMass(this.originalMass);
      }
      else if ((this.newPosition.distanceSquared(this.oldPosition) <= this.positionTolerance) && (this.velocity.lengthSquared() <= 0.001F) && (this.biasedVelocity.lengthSquared() <= 0.001F) && (Math.abs(this.angularVelocity) <= this.rotationTolerance))
      {
        if (!this.touchingStatic)
          this.touchingStatic = isTouchingStatic(new ArrayList());
        if (this.touchingStatic)
        {
          this.isResting = true;
          setMass(3.4028235E+38F);
          this.velocity.set(0.0F, 0.0F);
          this.biasedVelocity.set(0.0F, 0.0F);
          this.angularVelocity = 0.0F;
          this.biasedAngularVelocity = 0.0F;
          this.force.set(0.0F, 0.0F);
          this.torque = 0.0F;
        }
      }
      if ((this.newPosition.distanceSquared(this.oldPosition) > this.positionTolerance) && (Math.abs(this.angularVelocity) > this.rotationTolerance))
        this.touchingStatic = false;
    }
  }

  public strictfp boolean isTouchingStatic(ArrayList paramArrayList)
  {
    boolean bool = false;
    paramArrayList.add(this);
    for (int i = 0; i < this.touching.size(); i++)
    {
      Body localBody = this.touching.get(i);
      if (!paramArrayList.contains(localBody))
      {
        if (localBody.isStatic())
        {
          bool = true;
          break;
        }
        if (localBody.isTouchingStatic(paramArrayList))
        {
          bool = true;
          break;
        }
      }
    }
    return bool;
  }

  public strictfp boolean isStatic()
  {
    return false;
  }

  public strictfp boolean isResting()
  {
    return this.isResting;
  }

  public strictfp void setIsResting(boolean paramBoolean)
  {
    if ((this.isResting) && (!paramBoolean))
      setMass(this.originalMass);
    this.touchingStatic = false;
    this.isResting = paramBoolean;
  }

  public strictfp boolean getGravityEffected()
  {
    return (this.gravity) || (this.I == 3.4028235E+38F);
  }

  public strictfp BodyList getExcludedList()
  {
    return this.excluded;
  }

  public strictfp float getMass()
  {
    return this.mass;
  }

  public strictfp void setRestitution(float paramFloat)
  {
    this.restitution = paramFloat;
  }

  public strictfp float getRestitution()
  {
    return this.restitution;
  }

  public strictfp void set(Shape paramShape, float paramFloat)
  {
    this.position.set(0.0F, 0.0F);
    this.lastPosition.set(0.0F, 0.0F);
    this.rotation = 0.0F;
    this.velocity.set(0.0F, 0.0F);
    this.angularVelocity = 0.0F;
    this.force.set(0.0F, 0.0F);
    this.torque = 0.0F;
    this.surfaceFriction = 0.2F;
    this.shape = paramShape;
    setMass(paramFloat);
  }

  public strictfp void setShape(Shape paramShape)
  {
    this.shape = paramShape;
  }

  private strictfp void setMass(float paramFloat)
  {
    this.mass = paramFloat;
    if (this.mass < 3.4028235E+38F)
    {
      this.invMass = (1.0F / this.mass);
      this.I = (this.mass * this.shape.getSurfaceFactor() / 12.0F);
      this.invI = (1.0F / this.I);
    }
    else
    {
      this.invMass = 0.0F;
      this.I = 3.4028235E+38F;
      this.invI = 0.0F;
    }
  }

  public strictfp void setFriction(float paramFloat)
  {
    this.surfaceFriction = paramFloat;
  }

  public strictfp void setRotation(float paramFloat)
  {
    this.rotation = paramFloat;
  }

  public strictfp void setDamping(float paramFloat)
  {
    this.damping = paramFloat;
  }

  public strictfp float getDamping()
  {
    return this.damping;
  }

  public strictfp void setRotDamping(float paramFloat)
  {
    this.rotDamping = paramFloat;
  }

  public strictfp float getRotDamping()
  {
    return this.rotDamping;
  }

  public strictfp Shape getShape()
  {
    return this.shape;
  }

  public strictfp void setPosition(float paramFloat1, float paramFloat2)
  {
    this.position.set(paramFloat1, paramFloat2);
    this.lastPosition.set(paramFloat1, paramFloat2);
  }

  public strictfp ROVector2f getPosition()
  {
    return this.position;
  }

  public strictfp float getRotation()
  {
    return this.rotation;
  }

  strictfp float getInvI()
  {
    return this.invI;
  }

  public strictfp void adjustPosition(ROVector2f paramROVector2f, float paramFloat)
  {
    this.lastPosition.set(this.position);
    this.position.x += paramROVector2f.getX() * paramFloat;
    this.position.y += paramROVector2f.getY() * paramFloat;
  }

  public strictfp void adjustRotation(float paramFloat)
  {
    this.rotation += paramFloat;
  }

  public strictfp void setForce(float paramFloat1, float paramFloat2)
  {
    this.force.set(paramFloat1, paramFloat2);
  }

  public strictfp void setTorque(float paramFloat)
  {
    this.torque = paramFloat;
  }

  public strictfp ROVector2f getVelocity()
  {
    return this.velocity;
  }

  public strictfp float getAngularVelocity()
  {
    return this.angularVelocity;
  }

  public strictfp void adjustVelocity(Vector2f paramVector2f)
  {
    if (!isMoveable())
      return;
    this.lastVelocity.set(this.velocity);
    this.velocity.add(paramVector2f);
    validateVelocity();
  }

  public strictfp void adjustAngularVelocity(float paramFloat)
  {
    if (!isRotatable())
      return;
    this.lastAngularVelocity = this.angularVelocity;
    this.angularVelocity += paramFloat;
  }

  public strictfp float getFriction()
  {
    return this.surfaceFriction;
  }

  public strictfp ROVector2f getForce()
  {
    return this.force;
  }

  public strictfp float getTorque()
  {
    return this.torque;
  }

  strictfp float getInvMass()
  {
    return this.invMass;
  }

  public strictfp String toString()
  {
    return "[Body '" + this.name + "' id: " + this.id + " pos: " + this.position + " vel: " + this.velocity + " (" + this.angularVelocity + ")]";
  }

  public strictfp int hashCode()
  {
    return this.id;
  }

  public strictfp boolean equals(Object paramObject)
  {
    return (paramObject.getClass() == getClass()) && (((Body)paramObject).id == this.id);
  }

  public strictfp ROVector2f getBiasedVelocity()
  {
    return this.biasedVelocity;
  }

  public strictfp float getBiasedAngularVelocity()
  {
    return this.biasedAngularVelocity;
  }

  public strictfp void adjustBiasedVelocity(Vector2f paramVector2f)
  {
    if (!isMoveable())
      return;
    this.biasedVelocity.add(paramVector2f);
  }

  public strictfp void adjustBiasedAngularVelocity(float paramFloat)
  {
    if (!isRotatable())
      return;
    this.biasedAngularVelocity += paramFloat;
  }

  public strictfp void resetBias()
  {
    this.biasedVelocity.set(0.0F, 0.0F);
    this.biasedAngularVelocity = 0.0F;
  }

  public strictfp long getBitmask()
  {
    return this.bitmask;
  }

  public strictfp boolean added()
  {
    return this.added;
  }

  public strictfp void setAdded(boolean paramBoolean)
  {
    this.added = paramBoolean;
  }

  protected strictfp void validateVelocity()
  {
    if (this.maxVelocity == null)
      return;
    if (Math.abs(this.velocity.x) > this.maxVelocity.x)
      if (this.velocity.x > 0.0F)
        this.velocity.x = this.maxVelocity.x;
      else
        this.velocity.x = (-this.maxVelocity.x);
    if (Math.abs(this.velocity.y) > this.maxVelocity.y)
      if (this.velocity.y > 0.0F)
        this.velocity.y = this.maxVelocity.y;
      else
        this.velocity.y = (-this.maxVelocity.y);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.Body
 * JD-Core Version:    0.6.2
 */