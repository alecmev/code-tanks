package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.PrivateBinder;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..ImmutableMap;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Maps;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.Element;
import com.google.inject.spi.ElementVisitor;
import com.google.inject.spi.PrivateElements;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class PrivateElementsImpl
  implements PrivateElements
{
  private final Object source;
  private List elementsMutable = $Lists.newArrayList();
  private List exposureBuilders = $Lists.newArrayList();
  private $ImmutableList elements;
  private $ImmutableMap exposedKeysToSources;
  private Injector injector;

  public PrivateElementsImpl(Object paramObject)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
  }

  public Object getSource()
  {
    return this.source;
  }

  public List getElements()
  {
    if (this.elements == null)
    {
      this.elements = $ImmutableList.copyOf(this.elementsMutable);
      this.elementsMutable = null;
    }
    return this.elements;
  }

  public Injector getInjector()
  {
    return this.injector;
  }

  public void initInjector(Injector paramInjector)
  {
    $Preconditions.checkState(this.injector == null, "injector already initialized");
    this.injector = ((Injector).Preconditions.checkNotNull(paramInjector, "injector"));
  }

  public Set getExposedKeys()
  {
    if (this.exposedKeysToSources == null)
    {
      LinkedHashMap localLinkedHashMap = $Maps.newLinkedHashMap();
      Iterator localIterator = this.exposureBuilders.iterator();
      while (localIterator.hasNext())
      {
        ExposureBuilder localExposureBuilder = (ExposureBuilder)localIterator.next();
        localLinkedHashMap.put(localExposureBuilder.getKey(), localExposureBuilder.getSource());
      }
      this.exposedKeysToSources = $ImmutableMap.copyOf(localLinkedHashMap);
      this.exposureBuilders = null;
    }
    return this.exposedKeysToSources.keySet();
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public List getElementsMutable()
  {
    return this.elementsMutable;
  }

  public void addExposureBuilder(ExposureBuilder paramExposureBuilder)
  {
    this.exposureBuilders.add(paramExposureBuilder);
  }

  public void applyTo(Binder paramBinder)
  {
    PrivateBinder localPrivateBinder = paramBinder.withSource(this.source).newPrivateBinder();
    Iterator localIterator = getElements().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (Element)localIterator.next();
      ((Element)localObject).applyTo(localPrivateBinder);
    }
    getExposedKeys();
    localIterator = this.exposedKeysToSources.entrySet().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Map.Entry)localIterator.next();
      localPrivateBinder.withSource(((Map.Entry)localObject).getValue()).expose((Key)((Map.Entry)localObject).getKey());
    }
  }

  public Object getExposedSource(Key paramKey)
  {
    getExposedKeys();
    Object localObject = this.exposedKeysToSources.get(paramKey);
    $Preconditions.checkArgument(localObject != null, "%s not exposed by %s.", new Object[] { paramKey, this });
    return localObject;
  }

  public String toString()
  {
    return new $ToStringBuilder(PrivateElements.class).add("exposedKeys", getExposedKeys()).add("source", getSource()).toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.PrivateElementsImpl
 * JD-Core Version:    0.6.2
 */