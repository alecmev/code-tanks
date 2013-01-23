package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class VersionInfo
{
  private final String infoPackage;
  private final String infoModule;
  private final String infoRelease;
  private final String infoTimestamp;
  private final String infoClassloader;

  protected VersionInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    this.infoPackage = paramString1;
    this.infoModule = (paramString2 != null ? paramString2 : "UNAVAILABLE");
    this.infoRelease = (paramString3 != null ? paramString3 : "UNAVAILABLE");
    this.infoTimestamp = (paramString4 != null ? paramString4 : "UNAVAILABLE");
    this.infoClassloader = (paramString5 != null ? paramString5 : "UNAVAILABLE");
  }

  public final String getRelease()
  {
    return this.infoRelease;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(20 + this.infoPackage.length() + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
    localStringBuilder.append("VersionInfo(").append(this.infoPackage).append(':').append(this.infoModule);
    if (!"UNAVAILABLE".equals(this.infoRelease))
      localStringBuilder.append(':').append(this.infoRelease);
    if (!"UNAVAILABLE".equals(this.infoTimestamp))
      localStringBuilder.append(':').append(this.infoTimestamp);
    localStringBuilder.append(')');
    if (!"UNAVAILABLE".equals(this.infoClassloader))
      localStringBuilder.append('@').append(this.infoClassloader);
    return localStringBuilder.toString();
  }

  public static final VersionInfo loadVersionInfo(String paramString, ClassLoader paramClassLoader)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    if (paramClassLoader == null)
      paramClassLoader = Thread.currentThread().getContextClassLoader();
    Object localObject1 = null;
    try
    {
      InputStream localInputStream = paramClassLoader.getResourceAsStream(paramString.replace('.', '/') + "/" + "version.properties");
      if (localInputStream != null)
        try
        {
          Properties localProperties = new Properties();
          localProperties.load(localInputStream);
          localObject1 = localProperties;
        }
        finally
        {
          localInputStream.close();
        }
    }
    catch (IOException localIOException)
    {
    }
    VersionInfo localVersionInfo = null;
    if (localObject1 != null)
      localVersionInfo = fromMap(paramString, localObject1, paramClassLoader);
    return localVersionInfo;
  }

  protected static final VersionInfo fromMap(String paramString, Map paramMap, ClassLoader paramClassLoader)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    String str1 = null;
    String str2 = null;
    String str3 = null;
    if (paramMap != null)
    {
      str1 = (String)paramMap.get("info.module");
      if ((str1 != null) && (str1.length() < 1))
        str1 = null;
      str2 = (String)paramMap.get("info.release");
      if ((str2 != null) && ((str2.length() < 1) || (str2.equals("${pom.version}"))))
        str2 = null;
      str3 = (String)paramMap.get("info.timestamp");
      if ((str3 != null) && ((str3.length() < 1) || (str3.equals("${mvn.timestamp}"))))
        str3 = null;
    }
    String str4 = null;
    if (paramClassLoader != null)
      str4 = paramClassLoader.toString();
    return new VersionInfo(paramString, str1, str2, str3, str4);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.util.VersionInfo
 * JD-Core Version:    0.6.2
 */