package com.google.inject.util;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;
import com.google.inject.Scope;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Iterables;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Maps;
import com.google.inject.internal.util..Sets;
import com.google.inject.spi.DefaultBindingScopingVisitor;
import com.google.inject.spi.DefaultElementVisitor;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import com.google.inject.spi.PrivateElements;
import com.google.inject.spi.ScopeBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Modules
{
  public static final Module EMPTY_MODULE = new Module()
  {
    public void configure(Binder paramAnonymousBinder)
    {
    }
  };

  public static OverriddenModuleBuilder override(Module[] paramArrayOfModule)
  {
    return new RealOverriddenModuleBuilder(Arrays.asList(paramArrayOfModule), null);
  }

  public static OverriddenModuleBuilder override(Iterable paramIterable)
  {
    return new RealOverriddenModuleBuilder(paramIterable, null);
  }

  public static Module combine(Module[] paramArrayOfModule)
  {
    return combine($ImmutableSet.of(paramArrayOfModule));
  }

  public static Module combine(Iterable paramIterable)
  {
    $ImmutableSet localImmutableSet = $ImmutableSet.copyOf(paramIterable);
    return new Module()
    {
      public void configure(Binder paramAnonymousBinder)
      {
        paramAnonymousBinder = paramAnonymousBinder.skipSources(new Class[] { getClass() });
        Iterator localIterator = this.val$modulesSet.iterator();
        while (localIterator.hasNext())
        {
          Module localModule = (Module)localIterator.next();
          paramAnonymousBinder.install(localModule);
        }
      }
    };
  }

  private static class ModuleWriter extends DefaultElementVisitor
  {
    protected final Binder binder;

    ModuleWriter(Binder paramBinder)
    {
      this.binder = paramBinder;
    }

    protected Void visitOther(Element paramElement)
    {
      paramElement.applyTo(this.binder);
      return null;
    }

    void writeAll(Iterable paramIterable)
    {
      Iterator localIterator = paramIterable.iterator();
      while (localIterator.hasNext())
      {
        Element localElement = (Element)localIterator.next();
        localElement.acceptVisitor(this);
      }
    }
  }

  private static final class RealOverriddenModuleBuilder
    implements Modules.OverriddenModuleBuilder
  {
    private final .ImmutableSet baseModules;

    private RealOverriddenModuleBuilder(Iterable paramIterable)
    {
      this.baseModules = $ImmutableSet.copyOf(paramIterable);
    }

    public Module with(Module[] paramArrayOfModule)
    {
      return with(Arrays.asList(paramArrayOfModule));
    }

    public Module with(final Iterable paramIterable)
    {
      return new AbstractModule()
      {
        public void configure()
        {
          Object localObject1 = binder();
          List localList = Elements.getElements(Modules.RealOverriddenModuleBuilder.this.baseModules);
          if (localList.size() == 1)
          {
            localObject2 = (Element).Iterables.getOnlyElement(localList);
            if ((localObject2 instanceof PrivateElements))
            {
              localObject3 = (PrivateElements)localObject2;
              localObject4 = ((Binder)localObject1).newPrivateBinder().withSource(((PrivateElements)localObject3).getSource());
              localObject5 = ((PrivateElements)localObject3).getExposedKeys().iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localObject6 = (Key)((Iterator)localObject5).next();
                ((PrivateBinder)localObject4).withSource(((PrivateElements)localObject3).getExposedSource((Key)localObject6)).expose((Key)localObject6);
              }
              localObject1 = localObject4;
              localList = ((PrivateElements)localObject3).getElements();
            }
          }
          Object localObject2 = localObject1;
          Object localObject3 = new LinkedHashSet(localList);
          Object localObject4 = Elements.getElements(paramIterable);
          Object localObject5 = $Sets.newHashSet();
          Object localObject6 = $Sets.newHashSet();
          new Modules.ModuleWriter((Binder)localObject2)
          {
            public Void visit(Binding paramAnonymous2Binding)
            {
              this.val$overriddenKeys.add(paramAnonymous2Binding.getKey());
              return (Void)super.visit(paramAnonymous2Binding);
            }

            public Void visit(ScopeBinding paramAnonymous2ScopeBinding)
            {
              this.val$overridesScopeAnnotations.add(paramAnonymous2ScopeBinding.getAnnotationType());
              return (Void)super.visit(paramAnonymous2ScopeBinding);
            }

            public Void visit(PrivateElements paramAnonymous2PrivateElements)
            {
              this.val$overriddenKeys.addAll(paramAnonymous2PrivateElements.getExposedKeys());
              return (Void)super.visit(paramAnonymous2PrivateElements);
            }
          }
          $writeAll((Iterable)localObject4);
          final HashMap localHashMap = $Maps.newHashMap();
          final ArrayList localArrayList = $Lists.newArrayList();
          new Modules.ModuleWriter((Binder)localObject2)
          {
            public Void visit(Binding paramAnonymous2Binding)
            {
              if (!this.val$overriddenKeys.remove(paramAnonymous2Binding.getKey()))
              {
                super.visit(paramAnonymous2Binding);
                Scope localScope = Modules.RealOverriddenModuleBuilder.1.this.getScopeInstanceOrNull(paramAnonymous2Binding);
                if (localScope != null)
                  localHashMap.put(localScope, paramAnonymous2Binding.getSource());
              }
              return null;
            }

            void rewrite(Binder paramAnonymous2Binder, PrivateElements paramAnonymous2PrivateElements, Set paramAnonymous2Set)
            {
              PrivateBinder localPrivateBinder = paramAnonymous2Binder.withSource(paramAnonymous2PrivateElements.getSource()).newPrivateBinder();
              HashSet localHashSet = $Sets.newHashSet();
              Iterator localIterator = paramAnonymous2PrivateElements.getExposedKeys().iterator();
              Object localObject;
              while (localIterator.hasNext())
              {
                localObject = (Key)localIterator.next();
                if (paramAnonymous2Set.remove(localObject))
                  localHashSet.add(localObject);
                else
                  localPrivateBinder.withSource(paramAnonymous2PrivateElements.getExposedSource((Key)localObject)).expose((Key)localObject);
              }
              localIterator = paramAnonymous2PrivateElements.getElements().iterator();
              while (localIterator.hasNext())
              {
                localObject = (Element)localIterator.next();
                if ((!(localObject instanceof Binding)) || (!localHashSet.remove(((Binding)localObject).getKey())))
                  if ((localObject instanceof PrivateElements))
                    rewrite(localPrivateBinder, (PrivateElements)localObject, localHashSet);
                  else
                    ((Element)localObject).applyTo(localPrivateBinder);
              }
            }

            public Void visit(PrivateElements paramAnonymous2PrivateElements)
            {
              rewrite(this.binder, paramAnonymous2PrivateElements, this.val$overriddenKeys);
              return null;
            }

            public Void visit(ScopeBinding paramAnonymous2ScopeBinding)
            {
              localArrayList.add(paramAnonymous2ScopeBinding);
              return null;
            }
          }
          $writeAll((Iterable)localObject3);
          new Modules.ModuleWriter((Binder)localObject2)
          {
            public Void visit(ScopeBinding paramAnonymous2ScopeBinding)
            {
              if (!this.val$overridesScopeAnnotations.remove(paramAnonymous2ScopeBinding.getAnnotationType()))
              {
                super.visit(paramAnonymous2ScopeBinding);
              }
              else
              {
                Object localObject = localHashMap.get(paramAnonymous2ScopeBinding.getScope());
                if (localObject != null)
                  this.binder.withSource(localObject).addError("The scope for @%s is bound directly and cannot be overridden.", new Object[] { paramAnonymous2ScopeBinding.getAnnotationType().getSimpleName() });
              }
              return null;
            }
          }
          $writeAll(localArrayList);
        }

        private Scope getScopeInstanceOrNull(Binding paramAnonymousBinding)
        {
          return (Scope)paramAnonymousBinding.acceptScopingVisitor(new DefaultBindingScopingVisitor()
          {
            public Scope visitScope(Scope paramAnonymous2Scope)
            {
              return paramAnonymous2Scope;
            }
          });
        }
      };
    }
  }

  public static abstract interface OverriddenModuleBuilder
  {
    public abstract Module with(Module[] paramArrayOfModule);

    public abstract Module with(Iterable paramIterable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.util.Modules
 * JD-Core Version:    0.6.2
 */