package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractList
  implements LazyStringList, RandomAccess
{
  public static final LazyStringList EMPTY = new UnmodifiableLazyStringList(new LazyStringArrayList());
  private final List list;

  public LazyStringArrayList()
  {
    this.list = new ArrayList();
  }

  public LazyStringArrayList(List paramList)
  {
    this.list = new ArrayList(paramList);
  }

  public String get(int paramInt)
  {
    Object localObject = this.list.get(paramInt);
    if ((localObject instanceof String))
      return (String)localObject;
    ByteString localByteString = (ByteString)localObject;
    String str = localByteString.toStringUtf8();
    if (Internal.isValidUtf8(localByteString))
      this.list.set(paramInt, str);
    return str;
  }

  public int size()
  {
    return this.list.size();
  }

  public String set(int paramInt, String paramString)
  {
    Object localObject = this.list.set(paramInt, paramString);
    return asString(localObject);
  }

  public void add(int paramInt, String paramString)
  {
    this.list.add(paramInt, paramString);
    this.modCount += 1;
  }

  public boolean addAll(int paramInt, Collection paramCollection)
  {
    boolean bool = this.list.addAll(paramInt, paramCollection);
    this.modCount += 1;
    return bool;
  }

  public String remove(int paramInt)
  {
    Object localObject = this.list.remove(paramInt);
    this.modCount += 1;
    return asString(localObject);
  }

  public void clear()
  {
    this.list.clear();
    this.modCount += 1;
  }

  public void add(ByteString paramByteString)
  {
    this.list.add(paramByteString);
    this.modCount += 1;
  }

  public ByteString getByteString(int paramInt)
  {
    Object localObject = this.list.get(paramInt);
    if ((localObject instanceof String))
    {
      ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
      this.list.set(paramInt, localByteString);
      return localByteString;
    }
    return (ByteString)localObject;
  }

  private String asString(Object paramObject)
  {
    if ((paramObject instanceof String))
      return (String)paramObject;
    return ((ByteString)paramObject).toStringUtf8();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.LazyStringArrayList
 * JD-Core Version:    0.6.2
 */