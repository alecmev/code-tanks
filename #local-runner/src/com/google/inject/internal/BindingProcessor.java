package com.google.inject.internal;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.ConstructorBinding;
import com.google.inject.spi.ConvertedConstantBinding;
import com.google.inject.spi.ExposedBinding;
import com.google.inject.spi.InstanceBinding;
import com.google.inject.spi.LinkedKeyBinding;
import com.google.inject.spi.PrivateElements;
import com.google.inject.spi.ProviderBinding;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProviderKeyBinding;
import com.google.inject.spi.UntargettedBinding;
import java.util.Iterator;
import java.util.Set;

final class BindingProcessor extends AbstractBindingProcessor
{
  private final Initializer initializer;

  BindingProcessor(Errors paramErrors, Initializer paramInitializer, ProcessedBindingData paramProcessedBindingData)
  {
    super(paramErrors, paramProcessedBindingData);
    this.initializer = paramInitializer;
  }

  public Boolean visit(Binding paramBinding)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 42 1 0
    //   6: invokevirtual 25	com/google/inject/Key:getTypeLiteral	()Lcom/google/inject/TypeLiteral;
    //   9: invokevirtual 26	com/google/inject/TypeLiteral:getRawType	()Ljava/lang/Class;
    //   12: astore_2
    //   13: ldc 18
    //   15: aload_2
    //   16: invokevirtual 40	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   19: ifeq +49 -> 68
    //   22: aload_1
    //   23: instanceof 15
    //   26: ifeq +29 -> 55
    //   29: aload_1
    //   30: checkcast 15	com/google/inject/spi/ProviderInstanceBinding
    //   33: invokeinterface 45 1 0
    //   38: instanceof 13
    //   41: ifeq +14 -> 55
    //   44: aload_0
    //   45: getfield 22	com/google/inject/internal/BindingProcessor:errors	Lcom/google/inject/internal/Errors;
    //   48: invokevirtual 35	com/google/inject/internal/Errors:voidProviderMethod	()Lcom/google/inject/internal/Errors;
    //   51: pop
    //   52: goto +11 -> 63
    //   55: aload_0
    //   56: getfield 22	com/google/inject/internal/BindingProcessor:errors	Lcom/google/inject/internal/Errors;
    //   59: invokevirtual 34	com/google/inject/internal/Errors:missingConstantValues	()Lcom/google/inject/internal/Errors;
    //   62: pop
    //   63: iconst_1
    //   64: invokestatic 39	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   67: areturn
    //   68: aload_2
    //   69: ldc 3
    //   71: if_acmpne +16 -> 87
    //   74: aload_0
    //   75: getfield 22	com/google/inject/internal/BindingProcessor:errors	Lcom/google/inject/internal/Errors;
    //   78: invokevirtual 33	com/google/inject/internal/Errors:bindingToProvider	()Lcom/google/inject/internal/Errors;
    //   81: pop
    //   82: iconst_1
    //   83: invokestatic 39	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   86: areturn
    //   87: aload_1
    //   88: new 8	com/google/inject/internal/BindingProcessor$1
    //   91: dup
    //   92: aload_0
    //   93: aload_1
    //   94: checkcast 6	com/google/inject/internal/BindingImpl
    //   97: invokespecial 32	com/google/inject/internal/BindingProcessor$1:<init>	(Lcom/google/inject/internal/BindingProcessor;Lcom/google/inject/internal/BindingImpl;)V
    //   100: invokeinterface 41 2 0
    //   105: checkcast 16	java/lang/Boolean
    //   108: areturn
  }

  public Boolean visit(PrivateElements paramPrivateElements)
  {
    Iterator localIterator = paramPrivateElements.getExposedKeys().iterator();
    while (localIterator.hasNext())
    {
      Key localKey = (Key)localIterator.next();
      bindExposed(paramPrivateElements, localKey);
    }
    return Boolean.valueOf(false);
  }

  private void bindExposed(PrivateElements paramPrivateElements, Key paramKey)
  {
    ExposedKeyFactory localExposedKeyFactory = new ExposedKeyFactory(paramKey, paramPrivateElements);
    this.bindingData.addCreationListener(localExposedKeyFactory);
    putBinding(new ExposedBindingImpl(this.injector, paramPrivateElements.getExposedSource(paramKey), paramKey, localExposedKeyFactory, paramPrivateElements));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.BindingProcessor
 * JD-Core Version:    0.6.2
 */