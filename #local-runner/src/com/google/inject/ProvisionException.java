package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..ImmutableSet;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.spi.Message;
import java.util.Collection;

public final class ProvisionException extends RuntimeException
{
  private final .ImmutableSet messages;
  private static final long serialVersionUID = 0L;

  public ProvisionException(Iterable paramIterable)
  {
    this.messages = $ImmutableSet.copyOf(paramIterable);
    $Preconditions.checkArgument(!this.messages.isEmpty());
    initCause(Errors.getOnlyCause(this.messages));
  }

  public ProvisionException(String paramString, Throwable paramThrowable)
  {
    super(paramThrowable);
    this.messages = $ImmutableSet.of(new Message($ImmutableList.of(), paramString, paramThrowable));
  }

  public ProvisionException(String paramString)
  {
    this.messages = $ImmutableSet.of(new Message(paramString));
  }

  public Collection getErrorMessages()
  {
    return this.messages;
  }

  public String getMessage()
  {
    return Errors.format("Guice provision errors", this.messages);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.ProvisionException
 * JD-Core Version:    0.6.2
 */