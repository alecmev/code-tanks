package net.phys2d.raw.collide;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Polygon;

public class PolygonBoxCollider extends PolygonPolygonCollider
{
  public int collide(Contact[] paramArrayOfContact, Body paramBody1, Body paramBody2)
  {
    Polygon localPolygon = (Polygon)paramBody1.getShape();
    Box localBox = (Box)paramBody2.getShape();
    Vector2f[] arrayOfVector2f1 = localPolygon.getVertices(paramBody1.getPosition(), paramBody1.getRotation());
    Vector2f[] arrayOfVector2f2 = localBox.getPoints(paramBody2.getPosition(), paramBody2.getRotation());
    Vector2f localVector2f = new Vector2f(arrayOfVector2f2[1]);
    localVector2f.sub(arrayOfVector2f2[2]);
    EdgeSweep localEdgeSweep = new EdgeSweep(localVector2f);
    localEdgeSweep.addVerticesToSweep(true, arrayOfVector2f1);
    localEdgeSweep.addVerticesToSweep(false, arrayOfVector2f2);
    int[][] arrayOfInt = localEdgeSweep.getOverlappingEdges();
    Intersection[][] arrayOfIntersection = getIntersectionPairs(arrayOfVector2f1, arrayOfVector2f2, arrayOfInt);
    return populateContacts(paramArrayOfContact, arrayOfVector2f1, arrayOfVector2f2, arrayOfIntersection);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.PolygonBoxCollider
 * JD-Core Version:    0.6.2
 */