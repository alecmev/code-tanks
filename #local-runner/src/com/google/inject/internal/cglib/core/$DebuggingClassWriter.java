package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..ClassReader;
import com.google.inject.internal.asm..ClassWriter;
import com.google.inject.internal.asm.util..TraceClassVisitor;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class $DebuggingClassWriter extends $ClassWriter
{
  public static final String DEBUG_LOCATION_PROPERTY = "cglib.debugLocation";
  private static String debugLocation = System.getProperty("cglib.debugLocation");
  private static boolean traceEnabled;
  private String className;
  private String superName;

  public $DebuggingClassWriter(int paramInt)
  {
    super(paramInt);
  }

  public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    this.className = paramString1.replace('/', '.');
    this.superName = paramString3.replace('/', '.');
    super.visit(paramInt1, paramInt2, paramString1, paramString2, paramString3, paramArrayOfString);
  }

  public String getClassName()
  {
    return this.className;
  }

  public String getSuperName()
  {
    return this.superName;
  }

  public byte[] toByteArray()
  {
    return (byte[])AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        byte[] arrayOfByte = $DebuggingClassWriter.this.toByteArray();
        if ($DebuggingClassWriter.debugLocation != null)
        {
          String str = $DebuggingClassWriter.this.className.replace('.', File.separatorChar);
          try
          {
            new File($DebuggingClassWriter.debugLocation + File.separatorChar + str).getParentFile().mkdirs();
            File localFile = new File(new File($DebuggingClassWriter.debugLocation), str + ".class");
            BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile));
            try
            {
              localBufferedOutputStream.write(arrayOfByte);
            }
            finally
            {
              localBufferedOutputStream.close();
            }
            if ($DebuggingClassWriter.traceEnabled)
            {
              localFile = new File(new File($DebuggingClassWriter.debugLocation), str + ".asm");
              localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile));
              try
              {
                $ClassReader localClassReader = new $ClassReader(arrayOfByte);
                PrintWriter localPrintWriter = new PrintWriter(new OutputStreamWriter(localBufferedOutputStream));
                $TraceClassVisitor localTraceClassVisitor = new $TraceClassVisitor(null, localPrintWriter);
                localClassReader.accept(localTraceClassVisitor, 0);
                localPrintWriter.flush();
              }
              finally
              {
                localBufferedOutputStream.close();
              }
            }
          }
          catch (IOException localIOException)
          {
            throw new $CodeGenerationException(localIOException);
          }
        }
        return arrayOfByte;
      }
    });
  }

  static
  {
    if (debugLocation != null)
    {
      System.err.println("CGLIB debugging enabled, writing to '" + debugLocation + "'");
      try
      {
        Class.forName("com.google.inject.internal.asm.util.$TraceClassVisitor");
        traceEnabled = true;
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..DebuggingClassWriter
 * JD-Core Version:    0.6.2
 */