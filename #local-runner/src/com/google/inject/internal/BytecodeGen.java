package com.google.inject.internal;

import com.google.inject.internal.cglib.core..DefaultNamingPolicy;
import com.google.inject.internal.cglib.core..NamingPolicy;
import com.google.inject.internal.cglib.core..Predicate;
import com.google.inject.internal.cglib.proxy..Enhancer;
import com.google.inject.internal.cglib.reflect..FastClass;
import com.google.inject.internal.cglib.reflect..FastClass.Generator;
import com.google.inject.internal.util..Function;
import com.google.inject.internal.util..ImmutableMap;
import com.google.inject.internal.util..MapMaker;
import com.google.inject.internal.util..Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.logging.Logger;

public final class BytecodeGen
{
  static final Logger logger = Logger.getLogger(BytecodeGen.class.getName());
  static final ClassLoader GUICE_CLASS_LOADER = canonicalize(BytecodeGen.class.getClassLoader());
  static final String GUICE_INTERNAL_PACKAGE = BytecodeGen.class.getName().replaceFirst("\\.internal\\..*$", ".internal");
  static final String CGLIB_PACKAGE = $Enhancer.class.getName().replaceFirst("\\.cglib\\..*$", ".cglib");
  static final .NamingPolicy FASTCLASS_NAMING_POLICY = new $DefaultNamingPolicy()
  {
    protected String getTag()
    {
      return "ByGuice";
    }

    public String getClassName(String paramAnonymousString1, String paramAnonymousString2, Object paramAnonymousObject, .Predicate paramAnonymousPredicate)
    {
      return super.getClassName(paramAnonymousString1, "FastClass", paramAnonymousObject, paramAnonymousPredicate);
    }
  };
  static final .NamingPolicy ENHANCER_NAMING_POLICY = new $DefaultNamingPolicy()
  {
    protected String getTag()
    {
      return "ByGuice";
    }

    public String getClassName(String paramAnonymousString1, String paramAnonymousString2, Object paramAnonymousObject, .Predicate paramAnonymousPredicate)
    {
      return super.getClassName(paramAnonymousString1, "Enhancer", paramAnonymousObject, paramAnonymousPredicate);
    }
  };
  private static final boolean CUSTOM_LOADER_ENABLED = Boolean.parseBoolean(System.getProperty("guice.custom.loader", "true"));
  private static final Map CLASS_LOADER_CACHE;

  private static ClassLoader canonicalize(ClassLoader paramClassLoader)
  {
    return paramClassLoader != null ? paramClassLoader : SystemBridgeHolder.SYSTEM_BRIDGE.getParent();
  }

  public static ClassLoader getClassLoader(Class paramClass)
  {
    return getClassLoader(paramClass, paramClass.getClassLoader());
  }

  private static ClassLoader getClassLoader(Class paramClass, ClassLoader paramClassLoader)
  {
    if (!CUSTOM_LOADER_ENABLED)
      return paramClassLoader;
    if (paramClass.getName().startsWith("java."))
      return GUICE_CLASS_LOADER;
    paramClassLoader = canonicalize(paramClassLoader);
    if ((paramClassLoader == GUICE_CLASS_LOADER) || ((paramClassLoader instanceof BridgeClassLoader)))
      return paramClassLoader;
    if (Visibility.forType(paramClass) == Visibility.PUBLIC)
    {
      if (paramClassLoader != SystemBridgeHolder.SYSTEM_BRIDGE.getParent())
        return (ClassLoader)CLASS_LOADER_CACHE.get(paramClassLoader);
      return SystemBridgeHolder.SYSTEM_BRIDGE;
    }
    return paramClassLoader;
  }

  public static .FastClass newFastClass(Class paramClass, Visibility paramVisibility)
  {
    $FastClass.Generator localGenerator = new $FastClass.Generator();
    localGenerator.setType(paramClass);
    if (paramVisibility == Visibility.PUBLIC)
      localGenerator.setClassLoader(getClassLoader(paramClass));
    localGenerator.setNamingPolicy(FASTCLASS_NAMING_POLICY);
    logger.fine("Loading " + paramClass + " FastClass with " + localGenerator.getClassLoader());
    return localGenerator.create();
  }

