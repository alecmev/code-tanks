package net.phys2d.raw.collide;

import net.phys2d.raw.shapes.Shape;

public class ColliderUnavailableException extends Exception
{
  public ColliderUnavailableException(Shape paramShape1, Shape paramShape2)
  {
    super("No collider available for shapes of type " + paramShape1.getClass().getName() + " and " + paramShape2.getClass().getName());
  }

  protected ColliderUnavailableException()
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.ColliderUnavailableException
 * JD-Core Version:    0.6.2
 */