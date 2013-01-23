package com.google.inject.internal;

import com.google.inject.internal.util..Maps;
import com.google.inject.spi.Dependency;
import java.util.Map;

final class InternalContext
{
  private Map constructionContexts = $Maps.newHashMap();
  private Dependency dependency;

  public ConstructionContext getConstructionContext(Object paramObject)
  {
    ConstructionContext localConstructionContext = (ConstructionContext)this.constructionContexts.get(paramObject);
    if (localConstructionContext == null)
    {
      localConstructionContext = new ConstructionContext();
      this.constructionContexts.put(paramObject, localConstructionContext);
    }
    return localConstructionContext;
  }

  public Dependency getDependency()
  {
    return this.dependency;
  }

  public Dependency setDependency(Dependency paramDependency)
  {
    Dependency localDependency = this.dependency;
    this.dependency = paramDependency;
    return localDependency;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InternalContext
 * JD-Core Version:    0.6.2
 */