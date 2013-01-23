package net.phys2d.raw.collide;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;

public class EdgeSweep
{
  private ProjectedVertex current;
  private Vector2f sweepDir;

  public EdgeSweep(ROVector2f paramROVector2f)
  {
    this.sweepDir = new Vector2f(paramROVector2f);
  }

  private void insertBackwards(int paramInt, boolean paramBoolean, float paramFloat)
  {
    ProjectedVertex localProjectedVertex = new ProjectedVertex(paramInt, paramBoolean, paramFloat);
    if (this.current == null)
    {
      this.current = localProjectedVertex;
      return;
    }
    while (this.current.distance > localProjectedVertex.distance)
    {
      if (this.current.previous == null)
      {
        this.current.previous = localProjectedVertex;
        localProjectedVertex.next = this.current;
        this.current = localProjectedVertex;
        return;
      }
      this.current = this.current.previous;
    }
    localProjectedVertex.next = this.current.next;
    localProjectedVertex.previous = this.current;
    this.current.next = localProjectedVertex;
    if (localProjectedVertex.next != null)
      localProjectedVertex.next.previous = localProjectedVertex;
    this.current = localProjectedVertex;
  }

  public void insert(int paramInt, boolean paramBoolean, float paramFloat)
  {
    if ((this.current == null) || (this.current.distance <= paramFloat))
      insertForwards(paramInt, paramBoolean, paramFloat);
    else
      insertBackwards(paramInt, paramBoolean, paramFloat);
  }

  private void insertForwards(int paramInt, boolean paramBoolean, float paramFloat)
  {
    ProjectedVertex localProjectedVertex = new ProjectedVertex(paramInt, paramBoolean, paramFloat);
    if (this.current == null)
    {
      this.current = localProjectedVertex;
      return;
    }
    while (this.current.distance <= localProjectedVertex.distance)
    {
      if (this.current.next == null)
      {
        this.current.next = localProjectedVertex;
        localProjectedVertex.previous = this.current;
        this.current = localProjectedVertex;
        return;
      }
      this.current = this.current.next;
    }
    localProjectedVertex.next = this.current;
    localProjectedVertex.previous = this.current.previous;
    this.current.previous = localProjectedVertex;
    if (localProjectedVertex.previous != null)
      localProjectedVertex.previous.next = localProjectedVertex;
    this.current = localProjectedVertex;
  }

  private void goToStart()
  {
    while (this.current.previous != null)
      this.current = this.current.previous;
  }

  public int[][] getOverlappingEdges()
  {
    if (this.current == null)
      return new int[0][2];
    goToStart();
    CurrentEdges localCurrentEdges1 = new CurrentEdges(null);
    CurrentEdges localCurrentEdges2 = new CurrentEdges(null);
    EdgePairs localEdgePairs = new EdgePairs(null);
    float f = -3.402824E+038F;
    while (this.current != null)
    {
      if (this.current.distance > f)
      {
        f = this.current.distance;
        localCurrentEdges1.removeScheduled();
        localCurrentEdges2.removeScheduled();
      }
      int[] arrayOfInt;
      int i;
      if (this.current.isA)
      {
        if (!localCurrentEdges1.contains(this.current.vertex))
        {
          localCurrentEdges1.addEdge(this.current.vertex);
          arrayOfInt = localCurrentEdges2.getEdges();
          for (i = 0; i < arrayOfInt.length; i++)
            localEdgePairs.add(this.current.vertex, arrayOfInt[i]);
        }
        else
        {
          localCurrentEdges1.scheduleRemoval(this.current.vertex);
        }
      }
      else if (!localCurrentEdges2.contains(this.current.vertex))
      {
        localCurrentEdges2.addEdge(this.current.vertex);
        arrayOfInt = localCurrentEdges1.getEdges();
        for (i = 0; i < arrayOfInt.length; i++)
          localEdgePairs.add(arrayOfInt[i], this.current.vertex);
      }
      else
      {
        localCurrentEdges2.scheduleRemoval(this.current.vertex);
      }
      this.current = this.current.next;
    }
    return localEdgePairs.toList();
  }

  public void addVerticesToSweep(boolean paramBoolean, Vector2f[] paramArrayOfVector2f)
  {
    int i = 0;
    int j = paramArrayOfVector2f.length - 1;
    while (i < paramArrayOfVector2f.length)
    {
      float f = this.sweepDir.dot(paramArrayOfVector2f[i]);
      insert(i, paramBoolean, f);
      insert(j, paramBoolean, f);
      j = i;
      i++;
    }
  }

  private class EdgePairs
  {
    private EdgePair first;
    private int size = 0;

