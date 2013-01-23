package com.google.inject;

import com.google.inject.binder.AnnotatedElementBuilder;

public abstract interface PrivateBinder extends Binder
{
  public abstract void expose(Key paramKey);

  public abstract AnnotatedElementBuilder expose(Class paramClass);

  public abstract AnnotatedElementBuilder expose(TypeLiteral paramTypeLiteral);

  public abstract PrivateBinder withSource(Object paramObject);

  public abstract PrivateBinder skipSources(Class[] paramArrayOfClass);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.PrivateBinder
 * JD-Core Version:    0.6.2
 */