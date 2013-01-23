package net.phys2d.raw.collide;

import net.phys2d.math.MathUtil;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.AABox;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Shape;

public class CircleCircleCollider
  implements Collider
{
  public strictfp int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    float f1 = paramBody1.getPosition().getX();
    float f2 = paramBody1.getPosition().getY();
    float f3 = paramBody2.getPosition().getX();
    float f4 = paramBody2.getPosition().getY();
    boolean bool = paramBody1.getShape().getBounds().touches(f1, f2, paramBody2.getShape().getBounds(), f3, f4);
    if (!bool)
      return 0;
    Circle localCircle1 = (Circle)paramBody1.getShape();
    Circle localCircle2 = (Circle)paramBody2.getShape();
    bool = localCircle1.touches(f1, f2, localCircle2, f3, f4);
    if (!bool)
      return 0;
    Vector2f localVector2f1 = MathUtil.sub(paramBody2.getPosition(), paramBody1.getPosition());
    float f5 = localCircle1.getRadius() + localCircle2.getRadius() - localVector2f1.length();
    localVector2f1.normalise();
    Vector2f localVector2f2 = MathUtil.scale(localVector2f1, localCircle1.getRadius());
    localVector2f2.add(paramBody1.getPosition());
    paramArrayOfContact[0].setSeparation(-f5);
    paramArrayOfContact[0].setPosition(localVector2f2);
    paramArrayOfContact[0].setNormal(localVector2f1);
    FeaturePair localFeaturePair = new FeaturePair();
    paramArrayOfContact[0].setFeature(localFeaturePair);
    return 1;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.CircleCircleCollider
 * JD-Core Version:    0.6.2
 */