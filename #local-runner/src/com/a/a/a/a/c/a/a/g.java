package com.a.a.a.a.c.a.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class g
{
  private static final Pattern a = Pattern.compile("(%[a-zA-Z_1-90]+%)");
  private final String b;
  private final List c;
  private final Set d;

  public g(String paramString, List paramList, Set paramSet)
  {
    this.b = paramString;
    this.c = new ArrayList(paramList);
    this.d = new HashSet(paramSet);
  }

  public String a(String paramString, Map paramMap)
  {
    String str1 = this.b;
    Matcher localMatcher = a.matcher(this.b);
    if (localMatcher.find())
    {
      int i = 0;
      int j = localMatcher.groupCount();
      while (i < j)
      {
        String str2 = localMatcher.group(i);
        String str3 = System.getenv(str2.substring(1, str2.length() - 1));
        if (str3 != null)
          str1 = str1.replace(str2, str3);
        i++;
      }
    }
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        str1 = str1.replace("${" + (String)localEntry.getKey() + '}', (CharSequence)localEntry.getValue());
      }
    }
    return String.format(str1, new Object[] { paramString });
  }

  public List a()
  {
    return Collections.unmodifiableList(this.c);
  }

  public boolean a(String paramString)
  {
    return this.d.contains(paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.c.a.a.g
 * JD-Core Version:    0.6.2
 */