package com.google.inject.matcher;

public abstract interface Matcher
{
  public abstract boolean matches(Object paramObject);

  public abstract Matcher and(Matcher paramMatcher);

  public abstract Matcher or(Matcher paramMatcher);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.matcher.Matcher
 * JD-Core Version:    0.6.2
 */