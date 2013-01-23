package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..ClassAdapter;
import com.google.inject.internal.asm..ClassReader;
import com.google.inject.internal.asm..ClassVisitor;
import java.util.ArrayList;
import java.util.List;

public class $ClassNameReader
{
  private static final EarlyExitException EARLY_EXIT = new EarlyExitException(null);

  public static String getClassName($ClassReader paramClassReader)
  {
    return getClassInfo(paramClassReader)[0];
  }

  public static String[] getClassInfo($ClassReader paramClassReader)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramClassReader.accept(new $ClassAdapter(null)
      {
        private final List val$array;

        public void visit(int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String[] paramAnonymousArrayOfString)
        {
          this.val$array.add(paramAnonymousString1.replace('/', '.'));
          if (paramAnonymousString3 != null)
            this.val$array.add(paramAnonymousString3.replace('/', '.'));
          for (int i = 0; i < paramAnonymousArrayOfString.length; i++)
            this.val$array.add(paramAnonymousArrayOfString[i].replace('/', '.'));
          throw .ClassNameReader.EARLY_EXIT;
        }
      }
      , 6);
    }
    catch (EarlyExitException localEarlyExitException)
    {
    }
    return (String[])localArrayList.toArray(new String[0]);
  }

  private static class EarlyExitException extends RuntimeException
  {
    private EarlyExitException()
    {
    }

    EarlyExitException($ClassNameReader.1 param1)
    {
      this();
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..ClassNameReader
 * JD-Core Version:    0.6.2
 */