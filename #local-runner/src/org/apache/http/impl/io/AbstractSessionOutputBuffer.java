package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractSessionOutputBuffer
  implements BufferInfo, SessionOutputBuffer
{
  private static final Charset ASCII = Charset.forName("US-ASCII");
  private static final byte[] CRLF = { 13, 10 };
  private OutputStream outstream;
  private ByteArrayBuffer buffer;
  private Charset charset;
  private CharsetEncoder encoder;
  private ByteBuffer bbuf;
  private boolean ascii = true;
  private int minChunkLimit = 512;
  private HttpTransportMetricsImpl metrics;
  private CodingErrorAction onMalformedInputAction;
  private CodingErrorAction onUnMappableInputAction;

  protected void init(OutputStream paramOutputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.outstream = paramOutputStream;
    this.buffer = new ByteArrayBuffer(paramInt);
    this.charset = Charset.forName(HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    this.ascii = this.charset.equals(ASCII);
    this.encoder = null;
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
    return this.buffer.length();
  }

  protected void flushBuffer()
    throws IOException
  {
    int i = this.buffer.length();
    if (i > 0)
    {
      this.outstream.write(this.buffer.buffer(), 0, i);
      this.buffer.clear();
      this.metrics.incrementBytesTransferred(i);
    }
  }

  public void flush()
    throws IOException
  {
    flushBuffer();
    this.outstream.flush();
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return;
    if ((paramInt2 > this.minChunkLimit) || (paramInt2 > this.buffer.capacity()))
    {
      flushBuffer();
      this.outstream.write(paramArrayOfByte, paramInt1, paramInt2);
      this.metrics.incrementBytesTransferred(paramInt2);
    }
    else
    {
      int i = this.buffer.capacity() - this.buffer.length();
      if (paramInt2 > i)
        flushBuffer();
      this.buffer.append(paramArrayOfByte, paramInt1, paramInt2);
    }
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return;
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(int paramInt)
    throws IOException
  {
    if (this.buffer.isFull())
      flushBuffer();
    this.buffer.append(paramInt);
  }

  public void writeLine(String paramString)
    throws IOException
  {
    if (paramString == null)
      return;
    if (paramString.length() > 0)
      if (this.ascii)
      {
        for (int i = 0; i < paramString.length(); i++)
          write(paramString.charAt(i));
      }
      else
      {
        CharBuffer localCharBuffer = CharBuffer.wrap(paramString);
        writeEncoded(localCharBuffer);
      }
    write(CRLF);
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    if (paramCharArrayBuffer == null)
      return;
    if (this.ascii)
    {
      int i = 0;
      int j = paramCharArrayBuffer.length();
      while (j > 0)
      {
        int k = this.buffer.capacity() - this.buffer.length();
        k = Math.min(k, j);
        if (k > 0)
          this.buffer.append(paramCharArrayBuffer, i, k);
        if (this.buffer.isFull())
          flushBuffer();
        i += k;
        j -= k;
      }
    }
    else
    {
      CharBuffer localCharBuffer = CharBuffer.wrap(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length());
      writeEncoded(localCharBuffer);
    }
    write(CRLF);
  }

  private void writeEncoded(CharBuffer paramCharBuffer)
    throws IOException
  {
    if (!paramCharBuffer.hasRemaining())
      return;
    if (this.encoder == null)
    {
      this.encoder = this.charset.newEncoder();
      this.encoder.onMalformedInput(this.onMalformedInputAction);
      this.encoder.onUnmappableCharacter(this.onUnMappableInputAction);
    }
    if (this.bbuf == null)
      this.bbuf = ByteBuffer.allocate(1024);
    this.encoder.reset();
    while (paramCharBuffer.hasRemaining())
    {
      localCoderResult = this.encoder.encode(paramCharBuffer, this.bbuf, true);
      handleEncodingResult(localCoderResult);
    }
    CoderResult localCoderResult = this.encoder.flush(this.bbuf);
    handleEncodingResult(localCoderResult);
    this.bbuf.clear();
  }

  private void handleEncodingResult(CoderResult paramCoderResult)
    throws IOException
  {
    if (paramCoderResult.isError())
      paramCoderResult.throwException();
    this.bbuf.flip();
    while (this.bbuf.hasRemaining())
      write(this.bbuf.get());
    this.bbuf.compact();
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.io.AbstractSessionOutputBuffer
 * JD-Core Version:    0.6.2
 */