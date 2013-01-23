package com.google.inject.internal.cglib.core;

import java.lang.reflect.Member;

public class $RejectModifierPredicate
  implements .Predicate
{
  private int rejectMask;

  public $RejectModifierPredicate(int paramInt)
  {
    this.rejectMask = paramInt;
  }

  public boolean evaluate(Object paramObject)
  {
    return (((Member)paramObject).getModifiers() & this.rejectMask) == 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..RejectModifierPredicate
 * JD-Core Version:    0.6.2
 */