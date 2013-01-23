package net.phys2d.raw;

import java.io.PrintStream;
import net.phys2d.raw.collide.Collider;
import net.phys2d.raw.collide.ColliderFactory;
import net.phys2d.raw.collide.ColliderUnavailableException;

public class Collide
{
  private static ColliderFactory collFactory = new ColliderFactory();

  public static strictfp int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2, float paramFloat)
  {
    Collider localCollider;
    try
    {
      localCollider = collFactory.createCollider(paramBody1, paramBody2);
    }
    catch (ColliderUnavailableException localColliderUnavailableException)
    {
      System.out.println(localColliderUnavailableException.getMessage() + "\n Ignoring any possible collision between the bodies in question");
      return 0;
    }
    return localCollider.collide(paramArrayOfContact, paramBody1, paramBody2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.Collide
 * JD-Core Version:    0.6.2
 */