package com.google.inject.internal.util;

import com.google.inject.internal.asm..AnnotationVisitor;
import com.google.inject.internal.asm..Attribute;
import com.google.inject.internal.asm..ClassReader;
import com.google.inject.internal.asm..ClassVisitor;
import com.google.inject.internal.asm..FieldVisitor;
import com.google.inject.internal.asm..Label;
import com.google.inject.internal.asm..MethodVisitor;
import com.google.inject.internal.asm..Type;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;

final class $LineNumbers
{
  private final Class type;
  private final Map lines = $Maps.newHashMap();
  private String source;
  private int firstLine = 2147483647;

  public $LineNumbers(Class paramClass)
    throws IOException
  {
    this.type = paramClass;
    if (!paramClass.isArray())
    {
      InputStream localInputStream = paramClass.getResourceAsStream("/" + paramClass.getName().replace('.', '/') + ".class");
      if (localInputStream != null)
        new $ClassReader(localInputStream).accept(new LineNumberReader(null), 4);
    }
  }

  public String getSource()
  {
    return this.source;
  }

  public Integer getLineNumber(Member paramMember)
  {
    $Preconditions.checkArgument(this.type == paramMember.getDeclaringClass(), "Member %s belongs to %s, not %s", new Object[] { paramMember, paramMember.getDeclaringClass(), this.type });
    return (Integer)this.lines.get(memberKey(paramMember));
  }

  public int getFirstLine()
  {
    return this.firstLine == 2147483647 ? 1 : this.firstLine;
  }

  private String memberKey(Member paramMember)
  {
    $Preconditions.checkNotNull(paramMember, "member");
    if ((paramMember instanceof Field))
      return paramMember.getName();
    if ((paramMember instanceof Method))
      return paramMember.getName() + .Type.getMethodDescriptor((Method)paramMember);
    if ((paramMember instanceof Constructor))
    {
      StringBuilder localStringBuilder = new StringBuilder().append("<init>(");
      for (Class localClass : ((Constructor)paramMember).getParameterTypes())
        localStringBuilder.append($Type.getDescriptor(localClass));
      return ")V";
    }
    throw new IllegalArgumentException("Unsupported implementation class for Member, " + paramMember.getClass());
  }

  private class LineNumberReader
    implements .AnnotationVisitor, .ClassVisitor, .MethodVisitor
  {
    private int line = -1;
    private String pendingMethod;
    private String name;

    private LineNumberReader()
    {
    }

    public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
    {
      this.name = paramString1;
    }

    public .MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
    {
      if ((paramInt & 0x2) != 0)
        return null;
      this.pendingMethod = (paramString1 + paramString2);
      this.line = -1;
      return this;
    }

    public void visitSource(String paramString1, String paramString2)
    {
      $LineNumbers.this.source = paramString1;
    }

    public void visitLineNumber(int paramInt, .Label paramLabel)
    {
      if (paramInt < .LineNumbers.this.firstLine)
        $LineNumbers.this.firstLine = paramInt;
      this.line = paramInt;
      if (this.pendingMethod != null)
      {
        $LineNumbers.this.lines.put(this.pendingMethod, Integer.valueOf(paramInt));
        this.pendingMethod = null;
      }
    }

    public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3)
    {
      if ((paramInt == 181) && (this.name.equals(paramString1)) && (!.LineNumbers.this.lines.containsKey(paramString2)) && (this.line != -1))
        $LineNumbers.this.lines.put(paramString2, Integer.valueOf(this.line));
    }

    public void visitEnd()
    {
    }

    public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt)
    {
    }

    public void visitOuterClass(String paramString1, String paramString2, String paramString3)
    {
    }

    public void visitAttribute($Attribute paramAttribute)
    {
    }

    public .FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject)
    {
      return null;
    }

    public .AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean)
    {
      return this;
    }

    public .AnnotationVisitor visitAnnotation(String paramString1, String paramString2)
    {
      return this;
    }

    public .AnnotationVisitor visitAnnotationDefault()
    {
      return this;
    }

    public .AnnotationVisitor visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean)
    {
      return this;
    }

    public .AnnotationVisitor visitArray(String paramString)
    {
      return this;
    }

    public void visitEnum(String paramString1, String paramString2, String paramString3)
    {
    }

    public void visit(String paramString, Object paramObject)
    {
    }

    public void visitCode()
    {
    }

    public void visitFrame(int paramInt1, int paramInt2, Object[] paramArrayOfObject1, int paramInt3, Object[] paramArrayOfObject2)
    {
    }

    public void visitIincInsn(int paramInt1, int paramInt2)
    {
    }

    public void visitInsn(int paramInt)
    {
    }

    public void visitIntInsn(int paramInt1, int paramInt2)
    {
    }

    public void visitJumpInsn(int paramInt, .Label paramLabel)
    {
    }

    public void visitLabel($Label paramLabel)
    {
    }

    public void visitLdcInsn(Object paramObject)
    {
    }

    public void visitLocalVariable(String paramString1, String paramString2, String paramString3, .Label paramLabel1, .Label paramLabel2, int paramInt)
    {
    }

    public void visitLookupSwitchInsn($Label paramLabel, int[] paramArrayOfInt, .Label[] paramArrayOfLabel)
    {
    }

    public void visitMaxs(int paramInt1, int paramInt2)
    {
    }

    public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3)
    {
    }

    public void visitMultiANewArrayInsn(String paramString, int paramInt)
    {
    }

    public void visitTableSwitchInsn(int paramInt1, int paramInt2, .Label paramLabel, .Label[] paramArrayOfLabel)
    {
    }

    public void visitTryCatchBlock($Label paramLabel1, .Label paramLabel2, .Label paramLabel3, String paramString)
    {
    }

    public void visitTypeInsn(int paramInt, String paramString)
    {
    }

    public void visitVarInsn(int paramInt1, int paramInt2)
    {
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.util..LineNumbers
 * JD-Core Version:    0.6.2
 */