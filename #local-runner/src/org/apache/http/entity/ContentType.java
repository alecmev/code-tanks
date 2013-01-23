package org.apache.http.entity;

import java.nio.charset.Charset;
import java.util.Locale;
import org.apache.http.Consts;

public final class ContentType
{
  public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_JSON = create("application/json", Consts.UTF_8);
  public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset)null);
  public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
  public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", Consts.ISO_8859_1);
  public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
  public static final ContentType TEXT_PLAIN = create("text/plain", Consts.ISO_8859_1);
  public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
  public static final ContentType WILDCARD = create("*/*", (Charset)null);
  public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
  public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
  private final String mimeType;
  private final Charset charset;

  ContentType(String paramString, Charset paramCharset)
  {
    this.mimeType = paramString;
    this.charset = paramCharset;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.mimeType);
    if (this.charset != null)
    {
      localStringBuilder.append("; charset=");
      localStringBuilder.append(this.charset.name());
    }
    return localStringBuilder.toString();
  }

  private static boolean valid(String paramString)
  {
    for (int i = 0; i < paramString.length(); i++)
    {
      int j = paramString.charAt(i);
      if ((j == 34) || (j == 44) || (j == 59))
        return false;
    }
    return true;
  }

  public static ContentType create(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      throw new IllegalArgumentException("MIME type may not be null");
    String str = paramString.trim().toLowerCase(Locale.US);
    if (str.length() == 0)
      throw new IllegalArgumentException("MIME type may not be empty");
    if (!valid(str))
      throw new IllegalArgumentException("MIME type may not contain reserved characters");
    return new ContentType(str, paramCharset);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.entity.ContentType
 * JD-Core Version:    0.6.2
 */