package com.google.inject.spi;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;
import com.google.inject.PrivateModule;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.AnnotatedElementBuilder;
import com.google.inject.internal.AbstractBindingBuilder;
import com.google.inject.internal.BindingBuilder;
import com.google.inject.internal.ConstantBindingBuilderImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ExposureBuilder;
import com.google.inject.internal.PrivateElementsImpl;
import com.google.inject.internal.ProviderMethodsModule;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..Lists;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.internal.util..Sets;
import com.google.inject.internal.util..SourceProvider;
import com.google.inject.matcher.Matcher;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.aopalliance.intercept.MethodInterceptor;

public final class Elements
{
  private static final BindingTargetVisitor GET_INSTANCE_VISITOR = new DefaultBindingTargetVisitor()
  {
    public Object visit(InstanceBinding paramAnonymousInstanceBinding)
    {
      return paramAnonymousInstanceBinding.getInstance();
    }

    protected Object visitOther(Binding paramAnonymousBinding)
    {
      throw new IllegalArgumentException();
    }
  };

  public static List getElements(Module[] paramArrayOfModule)
  {
    return getElements(Stage.DEVELOPMENT, Arrays.asList(paramArrayOfModule));
  }

  public static List getElements(Stage paramStage, Module[] paramArrayOfModule)
  {
    return getElements(paramStage, Arrays.asList(paramArrayOfModule));
  }

  public static List getElements(Iterable paramIterable)
  {
    return getElements(Stage.DEVELOPMENT, paramIterable);
  }

