package org.apache.http.conn.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.auth.x500.X500Principal;
import org.apache.http.conn.util.InetAddressUtils;

public abstract class AbstractVerifier
  implements X509HostnameVerifier
{
  private static final String[] BAD_COUNTRY_2LDS = { "ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org" };

  public final void verify(String paramString, SSLSocket paramSSLSocket)
    throws IOException
  {
    if (paramString == null)
      throw new NullPointerException("host to verify is null");
    SSLSession localSSLSession = paramSSLSocket.getSession();
    if (localSSLSession == null)
    {
      localObject = paramSSLSocket.getInputStream();
      ((InputStream)localObject).available();
      localSSLSession = paramSSLSocket.getSession();
      if (localSSLSession == null)
      {
        paramSSLSocket.startHandshake();
        localSSLSession = paramSSLSocket.getSession();
      }
    }
    Object localObject = localSSLSession.getPeerCertificates();
    X509Certificate localX509Certificate = (X509Certificate)localObject[0];
    verify(paramString, localX509Certificate);
  }

  public final boolean verify(String paramString, SSLSession paramSSLSession)
  {
    try
    {
      Certificate[] arrayOfCertificate = paramSSLSession.getPeerCertificates();
      X509Certificate localX509Certificate = (X509Certificate)arrayOfCertificate[0];
      verify(paramString, localX509Certificate);
      return true;
    }
    catch (SSLException localSSLException)
    {
    }
    return false;
  }

  public final void verify(String paramString, X509Certificate paramX509Certificate)
    throws SSLException
  {
    String[] arrayOfString1 = getCNs(paramX509Certificate);
    String[] arrayOfString2 = getSubjectAlts(paramX509Certificate, paramString);
    verify(paramString, arrayOfString1, arrayOfString2);
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
    throws SSLException
  {
    LinkedList localLinkedList = new LinkedList();
    if ((paramArrayOfString1 != null) && (paramArrayOfString1.length > 0) && (paramArrayOfString1[0] != null))
      localLinkedList.add(paramArrayOfString1[0]);
    if (paramArrayOfString2 != null)
      for (localObject2 : paramArrayOfString2)
        if (localObject2 != null)
          localLinkedList.add(localObject2);
    if (localLinkedList.isEmpty())
    {
      ??? = "Certificate for <" + paramString + "> doesn't contain CN or DNS subjectAlt";
      throw new SSLException((String)???);
    }
    ??? = new StringBuilder();
    String str1 = paramString.trim().toLowerCase(Locale.ENGLISH);
    ??? = 0;
    Object localObject2 = localLinkedList.iterator();
    boolean bool;
    while (((Iterator)localObject2).hasNext())
    {
      String str2 = (String)((Iterator)localObject2).next();
      str2 = str2.toLowerCase(Locale.ENGLISH);
      ((StringBuilder)???).append(" <");
      ((StringBuilder)???).append(str2);
      ((StringBuilder)???).append('>');
      if (((Iterator)localObject2).hasNext())
        ((StringBuilder)???).append(" OR");
      String[] arrayOfString = str2.split("\\.");
      int k = (arrayOfString.length >= 3) && (arrayOfString[0].endsWith("*")) && (acceptableCountryWildcard(str2)) && (!isIPAddress(paramString)) ? 1 : 0;
      if (k != 0)
      {
        if (arrayOfString[0].length() > 1)
        {
          String str3 = arrayOfString[0].substring(0, arrayOfString.length - 2);
          String str4 = str2.substring(arrayOfString[0].length());
          String str5 = str1.substring(str3.length());
          ??? = (str1.startsWith(str3)) && (str5.endsWith(str4)) ? 1 : 0;
        }
        else
        {
          bool = str1.endsWith(str2.substring(1));
        }
        if ((bool) && (paramBoolean))
          bool = countDots(str1) == countDots(str2);
      }
      else
      {
        bool = str1.equals(str2);
      }
      if (bool)
        break;
    }
    if (!bool)
      throw new SSLException("hostname in certificate didn't match: <" + paramString + "> !=" + ???);
  }

  public static boolean acceptableCountryWildcard(String paramString)
  {
    String[] arrayOfString = paramString.split("\\.");
    if ((arrayOfString.length != 3) || (arrayOfString[2].length() != 2))
      return true;
    return Arrays.binarySearch(BAD_COUNTRY_2LDS, arrayOfString[1]) < 0;
  }

  public static String[] getCNs(X509Certificate paramX509Certificate)
  {
    LinkedList localLinkedList = new LinkedList();
    String str = paramX509Certificate.getSubjectX500Principal().toString();
    StringTokenizer localStringTokenizer = new StringTokenizer(str, ",");
    Object localObject;
    while (localStringTokenizer.hasMoreTokens())
    {
      localObject = localStringTokenizer.nextToken();
      int i = ((String)localObject).indexOf("CN=");
      if (i >= 0)
        localLinkedList.add(((String)localObject).substring(i + 3));
    }
    if (!localLinkedList.isEmpty())
    {
      localObject = new String[localLinkedList.size()];
      localLinkedList.toArray((Object[])localObject);
      return localObject;
    }
    return null;
  }

  private static String[] getSubjectAlts(X509Certificate paramX509Certificate, String paramString)
  {
    int i;
    if (isIPAddress(paramString))
      i = 7;
    else
      i = 2;
    LinkedList localLinkedList = new LinkedList();
    Collection localCollection = null;
    try
    {
      localCollection = paramX509Certificate.getSubjectAlternativeNames();
    }
    catch (CertificateParsingException localCertificateParsingException)
    {
      Logger.getLogger(AbstractVerifier.class.getName()).log(Level.FINE, "Error parsing certificate.", localCertificateParsingException);
    }
    Object localObject;
    if (localCollection != null)
    {
      localObject = localCollection.iterator();
      while (((Iterator)localObject).hasNext())
      {
        List localList1 = (List)((Iterator)localObject).next();
        List localList2 = localList1;
        int j = ((Integer)localList2.get(0)).intValue();
        if (j == i)
        {
          String str = (String)localList2.get(1);
          localLinkedList.add(str);
        }
      }
    }
    if (!localLinkedList.isEmpty())
    {
      localObject = new String[localLinkedList.size()];
      localLinkedList.toArray((Object[])localObject);
      return localObject;
    }
    return null;
  }

  public static int countDots(String paramString)
  {
    int i = 0;
    for (int j = 0; j < paramString.length(); j++)
      if (paramString.charAt(j) == '.')
        i++;
    return i;
  }

  private static boolean isIPAddress(String paramString)
  {
    return (paramString != null) && ((InetAddressUtils.isIPv4Address(paramString)) || (InetAddressUtils.isIPv6Address(paramString)));
  }

  static
  {
    Arrays.sort(BAD_COUNTRY_2LDS);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.conn.ssl.AbstractVerifier
 * JD-Core Version:    0.6.2
 */