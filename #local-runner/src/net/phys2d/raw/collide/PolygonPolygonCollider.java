package net.phys2d.raw.collide;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Polygon;

public class PolygonPolygonCollider
  implements Collider
{
  public int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    Polygon localPolygon1 = (Polygon)paramBody1.getShape();
    Polygon localPolygon2 = (Polygon)paramBody2.getShape();
    Vector2f[] arrayOfVector2f1 = localPolygon1.getVertices(paramBody1.getPosition(), paramBody1.getRotation());
    Vector2f[] arrayOfVector2f2 = localPolygon2.getVertices(paramBody2.getPosition(), paramBody2.getRotation());
    Vector2f localVector2f1 = new Vector2f(localPolygon1.getCentroid());
    localVector2f1.add(paramBody1.getPosition());
    Vector2f localVector2f2 = new Vector2f(localPolygon2.getCentroid());
    localVector2f2.add(paramBody2.getPosition());
    int[][] arrayOfInt = getCollisionCandidates(arrayOfVector2f1, arrayOfVector2f2, localVector2f1, localVector2f2);
    Intersection[][] arrayOfIntersection = getIntersectionPairs(arrayOfVector2f1, arrayOfVector2f2, arrayOfInt);
    return populateContacts(paramArrayOfContact, arrayOfVector2f1, arrayOfVector2f2, arrayOfIntersection);
  }

  public Intersection[][] getIntersectionPairs(Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2, int[][] paramArrayOfInt)
  {
    if (paramArrayOfInt.length == 0)
      return new Intersection[0][2];
    IntersectionGatherer localIntersectionGatherer = new IntersectionGatherer(paramArrayOfVector2f1, paramArrayOfVector2f2);
    for (int i = 0; i < paramArrayOfInt.length; i++)
      localIntersectionGatherer.intersect(paramArrayOfInt[i][0], paramArrayOfInt[i][1]);
    return localIntersectionGatherer.getIntersectionPairs();
  }

  public int populateContacts(Contact[] paramArrayOfContact, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2, Intersection[][] paramArrayOfIntersection)
  {
    if (paramArrayOfIntersection.length == 0)
      return 0;
    int i = 0;
    for (int j = 0; j < paramArrayOfIntersection.length; j++)
    {
      if (i >= paramArrayOfContact.length)
        return paramArrayOfContact.length;
      if ((paramArrayOfIntersection[j].length == 2) && (i < paramArrayOfContact.length - 1))
      {
        setContactPair(paramArrayOfContact[i], paramArrayOfContact[(i + 1)], paramArrayOfIntersection[j][0], paramArrayOfIntersection[j][1], paramArrayOfVector2f1, paramArrayOfVector2f2);
        i += 2;
      }
      else if (paramArrayOfIntersection[j].length == 1)
      {
        setContact(paramArrayOfContact[i], paramArrayOfIntersection[j][0], paramArrayOfVector2f1, paramArrayOfVector2f2);
        i++;
      }
    }
    return i;
  }

  public void setContact(Contact paramContact, Intersection paramIntersection, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2)
  {
    Vector2f localVector2f1 = paramArrayOfVector2f1[paramIntersection.edgeA];
    Vector2f localVector2f2 = paramArrayOfVector2f1[((paramIntersection.edgeA + 1) % paramArrayOfVector2f1.length)];
    Vector2f localVector2f3 = paramArrayOfVector2f2[paramIntersection.edgeB];
    Vector2f localVector2f4 = paramArrayOfVector2f2[((paramIntersection.edgeB + 1) % paramArrayOfVector2f2.length)];
    Vector2f localVector2f5 = MathUtil.getNormal(localVector2f1, localVector2f2);
    localVector2f5.sub(MathUtil.getNormal(localVector2f3, localVector2f4));
    localVector2f5.normalise();
    paramContact.setNormal(localVector2f5);
    paramContact.setSeparation(0.0F);
    paramContact.setFeature(new FeaturePair(paramIntersection.edgeA, paramIntersection.edgeB, 0, 0));
    paramContact.setPosition(paramIntersection.position);
  }

  public void setContactPair(Contact paramContact1, Contact paramContact2, Intersection paramIntersection1, Intersection paramIntersection2, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2)
  {
    Vector2f localVector2f1 = paramIntersection1.position;
    Vector2f localVector2f2 = paramIntersection2.position;
    Vector2f localVector2f3 = MathUtil.getNormal(localVector2f1, localVector2f2);
    FeaturePair localFeaturePair = new FeaturePair(paramIntersection1.edgeA, paramIntersection1.edgeB, paramIntersection2.edgeA, paramIntersection2.edgeB);
    float f = -PenetrationSweep.getPenetrationDepth(paramIntersection1, paramIntersection2, localVector2f3, paramArrayOfVector2f1, paramArrayOfVector2f2);
    f /= 4.0F;
    paramContact1.setSeparation(f);
    paramContact1.setNormal(localVector2f3);
    paramContact1.setPosition(localVector2f1);
    paramContact1.setFeature(localFeaturePair);
    paramContact2.setSeparation(f);
    paramContact2.setNormal(localVector2f3);
    paramContact2.setPosition(localVector2f2);
    paramContact2.setFeature(localFeaturePair);
  }

  public int[][] getCollisionCandidates(EdgeSweep paramEdgeSweep, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2)
  {
    paramEdgeSweep.addVerticesToSweep(true, paramArrayOfVector2f1);
    paramEdgeSweep.addVerticesToSweep(false, paramArrayOfVector2f2);
    return paramEdgeSweep.getOverlappingEdges();
  }

  public int[][] getCollisionCandidates(Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2, Vector2f paramVector2f1, Vector2f paramVector2f2)
  {
    Vector2f localVector2f = new Vector2f(paramVector2f2);
    localVector2f.sub(paramVector2f1);
    return getCollisionCandidates(new EdgeSweep(localVector2f), paramArrayOfVector2f1, paramArrayOfVector2f2);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.PolygonPolygonCollider
 * JD-Core Version:    0.6.2
 */