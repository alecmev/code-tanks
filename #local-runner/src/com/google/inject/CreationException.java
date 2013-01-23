package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Preconditions;
import java.util.Collection;

public class CreationException extends RuntimeException
{
  private final .ImmutableSet messages;
  private static final long serialVersionUID = 0L;

  public CreationException(Collection paramCollection)
  {
    this.messages = $ImmutableSet.copyOf(paramCollection);
    $Preconditions.checkArgument(!this.messages.isEmpty());
    initCause(Errors.getOnlyCause(this.messages));
  }

  public Collection getErrorMessages()
  {
    return this.messages;
  }

  public String getMessage()
  {
    return Errors.format("Guice creation errors", this.messages);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.CreationException
 * JD-Core Version:    0.6.2
 */