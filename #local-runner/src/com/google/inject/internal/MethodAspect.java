package com.google.inject.internal;

import com.google.inject.internal.util..Preconditions;
import com.google.inject.matcher.Matcher;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;

final class MethodAspect
{
  private final Matcher classMatcher;
  private final Matcher methodMatcher;
  private final List interceptors;

  MethodAspect(Matcher paramMatcher1, Matcher paramMatcher2, List paramList)
  {
    this.classMatcher = ((Matcher).Preconditions.checkNotNull(paramMatcher1, "class matcher"));
    this.methodMatcher = ((Matcher).Preconditions.checkNotNull(paramMatcher2, "method matcher"));
    this.interceptors = ((List).Preconditions.checkNotNull(paramList, "interceptors"));
  }

  MethodAspect(Matcher paramMatcher1, Matcher paramMatcher2, MethodInterceptor[] paramArrayOfMethodInterceptor)
  {
    this(paramMatcher1, paramMatcher2, Arrays.asList(paramArrayOfMethodInterceptor));
  }

  boolean matches(Class paramClass)
  {
    return this.classMatcher.matches(paramClass);
  }

  boolean matches(Method paramMethod)
  {
    return this.methodMatcher.matches(paramMethod);
  }

  List interceptors()
  {
    return this.interceptors;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.MethodAspect
 * JD-Core Version:    0.6.2
 */