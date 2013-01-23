package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..Preconditions;

public final class MembersInjectorLookup
  implements Element
{
  private final Object source;
  private final TypeLiteral type;
  private MembersInjector delegate;

  public MembersInjectorLookup(Object paramObject, TypeLiteral paramTypeLiteral)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
    this.type = ((TypeLiteral).Preconditions.checkNotNull(paramTypeLiteral, "type"));
  }

  public Object getSource()
  {
    return this.source;
  }

  public TypeLiteral getType()
  {
    return this.type;
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void initializeDelegate(MembersInjector paramMembersInjector)
  {
    $Preconditions.checkState(this.delegate == null, "delegate already initialized");
    this.delegate = ((MembersInjector).Preconditions.checkNotNull(paramMembersInjector, "delegate"));
  }

  public void applyTo(Binder paramBinder)
  {
    initializeDelegate(paramBinder.withSource(getSource()).getMembersInjector(this.type));
  }

  public MembersInjector getDelegate()
  {
    return this.delegate;
  }

  public MembersInjector getMembersInjector()
  {
    return new MembersInjector()
    {
      public void injectMembers(Object paramAnonymousObject)
      {
        $Preconditions.checkState(MembersInjectorLookup.this.delegate != null, "This MembersInjector cannot be used until the Injector has been created.");
        MembersInjectorLookup.this.delegate.injectMembers(paramAnonymousObject);
      }

      public String toString()
      {
        return "MembersInjector<" + MembersInjectorLookup.this.type + ">";
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.MembersInjectorLookup
 * JD-Core Version:    0.6.2
 */