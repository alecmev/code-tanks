package com.google.inject.spi;

public abstract interface BindingTargetVisitor
{
  public abstract Object visit(InstanceBinding paramInstanceBinding);

  public abstract Object visit(ProviderInstanceBinding paramProviderInstanceBinding);

  public abstract Object visit(ProviderKeyBinding paramProviderKeyBinding);

  public abstract Object visit(LinkedKeyBinding paramLinkedKeyBinding);

  public abstract Object visit(ExposedBinding paramExposedBinding);

  public abstract Object visit(UntargettedBinding paramUntargettedBinding);

  public abstract Object visit(ConstructorBinding paramConstructorBinding);

  public abstract Object visit(ConvertedConstantBinding paramConvertedConstantBinding);

  public abstract Object visit(ProviderBinding paramProviderBinding);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.BindingTargetVisitor
 * JD-Core Version:    0.6.2
 */