package com.google.inject.internal.cglib.proxy;

import com.google.inject.internal.cglib.core..ClassEmitter;
import com.google.inject.internal.cglib.core..CodeEmitter;
import com.google.inject.internal.cglib.core..EmitUtils;
import com.google.inject.internal.cglib.core..MethodInfo;
import com.google.inject.internal.cglib.core..TypeUtils;
import java.util.Iterator;
import java.util.List;

class $NoOpGenerator
  implements .CallbackGenerator
{
  public static final NoOpGenerator INSTANCE = new NoOpGenerator();

  public void generate($ClassEmitter paramClassEmitter, .CallbackGenerator.Context paramContext, List paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      $MethodInfo localMethodInfo = ($MethodInfo)localIterator.next();
      if (($TypeUtils.isProtected(paramContext.getOriginalModifiers(localMethodInfo))) && ($TypeUtils.isPublic(localMethodInfo.getModifiers())))
      {
        $CodeEmitter localCodeEmitter = $EmitUtils.begin_method(paramClassEmitter, localMethodInfo);
        localCodeEmitter.load_this();
        localCodeEmitter.load_args();
        localCodeEmitter.super_invoke();
        localCodeEmitter.return_value();
        localCodeEmitter.end_method();
      }
    }
  }

  public void generateStatic($CodeEmitter paramCodeEmitter, .CallbackGenerator.Context paramContext, List paramList)
  {
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.proxy..NoOpGenerator
 * JD-Core Version:    0.6.2
 */