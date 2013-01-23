package com.a.a.a.a.c.a.a;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class c
{
  private static final Pattern a = Pattern.compile(";");
  private static final ConcurrentMap b = new ConcurrentHashMap();

  private c()
  {
    throw new UnsupportedOperationException();
  }

  public static g a(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.endsWith(".zip"))
        paramString = paramString.substring(0, paramString.length() - ".zip".length());
      Iterator localIterator = b.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (paramString.endsWith('.' + (String)localEntry.getKey()))
          return (g)localEntry.getValue();
      }
    }
    throw new IllegalArgumentException(String.format("Can't find run script for '%s'.", new Object[] { paramString }));
  }

  private static void a()
  {
    String str1 = "/remote-process.properties";
    Properties localProperties = new Properties();
    try
    {
      localProperties.load(c.class.getResourceAsStream(str1));
    }
    catch (IOException localIOException)
    {
      throw new IllegalArgumentException(String.format("Can't read property file '%s'.", new Object[] { str1 }), localIOException);
    }
    String[] arrayOfString1 = a.split(localProperties.getProperty("remote-process.supported-postfixes"));
    for (String str2 : arrayOfString1)
    {
      String str3 = "remote-process.postfix-command-line." + str2;
      String str4 = localProperties.getProperty(str3);
      if (StringUtils.isBlank(str4))
        throw new IllegalArgumentException(String.format("Expected property '%s' in '%s'.", new Object[] { str3, str1 }));
      String str5 = str4.trim();
      String str6 = "remote-process.resources-to-copy." + str2;
      String str7 = localProperties.getProperty(str6);
      ArrayList localArrayList = new ArrayList();
      String[] arrayOfString3;
      if (!StringUtils.isBlank(str7))
        for (arrayOfString3 : a.split(str7))
          localArrayList.add(arrayOfString3.trim());
      ??? = "remote-process.resources-to-filter." + str2;
      String str8 = localProperties.getProperty((String)???);
      HashSet localHashSet = new HashSet();
      if (!StringUtils.isBlank(str8))
        for (String str9 : a.split(str8))
          localHashSet.add(str9.trim());
      b.put(str2, new g(str5, localArrayList, localHashSet));
    }
  }

  static
  {
    a();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.a.c
 * JD-Core Version:    0.6.2
 */