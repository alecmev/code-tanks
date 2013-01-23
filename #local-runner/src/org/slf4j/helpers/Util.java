package org.slf4j.helpers;

import java.io.PrintStream;

public class Util
{
  public static final void report(String paramString, Throwable paramThrowable)
  {
    System.err.println(paramString);
    System.err.println("Reported exception:");
    paramThrowable.printStackTrace();
  }

  public static final void report(String paramString)
  {
    System.err.println("SLF4J: " + paramString);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.slf4j.helpers.Util
 * JD-Core Version:    0.6.2
 */