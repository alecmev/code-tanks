package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Preconditions;
import java.util.Collection;

public final class ConfigurationException extends RuntimeException
{
  private final .ImmutableSet messages;
  private Object partialValue = null;
  private static final long serialVersionUID = 0L;

  public ConfigurationException(Iterable paramIterable)
  {
    this.messages = $ImmutableSet.copyOf(paramIterable);
    initCause(Errors.getOnlyCause(this.messages));
  }

  public ConfigurationException withPartialValue(Object paramObject)
  {
    $Preconditions.checkState(this.partialValue == null, "Can't clobber existing partial value %s with %s", new Object[] { this.partialValue, paramObject });
    ConfigurationException localConfigurationException = new ConfigurationException(this.messages);
    localConfigurationException.partialValue = paramObject;
    return localConfigurationException;
  }

  public Collection getErrorMessages()
  {
    return this.messages;
  }

  public Object getPartialValue()
  {
    return this.partialValue;
  }

  public String getMessage()
  {
    return Errors.format("Guice configuration errors", this.messages);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.ConfigurationException
 * JD-Core Version:    0.6.2
 */