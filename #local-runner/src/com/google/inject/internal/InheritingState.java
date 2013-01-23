package com.google.inject.internal;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.Scope;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..ImmutableList.Builder;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Maps;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.TypeConverterBinding;
import com.google.inject.spi.TypeListenerBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class InheritingState
  implements State
{
  private final State parent;
  private final Map explicitBindingsMutable = $Maps.newLinkedHashMap();
  private final Map explicitBindings = Collections.unmodifiableMap(this.explicitBindingsMutable);
  private final Map scopes = $Maps.newHashMap();
  private final List converters = $Lists.newArrayList();
  private final List methodAspects = $Lists.newArrayList();
  private final List listenerBindings = $Lists.newArrayList();
  private final WeakKeySet blacklistedKeys = new WeakKeySet();
  private final Object lock;

  InheritingState(State paramState)
  {
    this.parent = ((State).Preconditions.checkNotNull(paramState, "parent"));
    this.lock = (paramState == State.NONE ? this : paramState.lock());
  }

  public State parent()
  {
    return this.parent;
  }

  public BindingImpl getExplicitBinding(Key paramKey)
  {
    Binding localBinding = (Binding)this.explicitBindings.get(paramKey);
    return localBinding != null ? (BindingImpl)localBinding : this.parent.getExplicitBinding(paramKey);
  }

  public Map getExplicitBindingsThisLevel()
  {
    return this.explicitBindings;
  }

  public void putBinding(Key paramKey, BindingImpl paramBindingImpl)
  {
    this.explicitBindingsMutable.put(paramKey, paramBindingImpl);
  }

  public Scope getScope(Class paramClass)
  {
    Scope localScope = (Scope)this.scopes.get(paramClass);
    return localScope != null ? localScope : this.parent.getScope(paramClass);
  }

  public void putAnnotation(Class paramClass, Scope paramScope)
  {
    this.scopes.put(paramClass, paramScope);
  }

  public Iterable getConvertersThisLevel()
  {
    return this.converters;
  }

  public void addConverter(TypeConverterBinding paramTypeConverterBinding)
  {
    this.converters.add(paramTypeConverterBinding);
  }

  public TypeConverterBinding getConverter(String paramString, TypeLiteral paramTypeLiteral, Errors paramErrors, Object paramObject)
  {
    Object localObject1 = null;
    for (Object localObject2 = this; localObject2 != State.NONE; localObject2 = ((State)localObject2).parent())
    {
      Iterator localIterator = ((State)localObject2).getConvertersThisLevel().iterator();
      while (localIterator.hasNext())
      {
        TypeConverterBinding localTypeConverterBinding = (TypeConverterBinding)localIterator.next();
        if (localTypeConverterBinding.getTypeMatcher().matches(paramTypeLiteral))
        {
          if (localObject1 != null)
            paramErrors.ambiguousTypeConversion(paramString, paramObject, paramTypeLiteral, (TypeConverterBinding)localObject1, localTypeConverterBinding);
          localObject1 = localTypeConverterBinding;
        }
      }
    }
    return localObject1;
  }

  public void addMethodAspect(MethodAspect paramMethodAspect)
  {
    this.methodAspects.add(paramMethodAspect);
  }

  public .ImmutableList getMethodAspects()
  {
    return new $ImmutableList.Builder().addAll(this.parent.getMethodAspects()).addAll(this.methodAspects).build();
  }

  public void addTypeListener(TypeListenerBinding paramTypeListenerBinding)
  {
    this.listenerBindings.add(paramTypeListenerBinding);
  }

  public List getTypeListenerBindings()
  {
    List localList = this.parent.getTypeListenerBindings();
    ArrayList localArrayList = new ArrayList(localList.size() + 1);
    localArrayList.addAll(localList);
    localArrayList.addAll(this.listenerBindings);
    return localArrayList;
  }

  public void blacklist(Key paramKey, Object paramObject)
  {
    this.parent.blacklist(paramKey, paramObject);
    this.blacklistedKeys.add(paramKey, paramObject);
  }

  public boolean isBlacklisted(Key paramKey)
  {
    return this.blacklistedKeys.contains(paramKey);
  }

  public Set getSourcesForBlacklistedKey(Key paramKey)
  {
    return this.blacklistedKeys.getSources(paramKey);
  }

  public Object lock()
  {
    return this.lock;
  }

  public Map getScopes()
  {
    return this.scopes;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InheritingState
 * JD-Core Version:    0.6.2
 */