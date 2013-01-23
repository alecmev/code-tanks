package net.phys2d.raw.collide;

import net.phys2d.math.MathUtil;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.AABox;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Shape;

public class BoxCircleCollider
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
    Box localBox = (Box)paramBody1.getShape();
    Circle localCircle = (Circle)paramBody2.getShape();
    Vector2f[] arrayOfVector2f = localBox.getPoints(paramBody1.getPosition(), paramBody1.getRotation());
    Line[] arrayOfLine = new Line[4];
    arrayOfLine[0] = new Line(arrayOfVector2f[0], arrayOfVector2f[1]);
    arrayOfLine[1] = new Line(arrayOfVector2f[1], arrayOfVector2f[2]);
    arrayOfLine[2] = new Line(arrayOfVector2f[2], arrayOfVector2f[3]);
    arrayOfLine[3] = new Line(arrayOfVector2f[3], arrayOfVector2f[0]);
    float f5 = localCircle.getRadius() * localCircle.getRadius();
    int i = -1;
    float f6 = 3.4028235E+38F;
    for (int j = 0; j < 4; j++)
    {
      float f8 = arrayOfLine[j].distanceSquared(paramBody2.getPosition());
      if ((f8 < f5) && (f6 > f8))
      {
        f6 = f8;
        i = j;
      }
    }
    if (i > -1)
    {
      float f7 = (float)Math.sqrt(f6);
      paramArrayOfContact[0].setSeparation(f7 - localCircle.getRadius());
      Vector2f localVector2f1 = new Vector2f();
      arrayOfLine[i].getClosestPoint(paramBody2.getPosition(), localVector2f1);
      Vector2f localVector2f2 = MathUtil.sub(paramBody2.getPosition(), localVector2f1);
      localVector2f2.normalise();
      paramArrayOfContact[0].setNormal(localVector2f2);
      paramArrayOfContact[0].setPosition(localVector2f1);
      paramArrayOfContact[0].setFeature(new FeaturePair());
      return 1;
    }
    return 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.BoxCircleCollider
 * JD-Core Version:    0.6.2
 */