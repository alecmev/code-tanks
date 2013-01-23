package com.a.a.a.a.c.a.a;

import com.a.a.a.a.a.a;
import com.a.a.a.a.a.b;
import com.a.a.a.a.a.h;
import com.a.a.a.a.a.i;
import com.a.a.a.a.a.k;
import com.a.a.a.a.a.m;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;
import com.google.common.primitives.Ints;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.IOUtils;

public class d
  implements f
{
  private static final ByteOrder a = ByteOrder.LITTLE_ENDIAN;
  private final AtomicBoolean b = new AtomicBoolean();
  private final int c;
  private ServerSocket d;
  private Socket e;
  private InputStream f;
  private OutputStream g;
  private final ByteArrayOutputStream h;
  private final File i;
  private OutputStream j;

  public d(Map paramMap, File paramFile)
  {
    if (Boolean.parseBoolean((String)paramMap.get("debug")))
      this.c = Ints.checkedCast(TimeUnit.MINUTES.toMillis(10L));
    else
      this.c = Ints.checkedCast(TimeUnit.SECONDS.toMillis(10L));
    this.h = new ByteArrayOutputStream(1048576);
    this.i = paramFile;
  }

  public void a(int paramInt)
  {
    try
    {
      this.d = new ServerSocket(paramInt);
      this.d.setSoTimeout(this.c);
      this.d.setReceiveBufferSize(1048576);
    }
    catch (IOException localIOException)
    {
      throw new com.a.a.a.a.c.a.d(String.format("Can't start %s.", new Object[] { d.class }), localIOException);
    }
  }

  public void a()
  {
    IOUtils.closeQuietly(this.e);
    try
    {
      this.e = this.d.accept();
      this.e.setSoTimeout(this.c);
      this.e.setSendBufferSize(1048576);
      this.e.setReceiveBufferSize(1048576);
      this.f = this.e.getInputStream();
      this.g = this.e.getOutputStream();
      if (this.i != null)
        this.j = new FileOutputStream(this.i);
    }
    catch (IOException localIOException)
    {
      throw new com.a.a.a.a.c.a.d("Can't accept remote process connection.", localIOException);
    }
  }

  public String b()
  {
    a((a)a(a.class), a.c);
    return g();
  }

  public void b(int paramInt)
  {
    a(a.d);
    c(paramInt);
    l();
  }

  public b[] c()
  {
    a((a)a(a.class), a.e);
    int k = i();
    if (k < 0)
      return null;
    b[] arrayOfb = new b[k];
    for (int m = 0; m < k; m++)
      arrayOfb[m] = ((b)a(b.class));
    return arrayOfb;
  }

  public void a(h paramh)
  {
    a(a.f);
    if (paramh == null)
    {
      a(false);
      return;
    }
    a(true);
    a(paramh.a());
    a(paramh.b());
    l();
  }

  public m[] d()
  {
    a((a)a(a.class), a.g);
    int k = i();
    if (k < 0)
      return null;
    m[] arrayOfm = new m[k];
    for (int m = 0; m < k; m++)
      if (h())
      {
        m localm = new m();
        arrayOfm[m] = localm;
        localm.a(k());
        localm.b(k());
        localm.c(k());
        localm.a((com.a.a.a.a.a.d)a(com.a.a.a.a.a.d.class));
      }
    return arrayOfm;
  }

  public void e()
  {
    try
    {
      a(a.b);
      l();
    }
    catch (RuntimeException localRuntimeException)
    {
    }
  }

  private void a(n paramn)
  {
    if (paramn == null)
    {
      a(false);
      return;
    }
    a(true);
    c(paramn.b());
    a(paramn.d());
    a(paramn.e());
    a(paramn.f());
    a(paramn.g());
    a(paramn.h());
    a(paramn.i());
    a(paramn.j());
  }

  private void a(i[] paramArrayOfi)
  {
    if (paramArrayOfi == null)
    {
      c(-1);
    }
    else
    {
      int k = paramArrayOfi.length;
      c(k);
      for (int m = 0; m < k; m++)
      {
        i locali = paramArrayOfi[m];
        if (locali == null)
        {
          a(false);
        }
        else
        {
          a(true);
          a(locali.a());
          c(locali.b());
          a(locali.c());
        }
      }
    }
  }

  private void a(a[] paramArrayOfa)
  {
    if (paramArrayOfa == null)
    {
      c(-1);
    }
    else
    {
      int k = paramArrayOfa.length;
      c(k);
      for (int m = 0; m < k; m++)
      {
        a locala = paramArrayOfa[m];
        if (locala == null)
        {
          a(false);
        }
        else
        {
          a(true);
          a(locala.c());
          a(locala.d());
          a(locala.e());
          a(locala.f());
          a(locala.g());
        }
      }
    }
  }

  private void a(o[] paramArrayOfo)
  {
    if (paramArrayOfo == null)
    {
      c(-1);
    }
    else
    {
      int k = paramArrayOfo.length;
      c(k);
      for (int m = 0; m < k; m++)
      {
        o localo = paramArrayOfo[m];
        if (localo == null)
        {
          a(false);
        }
        else
        {
          a(true);
          a(localo.c());
          a(localo.a());
          c(localo.b());
          a(localo.f());
          a(localo.g());
          a(localo.h());
          a(localo.i());
          a(localo.j());
          a(localo.k());
          a(localo.l());
          c(localo.m());
          c(localo.o());
          c(localo.q());
          c(localo.r());
          c(localo.s());
          a(localo.t());
          a(localo.u());
        }
      }
    }
  }

  private void a(com.a.a.a.a.a.f[] paramArrayOff)
  {
    if (paramArrayOff == null)
    {
      c(-1);
    }
    else
    {
      int k = paramArrayOff.length;
      c(k);
      for (int m = 0; m < k; m++)
      {
        com.a.a.a.a.a.f localf = paramArrayOff[m];
        if (localf == null)
        {
          a(false);
        }
        else
        {
          a(true);
          a(localf.c());
          a(localf.a());
          a(localf.d());
          a(localf.e());
          a(localf.f());
          a(localf.g());
          a(localf.h());
          a(localf.i());
          a(localf.j());
          a(localf.k());
          a(localf.b());
        }
      }
    }
  }

  private void a(k[] paramArrayOfk)
  {
    if (paramArrayOfk == null)
    {
      c(-1);
    }
    else
    {
      int k = paramArrayOfk.length;
      c(k);
      for (int m = 0; m < k; m++)
      {
        k localk = paramArrayOfk[m];
        if (localk == null)
        {
          a(false);
        }
        else
        {
          a(true);
          a(localk.c());
          a(localk.d());
          a(localk.e());
          a(localk.f());
          a(localk.g());
          a(localk.a());
        }
      }
    }
  }

  private static void a(a parama1, a parama2)
  {
    if (parama1 != parama2)
      throw new com.a.a.a.a.c.a.d(String.format("Received wrong message [actual=%s, expected=%s].", new Object[] { parama1, parama2 }));
  }

  private Enum a(Class paramClass)
  {
    int k = d(1)[0];
    Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
    int m = 0;
    int n = arrayOfEnum.length;
    while (m < n)
    {
      Enum localEnum = arrayOfEnum[m];
      if (localEnum.ordinal() == k)
        return localEnum;
      m++;
    }
    return null;
  }

  private void a(Enum paramEnum)
  {
    a(new byte[] { (byte)(paramEnum == null ? -1 : paramEnum.ordinal()) });
  }

  private String g()
  {
    int k = i();
    if (k < 0)
      return null;
    try
    {
      return new String(d(k), "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new com.a.a.a.a.c.a.d("UTF-8 is unsupported.", localUnsupportedEncodingException);
    }
  }

  private void a(String paramString)
  {
    if (paramString == null)
    {
      c(-1);
      return;
    }
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = paramString.getBytes("UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new com.a.a.a.a.c.a.d("UTF-8 is unsupported.", localUnsupportedEncodingException);
    }
    c(arrayOfByte.length);
    a(arrayOfByte);
  }

  private boolean h()
  {
    return d(1)[0] == 1;
  }

  private void a(boolean paramBoolean)
  {
    a(new byte[] { (byte)(paramBoolean ? 1 : 0) });
  }

  private int i()
  {
    return ByteBuffer.wrap(d(4)).order(a).getInt();
  }

  private void c(int paramInt)
  {
    a(ByteBuffer.allocate(4).order(a).putInt(paramInt).array());
  }

  private long j()
  {
    return ByteBuffer.wrap(d(8)).order(a).getLong();
  }

  private void a(long paramLong)
  {
    a(ByteBuffer.allocate(8).order(a).putLong(paramLong).array());
  }

  private double k()
  {
    return Double.longBitsToDouble(j());
  }

  private void a(double paramDouble)
  {
    a(Double.doubleToLongBits(paramDouble));
  }

  private byte[] d(int paramInt)
  {
    m();
    try
    {
      return IOUtils.toByteArray(this.f, paramInt);
    }
    catch (IOException localIOException)
    {
      throw new com.a.a.a.a.c.a.d(String.format("Can't read %d bytes from input stream.", new Object[] { Integer.valueOf(paramInt) }), localIOException);
    }
  }

  private void a(byte[] paramArrayOfByte)
  {
    m();
    try
    {
      this.h.write(paramArrayOfByte);
    }
    catch (IOException localIOException)
    {
      throw new com.a.a.a.a.c.a.d(String.format("Can't write %d bytes into output stream.", new Object[] { Integer.valueOf(paramArrayOfByte.length) }), localIOException);
    }
  }

  private void l()
  {
    m();
    try
    {
      byte[] arrayOfByte = this.h.toByteArray();
      this.h.reset();
      this.g.write(arrayOfByte);
      this.g.flush();
      if (this.j != null)
        this.j.write(arrayOfByte);
    }
    catch (IOException localIOException)
    {
      throw new com.a.a.a.a.c.a.d("Can't flush output stream.", localIOException);
    }
  }

  private void m()
  {
    if (this.b.get())
      throw new IllegalStateException(String.format("%s is stopped.", new Object[] { d.class }));
  }

  public void f()
  {
    if (!this.b.compareAndSet(false, true))
      return;
    IOUtils.closeQuietly(this.j);
    IOUtils.closeQuietly(this.e);
    try
    {
      if (this.d != null)
        this.d.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  private static enum a
  {
    a, b, c, d, e, f, g;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.a.d
 * JD-Core Version:    0.6.2
 */