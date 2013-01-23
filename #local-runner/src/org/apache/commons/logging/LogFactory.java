package org.apache.commons.logging;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public abstract class LogFactory
{
  private static PrintStream diagnosticsStream = null;
  private static String diagnosticPrefix;
  private static ClassLoader thisClassLoader;
  protected static Hashtable factories = null;
  protected static LogFactory nullClassLoaderFactory = null;

  public abstract Log getInstance(Class paramClass)
    throws LogConfigurationException;

  public abstract Log getInstance(String paramString)
    throws LogConfigurationException;

  public abstract void setAttribute(String paramString, Object paramObject);

  private static final Hashtable createFactoryStore()
  {
    Hashtable localHashtable = null;
    String str;
    try
    {
      str = getSystemProperty("org.apache.commons.logging.LogFactory.HashtableImpl", null);
    }
    catch (SecurityException localSecurityException)
    {
      str = null;
    }
    if (str == null)
      str = "org.apache.commons.logging.impl.WeakHashtable";
    try
    {
      Class localClass = Class.forName(str);
      localHashtable = (Hashtable)localClass.newInstance();
    }
    catch (Throwable localThrowable)
    {
      if (!"org.apache.commons.logging.impl.WeakHashtable".equals(str))
        if (isDiagnosticsEnabled())
          logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
        else
          System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
    }
    if (localHashtable == null)
      localHashtable = new Hashtable();
    return localHashtable;
  }

  private static String trim(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.trim();
  }

  public static LogFactory getFactory()
    throws LogConfigurationException
  {
    ClassLoader localClassLoader1 = getContextClassLoaderInternal();
    if ((localClassLoader1 == null) && (isDiagnosticsEnabled()))
      logDiagnostic("Context classloader is null.");
    LogFactory localLogFactory = getCachedFactory(localClassLoader1);
    if (localLogFactory != null)
      return localLogFactory;
    if (isDiagnosticsEnabled())
    {
      logDiagnostic("[LOOKUP] LogFactory implementation requested for the first time for context classloader " + objectId(localClassLoader1));
      logHierarchy("[LOOKUP] ", localClassLoader1);
    }
    Properties localProperties = getConfigurationFile(localClassLoader1, "commons-logging.properties");
    ClassLoader localClassLoader2 = localClassLoader1;
    String str1;
    if (localProperties != null)
    {
      str1 = localProperties.getProperty("use_tccl");
      if ((str1 != null) && (!Boolean.valueOf(str1).booleanValue()))
        localClassLoader2 = thisClassLoader;
    }
    if (isDiagnosticsEnabled())
      logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
    try
    {
      str1 = getSystemProperty("org.apache.commons.logging.LogFactory", null);
      if (str1 != null)
      {
        if (isDiagnosticsEnabled())
          logDiagnostic("[LOOKUP] Creating an instance of LogFactory class '" + str1 + "' as specified by system property " + "org.apache.commons.logging.LogFactory");
        localLogFactory = newFactory(str1, localClassLoader2, localClassLoader1);
      }
      else if (isDiagnosticsEnabled())
      {
        logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
      }
    }
    catch (SecurityException localSecurityException)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(localSecurityException.getMessage()) + "]. Trying alternative implementations...");
    }
    catch (RuntimeException localRuntimeException)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + trim(localRuntimeException.getMessage()) + "] as specified by a system property.");
      throw localRuntimeException;
    }
    Object localObject2;
    String str2;
    if (localLogFactory == null)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
      try
      {
        InputStream localInputStream = getResourceAsStream(localClassLoader1, "META-INF/services/org.apache.commons.logging.LogFactory");
        if (localInputStream != null)
        {
          try
          {
            localObject2 = new BufferedReader(new InputStreamReader(localInputStream, "UTF-8"));
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            localObject2 = new BufferedReader(new InputStreamReader(localInputStream));
          }
          str2 = ((BufferedReader)localObject2).readLine();
          ((BufferedReader)localObject2).close();
          if ((str2 != null) && (!"".equals(str2)))
          {
            if (isDiagnosticsEnabled())
              logDiagnostic("[LOOKUP]  Creating an instance of LogFactory class " + str2 + " as specified by file '" + "META-INF/services/org.apache.commons.logging.LogFactory" + "' which was present in the path of the context" + " classloader.");
            localLogFactory = newFactory(str2, localClassLoader2, localClassLoader1);
          }
        }
        else if (isDiagnosticsEnabled())
        {
          logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
        }
      }
      catch (Exception localException)
      {
        if (isDiagnosticsEnabled())
          logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(localException.getMessage()) + "]. Trying alternative implementations...");
      }
    }
    Object localObject1;
    if (localLogFactory == null)
      if (localProperties != null)
      {
        if (isDiagnosticsEnabled())
          logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
        localObject1 = localProperties.getProperty("org.apache.commons.logging.LogFactory");
        if (localObject1 != null)
        {
          if (isDiagnosticsEnabled())
            logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + (String)localObject1 + "'");
          localLogFactory = newFactory((String)localObject1, localClassLoader2, localClassLoader1);
        }
        else if (isDiagnosticsEnabled())
        {
          logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
        }
      }
      else if (isDiagnosticsEnabled())
      {
        logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
      }
    if (localLogFactory == null)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
      localLogFactory = newFactory("org.apache.commons.logging.impl.LogFactoryImpl", thisClassLoader, localClassLoader1);
    }
    if (localLogFactory != null)
    {
      cacheFactory(localClassLoader1, localLogFactory);
      if (localProperties != null)
      {
        localObject1 = localProperties.propertyNames();
        while (((Enumeration)localObject1).hasMoreElements())
        {
          localObject2 = (String)((Enumeration)localObject1).nextElement();
          str2 = localProperties.getProperty((String)localObject2);
          localLogFactory.setAttribute((String)localObject2, str2);
        }
      }
    }
    return localLogFactory;
  }

  public static Log getLog(Class paramClass)
    throws LogConfigurationException
  {
    return getFactory().getInstance(paramClass);
  }

  public static Log getLog(String paramString)
    throws LogConfigurationException
  {
    return getFactory().getInstance(paramString);
  }

  protected static ClassLoader getClassLoader(Class paramClass)
  {
    try
    {
      return paramClass.getClassLoader();
    }
    catch (SecurityException localSecurityException)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("Unable to get classloader for class '" + paramClass + "' due to security restrictions - " + localSecurityException.getMessage());
      throw localSecurityException;
    }
  }

  private static ClassLoader getContextClassLoaderInternal()
    throws LogConfigurationException
  {
    return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return LogFactory.directGetContextClassLoader();
      }
    });
  }

  protected static ClassLoader directGetContextClassLoader()
    throws LogConfigurationException
  {
    ClassLoader localClassLoader = null;
    try
    {
      Method localMethod = Thread.class.getMethod("getContextClassLoader", (Class[])null);
      try
      {
        localClassLoader = (ClassLoader)localMethod.invoke(Thread.currentThread(), (Object[])null);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new LogConfigurationException("Unexpected IllegalAccessException", localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        if (!(localInvocationTargetException.getTargetException() instanceof SecurityException))
          throw new LogConfigurationException("Unexpected InvocationTargetException", localInvocationTargetException.getTargetException());
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localClassLoader = getClassLoader(LogFactory.class);
    }
    return localClassLoader;
  }

  private static LogFactory getCachedFactory(ClassLoader paramClassLoader)
  {
    LogFactory localLogFactory = null;
    if (paramClassLoader == null)
      localLogFactory = nullClassLoaderFactory;
    else
      localLogFactory = (LogFactory)factories.get(paramClassLoader);
    return localLogFactory;
  }

  private static void cacheFactory(ClassLoader paramClassLoader, LogFactory paramLogFactory)
  {
    if (paramLogFactory != null)
      if (paramClassLoader == null)
        nullClassLoaderFactory = paramLogFactory;
      else
        factories.put(paramClassLoader, paramLogFactory);
  }

  protected static LogFactory newFactory(String paramString, ClassLoader paramClassLoader1, ClassLoader paramClassLoader2)
    throws LogConfigurationException
  {
    Object localObject = AccessController.doPrivileged(new PrivilegedAction()
    {
      private final String val$factoryClass;
      private final ClassLoader val$classLoader;

      public Object run()
      {
        return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
      }
    });
    if ((localObject instanceof LogConfigurationException))
    {
      LogConfigurationException localLogConfigurationException = (LogConfigurationException)localObject;
      if (isDiagnosticsEnabled())
        logDiagnostic("An error occurred while loading the factory class:" + localLogConfigurationException.getMessage());
      throw localLogConfigurationException;
    }
    if (isDiagnosticsEnabled())
      logDiagnostic("Created object " + objectId(localObject) + " to manage classloader " + objectId(paramClassLoader2));
    return (LogFactory)localObject;
  }

  protected static Object createFactory(String paramString, ClassLoader paramClassLoader)
  {
    Class localClass = null;
    try
    {
      if (paramClassLoader != null)
        try
        {
          localClass = paramClassLoader.loadClass(paramString);
          if (LogFactory.class.isAssignableFrom(localClass))
          {
            if (isDiagnosticsEnabled())
              logDiagnostic("Loaded class " + localClass.getName() + " from classloader " + objectId(paramClassLoader));
          }
          else if (isDiagnosticsEnabled())
          {
            logDiagnostic("Factory class " + localClass.getName() + " loaded from classloader " + objectId(localClass.getClassLoader()) + " does not extend '" + LogFactory.class.getName() + "' as loaded by this classloader.");
            logHierarchy("[BAD CL TREE] ", paramClassLoader);
          }
          return (LogFactory)localClass.newInstance();
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          if (paramClassLoader == thisClassLoader)
          {
            if (isDiagnosticsEnabled())
              logDiagnostic("Unable to locate any class called '" + paramString + "' via classloader " + objectId(paramClassLoader));
            throw localClassNotFoundException;
          }
        }
        catch (NoClassDefFoundError localNoClassDefFoundError)
        {
          if (paramClassLoader == thisClassLoader)
          {
            if (isDiagnosticsEnabled())
              logDiagnostic("Class '" + paramString + "' cannot be loaded" + " via classloader " + objectId(paramClassLoader) + " - it depends on some other class that cannot" + " be found.");
            throw localNoClassDefFoundError;
          }
        }
        catch (ClassCastException localClassCastException1)
        {
          if (paramClassLoader == thisClassLoader)
          {
            boolean bool = implementsLogFactory(localClass);
            String str = "The application has specified that a custom LogFactory implementation should be used but Class '" + paramString + "' cannot be converted to '" + LogFactory.class.getName() + "'. ";
            if (bool)
              str = str + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://commons.apache.org/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. ";
            else
              str = str + "Please check the custom implementation. ";
            str = str + "Help can be found @http://commons.apache.org/logging/troubleshooting.html.";
            if (isDiagnosticsEnabled())
              logDiagnostic(str);
            ClassCastException localClassCastException2 = new ClassCastException(str);
            throw localClassCastException2;
          }
        }
      if (isDiagnosticsEnabled())
        logDiagnostic("Unable to load factory class via classloader " + objectId(paramClassLoader) + " - trying the classloader associated with this LogFactory.");
      localClass = Class.forName(paramString);
      return (LogFactory)localClass.newInstance();
    }
    catch (Exception localException)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("Unable to create LogFactory instance.");
      if ((localClass != null) && (!LogFactory.class.isAssignableFrom(localClass)))
        return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", localException);
      return new LogConfigurationException(localException);
    }
  }

  private static boolean implementsLogFactory(Class paramClass)
  {
    boolean bool = false;
    if (paramClass != null)
      try
      {
        ClassLoader localClassLoader = paramClass.getClassLoader();
        if (localClassLoader == null)
        {
          logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
        }
        else
        {
          logHierarchy("[CUSTOM LOG FACTORY] ", localClassLoader);
          Class localClass = Class.forName("org.apache.commons.logging.LogFactory", false, localClassLoader);
          bool = localClass.isAssignableFrom(paramClass);
          if (bool)
            logDiagnostic("[CUSTOM LOG FACTORY] " + paramClass.getName() + " implements LogFactory but was loaded by an incompatible classloader.");
          else
            logDiagnostic("[CUSTOM LOG FACTORY] " + paramClass.getName() + " does not implement LogFactory.");
        }
      }
      catch (SecurityException localSecurityException)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + localSecurityException.getMessage());
      }
      catch (LinkageError localLinkageError)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + localLinkageError.getMessage());
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
      }
    return bool;
  }

  private static InputStream getResourceAsStream(ClassLoader paramClassLoader, String paramString)
  {
    return (InputStream)AccessController.doPrivileged(new PrivilegedAction()
    {
      private final ClassLoader val$loader;
      private final String val$name;

      public Object run()
      {
        if (this.val$loader != null)
          return this.val$loader.getResourceAsStream(this.val$name);
        return ClassLoader.getSystemResourceAsStream(this.val$name);
      }
    });
  }

  private static Enumeration getResources(ClassLoader paramClassLoader, String paramString)
  {
    PrivilegedAction local4 = new PrivilegedAction()
    {
      private final ClassLoader val$loader;
      private final String val$name;

      public Object run()
      {
        try
        {
          if (this.val$loader != null)
            return this.val$loader.getResources(this.val$name);
          return ClassLoader.getSystemResources(this.val$name);
        }
        catch (IOException localIOException)
        {
          if (LogFactory.isDiagnosticsEnabled())
            LogFactory.logDiagnostic("Exception while trying to find configuration file " + this.val$name + ":" + localIOException.getMessage());
          return null;
        }
        catch (NoSuchMethodError localNoSuchMethodError)
        {
        }
        return null;
      }
    };
    Object localObject = AccessController.doPrivileged(local4);
    return (Enumeration)localObject;
  }

  private static Properties getProperties(URL paramURL)
  {
    PrivilegedAction local5 = new PrivilegedAction()
    {
      private final URL val$url;

      public Object run()
      {
        try
        {
          InputStream localInputStream = this.val$url.openStream();
          if (localInputStream != null)
          {
            Properties localProperties = new Properties();
            localProperties.load(localInputStream);
            localInputStream.close();
            return localProperties;
          }
        }
        catch (IOException localIOException)
        {
          if (LogFactory.isDiagnosticsEnabled())
            LogFactory.logDiagnostic("Unable to read URL " + this.val$url);
        }
        return null;
      }
    };
    return (Properties)AccessController.doPrivileged(local5);
  }

  private static final Properties getConfigurationFile(ClassLoader paramClassLoader, String paramString)
  {
    Object localObject1 = null;
    double d1 = 0.0D;
    Object localObject2 = null;
    try
    {
      Enumeration localEnumeration = getResources(paramClassLoader, paramString);
      if (localEnumeration == null)
        return null;
      while (localEnumeration.hasMoreElements())
      {
        URL localURL = (URL)localEnumeration.nextElement();
        Properties localProperties = getProperties(localURL);
        if (localProperties != null)
        {
          String str;
          if (localObject1 == null)
          {
            localObject2 = localURL;
            localObject1 = localProperties;
            str = localObject1.getProperty("priority");
            d1 = 0.0D;
            if (str != null)
              d1 = Double.parseDouble(str);
            if (isDiagnosticsEnabled())
              logDiagnostic("[LOOKUP] Properties file found at '" + localURL + "'" + " with priority " + d1);
          }
          else
          {
            str = localProperties.getProperty("priority");
            double d2 = 0.0D;
            if (str != null)
              d2 = Double.parseDouble(str);
            if (d2 > d1)
            {
              if (isDiagnosticsEnabled())
                logDiagnostic("[LOOKUP] Properties file at '" + localURL + "'" + " with priority " + d2 + " overrides file at '" + localObject2 + "'" + " with priority " + d1);
              localObject2 = localURL;
              localObject1 = localProperties;
              d1 = d2;
            }
            else if (isDiagnosticsEnabled())
            {
              logDiagnostic("[LOOKUP] Properties file at '" + localURL + "'" + " with priority " + d2 + " does not override file at '" + localObject2 + "'" + " with priority " + d1);
            }
          }
        }
      }
    }
    catch (SecurityException localSecurityException)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("SecurityException thrown while trying to find/read config files.");
    }
    if (isDiagnosticsEnabled())
      if (localObject1 == null)
        logDiagnostic("[LOOKUP] No properties file of name '" + paramString + "' found.");
      else
        logDiagnostic("[LOOKUP] Properties file of name '" + paramString + "' found at '" + localObject2 + '"');
    return localObject1;
  }

  private static String getSystemProperty(String paramString1, String paramString2)
    throws SecurityException
  {
    return (String)AccessController.doPrivileged(new PrivilegedAction()
    {
      private final String val$key;
      private final String val$def;

      public Object run()
      {
        return System.getProperty(this.val$key, this.val$def);
      }
    });
  }

  private static void initDiagnostics()
  {
    String str1;
    try
    {
      str1 = getSystemProperty("org.apache.commons.logging.diagnostics.dest", null);
      if (str1 == null)
        return;
    }
    catch (SecurityException localSecurityException1)
    {
      return;
    }
    if (str1.equals("STDOUT"))
      diagnosticsStream = System.out;
    else if (str1.equals("STDERR"))
      diagnosticsStream = System.err;
    else
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(str1, true);
        diagnosticsStream = new PrintStream(localFileOutputStream);
      }
      catch (IOException localIOException)
      {
        return;
      }
    String str2;
    try
    {
      ClassLoader localClassLoader = thisClassLoader;
      if (thisClassLoader == null)
        str2 = "BOOTLOADER";
      else
        str2 = objectId(localClassLoader);
    }
    catch (SecurityException localSecurityException2)
    {
      str2 = "UNKNOWN";
    }
    diagnosticPrefix = "[LogFactory from " + str2 + "] ";
  }

  protected static boolean isDiagnosticsEnabled()
  {
    return diagnosticsStream != null;
  }

  private static final void logDiagnostic(String paramString)
  {
    if (diagnosticsStream != null)
    {
      diagnosticsStream.print(diagnosticPrefix);
      diagnosticsStream.println(paramString);
      diagnosticsStream.flush();
    }
  }

  private static void logClassLoaderEnvironment(Class paramClass)
  {
    if (!isDiagnosticsEnabled())
      return;
    try
    {
      logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
      logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
    }
    catch (SecurityException localSecurityException1)
    {
      logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
    }
    String str = paramClass.getName();
    ClassLoader localClassLoader;
    try
    {
      localClassLoader = getClassLoader(paramClass);
    }
    catch (SecurityException localSecurityException2)
    {
      logDiagnostic("[ENV] Security forbids determining the classloader for " + str);
      return;
    }
    logDiagnostic("[ENV] Class " + str + " was loaded via classloader " + objectId(localClassLoader));
    logHierarchy("[ENV] Ancestry of classloader which loaded " + str + " is ", localClassLoader);
  }

  private static void logHierarchy(String paramString, ClassLoader paramClassLoader)
  {
    if (!isDiagnosticsEnabled())
      return;
    if (paramClassLoader != null)
    {
      String str = paramClassLoader.toString();
      logDiagnostic(paramString + objectId(paramClassLoader) + " == '" + str + "'");
    }
    ClassLoader localClassLoader;
    try
    {
      localClassLoader = ClassLoader.getSystemClassLoader();
    }
    catch (SecurityException localSecurityException1)
    {
      logDiagnostic(paramString + "Security forbids determining the system classloader.");
      return;
    }
    if (paramClassLoader != null)
    {
      StringBuffer localStringBuffer = new StringBuffer(paramString + "ClassLoader tree:");
      do
      {
        localStringBuffer.append(objectId(paramClassLoader));
        if (paramClassLoader == localClassLoader)
          localStringBuffer.append(" (SYSTEM) ");
        try
        {
          paramClassLoader = paramClassLoader.getParent();
        }
        catch (SecurityException localSecurityException2)
        {
          localStringBuffer.append(" --> SECRET");
          break;
        }
        localStringBuffer.append(" --> ");
      }
      while (paramClassLoader != null);
      localStringBuffer.append("BOOT");
      logDiagnostic(localStringBuffer.toString());
    }
  }

  public static String objectId(Object paramObject)
  {
    if (paramObject == null)
      return "null";
    return paramObject.getClass().getName() + "@" + System.identityHashCode(paramObject);
  }

  static
  {
    thisClassLoader = getClassLoader(LogFactory.class);
    initDiagnostics();
    logClassLoaderEnvironment(LogFactory.class);
    factories = createFactoryStore();
    if (isDiagnosticsEnabled())
      logDiagnostic("BOOTSTRAP COMPLETED");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.logging.LogFactory
 * JD-Core Version:    0.6.2
 */