  public static .Enhancer newEnhancer(Class paramClass, Visibility paramVisibility)
  {
    $Enhancer localEnhancer = new $Enhancer();
    localEnhancer.setSuperclass(paramClass);
    localEnhancer.setUseFactory(false);
    if (paramVisibility == Visibility.PUBLIC)
      localEnhancer.setClassLoader(getClassLoader(paramClass));
    localEnhancer.setNamingPolicy(ENHANCER_NAMING_POLICY);
    logger.fine("Loading " + paramClass + " Enhancer with " + localEnhancer.getClassLoader());
    return localEnhancer;
  }

  static
  {
    if (CUSTOM_LOADER_ENABLED)
      CLASS_LOADER_CACHE = new $MapMaker().weakKeys().weakValues().makeComputingMap(new $Function()
      {
        public ClassLoader apply(@$Nullable final ClassLoader paramAnonymousClassLoader)
        {
          BytecodeGen.logger.fine("Creating a bridge ClassLoader for " + paramAnonymousClassLoader);
          return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
          {
            public ClassLoader run()
            {
              return new BytecodeGen.BridgeClassLoader(paramAnonymousClassLoader);
            }
          });
        }
      });
    else
      CLASS_LOADER_CACHE = $ImmutableMap.of();
  }

  private static class BridgeClassLoader extends ClassLoader
  {
    BridgeClassLoader()
    {
    }

    BridgeClassLoader(ClassLoader paramClassLoader)
    {
      super();
    }

    protected Class loadClass(String paramString, boolean paramBoolean)
      throws ClassNotFoundException
    {
      if (paramString.startsWith("sun.reflect"))
        return BytecodeGen.SystemBridgeHolder.SYSTEM_BRIDGE.classicLoadClass(paramString, paramBoolean);
      if ((paramString.startsWith(BytecodeGen.GUICE_INTERNAL_PACKAGE)) || (paramString.startsWith(BytecodeGen.CGLIB_PACKAGE)))
      {
        if (null == BytecodeGen.GUICE_CLASS_LOADER)
          return BytecodeGen.SystemBridgeHolder.SYSTEM_BRIDGE.classicLoadClass(paramString, paramBoolean);
        try
        {
          Class localClass = BytecodeGen.GUICE_CLASS_LOADER.loadClass(paramString);
          if (paramBoolean)
            resolveClass(localClass);
          return localClass;
        }
        catch (Throwable localThrowable)
        {
        }
      }
      return classicLoadClass(paramString, paramBoolean);
    }

    Class classicLoadClass(String paramString, boolean paramBoolean)
      throws ClassNotFoundException
    {
      return super.loadClass(paramString, paramBoolean);
    }
  }

  public static abstract enum Visibility
  {
    PUBLIC, SAME_PACKAGE;

    public static Visibility forMember(Member paramMember)
    {
      if ((paramMember.getModifiers() & 0x5) == 0)
        return SAME_PACKAGE;
      Class[] arrayOfClass;
      Object localObject;
      if ((paramMember instanceof Constructor))
      {
        arrayOfClass = ((Constructor)paramMember).getParameterTypes();
      }
      else
      {
        localObject = (Method)paramMember;
        if (forType(((Method)localObject).getReturnType()) == SAME_PACKAGE)
          return SAME_PACKAGE;
        arrayOfClass = ((Method)localObject).getParameterTypes();
      }
      for (Class localClass : arrayOfClass)
        if (forType(localClass) == SAME_PACKAGE)
          return SAME_PACKAGE;
      return PUBLIC;
    }

    public static Visibility forType(Class paramClass)
    {
      return (paramClass.getModifiers() & 0x5) != 0 ? PUBLIC : SAME_PACKAGE;
    }

    public abstract Visibility and(Visibility paramVisibility);
  }

  private static class SystemBridgeHolder
  {
    static final BytecodeGen.BridgeClassLoader SYSTEM_BRIDGE = new BytecodeGen.BridgeClassLoader();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.BytecodeGen
 * JD-Core Version:    0.6.2
 */