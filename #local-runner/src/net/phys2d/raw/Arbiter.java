package net.phys2d.raw;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.collide.FeaturePair;

public class Arbiter
{
  private Contact[] contacts = new Contact[10];
  private int numContacts;
  private Body body1;
  private Body body2;
  private float friction;

  strictfp Arbiter(Body paramBody1, Body paramBody2)
  {
    for (int i = 0; i < 10; i++)
      this.contacts[i] = new Contact();
    if ((!(paramBody2 instanceof StaticBody)) && (paramBody1.hashCode() < paramBody2.hashCode()))
    {
      this.body1 = paramBody1;
      this.body2 = paramBody2;
    }
    else
    {
      this.body1 = paramBody2;
      this.body2 = paramBody1;
    }
  }

  public strictfp boolean hasRestingPair()
  {
    return (this.body1.isResting()) && (this.body2.isResting());
  }

  public strictfp void collide(float paramFloat)
  {
    this.numContacts = Collide.collide(this.contacts, this.body1, this.body2, paramFloat);
  }

  strictfp Contact getContact(int paramInt)
  {
    return this.contacts[paramInt];
  }

  public strictfp void init()
  {
    if (this.numContacts > 0)
      this.friction = ((float)Math.sqrt(this.body1.getFriction() * this.body2.getFriction()));
  }

  public strictfp Contact[] getContacts()
  {
    return this.contacts;
  }

  public strictfp int getNumContacts()
  {
    return this.numContacts;
  }

  public strictfp Body getBody1()
  {
    return this.body1;
  }

  public strictfp Body getBody2()
  {
    return this.body2;
  }

  strictfp void update(Contact[] paramArrayOfContact, int paramInt)
  {
    Contact[] arrayOfContact = new Contact[10];
    for (int i = 0; i < arrayOfContact.length; i++)
      arrayOfContact[i] = new Contact();
    for (i = 0; i < paramInt; i++)
    {
      Contact localContact1 = paramArrayOfContact[i];
      int j = -1;
      Contact localContact3;
      for (int k = 0; k < this.numContacts; k++)
      {
        localContact3 = this.contacts[k];
        if (localContact1.feature.equals(localContact3.feature))
        {
          j = k;
          break;
        }
      }
      if (j > -1)
      {
        Contact localContact2 = arrayOfContact[i];
        localContact3 = this.contacts[j];
        localContact2.set(localContact1);
        localContact2.accumulatedNormalImpulse = localContact3.accumulatedNormalImpulse;
        localContact2.accumulatedTangentImpulse = localContact3.accumulatedTangentImpulse;
      }
      else
      {
        arrayOfContact[i].set(paramArrayOfContact[i]);
      }
    }
    for (i = 0; i < paramInt; i++)
      this.contacts[i].set(arrayOfContact[i]);
    this.numContacts = paramInt;
  }

  public strictfp boolean concerns(Body paramBody)
  {
    boolean bool = (this.body1 == paramBody) || (this.body2 == paramBody);
    return bool;
  }

  strictfp void preStep(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f1 = 0.01F;
    float f2 = 0.8F;
    for (int i = 0; i < this.numContacts; i++)
    {
      Contact localContact = this.contacts[i];
      localContact.normal.normalise();
      Vector2f localVector2f1 = new Vector2f(localContact.position);
      localVector2f1.sub(this.body1.getPosition());
      Vector2f localVector2f2 = new Vector2f(localContact.position);
      localVector2f2.sub(this.body2.getPosition());
      float f3 = localVector2f1.dot(localContact.normal);
      float f4 = localVector2f2.dot(localContact.normal);
      float f5 = this.body1.getInvMass() + this.body2.getInvMass();
      f5 += this.body1.getInvI() * (localVector2f1.dot(localVector2f1) - f3 * f3) + this.body2.getInvI() * (localVector2f2.dot(localVector2f2) - f4 * f4);
      localContact.massNormal = (paramFloat3 / f5);
      Vector2f localVector2f3 = MathUtil.cross(localContact.normal, 1.0F);
      float f6 = localVector2f1.dot(localVector2f3);
      float f7 = localVector2f2.dot(localVector2f3);
      float f8 = this.body1.getInvMass() + this.body2.getInvMass();
      f8 += this.body1.getInvI() * (localVector2f1.dot(localVector2f1) - f6 * f6) + this.body2.getInvI() * (localVector2f2.dot(localVector2f2) - f7 * f7);
      localContact.massTangent = (paramFloat3 / f8);
      Vector2f localVector2f4 = new Vector2f(this.body2.getVelocity());
      localVector2f4.add(MathUtil.cross(localVector2f2, this.body2.getAngularVelocity()));
      localVector2f4.sub(this.body1.getVelocity());
      localVector2f4.sub(MathUtil.cross(localVector2f1, this.body1.getAngularVelocity()));
      float f9 = this.body1.getRestitution() * this.body2.getRestitution();
      float f10 = localContact.normal.dot(localVector2f4);
      localContact.restitution = (f9 * -f10);
      localContact.restitution = Math.max(localContact.restitution, 0.0F);
      float f11 = -localContact.separation / paramFloat2;
      if (localContact.restitution >= f11)
        localContact.bias = 0.0F;
      else
        localContact.bias = (-f2 * paramFloat1 * Math.min(0.0F, localContact.separation + f1));
      localContact.accumulatedNormalImpulse *= paramFloat3;
      Vector2f localVector2f5 = MathUtil.scale(localContact.normal, localContact.accumulatedNormalImpulse);
      localVector2f5.add(MathUtil.scale(localVector2f3, localContact.accumulatedTangentImpulse));
      this.body1.adjustVelocity(MathUtil.scale(localVector2f5, -this.body1.getInvMass()));
      this.body1.adjustAngularVelocity(-this.body1.getInvI() * MathUtil.cross(localVector2f1, localVector2f5));
      this.body2.adjustVelocity(MathUtil.scale(localVector2f5, this.body2.getInvMass()));
      this.body2.adjustAngularVelocity(this.body2.getInvI() * MathUtil.cross(localVector2f2, localVector2f5));
      localContact.biasImpulse = 0.0F;
    }
  }

