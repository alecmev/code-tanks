package com.google.gson;

import java.lang.reflect.Type;

public abstract interface InstanceCreator
{
  public abstract Object createInstance(Type paramType);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.gson.InstanceCreator
 * JD-Core Version:    0.6.2
 */