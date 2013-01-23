package net.phys2d.raw.shapes;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;

public class Polygon extends AbstractShape
  implements DynamicShape
{
  protected Vector2f[] vertices;
  protected float area;
  protected Vector2f centroid;

  public float getArea()
  {
    return this.area;
  }

  public Vector2f getCentroid()
  {
    return this.centroid;
  }

  public Vector2f[] getVertices(ROVector2f paramROVector2f, float paramFloat)
  {
    Vector2f[] arrayOfVector2f = new Vector2f[this.vertices.length];
    float f1 = (float)Math.cos(paramFloat);
    float f2 = (float)Math.sin(paramFloat);
    for (int i = 0; i < this.vertices.length; i++)
    {
      float f3 = this.vertices[i].x * f1 - this.vertices[i].y * f2;
      float f4 = this.vertices[i].y * f1 + this.vertices[i].x * f2;
      f3 += paramROVector2f.getX();
      f4 += paramROVector2f.getY();
      arrayOfVector2f[i] = new Vector2f(f3, f4);
    }
    return arrayOfVector2f;
  }

  public Vector2f getCentroid(ROVector2f paramROVector2f, float paramFloat)
  {
    float f1 = (float)Math.cos(paramFloat);
    float f2 = (float)Math.sin(paramFloat);
    return new Vector2f(this.centroid.x * f1 - this.centroid.y * f2 + paramROVector2f.getX(), this.centroid.y * f1 + this.centroid.x * f2 + paramROVector2f.getY());
  }

  public float getSurfaceFactor()
  {
    return getArea();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.shapes.Polygon
 * JD-Core Version:    0.6.2
 */