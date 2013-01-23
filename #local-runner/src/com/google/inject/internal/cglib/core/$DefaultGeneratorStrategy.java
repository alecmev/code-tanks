package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..ClassWriter;

public class $DefaultGeneratorStrategy
  implements .GeneratorStrategy
{
  public static final DefaultGeneratorStrategy INSTANCE = new DefaultGeneratorStrategy();

  public byte[] generate($ClassGenerator paramClassGenerator)
    throws Exception
  {
    $ClassWriter localClassWriter = getClassWriter();
    transform(paramClassGenerator).generateClass(localClassWriter);
    return transform(localClassWriter.toByteArray());
  }

  protected $ClassWriter getClassWriter()
    throws Exception
  {
    return new $DebuggingClassWriter(1);
  }

  protected byte[] transform(byte[] paramArrayOfByte)
    throws Exception
  {
    return paramArrayOfByte;
  }

  protected $ClassGenerator transform($ClassGenerator paramClassGenerator)
    throws Exception
  {
    return paramClassGenerator;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..DefaultGeneratorStrategy
 * JD-Core Version:    0.6.2
 */