package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractSessionInputBuffer
  implements BufferInfo, SessionInputBuffer
{
  private static final Charset ASCII = Charset.forName("US-ASCII");
  private InputStream instream;
  private byte[] buffer;
  private int bufferpos;
  private int bufferlen;
  private ByteArrayBuffer linebuffer = null;
  private Charset charset;
  private CharsetDecoder decoder;
  private CharBuffer cbuf;
  private boolean ascii = true;
  private int maxLineLen = -1;
  private int minChunkLimit = 512;
  private HttpTransportMetricsImpl metrics;
  private CodingErrorAction onMalformedInputAction;
  private CodingErrorAction onUnMappableInputAction;

  protected void init(InputStream paramInputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.instream = paramInputStream;
    this.buffer = new byte[paramInt];
    this.bufferpos = 0;
    this.bufferlen = 0;
    this.linebuffer = new ByteArrayBuffer(paramInt);
    this.charset = Charset.forName(HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    this.ascii = this.charset.equals(ASCII);
    this.decoder = null;
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    this.minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    this.metrics = createTransportMetrics();
    this.onMalformedInputAction = HttpProtocolParams.getMalformedInputAction(paramHttpParams);
    this.onUnMappableInputAction = HttpProtocolParams.getUnmappableInputAction(paramHttpParams);
  }

  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }

  public int length()
  {
    return this.bufferlen - this.bufferpos;
  }

  protected int fillBuffer()
    throws IOException
  {
    if (this.bufferpos > 0)
    {
      i = this.bufferlen - this.bufferpos;
      if (i > 0)
        System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, i);
      this.bufferpos = 0;
      this.bufferlen = i;
    }
    int j = this.bufferlen;
    int k = this.buffer.length - j;
    int i = this.instream.read(this.buffer, j, k);
    if (i == -1)
      return -1;
    this.bufferlen = (j + i);
    this.metrics.incrementBytesTransferred(i);
    return i;
  }

  protected boolean hasBufferedData()
  {
    return this.bufferpos < this.bufferlen;
  }

  public int read()
    throws IOException
  {
    int i = 0;
    while (!hasBufferedData())
    {
      i = fillBuffer();
      if (i == -1)
        return -1;
    }
    return this.buffer[(this.bufferpos++)] & 0xFF;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return 0;
    if (hasBufferedData())
    {
      i = Math.min(paramInt2, this.bufferlen - this.bufferpos);
      System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, i);
      this.bufferpos += i;
      return i;
    }
    if (paramInt2 > this.minChunkLimit)
    {
      i = this.instream.read(paramArrayOfByte, paramInt1, paramInt2);
      if (i > 0)
        this.metrics.incrementBytesTransferred(i);
      return i;
    }
    while (!hasBufferedData())
    {
      i = fillBuffer();
      if (i == -1)
        return -1;
    }
    int i = Math.min(paramInt2, this.bufferlen - this.bufferpos);
    System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, i);
    this.bufferpos += i;
    return i;
  }

  private int locateLF()
  {
    for (int i = this.bufferpos; i < this.bufferlen; i++)
      if (this.buffer[i] == 10)
        return i;
    return -1;
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    int i = 0;
    int j = 1;
    while (j != 0)
    {
      int k = locateLF();
      int m;
      if (k != -1)
      {
        if (this.linebuffer.isEmpty())
          return lineFromReadBuffer(paramCharArrayBuffer, k);
        j = 0;
        m = k + 1 - this.bufferpos;
        this.linebuffer.append(this.buffer, this.bufferpos, m);
        this.bufferpos = (k + 1);
      }
      else
      {
        if (hasBufferedData())
        {
          m = this.bufferlen - this.bufferpos;
          this.linebuffer.append(this.buffer, this.bufferpos, m);
          this.bufferpos = this.bufferlen;
        }
        i = fillBuffer();
        if (i == -1)
          j = 0;
      }
      if ((this.maxLineLen > 0) && (this.linebuffer.length() >= this.maxLineLen))
        throw new IOException("Maximum line length limit exceeded");
    }
    if ((i == -1) && (this.linebuffer.isEmpty()))
      return -1;
    return lineFromLineBuffer(paramCharArrayBuffer);
  }

  private int lineFromLineBuffer(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int i = this.linebuffer.length();
    if (i > 0)
    {
      if (this.linebuffer.byteAt(i - 1) == 10)
        i--;
      if ((i > 0) && (this.linebuffer.byteAt(i - 1) == 13))
        i--;
    }
    if (this.ascii)
    {
      paramCharArrayBuffer.append(this.linebuffer, 0, i);
    }
    else
    {
      ByteBuffer localByteBuffer = ByteBuffer.wrap(this.linebuffer.buffer(), 0, i);
      i = appendDecoded(paramCharArrayBuffer, localByteBuffer);
    }
    this.linebuffer.clear();
    return i;
  }

  private int lineFromReadBuffer(CharArrayBuffer paramCharArrayBuffer, int paramInt)
    throws IOException
  {
    int i = this.bufferpos;
    this.bufferpos = (paramInt + 1);
    if ((paramInt > i) && (this.buffer[(paramInt - 1)] == 13))
      paramInt--;
    int j = paramInt - i;
    if (this.ascii)
    {
      paramCharArrayBuffer.append(this.buffer, i, j);
    }
    else
    {
      ByteBuffer localByteBuffer = ByteBuffer.wrap(this.buffer, i, j);
      j = appendDecoded(paramCharArrayBuffer, localByteBuffer);
    }
    return j;
  }

  private int appendDecoded(CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (!paramByteBuffer.hasRemaining())
      return 0;
    if (this.decoder == null)
    {
      this.decoder = this.charset.newDecoder();
      this.decoder.onMalformedInput(this.onMalformedInputAction);
      this.decoder.onUnmappableCharacter(this.onUnMappableInputAction);
    }
    if (this.cbuf == null)
      this.cbuf = CharBuffer.allocate(1024);
    this.decoder.reset();
    int i = 0;
    while (paramByteBuffer.hasRemaining())
    {
      localCoderResult = this.decoder.decode(paramByteBuffer, this.cbuf, true);
      i += handleDecodingResult(localCoderResult, paramCharArrayBuffer, paramByteBuffer);
    }
    CoderResult localCoderResult = this.decoder.flush(this.cbuf);
    i += handleDecodingResult(localCoderResult, paramCharArrayBuffer, paramByteBuffer);
    this.cbuf.clear();
    return i;
  }

  private int handleDecodingResult(CoderResult paramCoderResult, CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (paramCoderResult.isError())
      paramCoderResult.throwException();
    this.cbuf.flip();
    int i = this.cbuf.remaining();
    while (this.cbuf.hasRemaining())
      paramCharArrayBuffer.append(this.cbuf.get());
    this.cbuf.compact();
    return i;
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.AbstractSessionInputBuffer
 * JD-Core Version:    0.6.2
 */