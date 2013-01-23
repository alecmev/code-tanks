package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;

public class BasicTokenIterator
  implements TokenIterator
{
  protected final HeaderIterator headerIt;
  protected String currentHeader;
  protected String currentToken;
  protected int searchPos;

  public BasicTokenIterator(HeaderIterator paramHeaderIterator)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator must not be null.");
    this.headerIt = paramHeaderIterator;
    this.searchPos = findNext(-1);
  }

  public boolean hasNext()
  {
    return this.currentToken != null;
  }

  public String nextToken()
    throws NoSuchElementException, ParseException
  {
    if (this.currentToken == null)
      throw new NoSuchElementException("Iteration already finished.");
    String str = this.currentToken;
    this.searchPos = findNext(this.searchPos);
    return str;
  }

  public final Object next()
    throws NoSuchElementException, ParseException
  {
    return nextToken();
  }

  public final void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Removing tokens is not supported.");
  }

  protected int findNext(int paramInt)
    throws ParseException
  {
    if (paramInt < 0)
    {
      if (!this.headerIt.hasNext())
        return -1;
      this.currentHeader = this.headerIt.nextHeader().getValue();
      paramInt = 0;
    }
    else
    {
      paramInt = findTokenSeparator(paramInt);
    }
    int i = findTokenStart(paramInt);
    if (i < 0)
    {
      this.currentToken = null;
      return -1;
    }
    int j = findTokenEnd(i);
    this.currentToken = createToken(this.currentHeader, i, j);
    return j;
  }

  protected String createToken(String paramString, int paramInt1, int paramInt2)
  {
    return paramString.substring(paramInt1, paramInt2);
  }

  protected int findTokenStart(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Search position must not be negative: " + paramInt);
    int i = 0;
    while ((i == 0) && (this.currentHeader != null))
    {
      int j = this.currentHeader.length();
      while ((i == 0) && (paramInt < j))
      {
        char c = this.currentHeader.charAt(paramInt);
        if ((isTokenSeparator(c)) || (isWhitespace(c)))
          paramInt++;
        else if (isTokenChar(this.currentHeader.charAt(paramInt)))
          i = 1;
        else
          throw new ParseException("Invalid character before token (pos " + paramInt + "): " + this.currentHeader);
      }
      if (i == 0)
        if (this.headerIt.hasNext())
        {
          this.currentHeader = this.headerIt.nextHeader().getValue();
          paramInt = 0;
        }
        else
        {
          this.currentHeader = null;
        }
    }
    return i != 0 ? paramInt : -1;
  }

  protected int findTokenSeparator(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Search position must not be negative: " + paramInt);
    int i = 0;
    int j = this.currentHeader.length();
    while ((i == 0) && (paramInt < j))
    {
      char c = this.currentHeader.charAt(paramInt);
      if (isTokenSeparator(c))
      {
        i = 1;
      }
      else if (isWhitespace(c))
      {
        paramInt++;
      }
      else
      {
        if (isTokenChar(c))
          throw new ParseException("Tokens without separator (pos " + paramInt + "): " + this.currentHeader);
        throw new ParseException("Invalid character after token (pos " + paramInt + "): " + this.currentHeader);
      }
    }
    return paramInt;
  }

  protected int findTokenEnd(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Token start position must not be negative: " + paramInt);
    int i = this.currentHeader.length();
    for (int j = paramInt + 1; (j < i) && (isTokenChar(this.currentHeader.charAt(j))); j++);
    return j;
  }

  protected boolean isTokenSeparator(char paramChar)
  {
    return paramChar == ',';
  }

  protected boolean isWhitespace(char paramChar)
  {
    return (paramChar == '\t') || (Character.isSpaceChar(paramChar));
  }

  protected boolean isTokenChar(char paramChar)
  {
    if (Character.isLetterOrDigit(paramChar))
      return true;
    if (Character.isISOControl(paramChar))
      return false;
    return !isHttpSeparator(paramChar);
  }

  protected boolean isHttpSeparator(char paramChar)
  {
    return " ,;=()<>@:\\\"/[]?{}\t".indexOf(paramChar) >= 0;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.message.BasicTokenIterator
 * JD-Core Version:    0.6.2
 */