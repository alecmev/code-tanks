package net.phys2d.raw.collide;

import net.phys2d.math.Vector2f;

public class PenetrationSweep
{
  private Vector2f normal;
  private Vector2f sweepDir;
  private float startDist;
  private float endDist;

  public PenetrationSweep(Vector2f paramVector2f1, Vector2f paramVector2f2, Vector2f paramVector2f3, Vector2f paramVector2f4)
  {
    this.normal = paramVector2f1;
    this.sweepDir = paramVector2f2;
    this.startDist = paramVector2f3.dot(paramVector2f2);
    this.endDist = paramVector2f4.dot(paramVector2f2);
  }

  public static float getPenetrationDepth(Intersection paramIntersection1, Intersection paramIntersection2, Vector2f paramVector2f, Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2)
  {
    Vector2f localVector2f = new Vector2f(paramIntersection2.position);
    localVector2f.sub(paramIntersection1.position);
    PenetrationSweep localPenetrationSweep = new PenetrationSweep(paramVector2f, localVector2f, paramIntersection1.position, paramIntersection2.position);
    PenetrationSweep tmp48_46 = localPenetrationSweep;
    tmp48_46.getClass();
    ContourWalker localContourWalker1 = new ContourWalker(paramArrayOfVector2f1, paramIntersection1.edgeA, paramIntersection2.edgeA, false);
    PenetrationSweep tmp74_72 = localPenetrationSweep;
    tmp74_72.getClass();
    ContourWalker localContourWalker2 = new ContourWalker(paramArrayOfVector2f2, (paramIntersection2.edgeB + 1) % paramArrayOfVector2f2.length, (paramIntersection1.edgeB + 1) % paramArrayOfVector2f2.length, true);
    float f1 = 0.0F;
    float f2 = paramIntersection1.position.dot(paramVector2f);
    float f3 = f2;
    while ((localContourWalker1.hasNext()) || (localContourWalker2.hasNext()))
      if ((localContourWalker1.hasNext()) && ((localContourWalker1.getNextDistance() < localContourWalker2.getNextDistance()) || (!localContourWalker2.hasNext())))
      {
        localContourWalker1.next();
        if ((localContourWalker1.getDistance() >= localPenetrationSweep.startDist) && (localContourWalker1.getDistance() <= localPenetrationSweep.endDist))
        {
          f3 = localContourWalker1.getPenetration();
          f2 = localContourWalker2.getPenetration(localContourWalker1.getDistance());
        }
      }
      else
      {
        localContourWalker2.next();
        if ((localContourWalker2.getDistance() >= localPenetrationSweep.startDist) && (localContourWalker2.getDistance() <= localPenetrationSweep.endDist))
        {
          f3 = localContourWalker1.getPenetration(localContourWalker2.getDistance());
          f2 = localContourWalker2.getPenetration();
          f1 = Math.max(f1, f3 - f2);
        }
      }
    return f1;
  }

  public class ContourWalker
  {
    private Vector2f[] verts;
    private int currentVert;
    private int firstVert;
    private int lastVert;
    private boolean isBackwards;
    private float distance;
    private float nextDistance;
    private float penetration;
    private float penetrationDelta;

    public ContourWalker(Vector2f[] paramInt1, int paramInt2, int paramBoolean, boolean arg5)
    {
      if ((paramInt2 < 0) || (paramBoolean))
        throw new IllegalArgumentException("Vertex numbers cannot be negative.");
      if ((paramInt2 > paramInt1.length) || (paramBoolean > paramInt1.length))
        throw new IllegalArgumentException("The given vertex array doesn't include the first or the last vertex.");
      boolean bool;
      this.isBackwards = bool;
      this.verts = paramInt1;
      this.firstVert = paramInt2;
      this.lastVert = paramBoolean;
      this.currentVert = (bool ? paramBoolean : paramInt2);
      this.distance = paramInt1[this.currentVert].dot(PenetrationSweep.this.sweepDir);
      this.penetration = paramInt1[this.currentVert].dot(PenetrationSweep.this.normal);
      calculateNextValues();
    }

    public float getDistance()
    {
      return this.distance;
    }

    public float getNextDistance()
    {
      if (this.distance < PenetrationSweep.this.startDist)
        return Math.min(this.nextDistance, PenetrationSweep.this.startDist);
      if (this.distance < PenetrationSweep.this.endDist)
        return Math.min(this.nextDistance, PenetrationSweep.this.endDist);
      return this.nextDistance;
    }

    public float getPenetration()
    {
      return this.penetration;
    }

    public float getPenetration(float paramFloat)
    {
      return this.penetration + this.penetrationDelta * (paramFloat - this.distance);
    }

    public void next()
    {
      if (!hasNext())
        return;
      if ((this.distance < PenetrationSweep.this.startDist) && (this.nextDistance > PenetrationSweep.this.startDist))
      {
        this.penetration = getPenetration(PenetrationSweep.this.startDist);
        this.distance = PenetrationSweep.this.startDist;
        return;
      }
      if ((this.distance < PenetrationSweep.this.endDist) && (this.nextDistance > PenetrationSweep.this.endDist))
      {
        this.penetration = getPenetration(PenetrationSweep.this.endDist);
        this.distance = PenetrationSweep.this.endDist;
        return;
      }
      if (this.isBackwards)
        this.currentVert = ((this.currentVert - 1 + this.verts.length) % this.verts.length);
      else
        this.currentVert = ((this.currentVert + 1) % this.verts.length);
      this.distance = this.verts[this.currentVert].dot(PenetrationSweep.this.sweepDir);
      this.penetration = this.verts[this.currentVert].dot(PenetrationSweep.this.normal);
      calculateNextValues();
    }

    private void calculateNextValues()
    {
      int i = this.isBackwards ? this.currentVert - 1 : this.currentVert + 1;
      i = (i + this.verts.length) % this.verts.length;
      this.nextDistance = this.verts[i].dot(PenetrationSweep.this.sweepDir);
      this.penetrationDelta = (this.verts[i].dot(PenetrationSweep.this.normal) - this.penetration);
      if (this.nextDistance == this.distance)
      {
        this.penetration += this.penetrationDelta;
        this.penetrationDelta = 0.0F;
      }
      else
      {
        this.penetrationDelta /= (this.nextDistance - this.distance);
      }
    }

    public boolean hasNext()
    {
      if ((this.distance < PenetrationSweep.this.startDist) && (this.nextDistance > PenetrationSweep.this.startDist))
        return true;
      if ((this.distance < PenetrationSweep.this.endDist) && (this.nextDistance > PenetrationSweep.this.endDist))
        return true;
      int i = this.isBackwards ? this.lastVert - this.currentVert : this.currentVert - this.firstVert;
      i = (i + this.verts.length) % this.verts.length;
      i = (this.lastVert - this.firstVert + this.verts.length) % this.verts.length - i;
      return i > 0;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.PenetrationSweep
 * JD-Core Version:    0.6.2
 */