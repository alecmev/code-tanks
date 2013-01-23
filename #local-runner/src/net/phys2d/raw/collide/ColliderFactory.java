package net.phys2d.raw.collide;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Polygon;
import net.phys2d.raw.shapes.Shape;

public class ColliderFactory
{
  public Collider createCollider(Body paramBody1, Body paramBody2)
    throws ColliderUnavailableException
  {
    Shape localShape1 = paramBody1.getShape();
    Shape localShape2 = paramBody2.getShape();
    if ((localShape1 instanceof Circle))
      return createColliderFor((Circle)localShape1, localShape2);
    if ((localShape1 instanceof Box))
      return createColliderFor((Box)localShape1, localShape2);
    if ((localShape1 instanceof Line))
      return createColliderFor((Line)localShape1, localShape2);
    if ((localShape1 instanceof Polygon))
      return createColliderFor((Polygon)localShape1, localShape2);
    throw new ColliderUnavailableException(localShape1, localShape2);
  }

  public Collider createColliderFor(Circle paramCircle, Shape paramShape)
    throws ColliderUnavailableException
  {
    if ((paramShape instanceof Circle))
      return new CircleCircleCollider();
    if ((paramShape instanceof Box))
      return new SwapCollider(new BoxCircleCollider());
    if ((paramShape instanceof Line))
      return new SwapCollider(new LineCircleCollider());
    if ((paramShape instanceof Polygon))
      return new SwapCollider(new PolygonCircleCollider());
    throw new ColliderUnavailableException(paramCircle, paramShape);
  }

  public Collider createColliderFor(Box paramBox, Shape paramShape)
    throws ColliderUnavailableException
  {
    if ((paramShape instanceof Circle))
      return new BoxCircleCollider();
    if ((paramShape instanceof Box))
      return new BoxBoxCollider();
    if ((paramShape instanceof Line))
      return new SwapCollider(new LineBoxCollider());
    if ((paramShape instanceof Polygon))
      return new SwapCollider(new PolygonBoxCollider());
    throw new ColliderUnavailableException(paramBox, paramShape);
  }

  public Collider createColliderFor(Line paramLine, Shape paramShape)
    throws ColliderUnavailableException
  {
    if ((paramShape instanceof Circle))
      return new LineCircleCollider();
    if ((paramShape instanceof Box))
      return new LineBoxCollider();
    if ((paramShape instanceof Line))
      return new LineLineCollider();
    if ((paramShape instanceof Polygon))
      return new LinePolygonCollider();
    throw new ColliderUnavailableException(paramLine, paramShape);
  }

  public Collider createColliderFor(Polygon paramPolygon, Shape paramShape)
    throws ColliderUnavailableException
  {
    if ((paramShape instanceof Circle))
      return new PolygonCircleCollider();
    if ((paramShape instanceof Box))
      return new PolygonBoxCollider();
    if ((paramShape instanceof Line))
      return new SwapCollider(new LinePolygonCollider());
    if ((paramShape instanceof Polygon))
      return new PolygonPolygonCollider();
    throw new ColliderUnavailableException(paramPolygon, paramShape);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.ColliderFactory
 * JD-Core Version:    0.6.2
 */