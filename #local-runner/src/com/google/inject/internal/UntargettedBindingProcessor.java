package com.google.inject.internal;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.spi.UntargettedBinding;

class UntargettedBindingProcessor extends AbstractBindingProcessor
{
  UntargettedBindingProcessor(Errors paramErrors, ProcessedBindingData paramProcessedBindingData)
  {
    super(paramErrors, paramProcessedBindingData);
  }

  public Boolean visit(Binding paramBinding)
  {
    // Byte code:
    //   0: aload_1
    //   1: new 5	com/google/inject/internal/UntargettedBindingProcessor$1
    //   4: dup
    //   5: aload_0
    //   6: aload_1
    //   7: checkcast 3	com/google/inject/internal/BindingImpl
    //   10: invokespecial 9	com/google/inject/internal/UntargettedBindingProcessor$1:<init>	(Lcom/google/inject/internal/UntargettedBindingProcessor;Lcom/google/inject/internal/BindingImpl;)V
    //   13: invokeinterface 10 2 0
    //   18: checkcast 6	java/lang/Boolean
    //   21: areturn
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.UntargettedBindingProcessor
 * JD-Core Version:    0.6.2
 */