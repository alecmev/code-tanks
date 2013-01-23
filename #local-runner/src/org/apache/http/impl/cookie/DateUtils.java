package org.apache.http.impl.cookie;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class DateUtils
{
  private static final String[] DEFAULT_PATTERNS = { "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy" };
  private static final Date DEFAULT_TWO_DIGIT_YEAR_START = localCalendar.getTime();
  public static final TimeZone GMT = TimeZone.getTimeZone("GMT");

  public static Date parseDate(String paramString, String[] paramArrayOfString)
    throws DateParseException
  {
    return parseDate(paramString, paramArrayOfString, null);
  }

  public static Date parseDate(String paramString, String[] paramArrayOfString, Date paramDate)
    throws DateParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("dateValue is null");
    if (paramArrayOfString == null)
      paramArrayOfString = DEFAULT_PATTERNS;
    if (paramDate == null)
      paramDate = DEFAULT_TWO_DIGIT_YEAR_START;
    if ((paramString.length() > 1) && (paramString.startsWith("'")) && (paramString.endsWith("'")))
      paramString = paramString.substring(1, paramString.length() - 1);
    String[] arrayOfString = paramArrayOfString;
    int i = arrayOfString.length;
    int j = 0;
    while (j < i)
    {
      String str = arrayOfString[j];
      SimpleDateFormat localSimpleDateFormat = DateFormatHolder.formatFor(str);
      localSimpleDateFormat.set2DigitYearStart(paramDate);
      try
      {
        return localSimpleDateFormat.parse(paramString);
      }
      catch (ParseException localParseException)
      {
        j++;
      }
    }
    throw new DateParseException("Unable to parse the date " + paramString);
  }

  static
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(GMT);
    localCalendar.set(2000, 0, 1, 0, 0, 0);
    localCalendar.set(14, 0);
  }

  static final class DateFormatHolder
  {
    private static final ThreadLocal THREADLOCAL_FORMATS = new ThreadLocal()
    {
      protected SoftReference initialValue()
      {
        return new SoftReference(new HashMap());
      }
    };

    public static SimpleDateFormat formatFor(String paramString)
    {
      SoftReference localSoftReference = (SoftReference)THREADLOCAL_FORMATS.get();
      Object localObject = (Map)localSoftReference.get();
      if (localObject == null)
      {
        localObject = new HashMap();
        THREADLOCAL_FORMATS.set(new SoftReference(localObject));
      }
      SimpleDateFormat localSimpleDateFormat = (SimpleDateFormat)((Map)localObject).get(paramString);
      if (localSimpleDateFormat == null)
      {
        localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.US);
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        ((Map)localObject).put(paramString, localSimpleDateFormat);
      }
      return localSimpleDateFormat;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.cookie.DateUtils
 * JD-Core Version:    0.6.2
 */