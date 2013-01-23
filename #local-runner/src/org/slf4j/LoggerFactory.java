package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public final class LoggerFactory
{
  static int INITIALIZATION_STATE = 0;
  static SubstituteLoggerFactory TEMP_FACTORY = new SubstituteLoggerFactory();
  static NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
  private static final String[] API_COMPATIBILITY_LIST = { "1.6" };
  private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";

  private static final void performInitialization()
  {
    bind();
    if (INITIALIZATION_STATE == 3)
      versionSanityCheck();
  }

  private static boolean messageContainsOrgSlf4jImplStaticLoggerBinder(String paramString)
  {
    if (paramString == null)
      return false;
    if (paramString.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1)
      return true;
    return paramString.indexOf("org.slf4j.impl.StaticLoggerBinder") != -1;
  }

  private static final void bind()
  {
    try
    {
      Set localSet = findPossibleStaticLoggerBinderPathSet();
      reportMultipleBindingAmbiguity(localSet);
      StaticLoggerBinder.getSingleton();
      INITIALIZATION_STATE = 3;
      reportActualBinding(localSet);
      emitSubstituteLoggerWarning();
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      str = localNoClassDefFoundError.getMessage();
      if (messageContainsOrgSlf4jImplStaticLoggerBinder(str))
      {
        INITIALIZATION_STATE = 4;
        Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
        Util.report("Defaulting to no-operation (NOP) logger implementation");
        Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
      }
      else
      {
        failedBinding(localNoClassDefFoundError);
        throw localNoClassDefFoundError;
      }
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      String str = localNoSuchMethodError.getMessage();
      if ((str != null) && (str.indexOf("org.slf4j.impl.StaticLoggerBinder.getSingleton()") != -1))
      {
        INITIALIZATION_STATE = 2;
        Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
        Util.report("Your binding is version 1.5.5 or earlier.");
        Util.report("Upgrade your binding to version 1.6.x.");
      }
      throw localNoSuchMethodError;
    }
    catch (Exception localException)
    {
      failedBinding(localException);
      throw new IllegalStateException("Unexpected initialization failure", localException);
    }
  }

  static void failedBinding(Throwable paramThrowable)
  {
    INITIALIZATION_STATE = 2;
    Util.report("Failed to instantiate SLF4J LoggerFactory", paramThrowable);
  }

  private static final void emitSubstituteLoggerWarning()
  {
    List localList = TEMP_FACTORY.getLoggerNameList();
    if (localList.size() == 0)
      return;
    Util.report("The following loggers will not work because they were created");
    Util.report("during the default configuration phase of the underlying logging system.");
    Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
    for (int i = 0; i < localList.size(); i++)
    {
      String str = (String)localList.get(i);
      Util.report(str);
    }
  }

  private static final void versionSanityCheck()
  {
    try
    {
      String str = StaticLoggerBinder.REQUESTED_API_VERSION;
      int i = 0;
      for (int j = 0; j < API_COMPATIBILITY_LIST.length; j++)
        if (str.startsWith(API_COMPATIBILITY_LIST[j]))
          i = 1;
      if (i == 0)
      {
        Util.report("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
        Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
    }
    catch (Throwable localThrowable)
    {
      Util.report("Unexpected problem occured during version sanity check", localThrowable);
    }
  }

  private static Set findPossibleStaticLoggerBinderPathSet()
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    try
    {
      ClassLoader localClassLoader = LoggerFactory.class.getClassLoader();
      Enumeration localEnumeration;
      if (localClassLoader == null)
        localEnumeration = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
      else
        localEnumeration = localClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
      while (localEnumeration.hasMoreElements())
      {
        URL localURL = (URL)localEnumeration.nextElement();
        localLinkedHashSet.add(localURL);
      }
    }
    catch (IOException localIOException)
    {
      Util.report("Error getting resources from path", localIOException);
    }
    return localLinkedHashSet;
  }

  private static boolean isAmbiguousStaticLoggerBinderPathSet(Set paramSet)
  {
    return paramSet.size() > 1;
  }

  private static void reportMultipleBindingAmbiguity(Set paramSet)
  {
    if (isAmbiguousStaticLoggerBinderPathSet(paramSet))
    {
      Util.report("Class path contains multiple SLF4J bindings.");
      Iterator localIterator = paramSet.iterator();
      while (localIterator.hasNext())
      {
        URL localURL = (URL)localIterator.next();
        Util.report("Found binding in [" + localURL + "]");
      }
      Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
    }
  }

  private static void reportActualBinding(Set paramSet)
  {
    if (isAmbiguousStaticLoggerBinderPathSet(paramSet))
      Util.report("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
  }

  public static Logger getLogger(String paramString)
  {
    ILoggerFactory localILoggerFactory = getILoggerFactory();
    return localILoggerFactory.getLogger(paramString);
  }

  public static Logger getLogger(Class paramClass)
  {
    return getLogger(paramClass.getName());
  }

  public static ILoggerFactory getILoggerFactory()
  {
    if (INITIALIZATION_STATE == 0)
    {
      INITIALIZATION_STATE = 1;
      performInitialization();
    }
    switch (INITIALIZATION_STATE)
    {
    case 3:
      return StaticLoggerBinder.getSingleton().getLoggerFactory();
    case 4:
      return NOP_FALLBACK_FACTORY;
    case 2:
      throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
    case 1:
      return TEMP_FACTORY;
    }
    throw new IllegalStateException("Unreachable code");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.LoggerFactory
 * JD-Core Version:    0.6.2
 */