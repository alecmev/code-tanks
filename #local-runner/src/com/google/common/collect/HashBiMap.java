package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

@GwtCompatible(emulated=true)
public final class HashBiMap extends AbstractBiMap
{

  @GwtIncompatible("Not needed in emulated source")
  private static final long serialVersionUID = 0L;

  public static HashBiMap create()
  {
    return new HashBiMap();
  }

  public static HashBiMap create(int paramInt)
  {
    return new HashBiMap(paramInt);
  }

  public static HashBiMap create(Map paramMap)
  {
    HashBiMap localHashBiMap = create(paramMap.size());
    localHashBiMap.putAll(paramMap);
    return localHashBiMap;
  }

  private HashBiMap()
  {
    super(new HashMap(), new HashMap());
  }

  private HashBiMap(int paramInt)
  {
    super(Maps.newHashMapWithExpectedSize(paramInt), Maps.newHashMapWithExpectedSize(paramInt));
  }

  public Object put(Object paramObject1, Object paramObject2)
  {
    return super.put(paramObject1, paramObject2);
  }

  public Object forcePut(Object paramObject1, Object paramObject2)
  {
    return super.forcePut(paramObject1, paramObject2);
  }

  @GwtIncompatible("java.io.ObjectOutputStream")
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    Serialization.writeMap(this, paramObjectOutputStream);
  }

  @GwtIncompatible("java.io.ObjectInputStream")
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    int i = Serialization.readCount(paramObjectInputStream);
    setDelegates(Maps.newHashMapWithExpectedSize(i), Maps.newHashMapWithExpectedSize(i));
    Serialization.populateMap(this, paramObjectInputStream, i);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.HashBiMap
 * JD-Core Version:    0.6.2
 */