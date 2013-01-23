package com.google.inject.internal.cglib.proxy;

import com.google.inject.internal.cglib.core..ClassEmitter;
import com.google.inject.internal.cglib.core..CodeEmitter;
import com.google.inject.internal.cglib.core..MethodInfo;
import com.google.inject.internal.cglib.core..Signature;
import java.util.List;

abstract interface $CallbackGenerator
{
  public abstract void generate($ClassEmitter paramClassEmitter, Context paramContext, List paramList)
    throws Exception;

  public abstract void generateStatic($CodeEmitter paramCodeEmitter, Context paramContext, List paramList)
    throws Exception;

  public static abstract interface Context
  {
    public abstract ClassLoader getClassLoader();

    public abstract $CodeEmitter beginMethod($ClassEmitter paramClassEmitter, .MethodInfo paramMethodInfo);

    public abstract int getOriginalModifiers($MethodInfo paramMethodInfo);

    public abstract int getIndex($MethodInfo paramMethodInfo);

    public abstract void emitCallback($CodeEmitter paramCodeEmitter, int paramInt);

    public abstract $Signature getImplSignature($MethodInfo paramMethodInfo);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.proxy..CallbackGenerator
 * JD-Core Version:    0.6.2
 */