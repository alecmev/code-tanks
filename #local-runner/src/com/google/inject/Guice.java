package com.google.inject;

import com.google.inject.internal.InternalInjectorCreator;
import java.util.Arrays;

public final class Guice
{
  public static Injector createInjector(Module[] paramArrayOfModule)
  {
    return createInjector(Arrays.asList(paramArrayOfModule));
  }

  public static Injector createInjector(Iterable paramIterable)
  {
    return createInjector(Stage.DEVELOPMENT, paramIterable);
  }

  public static Injector createInjector(Stage paramStage, Module[] paramArrayOfModule)
  {
    return createInjector(paramStage, Arrays.asList(paramArrayOfModule));
  }

  public static Injector createInjector(Stage paramStage, Iterable paramIterable)
  {
    return new InternalInjectorCreator().stage(paramStage).addModules(paramIterable).build();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.Guice
 * JD-Core Version:    0.6.2
 */