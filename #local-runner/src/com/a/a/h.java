package com.a.a;

import com.a.a.a.a.e.a;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class h
  implements Runnable
{
  private static final Logger a = LoggerFactory.getLogger(h.class);
  private final String[] b;

  public h(String[] paramArrayOfString)
  {
    this.b = paramArrayOfString;
  }

  public void run()
  {
    Thread.setDefaultUncaughtExceptionHandler(new e(this));
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    for (Object localObject3 : this.b)
      if (localObject3.startsWith("-"))
      {
        Map.Entry localEntry = a(localObject3.substring("-".length()));
        localHashMap.put(localEntry.getKey(), localEntry.getValue());
      }
      else
      {
        localArrayList.add(localObject3);
      }
    try
    {
      ??? = (String)localHashMap.get("seed");
      if (!StringUtils.isBlank((String)???))
        com.a.c.a.a.b.a(Long.parseLong((String)???));
      localObject2 = (b)Guice.createInjector(new Module[] { new a() }).getInstance(b.class);
      try
      {
        ((b)localObject2).a(Collections.unmodifiableMap(localHashMap), Collections.unmodifiableList(localArrayList));
        ((b)localObject2).a();
      }
      finally
      {
        ((b)localObject2).b();
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      a.error("Got unexpected game exception.", localRuntimeException);
      Object localObject2 = (String)localHashMap.get("results-file");
      if (!StringUtils.isBlank((String)localObject2))
        try
        {
          String str = "FAILED\n" + ExceptionUtils.getStackTrace(localRuntimeException) + '\n';
          FileUtils.writeByteArrayToFile(new File((String)localObject2), str.getBytes("UTF-8"));
        }
        catch (IOException localIOException)
        {
          a.error(String.format("Can't write results to file '%s'.", new Object[] { localObject2 }), localIOException);
        }
    }
  }

  private static Map.Entry a(String paramString)
  {
    int i = paramString.indexOf('=');
    if (i <= 0)
      throw new IllegalArgumentException("Illegal property string: '" + paramString + "'.");
    return new AbstractMap.SimpleEntry(paramString.substring(0, i), paramString.substring(i + 1));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.h
 * JD-Core Version:    0.6.2
 */