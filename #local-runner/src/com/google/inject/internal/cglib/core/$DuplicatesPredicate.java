package com.google.inject.internal.cglib.core;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class $DuplicatesPredicate
  implements .Predicate
{
  private Set unique = new HashSet();

  public boolean evaluate(Object paramObject)
  {
    return this.unique.add($MethodWrapper.create((Method)paramObject));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..DuplicatesPredicate
 * JD-Core Version:    0.6.2
 */