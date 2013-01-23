package net.phys2d.raw.collide;

import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;

public abstract interface Collider
{
  public abstract int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.Collider
 * JD-Core Version:    0.6.2
 */