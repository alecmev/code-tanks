package net.phys2d.raw.collide;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Line;

public class LineCircleCollider
  implements Collider
{
  public strictfp int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    Line localLine = (Line)paramBody1.getShape();
    Circle localCircle = (Circle)paramBody2.getShape();
    Vector2f[] arrayOfVector2f = localLine.getVertices(paramBody1.getPosition(), paramBody1.getRotation());
    Vector2f localVector2f1 = arrayOfVector2f[0];
    Vector2f localVector2f2 = arrayOfVector2f[1];
    ROVector2f localROVector2f = paramBody2.getPosition();
    Vector2f localVector2f3 = new Vector2f(localVector2f2);
    localVector2f3.sub(localVector2f1);
    localVector2f3.set(localVector2f3.y, -localVector2f3.x);
    float f1 = localVector2f3.y * (localVector2f2.x - localVector2f1.x);
    f1 -= localVector2f3.x * (localVector2f2.y - localVector2f1.y);
    float f2 = localVector2f3.x * (localVector2f1.y - localROVector2f.getY());
    f2 -= localVector2f3.y * (localVector2f1.x - localROVector2f.getX());
    f2 /= f1;
    Vector2f localVector2f4 = null;
    if (f2 < 0.0F)
      localVector2f4 = localVector2f1;
    else if (f2 > 1.0F)
      localVector2f4 = localVector2f2;
    else
      localVector2f4 = new Vector2f(localVector2f1.x + f2 * (localVector2f2.x - localVector2f1.x), localVector2f1.y + f2 * (localVector2f2.y - localVector2f1.y));
    Vector2f localVector2f5 = localVector2f3;
    localVector2f5.set(localROVector2f);
    localVector2f5.sub(localVector2f4);
    float f3 = localVector2f5.lengthSquared();
    float f4 = localCircle.getRadius() * localCircle.getRadius();
    if (f3 < f4)
    {
      paramArrayOfContact[0].setPosition(localVector2f4);
      paramArrayOfContact[0].setFeature(new FeaturePair());
      localVector2f5.normalise();
      paramArrayOfContact[0].setNormal(localVector2f5);
      float f5 = (float)Math.sqrt(f3) - localCircle.getRadius();
      paramArrayOfContact[0].setSeparation(f5);
      return 1;
    }
    return 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.LineCircleCollider
 * JD-Core Version:    0.6.2
 */