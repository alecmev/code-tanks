package net.phys2d.raw.collide;

import net.phys2d.math.MathUtil;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Line;

public class LineBoxCollider
  implements Collider
{
  private static LineBoxCollider single = new LineBoxCollider();

  private strictfp float getProp(Vector2f paramVector2f1, Vector2f paramVector2f2)
  {
    if ((paramVector2f2.getX() == 0.0F) && (paramVector2f2.getY() == 0.0F))
      return 0.0F;
    if (paramVector2f2.getX() != 0.0F)
      return paramVector2f1.getX() / paramVector2f2.getX();
    return paramVector2f1.getY() / paramVector2f2.getY();
  }

  public strictfp int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    int i = 0;
    Line localLine1 = (Line)paramBody1.getShape();
    Box localBox = (Box)paramBody2.getShape();
    Vector2f localVector2f1 = new Vector2f(localLine1.getDX(), localLine1.getDY());
    localVector2f1.normalise();
    Vector2f localVector2f2 = new Vector2f(-localLine1.getDY(), localLine1.getDX());
    localVector2f2.normalise();
    Vector2f localVector2f3 = new Vector2f();
    localLine1.getStart().projectOntoUnit(localVector2f2, localVector2f3);
    float f1 = getProp(localVector2f3, localVector2f2);
    Vector2f localVector2f4 = MathUtil.sub(paramBody2.getPosition(), paramBody1.getPosition());
    localVector2f4.projectOntoUnit(localVector2f2, localVector2f3);
    float f2 = getProp(localVector2f3, localVector2f2);
    Vector2f[] arrayOfVector2f = localBox.getPoints(paramBody2.getPosition(), paramBody2.getRotation());
    float[] arrayOfFloat1 = new float[4];
    float[] arrayOfFloat2 = new float[4];
    int j = 0;
    for (int k = 0; k < 4; k++)
    {
      arrayOfVector2f[k].sub(paramBody1.getPosition());
      arrayOfVector2f[k].projectOntoUnit(localVector2f2, localVector2f3);
      arrayOfFloat1[k] = getProp(localVector2f3, localVector2f2);
      arrayOfVector2f[k].projectOntoUnit(localVector2f1, localVector2f3);
      arrayOfFloat2[k] = getProp(localVector2f3, new Vector2f(localLine1.getDX(), localLine1.getDY()));
      if ((arrayOfFloat2[k] >= 1.0F) || (arrayOfFloat2[k] <= 0.0F))
        j++;
    }
    if (j == 4)
      return 0;
    Vector2f localVector2f5 = new Vector2f(localVector2f2);
    int m;
    Vector2f localVector2f6;
    Line localLine2;
    Line localLine3;
    float f3;
    float f4;
    Vector2f localVector2f7;
    if (f2 < f1)
    {
      if (!localLine1.blocksInnerEdge())
        return 0;
      localVector2f5.scale(-1.0F);
      for (m = 0; m < 4; m++)
        if (arrayOfFloat1[m] > f1)
          if (arrayOfFloat2[m] < 0.0F)
          {
            localVector2f6 = new Vector2f();
            localLine2 = new Line(getPt(arrayOfVector2f, m - 1), arrayOfVector2f[m]);
            localLine3 = new Line(getPt(arrayOfVector2f, m + 1), arrayOfVector2f[m]);
            localLine2.getClosestPoint(localLine1.getStart(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f3 = getProp(localVector2f6, localVector2f2);
            localLine3.getClosestPoint(localLine1.getStart(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f4 = getProp(localVector2f6, localVector2f2);
            if ((f3 > 0.0F) && (f4 > 0.0F))
            {
              localVector2f7 = new Vector2f(paramBody1.getPosition());
              localVector2f7.add(localLine1.getStart());
              resolveEndPointCollision(localVector2f7, paramBody1, paramBody2, localVector2f5, localLine2, localLine3, paramArrayOfContact[i], m);
              i++;
            }
          }
          else if (arrayOfFloat2[m] > 1.0F)
          {
            localVector2f6 = new Vector2f();
            localLine2 = new Line(getPt(arrayOfVector2f, m - 1), arrayOfVector2f[m]);
            localLine3 = new Line(getPt(arrayOfVector2f, m + 1), arrayOfVector2f[m]);
            localLine2.getClosestPoint(localLine1.getEnd(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f3 = getProp(localVector2f6, localVector2f2);
            localLine3.getClosestPoint(localLine1.getEnd(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f4 = getProp(localVector2f6, localVector2f2);
            if ((f3 > 0.0F) && (f4 > 0.0F))
            {
              localVector2f7 = new Vector2f(paramBody1.getPosition());
              localVector2f7.add(localLine1.getEnd());
              resolveEndPointCollision(localVector2f7, paramBody1, paramBody2, localVector2f5, localLine2, localLine3, paramArrayOfContact[i], m);
              i++;
            }
          }
          else
          {
            arrayOfVector2f[m].projectOntoUnit(localVector2f1, localVector2f3);
            localVector2f3.add(paramBody1.getPosition());
            paramArrayOfContact[i].setSeparation(-(arrayOfFloat1[m] - f1));
            paramArrayOfContact[i].setPosition(new Vector2f(localVector2f3));
            paramArrayOfContact[i].setNormal(localVector2f5);
            paramArrayOfContact[i].setFeature(new FeaturePair(m));
            i++;
          }
    }
    else
    {
      if (!localLine1.blocksOuterEdge())
        return 0;
      for (m = 0; m < 4; m++)
        if (arrayOfFloat1[m] < f1)
          if (arrayOfFloat2[m] < 0.0F)
          {
            localVector2f6 = new Vector2f();
            localLine2 = new Line(getPt(arrayOfVector2f, m - 1), arrayOfVector2f[m]);
            localLine3 = new Line(getPt(arrayOfVector2f, m + 1), arrayOfVector2f[m]);
            localLine2.getClosestPoint(localLine1.getStart(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f3 = getProp(localVector2f6, localVector2f2);
            localLine3.getClosestPoint(localLine1.getStart(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f4 = getProp(localVector2f6, localVector2f2);
            if ((f3 < 0.0F) && (f4 < 0.0F))
            {
              localVector2f7 = new Vector2f(paramBody1.getPosition());
              localVector2f7.add(localLine1.getStart());
              resolveEndPointCollision(localVector2f7, paramBody1, paramBody2, localVector2f5, localLine2, localLine3, paramArrayOfContact[i], m);
              i++;
            }
          }
          else if (arrayOfFloat2[m] > 1.0F)
          {
            localVector2f6 = new Vector2f();
            localLine2 = new Line(getPt(arrayOfVector2f, m - 1), arrayOfVector2f[m]);
            localLine3 = new Line(getPt(arrayOfVector2f, m + 1), arrayOfVector2f[m]);
            localLine2.getClosestPoint(localLine1.getEnd(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f3 = getProp(localVector2f6, localVector2f2);
            localLine3.getClosestPoint(localLine1.getEnd(), localVector2f3);
            localVector2f3.projectOntoUnit(localVector2f2, localVector2f6);
            f4 = getProp(localVector2f6, localVector2f2);
            if ((f3 < 0.0F) && (f4 < 0.0F))
            {
              localVector2f7 = new Vector2f(paramBody1.getPosition());
              localVector2f7.add(localLine1.getEnd());
              resolveEndPointCollision(localVector2f7, paramBody1, paramBody2, localVector2f5, localLine2, localLine3, paramArrayOfContact[i], m);
              i++;
            }
          }
          else
          {
            arrayOfVector2f[m].projectOntoUnit(localVector2f1, localVector2f3);
            localVector2f3.add(paramBody1.getPosition());
            paramArrayOfContact[i].setSeparation(-(f1 - arrayOfFloat1[m]));
            paramArrayOfContact[i].setPosition(new Vector2f(localVector2f3));
            paramArrayOfContact[i].setNormal(localVector2f5);
            paramArrayOfContact[i].setFeature(new FeaturePair());
            i++;
          }
    }
    if (i > 2)
      throw new RuntimeException("LineBoxCollision: > 2 contacts");
    return i;
  }

  private strictfp void resolveEndPointCollision(Vector2f paramVector2f1, Body paramBody1, Body paramBody2, Vector2f paramVector2f2, Line paramLine1, Line paramLine2, Contact paramContact, int paramInt)
  {
    Vector2f localVector2f1 = new Vector2f(paramVector2f1);
    Vector2f localVector2f2 = new Vector2f(localVector2f1);
    localVector2f2.add(paramVector2f2);
    paramLine2.move(paramBody1.getPosition());
    paramLine1.move(paramBody1.getPosition());
    Line localLine = new Line(localVector2f1, localVector2f2);
    Vector2f localVector2f3 = localLine.intersect(paramLine2);
    Vector2f localVector2f4 = localLine.intersect(paramLine1);
    float f1 = 3.4028235E+38F;
    if (localVector2f3 != null)
      f1 = localVector2f3.distance(localVector2f1) - paramVector2f2.length();
    float f2 = 3.4028235E+38F;
    if (localVector2f4 != null)
      f2 = localVector2f4.distance(localVector2f1) - paramVector2f2.length();
    paramVector2f2.normalise();
    float f3 = Math.min(f1, f2);
    paramContact.setSeparation(-f3);
    paramContact.setPosition(paramVector2f1);
    paramContact.setNormal(paramVector2f2);
    paramContact.setFeature(new FeaturePair(paramInt));
  }

  private strictfp Vector2f getPt(Vector2f[] paramArrayOfVector2f, int paramInt)
  {
    if (paramInt < 0)
      paramInt += paramArrayOfVector2f.length;
    if (paramInt >= paramArrayOfVector2f.length)
      paramInt -= paramArrayOfVector2f.length;
    return paramArrayOfVector2f[paramInt];
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.LineBoxCollider
 * JD-Core Version:    0.6.2
 */