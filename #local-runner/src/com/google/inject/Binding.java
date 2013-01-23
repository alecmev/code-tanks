package com.google.inject;

import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Element;

public abstract interface Binding extends Element
{
  public abstract Key getKey();

  public abstract Provider getProvider();

  public abstract Object acceptTargetVisitor(BindingTargetVisitor paramBindingTargetVisitor);

  public abstract Object acceptScopingVisitor(BindingScopingVisitor paramBindingScopingVisitor);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Binding
 * JD-Core Version:    0.6.2
 */