package com.google.inject.spi;

import com.google.inject.Key;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Objects;
import com.google.inject.internal.util..Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class Dependency
{
  private final InjectionPoint injectionPoint;
  private final Key key;
  private final boolean nullable;
  private final int parameterIndex;

  Dependency(InjectionPoint paramInjectionPoint, Key paramKey, boolean paramBoolean, int paramInt)
  {
    this.injectionPoint = paramInjectionPoint;
    this.key = ((Key).Preconditions.checkNotNull(paramKey, "key"));
    this.nullable = paramBoolean;
    this.parameterIndex = paramInt;
  }

  public static Dependency get(Key paramKey)
  {
    return new Dependency(null, paramKey, true, -1);
  }

  public static Set forInjectionPoints(Set paramSet)
  {
    ArrayList localArrayList = $Lists.newArrayList();
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      InjectionPoint localInjectionPoint = (InjectionPoint)localIterator.next();
      localArrayList.addAll(localInjectionPoint.getDependencies());
    }
    return .ImmutableSet.copyOf(localArrayList);
  }

  public Key getKey()
  {
    return this.key;
  }

  public boolean isNullable()
  {
    return this.nullable;
  }

  public InjectionPoint getInjectionPoint()
  {
    return this.injectionPoint;
  }

  public int getParameterIndex()
  {
    return this.parameterIndex;
  }

  public int hashCode()
  {
    return .Objects.hashCode(new Object[] { this.injectionPoint, Integer.valueOf(this.parameterIndex), this.key });
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Dependency))
    {
      Dependency localDependency = (Dependency)paramObject;
      return ($Objects.equal(this.injectionPoint, localDependency.injectionPoint)) && ($Objects.equal(Integer.valueOf(this.parameterIndex), Integer.valueOf(localDependency.parameterIndex))) && ($Objects.equal(this.key, localDependency.key));
    }
    return false;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.key);
    if (this.injectionPoint != null)
    {
      localStringBuilder.append("@").append(this.injectionPoint);
      if (this.parameterIndex != -1)
        localStringBuilder.append("[").append(this.parameterIndex).append("]");
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.Dependency
 * JD-Core Version:    0.6.2
 */