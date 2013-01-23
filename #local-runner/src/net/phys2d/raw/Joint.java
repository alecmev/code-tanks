package net.phys2d.raw;

public abstract interface Joint
{
  public abstract Body getBody1();

  public abstract Body getBody2();

  public abstract void applyImpulse();

  public abstract void preStep(float paramFloat);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.Joint
 * JD-Core Version:    0.6.2
 */