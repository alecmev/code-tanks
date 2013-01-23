package com.google.inject.matcher;

import java.io.Serializable;

public abstract class AbstractMatcher
  implements Matcher
{
  public Matcher and(Matcher paramMatcher)
  {
    return new AndMatcher(this, paramMatcher);
  }

  public Matcher or(Matcher paramMatcher)
  {
    return new OrMatcher(this, paramMatcher);
  }

  private static class OrMatcher extends AbstractMatcher
    implements Serializable
  {
    private final Matcher a;
    private final Matcher b;
    private static final long serialVersionUID = 0L;

    public OrMatcher(Matcher paramMatcher1, Matcher paramMatcher2)
    {
      this.a = paramMatcher1;
      this.b = paramMatcher2;
    }

    public boolean matches(Object paramObject)
    {
      return (this.a.matches(paramObject)) || (this.b.matches(paramObject));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof OrMatcher)) && (((OrMatcher)paramObject).a.equals(this.a)) && (((OrMatcher)paramObject).b.equals(this.b));
    }

    public int hashCode()
    {
      return 37 * (this.a.hashCode() ^ this.b.hashCode());
    }

    public String toString()
    {
      return "or(" + this.a + ", " + this.b + ")";
    }
  }

  private static class AndMatcher extends AbstractMatcher
    implements Serializable
  {
    private final Matcher a;
    private final Matcher b;
    private static final long serialVersionUID = 0L;

    public AndMatcher(Matcher paramMatcher1, Matcher paramMatcher2)
    {
      this.a = paramMatcher1;
      this.b = paramMatcher2;
    }

    public boolean matches(Object paramObject)
    {
      return (this.a.matches(paramObject)) && (this.b.matches(paramObject));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof AndMatcher)) && (((AndMatcher)paramObject).a.equals(this.a)) && (((AndMatcher)paramObject).b.equals(this.b));
    }

    public int hashCode()
    {
      return 41 * (this.a.hashCode() ^ this.b.hashCode());
    }

    public String toString()
    {
      return "and(" + this.a + ", " + this.b + ")";
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.matcher.AbstractMatcher
 * JD-Core Version:    0.6.2
 */