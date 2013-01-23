package com.a.a.a.a.c.a;

import com.a.a.a.a.a.b;
import com.a.a.a.a.a.h;
import com.a.a.a.a.a.m;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;
import com.a.a.a.a.c.a.a.f;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class a
  implements e
{
  private static final Logger a = LoggerFactory.getLogger(a.class);
  private static final Pattern b = Pattern.compile("[1-5][\\d]{4}");
  private static final AtomicInteger c = new AtomicInteger();
  private final Map d;
  private final String e;
  private final boolean f;
  private final int g;
  private final int h;
  private com.a.a.a.a.c.a.a.e i;
  private f j;

  public static a a(Map paramMap, int paramInt1, String paramString, int paramInt2)
  {
    Preconditions.checkArgument((paramInt1 >= 0) && (paramInt1 <= 9), "Unexpected argument 'playerIndex': " + paramInt1 + '.');
    Preconditions.checkArgument((paramInt2 >= 1) && (paramInt2 <= 9), "Unexpected argument 'teamSize': " + paramInt2 + '.');
    Preconditions.checkArgument(new File(paramString).isFile(), "Argument 'playerDefinition' is expected to be a file.");
    return new a(paramMap, paramInt1, paramString, paramInt2, false);
  }

  public static a b(Map paramMap, int paramInt1, String paramString, int paramInt2)
  {
    Preconditions.checkArgument((paramInt1 >= 0) && (paramInt1 <= 9), "Unexpected argument 'playerIndex': " + paramInt1 + '.');
    Preconditions.checkArgument((paramInt2 >= 1) && (paramInt2 <= 9), "Unexpected argument 'teamSize': " + paramInt2 + '.');
    Preconditions.checkArgument("#LocalTestPlayer".equals(paramString), "Argument 'playerDefinition' is not '#LocalTestPlayer'.");
    return new a(paramMap, paramInt1, paramString, paramInt2, true);
  }

  private a(Map paramMap, int paramInt1, String paramString, int paramInt2, boolean paramBoolean)
  {
    this.g = paramInt1;
    this.d = new HashMap(paramMap);
    this.e = paramString;
    this.h = paramInt2;
    this.f = paramBoolean;
  }

  public void a()
  {
    String str1 = (String)this.d.get("base-adapter-port");
    Preconditions.checkArgument((!StringUtils.isBlank(str1)) && (b.matcher(str1).matches()), "Argument 'base-adapter-port' is expected to be an integer between 10000 and 59999 inclusive.");
    int k = Integer.parseInt(str1) + c.getAndIncrement();
    String str2 = Integer.toString(k);
    String str3;
    if (Boolean.parseBoolean((String)this.d.get("debug")))
      str3 = StringUtils.repeat("0", 16);
    else
      str3 = RandomStringUtils.randomAlphanumeric(16);
    File localFile = Boolean.parseBoolean((String)this.d.get("dump-tcp-data")) ? new File("p" + (this.g + 1) + "-tcp-dump.bin") : null;
    this.j = new com.a.a.a.a.c.a.a.d(this.d, localFile);
    this.j.a(k);
    if (!this.f)
    {
      long l = TimeUnit.MILLISECONDS.toSeconds(20L * com.a.a.a.a.b.b.c.a(this.d) * this.h + TimeUnit.SECONDS.toMillis(1L) - 1L);
      StringBuilder localStringBuilder = new StringBuilder();
      String str4 = (String)this.d.get("system-user-login");
      if (!StringUtils.isBlank(str4))
        localStringBuilder.append(" -l ").append(str4);
      String str5 = (String)this.d.get("system-user-password");
      if (!StringUtils.isBlank(str5))
        localStringBuilder.append(" -p ").append(str5);
      HashMap localHashMap = new HashMap();
      localHashMap.put("remote-process.port", str2);
      localHashMap.put("time-limit-seconds", String.valueOf(l));
      localHashMap.put("system-user-credentials", localStringBuilder.toString());
      try
      {
        this.i = com.a.a.a.a.c.a.a.e.a(this.e, localHashMap, new String[] { "localhost", str2, str3 });
      }
      catch (IOException localIOException)
      {
        throw new d(String.format("Failed to start process for player '%s'.", new Object[] { this.e }), localIOException);
      }
    }
    a(str3);
  }

  private void a(String paramString)
  {
    for (int k = 2; k >= 0; k--)
    {
      String str1;
      try
      {
        this.j.a();
        str1 = this.j.b();
        if (paramString.equals(str1))
          break;
      }
      catch (d locald)
      {
        a.error("Got unexpected exception while authenticating strategy '" + this.e + "'.", locald);
        if (k == 0)
          throw locald;
        continue;
      }
      String str2 = String.format("Player '%s' has returned unexpected token: '%s' expected, but '%s' found.", new Object[] { this.e, paramString, str1 });
      a.error(str2);
      if (k == 0)
        throw new d(str2);
    }
  }

  public m[] a(o[] paramArrayOfo, n paramn)
  {
    this.j.a(new h(paramArrayOfo, paramn));
    return this.j.d();
  }

  public b[] b()
  {
    this.j.b(this.h);
    return this.j.c();
  }

  public void close()
  {
    if (this.j != null)
      this.j.e();
    new Thread(new c(this)).start();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.a
 * JD-Core Version:    0.6.2
 */