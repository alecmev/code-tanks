package net.phys2d.raw;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.collide.FeaturePair;

public class Contact
{
  Vector2f position = new Vector2f();
  Vector2f normal = new Vector2f();
  float separation;
  float accumulatedNormalImpulse = 0.0F;
  float accumulatedTangentImpulse = 0.0F;
  float massNormal;
  float massTangent;
  float bias;
  FeaturePair feature = new FeaturePair();
  float restitution;
  float biasImpulse;

  public strictfp ROVector2f getPosition()
  {
    return this.position;
  }

  strictfp void set(Contact paramContact)
  {
    this.position.set(paramContact.position);
    this.normal.set(paramContact.normal);
    this.separation = paramContact.separation;
    this.accumulatedNormalImpulse = paramContact.accumulatedNormalImpulse;
    this.accumulatedTangentImpulse = paramContact.accumulatedTangentImpulse;
    this.massNormal = paramContact.massNormal;
    this.massTangent = paramContact.massTangent;
    this.bias = paramContact.bias;
    this.restitution = paramContact.restitution;
    this.feature.set(paramContact.feature);
  }

  public strictfp float getSeparation()
  {
    return this.separation;
  }

  public strictfp ROVector2f getNormal()
  {
    return this.normal;
  }

  public strictfp void setNormal(ROVector2f paramROVector2f)
  {
    this.normal.set(paramROVector2f);
  }

  public strictfp void setPosition(ROVector2f paramROVector2f)
  {
    this.position.set(paramROVector2f);
  }

  public strictfp FeaturePair getFeature()
  {
    return this.feature;
  }

  public strictfp void setFeature(FeaturePair paramFeaturePair)
  {
    this.feature = paramFeaturePair;
  }

  public strictfp void setSeparation(float paramFloat)
  {
    this.separation = paramFloat;
  }

  public strictfp int hashCode()
  {
    return this.feature.hashCode();
  }

  public strictfp boolean equals(Object paramObject)
  {
    if (paramObject.getClass() == getClass())
      return ((Contact)paramObject).feature.equals(this.feature);
    return false;
  }

  public strictfp String toString()
  {
    return "[Contact " + this.position + " n: " + this.normal + " sep: " + this.separation + "]";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.Contact
 * JD-Core Version:    0.6.2
 */