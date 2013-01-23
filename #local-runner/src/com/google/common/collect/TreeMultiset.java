package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.SortedSet;

@GwtCompatible(emulated=true)
public final class TreeMultiset extends AbstractSortedMultiset
  implements Serializable
{
  private final transient GeneralRange range;
  private final transient Reference rootReference;
  private static final BstAggregate DISTINCT_AGGREGATE = new BstAggregate()
  {
    public int entryValue(TreeMultiset.Node paramAnonymousNode)
    {
      return 1;
    }

    public long treeValue(TreeMultiset.Node paramAnonymousNode)
    {
      return TreeMultiset.distinctOrZero(paramAnonymousNode);
    }
  };
  private static final BstAggregate SIZE_AGGREGATE = new BstAggregate()
  {
    public int entryValue(TreeMultiset.Node paramAnonymousNode)
    {
      return paramAnonymousNode.elemCount();
    }

    public long treeValue(TreeMultiset.Node paramAnonymousNode)
    {
      return TreeMultiset.sizeOrZero(paramAnonymousNode);
    }
  };
  private static final BstNodeFactory NODE_FACTORY = new BstNodeFactory()
  {
    public TreeMultiset.Node createNode(TreeMultiset.Node paramAnonymousNode1, TreeMultiset.Node paramAnonymousNode2, TreeMultiset.Node paramAnonymousNode3)
    {
      return new TreeMultiset.Node(paramAnonymousNode1.getKey(), paramAnonymousNode1.elemCount(), paramAnonymousNode2, paramAnonymousNode3, null);
    }
  };

  @GwtIncompatible("not needed in emulated source")
  private static final long serialVersionUID = 1L;

  public static TreeMultiset create()
  {
    return new TreeMultiset(Ordering.natural());
  }

  public static TreeMultiset create(Comparator paramComparator)
  {
    return paramComparator == null ? new TreeMultiset(Ordering.natural()) : new TreeMultiset(paramComparator);
  }

  public static TreeMultiset create(Iterable paramIterable)
  {
    TreeMultiset localTreeMultiset = create();
    Iterables.addAll(localTreeMultiset, paramIterable);
    return localTreeMultiset;
  }

  public Iterator iterator()
  {
    return super.iterator();
  }

  private TreeMultiset(Comparator paramComparator)
  {
    super(paramComparator);
    this.range = GeneralRange.all(paramComparator);
    this.rootReference = new Reference();
  }

  private TreeMultiset(GeneralRange paramGeneralRange, Reference paramReference)
  {
    super(paramGeneralRange.comparator());
    this.range = paramGeneralRange;
    this.rootReference = paramReference;
  }

  Object checkElement(Object paramObject)
  {
    Object localObject = paramObject;
    this.comparator.compare(localObject, localObject);
    return localObject;
  }

  int distinctElements()
  {
    Node localNode = (Node)this.rootReference.get();
    return Ints.checkedCast(BstRangeOps.totalInRange(distinctAggregate(), this.range, localNode));
  }

  public int size()
  {
    Node localNode = (Node)this.rootReference.get();
    return Ints.saturatedCast(BstRangeOps.totalInRange(sizeAggregate(), this.range, localNode));
  }

  public int count(Object paramObject)
  {
    try
    {
      Object localObject = checkElement(paramObject);
      if (this.range.contains(localObject))
      {
        Node localNode = (Node)BstOperations.seek(comparator(), (BstNode)this.rootReference.get(), localObject);
        return countOrZero(localNode);
      }
      return 0;
    }
    catch (ClassCastException localClassCastException)
    {
      return 0;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return 0;
  }

  private int mutate(Object paramObject, MultisetModifier paramMultisetModifier)
  {
    BstMutationRule localBstMutationRule = BstMutationRule.createRule(paramMultisetModifier, BstCountBasedBalancePolicies.singleRebalancePolicy(distinctAggregate()), nodeFactory());
    BstMutationResult localBstMutationResult = BstOperations.mutate(comparator(), localBstMutationRule, (BstNode)this.rootReference.get(), paramObject);
    if (!this.rootReference.compareAndSet(localBstMutationResult.getOriginalRoot(), localBstMutationResult.getChangedRoot()))
      throw new ConcurrentModificationException();
    Node localNode = (Node)localBstMutationResult.getOriginalTarget();
    return countOrZero(localNode);
  }

  public int add(Object paramObject, int paramInt)
  {
    checkElement(paramObject);
    if (paramInt == 0)
      return count(paramObject);
    Preconditions.checkArgument(this.range.contains(paramObject));
    return mutate(paramObject, new AddModifier(paramInt, null));
  }

  public int remove(Object paramObject, int paramInt)
  {
    if (paramInt == 0)
      return count(paramObject);
    try
    {
      Object localObject = checkElement(paramObject);
      return this.range.contains(localObject) ? mutate(localObject, new RemoveModifier(paramInt, null)) : 0;
    }
    catch (ClassCastException localClassCastException)
    {
      return 0;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return 0;
  }

  public boolean setCount(Object paramObject, int paramInt1, int paramInt2)
  {
    checkElement(paramObject);
    Preconditions.checkArgument(this.range.contains(paramObject));
    return mutate(paramObject, new ConditionalSetCountModifier(paramInt1, paramInt2, null)) == paramInt1;
  }

  public int setCount(Object paramObject, int paramInt)
  {
    checkElement(paramObject);
    Preconditions.checkArgument(this.range.contains(paramObject));
    return mutate(paramObject, new SetCountModifier(paramInt, null));
  }

  private BstPathFactory pathFactory()
  {
    return BstInOrderPath.inOrderFactory();
  }

  Iterator entryIterator()
  {
    Node localNode = (Node)this.rootReference.get();
    BstInOrderPath localBstInOrderPath = (BstInOrderPath)BstRangeOps.furthestPath(this.range, BstSide.LEFT, pathFactory(), localNode);
    return iteratorInDirection(localBstInOrderPath, BstSide.RIGHT);
  }

  Iterator descendingEntryIterator()
  {
    Node localNode = (Node)this.rootReference.get();
    BstInOrderPath localBstInOrderPath = (BstInOrderPath)BstRangeOps.furthestPath(this.range, BstSide.RIGHT, pathFactory(), localNode);
    return iteratorInDirection(localBstInOrderPath, BstSide.LEFT);
  }

  private Iterator iteratorInDirection(BstInOrderPath paramBstInOrderPath, final BstSide paramBstSide)
  {
    final AbstractSequentialIterator local1 = new AbstractSequentialIterator(paramBstInOrderPath)
    {
      protected BstInOrderPath computeNext(BstInOrderPath paramAnonymousBstInOrderPath)
      {
        if (!paramAnonymousBstInOrderPath.hasNext(paramBstSide))
          return null;
        BstInOrderPath localBstInOrderPath = paramAnonymousBstInOrderPath.next(paramBstSide);
        return TreeMultiset.this.range.contains(((TreeMultiset.Node)localBstInOrderPath.getTip()).getKey()) ? localBstInOrderPath : null;
      }
    };
    return new Iterator()
    {
      final TreeMultiset.ToRemove toRemove = new TreeMultiset.ToRemove(null);

      public boolean hasNext()
      {
        return local1.hasNext();
      }

      public Multiset.Entry next()
      {
        BstInOrderPath localBstInOrderPath = (BstInOrderPath)local1.next();
        return new TreeMultiset.LiveEntry(TreeMultiset.this, this.toRemove.setAndGet(((TreeMultiset.Node)localBstInOrderPath.getTip()).getKey()), ((TreeMultiset.Node)localBstInOrderPath.getTip()).elemCount(), null);
      }

      public void remove()
      {
        TreeMultiset.this.setCount(this.toRemove.getAndClear(), 0);
      }
    };
  }

  public void clear()
  {
    Node localNode1 = (Node)this.rootReference.get();
    Node localNode2 = (Node)BstRangeOps.minusRange(this.range, BstCountBasedBalancePolicies.fullRebalancePolicy(distinctAggregate()), nodeFactory(), localNode1);
    if (!this.rootReference.compareAndSet(localNode1, localNode2))
      throw new ConcurrentModificationException();
  }

  public SortedMultiset headMultiset(Object paramObject, BoundType paramBoundType)
  {
    Preconditions.checkNotNull(paramObject);
    return new TreeMultiset(this.range.intersect(GeneralRange.upTo(this.comparator, paramObject, paramBoundType)), this.rootReference);
  }

  public SortedMultiset tailMultiset(Object paramObject, BoundType paramBoundType)
  {
    Preconditions.checkNotNull(paramObject);
    return new TreeMultiset(this.range.intersect(GeneralRange.downTo(this.comparator, paramObject, paramBoundType)), this.rootReference);
  }

  public Comparator comparator()
  {
    return super.comparator();
  }

  private static long sizeOrZero(Node paramNode)
  {
    return paramNode == null ? 0L : paramNode.size;
  }

  private static int distinctOrZero(Node paramNode)
  {
    return paramNode == null ? 0 : paramNode.distinct;
  }

  private static int countOrZero(Node paramNode)
  {
    return paramNode == null ? 0 : paramNode.elemCount();
  }

  private BstAggregate distinctAggregate()
  {
    return DISTINCT_AGGREGATE;
  }

  private BstAggregate sizeAggregate()
  {
    return SIZE_AGGREGATE;
  }

  private BstNodeFactory nodeFactory()
  {
    return NODE_FACTORY;
  }

  @GwtIncompatible("java.io.ObjectOutputStream")
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeObject(elementSet().comparator());
    Serialization.writeMultiset(this, paramObjectOutputStream);
  }

  @GwtIncompatible("java.io.ObjectInputStream")
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    Comparator localComparator = (Comparator)paramObjectInputStream.readObject();
    Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set(this, localComparator);
    Serialization.getFieldSetter(TreeMultiset.class, "range").set(this, GeneralRange.all(localComparator));
    Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set(this, new Reference());
    Serialization.populateMultiset(this, paramObjectInputStream);
  }

  private final class ConditionalSetCountModifier extends TreeMultiset.MultisetModifier
  {
    private final int expectedCount;
    private final int setCount;

    private ConditionalSetCountModifier(int paramInt1, int arg3)
    {
      super(null);
      int i;
      Preconditions.checkArgument((i >= 0 ? 1 : 0) & (paramInt1 >= 0 ? 1 : 0));
      this.expectedCount = paramInt1;
      this.setCount = i;
    }

    int newCount(int paramInt)
    {
      return paramInt == this.expectedCount ? this.setCount : paramInt;
    }
  }

  private final class SetCountModifier extends TreeMultiset.MultisetModifier
  {
    private final int countToSet;

    private SetCountModifier(int arg2)
    {
      super(null);
      int i;
      Preconditions.checkArgument(i >= 0);
      this.countToSet = i;
    }

    int newCount(int paramInt)
    {
      return this.countToSet;
    }
  }

  private final class RemoveModifier extends TreeMultiset.MultisetModifier
  {
    private final int countToRemove;

    private RemoveModifier(int arg2)
    {
      super(null);
      int i;
      Preconditions.checkArgument(i > 0);
      this.countToRemove = i;
    }

    int newCount(int paramInt)
    {
      return Math.max(0, paramInt - this.countToRemove);
    }
  }

  private final class AddModifier extends TreeMultiset.MultisetModifier
  {
    private final int countToAdd;

    private AddModifier(int arg2)
    {
      super(null);
      int i;
      Preconditions.checkArgument(i > 0);
      this.countToAdd = i;
    }

    int newCount(int paramInt)
    {
      Preconditions.checkArgument(this.countToAdd <= 2147483647 - paramInt, "Cannot add this many elements");
      return paramInt + this.countToAdd;
    }
  }

  private abstract class MultisetModifier
    implements BstModifier
  {
    private MultisetModifier()
    {
    }

    abstract int newCount(int paramInt);

    public BstModificationResult modify(Object paramObject, TreeMultiset.Node paramNode)
    {
      int i = TreeMultiset.countOrZero(paramNode);
      int j = newCount(i);
      if (i == j)
        return BstModificationResult.identity(paramNode);
      if (j == 0)
        return BstModificationResult.rebalancingChange(paramNode, null);
      if (i == 0)
        return BstModificationResult.rebalancingChange(null, new TreeMultiset.Node(paramObject, j, null));
      return BstModificationResult.rebuildingChange(paramNode, new TreeMultiset.Node(paramNode.getKey(), j, null));
    }
  }

  private static final class Node extends BstNode
    implements Serializable
  {
    private final long size;
    private final int distinct;
    private static final long serialVersionUID = 0L;

    private Node(Object paramObject, int paramInt, Node paramNode1, Node paramNode2)
    {
      super(paramNode1, paramNode2);
      Preconditions.checkArgument(paramInt > 0);
      this.size = (paramInt + TreeMultiset.sizeOrZero(paramNode1) + TreeMultiset.sizeOrZero(paramNode2));
      this.distinct = (1 + TreeMultiset.distinctOrZero(paramNode1) + TreeMultiset.distinctOrZero(paramNode2));
    }

    int elemCount()
    {
      long l = this.size - TreeMultiset.sizeOrZero((Node)childOrNull(BstSide.LEFT)) - TreeMultiset.sizeOrZero((Node)childOrNull(BstSide.RIGHT));
      return Ints.checkedCast(l);
    }

    private Node(Object paramObject, int paramInt)
    {
      this(paramObject, paramInt, null, null);
    }
  }

  class LiveEntry extends Multisets.AbstractEntry
  {
    private TreeMultiset.Node expectedRoot = (TreeMultiset.Node)TreeMultiset.this.rootReference.get();
    private final Object element;
    private int count;

    private LiveEntry(Object paramInt, int arg3)
    {
      this.element = paramInt;
      int i;
      this.count = i;
    }

    public Object getElement()
    {
      return this.element;
    }

    public int getCount()
    {
      if (TreeMultiset.this.rootReference.get() == this.expectedRoot)
        return this.count;
      this.expectedRoot = ((TreeMultiset.Node)TreeMultiset.this.rootReference.get());
      return this.count = TreeMultiset.this.count(this.element);
    }
  }

  private static final class ToRemove
  {
    Optional element;

    Object setAndGet(Object paramObject)
    {
      this.element = Optional.fromNullable(paramObject);
      return paramObject;
    }

    Object getAndClear()
    {
      Preconditions.checkState(this.element != null);
      Object localObject = this.element.orNull();
      this.element = null;
      return localObject;
    }
  }

  static final class Reference
  {
    Object value;

    public Object get()
    {
      return this.value;
    }

    public boolean compareAndSet(Object paramObject1, Object paramObject2)
    {
      if (this.value == paramObject1)
      {
        this.value = paramObject2;
        return true;
      }
      return false;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.TreeMultiset
 * JD-Core Version:    0.6.2
 */