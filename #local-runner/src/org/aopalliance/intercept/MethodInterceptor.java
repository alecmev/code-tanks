package org.aopalliance.intercept;

public abstract interface MethodInterceptor
{
  public abstract Object invoke(MethodInvocation paramMethodInvocation)
    throws Throwable;
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.aopalliance.intercept.MethodInterceptor
 * JD-Core Version:    0.6.2
 */