package com.google.inject.internal.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.util.Map;

public class $StackTraceElements
{
  static final Map lineNumbersCache = new $MapMaker().weakKeys().softValues().makeComputingMap(new $Function()
  {
    public .LineNumbers apply(Class paramAnonymousClass)
    {
      try
      {
        return new $LineNumbers(paramAnonymousClass);
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException(localIOException);
      }
    }
  });

  public static Object forMember(Member paramMember)
  {
    if (paramMember == null)
      return .SourceProvider.UNKNOWN_SOURCE;
    Class localClass1 = paramMember.getDeclaringClass();
    $LineNumbers localLineNumbers = ($LineNumbers)lineNumbersCache.get(localClass1);
    String str1 = localLineNumbers.getSource();
    Integer localInteger = localLineNumbers.getLineNumber(paramMember);
    int i = localInteger == null ? localLineNumbers.getFirstLine() : localInteger.intValue();
    Class localClass2 = $Classes.memberType(paramMember);
    String str2 = localClass2 == Constructor.class ? "<init>" : paramMember.getName();
    return new StackTraceElement(localClass1.getName(), str2, str1, i);
  }

  public static Object forType(Class paramClass)
  {
    $LineNumbers localLineNumbers = ($LineNumbers)lineNumbersCache.get(paramClass);
    int i = localLineNumbers.getFirstLine();
    String str = localLineNumbers.getSource();
    return new StackTraceElement(paramClass.getName(), "class", str, i);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..StackTraceElements
 * JD-Core Version:    0.6.2
 */