  strictfp void applyImpulse()
  {
    Body localBody1 = this.body1;
    Body localBody2 = this.body2;
    for (int i = 0; i < this.numContacts; i++)
    {
      Contact localContact = this.contacts[i];
      Vector2f localVector2f1 = new Vector2f(localContact.position);
      localVector2f1.sub(localBody1.getPosition());
      Vector2f localVector2f2 = new Vector2f(localContact.position);
      localVector2f2.sub(localBody2.getPosition());
      Vector2f localVector2f3 = new Vector2f(localBody2.getVelocity());
      localVector2f3.add(MathUtil.cross(localBody2.getAngularVelocity(), localVector2f2));
      localVector2f3.sub(localBody1.getVelocity());
      localVector2f3.sub(MathUtil.cross(localBody1.getAngularVelocity(), localVector2f1));
      float f1 = localVector2f3.dot(localContact.normal);
      float f2 = localContact.massNormal * (localContact.restitution - f1);
      float f3 = localContact.accumulatedNormalImpulse;
      localContact.accumulatedNormalImpulse = Math.max(f3 + f2, 0.0F);
      f2 = localContact.accumulatedNormalImpulse - f3;
      Vector2f localVector2f4 = MathUtil.scale(localContact.normal, f2);
      localBody1.adjustVelocity(MathUtil.scale(localVector2f4, -localBody1.getInvMass()));
      localBody1.adjustAngularVelocity(-(localBody1.getInvI() * MathUtil.cross(localVector2f1, localVector2f4)));
      localBody2.adjustVelocity(MathUtil.scale(localVector2f4, localBody2.getInvMass()));
      localBody2.adjustAngularVelocity(localBody2.getInvI() * MathUtil.cross(localVector2f2, localVector2f4));
      localVector2f3.set(localBody2.getBiasedVelocity());
      localVector2f3.add(MathUtil.cross(localBody2.getBiasedAngularVelocity(), localVector2f2));
      localVector2f3.sub(localBody1.getBiasedVelocity());
      localVector2f3.sub(MathUtil.cross(localBody1.getBiasedAngularVelocity(), localVector2f1));
      float f4 = localVector2f3.dot(localContact.normal);
      float f5 = localContact.massNormal * (-f4 + localContact.bias);
      float f6 = localContact.biasImpulse;
      localContact.biasImpulse = Math.max(f6 + f5, 0.0F);
      f5 = localContact.biasImpulse - f6;
      Vector2f localVector2f5 = MathUtil.scale(localContact.normal, f5);
      localBody1.adjustBiasedVelocity(MathUtil.scale(localVector2f5, -localBody1.getInvMass()));
      localBody1.adjustBiasedAngularVelocity(-(localBody1.getInvI() * MathUtil.cross(localVector2f1, localVector2f5)));
      localBody2.adjustBiasedVelocity(MathUtil.scale(localVector2f5, localBody2.getInvMass()));
      localBody2.adjustBiasedAngularVelocity(localBody2.getInvI() * MathUtil.cross(localVector2f2, localVector2f5));
      float f7 = this.friction * localContact.accumulatedNormalImpulse;
      localVector2f3.set(localBody2.getVelocity());
      localVector2f3.add(MathUtil.cross(localBody2.getAngularVelocity(), localVector2f2));
      localVector2f3.sub(localBody1.getVelocity());
      localVector2f3.sub(MathUtil.cross(localBody1.getAngularVelocity(), localVector2f1));
      Vector2f localVector2f6 = MathUtil.cross(localContact.normal, 1.0F);
      float f8 = localVector2f3.dot(localVector2f6);
      float f9 = localContact.massTangent * -f8;
      float f10 = localContact.accumulatedTangentImpulse;
      localContact.accumulatedTangentImpulse = MathUtil.clamp(f10 + f9, -f7, f7);
      f9 = localContact.accumulatedTangentImpulse - f10;
      localVector2f4 = MathUtil.scale(localVector2f6, f9);
      localBody1.adjustVelocity(MathUtil.scale(localVector2f4, -localBody1.getInvMass()));
      localBody1.adjustAngularVelocity(-localBody1.getInvI() * MathUtil.cross(localVector2f1, localVector2f4));
      localBody2.adjustVelocity(MathUtil.scale(localVector2f4, localBody2.getInvMass()));
      localBody2.adjustAngularVelocity(localBody2.getInvI() * MathUtil.cross(localVector2f2, localVector2f4));
    }
  }

  public strictfp int hashCode()
  {
    return this.body1.hashCode() + this.body2.hashCode();
  }

  public strictfp boolean equals(Object paramObject)
  {
    if (paramObject.getClass().equals(getClass()))
    {
      Arbiter localArbiter = (Arbiter)paramObject;
      return (localArbiter.body1.equals(this.body1)) && (localArbiter.body2.equals(this.body2));
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.Arbiter
 * JD-Core Version:    0.6.2
 */