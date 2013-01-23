package net.phys2d.raw.collide;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import net.phys2d.math.Vector2f;

public class IntersectionGatherer
{
  public static float MIN_PAIR_DIST = 0.5F;
  public static int MAX_INTERSECTIONS = 50;
  private SortableIntersection[] intersections = new SortableIntersection[MAX_INTERSECTIONS];
  private int noIntersections = 0;
  private Vector2f[] vertsA;
  private Vector2f[] vertsB;

  public IntersectionGatherer(Vector2f[] paramArrayOfVector2f1, Vector2f[] paramArrayOfVector2f2)
  {
    this.vertsA = paramArrayOfVector2f1;
    this.vertsB = paramArrayOfVector2f2;
  }

  public void intersect(int paramInt1, int paramInt2)
  {
    if (this.noIntersections >= MAX_INTERSECTIONS)
      return;
    Vector2f localVector2f1 = this.vertsA[paramInt1];
    Vector2f localVector2f2 = this.vertsA[((paramInt1 + 1) % this.vertsA.length)];
    Vector2f localVector2f3 = this.vertsB[paramInt2];
    Vector2f localVector2f4 = this.vertsB[((paramInt2 + 1) % this.vertsB.length)];
    float f1 = (localVector2f4.y - localVector2f3.y) * (localVector2f2.x - localVector2f1.x) - (localVector2f4.x - localVector2f3.x) * (localVector2f2.y - localVector2f1.y);
    if (f1 == 0.0F)
      return;
    float f2 = (localVector2f4.x - localVector2f3.x) * (localVector2f1.y - localVector2f3.y) - (localVector2f4.y - localVector2f3.y) * (localVector2f1.x - localVector2f3.x);
    f2 /= f1;
    float f3 = (localVector2f2.x - localVector2f1.x) * (localVector2f1.y - localVector2f3.y) - (localVector2f2.y - localVector2f1.y) * (localVector2f1.x - localVector2f3.x);
    f3 /= f1;
    if ((f2 < 0.0F) || (f2 > 1.0F) || (f3 < 0.0F) || (f3 > 1.0F))
      return;
    Vector2f localVector2f5 = new Vector2f(localVector2f1.x + f2 * (localVector2f2.x - localVector2f1.x), localVector2f1.y + f2 * (localVector2f2.y - localVector2f1.y));
    Vector2f localVector2f6 = new Vector2f(localVector2f5);
    localVector2f6.sub(localVector2f1);
    float f4 = localVector2f6.lengthSquared();
    localVector2f6 = new Vector2f(localVector2f5);
    localVector2f6.sub(localVector2f3);
    float f5 = localVector2f6.lengthSquared();
    float f6 = (localVector2f1.x - localVector2f3.x) * (localVector2f4.y - localVector2f3.y) - (localVector2f4.x - localVector2f3.x) * (localVector2f1.y - localVector2f3.y);
    if (f6 > 0.0F)
      this.intersections[this.noIntersections] = new SortableIntersection(paramInt1, paramInt2, localVector2f5, true, f4, f5);
    else
      this.intersections[this.noIntersections] = new SortableIntersection(paramInt1, paramInt2, localVector2f5, false, f4, f5);
    this.noIntersections += 1;
  }

  public Intersection[] getIntersections()
  {
    Intersection[] arrayOfIntersection = new Intersection[this.noIntersections];
    for (int i = 0; i < this.noIntersections; i++)
      arrayOfIntersection[i] = this.intersections[i];
    Arrays.sort(arrayOfIntersection, new IntersectionComparator());
    return arrayOfIntersection;
  }

  public Intersection[][] getIntersectionPairs()
  {
    if (this.noIntersections < 2)
      return new Intersection[0][2];
    Arrays.sort(this.intersections, 0, this.noIntersections, new IntersectionComparator());
    Integer[] arrayOfInteger = new Integer[this.noIntersections];
    for (int i = 0; i < this.noIntersections; i++)
      arrayOfInteger[i] = new Integer(i);
    Arrays.sort(arrayOfInteger, new PointerTableComparator());
    i = getReferencePointer(arrayOfInteger);
    filterIntersections(i, arrayOfInteger);
    int j = this.intersections[0].isIngoing ? 0 : 1;
    LinkedList localLinkedList = new LinkedList();
    int k = j;
    while (k < this.noIntersections + j)
    {
      SortableIntersection localSortableIntersection1 = this.intersections[(k % this.noIntersections)];
      SortableIntersection localSortableIntersection2 = this.intersections[((k + 1) % this.noIntersections)];
      if (localSortableIntersection1 == null)
      {
        k++;
      }
      else
      {
        Intersection[] arrayOfIntersection;
        if ((localSortableIntersection2 != null) && (localSortableIntersection1.isIngoing) && (!localSortableIntersection2.isIngoing) && (!localSortableIntersection1.position.equalsDelta(localSortableIntersection2.position, MIN_PAIR_DIST)))
        {
          arrayOfIntersection = new Intersection[] { localSortableIntersection1, localSortableIntersection2 };
          localLinkedList.add(arrayOfIntersection);
          k += 2;
        }
        else
        {
          arrayOfIntersection = new Intersection[] { localSortableIntersection1 };
          localLinkedList.add(arrayOfIntersection);
          k++;
        }
      }
    }
    return (Intersection[][])localLinkedList.toArray(new Intersection[localLinkedList.size()][]);
  }

