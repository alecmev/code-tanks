package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.TruncatedChunkException;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

public class ChunkedInputStream extends InputStream
{
  private final SessionInputBuffer in;
  private final CharArrayBuffer buffer;
  private int state;
  private int chunkSize;
  private int pos;
  private boolean eof = false;
  private boolean closed = false;
  private Header[] footers = new Header[0];

  public ChunkedInputStream(SessionInputBuffer paramSessionInputBuffer)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.in = paramSessionInputBuffer;
    this.pos = 0;
    this.buffer = new CharArrayBuffer(16);
    this.state = 1;
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
    {
      int i = ((BufferInfo)this.in).length();
      return Math.min(i, this.chunkSize - this.pos);
    }
    return 0;
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof)
      return -1;
    if (this.state != 2)
    {
      nextChunk();
      if (this.eof)
        return -1;
    }
    int i = this.in.read();
    if (i != -1)
    {
      this.pos += 1;
      if (this.pos >= this.chunkSize)
        this.state = 3;
    }
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof)
      return -1;
    if (this.state != 2)
    {
      nextChunk();
      if (this.eof)
        return -1;
    }
    paramInt2 = Math.min(paramInt2, this.chunkSize - this.pos);
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i != -1)
    {
      this.pos += i;
      if (this.pos >= this.chunkSize)
        this.state = 3;
      return i;
    }
    this.eof = true;
    throw new TruncatedChunkException("Truncated chunk ( expected size: " + this.chunkSize + "; actual size: " + this.pos + ")");
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  private void nextChunk()
    throws IOException
  {
    this.chunkSize = getChunkSize();
    if (this.chunkSize < 0)
      throw new MalformedChunkCodingException("Negative chunk size");
    this.state = 2;
    this.pos = 0;
    if (this.chunkSize == 0)
    {
      this.eof = true;
      parseTrailerHeaders();
    }
  }

  private int getChunkSize()
    throws IOException
  {
    int i = this.state;
    int j;
    switch (i)
    {
    case 3:
      this.buffer.clear();
      j = this.in.readLine(this.buffer);
      if (j == -1)
        return 0;
      if (!this.buffer.isEmpty())
        throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
      this.state = 1;
    case 1:
      this.buffer.clear();
      j = this.in.readLine(this.buffer);
      if (j == -1)
        return 0;
      int k = this.buffer.indexOf(59);
      if (k < 0)
        k = this.buffer.length();
      try
      {
        return Integer.parseInt(this.buffer.substringTrimmed(0, k), 16);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new MalformedChunkCodingException("Bad chunk header");
      }
    }
    throw new IllegalStateException("Inconsistent codec state");
  }

  private void parseTrailerHeaders()
    throws IOException
  {
    try
    {
      this.footers = AbstractMessageParser.parseHeaders(this.in, -1, -1, null);
    }
    catch (HttpException localHttpException)
    {
      MalformedChunkCodingException localMalformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + localHttpException.getMessage());
      localMalformedChunkCodingException.initCause(localHttpException);
      throw localMalformedChunkCodingException;
    }
  }

  public void close()
    throws IOException
  {
    if (!this.closed)
      try
      {
        if (!this.eof)
        {
          byte[] arrayOfByte = new byte[2048];
          while (read(arrayOfByte) >= 0);
        }
      }
      finally
      {
        this.eof = true;
        this.closed = true;
      }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.ChunkedInputStream
 * JD-Core Version:    0.6.2
 */