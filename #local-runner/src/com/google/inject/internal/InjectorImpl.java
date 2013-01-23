package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.ConfigurationException;
import com.google.inject.ImplementedBy;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Module;
import com.google.inject.ProvidedBy;
import com.google.inject.Provider;
import com.google.inject.ProvisionException;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..ImmutableMap;
import com.google.inject.internal.util..ImmutableMap.Builder;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Maps;
import com.google.inject.internal.util..Nullable;
import com.google.inject.internal.util..Objects;
import com.google.inject.internal.util..SourceProvider;
import com.google.inject.internal.util..ToStringBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ConvertedConstantBinding;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.HasDependencies;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.ProviderBinding;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeConverterBinding;
import com.google.inject.util.Providers;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class InjectorImpl
  implements Injector, Lookups
{
  public static final TypeLiteral STRING_TYPE = TypeLiteral.get(String.class);
  final State state;
  final InjectorImpl parent;
  final BindingsMultimap bindingsMultimap = new BindingsMultimap(null);
  final InjectorOptions options;
  final Map jitBindings = $Maps.newHashMap();
  Lookups lookups = new DeferredLookups(this);
  final ConstructorInjectorStore constructors = new ConstructorInjectorStore(this);
  MembersInjectorStore membersInjectorStore;
  final ThreadLocal localContext;

  InjectorImpl(@$Nullable InjectorImpl paramInjectorImpl, State paramState, InjectorOptions paramInjectorOptions)
  {
    this.parent = paramInjectorImpl;
    this.state = paramState;
    this.options = paramInjectorOptions;
    if (paramInjectorImpl != null)
      this.localContext = paramInjectorImpl.localContext;
    else
      this.localContext = new ThreadLocal()
      {
        protected Object[] initialValue()
        {
          return new Object[1];
        }
      };
  }

  void index()
  {
    Iterator localIterator = this.state.getExplicitBindingsThisLevel().values().iterator();
    while (localIterator.hasNext())
    {
      Binding localBinding = (Binding)localIterator.next();
      index(localBinding);
    }
  }

  void index(Binding paramBinding)
  {
    this.bindingsMultimap.put(paramBinding.getKey().getTypeLiteral(), paramBinding);
  }

  public List findBindingsByType(TypeLiteral paramTypeLiteral)
  {
    return this.bindingsMultimap.getAll(paramTypeLiteral);
  }

  public BindingImpl getBinding(Key paramKey)
  {
    Errors localErrors = new Errors(paramKey);
    try
    {
      BindingImpl localBindingImpl = getBindingOrThrow(paramKey, localErrors, JitLimitation.EXISTING_JIT);
      localErrors.throwConfigurationExceptionIfErrorsExist();
      return localBindingImpl;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ConfigurationException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public BindingImpl getExistingBinding(Key paramKey)
  {
    BindingImpl localBindingImpl1 = this.state.getExplicitBinding(paramKey);
    if (localBindingImpl1 != null)
      return localBindingImpl1;
    synchronized (this.state.lock())
    {
      for (InjectorImpl localInjectorImpl = this; localInjectorImpl != null; localInjectorImpl = localInjectorImpl.parent)
      {
        BindingImpl localBindingImpl2 = (BindingImpl)localInjectorImpl.jitBindings.get(paramKey);
        if (localBindingImpl2 != null)
          return localBindingImpl2;
      }
    }
    if (isProvider(paramKey))
      try
      {
        ??? = getProvidedKey(paramKey, new Errors());
        if (getExistingBinding((Key)???) != null)
          return getBinding(paramKey);
      }
      catch (ErrorsException localErrorsException)
      {
        throw new ConfigurationException(localErrorsException.getErrors().getMessages());
      }
    return null;
  }

  BindingImpl getBindingOrThrow(Key paramKey, Errors paramErrors, JitLimitation paramJitLimitation)
    throws ErrorsException
  {
    BindingImpl localBindingImpl = this.state.getExplicitBinding(paramKey);
    if (localBindingImpl != null)
      return localBindingImpl;
    return getJustInTimeBinding(paramKey, paramErrors, paramJitLimitation);
  }

  public Binding getBinding(Class paramClass)
  {
    return getBinding(Key.get(paramClass));
  }

  public Injector getParent()
  {
    return this.parent;
  }

  public Injector createChildInjector(Iterable paramIterable)
  {
    return new InternalInjectorCreator().parentInjector(this).addModules(paramIterable).build();
  }

  public Injector createChildInjector(Module[] paramArrayOfModule)
  {
    return createChildInjector($ImmutableList.of(paramArrayOfModule));
  }

  private BindingImpl getJustInTimeBinding(Key paramKey, Errors paramErrors, JitLimitation paramJitLimitation)
    throws ErrorsException
  {
    int i = (isProvider(paramKey)) || (isTypeLiteral(paramKey)) || (isMembersInjector(paramKey)) ? 1 : 0;
    synchronized (this.state.lock())
    {
      for (InjectorImpl localInjectorImpl = this; localInjectorImpl != null; localInjectorImpl = localInjectorImpl.parent)
      {
        BindingImpl localBindingImpl = (BindingImpl)localInjectorImpl.jitBindings.get(paramKey);
        if (localBindingImpl != null)
        {
          if ((this.options.jitDisabled) && (paramJitLimitation == JitLimitation.NO_JIT) && (i == 0) && (!(localBindingImpl instanceof ConvertedConstantBindingImpl)))
            throw paramErrors.jitDisabled(paramKey).toException();
          return localBindingImpl;
        }
      }
      return createJustInTimeBindingRecursive(paramKey, paramErrors, this.options.jitDisabled, paramJitLimitation);
    }
  }

  private static boolean isProvider(Key paramKey)
  {
    return paramKey.getTypeLiteral().getRawType().equals(Provider.class);
  }

  private static boolean isTypeLiteral(Key paramKey)
  {
    return paramKey.getTypeLiteral().getRawType().equals(TypeLiteral.class);
  }

  private static Key getProvidedKey(Key paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Type localType1 = paramKey.getTypeLiteral().getType();
    if (!(localType1 instanceof ParameterizedType))
      throw paramErrors.cannotInjectRawProvider().toException();
    Type localType2 = ((ParameterizedType)localType1).getActualTypeArguments()[0];
    Key localKey = paramKey.ofType(localType2);
    return localKey;
  }

  private static boolean isMembersInjector(Key paramKey)
  {
    return (paramKey.getTypeLiteral().getRawType().equals(MembersInjector.class)) && (paramKey.getAnnotationType() == null);
  }

  private BindingImpl createMembersInjectorBinding(Key paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Type localType = paramKey.getTypeLiteral().getType();
    if (!(localType instanceof ParameterizedType))
      throw paramErrors.cannotInjectRawMembersInjector().toException();
    TypeLiteral localTypeLiteral = TypeLiteral.get(((ParameterizedType)localType).getActualTypeArguments()[0]);
    MembersInjectorImpl localMembersInjectorImpl = this.membersInjectorStore.get(localTypeLiteral, paramErrors);
    ConstantFactory localConstantFactory = new ConstantFactory(Initializables.of(localMembersInjectorImpl));
    return new InstanceBindingImpl(this, paramKey, .SourceProvider.UNKNOWN_SOURCE, localConstantFactory, .ImmutableSet.of(), localMembersInjectorImpl);
  }

  private BindingImpl createProviderBinding(Key paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Key localKey = getProvidedKey(paramKey, paramErrors);
    BindingImpl localBindingImpl = getBindingOrThrow(localKey, paramErrors, JitLimitation.NO_JIT);
    return new ProviderBindingImpl(this, paramKey, localBindingImpl);
  }

  private BindingImpl convertConstantStringBinding(Key paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Key localKey = paramKey.ofType(STRING_TYPE);
    BindingImpl localBindingImpl = this.state.getExplicitBinding(localKey);
    if ((localBindingImpl == null) || (!localBindingImpl.isConstant()))
      return null;
    String str = (String)localBindingImpl.getProvider().get();
    Object localObject1 = localBindingImpl.getSource();
    TypeLiteral localTypeLiteral = paramKey.getTypeLiteral();
    TypeConverterBinding localTypeConverterBinding = this.state.getConverter(str, localTypeLiteral, paramErrors, localObject1);
    if (localTypeConverterBinding == null)
      return null;
    try
    {
      Object localObject2 = localTypeConverterBinding.getTypeConverter().convert(str, localTypeLiteral);
      if (localObject2 == null)
        throw paramErrors.converterReturnedNull(str, localObject1, localTypeLiteral, localTypeConverterBinding).toException();
      if (!localTypeLiteral.getRawType().isInstance(localObject2))
        throw paramErrors.conversionTypeError(str, localObject1, localTypeLiteral, localTypeConverterBinding, localObject2).toException();
      return new ConvertedConstantBindingImpl(this, paramKey, localObject2, localBindingImpl, localTypeConverterBinding);
    }
    catch (ErrorsException localErrorsException)
    {
      throw localErrorsException;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw paramErrors.conversionError(str, localObject1, localTypeLiteral, localTypeConverterBinding, localRuntimeException).toException();
    }
  }

  void initializeBinding(BindingImpl paramBindingImpl, Errors paramErrors)
    throws ErrorsException
  {
    if ((paramBindingImpl instanceof ConstructorBindingImpl))
      ((ConstructorBindingImpl)paramBindingImpl).initialize(this, paramErrors);
  }

  void initializeJitBinding(BindingImpl paramBindingImpl, Errors paramErrors)
    throws ErrorsException
  {
    if ((paramBindingImpl instanceof ConstructorBindingImpl))
    {
      Key localKey = paramBindingImpl.getKey();
      this.jitBindings.put(localKey, paramBindingImpl);
      int i = 0;
      ConstructorBindingImpl localConstructorBindingImpl = (ConstructorBindingImpl)paramBindingImpl;
      try
      {
        localConstructorBindingImpl.initialize(this, paramErrors);
        i = 1;
      }
      finally
      {
        if (i == 0)
        {
          removeFailedJitBinding(localKey, null);
          cleanup(paramBindingImpl, new HashSet());
        }
      }
    }
  }

  private boolean cleanup(BindingImpl paramBindingImpl, Set paramSet)
  {
    boolean bool1 = false;
    Set localSet = getInternalDependencies(paramBindingImpl);
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      Dependency localDependency = (Dependency)localIterator.next();
      Key localKey = localDependency.getKey();
      InjectionPoint localInjectionPoint = localDependency.getInjectionPoint();
      if (paramSet.add(localKey))
      {
        BindingImpl localBindingImpl = (BindingImpl)this.jitBindings.get(localKey);
        if (localBindingImpl != null)
        {
          boolean bool2 = cleanup(localBindingImpl, paramSet);
          if ((localBindingImpl instanceof ConstructorBindingImpl))
          {
            ConstructorBindingImpl localConstructorBindingImpl = (ConstructorBindingImpl)localBindingImpl;
            localInjectionPoint = localConstructorBindingImpl.getInternalConstructor();
            if (!localConstructorBindingImpl.isInitialized())
              bool2 = true;
          }
          if (bool2)
          {
            removeFailedJitBinding(localKey, localInjectionPoint);
            bool1 = true;
          }
        }
        else if (this.state.getExplicitBinding(localKey) == null)
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }

  private void removeFailedJitBinding(Key paramKey, InjectionPoint paramInjectionPoint)
  {
    this.jitBindings.remove(paramKey);
    this.membersInjectorStore.remove(paramKey.getTypeLiteral());
    if (paramInjectionPoint != null)
      this.constructors.remove(paramInjectionPoint);
  }

  private Set getInternalDependencies(BindingImpl paramBindingImpl)
  {
    if ((paramBindingImpl instanceof ConstructorBindingImpl))
      return ((ConstructorBindingImpl)paramBindingImpl).getInternalDependencies();
    if ((paramBindingImpl instanceof HasDependencies))
      return ((HasDependencies)paramBindingImpl).getDependencies();
    return .ImmutableSet.of();
  }

  BindingImpl createUninitializedBinding(Key paramKey, Scoping paramScoping, Object paramObject, Errors paramErrors, boolean paramBoolean)
    throws ErrorsException
  {
    Class localClass = paramKey.getTypeLiteral().getRawType();
    if ((localClass.isArray()) || (localClass.isEnum()))
      throw paramErrors.missingImplementation(paramKey).toException();
    if (localClass == TypeLiteral.class)
    {
      localObject = createTypeLiteralBinding(paramKey, paramErrors);
      return localObject;
    }
    Object localObject = (ImplementedBy)localClass.getAnnotation(ImplementedBy.class);
    if (localObject != null)
    {
      Annotations.checkForMisplacedScopeAnnotations(localClass, paramObject, paramErrors);
      return createImplementedByBinding(paramKey, paramScoping, (ImplementedBy)localObject, paramErrors);
    }
    ProvidedBy localProvidedBy = (ProvidedBy)localClass.getAnnotation(ProvidedBy.class);
    if (localProvidedBy != null)
    {
      Annotations.checkForMisplacedScopeAnnotations(localClass, paramObject, paramErrors);
      return createProvidedByBinding(paramKey, paramScoping, localProvidedBy, paramErrors);
    }
    return ConstructorBindingImpl.create(this, paramKey, null, paramObject, paramScoping, paramErrors, (paramBoolean) && (this.options.jitDisabled));
  }

  private BindingImpl createTypeLiteralBinding(Key paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Type localType1 = paramKey.getTypeLiteral().getType();
    if (!(localType1 instanceof ParameterizedType))
      throw paramErrors.cannotInjectRawTypeLiteral().toException();
    ParameterizedType localParameterizedType = (ParameterizedType)localType1;
    Type localType2 = localParameterizedType.getActualTypeArguments()[0];
    if ((!(localType2 instanceof Class)) && (!(localType2 instanceof GenericArrayType)) && (!(localType2 instanceof ParameterizedType)))
      throw paramErrors.cannotInjectTypeLiteralOf(localType2).toException();
    TypeLiteral localTypeLiteral = TypeLiteral.get(localType2);
    ConstantFactory localConstantFactory = new ConstantFactory(Initializables.of(localTypeLiteral));
    return new InstanceBindingImpl(this, paramKey, .SourceProvider.UNKNOWN_SOURCE, localConstantFactory, .ImmutableSet.of(), localTypeLiteral);
  }

  BindingImpl createProvidedByBinding(Key paramKey, Scoping paramScoping, ProvidedBy paramProvidedBy, Errors paramErrors)
    throws ErrorsException
  {
    final Class localClass1 = paramKey.getTypeLiteral().getRawType();
    final Class localClass2 = paramProvidedBy.value();
    if (localClass2 == localClass1)
      throw paramErrors.recursiveProviderType().toException();
    final Key localKey = Key.get(localClass2);
    final BindingImpl localBindingImpl = getBindingOrThrow(localKey, paramErrors, JitLimitation.NEW_OR_EXISTING_JIT);
    InternalFactory local2 = new InternalFactory()
    {
      public Object get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency paramAnonymousDependency, boolean paramAnonymousBoolean)
        throws ErrorsException
      {
        paramAnonymousErrors = paramAnonymousErrors.withSource(localKey);
        Provider localProvider = (Provider)localBindingImpl.getInternalFactory().get(paramAnonymousErrors, paramAnonymousInternalContext, paramAnonymousDependency, true);
        try
        {
          Object localObject1 = localProvider.get();
          if ((localObject1 != null) && (!localClass1.isInstance(localObject1)))
            throw paramAnonymousErrors.subtypeNotProvided(localClass2, localClass1).toException();
          Object localObject2 = localObject1;
          return localObject2;
        }
        catch (RuntimeException localRuntimeException)
        {
          throw paramAnonymousErrors.errorInProvider(localRuntimeException).toException();
        }
      }
    };
    Class localClass3 = localClass1;
    return new LinkedProviderBindingImpl(this, paramKey, localClass3, Scoping.scope(paramKey, this, local2, localClass3, paramScoping), paramScoping, localKey);
  }

  private BindingImpl createImplementedByBinding(Key paramKey, Scoping paramScoping, ImplementedBy paramImplementedBy, Errors paramErrors)
    throws ErrorsException
  {
    Class localClass1 = paramKey.getTypeLiteral().getRawType();
    Class localClass2 = paramImplementedBy.value();
    if (localClass2 == localClass1)
      throw paramErrors.recursiveImplementationType().toException();
    if (!localClass1.isAssignableFrom(localClass2))
      throw paramErrors.notASubtype(localClass2, localClass1).toException();
    Class localClass3 = localClass2;
    final Key localKey = Key.get(localClass3);
    final BindingImpl localBindingImpl = getBindingOrThrow(localKey, paramErrors, JitLimitation.NEW_OR_EXISTING_JIT);
    InternalFactory local3 = new InternalFactory()
    {
      public Object get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency paramAnonymousDependency, boolean paramAnonymousBoolean)
        throws ErrorsException
      {
        return localBindingImpl.getInternalFactory().get(paramAnonymousErrors.withSource(localKey), paramAnonymousInternalContext, paramAnonymousDependency, true);
      }
    };
    Class localClass4 = localClass1;
    return new LinkedBindingImpl(this, paramKey, localClass4, Scoping.scope(paramKey, this, local3, localClass4, paramScoping), paramScoping, localKey);
  }

  private BindingImpl createJustInTimeBindingRecursive(Key paramKey, Errors paramErrors, boolean paramBoolean, JitLimitation paramJitLimitation)
    throws ErrorsException
  {
    if (this.parent != null)
      try
      {
        return this.parent.createJustInTimeBindingRecursive(paramKey, new Errors(), paramBoolean, this.parent.options.jitDisabled ? JitLimitation.NO_JIT : paramJitLimitation);
      }
      catch (ErrorsException localErrorsException)
      {
      }
    if (this.state.isBlacklisted(paramKey))
    {
      localObject = this.state.getSourcesForBlacklistedKey(paramKey);
      throw paramErrors.childBindingAlreadySet(paramKey, (Set)localObject).toException();
    }
    Object localObject = createJustInTimeBinding(paramKey, paramErrors, paramBoolean, paramJitLimitation);
    this.state.parent().blacklist(paramKey, ((BindingImpl)localObject).getSource());
    this.jitBindings.put(paramKey, localObject);
    return localObject;
  }

  private BindingImpl createJustInTimeBinding(Key paramKey, Errors paramErrors, boolean paramBoolean, JitLimitation paramJitLimitation)
    throws ErrorsException
  {
    int i = paramErrors.size();
    if (this.state.isBlacklisted(paramKey))
    {
      localObject = this.state.getSourcesForBlacklistedKey(paramKey);
      throw paramErrors.childBindingAlreadySet(paramKey, (Set)localObject).toException();
    }
    if (isProvider(paramKey))
    {
      localObject = createProviderBinding(paramKey, paramErrors);
      return localObject;
    }
    if (isMembersInjector(paramKey))
    {
      localObject = createMembersInjectorBinding(paramKey, paramErrors);
      return localObject;
    }
    Object localObject = convertConstantStringBinding(paramKey, paramErrors);
    if (localObject != null)
      return localObject;
    if ((!isTypeLiteral(paramKey)) && (paramBoolean) && (paramJitLimitation != JitLimitation.NEW_OR_EXISTING_JIT))
      throw paramErrors.jitDisabled(paramKey).toException();
    if (paramKey.getAnnotationType() != null)
    {
      if (paramKey.hasAttributes())
        try
        {
          Errors localErrors = new Errors();
          return getBindingOrThrow(paramKey.withoutAttributes(), localErrors, JitLimitation.NO_JIT);
        }
        catch (ErrorsException localErrorsException)
        {
        }
      throw paramErrors.missingImplementation(paramKey).toException();
    }
    Class localClass = paramKey.getTypeLiteral().getRawType();
    BindingImpl localBindingImpl = createUninitializedBinding(paramKey, Scoping.UNSCOPED, localClass, paramErrors, true);
    paramErrors.throwIfNewErrors(i);
    initializeJitBinding(localBindingImpl, paramErrors);
    return localBindingImpl;
  }

  InternalFactory getInternalFactory(Key paramKey, Errors paramErrors, JitLimitation paramJitLimitation)
    throws ErrorsException
  {
    return getBindingOrThrow(paramKey, paramErrors, paramJitLimitation).getInternalFactory();
  }

  public Map getBindings()
  {
    return this.state.getExplicitBindingsThisLevel();
  }

  public Map getAllBindings()
  {
    synchronized (this.state.lock())
    {
      return new $ImmutableMap.Builder().putAll(this.state.getExplicitBindingsThisLevel()).putAll(this.jitBindings).build();
    }
  }

  public Map getScopeBindings()
  {
    return .ImmutableMap.copyOf(this.state.getScopes());
  }

  public Set getTypeConverterBindings()
  {
    return .ImmutableSet.copyOf(this.state.getConvertersThisLevel());
  }

  SingleParameterInjector[] getParametersInjectors(List paramList, Errors paramErrors)
    throws ErrorsException
  {
    if (paramList.isEmpty())
      return null;
    int i = paramErrors.size();
    SingleParameterInjector[] arrayOfSingleParameterInjector = new SingleParameterInjector[paramList.size()];
    int j = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Dependency localDependency = (Dependency)localIterator.next();
      try
      {
        arrayOfSingleParameterInjector[(j++)] = createParameterInjector(localDependency, paramErrors.withSource(localDependency));
      }
      catch (ErrorsException localErrorsException)
      {
      }
    }
    paramErrors.throwIfNewErrors(i);
    return arrayOfSingleParameterInjector;
  }

  SingleParameterInjector createParameterInjector(Dependency paramDependency, Errors paramErrors)
    throws ErrorsException
  {
    InternalFactory localInternalFactory = getInternalFactory(paramDependency.getKey(), paramErrors, JitLimitation.NO_JIT);
    return new SingleParameterInjector(paramDependency, localInternalFactory);
  }

  public void injectMembers(Object paramObject)
  {
    MembersInjector localMembersInjector = getMembersInjector(paramObject.getClass());
    localMembersInjector.injectMembers(paramObject);
  }

  public MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral)
  {
    Errors localErrors = new Errors(paramTypeLiteral);
    try
    {
      return this.membersInjectorStore.get(paramTypeLiteral, localErrors);
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ConfigurationException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public MembersInjector getMembersInjector(Class paramClass)
  {
    return getMembersInjector(TypeLiteral.get(paramClass));
  }

  public Provider getProvider(Class paramClass)
  {
    return getProvider(Key.get(paramClass));
  }

  Provider getProviderOrThrow(Key paramKey, Errors paramErrors)
    throws ErrorsException
  {
    final InternalFactory localInternalFactory = getInternalFactory(paramKey, paramErrors, JitLimitation.NO_JIT);
    final Dependency localDependency = Dependency.get(paramKey);
    return new Provider()
    {
      public Object get()
      {
        final Errors localErrors = new Errors(localDependency);
        try
        {
          Object localObject = InjectorImpl.this.callInContext(new ContextualCallable()
          {
            public Object call(InternalContext paramAnonymous2InternalContext)
              throws ErrorsException
            {
              Dependency localDependency = paramAnonymous2InternalContext.setDependency(InjectorImpl.4.this.val$dependency);
              try
              {
                Object localObject1 = InjectorImpl.4.this.val$factory.get(localErrors, paramAnonymous2InternalContext, InjectorImpl.4.this.val$dependency, false);
                return localObject1;
              }
              finally
              {
                paramAnonymous2InternalContext.setDependency(localDependency);
              }
            }
          });
          localErrors.throwIfNewErrors(0);
          return localObject;
        }
        catch (ErrorsException localErrorsException)
        {
          throw new ProvisionException(localErrors.merge(localErrorsException.getErrors()).getMessages());
        }
      }

      public String toString()
      {
        return localInternalFactory.toString();
      }
    };
  }

  public Provider getProvider(Key paramKey)
  {
    Errors localErrors = new Errors(paramKey);
    try
    {
      Provider localProvider = getProviderOrThrow(paramKey, localErrors);
      localErrors.throwIfNewErrors(0);
      return localProvider;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ConfigurationException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public Object getInstance(Key paramKey)
  {
    return getProvider(paramKey).get();
  }

  public Object getInstance(Class paramClass)
  {
    return getProvider(paramClass).get();
  }

  Object callInContext(ContextualCallable paramContextualCallable)
    throws ErrorsException
  {
    Object[] arrayOfObject = (Object[])this.localContext.get();
    if (arrayOfObject[0] == null)
    {
      arrayOfObject[0] = new InternalContext();
      try
      {
        Object localObject1 = paramContextualCallable.call((InternalContext)arrayOfObject[0]);
        return localObject1;
      }
      finally
      {
        arrayOfObject[0] = null;
      }
    }
    return paramContextualCallable.call((InternalContext)arrayOfObject[0]);
  }

  public String toString()
  {
    return new $ToStringBuilder(Lookups.class).add("bindings", this.state.getExplicitBindingsThisLevel().values()).toString();
  }

  static abstract interface MethodInvoker
  {
    public abstract Object invoke(Object paramObject, Object[] paramArrayOfObject)
      throws IllegalAccessException, InvocationTargetException;
  }

  private static class BindingsMultimap
  {
    final Map multimap = $Maps.newHashMap();

    void put(TypeLiteral paramTypeLiteral, Binding paramBinding)
    {
      Object localObject = (List)this.multimap.get(paramTypeLiteral);
      if (localObject == null)
      {
        localObject = $Lists.newArrayList();
        this.multimap.put(paramTypeLiteral, localObject);
      }
      ((List)localObject).add(paramBinding);
    }

    List getAll(TypeLiteral paramTypeLiteral)
    {
      List localList = (List)this.multimap.get(paramTypeLiteral);
      return localList != null ? Collections.unmodifiableList((List)this.multimap.get(paramTypeLiteral)) : .ImmutableList.of();
    }
  }

  private static class ConvertedConstantBindingImpl extends BindingImpl
    implements ConvertedConstantBinding
  {
    final Object value;
    final Provider provider;
    final Binding originalBinding;
    final TypeConverterBinding typeConverterBinding;

    ConvertedConstantBindingImpl(InjectorImpl paramInjectorImpl, Key paramKey, Object paramObject, Binding paramBinding, TypeConverterBinding paramTypeConverterBinding)
    {
      super(paramKey, paramBinding.getSource(), new ConstantFactory(Initializables.of(paramObject)), Scoping.UNSCOPED);
      this.value = paramObject;
      this.provider = Providers.of(paramObject);
      this.originalBinding = paramBinding;
      this.typeConverterBinding = paramTypeConverterBinding;
    }

    public Provider getProvider()
    {
      return this.provider;
    }

    public Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor)
    {
      return paramBindingTargetVisitor.visit(this);
    }

    public Object getValue()
    {
      return this.value;
    }

    public TypeConverterBinding getTypeConverterBinding()
    {
      return this.typeConverterBinding;
    }

    public Key getSourceKey()
    {
      return this.originalBinding.getKey();
    }

    public Set getDependencies()
    {
      return .ImmutableSet.of(Dependency.get(getSourceKey()));
    }

    public void applyTo(Binder paramBinder)
    {
      throw new UnsupportedOperationException("This element represents a synthetic binding.");
    }

    public String toString()
    {
      return new $ToStringBuilder(ConvertedConstantBinding.class).add("key", getKey()).add("sourceKey", getSourceKey()).add("value", this.value).toString();
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof ConvertedConstantBindingImpl))
      {
        ConvertedConstantBindingImpl localConvertedConstantBindingImpl = (ConvertedConstantBindingImpl)paramObject;
        return (getKey().equals(localConvertedConstantBindingImpl.getKey())) && (getScoping().equals(localConvertedConstantBindingImpl.getScoping())) && ($Objects.equal(this.value, localConvertedConstantBindingImpl.value));
      }
      return false;
    }

    public int hashCode()
    {
      return .Objects.hashCode(new Object[] { getKey(), getScoping(), this.value });
    }
  }

  private static class ProviderBindingImpl extends BindingImpl
    implements HasDependencies, ProviderBinding
  {
    final BindingImpl providedBinding;

    ProviderBindingImpl(InjectorImpl paramInjectorImpl, Key paramKey, Binding paramBinding)
    {
      super(paramKey, paramBinding.getSource(), createInternalFactory(paramBinding), Scoping.UNSCOPED);
      this.providedBinding = ((BindingImpl)paramBinding);
    }

    static InternalFactory createInternalFactory(Binding paramBinding)
    {
      Provider localProvider = paramBinding.getProvider();
      return new InternalFactory()
      {
        public Provider get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency paramAnonymousDependency, boolean paramAnonymousBoolean)
        {
          return this.val$provider;
        }
      };
    }

    public Key getProvidedKey()
    {
      return this.providedBinding.getKey();
    }

    public Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor)
    {
      return paramBindingTargetVisitor.visit(this);
    }

    public void applyTo(Binder paramBinder)
    {
      throw new UnsupportedOperationException("This element represents a synthetic binding.");
    }

    public String toString()
    {
      return new $ToStringBuilder(ProviderBinding.class).add("key", getKey()).add("providedKey", getProvidedKey()).toString();
    }

    public Set getDependencies()
    {
      return .ImmutableSet.of(Dependency.get(getProvidedKey()));
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof ProviderBindingImpl))
      {
        ProviderBindingImpl localProviderBindingImpl = (ProviderBindingImpl)paramObject;
        return (getKey().equals(localProviderBindingImpl.getKey())) && (getScoping().equals(localProviderBindingImpl.getScoping())) && ($Objects.equal(this.providedBinding, localProviderBindingImpl.providedBinding));
      }
      return false;
    }

    public int hashCode()
    {
      return .Objects.hashCode(new Object[] { getKey(), getScoping(), this.providedBinding });
    }
  }

  static enum JitLimitation
  {
    NO_JIT, EXISTING_JIT, NEW_OR_EXISTING_JIT;
  }

  static class InjectorOptions
  {
    final Stage stage;
    final boolean jitDisabled;
    final boolean disableCircularProxies;

    InjectorOptions(Stage paramStage, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.stage = paramStage;
      this.jitDisabled = paramBoolean1;
      this.disableCircularProxies = paramBoolean2;
    }

    public String toString()
    {
      return new $ToStringBuilder(getClass()).add("stage", this.stage).add("jitDisabled", Boolean.valueOf(this.jitDisabled)).add("disableCircularProxies", Boolean.valueOf(this.disableCircularProxies)).toString();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.InjectorImpl
 * JD-Core Version:    0.6.2
 */