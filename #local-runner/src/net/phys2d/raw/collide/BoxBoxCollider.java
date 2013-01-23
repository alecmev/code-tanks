package net.phys2d.raw.collide;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Matrix2f;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.AABox;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Shape;

public class BoxBoxCollider
  implements Collider
{
  private static Vector2f hA = new Vector2f();
  private static Vector2f hB = new Vector2f();

  private strictfp void flip(FeaturePair paramFeaturePair)
  {
    int i = paramFeaturePair.inEdge1;
    paramFeaturePair.inEdge1 = paramFeaturePair.inEdge2;
    paramFeaturePair.inEdge2 = i;
    i = paramFeaturePair.outEdge1;
    paramFeaturePair.outEdge1 = paramFeaturePair.outEdge2;
    paramFeaturePair.outEdge2 = i;
  }

  private strictfp int clipSegmentToLine(ClipVertex[] paramArrayOfClipVertex1, ClipVertex[] paramArrayOfClipVertex2, Vector2f paramVector2f, float paramFloat, char paramChar)
  {
    int i = 0;
    float f1 = paramVector2f.dot(paramArrayOfClipVertex2[0].v) - paramFloat;
    float f2 = paramVector2f.dot(paramArrayOfClipVertex2[1].v) - paramFloat;
    if (f1 <= 0.0F)
      paramArrayOfClipVertex1[(i++)] = paramArrayOfClipVertex2[0];
    if (f2 <= 0.0F)
      paramArrayOfClipVertex1[(i++)] = paramArrayOfClipVertex2[1];
    if (f1 * f2 < 0.0F)
    {
      float f3 = f1 / (f1 - f2);
      paramArrayOfClipVertex1[i].v = MathUtil.scale(MathUtil.sub(paramArrayOfClipVertex2[1].v, paramArrayOfClipVertex2[0].v), f3);
      paramArrayOfClipVertex1[i].v.add(paramArrayOfClipVertex2[0].v);
      if (f1 > 0.0F)
      {
        paramArrayOfClipVertex1[i].fp = paramArrayOfClipVertex2[0].fp;
        paramArrayOfClipVertex1[i].fp.inEdge1 = paramChar;
        paramArrayOfClipVertex1[i].fp.inEdge2 = 0;
      }
      else
      {
        paramArrayOfClipVertex1[i].fp = paramArrayOfClipVertex2[1].fp;
        paramArrayOfClipVertex1[i].fp.outEdge1 = paramChar;
        paramArrayOfClipVertex1[i].fp.outEdge2 = 0;
      }
      i++;
    }
    return i;
  }

  private strictfp void computeIncidentEdge(ClipVertex[] paramArrayOfClipVertex, ROVector2f paramROVector2f1, ROVector2f paramROVector2f2, Matrix2f paramMatrix2f, Vector2f paramVector2f)
  {
    Matrix2f localMatrix2f = paramMatrix2f.transpose();
    Vector2f localVector2f1 = MathUtil.scale(MathUtil.mul(localMatrix2f, paramVector2f), -1.0F);
    Vector2f localVector2f2 = MathUtil.abs(localVector2f1);
    if (localVector2f2.x > localVector2f2.y)
    {
      if (MathUtil.sign(localVector2f1.x) > 0.0F)
      {
        paramArrayOfClipVertex[0].v.set(paramROVector2f1.getX(), -paramROVector2f1.getY());
        paramArrayOfClipVertex[0].fp.inEdge2 = 3;
        paramArrayOfClipVertex[0].fp.outEdge2 = 4;
        paramArrayOfClipVertex[1].v.set(paramROVector2f1.getX(), paramROVector2f1.getY());
        paramArrayOfClipVertex[1].fp.inEdge2 = 4;
        paramArrayOfClipVertex[1].fp.outEdge2 = 1;
      }
      else
      {
        paramArrayOfClipVertex[0].v.set(-paramROVector2f1.getX(), paramROVector2f1.getY());
        paramArrayOfClipVertex[0].fp.inEdge2 = 1;
        paramArrayOfClipVertex[0].fp.outEdge2 = 2;
        paramArrayOfClipVertex[1].v.set(-paramROVector2f1.getX(), -paramROVector2f1.getY());
        paramArrayOfClipVertex[1].fp.inEdge2 = 2;
        paramArrayOfClipVertex[1].fp.outEdge2 = 3;
      }
    }
    else if (MathUtil.sign(localVector2f1.y) > 0.0F)
    {
      paramArrayOfClipVertex[0].v.set(paramROVector2f1.getX(), paramROVector2f1.getY());
      paramArrayOfClipVertex[0].fp.inEdge2 = 4;
      paramArrayOfClipVertex[0].fp.outEdge2 = 1;
      paramArrayOfClipVertex[1].v.set(-paramROVector2f1.getX(), paramROVector2f1.getY());
      paramArrayOfClipVertex[1].fp.inEdge2 = 1;
      paramArrayOfClipVertex[1].fp.outEdge2 = 2;
    }
    else
    {
      paramArrayOfClipVertex[0].v.set(-paramROVector2f1.getX(), -paramROVector2f1.getY());
      paramArrayOfClipVertex[0].fp.inEdge2 = 2;
      paramArrayOfClipVertex[0].fp.outEdge2 = 3;
      paramArrayOfClipVertex[1].v.set(paramROVector2f1.getX(), -paramROVector2f1.getY());
      paramArrayOfClipVertex[1].fp.inEdge2 = 3;
      paramArrayOfClipVertex[1].fp.outEdge2 = 4;
    }
    paramArrayOfClipVertex[0].v = MathUtil.mul(paramMatrix2f, paramArrayOfClipVertex[0].v);
    paramArrayOfClipVertex[0].v.add(paramROVector2f2);
    paramArrayOfClipVertex[1].v = MathUtil.mul(paramMatrix2f, paramArrayOfClipVertex[1].v);
    paramArrayOfClipVertex[1].v.add(paramROVector2f2);
  }

  public strictfp int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    float f1 = paramBody1.getPosition().getX();
    float f2 = paramBody1.getPosition().getY();
    float f3 = paramBody2.getPosition().getX();
    float f4 = paramBody2.getPosition().getY();
    boolean bool = paramBody1.getShape().getBounds().touches(f1, f2, paramBody2.getShape().getBounds(), f3, f4);
    if (!bool)
      return 0;
    hA.set(((Box)paramBody1.getShape()).getSize());
    hA.scale(0.5F);
    hB.set(((Box)paramBody2.getShape()).getSize());
    hB.scale(0.5F);
    ROVector2f localROVector2f1 = paramBody1.getPosition();
    ROVector2f localROVector2f2 = paramBody2.getPosition();
    Matrix2f localMatrix2f1 = new Matrix2f(paramBody1.getRotation());
    Matrix2f localMatrix2f2 = new Matrix2f(paramBody2.getRotation());
    Matrix2f localMatrix2f3 = localMatrix2f1.transpose();
    Matrix2f localMatrix2f4 = localMatrix2f2.transpose();
    Vector2f localVector2f1 = MathUtil.sub(localROVector2f2, localROVector2f1);
    Vector2f localVector2f2 = MathUtil.mul(localMatrix2f3, localVector2f1);
    Vector2f localVector2f3 = MathUtil.mul(localMatrix2f4, localVector2f1);
    Matrix2f localMatrix2f5 = MathUtil.mul(localMatrix2f3, localMatrix2f2);
    Matrix2f localMatrix2f6 = MathUtil.abs(localMatrix2f5);
    Matrix2f localMatrix2f7 = localMatrix2f6.transpose();
    Vector2f localVector2f4 = MathUtil.abs(localVector2f2);
    localVector2f4.sub(hA);
    localVector2f4.sub(MathUtil.mul(localMatrix2f6, hB));
    if ((localVector2f4.x > 0.0F) || (localVector2f4.y > 0.0F))
      return 0;
    Vector2f localVector2f5 = MathUtil.abs(localVector2f3);
    localVector2f5.sub(MathUtil.mul(localMatrix2f7, hA));
    localVector2f5.sub(hB);
    if ((localVector2f5.x > 0.0F) || (localVector2f5.y > 0.0F))
      return 0;
    int i = 1;
    float f5 = localVector2f4.x;
    Vector2f localVector2f6 = localVector2f2.x > 0.0F ? localMatrix2f1.col1 : MathUtil.scale(localMatrix2f1.col1, -1.0F);
    if (localVector2f4.y > 1.05F * f5 + 0.01F * hA.y)
    {
      i = 2;
      f5 = localVector2f4.y;
      localVector2f6 = localVector2f2.y > 0.0F ? localMatrix2f1.col2 : MathUtil.scale(localMatrix2f1.col2, -1.0F);
    }
    if (localVector2f5.x > 1.05F * f5 + 0.01F * hB.x)
    {
      i = 3;
      f5 = localVector2f5.x;
      localVector2f6 = localVector2f3.x > 0.0F ? localMatrix2f2.col1 : MathUtil.scale(localMatrix2f2.col1, -1.0F);
    }
    if (localVector2f5.y > 1.05F * f5 + 0.01F * hB.y)
    {
      i = 4;
      f5 = localVector2f5.y;
      localVector2f6 = localVector2f3.y > 0.0F ? localMatrix2f2.col2 : MathUtil.scale(localMatrix2f2.col2, -1.0F);
    }
    ClipVertex[] arrayOfClipVertex1 = { new ClipVertex(), new ClipVertex() };
    Vector2f localVector2f7;
    float f6;
    Vector2f localVector2f8;
    float f9;
    float f7;
    float f8;
    char c1;
    char c2;
    switch (i)
    {
    case 1:
      localVector2f7 = localVector2f6;
      f6 = localROVector2f1.dot(localVector2f7) + hA.x;
      localVector2f8 = localMatrix2f1.col2;
      f9 = localROVector2f1.dot(localVector2f8);
      f7 = -f9 + hA.y;
      f8 = f9 + hA.y;
      c1 = '\003';
      c2 = '\001';
      computeIncidentEdge(arrayOfClipVertex1, hB, localROVector2f2, localMatrix2f2, localVector2f7);
      break;
    case 2:
      localVector2f7 = localVector2f6;
      f6 = localROVector2f1.dot(localVector2f7) + hA.y;
      localVector2f8 = localMatrix2f1.col1;
      f9 = localROVector2f1.dot(localVector2f8);
      f7 = -f9 + hA.x;
      f8 = f9 + hA.x;
      c1 = '\002';
      c2 = '\004';
      computeIncidentEdge(arrayOfClipVertex1, hB, localROVector2f2, localMatrix2f2, localVector2f7);
      break;
    case 3:
      localVector2f7 = MathUtil.scale(localVector2f6, -1.0F);
      f6 = localROVector2f2.dot(localVector2f7) + hB.x;
      localVector2f8 = localMatrix2f2.col2;
      f9 = localROVector2f2.dot(localVector2f8);
      f7 = -f9 + hB.y;
      f8 = f9 + hB.y;
      c1 = '\003';
      c2 = '\001';
      computeIncidentEdge(arrayOfClipVertex1, hA, localROVector2f1, localMatrix2f1, localVector2f7);
      break;
    case 4:
      localVector2f7 = MathUtil.scale(localVector2f6, -1.0F);
      f6 = localROVector2f2.dot(localVector2f7) + hB.y;
      localVector2f8 = localMatrix2f2.col1;
      f9 = localROVector2f2.dot(localVector2f8);
      f7 = -f9 + hB.x;
      f8 = f9 + hB.x;
      c1 = '\002';
      c2 = '\004';
      computeIncidentEdge(arrayOfClipVertex1, hA, localROVector2f1, localMatrix2f1, localVector2f7);
      break;
    default:
      throw new RuntimeException("Unknown face!");
    }
    ClipVertex[] arrayOfClipVertex2 = { new ClipVertex(), new ClipVertex() };
    ClipVertex[] arrayOfClipVertex3 = { new ClipVertex(), new ClipVertex() };
    int j = clipSegmentToLine(arrayOfClipVertex2, arrayOfClipVertex1, MathUtil.scale(localVector2f8, -1.0F), f7, c1);
    if (j < 2)
      return 0;
    j = clipSegmentToLine(arrayOfClipVertex3, arrayOfClipVertex2, localVector2f8, f8, c2);
    if (j < 2)
      return 0;
    int k = 0;
    for (int m = 0; m < 2; m++)
    {
      float f10 = localVector2f7.dot(arrayOfClipVertex3[m].v) - f6;
      if (f10 <= 0.0F)
      {
        paramArrayOfContact[k].setSeparation(f10);
        paramArrayOfContact[k].setNormal(localVector2f6);
        paramArrayOfContact[k].setPosition(MathUtil.sub(arrayOfClipVertex3[m].v, MathUtil.scale(localVector2f7, f10)));
        paramArrayOfContact[k].setFeature(arrayOfClipVertex3[m].fp);
        if ((i == 3) || (i == 4))
          flip(paramArrayOfContact[k].getFeature());
        k++;
      }
    }
    return k;
  }

  private class ClipVertex
  {
    Vector2f v = new Vector2f();
    FeaturePair fp = new FeaturePair();

    public strictfp ClipVertex()
    {
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.BoxBoxCollider
 * JD-Core Version:    0.6.2
 */