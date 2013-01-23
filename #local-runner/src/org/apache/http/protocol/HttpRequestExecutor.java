package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

public class HttpRequestExecutor
{
  protected boolean canResponseHaveBody(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    if ("HEAD".equalsIgnoreCase(paramHttpRequest.getRequestLine().getMethod()))
      return false;
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    return (i >= 200) && (i != 204) && (i != 304) && (i != 205);
  }

  public HttpResponse execute(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("Client connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    try
    {
      HttpResponse localHttpResponse = doSendRequest(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
      if (localHttpResponse == null)
        localHttpResponse = doReceiveResponse(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
      return localHttpResponse;
    }
    catch (IOException localIOException)
    {
      closeConnection(paramHttpClientConnection);
      throw localIOException;
    }
    catch (HttpException localHttpException)
    {
      closeConnection(paramHttpClientConnection);
      throw localHttpException;
    }
    catch (RuntimeException localRuntimeException)
    {
      closeConnection(paramHttpClientConnection);
      throw localRuntimeException;
    }
  }

  private static final void closeConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public void preProcess(HttpRequest paramHttpRequest, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.request", paramHttpRequest);
    paramHttpProcessor.process(paramHttpRequest, paramHttpContext);
  }

  protected HttpResponse doSendRequest(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("HTTP connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    HttpResponse localHttpResponse = null;
    paramHttpContext.setAttribute("http.connection", paramHttpClientConnection);
    paramHttpContext.setAttribute("http.request_sent", Boolean.FALSE);
    paramHttpClientConnection.sendRequestHeader(paramHttpRequest);
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      int i = 1;
      ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
      if ((((HttpEntityEnclosingRequest)paramHttpRequest).expectContinue()) && (!localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)))
      {
        paramHttpClientConnection.flush();
        int j = paramHttpRequest.getParams().getIntParameter("http.protocol.wait-for-continue", 2000);
        if (paramHttpClientConnection.isResponseAvailable(j))
        {
          localHttpResponse = paramHttpClientConnection.receiveResponseHeader();
          if (canResponseHaveBody(paramHttpRequest, localHttpResponse))
            paramHttpClientConnection.receiveResponseEntity(localHttpResponse);
          int k = localHttpResponse.getStatusLine().getStatusCode();
          if (k < 200)
          {
            if (k != 100)
              throw new ProtocolException("Unexpected response: " + localHttpResponse.getStatusLine());
            localHttpResponse = null;
          }
          else
          {
            i = 0;
          }
        }
      }
      if (i != 0)
        paramHttpClientConnection.sendRequestEntity((HttpEntityEnclosingRequest)paramHttpRequest);
    }
    paramHttpClientConnection.flush();
    paramHttpContext.setAttribute("http.request_sent", Boolean.TRUE);
    return localHttpResponse;
  }

  protected HttpResponse doReceiveResponse(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("HTTP connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    HttpResponse localHttpResponse = null;
    for (int i = 0; (localHttpResponse == null) || (i < 200); i = localHttpResponse.getStatusLine().getStatusCode())
    {
      localHttpResponse = paramHttpClientConnection.receiveResponseHeader();
      if (canResponseHaveBody(paramHttpRequest, localHttpResponse))
        paramHttpClientConnection.receiveResponseEntity(localHttpResponse);
    }
    return localHttpResponse;
  }

  public void postProcess(HttpResponse paramHttpResponse, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.response", paramHttpResponse);
    paramHttpProcessor.process(paramHttpResponse, paramHttpContext);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.protocol.HttpRequestExecutor
 * JD-Core Version:    0.6.2
 */