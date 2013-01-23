package com.google.inject.internal;

import com.google.inject.ConfigurationException;
import com.google.inject.Stage;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..Lists;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.InjectionRequest;
import com.google.inject.spi.StaticInjectionRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class InjectionRequestProcessor extends AbstractProcessor
{
  private final List staticInjections = $Lists.newArrayList();
  private final Initializer initializer;

  InjectionRequestProcessor(Errors paramErrors, Initializer paramInitializer)
  {
    super(paramErrors);
    this.initializer = paramInitializer;
  }

  public Boolean visit(StaticInjectionRequest paramStaticInjectionRequest)
  {
    this.staticInjections.add(new StaticInjection(this.injector, paramStaticInjectionRequest));
    return Boolean.valueOf(true);
  }

  public Boolean visit(InjectionRequest paramInjectionRequest)
  {
    Set localSet;
    try
    {
      localSet = paramInjectionRequest.getInjectionPoints();
    }
    catch (ConfigurationException localConfigurationException)
    {
      this.errors.merge(localConfigurationException.getErrorMessages());
      localSet = (Set)localConfigurationException.getPartialValue();
    }
    this.initializer.requestInjection(this.injector, paramInjectionRequest.getInstance(), paramInjectionRequest.getSource(), localSet);
    return Boolean.valueOf(true);
  }

  void validate()
  {
    Iterator localIterator = this.staticInjections.iterator();
    while (localIterator.hasNext())
    {
      StaticInjection localStaticInjection = (StaticInjection)localIterator.next();
      localStaticInjection.validate();
    }
  }

  void injectMembers()
  {
    Iterator localIterator = this.staticInjections.iterator();
    while (localIterator.hasNext())
    {
      StaticInjection localStaticInjection = (StaticInjection)localIterator.next();
      localStaticInjection.injectMembers();
    }
  }

  private class StaticInjection
  {
    final InjectorImpl injector;
    final Object source;
    final StaticInjectionRequest request;
    $ImmutableList memberInjectors;

    public StaticInjection(InjectorImpl paramStaticInjectionRequest, StaticInjectionRequest arg3)
    {
      this.injector = paramStaticInjectionRequest;
      Object localObject;
      this.source = localObject.getSource();
      this.request = localObject;
    }

    void validate()
    {
      Errors localErrors = InjectionRequestProcessor.this.errors.withSource(this.source);
      Set localSet;
      try
      {
        localSet = this.request.getInjectionPoints();
      }
      catch (ConfigurationException localConfigurationException)
      {
        InjectionRequestProcessor.this.errors.merge(localConfigurationException.getErrorMessages());
        localSet = (Set)localConfigurationException.getPartialValue();
      }
      this.memberInjectors = this.injector.membersInjectorStore.getInjectors(localSet, localErrors);
    }

    void injectMembers()
    {
      try
      {
        this.injector.callInContext(new ContextualCallable()
        {
          public Void call(InternalContext paramAnonymousInternalContext)
          {
            Iterator localIterator = InjectionRequestProcessor.StaticInjection.this.memberInjectors.iterator();
            while (localIterator.hasNext())
            {
              SingleMemberInjector localSingleMemberInjector = (SingleMemberInjector)localIterator.next();
              if ((InjectionRequestProcessor.StaticInjection.this.injector.options.stage != Stage.TOOL) || (localSingleMemberInjector.getInjectionPoint().isToolable()))
                localSingleMemberInjector.inject(InjectionRequestProcessor.this.errors, paramAnonymousInternalContext, null);
            }
            return null;
          }
        });
      }
      catch (ErrorsException localErrorsException)
      {
        throw new AssertionError();
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InjectionRequestProcessor
 * JD-Core Version:    0.6.2
 */