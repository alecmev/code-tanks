package net.phys2d.raw.collide;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Polygon;

public class PolygonCircleCollider extends PolygonPolygonCollider
{
  public int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    Polygon localPolygon = (Polygon)paramBody1.getShape();
    Circle localCircle = (Circle)paramBody2.getShape();
    Vector2f[] arrayOfVector2f = localPolygon.getVertices(paramBody1.getPosition(), paramBody1.getRotation());
    Vector2f localVector2f1 = new Vector2f(localPolygon.getCentroid());
    localVector2f1.add(paramBody1.getPosition());
    int[][] arrayOfInt = getCollisionCandidates(arrayOfVector2f, localVector2f1, localCircle.getRadius(), paramBody2.getPosition());
    int i = 0;
    for (int j = 0; j < arrayOfInt.length; j++)
    {
      if (i >= paramArrayOfContact.length)
        return paramArrayOfContact.length;
      Vector2f localVector2f2 = arrayOfVector2f[arrayOfInt[j][0]];
      Vector2f localVector2f3 = arrayOfVector2f[((arrayOfInt[j][0] + 1) % arrayOfVector2f.length)];
      Line localLine = new Line(localVector2f2, localVector2f3);
      float f1 = localLine.distanceSquared(paramBody2.getPosition());
      float f2 = localCircle.getRadius() * localCircle.getRadius();
      if (f1 < f2)
      {
        Vector2f localVector2f4 = new Vector2f();
        localLine.getClosestPoint(paramBody2.getPosition(), localVector2f4);
        Vector2f localVector2f5 = new Vector2f(paramBody2.getPosition());
        localVector2f5.sub(localVector2f4);
        float f3 = localCircle.getRadius() - localVector2f5.length();
        localVector2f5.normalise();
        paramArrayOfContact[i].setSeparation(-f3);
        paramArrayOfContact[i].setPosition(localVector2f4);
        paramArrayOfContact[i].setNormal(localVector2f5);
        paramArrayOfContact[i].setFeature(new FeaturePair());
        i++;
      }
    }
    return i;
  }

  protected int[][] getCollisionCandidates(Vector2f[] paramArrayOfVector2f, ROVector2f paramROVector2f1, float paramFloat, ROVector2f paramROVector2f2)
  {
    Vector2f localVector2f = new Vector2f(paramROVector2f1);
    localVector2f.sub(paramROVector2f2);
    localVector2f.normalise();
    EdgeSweep localEdgeSweep = new EdgeSweep(localVector2f);
    localEdgeSweep.addVerticesToSweep(true, paramArrayOfVector2f);
    float f = paramROVector2f2.dot(localVector2f);
    localEdgeSweep.insert(0, false, -paramFloat + f);
    localEdgeSweep.insert(0, false, paramFloat + f);
    return localEdgeSweep.getOverlappingEdges();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.PolygonCircleCollider
 * JD-Core Version:    0.6.2
 */