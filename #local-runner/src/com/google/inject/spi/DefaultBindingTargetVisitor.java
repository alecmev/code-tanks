package com.google.inject.spi;

import com.google.inject.Binding;

public abstract class DefaultBindingTargetVisitor
  implements BindingTargetVisitor
{
  protected Object visitOther(Binding paramBinding)
  {
    return null;
  }

  public Object visit(InstanceBinding paramInstanceBinding)
  {
    return visitOther(paramInstanceBinding);
  }

  public Object visit(ProviderInstanceBinding paramProviderInstanceBinding)
  {
    return visitOther(paramProviderInstanceBinding);
  }

  public Object visit(ProviderKeyBinding paramProviderKeyBinding)
  {
    return visitOther(paramProviderKeyBinding);
  }

  public Object visit(LinkedKeyBinding paramLinkedKeyBinding)
  {
    return visitOther(paramLinkedKeyBinding);
  }

  public Object visit(ExposedBinding paramExposedBinding)
  {
    return visitOther(paramExposedBinding);
  }

  public Object visit(UntargettedBinding paramUntargettedBinding)
  {
    return visitOther(paramUntargettedBinding);
  }

  public Object visit(ConstructorBinding paramConstructorBinding)
  {
    return visitOther(paramConstructorBinding);
  }

  public Object visit(ConvertedConstantBinding paramConvertedConstantBinding)
  {
    return visitOther(paramConvertedConstantBinding);
  }

  public Object visit(ProviderBinding paramProviderBinding)
  {
    return visitOther(paramProviderBinding);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.DefaultBindingTargetVisitor
 * JD-Core Version:    0.6.2
 */