package com.google.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractMessageLite
  implements MessageLite
{
  public ByteString toByteString()
  {
    try
    {
      ByteString.CodedBuilder localCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
      writeTo(localCodedBuilder.getCodedOutput());
      return localCodedBuilder.build();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", localIOException);
    }
  }

  public byte[] toByteArray()
  {
    try
    {
      byte[] arrayOfByte = new byte[getSerializedSize()];
      CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(arrayOfByte);
      writeTo(localCodedOutputStream);
      localCodedOutputStream.checkNoSpaceLeft();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", localIOException);
    }
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    int i = CodedOutputStream.computePreferredBufferSize(getSerializedSize());
    CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(paramOutputStream, i);
    writeTo(localCodedOutputStream);
    localCodedOutputStream.flush();
  }

  public void writeDelimitedTo(OutputStream paramOutputStream)
    throws IOException
  {
    int i = getSerializedSize();
    int j = CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(i) + i);
    CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(paramOutputStream, j);
    localCodedOutputStream.writeRawVarint32(i);
    writeTo(localCodedOutputStream);
    localCodedOutputStream.flush();
  }

  public static abstract class Builder
    implements MessageLite.Builder
  {
    public abstract Builder clone();

    public Builder mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public abstract Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;

    public Builder mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = paramByteString.newCodedInput();
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = paramByteString.newCodedInput();
        mergeFrom(localCodedInputStream, paramExtensionRegistryLite);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length, paramExtensionRegistryLite);
    }

    public Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(localCodedInputStream, paramExtensionRegistryLite);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", localIOException);
      }
    }

    public Builder mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(localCodedInputStream);
      localCodedInputStream.checkLastTagWas(0);
      return this;
    }

    public Builder mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(localCodedInputStream, paramExtensionRegistryLite);
      localCodedInputStream.checkLastTagWas(0);
      return this;
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      int i = paramInputStream.read();
      if (i == -1)
        return false;
      int j = CodedInputStream.readRawVarint32(i, paramInputStream);
      LimitedInputStream localLimitedInputStream = new LimitedInputStream(paramInputStream, j);
      mergeFrom(localLimitedInputStream, paramExtensionRegistryLite);
      return true;
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      return mergeDelimitedFrom(paramInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    protected static UninitializedMessageException newUninitializedMessageException(MessageLite paramMessageLite)
    {
      return new UninitializedMessageException(paramMessageLite);
    }

    protected static void addAll(Iterable paramIterable, Collection paramCollection)
    {
      Object localObject1 = paramIterable.iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((Iterator)localObject1).next();
        if (localObject2 == null)
          throw new NullPointerException();
      }
      if ((paramIterable instanceof Collection))
      {
        localObject1 = (Collection)paramIterable;
        paramCollection.addAll((Collection)localObject1);
      }
      else
      {
        localObject1 = paramIterable.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = ((Iterator)localObject1).next();
          paramCollection.add(localObject2);
        }
      }
    }

    static final class LimitedInputStream extends FilterInputStream
    {
      private int limit;

      LimitedInputStream(InputStream paramInputStream, int paramInt)
      {
        super();
        this.limit = paramInt;
      }

      public int available()
        throws IOException
      {
        return Math.min(super.available(), this.limit);
      }

      public int read()
        throws IOException
      {
        if (this.limit <= 0)
          return -1;
        int i = super.read();
        if (i >= 0)
          this.limit -= 1;
        return i;
      }

      public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws IOException
      {
        if (this.limit <= 0)
          return -1;
        paramInt2 = Math.min(paramInt2, this.limit);
        int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
        if (i >= 0)
          this.limit -= i;
        return i;
      }

      public long skip(long paramLong)
        throws IOException
      {
        long l = super.skip(Math.min(paramLong, this.limit));
        if (l >= 0L)
          this.limit = ((int)(this.limit - l));
        return l;
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.AbstractMessageLite
 * JD-Core Version:    0.6.2
 */