    private EdgePairs()
    {
    }

    public void add(int paramInt1, int paramInt2)
    {
      this.first = new EdgePair(paramInt1, paramInt2, this.first);
      this.size += 1;
    }

    public int[][] toList()
    {
      int[][] arrayOfInt = new int[this.size][2];
      EdgePair localEdgePair = this.first;
      for (int i = 0; i < this.size; i++)
      {
        arrayOfInt[i][0] = localEdgePair.a;
        arrayOfInt[i][1] = localEdgePair.b;
        localEdgePair = localEdgePair.next;
      }
      return arrayOfInt;
    }

    class EdgePair
    {
      public int a;
      public int b;
      public EdgePair next;

      public EdgePair(int paramInt1, int paramEdgePair, EdgePair arg4)
      {
        this.a = paramInt1;
        this.b = paramEdgePair;
        Object localObject;
        this.next = localObject;
      }
    }
  }

  private class CurrentEdges
  {
    private LinkedEdgeList currentEdges;
    private LinkedEdgeList scheduledForRemoval;

    private CurrentEdges()
    {
    }

    public void addEdge(int paramInt)
    {
      this.currentEdges = new LinkedEdgeList(paramInt, this.currentEdges);
    }

    public void scheduleRemoval(int paramInt)
    {
      if (this.currentEdges == null)
        return;
      if (this.currentEdges.edge == paramInt)
      {
        this.currentEdges = this.currentEdges.next;
      }
      else
      {
        LinkedEdgeList localLinkedEdgeList1 = this.currentEdges.next;
        LinkedEdgeList localLinkedEdgeList2 = this.currentEdges;
        while (localLinkedEdgeList1 != null)
        {
          if (localLinkedEdgeList1.edge == paramInt)
          {
            localLinkedEdgeList2.next = localLinkedEdgeList1.next;
            this.scheduledForRemoval = new LinkedEdgeList(paramInt, this.scheduledForRemoval);
            return;
          }
          localLinkedEdgeList2 = localLinkedEdgeList1;
          localLinkedEdgeList1 = localLinkedEdgeList1.next;
        }
      }
    }

    public void removeScheduled()
    {
      this.scheduledForRemoval = null;
    }

    public boolean contains(int paramInt)
    {
      for (LinkedEdgeList localLinkedEdgeList = this.currentEdges; localLinkedEdgeList != null; localLinkedEdgeList = localLinkedEdgeList.next)
        if (localLinkedEdgeList.edge == paramInt)
          return true;
      for (localLinkedEdgeList = this.scheduledForRemoval; localLinkedEdgeList != null; localLinkedEdgeList = localLinkedEdgeList.next)
        if (localLinkedEdgeList.edge == paramInt)
          return true;
      return false;
    }

    public int getNoEdges()
    {
      int i = 0;
      for (LinkedEdgeList localLinkedEdgeList = this.currentEdges; localLinkedEdgeList != null; localLinkedEdgeList = localLinkedEdgeList.next)
        i++;
      for (localLinkedEdgeList = this.scheduledForRemoval; localLinkedEdgeList != null; localLinkedEdgeList = localLinkedEdgeList.next)
        i++;
      return i;
    }

    public int[] getEdges()
    {
      int[] arrayOfInt = new int[getNoEdges()];
      int i = 0;
      for (LinkedEdgeList localLinkedEdgeList = this.currentEdges; localLinkedEdgeList != null; localLinkedEdgeList = localLinkedEdgeList.next)
      {
        arrayOfInt[i] = localLinkedEdgeList.edge;
        i++;
      }
      for (localLinkedEdgeList = this.scheduledForRemoval; localLinkedEdgeList != null; localLinkedEdgeList = localLinkedEdgeList.next)
      {
        arrayOfInt[i] = localLinkedEdgeList.edge;
        i++;
      }
      return arrayOfInt;
    }

    class LinkedEdgeList
    {
      public int edge;
      public LinkedEdgeList next;

      public LinkedEdgeList(int paramLinkedEdgeList, LinkedEdgeList arg3)
      {
        this.edge = paramLinkedEdgeList;
        Object localObject;
        this.next = localObject;
      }
    }
  }

  class ProjectedVertex
  {
    public int vertex;
    public boolean isA;
    public float distance;
    public ProjectedVertex next;
    public ProjectedVertex previous;

    public ProjectedVertex(int paramBoolean, boolean paramFloat, float arg4)
    {
      this.vertex = paramBoolean;
      this.isA = paramFloat;
      Object localObject;
      this.distance = localObject;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.collide.EdgeSweep
 * JD-Core Version:    0.6.2
 */