package com.google.inject.util;

import com.google.inject.Key;
import com.google.inject.internal.Errors;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Sets;
import java.util.Iterator;
import java.util.Set;

class Node
{
  private final Key key;
  private int appliedScope = 2147483647;
  private Node effectiveScopeDependency;
  private int effectiveScope = -2147483648;
  private Class appliedScopeAnnotation;
  private Set users = $ImmutableSet.of();

  Node(Key paramKey)
  {
    this.key = paramKey;
  }

  void setScopeRank(int paramInt, Class paramClass)
  {
    this.appliedScope = paramInt;
    this.effectiveScope = paramInt;
    this.appliedScopeAnnotation = paramClass;
  }

  private void setEffectiveScope(int paramInt, Node paramNode)
  {
    if (this.effectiveScope >= paramInt)
      return;
    this.effectiveScope = paramInt;
    this.effectiveScopeDependency = paramNode;
    pushScopeToUsers();
  }

  void pushScopeToUsers()
  {
    Iterator localIterator = this.users.iterator();
    while (localIterator.hasNext())
    {
      Node localNode = (Node)localIterator.next();
      localNode.setEffectiveScope(this.effectiveScope, this);
    }
  }

  boolean isScopedCorrectly()
  {
    return this.appliedScope >= this.effectiveScope;
  }

  boolean isEffectiveScopeAppliedScope()
  {
    return this.appliedScope == this.effectiveScope;
  }

  Node effectiveScopeDependency()
  {
    return this.effectiveScopeDependency;
  }

  public void addUser(Node paramNode)
  {
    if (this.users.isEmpty())
      this.users = $Sets.newHashSet();
    this.users.add(paramNode);
  }

  public String toString()
  {
    return this.appliedScopeAnnotation != null ? Errors.convert(this.key) + " in @" + this.appliedScopeAnnotation.getSimpleName() : Errors.convert(this.key).toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.util.Node
 * JD-Core Version:    0.6.2
 */