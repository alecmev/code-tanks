package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.internal.util..Preconditions;

public final class ProviderLookup
  implements Element
{
  private final Object source;
  private final Key key;
  private Provider delegate;

  public ProviderLookup(Object paramObject, Key paramKey)
  {
    this.source = $Preconditions.checkNotNull(paramObject, "source");
    this.key = ((Key).Preconditions.checkNotNull(paramKey, "key"));
  }

  public Object getSource()
  {
    return this.source;
  }

  public Key getKey()
  {
    return this.key;
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void initializeDelegate(Provider paramProvider)
  {
    $Preconditions.checkState(this.delegate == null, "delegate already initialized");
    this.delegate = ((Provider).Preconditions.checkNotNull(paramProvider, "delegate"));
  }

  public void applyTo(Binder paramBinder)
  {
    initializeDelegate(paramBinder.withSource(getSource()).getProvider(this.key));
  }

  public Provider getDelegate()
  {
    return this.delegate;
  }

  public Provider getProvider()
  {
    return new Provider()
    {
      public Object get()
      {
        $Preconditions.checkState(ProviderLookup.this.delegate != null, "This Provider cannot be used until the Injector has been created.");
        return ProviderLookup.this.delegate.get();
      }

      public String toString()
      {
        return "Provider<" + ProviderLookup.this.key.getTypeLiteral() + ">";
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.ProviderLookup
 * JD-Core Version:    0.6.2
 */