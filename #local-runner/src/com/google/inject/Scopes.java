package com.google.inject;

import com.google.inject.internal.CircularDependencyProxy;
import com.google.inject.internal.InternalInjectorCreator;
import com.google.inject.internal.LinkedBindingImpl;
import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.ExposedBinding;
import com.google.inject.spi.PrivateElements;

public class Scopes
{
  private static final Object NULL = new Object();
  public static final Scope SINGLETON = new Scope()
  {
    public Provider scope(Key paramAnonymousKey, final Provider paramAnonymousProvider)
    {
      return new Provider()
      {
        private volatile Object instance;

        public Object get()
        {
          if (this.instance == null)
            synchronized (InternalInjectorCreator.class)
            {
              if (this.instance == null)
              {
                localObject1 = paramAnonymousProvider.get();
                if ((localObject1 instanceof CircularDependencyProxy))
                  return localObject1;
                Object localObject2 = localObject1 == null ? Scopes.NULL : localObject1;
                if ((this.instance != null) && (this.instance != localObject2))
                  throw new ProvisionException("Provider was reentrant while creating a singleton");
                this.instance = localObject2;
              }
            }
          ??? = this.instance;
          Object localObject1 = ??? != Scopes.NULL ? ??? : null;
          return localObject1;
        }

        public String toString()
        {
          return String.format("%s[%s]", new Object[] { paramAnonymousProvider, Scopes.SINGLETON });
        }
      };
    }

    public String toString()
    {
      return "Scopes.SINGLETON";
    }
  };
  public static final Scope NO_SCOPE = new Scope()
  {
    public Provider scope(Key paramAnonymousKey, Provider paramAnonymousProvider)
    {
      return paramAnonymousProvider;
    }

    public String toString()
    {
      return "Scopes.NO_SCOPE";
    }
  };

  public static boolean isSingleton(Binding paramBinding)
  {
    while (true)
    {
      boolean bool = ((Boolean)paramBinding.acceptScopingVisitor(new BindingScopingVisitor()
      {
        public Boolean visitNoScoping()
        {
          return Boolean.valueOf(false);
        }

        public Boolean visitScopeAnnotation(Class paramAnonymousClass)
        {
          return Boolean.valueOf((paramAnonymousClass == Singleton.class) || (paramAnonymousClass == javax.inject.Singleton.class));
        }

        public Boolean visitScope(Scope paramAnonymousScope)
        {
          return Boolean.valueOf(paramAnonymousScope == Scopes.SINGLETON);
        }

        public Boolean visitEagerSingleton()
        {
          return Boolean.valueOf(true);
        }
      })).booleanValue();
      if (bool)
        return true;
      Object localObject1;
      Object localObject2;
      if ((paramBinding instanceof LinkedBindingImpl))
      {
        localObject1 = (LinkedBindingImpl)paramBinding;
        localObject2 = ((LinkedBindingImpl)localObject1).getInjector();
        if (localObject2 != null)
          paramBinding = ((Injector)localObject2).getBinding(((LinkedBindingImpl)localObject1).getLinkedKey());
        else
          break;
      }
      else
      {
        if (!(paramBinding instanceof ExposedBinding))
          break;
        localObject1 = (ExposedBinding)paramBinding;
        localObject2 = ((ExposedBinding)localObject1).getPrivateElements().getInjector();
        if (localObject2 == null)
          break;
        paramBinding = ((Injector)localObject2).getBinding(((ExposedBinding)localObject1).getKey());
      }
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Scopes
 * JD-Core Version:    0.6.2
 */