package com.google.inject.internal.asm;

public class $ClassWriter
  implements .ClassVisitor
{
  public static final int COMPUTE_MAXS = 1;
  public static final int COMPUTE_FRAMES = 2;
  static final byte[] a = arrayOfByte;
  $ClassReader J;
  int b;
  int c = 1;
  final .ByteVector d = new $ByteVector();
  $Item[] e = new $Item[256];
  int f = (int)(0.75D * this.e.length);
  final .Item g = new $Item();
  final .Item h = new $Item();
  final .Item i = new $Item();
  $Item[] E;
  private short D;
  private int j;
  private int k;
  String F;
  private int l;
  private int m;
  private int n;
  private int[] o;
  private int p;
  private $ByteVector q;
  private int r;
  private int s;
  private $AnnotationWriter t;
  private $AnnotationWriter u;
  private $Attribute v;
  private int w;
  private $ByteVector x;
  $FieldWriter y;
  $FieldWriter z;
  $MethodWriter A;
  $MethodWriter B;
  private final boolean H;
  private final boolean G;
  boolean I;

  public $ClassWriter(int paramInt)
  {
    this.H = ((paramInt & 0x1) != 0);
    this.G = ((paramInt & 0x2) != 0);
  }

  public $ClassWriter($ClassReader paramClassReader, int paramInt)
  {
    this(paramInt);
    paramClassReader.a(this);
    this.J = paramClassReader;
  }

  public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    this.b = paramInt1;
    this.j = paramInt2;
    this.k = newClass(paramString1);
    this.F = paramString1;
    if (paramString2 != null)
      this.l = newUTF8(paramString2);
    this.m = (paramString3 == null ? 0 : newClass(paramString3));
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      this.n = paramArrayOfString.length;
      this.o = new int[this.n];
      for (int i1 = 0; i1 < this.n; i1++)
        this.o[i1] = newClass(paramArrayOfString[i1]);
    }
  }

  public void visitSource(String paramString1, String paramString2)
  {
    if (paramString1 != null)
      this.p = newUTF8(paramString1);
    if (paramString2 != null)
      this.q = new $ByteVector().putUTF8(paramString2);
  }

  public void visitOuterClass(String paramString1, String paramString2, String paramString3)
  {
    this.r = newClass(paramString1);
    if ((paramString2 != null) && (paramString3 != null))
      this.s = newNameType(paramString2, paramString3);
  }

  public .AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean)
  {
    $ByteVector localByteVector = new $ByteVector();
    localByteVector.putShort(newUTF8(paramString)).putShort(0);
    $AnnotationWriter localAnnotationWriter = new $AnnotationWriter(this, true, localByteVector, localByteVector, 2);
    if (paramBoolean)
    {
      localAnnotationWriter.g = this.t;
      this.t = localAnnotationWriter;
    }
    else
    {
      localAnnotationWriter.g = this.u;
      this.u = localAnnotationWriter;
    }
    return localAnnotationWriter;
  }

  public void visitAttribute($Attribute paramAttribute)
  {
    paramAttribute.a = this.v;
    this.v = paramAttribute;
  }

  public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (this.x == null)
      this.x = new $ByteVector();
    this.w += 1;
    this.x.putShort(paramString1 == null ? 0 : newClass(paramString1));
    this.x.putShort(paramString2 == null ? 0 : newClass(paramString2));
    this.x.putShort(paramString3 == null ? 0 : newUTF8(paramString3));
    this.x.putShort(paramInt);
  }

  public .FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject)
  {
    return new $FieldWriter(this, paramInt, paramString1, paramString2, paramString3, paramObject);
  }

  public .MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    return new $MethodWriter(this, paramInt, paramString1, paramString2, paramString3, paramArrayOfString, this.H, this.G);
  }

  public void visitEnd()
  {
  }

  public byte[] toByteArray()
  {
    int i1 = 24 + 2 * this.n;
    int i2 = 0;
    for ($FieldWriter localFieldWriter = this.y; localFieldWriter != null; localFieldWriter = localFieldWriter.a)
    {
      i2++;
      i1 += localFieldWriter.a();
    }
    int i3 = 0;
    for ($MethodWriter localMethodWriter = this.A; localMethodWriter != null; localMethodWriter = localMethodWriter.a)
    {
      i3++;
      i1 += localMethodWriter.a();
    }
    int i4 = 0;
    if (this.l != 0)
    {
      i4++;
      i1 += 8;
      newUTF8("Signature");
    }
    if (this.p != 0)
    {
      i4++;
      i1 += 8;
      newUTF8("SourceFile");
    }
    if (this.q != null)
    {
      i4++;
      i1 += this.q.b + 4;
      newUTF8("SourceDebugExtension");
    }
    if (this.r != 0)
    {
      i4++;
      i1 += 10;
      newUTF8("EnclosingMethod");
    }
    if ((this.j & 0x20000) != 0)
    {
      i4++;
      i1 += 6;
      newUTF8("Deprecated");
    }
    if (((this.j & 0x1000) != 0) && ((this.b & 0xFFFF) < 49))
    {
      i4++;
      i1 += 6;
      newUTF8("Synthetic");
    }
    if (this.x != null)
    {
      i4++;
      i1 += 8 + this.x.b;
      newUTF8("InnerClasses");
    }
    if (this.t != null)
    {
      i4++;
      i1 += 8 + this.t.a();
      newUTF8("RuntimeVisibleAnnotations");
    }
    if (this.u != null)
    {
      i4++;
      i1 += 8 + this.u.a();
      newUTF8("RuntimeInvisibleAnnotations");
    }
    if (this.v != null)
    {
      i4 += this.v.a();
      i1 += this.v.a(this, null, 0, -1, -1);
    }
    i1 += this.d.b;
    $ByteVector localByteVector = new $ByteVector(i1);
    localByteVector.putInt(-889275714).putInt(this.b);
    localByteVector.putShort(this.c).putByteArray(this.d.a, 0, this.d.b);
    localByteVector.putShort(this.j).putShort(this.k).putShort(this.m);
    localByteVector.putShort(this.n);
    for (int i5 = 0; i5 < this.n; i5++)
      localByteVector.putShort(this.o[i5]);
    localByteVector.putShort(i2);
    for (localFieldWriter = this.y; localFieldWriter != null; localFieldWriter = localFieldWriter.a)
      localFieldWriter.a(localByteVector);
    localByteVector.putShort(i3);
    for (localMethodWriter = this.A; localMethodWriter != null; localMethodWriter = localMethodWriter.a)
      localMethodWriter.a(localByteVector);
    localByteVector.putShort(i4);
    if (this.l != 0)
      localByteVector.putShort(newUTF8("Signature")).putInt(2).putShort(this.l);
    if (this.p != 0)
      localByteVector.putShort(newUTF8("SourceFile")).putInt(2).putShort(this.p);
    if (this.q != null)
    {
      i5 = this.q.b - 2;
      localByteVector.putShort(newUTF8("SourceDebugExtension")).putInt(i5);
      localByteVector.putByteArray(this.q.a, 2, i5);
    }
    if (this.r != 0)
    {
      localByteVector.putShort(newUTF8("EnclosingMethod")).putInt(4);
      localByteVector.putShort(this.r).putShort(this.s);
    }
    if ((this.j & 0x20000) != 0)
      localByteVector.putShort(newUTF8("Deprecated")).putInt(0);
    if (((this.j & 0x1000) != 0) && ((this.b & 0xFFFF) < 49))
      localByteVector.putShort(newUTF8("Synthetic")).putInt(0);
    if (this.x != null)
    {
      localByteVector.putShort(newUTF8("InnerClasses"));
      localByteVector.putInt(this.x.b + 2).putShort(this.w);
      localByteVector.putByteArray(this.x.a, 0, this.x.b);
    }
    if (this.t != null)
    {
      localByteVector.putShort(newUTF8("RuntimeVisibleAnnotations"));
      this.t.a(localByteVector);
    }
    if (this.u != null)
    {
      localByteVector.putShort(newUTF8("RuntimeInvisibleAnnotations"));
      this.u.a(localByteVector);
    }
    if (this.v != null)
      this.v.a(this, null, 0, -1, -1, localByteVector);
    if (this.I)
    {
      ClassWriter localClassWriter = new ClassWriter(2);
      new $ClassReader(localByteVector.a).accept(localClassWriter, 4);
      return localClassWriter.toByteArray();
    }
    return localByteVector.a;
  }

  $Item a(Object paramObject)
  {
    int i1;
    if ((paramObject instanceof Integer))
    {
      i1 = ((Integer)paramObject).intValue();
      return a(i1);
    }
    if ((paramObject instanceof Byte))
    {
      i1 = ((Byte)paramObject).intValue();
      return a(i1);
    }
    if ((paramObject instanceof Character))
    {
      i1 = ((Character)paramObject).charValue();
      return a(i1);
    }
    if ((paramObject instanceof Short))
    {
      i1 = ((Short)paramObject).intValue();
      return a(i1);
    }
    if ((paramObject instanceof Boolean))
    {
      i1 = ((Boolean)paramObject).booleanValue() ? 1 : 0;
      return a(i1);
    }
    if ((paramObject instanceof Float))
    {
      float f1 = ((Float)paramObject).floatValue();
      return a(f1);
    }
    if ((paramObject instanceof Long))
    {
      long l1 = ((Long)paramObject).longValue();
      return a(l1);
    }
    if ((paramObject instanceof Double))
    {
      double d1 = ((Double)paramObject).doubleValue();
      return a(d1);
    }
    if ((paramObject instanceof String))
      return b((String)paramObject);
    if ((paramObject instanceof .Type))
    {
      $Type localType = ($Type)paramObject;
      return a(localType.getSort() == 10 ? localType.getInternalName() : localType.getDescriptor());
    }
    throw new IllegalArgumentException("value " + paramObject);
  }

  public int newConst(Object paramObject)
  {
    return a(paramObject).a;
  }

  public int newUTF8(String paramString)
  {
    this.g.a(1, paramString, null, null);
    $Item localItem = a(this.g);
    if (localItem == null)
    {
      this.d.putByte(1).putUTF8(paramString);
      localItem = new $Item(this.c++, this.g);
      b(localItem);
    }
    return localItem.a;
  }

  $Item a(String paramString)
  {
    this.h.a(7, paramString, null, null);
    $Item localItem = a(this.h);
    if (localItem == null)
    {
      this.d.b(7, newUTF8(paramString));
      localItem = new $Item(this.c++, this.h);
      b(localItem);
    }
    return localItem;
  }

  public int newClass(String paramString)
  {
    return a(paramString).a;
  }

  $Item a(String paramString1, String paramString2, String paramString3)
  {
    this.i.a(9, paramString1, paramString2, paramString3);
    $Item localItem = a(this.i);
    if (localItem == null)
    {
      a(9, newClass(paramString1), newNameType(paramString2, paramString3));
      localItem = new $Item(this.c++, this.i);
      b(localItem);
    }
    return localItem;
  }

  public int newField(String paramString1, String paramString2, String paramString3)
  {
    return a(paramString1, paramString2, paramString3).a;
  }

  $Item a(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    int i1 = paramBoolean ? 11 : 10;
    this.i.a(i1, paramString1, paramString2, paramString3);
    $Item localItem = a(this.i);
    if (localItem == null)
    {
      a(i1, newClass(paramString1), newNameType(paramString2, paramString3));
      localItem = new $Item(this.c++, this.i);
      b(localItem);
    }
    return localItem;
  }

  public int newMethod(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    return a(paramString1, paramString2, paramString3, paramBoolean).a;
  }

  $Item a(int paramInt)
  {
    this.g.a(paramInt);
    $Item localItem = a(this.g);
    if (localItem == null)
    {
      this.d.putByte(3).putInt(paramInt);
      localItem = new $Item(this.c++, this.g);
      b(localItem);
    }
    return localItem;
  }

  $Item a(float paramFloat)
  {
    this.g.a(paramFloat);
    $Item localItem = a(this.g);
    if (localItem == null)
    {
      this.d.putByte(4).putInt(this.g.c);
      localItem = new $Item(this.c++, this.g);
      b(localItem);
    }
    return localItem;
  }

  $Item a(long paramLong)
  {
    this.g.a(paramLong);
    $Item localItem = a(this.g);
    if (localItem == null)
    {
      this.d.putByte(5).putLong(paramLong);
      localItem = new $Item(this.c, this.g);
      b(localItem);
      this.c += 2;
    }
    return localItem;
  }

  $Item a(double paramDouble)
  {
    this.g.a(paramDouble);
    $Item localItem = a(this.g);
    if (localItem == null)
    {
      this.d.putByte(6).putLong(this.g.d);
      localItem = new $Item(this.c, this.g);
      b(localItem);
      this.c += 2;
    }
    return localItem;
  }

  private $Item b(String paramString)
  {
    this.h.a(8, paramString, null, null);
    $Item localItem = a(this.h);
    if (localItem == null)
    {
      this.d.b(8, newUTF8(paramString));
      localItem = new $Item(this.c++, this.h);
      b(localItem);
    }
    return localItem;
  }

  public int newNameType(String paramString1, String paramString2)
  {
    this.h.a(12, paramString1, paramString2, null);
    $Item localItem = a(this.h);
    if (localItem == null)
    {
      a(12, newUTF8(paramString1), newUTF8(paramString2));
      localItem = new $Item(this.c++, this.h);
      b(localItem);
    }
    return localItem.a;
  }

  int c(String paramString)
  {
    this.g.a(13, paramString, null, null);
    $Item localItem = a(this.g);
    if (localItem == null)
      localItem = c(this.g);
    return localItem.a;
  }

  int a(String paramString, int paramInt)
  {
    this.g.b = 14;
    this.g.c = paramInt;
    this.g.g = paramString;
    this.g.j = (0x7FFFFFFF & 14 + paramString.hashCode() + paramInt);
    $Item localItem = a(this.g);
    if (localItem == null)
      localItem = c(this.g);
    return localItem.a;
  }

  private $Item c($Item paramItem)
  {
    this.D = ((short)(this.D + 1));
    $Item localItem = new $Item(this.D, this.g);
    b(localItem);
    if (this.E == null)
      this.E = new $Item[16];
    if (this.D == this.E.length)
    {
      $Item[] arrayOfItem = new $Item[2 * this.E.length];
      System.arraycopy(this.E, 0, arrayOfItem, 0, this.E.length);
      this.E = arrayOfItem;
    }
    this.E[this.D] = localItem;
    return localItem;
  }

  int a(int paramInt1, int paramInt2)
  {
    this.h.b = 15;
    this.h.d = (paramInt1 | paramInt2 << 32);
    this.h.j = (0x7FFFFFFF & 15 + paramInt1 + paramInt2);
    $Item localItem = a(this.h);
    if (localItem == null)
    {
      String str1 = this.E[paramInt1].g;
      String str2 = this.E[paramInt2].g;
      this.h.c = c(getCommonSuperClass(str1, str2));
      localItem = new $Item(0, this.h);
      b(localItem);
    }
    return localItem.c;
  }

  protected String getCommonSuperClass(String paramString1, String paramString2)
  {
    Class localClass1;
    Class localClass2;
    try
    {
      localClass1 = Class.forName(paramString1.replace('/', '.'));
      localClass2 = Class.forName(paramString2.replace('/', '.'));
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException.toString());
    }
    if (localClass1.isAssignableFrom(localClass2))
      return paramString1;
    if (localClass2.isAssignableFrom(localClass1))
      return paramString2;
    if ((localClass1.isInterface()) || (localClass2.isInterface()))
      return "java/lang/Object";
    do
      localClass1 = localClass1.getSuperclass();
    while (!localClass1.isAssignableFrom(localClass2));
    return localClass1.getName().replace('.', '/');
  }

  private $Item a($Item paramItem)
  {
    for ($Item localItem = this.e[(paramItem.j % this.e.length)]; (localItem != null) && (!paramItem.a(localItem)); localItem = localItem.k);
    return localItem;
  }

  private void b($Item paramItem)
  {
    if (this.c > this.f)
    {
      i1 = this.e.length;
      int i2 = i1 * 2 + 1;
      $Item[] arrayOfItem = new $Item[i2];
      for (int i3 = i1 - 1; i3 >= 0; i3--)
      {
        $Item localItem;
        for (Object localObject = this.e[i3]; localObject != null; localObject = localItem)
        {
          int i4 = (($Item)localObject).j % arrayOfItem.length;
          localItem = (($Item)localObject).k;
          (($Item)localObject).k = arrayOfItem[i4];
          arrayOfItem[i4] = localObject;
        }
      }
      this.e = arrayOfItem;
      this.f = ((int)(i2 * 0.75D));
    }
    int i1 = paramItem.j % this.e.length;
    paramItem.k = this.e[i1];
    this.e[i1] = paramItem;
  }

  private void a(int paramInt1, int paramInt2, int paramInt3)
  {
    this.d.b(paramInt1, paramInt2).putShort(paramInt3);
  }

  static
  {
    byte[] arrayOfByte = new byte['Ü'];
    String str = "AAAAAAAAAAAAAAAABCKLLDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAAAAAAAAAAAAAAAAAIIIIIIIIIIIIIIIIDNOAAAAAAGGGGGGGHAFBFAAFFAAQPIIJJIIIIIIIIIIIIIIIIII";
    for (int i1 = 0; i1 < arrayOfByte.length; i1++)
      arrayOfByte[i1] = ((byte)(str.charAt(i1) - 'A'));
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.asm..ClassWriter
 * JD-Core Version:    0.6.2
 */