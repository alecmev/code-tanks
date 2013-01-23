package net.phys2d.raw.strategies;

import net.phys2d.raw.BodyList;
import net.phys2d.raw.BroadCollisionStrategy;
import net.phys2d.raw.CollisionContext;

public class BruteCollisionStrategy
  implements BroadCollisionStrategy
{
  public void collideBodies(CollisionContext paramCollisionContext, BodyList paramBodyList, float paramFloat)
  {
    paramCollisionContext.resolve(paramBodyList, paramFloat);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.strategies.BruteCollisionStrategy
 * JD-Core Version:    0.6.2
 */