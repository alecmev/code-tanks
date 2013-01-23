package org.apache.http.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;

public abstract interface CredentialsProvider
{
  public abstract Credentials getCredentials(AuthScope paramAuthScope);
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.http.client.CredentialsProvider
 * JD-Core Version:    0.6.2
 */