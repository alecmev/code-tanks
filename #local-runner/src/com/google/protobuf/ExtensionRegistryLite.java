package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite
{
  private final Map extensionsByNumber;
  private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);

  public static ExtensionRegistryLite newInstance()
  {
    return new ExtensionRegistryLite();
  }

  public static ExtensionRegistryLite getEmptyRegistry()
  {
    return EMPTY;
  }

  public ExtensionRegistryLite getUnmodifiable()
  {
    return new ExtensionRegistryLite(this);
  }

  public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite paramMessageLite, int paramInt)
  {
    return (GeneratedMessageLite.GeneratedExtension)this.extensionsByNumber.get(new ObjectIntPair(paramMessageLite, paramInt));
  }

  public final void add(GeneratedMessageLite.GeneratedExtension paramGeneratedExtension)
  {
    this.extensionsByNumber.put(new ObjectIntPair(paramGeneratedExtension.getContainingTypeDefaultInstance(), paramGeneratedExtension.getNumber()), paramGeneratedExtension);
  }

  ExtensionRegistryLite()
  {
    this.extensionsByNumber = new HashMap();
  }

  ExtensionRegistryLite(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    if (paramExtensionRegistryLite == EMPTY)
      this.extensionsByNumber = Collections.emptyMap();
    else
      this.extensionsByNumber = Collections.unmodifiableMap(paramExtensionRegistryLite.extensionsByNumber);
  }

  private ExtensionRegistryLite(boolean paramBoolean)
  {
    this.extensionsByNumber = Collections.emptyMap();
  }

  private static final class ObjectIntPair
  {
    private final Object object;
    private final int number;

    ObjectIntPair(Object paramObject, int paramInt)
    {
      this.object = paramObject;
      this.number = paramInt;
    }

    public int hashCode()
    {
      return System.identityHashCode(this.object) * 65535 + this.number;
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof ObjectIntPair))
        return false;
      ObjectIntPair localObjectIntPair = (ObjectIntPair)paramObject;
      return (this.object == localObjectIntPair.object) && (this.number == localObjectIntPair.number);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.ExtensionRegistryLite
 * JD-Core Version:    0.6.2
 */