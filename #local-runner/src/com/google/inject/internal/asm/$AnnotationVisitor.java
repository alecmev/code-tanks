package com.google.inject.internal.asm;

public abstract interface $AnnotationVisitor
{
  public abstract void visit(String paramString, Object paramObject);

  public abstract void visitEnum(String paramString1, String paramString2, String paramString3);

  public abstract AnnotationVisitor visitAnnotation(String paramString1, String paramString2);

  public abstract AnnotationVisitor visitArray(String paramString);

  public abstract void visitEnd();
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.asm..AnnotationVisitor
 * JD-Core Version:    0.6.2
 */