  private int getReferencePointer(Integer[] paramArrayOfInteger)
  {
    int i = this.intersections[paramArrayOfInteger[0].intValue()].isIngoing ? 0 : 1;
    int j = 0;
    int k = i + 1 % this.noIntersections;
    int m = -1;
    for (int n = i; n < this.noIntersections + i; n++)
    {
      int i1 = paramArrayOfInteger[(n % this.noIntersections)].intValue();
      SortableIntersection localSortableIntersection = this.intersections[i1];
      if (localSortableIntersection.isIngoing)
      {
        m = localSortableIntersection.edgeB;
      }
      else if (m >= 0)
      {
        int i2 = (localSortableIntersection.edgeB - m + this.vertsB.length) % this.vertsB.length;
        if (i2 > j)
        {
          j = i2;
          k = n % this.noIntersections;
        }
        m = -1;
      }
    }
    return k;
  }

  private void filterIntersections(int paramInt, Integer[] paramArrayOfInteger)
  {
    if ((paramInt >= this.noIntersections) && (paramInt < 0))
      throw new RuntimeException("The reference vertex cannot be correct since B does not have that many vertices.");
    int i = -2;
    for (int j = paramInt; j < this.noIntersections + paramInt; j++)
    {
      k = j % this.noIntersections;
      int m = paramArrayOfInteger[k].intValue();
      SortableIntersection localSortableIntersection = this.intersections[m];
      if (localSortableIntersection.isIngoing)
      {
        if ((i - 1 + this.noIntersections) % this.noIntersections == m)
          i = -2;
        else
          this.intersections[m] = null;
      }
      else if (i < 0)
        i = m;
      else
        this.intersections[m] = null;
    }
    j = 0;
    for (int k = 0; k < this.noIntersections; k++)
      if (this.intersections[k] == null)
        j++;
      else
        this.intersections[(k - j)] = this.intersections[k];
    this.noIntersections -= j;
  }

  class PointerTableComparator
    implements Comparator
  {
    PointerTableComparator()
    {
    }

    public int compare(Object paramObject1, Object paramObject2)
    {
      IntersectionGatherer.SortableIntersection localSortableIntersection1 = IntersectionGatherer.this.intersections[((Integer)paramObject1).intValue()];
      IntersectionGatherer.SortableIntersection localSortableIntersection2 = IntersectionGatherer.this.intersections[((Integer)paramObject2).intValue()];
      if (localSortableIntersection1.edgeB < localSortableIntersection2.edgeB)
        return -1;
      if (localSortableIntersection1.edgeB == localSortableIntersection2.edgeB)
      {
        if (localSortableIntersection1.distFromVertB < localSortableIntersection2.distFromVertB)
          return -1;
        if ((localSortableIntersection1.distFromVertB == localSortableIntersection2.distFromVertB) && (!localSortableIntersection1.isIngoing))
          return -1;
      }
      return 1;
    }
  }

  class IntersectionComparator
    implements Comparator
  {
    IntersectionComparator()
    {
    }

    public int compare(Object paramObject1, Object paramObject2)
    {
      IntersectionGatherer.SortableIntersection localSortableIntersection1 = (IntersectionGatherer.SortableIntersection)paramObject1;
      IntersectionGatherer.SortableIntersection localSortableIntersection2 = (IntersectionGatherer.SortableIntersection)paramObject2;
      if (localSortableIntersection1.edgeA < localSortableIntersection2.edgeA)
        return -1;
      if (localSortableIntersection1.edgeA == localSortableIntersection2.edgeA)
      {
        if (localSortableIntersection1.distFromVertA < localSortableIntersection2.distFromVertA)
          return -1;
        if ((localSortableIntersection1.distFromVertA == localSortableIntersection2.distFromVertA) && (localSortableIntersection1.isIngoing))
          return -1;
      }
      return 1;
    }
  }

  class SortableIntersection extends Intersection
  {
    public float distFromVertA;
    public float distFromVertB;

    public SortableIntersection(int paramInt1, int paramVector2f, Vector2f paramBoolean, boolean paramFloat1, float paramFloat2, float arg7)
    {
      super(paramVector2f, paramBoolean, paramFloat1);
      this.distFromVertA = paramFloat2;
      Object localObject;
      this.distFromVertB = localObject;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.IntersectionGatherer
 * JD-Core Version:    0.6.2
 */