package net.phys2d.raw.collide;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;

public class SwapCollider
  implements Collider
{
  private Collider collider;

  public SwapCollider(Collider paramCollider)
  {
    this.collider = paramCollider;
  }

  public int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    int i = this.collider.collide(paramArrayOfContact, paramBody2, paramBody1);
    for (int j = 0; j < i; j++)
    {
      Vector2f localVector2f = MathUtil.scale(paramArrayOfContact[j].getNormal(), -1.0F);
      paramArrayOfContact[j].setNormal(localVector2f);
    }
    return i;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.SwapCollider
 * JD-Core Version:    0.6.2
 */