  public static List getElements(Stage paramStage, Iterable paramIterable)
  {
    RecordingBinder localRecordingBinder = new RecordingBinder(paramStage, null);
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Module localModule = (Module)localIterator.next();
      localRecordingBinder.install(localModule);
    }
    return Collections.unmodifiableList(localRecordingBinder.elements);
  }

  public static Module getModule(Iterable paramIterable)
  {
    return new Module()
    {
      public void configure(Binder paramAnonymousBinder)
      {
        Iterator localIterator = this.val$elements.iterator();
        while (localIterator.hasNext())
        {
          Element localElement = (Element)localIterator.next();
          localElement.applyTo(paramAnonymousBinder);
        }
      }
    };
  }

  static BindingTargetVisitor getInstanceVisitor()
  {
    return GET_INSTANCE_VISITOR;
  }

  private static class RecordingBinder
    implements Binder, PrivateBinder
  {
    private final Stage stage;
    private final Set modules;
    private final List elements;
    private final Object source;
    private final .SourceProvider sourceProvider;
    private final RecordingBinder parent;
    private final PrivateElementsImpl privateElements;

    private RecordingBinder(Stage paramStage)
    {
      this.stage = paramStage;
      this.modules = $Sets.newHashSet();
      this.elements = $Lists.newArrayList();
      this.source = null;
      this.sourceProvider = $SourceProvider.DEFAULT_INSTANCE.plusSkippedClasses(new Class[] { Elements.class, RecordingBinder.class, AbstractModule.class, ConstantBindingBuilderImpl.class, AbstractBindingBuilder.class, BindingBuilder.class });
      this.parent = null;
      this.privateElements = null;
    }

    private RecordingBinder(RecordingBinder paramRecordingBinder, Object paramObject, .SourceProvider paramSourceProvider)
    {
      $Preconditions.checkArgument((paramObject == null ? 1 : 0) ^ (paramSourceProvider == null ? 1 : 0));
      this.stage = paramRecordingBinder.stage;
      this.modules = paramRecordingBinder.modules;
      this.elements = paramRecordingBinder.elements;
      this.source = paramObject;
      this.sourceProvider = paramSourceProvider;
      this.parent = paramRecordingBinder.parent;
      this.privateElements = paramRecordingBinder.privateElements;
    }

    private RecordingBinder(RecordingBinder paramRecordingBinder, PrivateElementsImpl paramPrivateElementsImpl)
    {
      this.stage = paramRecordingBinder.stage;
      this.modules = $Sets.newHashSet();
      this.elements = paramPrivateElementsImpl.getElementsMutable();
      this.source = paramRecordingBinder.source;
      this.sourceProvider = paramRecordingBinder.sourceProvider;
      this.parent = paramRecordingBinder;
      this.privateElements = paramPrivateElementsImpl;
    }

    public void bindInterceptor(Matcher paramMatcher1, Matcher paramMatcher2, MethodInterceptor[] paramArrayOfMethodInterceptor)
    {
      this.elements.add(new InterceptorBinding(getSource(), paramMatcher1, paramMatcher2, paramArrayOfMethodInterceptor));
    }

    public void bindScope(Class paramClass, Scope paramScope)
    {
      this.elements.add(new ScopeBinding(getSource(), paramClass, paramScope));
    }

    public void requestInjection(Object paramObject)
    {
      requestInjection(TypeLiteral.get(paramObject.getClass()), paramObject);
    }

    public void requestInjection(TypeLiteral paramTypeLiteral, Object paramObject)
    {
      this.elements.add(new InjectionRequest(getSource(), paramTypeLiteral, paramObject));
    }

    public MembersInjector getMembersInjector(TypeLiteral paramTypeLiteral)
    {
      MembersInjectorLookup localMembersInjectorLookup = new MembersInjectorLookup(getSource(), paramTypeLiteral);
      this.elements.add(localMembersInjectorLookup);
      return localMembersInjectorLookup.getMembersInjector();
    }

    public MembersInjector getMembersInjector(Class paramClass)
    {
      return getMembersInjector(TypeLiteral.get(paramClass));
    }

    public void bindListener(Matcher paramMatcher, TypeListener paramTypeListener)
    {
      this.elements.add(new TypeListenerBinding(getSource(), paramTypeListener, paramMatcher));
    }

    public void requestStaticInjection(Class[] paramArrayOfClass)
    {
      for (Class localClass : paramArrayOfClass)
        this.elements.add(new StaticInjectionRequest(getSource(), localClass));
    }

    public void install(Module paramModule)
    {
      if (this.modules.add(paramModule))
      {
        Object localObject = this;
        if ((paramModule instanceof PrivateModule))
          localObject = ((PrivateBinder)localObject).newPrivateBinder();
        try
        {
          paramModule.configure((PrivateBinder)localObject);
        }
        catch (RuntimeException localRuntimeException)
        {
          Collection localCollection = Errors.getMessagesFromThrowable(localRuntimeException);
          if (!localCollection.isEmpty())
            this.elements.addAll(localCollection);
          else
            addError(localRuntimeException);
        }
        ((PrivateBinder)localObject).install(ProviderMethodsModule.forModule(paramModule));
      }
    }

    public Stage currentStage()
    {
      return this.stage;
    }

    public void addError(String paramString, Object[] paramArrayOfObject)
    {
      this.elements.add(new Message(getSource(), Errors.format(paramString, paramArrayOfObject)));
    }

    public void addError(Throwable paramThrowable)
    {
      String str = "An exception was caught and reported. Message: " + paramThrowable.getMessage();
      this.elements.add(new Message($ImmutableList.of(getSource()), str, paramThrowable));
    }

    public void addError(Message paramMessage)
    {
      this.elements.add(paramMessage);
    }

    public AnnotatedBindingBuilder bind(Key paramKey)
    {
      return new BindingBuilder(this, this.elements, getSource(), paramKey);
    }

    public AnnotatedBindingBuilder bind(TypeLiteral paramTypeLiteral)
    {
      return bind(Key.get(paramTypeLiteral));
    }

    public AnnotatedBindingBuilder bind(Class paramClass)
    {
      return bind(Key.get(paramClass));
    }

    public AnnotatedConstantBindingBuilder bindConstant()
    {
      return new ConstantBindingBuilderImpl(this, this.elements, getSource());
    }

    public Provider getProvider(Key paramKey)
    {
      ProviderLookup localProviderLookup = new ProviderLookup(getSource(), paramKey);
      this.elements.add(localProviderLookup);
      return localProviderLookup.getProvider();
    }

    public Provider getProvider(Class paramClass)
    {
      return getProvider(Key.get(paramClass));
    }

    public void convertToTypes(Matcher paramMatcher, TypeConverter paramTypeConverter)
    {
      this.elements.add(new TypeConverterBinding(getSource(), paramMatcher, paramTypeConverter));
    }

    public RecordingBinder withSource(Object paramObject)
    {
      return new RecordingBinder(this, paramObject, null);
    }

    public RecordingBinder skipSources(Class[] paramArrayOfClass)
    {
      if (this.source != null)
        return this;
      $SourceProvider localSourceProvider = this.sourceProvider.plusSkippedClasses(paramArrayOfClass);
      return new RecordingBinder(this, null, localSourceProvider);
    }

    public PrivateBinder newPrivateBinder()
    {
      PrivateElementsImpl localPrivateElementsImpl = new PrivateElementsImpl(getSource());
      this.elements.add(localPrivateElementsImpl);
      return new RecordingBinder(this, localPrivateElementsImpl);
    }

    public void disableCircularProxies()
    {
      this.elements.add(new DisableCircularProxiesOption(getSource()));
    }

    public void requireExplicitBindings()
    {
      this.elements.add(new RequireExplicitBindingsOption(getSource()));
    }

    public void expose(Key paramKey)
    {
      exposeInternal(paramKey);
    }

    public AnnotatedElementBuilder expose(Class paramClass)
    {
      return exposeInternal(Key.get(paramClass));
    }

    public AnnotatedElementBuilder expose(TypeLiteral paramTypeLiteral)
    {
      return exposeInternal(Key.get(paramTypeLiteral));
    }

    private AnnotatedElementBuilder exposeInternal(Key paramKey)
    {
      if (this.privateElements == null)
      {
        addError("Cannot expose %s on a standard binder. Exposed bindings are only applicable to private binders.", new Object[] { paramKey });
        return new AnnotatedElementBuilder()
        {
          public void annotatedWith(Class paramAnonymousClass)
          {
          }

          public void annotatedWith(Annotation paramAnonymousAnnotation)
          {
          }
        };
      }
      ExposureBuilder localExposureBuilder = new ExposureBuilder(this, getSource(), paramKey);
      this.privateElements.addExposureBuilder(localExposureBuilder);
      return localExposureBuilder;
    }

    protected Object getSource()
    {
      return this.sourceProvider != null ? this.sourceProvider.get() : this.source;
    }

    public String toString()
    {
      return "Binder";
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.Elements
 * JD-Core Version:    0.6.2
 */