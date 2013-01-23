package com.google.common.eventbus;

import com.google.common.annotations.Beta;

@Beta
public class DeadEvent
{
  private final Object source;
  private final Object event;

  public DeadEvent(Object paramObject1, Object paramObject2)
  {
    this.source = paramObject1;
    this.event = paramObject2;
  }

  public Object getSource()
  {
    return this.source;
  }

  public Object getEvent()
  {
    return this.event;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.eventbus.DeadEvent
 * JD-Core Version:    0.6.2
 */