package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RepeatedFieldBuilder
  implements GeneratedMessage.BuilderParent
{
  private GeneratedMessage.BuilderParent parent;
  private List messages;
  private boolean isMessagesListMutable;
  private List builders;
  private boolean isClean;
  private MessageExternalList externalMessageList;
  private BuilderExternalList externalBuilderList;
  private MessageOrBuilderExternalList externalMessageOrBuilderList;

  public RepeatedFieldBuilder(List paramList, boolean paramBoolean1, GeneratedMessage.BuilderParent paramBuilderParent, boolean paramBoolean2)
  {
    this.messages = paramList;
    this.isMessagesListMutable = paramBoolean1;
    this.parent = paramBuilderParent;
    this.isClean = paramBoolean2;
  }

  public void dispose()
  {
    this.parent = null;
  }

  private void ensureMutableMessageList()
  {
    if (!this.isMessagesListMutable)
    {
      this.messages = new ArrayList(this.messages);
      this.isMessagesListMutable = true;
    }
  }

  private void ensureBuilders()
  {
    if (this.builders == null)
    {
      this.builders = new ArrayList(this.messages.size());
      for (int i = 0; i < this.messages.size(); i++)
        this.builders.add(null);
    }
  }

  public int getCount()
  {
    return this.messages.size();
  }

  public boolean isEmpty()
  {
    return this.messages.isEmpty();
  }

  public GeneratedMessage getMessage(int paramInt)
  {
    return getMessage(paramInt, false);
  }

  private GeneratedMessage getMessage(int paramInt, boolean paramBoolean)
  {
    if (this.builders == null)
      return (GeneratedMessage)this.messages.get(paramInt);
    SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)this.builders.get(paramInt);
    if (localSingleFieldBuilder == null)
      return (GeneratedMessage)this.messages.get(paramInt);
    return paramBoolean ? localSingleFieldBuilder.build() : localSingleFieldBuilder.getMessage();
  }

  public GeneratedMessage.Builder getBuilder(int paramInt)
  {
    ensureBuilders();
    SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)this.builders.get(paramInt);
    if (localSingleFieldBuilder == null)
    {
      GeneratedMessage localGeneratedMessage = (GeneratedMessage)this.messages.get(paramInt);
      localSingleFieldBuilder = new SingleFieldBuilder(localGeneratedMessage, this, this.isClean);
      this.builders.set(paramInt, localSingleFieldBuilder);
    }
    return localSingleFieldBuilder.getBuilder();
  }

  public MessageOrBuilder getMessageOrBuilder(int paramInt)
  {
    if (this.builders == null)
      return (MessageOrBuilder)this.messages.get(paramInt);
    SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)this.builders.get(paramInt);
    if (localSingleFieldBuilder == null)
      return (MessageOrBuilder)this.messages.get(paramInt);
    return localSingleFieldBuilder.getMessageOrBuilder();
  }

  public RepeatedFieldBuilder setMessage(int paramInt, GeneratedMessage paramGeneratedMessage)
  {
    if (paramGeneratedMessage == null)
      throw new NullPointerException();
    ensureMutableMessageList();
    this.messages.set(paramInt, paramGeneratedMessage);
    if (this.builders != null)
    {
      SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)this.builders.set(paramInt, null);
      if (localSingleFieldBuilder != null)
        localSingleFieldBuilder.dispose();
    }
    onChanged();
    incrementModCounts();
    return this;
  }

  public RepeatedFieldBuilder addMessage(GeneratedMessage paramGeneratedMessage)
  {
    if (paramGeneratedMessage == null)
      throw new NullPointerException();
    ensureMutableMessageList();
    this.messages.add(paramGeneratedMessage);
    if (this.builders != null)
      this.builders.add(null);
    onChanged();
    incrementModCounts();
    return this;
  }

  public RepeatedFieldBuilder addMessage(int paramInt, GeneratedMessage paramGeneratedMessage)
  {
    if (paramGeneratedMessage == null)
      throw new NullPointerException();
    ensureMutableMessageList();
    this.messages.add(paramInt, paramGeneratedMessage);
    if (this.builders != null)
      this.builders.add(paramInt, null);
    onChanged();
    incrementModCounts();
    return this;
  }

  public RepeatedFieldBuilder addAllMessages(Iterable paramIterable)
  {
    Object localObject1 = paramIterable.iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (GeneratedMessage)((Iterator)localObject1).next();
      if (localObject2 == null)
        throw new NullPointerException();
    }
    if ((paramIterable instanceof Collection))
    {
      localObject1 = (Collection)paramIterable;
      if (((Collection)localObject1).size() == 0)
        return this;
      ensureMutableMessageList();
      localObject2 = paramIterable.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        GeneratedMessage localGeneratedMessage = (GeneratedMessage)((Iterator)localObject2).next();
        addMessage(localGeneratedMessage);
      }
    }
    else
    {
      ensureMutableMessageList();
      localObject1 = paramIterable.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (GeneratedMessage)((Iterator)localObject1).next();
        addMessage((GeneratedMessage)localObject2);
      }
    }
    onChanged();
    incrementModCounts();
    return this;
  }

  public GeneratedMessage.Builder addBuilder(GeneratedMessage paramGeneratedMessage)
  {
    ensureMutableMessageList();
    ensureBuilders();
    SingleFieldBuilder localSingleFieldBuilder = new SingleFieldBuilder(paramGeneratedMessage, this, this.isClean);
    this.messages.add(null);
    this.builders.add(localSingleFieldBuilder);
    onChanged();
    incrementModCounts();
    return localSingleFieldBuilder.getBuilder();
  }

  public GeneratedMessage.Builder addBuilder(int paramInt, GeneratedMessage paramGeneratedMessage)
  {
    ensureMutableMessageList();
    ensureBuilders();
    SingleFieldBuilder localSingleFieldBuilder = new SingleFieldBuilder(paramGeneratedMessage, this, this.isClean);
    this.messages.add(paramInt, null);
    this.builders.add(paramInt, localSingleFieldBuilder);
    onChanged();
    incrementModCounts();
    return localSingleFieldBuilder.getBuilder();
  }

  public void remove(int paramInt)
  {
    ensureMutableMessageList();
    this.messages.remove(paramInt);
    if (this.builders != null)
    {
      SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)this.builders.remove(paramInt);
      if (localSingleFieldBuilder != null)
        localSingleFieldBuilder.dispose();
    }
    onChanged();
    incrementModCounts();
  }

  public void clear()
  {
    this.messages = Collections.emptyList();
    this.isMessagesListMutable = false;
    if (this.builders != null)
    {
      Iterator localIterator = this.builders.iterator();
      while (localIterator.hasNext())
      {
        SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)localIterator.next();
        if (localSingleFieldBuilder != null)
          localSingleFieldBuilder.dispose();
      }
      this.builders = null;
    }
    onChanged();
    incrementModCounts();
  }

  public List build()
  {
    this.isClean = true;
    if ((!this.isMessagesListMutable) && (this.builders == null))
      return this.messages;
    int i = 1;
    if (!this.isMessagesListMutable)
    {
      for (j = 0; j < this.messages.size(); j++)
      {
        Message localMessage = (Message)this.messages.get(j);
        SingleFieldBuilder localSingleFieldBuilder = (SingleFieldBuilder)this.builders.get(j);
        if ((localSingleFieldBuilder != null) && (localSingleFieldBuilder.build() != localMessage))
        {
          i = 0;
          break;
        }
      }
      if (i != 0)
        return this.messages;
    }
    ensureMutableMessageList();
    for (int j = 0; j < this.messages.size(); j++)
      this.messages.set(j, getMessage(j, true));
    this.messages = Collections.unmodifiableList(this.messages);
    this.isMessagesListMutable = false;
    return this.messages;
  }

  public List getMessageList()
  {
    if (this.externalMessageList == null)
      this.externalMessageList = new MessageExternalList(this);
    return this.externalMessageList;
  }

  public List getBuilderList()
  {
    if (this.externalBuilderList == null)
      this.externalBuilderList = new BuilderExternalList(this);
    return this.externalBuilderList;
  }

  public List getMessageOrBuilderList()
  {
    if (this.externalMessageOrBuilderList == null)
      this.externalMessageOrBuilderList = new MessageOrBuilderExternalList(this);
    return this.externalMessageOrBuilderList;
  }

  private void onChanged()
  {
    if ((this.isClean) && (this.parent != null))
    {
      this.parent.markDirty();
      this.isClean = false;
    }
  }

  public void markDirty()
  {
    onChanged();
  }

  private void incrementModCounts()
  {
    if (this.externalMessageList != null)
      this.externalMessageList.incrementModCount();
    if (this.externalBuilderList != null)
      this.externalBuilderList.incrementModCount();
    if (this.externalMessageOrBuilderList != null)
      this.externalMessageOrBuilderList.incrementModCount();
  }

  private static class MessageOrBuilderExternalList extends AbstractList
    implements List
  {
    RepeatedFieldBuilder builder;

    MessageOrBuilderExternalList(RepeatedFieldBuilder paramRepeatedFieldBuilder)
    {
      this.builder = paramRepeatedFieldBuilder;
    }

    public int size()
    {
      return this.builder.getCount();
    }

    public MessageOrBuilder get(int paramInt)
    {
      return this.builder.getMessageOrBuilder(paramInt);
    }

    void incrementModCount()
    {
      this.modCount += 1;
    }
  }

  private static class BuilderExternalList extends AbstractList
    implements List
  {
    RepeatedFieldBuilder builder;

    BuilderExternalList(RepeatedFieldBuilder paramRepeatedFieldBuilder)
    {
      this.builder = paramRepeatedFieldBuilder;
    }

    public int size()
    {
      return this.builder.getCount();
    }

    public GeneratedMessage.Builder get(int paramInt)
    {
      return this.builder.getBuilder(paramInt);
    }

    void incrementModCount()
    {
      this.modCount += 1;
    }
  }

  private static class MessageExternalList extends AbstractList
    implements List
  {
    RepeatedFieldBuilder builder;

    MessageExternalList(RepeatedFieldBuilder paramRepeatedFieldBuilder)
    {
      this.builder = paramRepeatedFieldBuilder;
    }

    public int size()
    {
      return this.builder.getCount();
    }

    public GeneratedMessage get(int paramInt)
    {
      return this.builder.getMessage(paramInt);
    }

    void incrementModCount()
    {
      this.modCount += 1;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.RepeatedFieldBuilder
 * JD-Core Version:    0.6.2
 */