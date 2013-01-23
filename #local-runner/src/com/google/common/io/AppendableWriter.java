package com.google.common.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

class AppendableWriter extends Writer
{
  private final Appendable target;
  private boolean closed;

  AppendableWriter(Appendable paramAppendable)
  {
    this.target = paramAppendable;
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    checkNotClosed();
    this.target.append(new String(paramArrayOfChar, paramInt1, paramInt2));
  }

  public void flush()
    throws IOException
  {
    checkNotClosed();
    if ((this.target instanceof Flushable))
      ((Flushable)this.target).flush();
  }

  public void close()
    throws IOException
  {
    this.closed = true;
    if ((this.target instanceof Closeable))
      ((Closeable)this.target).close();
  }

  public void write(int paramInt)
    throws IOException
  {
    checkNotClosed();
    this.target.append((char)paramInt);
  }

  public void write(String paramString)
    throws IOException
  {
    checkNotClosed();
    this.target.append(paramString);
  }

  public void write(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    checkNotClosed();
    this.target.append(paramString, paramInt1, paramInt1 + paramInt2);
  }

  public Writer append(char paramChar)
    throws IOException
  {
    checkNotClosed();
    this.target.append(paramChar);
    return this;
  }

  public Writer append(CharSequence paramCharSequence)
    throws IOException
  {
    checkNotClosed();
    this.target.append(paramCharSequence);
    return this;
  }

  public Writer append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    throws IOException
  {
    checkNotClosed();
    this.target.append(paramCharSequence, paramInt1, paramInt2);
    return this;
  }

  private void checkNotClosed()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Cannot write to a closed writer.");
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.io.AppendableWriter
 * JD-Core Version:    0.6.2
 */