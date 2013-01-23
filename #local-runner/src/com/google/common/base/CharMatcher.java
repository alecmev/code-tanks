package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Beta
@GwtCompatible
public abstract class CharMatcher
  implements Predicate
{
  public static final CharMatcher BREAKING_WHITESPACE = anyOf("\t\n\013\f\r     　").or(inRange(' ', ' ')).or(inRange(' ', ' ')).withToString("CharMatcher.BREAKING_WHITESPACE").precomputed();
  public static final CharMatcher ASCII = inRange('\000', '').withToString("CharMatcher.ASCII");
  public static final CharMatcher DIGIT = localCharMatcher.withToString("CharMatcher.DIGIT").precomputed();
  public static final CharMatcher JAVA_DIGIT = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return Character.isDigit(paramAnonymousChar);
    }

    public String toString()
    {
      return "CharMatcher.JAVA_DIGIT";
    }
  };
  public static final CharMatcher JAVA_LETTER = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return Character.isLetter(paramAnonymousChar);
    }

    public String toString()
    {
      return "CharMatcher.JAVA_LETTER";
    }
  };
  public static final CharMatcher JAVA_LETTER_OR_DIGIT = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return Character.isLetterOrDigit(paramAnonymousChar);
    }

    public String toString()
    {
      return "CharMatcher.JAVA_LETTER_OR_DIGIT";
    }
  };
  public static final CharMatcher JAVA_UPPER_CASE = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return Character.isUpperCase(paramAnonymousChar);
    }

    public String toString()
    {
      return "CharMatcher.JAVA_UPPER_CASE";
    }
  };
  public static final CharMatcher JAVA_LOWER_CASE = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return Character.isLowerCase(paramAnonymousChar);
    }

    public String toString()
    {
      return "CharMatcher.JAVA_LOWER_CASE";
    }
  };
  public static final CharMatcher JAVA_ISO_CONTROL = inRange('\000', '\037').or(inRange('', '')).withToString("CharMatcher.JAVA_ISO_CONTROL");
  public static final CharMatcher INVISIBLE = inRange('\000', ' ').or(inRange('', ' ')).or(is('­')).or(inRange('؀', '؃')).or(anyOf("۝܏ ឴឵᠎")).or(inRange(' ', '‏')).or(inRange(' ', ' ')).or(inRange(' ', '⁤')).or(inRange('⁪', '⁯')).or(is('　')).or(inRange(55296, 63743)).or(anyOf("﻿￹￺￻")).withToString("CharMatcher.INVISIBLE").precomputed();
  public static final CharMatcher SINGLE_WIDTH = inRange('\000', 'ӹ').or(is('־')).or(inRange('א', 'ת')).or(is('׳')).or(is('״')).or(inRange('؀', 'ۿ')).or(inRange('ݐ', 'ݿ')).or(inRange('฀', '๿')).or(inRange('Ḁ', '₯')).or(inRange('℀', '℺')).or(inRange(64336, 65023)).or(inRange(65136, 65279)).or(inRange(65377, 65500)).withToString("CharMatcher.SINGLE_WIDTH").precomputed();
  public static final CharMatcher ANY = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return true;
    }

    public int indexIn(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.length() == 0 ? -1 : 0;
    }

    public int indexIn(CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
    {
      int i = paramAnonymousCharSequence.length();
      Preconditions.checkPositionIndex(paramAnonymousInt, i);
      return paramAnonymousInt == i ? -1 : paramAnonymousInt;
    }

    public int lastIndexIn(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.length() - 1;
    }

    public boolean matchesAllOf(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return true;
    }

    public boolean matchesNoneOf(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.length() == 0;
    }

    public String removeFrom(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return "";
    }

    public String replaceFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
    {
      char[] arrayOfChar = new char[paramAnonymousCharSequence.length()];
      Arrays.fill(arrayOfChar, paramAnonymousChar);
      return new String(arrayOfChar);
    }

    public String replaceFrom(CharSequence paramAnonymousCharSequence1, CharSequence paramAnonymousCharSequence2)
    {
      StringBuilder localStringBuilder = new StringBuilder(paramAnonymousCharSequence1.length() * paramAnonymousCharSequence2.length());
      for (int i = 0; i < paramAnonymousCharSequence1.length(); i++)
        localStringBuilder.append(paramAnonymousCharSequence2);
      return localStringBuilder.toString();
    }

    public String collapseFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
    {
      return paramAnonymousCharSequence.length() == 0 ? "" : String.valueOf(paramAnonymousChar);
    }

    public String trimFrom(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return "";
    }

    public int countIn(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.length();
    }

    public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
    {
      return (CharMatcher)Preconditions.checkNotNull(paramAnonymousCharMatcher);
    }

    public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
    {
      Preconditions.checkNotNull(paramAnonymousCharMatcher);
      return this;
    }

    public CharMatcher negate()
    {
      return NONE;
    }

    public CharMatcher precomputed()
    {
      return this;
    }

    public String toString()
    {
      return "CharMatcher.ANY";
    }
  };
  public static final CharMatcher NONE = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      return false;
    }

    public int indexIn(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return -1;
    }

    public int indexIn(CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
    {
      int i = paramAnonymousCharSequence.length();
      Preconditions.checkPositionIndex(paramAnonymousInt, i);
      return -1;
    }

    public int lastIndexIn(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return -1;
    }

    public boolean matchesAllOf(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.length() == 0;
    }

    public boolean matchesNoneOf(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return true;
    }

    public String removeFrom(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.toString();
    }

    public String replaceFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
    {
      return paramAnonymousCharSequence.toString();
    }

    public String replaceFrom(CharSequence paramAnonymousCharSequence1, CharSequence paramAnonymousCharSequence2)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence2);
      return paramAnonymousCharSequence1.toString();
    }

    public String collapseFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
    {
      return paramAnonymousCharSequence.toString();
    }

    public String trimFrom(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.toString();
    }

    public int countIn(CharSequence paramAnonymousCharSequence)
    {
      Preconditions.checkNotNull(paramAnonymousCharSequence);
      return 0;
    }

    public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
    {
      Preconditions.checkNotNull(paramAnonymousCharMatcher);
      return this;
    }

    public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
    {
      return (CharMatcher)Preconditions.checkNotNull(paramAnonymousCharMatcher);
    }

    public CharMatcher negate()
    {
      return ANY;
    }

    void setBits(CharMatcher.LookupTable paramAnonymousLookupTable)
    {
    }

    public CharMatcher precomputed()
    {
      return this;
    }

    public String toString()
    {
      return "CharMatcher.NONE";
    }
  };
  public static final CharMatcher WHITESPACE = new CharMatcher()
  {
    private final char[] table = { '\001', '\000', ' ', '\000', '\000', '\000', '\000', '\000', '\000', '\t', '\n', '\013', '\f', '\r', '\000', '\000', ' ', ' ', '\000', '\000', '\000', '\000', '\000', ' ', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', ' ', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '　', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '\000', '', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '\000', '\000', '\000', '\000', '\000', ' ', ' ', '\000', '\000', '᠎', '\000', '\000', '\000' };

    public boolean matches(char paramAnonymousChar)
    {
      return this.table[(paramAnonymousChar % 'O')] == paramAnonymousChar;
    }

    public CharMatcher precomputed()
    {
      return this;
    }

    public String toString()
    {
      return "CharMatcher.WHITESPACE";
    }
  };

  public static CharMatcher is(char paramChar)
  {
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return paramAnonymousChar == this.val$match;
      }

      public String replaceFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
      {
        return paramAnonymousCharSequence.toString().replace(this.val$match, paramAnonymousChar);
      }

      public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
      {
        return paramAnonymousCharMatcher.matches(this.val$match) ? this : NONE;
      }

      public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
      {
        return paramAnonymousCharMatcher.matches(this.val$match) ? paramAnonymousCharMatcher : super.or(paramAnonymousCharMatcher);
      }

      public CharMatcher negate()
      {
        return isNot(this.val$match);
      }

      void setBits(CharMatcher.LookupTable paramAnonymousLookupTable)
      {
        paramAnonymousLookupTable.set(this.val$match);
      }

      public CharMatcher precomputed()
      {
        return this;
      }

      public String toString()
      {
        return "CharMatcher.is(" + Integer.toHexString(this.val$match) + ")";
      }
    };
  }

  public static CharMatcher isNot(char paramChar)
  {
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return paramAnonymousChar != this.val$match;
      }

      public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
      {
        return paramAnonymousCharMatcher.matches(this.val$match) ? super.and(paramAnonymousCharMatcher) : paramAnonymousCharMatcher;
      }

      public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
      {
        return paramAnonymousCharMatcher.matches(this.val$match) ? ANY : this;
      }

      public CharMatcher negate()
      {
        return is(this.val$match);
      }

      public String toString()
      {
        return "CharMatcher.isNot(" + Integer.toHexString(this.val$match) + ")";
      }
    };
  }

  public static CharMatcher anyOf(CharSequence paramCharSequence)
  {
    switch (paramCharSequence.length())
    {
    case 0:
      return NONE;
    case 1:
      return is(paramCharSequence.charAt(0));
    case 2:
      char c1 = paramCharSequence.charAt(0);
      final char c2 = paramCharSequence.charAt(1);
      return new CharMatcher()
      {
        public boolean matches(char paramAnonymousChar)
        {
          return (paramAnonymousChar == this.val$match1) || (paramAnonymousChar == c2);
        }

        void setBits(CharMatcher.LookupTable paramAnonymousLookupTable)
        {
          paramAnonymousLookupTable.set(this.val$match1);
          paramAnonymousLookupTable.set(c2);
        }

        public CharMatcher precomputed()
        {
          return this;
        }
      };
    }
    char[] arrayOfChar = paramCharSequence.toString().toCharArray();
    Arrays.sort(arrayOfChar);
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Arrays.binarySearch(this.val$chars, paramAnonymousChar) >= 0;
      }

      void setBits(CharMatcher.LookupTable paramAnonymousLookupTable)
      {
        for (char c : this.val$chars)
          paramAnonymousLookupTable.set(c);
      }

      public String toString()
      {
        return "CharMatcher.anyOf(\"" + this.val$chars + "\")";
      }
    };
  }

  public static CharMatcher noneOf(CharSequence paramCharSequence)
  {
    return anyOf(paramCharSequence).negate();
  }

  public static CharMatcher inRange(char paramChar1, final char paramChar2)
  {
    Preconditions.checkArgument(paramChar2 >= paramChar1);
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return (this.val$startInclusive <= paramAnonymousChar) && (paramAnonymousChar <= paramChar2);
      }

      void setBits(CharMatcher.LookupTable paramAnonymousLookupTable)
      {
        char c = this.val$startInclusive;
        while (true)
        {
          paramAnonymousLookupTable.set(c);
          c = (char)(c + '\001');
          if (c == paramChar2)
            break;
        }
      }

      public CharMatcher precomputed()
      {
        return this;
      }

      public String toString()
      {
        return "CharMatcher.inRange(" + Integer.toHexString(this.val$startInclusive) + ", " + Integer.toHexString(paramChar2) + ")";
      }
    };
  }

  public static CharMatcher forPredicate(Predicate paramPredicate)
  {
    Preconditions.checkNotNull(paramPredicate);
    if ((paramPredicate instanceof CharMatcher))
      return (CharMatcher)paramPredicate;
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return this.val$predicate.apply(Character.valueOf(paramAnonymousChar));
      }

      public boolean apply(Character paramAnonymousCharacter)
      {
        return this.val$predicate.apply(Preconditions.checkNotNull(paramAnonymousCharacter));
      }

      public String toString()
      {
        return "CharMatcher.forPredicate(" + this.val$predicate + ')';
      }
    };
  }

  public abstract boolean matches(char paramChar);

  public CharMatcher negate()
  {
    final CharMatcher localCharMatcher = this;
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return !localCharMatcher.matches(paramAnonymousChar);
      }

      public boolean matchesAllOf(CharSequence paramAnonymousCharSequence)
      {
        return localCharMatcher.matchesNoneOf(paramAnonymousCharSequence);
      }

      public boolean matchesNoneOf(CharSequence paramAnonymousCharSequence)
      {
        return localCharMatcher.matchesAllOf(paramAnonymousCharSequence);
      }

      public int countIn(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.length() - localCharMatcher.countIn(paramAnonymousCharSequence);
      }

      public CharMatcher negate()
      {
        return localCharMatcher;
      }

      public String toString()
      {
        return localCharMatcher + ".negate()";
      }
    };
  }

  public CharMatcher and(CharMatcher paramCharMatcher)
  {
    return new And(Arrays.asList(new CharMatcher[] { this, (CharMatcher)Preconditions.checkNotNull(paramCharMatcher) }));
  }

  public CharMatcher or(CharMatcher paramCharMatcher)
  {
    return new Or(Arrays.asList(new CharMatcher[] { this, (CharMatcher)Preconditions.checkNotNull(paramCharMatcher) }));
  }

  public CharMatcher precomputed()
  {
    return Platform.precomputeCharMatcher(this);
  }

  CharMatcher precomputedInternal()
  {
    final LookupTable localLookupTable = new LookupTable(null);
    setBits(localLookupTable);
    final CharMatcher localCharMatcher = this;
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return localLookupTable.get(paramAnonymousChar);
      }

      public CharMatcher precomputed()
      {
        return this;
      }

      public String toString()
      {
        return localCharMatcher.toString();
      }
    };
  }

  CharMatcher withToString(final String paramString)
  {
    final CharMatcher localCharMatcher = this;
    return new CharMatcher()
    {
      public boolean matches(char paramAnonymousChar)
      {
        return localCharMatcher.matches(paramAnonymousChar);
      }

      void setBits(CharMatcher.LookupTable paramAnonymousLookupTable)
      {
        localCharMatcher.setBits(paramAnonymousLookupTable);
      }

      public String toString()
      {
        return paramString;
      }
    };
  }

  void setBits(LookupTable paramLookupTable)
  {
    int i = 0;
    while (true)
    {
      if (matches(i))
        paramLookupTable.set(i);
      i = (char)(i + 1);
      if (i == 65535)
        break;
    }
  }

  public boolean matchesAnyOf(CharSequence paramCharSequence)
  {
    return !matchesNoneOf(paramCharSequence);
  }

  public boolean matchesAllOf(CharSequence paramCharSequence)
  {
    for (int i = paramCharSequence.length() - 1; i >= 0; i--)
      if (!matches(paramCharSequence.charAt(i)))
        return false;
    return true;
  }

  public boolean matchesNoneOf(CharSequence paramCharSequence)
  {
    return indexIn(paramCharSequence) == -1;
  }

  public int indexIn(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    for (int j = 0; j < i; j++)
      if (matches(paramCharSequence.charAt(j)))
        return j;
    return -1;
  }

  public int indexIn(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    Preconditions.checkPositionIndex(paramInt, i);
    for (int j = paramInt; j < i; j++)
      if (matches(paramCharSequence.charAt(j)))
        return j;
    return -1;
  }

  public int lastIndexIn(CharSequence paramCharSequence)
  {
    for (int i = paramCharSequence.length() - 1; i >= 0; i--)
      if (matches(paramCharSequence.charAt(i)))
        return i;
    return -1;
  }

  public int countIn(CharSequence paramCharSequence)
  {
    int i = 0;
    for (int j = 0; j < paramCharSequence.length(); j++)
      if (matches(paramCharSequence.charAt(j)))
        i++;
    return i;
  }

  public String removeFrom(CharSequence paramCharSequence)
  {
    String str = paramCharSequence.toString();
    int i = indexIn(str);
    if (i == -1)
      return str;
    char[] arrayOfChar = str.toCharArray();
    for (int j = 1; ; j++)
    {
      i++;
      while (true)
      {
        if (i == arrayOfChar.length)
          break label77;
        if (matches(arrayOfChar[i]))
          break;
        arrayOfChar[(i - j)] = arrayOfChar[i];
        i++;
      }
    }
    label77: return new String(arrayOfChar, 0, i - j);
  }

  public String retainFrom(CharSequence paramCharSequence)
  {
    return negate().removeFrom(paramCharSequence);
  }

  public String replaceFrom(CharSequence paramCharSequence, char paramChar)
  {
    String str = paramCharSequence.toString();
    int i = indexIn(str);
    if (i == -1)
      return str;
    char[] arrayOfChar = str.toCharArray();
    arrayOfChar[i] = paramChar;
    for (int j = i + 1; j < arrayOfChar.length; j++)
      if (matches(arrayOfChar[j]))
        arrayOfChar[j] = paramChar;
    return new String(arrayOfChar);
  }

  public String replaceFrom(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    int i = paramCharSequence2.length();
    if (i == 0)
      return removeFrom(paramCharSequence1);
    if (i == 1)
      return replaceFrom(paramCharSequence1, paramCharSequence2.charAt(0));
    String str = paramCharSequence1.toString();
    int j = indexIn(str);
    if (j == -1)
      return str;
    int k = str.length();
    StringBuilder localStringBuilder = new StringBuilder(k * 3 / 2 + 16);
    int m = 0;
    do
    {
      localStringBuilder.append(str, m, j);
      localStringBuilder.append(paramCharSequence2);
      m = j + 1;
      j = indexIn(str, m);
    }
    while (j != -1);
    localStringBuilder.append(str, m, k);
    return localStringBuilder.toString();
  }

  public String trimFrom(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    for (int j = 0; (j < i) && (matches(paramCharSequence.charAt(j))); j++);
    for (int k = i - 1; (k > j) && (matches(paramCharSequence.charAt(k))); k--);
    return paramCharSequence.subSequence(j, k + 1).toString();
  }

  public String trimLeadingFrom(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    for (int j = 0; (j < i) && (matches(paramCharSequence.charAt(j))); j++);
    return paramCharSequence.subSequence(j, i).toString();
  }

  public String trimTrailingFrom(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    for (int j = i - 1; (j >= 0) && (matches(paramCharSequence.charAt(j))); j--);
    return paramCharSequence.subSequence(0, j + 1).toString();
  }

  public String collapseFrom(CharSequence paramCharSequence, char paramChar)
  {
    int i = indexIn(paramCharSequence);
    if (i == -1)
      return paramCharSequence.toString();
    StringBuilder localStringBuilder = new StringBuilder(paramCharSequence.length()).append(paramCharSequence.subSequence(0, i)).append(paramChar);
    int j = 1;
    for (int k = i + 1; k < paramCharSequence.length(); k++)
    {
      char c = paramCharSequence.charAt(k);
      if (matches(c))
      {
        if (j == 0)
        {
          localStringBuilder.append(paramChar);
          j = 1;
        }
      }
      else
      {
        localStringBuilder.append(c);
        j = 0;
      }
    }
    return localStringBuilder.toString();
  }

  public String trimAndCollapseFrom(CharSequence paramCharSequence, char paramChar)
  {
    int i = negate().indexIn(paramCharSequence);
    if (i == -1)
      return "";
    StringBuilder localStringBuilder = new StringBuilder(paramCharSequence.length());
    int j = 0;
    for (int k = i; k < paramCharSequence.length(); k++)
    {
      char c = paramCharSequence.charAt(k);
      if (matches(c))
      {
        j = 1;
      }
      else
      {
        if (j != 0)
        {
          localStringBuilder.append(paramChar);
          j = 0;
        }
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }

  public boolean apply(Character paramCharacter)
  {
    return matches(paramCharacter.charValue());
  }

  public String toString()
  {
    return super.toString();
  }

  static
  {
    CharMatcher localCharMatcher = inRange('0', '9');
    String str = "٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";
    for (char c : str.toCharArray())
      localCharMatcher = localCharMatcher.or(inRange(c, (char)(c + '\t')));
  }

  private static final class LookupTable
  {
    int[] data = new int[2048];

    void set(char paramChar)
    {
      this.data[(paramChar >> '\005')] |= '\001' << paramChar;
    }

    boolean get(char paramChar)
    {
      return (this.data[(paramChar >> '\005')] & '\001' << paramChar) != 0;
    }
  }

  private static class Or extends CharMatcher
  {
    List components;

    Or(List paramList)
    {
      this.components = paramList;
    }

    public boolean matches(char paramChar)
    {
      Iterator localIterator = this.components.iterator();
      while (localIterator.hasNext())
      {
        CharMatcher localCharMatcher = (CharMatcher)localIterator.next();
        if (localCharMatcher.matches(paramChar))
          return true;
      }
      return false;
    }

    public CharMatcher or(CharMatcher paramCharMatcher)
    {
      ArrayList localArrayList = new ArrayList(this.components);
      localArrayList.add(Preconditions.checkNotNull(paramCharMatcher));
      return new Or(localArrayList);
    }

    void setBits(CharMatcher.LookupTable paramLookupTable)
    {
      Iterator localIterator = this.components.iterator();
      while (localIterator.hasNext())
      {
        CharMatcher localCharMatcher = (CharMatcher)localIterator.next();
        localCharMatcher.setBits(paramLookupTable);
      }
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("CharMatcher.or(");
      Joiner.on(", ").appendTo(localStringBuilder, this.components);
      return ')';
    }
  }

  private static class And extends CharMatcher
  {
    List components;

    And(List paramList)
    {
      this.components = paramList;
    }

    public boolean matches(char paramChar)
    {
      Iterator localIterator = this.components.iterator();
      while (localIterator.hasNext())
      {
        CharMatcher localCharMatcher = (CharMatcher)localIterator.next();
        if (!localCharMatcher.matches(paramChar))
          return false;
      }
      return true;
    }

    public CharMatcher and(CharMatcher paramCharMatcher)
    {
      ArrayList localArrayList = new ArrayList(this.components);
      localArrayList.add(Preconditions.checkNotNull(paramCharMatcher));
      return new And(localArrayList);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("CharMatcher.and(");
      Joiner.on(", ").appendTo(localStringBuilder, this.components);
      return ')';
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.CharMatcher
 * JD-Core Version:    0.6.2
 */