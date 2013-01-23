package org.apache.http.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

public class DigestScheme extends RFC2617Scheme
{
  private static final char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  private boolean complete = false;
  private String lastNonce;
  private long nounceCount;
  private String cnonce;
  private String a1;
  private String a2;

  public DigestScheme(ChallengeState paramChallengeState)
  {
    super(paramChallengeState);
  }

  public DigestScheme()
  {
    this(null);
  }

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    super.processChallenge(paramHeader);
    this.complete = true;
  }

  public boolean isComplete()
  {
    String str = getParameter("stale");
    if ("true".equalsIgnoreCase(str))
      return false;
    return this.complete;
  }

  public String getSchemeName()
  {
    return "digest";
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  @Deprecated
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest, new BasicHttpContext());
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (getParameter("realm") == null)
      throw new AuthenticationException("missing realm in challenge");
    if (getParameter("nonce") == null)
      throw new AuthenticationException("missing nonce in challenge");
    getParameters().put("methodname", paramHttpRequest.getRequestLine().getMethod());
    getParameters().put("uri", paramHttpRequest.getRequestLine().getUri());
    String str = getParameter("charset");
    if (str == null)
    {
      str = AuthParams.getCredentialCharset(paramHttpRequest.getParams());
      getParameters().put("charset", str);
    }
    return createDigestHeader(paramCredentials, paramHttpRequest);
  }

  private static MessageDigest createMessageDigest(String paramString)
    throws UnsupportedDigestAlgorithmException
  {
    try
    {
      return MessageDigest.getInstance(paramString);
    }
    catch (Exception localException)
    {
    }
    throw new UnsupportedDigestAlgorithmException("Unsupported algorithm in HTTP Digest authentication: " + paramString);
  }

  private Header createDigestHeader(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    String str1 = getParameter("uri");
    String str2 = getParameter("realm");
    String str3 = getParameter("nonce");
    String str4 = getParameter("opaque");
    String str5 = getParameter("methodname");
    String str6 = getParameter("algorithm");
    HashSet localHashSet = new HashSet(8);
    int i = -1;
    String str7 = getParameter("qop");
    if (str7 != null)
    {
      localObject1 = new StringTokenizer(str7, ",");
      while (((StringTokenizer)localObject1).hasMoreTokens())
      {
        str8 = ((StringTokenizer)localObject1).nextToken().trim();
        localHashSet.add(str8.toLowerCase(Locale.US));
      }
      if (((paramHttpRequest instanceof HttpEntityEnclosingRequest)) && (localHashSet.contains("auth-int")))
        i = 1;
      else if (localHashSet.contains("auth"))
        i = 2;
    }
    else
    {
      i = 0;
    }
    if (i == -1)
      throw new AuthenticationException("None of the qop methods is supported: " + str7);
    if (str6 == null)
      str6 = "MD5";
    Object localObject1 = getParameter("charset");
    if (localObject1 == null)
      localObject1 = "ISO-8859-1";
    String str8 = str6;
    if (str8.equalsIgnoreCase("MD5-sess"))
      str8 = "MD5";
    MessageDigest localMessageDigest;
    try
    {
      localMessageDigest = createMessageDigest(str8);
    }
    catch (UnsupportedDigestAlgorithmException localUnsupportedDigestAlgorithmException)
    {
      throw new AuthenticationException("Unsuppported digest algorithm: " + str8);
    }
    String str9 = paramCredentials.getUserPrincipal().getName();
    String str10 = paramCredentials.getPassword();
    if (str3.equals(this.lastNonce))
    {
      this.nounceCount += 1L;
    }
    else
    {
      this.nounceCount = 1L;
      this.cnonce = null;
      this.lastNonce = str3;
    }
    StringBuilder localStringBuilder = new StringBuilder(256);
    Formatter localFormatter = new Formatter(localStringBuilder, Locale.US);
    localFormatter.format("%08x", new Object[] { Long.valueOf(this.nounceCount) });
    String str11 = localStringBuilder.toString();
    if (this.cnonce == null)
      this.cnonce = createCnonce();
    this.a1 = null;
    this.a2 = null;
    if (str6.equalsIgnoreCase("MD5-sess"))
    {
      localStringBuilder.setLength(0);
      localStringBuilder.append(str9).append(':').append(str2).append(':').append(str10);
      str12 = encode(localMessageDigest.digest(EncodingUtils.getBytes(localStringBuilder.toString(), (String)localObject1)));
      localStringBuilder.setLength(0);
      localStringBuilder.append(str12).append(':').append(str3).append(':').append(this.cnonce);
      this.a1 = localStringBuilder.toString();
    }
    else
    {
      localStringBuilder.setLength(0);
      localStringBuilder.append(str9).append(':').append(str2).append(':').append(str10);
      this.a1 = localStringBuilder.toString();
    }
    String str12 = encode(localMessageDigest.digest(EncodingUtils.getBytes(this.a1, (String)localObject1)));
    Object localObject3;
    if (i == 2)
    {
      this.a2 = (str5 + ':' + str1);
    }
    else if (i == 1)
    {
      localObject2 = null;
      if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
        localObject2 = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if ((localObject2 != null) && (!((HttpEntity)localObject2).isRepeatable()))
      {
        if (localHashSet.contains("auth"))
        {
          i = 2;
          this.a2 = (str5 + ':' + str1);
        }
        else
        {
          throw new AuthenticationException("Qop auth-int cannot be used with a non-repeatable entity");
        }
      }
      else
      {
        localObject3 = new HttpEntityDigester(localMessageDigest);
        try
        {
          if (localObject2 != null)
            ((HttpEntity)localObject2).writeTo((OutputStream)localObject3);
          ((HttpEntityDigester)localObject3).close();
        }
        catch (IOException localIOException)
        {
          throw new AuthenticationException("I/O error reading entity content", localIOException);
        }
        this.a2 = (str5 + ':' + str1 + ':' + encode(((HttpEntityDigester)localObject3).getDigest()));
      }
    }
    else
    {
      this.a2 = (str5 + ':' + str1);
    }
    Object localObject2 = encode(localMessageDigest.digest(EncodingUtils.getBytes(this.a2, (String)localObject1)));
    if (i == 0)
    {
      localStringBuilder.setLength(0);
      localStringBuilder.append(str12).append(':').append(str3).append(':').append((String)localObject2);
      localObject3 = localStringBuilder.toString();
    }
    else
    {
      localStringBuilder.setLength(0);
      localStringBuilder.append(str12).append(':').append(str3).append(':').append(str11).append(':').append(this.cnonce).append(':').append(i == 1 ? "auth-int" : "auth").append(':').append((String)localObject2);
      localObject3 = localStringBuilder.toString();
    }
    String str13 = encode(localMessageDigest.digest(EncodingUtils.getAsciiBytes((String)localObject3)));
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(128);
    if (isProxy())
      localCharArrayBuffer.append("Proxy-Authorization");
    else
      localCharArrayBuffer.append("Authorization");
    localCharArrayBuffer.append(": Digest ");
    ArrayList localArrayList = new ArrayList(20);
    localArrayList.add(new BasicNameValuePair("username", str9));
    localArrayList.add(new BasicNameValuePair("realm", str2));
    localArrayList.add(new BasicNameValuePair("nonce", str3));
    localArrayList.add(new BasicNameValuePair("uri", str1));
    localArrayList.add(new BasicNameValuePair("response", str13));
    if (i != 0)
    {
      localArrayList.add(new BasicNameValuePair("qop", i == 1 ? "auth-int" : "auth"));
      localArrayList.add(new BasicNameValuePair("nc", str11));
      localArrayList.add(new BasicNameValuePair("cnonce", this.cnonce));
    }
    if (str6 != null)
      localArrayList.add(new BasicNameValuePair("algorithm", str6));
    if (str4 != null)
      localArrayList.add(new BasicNameValuePair("opaque", str4));
    for (int j = 0; j < localArrayList.size(); j++)
    {
      BasicNameValuePair localBasicNameValuePair = (BasicNameValuePair)localArrayList.get(j);
      if (j > 0)
        localCharArrayBuffer.append(", ");
      int k = ("nc".equals(localBasicNameValuePair.getName())) || ("qop".equals(localBasicNameValuePair.getName())) ? 1 : 0;
      BasicHeaderValueFormatter.DEFAULT.formatNameValuePair(localCharArrayBuffer, localBasicNameValuePair, k == 0);
    }
    return new BufferedHeader(localCharArrayBuffer);
  }

  static String encode(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    char[] arrayOfChar = new char[i * 2];
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte[j] & 0xF;
      int m = (paramArrayOfByte[j] & 0xF0) >> 4;
      arrayOfChar[(j * 2)] = HEXADECIMAL[m];
      arrayOfChar[(j * 2 + 1)] = HEXADECIMAL[k];
    }
    return new String(arrayOfChar);
  }

  public static String createCnonce()
  {
    SecureRandom localSecureRandom = new SecureRandom();
    byte[] arrayOfByte = new byte[8];
    localSecureRandom.nextBytes(arrayOfByte);
    return encode(arrayOfByte);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.impl.auth.DigestScheme
 * JD-Core Version:    0.6.2
 */