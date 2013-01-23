package com.google.protobuf;

public class SingleFieldBuilder
  implements GeneratedMessage.BuilderParent
{
  private GeneratedMessage.BuilderParent parent;
  private GeneratedMessage.Builder builder;
  private GeneratedMessage message;
  private boolean isClean;

  public SingleFieldBuilder(GeneratedMessage paramGeneratedMessage, GeneratedMessage.BuilderParent paramBuilderParent, boolean paramBoolean)
  {
    if (paramGeneratedMessage == null)
      throw new NullPointerException();
    this.message = paramGeneratedMessage;
    this.parent = paramBuilderParent;
    this.isClean = paramBoolean;
  }

  public void dispose()
  {
    this.parent = null;
  }

  public GeneratedMessage getMessage()
  {
    if (this.message == null)
      this.message = ((GeneratedMessage)this.builder.buildPartial());
    return this.message;
  }

  public GeneratedMessage build()
  {
    this.isClean = true;
    return getMessage();
  }

  public GeneratedMessage.Builder getBuilder()
  {
    if (this.builder == null)
    {
      this.builder = ((GeneratedMessage.Builder)this.message.newBuilderForType(this));
      this.builder.mergeFrom(this.message);
      this.builder.markClean();
    }
    return this.builder;
  }

  public MessageOrBuilder getMessageOrBuilder()
  {
    if (this.builder != null)
      return this.builder;
    return this.message;
  }

  public SingleFieldBuilder setMessage(GeneratedMessage paramGeneratedMessage)
  {
    if (paramGeneratedMessage == null)
      throw new NullPointerException();
    this.message = paramGeneratedMessage;
    if (this.builder != null)
    {
      this.builder.dispose();
      this.builder = null;
    }
    onChanged();
    return this;
  }

  public SingleFieldBuilder mergeFrom(GeneratedMessage paramGeneratedMessage)
  {
    if ((this.builder == null) && (this.message == this.message.getDefaultInstanceForType()))
      this.message = paramGeneratedMessage;
    else
      getBuilder().mergeFrom(paramGeneratedMessage);
    onChanged();
    return this;
  }

  public SingleFieldBuilder clear()
  {
    this.message = ((GeneratedMessage)(this.message != null ? this.message.getDefaultInstanceForType() : this.builder.getDefaultInstanceForType()));
    if (this.builder != null)
    {
      this.builder.dispose();
      this.builder = null;
    }
    onChanged();
    return this;
  }

  private void onChanged()
  {
    if (this.builder != null)
      this.message = null;
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
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.SingleFieldBuilder
 * JD-Core Version:    0.6.2
 */