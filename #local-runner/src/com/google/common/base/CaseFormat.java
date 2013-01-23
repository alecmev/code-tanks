package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum CaseFormat
{
  LOWER_HYPHEN(CharMatcher.is('-'), "-"), LOWER_UNDERSCORE(CharMatcher.is('_'), "_"), LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), ""), UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), ""), UPPER_UNDERSCORE(CharMatcher.is('_'), "_");

  private final CharMatcher wordBoundary;
  private final String wordSeparator;

  private CaseFormat(CharMatcher arg3, String arg4)
  {
    Object localObject1;
    this.wordBoundary = localObject1;
    Object localObject2;
    this.wordSeparator = localObject2;
  }

  public String to(CaseFormat paramCaseFormat, String paramString)
  {
    if (paramCaseFormat == null)
      throw new NullPointerException();
    if (paramString == null)
      throw new NullPointerException();
    if (paramCaseFormat == this)
      return paramString;
    switch (1.$SwitchMap$com$google$common$base$CaseFormat[ordinal()])
    {
    case 3:
      switch (1.$SwitchMap$com$google$common$base$CaseFormat[paramCaseFormat.ordinal()])
      {
      case 1:
        return paramString.replace('-', '_');
      case 2:
        return Ascii.toUpperCase(paramString.replace('-', '_'));
      }
      break;
    case 1:
      switch (1.$SwitchMap$com$google$common$base$CaseFormat[paramCaseFormat.ordinal()])
      {
      case 3:
        return paramString.replace('_', '-');
      case 2:
        return Ascii.toUpperCase(paramString);
      }
      break;
    case 2:
      switch (1.$SwitchMap$com$google$common$base$CaseFormat[paramCaseFormat.ordinal()])
      {
      case 3:
        return Ascii.toLowerCase(paramString.replace('_', '-'));
      case 1:
        return Ascii.toLowerCase(paramString);
      }
      break;
    }
    StringBuilder localStringBuilder = null;
    int i = 0;
    int j = -1;
    while ((j = this.wordBoundary.indexIn(paramString, ++j)) != -1)
    {
      if (i == 0)
      {
        localStringBuilder = new StringBuilder(paramString.length() + 4 * this.wordSeparator.length());
        localStringBuilder.append(paramCaseFormat.normalizeFirstWord(paramString.substring(i, j)));
      }
      else
      {
        localStringBuilder.append(paramCaseFormat.normalizeWord(paramString.substring(i, j)));
      }
      localStringBuilder.append(paramCaseFormat.wordSeparator);
      i = j + this.wordSeparator.length();
    }
    if (i == 0)
      return paramCaseFormat.normalizeFirstWord(paramString);
    localStringBuilder.append(paramCaseFormat.normalizeWord(paramString.substring(i)));
    return localStringBuilder.toString();
  }

  private String normalizeFirstWord(String paramString)
  {
    switch (1.$SwitchMap$com$google$common$base$CaseFormat[ordinal()])
    {
    case 4:
      return Ascii.toLowerCase(paramString);
    }
    return normalizeWord(paramString);
  }

  private String normalizeWord(String paramString)
  {
    switch (1.$SwitchMap$com$google$common$base$CaseFormat[ordinal()])
    {
    case 3:
      return Ascii.toLowerCase(paramString);
    case 1:
      return Ascii.toLowerCase(paramString);
    case 4:
      return firstCharOnlyToUpper(paramString);
    case 5:
      return firstCharOnlyToUpper(paramString);
    case 2:
      return Ascii.toUpperCase(paramString);
    }
    throw new RuntimeException("unknown case: " + this);
  }

  private static String firstCharOnlyToUpper(String paramString)
  {
    int i = paramString.length();
    if (i == 0)
      return paramString;
    return i + Ascii.toUpperCase(paramString.charAt(0)) + Ascii.toLowerCase(paramString.substring(1));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.CaseFormat
 * JD-Core Version:    0.6.2
 */