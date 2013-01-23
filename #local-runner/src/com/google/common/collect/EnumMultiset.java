package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.Iterator;

@GwtCompatible(emulated=true)
public final class EnumMultiset extends AbstractMapBasedMultiset
{
  private transient Class type;

  @GwtIncompatible("Not needed in emulated source")
  private static final long serialVersionUID = 0L;

  public static EnumMultiset create(Class paramClass)
  {
    return new EnumMultiset(paramClass);
  }

  public static EnumMultiset create(Iterable paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    Preconditions.checkArgument(localIterator.hasNext(), "EnumMultiset constructor passed empty Iterable");
    EnumMultiset localEnumMultiset = new EnumMultiset(((Enum)localIterator.next()).getDeclaringClass());
    Iterables.addAll(localEnumMultiset, paramIterable);
    return localEnumMultiset;
  }

  private EnumMultiset(Class paramClass)
  {
    super(WellBehavedMap.wrap(new EnumMap(paramClass)));
    this.type = paramClass;
  }

  @GwtIncompatible("java.io.ObjectOutputStream")
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeObject(this.type);
    Serialization.writeMultiset(this, paramObjectOutputStream);
  }

  @GwtIncompatible("java.io.ObjectInputStream")
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    Class localClass = (Class)paramObjectInputStream.readObject();
    this.type = localClass;
    setBackingMap(WellBehavedMap.wrap(new EnumMap(this.type)));
    Serialization.populateMultiset(this, paramObjectInputStream);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.collect.EnumMultiset
 * JD-Core Version:    0.6.2
 */