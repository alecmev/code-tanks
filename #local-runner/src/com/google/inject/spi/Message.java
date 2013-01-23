package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.internal.Errors;
import com.google.inject.internal.util..ImmutableList;
import com.google.inject.internal.util..Objects;
import com.google.inject.internal.util..Preconditions;
import com.google.inject.internal.util..SourceProvider;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;

public final class Message
  implements Element, Serializable
{
  private final String message;
  private final Throwable cause;
  private final List sources;
  private static final long serialVersionUID = 0L;

  public Message(List paramList, String paramString, Throwable paramThrowable)
  {
    this.sources = $ImmutableList.copyOf(paramList);
    this.message = ((String).Preconditions.checkNotNull(paramString, "message"));
    this.cause = paramThrowable;
  }

  public Message(Object paramObject, String paramString)
  {
    this($ImmutableList.of(paramObject), paramString, null);
  }

  public Message(String paramString)
  {
    this($ImmutableList.of(), paramString, null);
  }

  public String getSource()
  {
    return this.sources.isEmpty() ? .SourceProvider.UNKNOWN_SOURCE.toString() : Errors.convert(this.sources.get(this.sources.size() - 1)).toString();
  }

  public List getSources()
  {
    return this.sources;
  }

  public String getMessage()
  {
    return this.message;
  }

  public Object acceptVisitor(ElementVisitor paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public Throwable getCause()
  {
    return this.cause;
  }

  public String toString()
  {
    return this.message;
  }

  public int hashCode()
  {
    return this.message.hashCode();
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Message))
      return false;
    Message localMessage = (Message)paramObject;
    return (this.message.equals(localMessage.message)) && ($Objects.equal(this.cause, localMessage.cause)) && (this.sources.equals(localMessage.sources));
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).addError(this);
  }

  private Object writeReplace()
    throws ObjectStreamException
  {
    Object[] arrayOfObject = this.sources.toArray();
    for (int i = 0; i < arrayOfObject.length; i++)
      arrayOfObject[i] = Errors.convert(arrayOfObject[i]).toString();
    return new Message($ImmutableList.of(arrayOfObject), this.message, this.cause);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.spi.Message
 * JD-Core Version:    0.6.2
 */