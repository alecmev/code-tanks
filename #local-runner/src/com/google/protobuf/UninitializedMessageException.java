package com.google.protobuf;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class UninitializedMessageException extends RuntimeException
{
  private static final long serialVersionUID = -7466929953374883507L;
  private final List missingFields;

  public UninitializedMessageException(MessageLite paramMessageLite)
  {
    super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    this.missingFields = null;
  }

  public UninitializedMessageException(List paramList)
  {
    super(buildDescription(paramList));
    this.missingFields = paramList;
  }

  public List getMissingFields()
  {
    return Collections.unmodifiableList(this.missingFields);
  }

  public InvalidProtocolBufferException asInvalidProtocolBufferException()
  {
    return new InvalidProtocolBufferException(getMessage());
  }

  private static String buildDescription(List paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder("Message missing required fields: ");
    int i = 1;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (i != 0)
        i = 0;
      else
        localStringBuilder.append(", ");
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.UninitializedMessageException
 * JD-Core Version:    0.6.2
 */