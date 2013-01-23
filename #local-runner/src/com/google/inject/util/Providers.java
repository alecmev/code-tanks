package com.google.inject.util;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.internal.util..Sets;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.ProviderWithDependencies;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Providers
{
  public static com.google.inject.Provider of(Object paramObject)
  {
    return new com.google.inject.Provider()
    {
      public Object get()
      {
        return this.val$instance;
      }

      public String toString()
      {
        return "of(" + this.val$instance + ")";
      }
    };
  }

  public static com.google.inject.Provider guicify(javax.inject.Provider paramProvider)
  {
    if ((paramProvider instanceof com.google.inject.Provider))
      return (com.google.inject.Provider)paramProvider;
    javax.inject.Provider localProvider = (javax.inject.Provider).Preconditions.checkNotNull(paramProvider, "provider");
    Set localSet = InjectionPoint.forInstanceMethodsAndFields(paramProvider.getClass());
    if (localSet.isEmpty())
      return new com.google.inject.Provider()
      {
        public Object get()
        {
          return this.val$delegate.get();
        }

        public String toString()
        {
          return "guicified(" + this.val$delegate + ")";
        }
      };
    HashSet localHashSet = $Sets.newHashSet();
    Object localObject = localSet.iterator();
    while (((Iterator)localObject).hasNext())
    {
      InjectionPoint localInjectionPoint = (InjectionPoint)((Iterator)localObject).next();
      localHashSet.addAll(localInjectionPoint.getDependencies());
    }
    localObject = $ImmutableSet.copyOf(localHashSet);
    return new ProviderWithDependencies()
    {
      @Inject
      void initialize(Injector paramAnonymousInjector)
      {
        paramAnonymousInjector.injectMembers(this.val$delegate);
      }

      public Set getDependencies()
      {
        return this.val$dependencies;
      }

      public Object get()
      {
        return this.val$delegate.get();
      }

      public String toString()
      {
        return "guicified(" + this.val$delegate + ")";
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.util.Providers
 * JD-Core Version:    0.6.2
 */