package com.google.inject.internal;

import com.google.inject.Guice;
import com.google.inject.spi.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

final class MessageProcessor extends AbstractProcessor
{
  private static final Logger logger = Logger.getLogger(Guice.class.getName());

  MessageProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(Message paramMessage)
  {
    if (paramMessage.getCause() != null)
    {
      String str = getRootMessage(paramMessage.getCause());
      logger.log(Level.INFO, "An exception was caught and reported. Message: " + str, paramMessage.getCause());
    }
    this.errors.addMessage(paramMessage);
    return Boolean.valueOf(true);
  }

  public static String getRootMessage(Throwable paramThrowable)
  {
    Throwable localThrowable = paramThrowable.getCause();
    return localThrowable == null ? paramThrowable.toString() : getRootMessage(localThrowable);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.MessageProcessor
 * JD-Core Version:    0.6.2
 */