package net.phys2d.raw.collide;

import java.io.PrintStream;
import net.phys2d.math.MathUtil;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Polygon;

public class LinePolygonCollider extends PolygonPolygonCollider
{
  public int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    Line localLine = (Line)paramBody1.getShape();
    Polygon localPolygon = (Polygon)paramBody2.getShape();
    Vector2f[] arrayOfVector2f1 = localLine.getVertices(paramBody1.getPosition(), paramBody1.getRotation());
    Vector2f[] arrayOfVector2f2 = localPolygon.getVertices(paramBody2.getPosition(), paramBody2.getRotation());
    Vector2f localVector2f1 = localPolygon.getCentroid(paramBody2.getPosition(), paramBody2.getRotation());
    int i = 0.0F > (localVector2f1.x - arrayOfVector2f1[0].x) * (arrayOfVector2f1[1].y - arrayOfVector2f1[0].y) - (arrayOfVector2f1[1].x - arrayOfVector2f1[0].x) * (localVector2f1.y - arrayOfVector2f1[0].y) ? 1 : 0;
    if (i != 0)
    {
      localVector2f2 = arrayOfVector2f1[0];
      arrayOfVector2f1[0] = arrayOfVector2f1[1];
      arrayOfVector2f1[1] = localVector2f2;
    }
    Vector2f localVector2f2 = new Vector2f(arrayOfVector2f1[1]);
    localVector2f2.sub(arrayOfVector2f1[0]);
    localVector2f2.set(localVector2f2.y, -localVector2f2.x);
    EdgeSweep localEdgeSweep = new EdgeSweep(localVector2f2);
    localEdgeSweep.insert(0, true, arrayOfVector2f1[0].dot(localVector2f2));
    localEdgeSweep.insert(0, true, arrayOfVector2f1[1].dot(localVector2f2));
    localEdgeSweep.addVerticesToSweep(false, arrayOfVector2f2);
    int[][] arrayOfInt = localEdgeSweep.getOverlappingEdges();
    IntersectionGatherer localIntersectionGatherer = new IntersectionGatherer(arrayOfVector2f1, arrayOfVector2f2);
    for (int j = 0; j < arrayOfInt.length; j++)
      localIntersectionGatherer.intersect(arrayOfInt[j][0], arrayOfInt[j][1]);
    Intersection[] arrayOfIntersection = localIntersectionGatherer.getIntersections();
    return populateContacts(paramArrayOfContact, arrayOfVector2f1, arrayOfVector2f2, arrayOfIntersection);
  }

  public int populateContacts(Contact[] paramArrayOfContact, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2, Intersection[] paramArrayOfIntersection)
  {
    if (paramArrayOfIntersection.length == 0)
      return 0;
    int i = 0;
    if (!paramArrayOfIntersection[0].isIngoing)
    {
      setLineEndContact(paramArrayOfContact[i], paramArrayOfIntersection[(paramArrayOfIntersection.length - 1)], paramArrayOfVector2f1, paramArrayOfVector2f2);
      if (paramArrayOfContact[i].getSeparation() < -10.0F)
        System.out.println("first " + paramArrayOfContact[i].getSeparation());
      i++;
    }
    int j = i;
    while (j < paramArrayOfIntersection.length - 1)
    {
      if (i > paramArrayOfContact.length - 2)
        return i;
      if ((!paramArrayOfIntersection[j].isIngoing) || (paramArrayOfIntersection[(j + 1)].isIngoing))
      {
        setContact(paramArrayOfContact[i], paramArrayOfIntersection[j], paramArrayOfVector2f1, paramArrayOfVector2f2);
        j++;
        i++;
      }
      else
      {
        setContactPair(paramArrayOfContact[i], paramArrayOfContact[(i + 1)], paramArrayOfIntersection[j], paramArrayOfIntersection[(j + 1)], paramArrayOfVector2f1, paramArrayOfVector2f2);
        if (paramArrayOfContact[i].getSeparation() < -10.0F)
          System.out.println("m " + paramArrayOfContact[i].getSeparation());
        i += 2;
        j += 2;
      }
    }
    if ((j < paramArrayOfIntersection.length) && (paramArrayOfIntersection[(paramArrayOfIntersection.length - 1)].isIngoing) && (i < paramArrayOfContact.length))
    {
      setLineEndContact(paramArrayOfContact[i], paramArrayOfIntersection[(paramArrayOfIntersection.length - 1)], paramArrayOfVector2f1, paramArrayOfVector2f2);
      if (paramArrayOfContact[i].getSeparation() < -10.0F)
        System.out.println(" last " + paramArrayOfContact[i].getSeparation());
      i++;
    }
    return i;
  }

  public void setLineEndContact(Contact paramContact, Intersection paramIntersection, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2)
  {
    Vector2f localVector2f = new Vector2f(paramIntersection.position);
    if (paramIntersection.isIngoing)
      localVector2f.sub(paramArrayOfVector2f1[1]);
    else
      localVector2f.sub(paramArrayOfVector2f1[0]);
    float f = 0.0F;
    paramContact.setSeparation(-f);
    paramContact.setNormal(MathUtil.getNormal(paramArrayOfVector2f2[((paramIntersection.edgeB + 1) % paramArrayOfVector2f2.length)], paramArrayOfVector2f2[paramIntersection.edgeB]));
    paramContact.setPosition(paramIntersection.position);
    paramContact.setFeature(new FeaturePair(0, 0, paramIntersection.edgeA, paramIntersection.edgeB));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.LinePolygonCollider
 * JD-Core Version:    0.6.2
 */