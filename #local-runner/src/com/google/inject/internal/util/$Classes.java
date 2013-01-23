package com.google.inject.internal.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class $Classes
{
  public static boolean isInnerClass(Class paramClass)
  {
    return (!Modifier.isStatic(paramClass.getModifiers())) && (paramClass.getEnclosingClass() != null);
  }

  public static boolean isConcrete(Class paramClass)
  {
    int i = paramClass.getModifiers();
    return (!paramClass.isInterface()) && (!Modifier.isAbstract(i));
  }

  public static String toString(Member paramMember)
  {
    Class localClass = memberType(paramMember);
    if (localClass == Method.class)
      return paramMember.getDeclaringClass().getName() + "." + paramMember.getName() + "()";
    if (localClass == Field.class)
      return paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    if (localClass == Constructor.class)
      return paramMember.getDeclaringClass().getName() + ".<init>()";
    throw new AssertionError();
  }

  public static Class memberType(Member paramMember)
  {
    $Preconditions.checkNotNull(paramMember, "member");
    if ((paramMember instanceof Field))
      return Field.class;
    if ((paramMember instanceof Method))
      return Method.class;
    if ((paramMember instanceof Constructor))
      return Constructor.class;
    throw new IllegalArgumentException("Unsupported implementation class for Member, " + paramMember.getClass());
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..Classes
 * JD-Core Version:    0.6.2
 */