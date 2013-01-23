package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..Type;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class $VisibilityPredicate
  implements .Predicate
{
  private boolean protectedOk;
  private String pkg;

  public $VisibilityPredicate(Class paramClass, boolean paramBoolean)
  {
    this.protectedOk = paramBoolean;
    this.pkg = $TypeUtils.getPackageName($Type.getType(paramClass));
  }

  public boolean evaluate(Object paramObject)
  {
    int i = (paramObject instanceof Member) ? ((Member)paramObject).getModifiers() : ((Integer)paramObject).intValue();
    if (Modifier.isPrivate(i))
      return false;
    if (Modifier.isPublic(i))
      return true;
    if (Modifier.isProtected(i))
      return this.protectedOk;
    return this.pkg.equals($TypeUtils.getPackageName($Type.getType(((Member)paramObject).getDeclaringClass())));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..VisibilityPredicate
 * JD-Core Version:    0.6.2
 */