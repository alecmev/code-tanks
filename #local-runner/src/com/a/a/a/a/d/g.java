package com.a.a.a.a.d;

import com.a.a.a.a.b.b.b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class g
  implements h
{
  private final String a;
  private StringBuilder b = new StringBuilder();

  public g(String paramString)
    throws IOException
  {
    this.a = paramString;
    a();
  }

  public void a(com.a.a.a.a.a.g paramg)
    throws IOException
  {
    this.b.append(b.a(paramg)).append('\n');
    if (b(paramg))
      a(false);
  }

  private void a()
    throws IOException
  {
    String str = this.a + "/begin";
    DefaultHttpClient localDefaultHttpClient = null;
    HttpPost localHttpPost = null;
    HttpResponse localHttpResponse = null;
    try
    {
      localDefaultHttpClient = new DefaultHttpClient();
      localHttpPost = new HttpPost(str);
      localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      if (localHttpResponse.getStatusLine().getStatusCode() != 200)
        throw new IOException(String.format("Got unexpected response code %d from remote storage '%s' while creating new document.", new Object[] { Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode()), str }));
    }
    finally
    {
      a(localDefaultHttpClient, localHttpPost, localHttpResponse);
    }
  }

  private void a(boolean paramBoolean)
    throws IOException
  {
    String str = this.a + '/' + (paramBoolean ? "end" : "append");
    DefaultHttpClient localDefaultHttpClient = null;
    HttpPost localHttpPost = null;
    HttpResponse localHttpResponse = null;
    try
    {
      localDefaultHttpClient = new DefaultHttpClient();
      localHttpPost = new HttpPost(str);
      ByteArrayOutputStream localByteArrayOutputStream = null;
      GZIPOutputStream localGZIPOutputStream = null;
      ByteArrayInputStream localByteArrayInputStream = null;
      InputStreamEntity localInputStreamEntity;
      try
      {
        byte[] arrayOfByte = this.b.toString().getBytes("UTF-8");
        localByteArrayOutputStream = new ByteArrayOutputStream();
        localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
        localGZIPOutputStream.write(arrayOfByte);
        localGZIPOutputStream.close();
        localByteArrayInputStream = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
        localInputStreamEntity = new InputStreamEntity(localByteArrayInputStream, localByteArrayOutputStream.size());
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new IllegalStateException("UTF-8 is unsupported.", localUnsupportedEncodingException);
      }
      finally
      {
        IOUtils.closeQuietly(localByteArrayOutputStream);
        IOUtils.closeQuietly(localGZIPOutputStream);
        IOUtils.closeQuietly(localByteArrayInputStream);
      }
      localHttpPost.addHeader(new BasicHeader("Content-Encoding", "gzip"));
      localHttpPost.setEntity(localInputStreamEntity);
      localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
      if (localHttpResponse.getStatusLine().getStatusCode() != 200)
        throw new IOException(String.format("Got unexpected response code %d from remote storage '%s' while appending document.", new Object[] { Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode()), str }));
    }
    finally
    {
      a(localDefaultHttpClient, localHttpPost, localHttpResponse);
    }
    this.b = new StringBuilder();
  }

  private static void a(HttpClient paramHttpClient, HttpPost paramHttpPost, HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse != null)
    {
      HttpEntity localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity != null)
        try
        {
          localHttpEntity.getContent().close();
        }
        catch (Exception localException)
        {
        }
    }
    if (paramHttpPost != null)
      paramHttpPost.abort();
    if (paramHttpClient != null)
      paramHttpClient.getConnectionManager().shutdown();
  }

  private static boolean b(com.a.a.a.a.a.g paramg)
  {
    return paramg.b() % 200 == 0;
  }

  public void close()
    throws IOException
  {
    a(true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.d.g
 * JD-Core Version:    0.6.2
 */