package org.apache.commons.lang.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class ExceptionUtils
{
  private static final Object CAUSE_METHOD_NAMES_LOCK = new Object();
  private static String[] CAUSE_METHOD_NAMES = { "getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable" };
  private static final Method THROWABLE_CAUSE_METHOD;
  private static final Method THROWABLE_INITCAUSE_METHOD = localMethod;

  public static String getStackTrace(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
    paramThrowable.printStackTrace(localPrintWriter);
    return localStringWriter.getBuffer().toString();
  }

  static
  {
    Method localMethod;
    try
    {
      localMethod = Throwable.class.getMethod("getCause", null);
    }
    catch (Exception localException1)
    {
      localMethod = null;
    }
    THROWABLE_CAUSE_METHOD = localMethod;
    try
    {
      localMethod = class$java$lang$Throwable.getMethod("initCause", new Class[] { Throwable.class });
    }
    catch (Exception localException2)
    {
      localMethod = null;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.commons.lang.exception.ExceptionUtils
 * JD-Core Version:    0.6.2
 */