package com.google.inject.internal.asm;

import java.io.IOException;
import java.io.InputStream;

public class $ClassReader
{
  public static final int SKIP_CODE = 1;
  public static final int SKIP_DEBUG = 2;
  public static final int SKIP_FRAMES = 4;
  public static final int EXPAND_FRAMES = 8;
  public final byte[] b;
  private final int[] a;
  private final String[] c;
  private final int d;
  public final int header;

  public $ClassReader(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public $ClassReader(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.b = paramArrayOfByte;
    this.a = new int[readUnsignedShort(paramInt1 + 8)];
    int i = this.a.length;
    this.c = new String[i];
    int j = 0;
    int k = paramInt1 + 10;
    for (int m = 1; m < i; m++)
    {
      this.a[m] = (k + 1);
      int n;
      switch (paramArrayOfByte[k])
      {
      case 3:
      case 4:
      case 9:
      case 10:
      case 11:
      case 12:
        n = 5;
        break;
      case 5:
      case 6:
        n = 9;
        m++;
        break;
      case 1:
        n = 3 + readUnsignedShort(k + 1);
        if (n > j)
          j = n;
        break;
      case 2:
      case 7:
      case 8:
      default:
        n = 3;
      }
      k += n;
    }
    this.d = j;
    this.header = k;
  }

  public int getAccess()
  {
    return readUnsignedShort(this.header);
  }

  public String getClassName()
  {
    return readClass(this.header + 2, new char[this.d]);
  }

  public String getSuperName()
  {
    int i = this.a[readUnsignedShort(this.header + 4)];
    return i == 0 ? null : readUTF8(i, new char[this.d]);
  }

  public String[] getInterfaces()
  {
    int i = this.header + 6;
    int j = readUnsignedShort(i);
    String[] arrayOfString = new String[j];
    if (j > 0)
    {
      char[] arrayOfChar = new char[this.d];
      for (int k = 0; k < j; k++)
      {
        i += 2;
        arrayOfString[k] = readClass(i, arrayOfChar);
      }
    }
    return arrayOfString;
  }

  void a($ClassWriter paramClassWriter)
  {
    char[] arrayOfChar = new char[this.d];
    int i = this.a.length;
    $Item[] arrayOfItem = new $Item[i];
    for (int j = 1; j < i; j++)
    {
      int k = this.a[j];
      int m = this.b[(k - 1)];
      $Item localItem = new $Item(j);
      switch (m)
      {
      case 9:
      case 10:
      case 11:
        int n = this.a[readUnsignedShort(k + 2)];
        localItem.a(m, readClass(k, arrayOfChar), readUTF8(n, arrayOfChar), readUTF8(n + 2, arrayOfChar));
        break;
      case 3:
        localItem.a(readInt(k));
        break;
      case 4:
        localItem.a(Float.intBitsToFloat(readInt(k)));
        break;
      case 12:
        localItem.a(m, readUTF8(k, arrayOfChar), readUTF8(k + 2, arrayOfChar), null);
        break;
      case 5:
        localItem.a(readLong(k));
        j++;
        break;
      case 6:
        localItem.a(Double.longBitsToDouble(readLong(k)));
        j++;
        break;
      case 1:
        String str = this.c[j];
        if (str == null)
        {
          k = this.a[j];
          str = this.c[j] =  = a(k + 2, readUnsignedShort(k), arrayOfChar);
        }
        localItem.a(m, str, null, null);
        break;
      case 2:
      case 7:
      case 8:
      default:
        localItem.a(m, readUTF8(k, arrayOfChar), null, null);
      }
      int i1 = localItem.j % arrayOfItem.length;
      localItem.k = arrayOfItem[i1];
      arrayOfItem[i1] = localItem;
    }
    j = this.a[1] - 1;
    paramClassWriter.d.putByteArray(this.b, j, this.header - j);
    paramClassWriter.e = arrayOfItem;
    paramClassWriter.f = ((int)(0.75D * i));
    paramClassWriter.c = i;
  }

  public $ClassReader(InputStream paramInputStream)
    throws IOException
  {
    this(a(paramInputStream));
  }

  public $ClassReader(String paramString)
    throws IOException
  {
    this(ClassLoader.getSystemResourceAsStream(paramString.replace('.', '/') + ".class"));
  }

  private static byte[] a(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IOException("Class not found");
    Object localObject = new byte[paramInputStream.available()];
    int i = 0;
    while (true)
    {
      int j = paramInputStream.read((byte[])localObject, i, localObject.length - i);
      byte[] arrayOfByte;
      if (j == -1)
      {
        if (i < localObject.length)
        {
          arrayOfByte = new byte[i];
          System.arraycopy(localObject, 0, arrayOfByte, 0, i);
          localObject = arrayOfByte;
        }
        return localObject;
      }
      i += j;
      if (i == localObject.length)
      {
        arrayOfByte = new byte[localObject.length + 1000];
        System.arraycopy(localObject, 0, arrayOfByte, 0, i);
        localObject = arrayOfByte;
      }
    }
  }

  public void accept($ClassVisitor paramClassVisitor, int paramInt)
  {
    accept(paramClassVisitor, new $Attribute[0], paramInt);
  }

  public void accept($ClassVisitor paramClassVisitor, .Attribute[] paramArrayOfAttribute, int paramInt)
  {
    byte[] arrayOfByte = this.b;
    char[] arrayOfChar = new char[this.d];
    int i = 0;
    int j = 0;
    Object localObject1 = null;
    int k = this.header;
    int m = readUnsignedShort(k);
    String str1 = readClass(k + 2, arrayOfChar);
    int n = this.a[readUnsignedShort(k + 4)];
    String str2 = n == 0 ? null : readUTF8(n, arrayOfChar);
    String[] arrayOfString1 = new String[readUnsignedShort(k + 6)];
    int i1 = 0;
    k += 8;
    for (int i2 = 0; i2 < arrayOfString1.length; i2++)
    {
      arrayOfString1[i2] = readClass(k, arrayOfChar);
      k += 2;
    }
    int i3 = (paramInt & 0x1) != 0 ? 1 : 0;
    int i4 = (paramInt & 0x2) != 0 ? 1 : 0;
    int i5 = (paramInt & 0x8) != 0 ? 1 : 0;
    n = k;
    i2 = readUnsignedShort(n);
    n += 2;
    int i6;
    while (i2 > 0)
    {
      i6 = readUnsignedShort(n + 6);
      n += 8;
      while (i6 > 0)
      {
        n += 6 + readInt(n + 2);
        i6--;
      }
      i2--;
    }
    i2 = readUnsignedShort(n);
    n += 2;
    while (i2 > 0)
    {
      i6 = readUnsignedShort(n + 6);
      n += 8;
      while (i6 > 0)
      {
        n += 6 + readInt(n + 2);
        i6--;
      }
      i2--;
    }
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
    String str8 = null;
    i2 = readUnsignedShort(n);
    n += 2;
    String str9;
    int i7;
    $Attribute localAttribute;
    while (i2 > 0)
    {
      str9 = readUTF8(n, arrayOfChar);
      if ("SourceFile".equals(str9))
      {
        str4 = readUTF8(n + 6, arrayOfChar);
      }
      else if ("InnerClasses".equals(str9))
      {
        i1 = n + 6;
      }
      else if ("EnclosingMethod".equals(str9))
      {
        str6 = readClass(n + 6, arrayOfChar);
        i7 = readUnsignedShort(n + 8);
        if (i7 != 0)
        {
          str7 = readUTF8(this.a[i7], arrayOfChar);
          str8 = readUTF8(this.a[i7] + 2, arrayOfChar);
        }
      }
      else if ("Signature".equals(str9))
      {
        str3 = readUTF8(n + 6, arrayOfChar);
      }
      else if ("RuntimeVisibleAnnotations".equals(str9))
      {
        i = n + 6;
      }
      else if ("Deprecated".equals(str9))
      {
        m |= 131072;
      }
      else if ("Synthetic".equals(str9))
      {
        m |= 4096;
      }
      else if ("SourceDebugExtension".equals(str9))
      {
        i7 = readInt(n + 2);
        str5 = a(n + 6, i7, new char[i7]);
      }
      else if ("RuntimeInvisibleAnnotations".equals(str9))
      {
        j = n + 6;
      }
      else
      {
        localAttribute = a(paramArrayOfAttribute, str9, n + 6, readInt(n + 2), arrayOfChar, -1, null);
        if (localAttribute != null)
        {
          localAttribute.a = localObject1;
          localObject1 = localAttribute;
        }
      }
      n += 6 + readInt(n + 2);
      i2--;
    }
    paramClassVisitor.visit(readInt(4), m, str1, str3, str2, arrayOfString1);
    if ((i4 == 0) && ((str4 != null) || (str5 != null)))
      paramClassVisitor.visitSource(str4, str5);
    if (str6 != null)
      paramClassVisitor.visitOuterClass(str6, str7, str8);
    for (i2 = 1; i2 >= 0; i2--)
    {
      n = i2 == 0 ? j : i;
      if (n != 0)
      {
        i6 = readUnsignedShort(n);
        n += 2;
        while (i6 > 0)
        {
          n = a(n + 2, arrayOfChar, true, paramClassVisitor.visitAnnotation(readUTF8(n, arrayOfChar), i2 != 0));
          i6--;
        }
      }
    }
    while (localObject1 != null)
    {
      localAttribute = localObject1.a;
      localObject1.a = null;
      paramClassVisitor.visitAttribute(localObject1);
      localObject1 = localAttribute;
    }
    if (i1 != 0)
    {
      i2 = readUnsignedShort(i1);
      i1 += 2;
      while (i2 > 0)
      {
        paramClassVisitor.visitInnerClass(readUnsignedShort(i1) == 0 ? null : readClass(i1, arrayOfChar), readUnsignedShort(i1 + 2) == 0 ? null : readClass(i1 + 2, arrayOfChar), readUnsignedShort(i1 + 4) == 0 ? null : readUTF8(i1 + 4, arrayOfChar), readUnsignedShort(i1 + 6));
        i1 += 8;
        i2--;
      }
    }
    i2 = readUnsignedShort(k);
    k += 2;
    String str10;
    int i9;
    while (i2 > 0)
    {
      m = readUnsignedShort(k);
      str1 = readUTF8(k + 2, arrayOfChar);
      str10 = readUTF8(k + 4, arrayOfChar);
      i7 = 0;
      str3 = null;
      i = 0;
      j = 0;
      localObject1 = null;
      i6 = readUnsignedShort(k + 6);
      k += 8;
      while (i6 > 0)
      {
        str9 = readUTF8(k, arrayOfChar);
        if ("ConstantValue".equals(str9))
        {
          i7 = readUnsignedShort(k + 6);
        }
        else if ("Signature".equals(str9))
        {
          str3 = readUTF8(k + 6, arrayOfChar);
        }
        else if ("Deprecated".equals(str9))
        {
          m |= 131072;
        }
        else if ("Synthetic".equals(str9))
        {
          m |= 4096;
        }
        else if ("RuntimeVisibleAnnotations".equals(str9))
        {
          i = k + 6;
        }
        else if ("RuntimeInvisibleAnnotations".equals(str9))
        {
          j = k + 6;
        }
        else
        {
          localAttribute = a(paramArrayOfAttribute, str9, k + 6, readInt(k + 2), arrayOfChar, -1, null);
          if (localAttribute != null)
          {
            localAttribute.a = localObject1;
            localObject1 = localAttribute;
          }
        }
        k += 6 + readInt(k + 2);
        i6--;
      }
      $FieldVisitor localFieldVisitor = paramClassVisitor.visitField(m, str1, str10, str3, i7 == 0 ? null : readConst(i7, arrayOfChar));
      if (localFieldVisitor != null)
      {
        for (i6 = 1; i6 >= 0; i6--)
        {
          n = i6 == 0 ? j : i;
          if (n != 0)
          {
            i9 = readUnsignedShort(n);
            n += 2;
            while (i9 > 0)
            {
              n = a(n + 2, arrayOfChar, true, localFieldVisitor.visitAnnotation(readUTF8(n, arrayOfChar), i6 != 0));
              i9--;
            }
          }
        }
        while (localObject1 != null)
        {
          localAttribute = localObject1.a;
          localObject1.a = null;
          localFieldVisitor.visitAttribute(localObject1);
          localObject1 = localAttribute;
        }
        localFieldVisitor.visitEnd();
      }
      i2--;
    }
    i2 = readUnsignedShort(k);
    k += 2;
    while (i2 > 0)
    {
      i7 = k + 6;
      m = readUnsignedShort(k);
      str1 = readUTF8(k + 2, arrayOfChar);
      str10 = readUTF8(k + 4, arrayOfChar);
      str3 = null;
      i = 0;
      j = 0;
      int i8 = 0;
      int i10 = 0;
      int i11 = 0;
      localObject1 = null;
      n = 0;
      i1 = 0;
      i6 = readUnsignedShort(k + 6);
      k += 8;
      while (i6 > 0)
      {
        str9 = readUTF8(k, arrayOfChar);
        int i12 = readInt(k + 2);
        k += 6;
        if ("Code".equals(str9))
        {
          if (i3 == 0)
            n = k;
        }
        else if ("Exceptions".equals(str9))
        {
          i1 = k;
        }
        else if ("Signature".equals(str9))
        {
          str3 = readUTF8(k, arrayOfChar);
        }
        else if ("Deprecated".equals(str9))
        {
          m |= 131072;
        }
        else if ("RuntimeVisibleAnnotations".equals(str9))
        {
          i = k;
        }
        else if ("AnnotationDefault".equals(str9))
        {
          i8 = k;
        }
        else if ("Synthetic".equals(str9))
        {
          m |= 4096;
        }
        else if ("RuntimeInvisibleAnnotations".equals(str9))
        {
          j = k;
        }
        else if ("RuntimeVisibleParameterAnnotations".equals(str9))
        {
          i10 = k;
        }
        else if ("RuntimeInvisibleParameterAnnotations".equals(str9))
        {
          i11 = k;
        }
        else
        {
          localAttribute = a(paramArrayOfAttribute, str9, k, i12, arrayOfChar, -1, null);
          if (localAttribute != null)
          {
            localAttribute.a = localObject1;
            localObject1 = localAttribute;
          }
        }
        k += i12;
        i6--;
      }
      String[] arrayOfString2;
      if (i1 == 0)
      {
        arrayOfString2 = null;
      }
      else
      {
        arrayOfString2 = new String[readUnsignedShort(i1)];
        i1 += 2;
        for (i6 = 0; i6 < arrayOfString2.length; i6++)
        {
          arrayOfString2[i6] = readClass(i1, arrayOfChar);
          i1 += 2;
        }
      }
      $MethodVisitor localMethodVisitor = paramClassVisitor.visitMethod(m, str1, str10, str3, arrayOfString2);
      int i14;
      if (localMethodVisitor != null)
      {
        Object localObject2;
        if ((localMethodVisitor instanceof .MethodWriter))
        {
          localObject2 = ($MethodWriter)localMethodVisitor;
          if (((($MethodWriter)localObject2).b.J == this) && (str3 == (($MethodWriter)localObject2).g))
          {
            i14 = 0;
            if (arrayOfString2 == null)
            {
              i14 = (($MethodWriter)localObject2).j == 0 ? 1 : 0;
            }
            else if (arrayOfString2.length == (($MethodWriter)localObject2).j)
            {
              i14 = 1;
              for (i6 = arrayOfString2.length - 1; i6 >= 0; i6--)
              {
                i1 -= 2;
                if (localObject2.k[i6] != readUnsignedShort(i1))
                {
                  i14 = 0;
                  break;
                }
              }
            }
            if (i14 != 0)
            {
              (($MethodWriter)localObject2).h = i7;
              (($MethodWriter)localObject2).i = (k - i7);
              break label5472;
            }
          }
        }
        if (i8 != 0)
        {
          localObject2 = localMethodVisitor.visitAnnotationDefault();
          a(i8, arrayOfChar, null, ($AnnotationVisitor)localObject2);
          if (localObject2 != null)
            (($AnnotationVisitor)localObject2).visitEnd();
        }
        for (i6 = 1; i6 >= 0; i6--)
        {
          i1 = i6 == 0 ? j : i;
          if (i1 != 0)
          {
            i9 = readUnsignedShort(i1);
            i1 += 2;
            while (i9 > 0)
            {
              i1 = a(i1 + 2, arrayOfChar, true, localMethodVisitor.visitAnnotation(readUTF8(i1, arrayOfChar), i6 != 0));
              i9--;
            }
          }
        }
        if (i10 != 0)
          a(i10, str10, arrayOfChar, true, localMethodVisitor);
        if (i11 != 0)
          a(i11, str10, arrayOfChar, false, localMethodVisitor);
        while (localObject1 != null)
        {
          localAttribute = localObject1.a;
          localObject1.a = null;
          localMethodVisitor.visitAttribute(localObject1);
          localObject1 = localAttribute;
        }
      }
      else
      {
        if ((localMethodVisitor != null) && (n != 0))
        {
          int i13 = readUnsignedShort(n);
          i14 = readUnsignedShort(n + 2);
          int i15 = readInt(n + 4);
          n += 8;
          int i16 = n;
          int i17 = n + i15;
          localMethodVisitor.visitCode();
          $Label[] arrayOfLabel1 = new $Label[i15 + 2];
          readLabel(i15 + 1, arrayOfLabel1);
          while (n < i17)
          {
            i1 = n - i16;
            int i18 = arrayOfByte[n] & 0xFF;
            switch ($ClassWriter.a[i18])
            {
            case 0:
            case 4:
              n++;
              break;
            case 8:
              readLabel(i1 + readShort(n + 1), arrayOfLabel1);
              n += 3;
              break;
            case 9:
              readLabel(i1 + readInt(n + 1), arrayOfLabel1);
              n += 5;
              break;
            case 16:
              i18 = arrayOfByte[(n + 1)] & 0xFF;
              if (i18 == 132)
                n += 6;
              else
                n += 4;
              break;
            case 13:
              n = n + 4 - (i1 & 0x3);
              readLabel(i1 + readInt(n), arrayOfLabel1);
              i6 = readInt(n + 8) - readInt(n + 4) + 1;
              n += 12;
            case 14:
            case 1:
            case 3:
            case 10:
            case 2:
            case 5:
            case 6:
            case 11:
            case 12:
            case 7:
            case 15:
            default:
              while (i6 > 0)
              {
                readLabel(i1 + readInt(n), arrayOfLabel1);
                n += 4;
                i6--;
                continue;
                n = n + 4 - (i1 & 0x3);
                readLabel(i1 + readInt(n), arrayOfLabel1);
                i6 = readInt(n + 4);
                n += 8;
                while (i6 > 0)
                {
                  readLabel(i1 + readInt(n + 4), arrayOfLabel1);
                  n += 8;
                  i6--;
                  continue;
                  n += 2;
                  break;
                  n += 3;
                  break;
                  n += 5;
                  break;
                  n += 4;
                }
              }
            }
          }
          i6 = readUnsignedShort(n);
          n += 2;
          while (i6 > 0)
          {
            $Label localLabel1 = readLabel(readUnsignedShort(n), arrayOfLabel1);
            $Label localLabel2 = readLabel(readUnsignedShort(n + 2), arrayOfLabel1);
            $Label localLabel3 = readLabel(readUnsignedShort(n + 4), arrayOfLabel1);
            i22 = readUnsignedShort(n + 6);
            if (i22 == 0)
              localMethodVisitor.visitTryCatchBlock(localLabel1, localLabel2, localLabel3, null);
            else
              localMethodVisitor.visitTryCatchBlock(localLabel1, localLabel2, localLabel3, readUTF8(this.a[i22], arrayOfChar));
            n += 8;
            i6--;
          }
          int i19 = 0;
          int i20 = 0;
          int i21 = 0;
          int i22 = 0;
          int i23 = 0;
          int i24 = 0;
          int i25 = 0;
          int i26 = 0;
          int i27 = 0;
          Object[] arrayOfObject1 = null;
          Object[] arrayOfObject2 = null;
          int i28 = 1;
          localObject1 = null;
          i6 = readUnsignedShort(n);
          n += 2;
          int i29;
          while (i6 > 0)
          {
            str9 = readUTF8(n, arrayOfChar);
            if ("LocalVariableTable".equals(str9))
            {
              if (i4 == 0)
              {
                i19 = n + 6;
                i9 = readUnsignedShort(n + 6);
                i1 = n + 8;
                while (i9 > 0)
                {
                  i29 = readUnsignedShort(i1);
                  if (arrayOfLabel1[i29] == null)
                    readLabel(i29, arrayOfLabel1).a |= 1;
                  i29 += readUnsignedShort(i1 + 2);
                  if (arrayOfLabel1[i29] == null)
                    readLabel(i29, arrayOfLabel1).a |= 1;
                  i1 += 10;
                  i9--;
                }
              }
            }
            else if ("LocalVariableTypeTable".equals(str9))
              i20 = n + 6;
            else if ("LineNumberTable".equals(str9))
            {
              if (i4 == 0)
              {
                i9 = readUnsignedShort(n + 6);
                i1 = n + 8;
                while (i9 > 0)
                {
                  i29 = readUnsignedShort(i1);
                  if (arrayOfLabel1[i29] == null)
                    readLabel(i29, arrayOfLabel1).a |= 1;
                  arrayOfLabel1[i29].b = readUnsignedShort(i1 + 2);
                  i1 += 4;
                  i9--;
                }
              }
            }
            else if ("StackMapTable".equals(str9))
            {
              if ((paramInt & 0x4) == 0)
              {
                i21 = n + 8;
                i22 = readUnsignedShort(n + 6);
              }
            }
            else if ("StackMap".equals(str9))
            {
              if ((paramInt & 0x4) == 0)
              {
                i21 = n + 8;
                i22 = readUnsignedShort(n + 6);
                i28 = 0;
              }
            }
            else
              for (i9 = 0; i9 < paramArrayOfAttribute.length; i9++)
                if (paramArrayOfAttribute[i9].type.equals(str9))
                {
                  localAttribute = paramArrayOfAttribute[i9].read(this, n + 6, readInt(n + 2), arrayOfChar, i16 - 8, arrayOfLabel1);
                  if (localAttribute != null)
                  {
                    localAttribute.a = localObject1;
                    localObject1 = localAttribute;
                  }
                }
            n += 6 + readInt(n + 2);
            i6--;
          }
          if (i21 != 0)
          {
            arrayOfObject1 = new Object[i14];
            arrayOfObject2 = new Object[i13];
            if (i5 != 0)
            {
              int i30 = 0;
              if ((m & 0x8) == 0)
                if ("<init>".equals(str1))
                  arrayOfObject1[(i30++)] = $Opcodes.UNINITIALIZED_THIS;
                else
                  arrayOfObject1[(i30++)] = readClass(this.header + 2, arrayOfChar);
              i6 = 1;
              while (true)
              {
                i9 = i6;
                switch (str10.charAt(i6++))
                {
                case 'B':
                case 'C':
                case 'I':
                case 'S':
                case 'Z':
                  arrayOfObject1[(i30++)] = $Opcodes.INTEGER;
                  break;
                case 'F':
                  arrayOfObject1[(i30++)] = $Opcodes.FLOAT;
                  break;
                case 'J':
                  arrayOfObject1[(i30++)] = $Opcodes.LONG;
                  break;
                case 'D':
                  arrayOfObject1[(i30++)] = $Opcodes.DOUBLE;
                  break;
                case '[':
                  while (str10.charAt(i6) == '[')
                    i6++;
                  if (str10.charAt(i6) == 'L')
                  {
                    i6++;
                    while (str10.charAt(i6) != ';')
                      i6++;
                  }
                  arrayOfObject1[(i30++)] = str10.substring(i9, ++i6);
                  break;
                case 'L':
                  while (str10.charAt(i6) != ';')
                    i6++;
                  arrayOfObject1[(i30++)] = str10.substring(i9 + 1, i6++);
                case 'E':
                case 'G':
                case 'H':
                case 'K':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                }
              }
              i25 = i30;
            }
            i24 = -1;
          }
          n = i16;
          int i32;
          int i33;
          Object localObject3;
          while (n < i17)
          {
            i1 = n - i16;
            localLabel4 = arrayOfLabel1[i1];
            if (localLabel4 != null)
            {
              localMethodVisitor.visitLabel(localLabel4);
              if ((i4 == 0) && (localLabel4.b > 0))
                localMethodVisitor.visitLineNumber(localLabel4.b, localLabel4);
            }
            while ((arrayOfObject1 != null) && ((i24 == i1) || (i24 == -1)))
            {
              if ((i28 == 0) || (i5 != 0))
                localMethodVisitor.visitFrame(-1, i25, arrayOfObject1, i27, arrayOfObject2);
              else if (i24 != -1)
                localMethodVisitor.visitFrame(i23, i26, arrayOfObject1, i27, arrayOfObject2);
              if (i22 > 0)
              {
                if (i28 != 0)
                {
                  i31 = arrayOfByte[(i21++)] & 0xFF;
                }
                else
                {
                  i31 = 255;
                  i24 = -1;
                }
                i26 = 0;
                if (i31 < 64)
                {
                  i32 = i31;
                  i23 = 3;
                  i27 = 0;
                }
                else if (i31 < 128)
                {
                  i32 = i31 - 64;
                  i21 = a(arrayOfObject2, 0, i21, arrayOfChar, arrayOfLabel1);
                  i23 = 4;
                  i27 = 1;
                }
                else
                {
                  i32 = readUnsignedShort(i21);
                  i21 += 2;
                  if (i31 == 247)
                  {
                    i21 = a(arrayOfObject2, 0, i21, arrayOfChar, arrayOfLabel1);
                    i23 = 4;
                    i27 = 1;
                  }
                  else if ((i31 >= 248) && (i31 < 251))
                  {
                    i23 = 2;
                    i26 = 251 - i31;
                    i25 -= i26;
                    i27 = 0;
                  }
                  else if (i31 == 251)
                  {
                    i23 = 3;
                    i27 = 0;
                  }
                  else if (i31 < 255)
                  {
                    i6 = i5 != 0 ? i25 : 0;
                    for (i9 = i31 - 251; i9 > 0; i9--)
                      i21 = a(arrayOfObject1, i6++, i21, arrayOfChar, arrayOfLabel1);
                    i23 = 1;
                    i26 = i31 - 251;
                    i25 += i26;
                    i27 = 0;
                  }
                  else
                  {
                    i23 = 0;
                    i33 = i26 = i25 = readUnsignedShort(i21);
                    i21 += 2;
                    i6 = 0;
                    while (i33 > 0)
                    {
                      i21 = a(arrayOfObject1, i6++, i21, arrayOfChar, arrayOfLabel1);
                      i33--;
                    }
                    i33 = i27 = readUnsignedShort(i21);
                    i21 += 2;
                    i6 = 0;
                    while (i33 > 0)
                    {
                      i21 = a(arrayOfObject2, i6++, i21, arrayOfChar, arrayOfLabel1);
                      i33--;
                    }
                  }
                }
                i24 += i32 + 1;
                readLabel(i24, arrayOfLabel1);
                i22--;
              }
              else
              {
                arrayOfObject1 = null;
              }
            }
            int i31 = arrayOfByte[n] & 0xFF;
            switch ($ClassWriter.a[i31])
            {
            case 0:
              localMethodVisitor.visitInsn(i31);
              n++;
              break;
            case 4:
              if (i31 > 54)
              {
                i31 -= 59;
                localMethodVisitor.visitVarInsn(54 + (i31 >> 2), i31 & 0x3);
              }
              else
              {
                i31 -= 26;
                localMethodVisitor.visitVarInsn(21 + (i31 >> 2), i31 & 0x3);
              }
              n++;
              break;
            case 8:
              localMethodVisitor.visitJumpInsn(i31, arrayOfLabel1[(i1 + readShort(n + 1))]);
              n += 3;
              break;
            case 9:
              localMethodVisitor.visitJumpInsn(i31 - 33, arrayOfLabel1[(i1 + readInt(n + 1))]);
              n += 5;
              break;
            case 16:
              i31 = arrayOfByte[(n + 1)] & 0xFF;
              if (i31 == 132)
              {
                localMethodVisitor.visitIincInsn(readUnsignedShort(n + 2), readShort(n + 4));
                n += 6;
              }
              else
              {
                localMethodVisitor.visitVarInsn(i31, readUnsignedShort(n + 2));
                n += 4;
              }
              break;
            case 13:
              n = n + 4 - (i1 & 0x3);
              i29 = i1 + readInt(n);
              i32 = readInt(n + 4);
              i33 = readInt(n + 8);
              n += 12;
              $Label[] arrayOfLabel2 = new $Label[i33 - i32 + 1];
              for (i6 = 0; i6 < arrayOfLabel2.length; i6++)
              {
                arrayOfLabel2[i6] = arrayOfLabel1[(i1 + readInt(n))];
                n += 4;
              }
              localMethodVisitor.visitTableSwitchInsn(i32, i33, arrayOfLabel1[i29], arrayOfLabel2);
              break;
            case 14:
              n = n + 4 - (i1 & 0x3);
              i29 = i1 + readInt(n);
              i6 = readInt(n + 4);
              n += 8;
              localObject3 = new int[i6];
              $Label[] arrayOfLabel3 = new $Label[i6];
              for (i6 = 0; i6 < localObject3.length; i6++)
              {
                localObject3[i6] = readInt(n);
                arrayOfLabel3[i6] = arrayOfLabel1[(i1 + readInt(n + 4))];
                n += 8;
              }
              localMethodVisitor.visitLookupSwitchInsn(arrayOfLabel1[i29], (int[])localObject3, arrayOfLabel3);
              break;
            case 3:
              localMethodVisitor.visitVarInsn(i31, arrayOfByte[(n + 1)] & 0xFF);
              n += 2;
              break;
            case 1:
              localMethodVisitor.visitIntInsn(i31, arrayOfByte[(n + 1)]);
              n += 2;
              break;
            case 2:
              localMethodVisitor.visitIntInsn(i31, readShort(n + 1));
              n += 3;
              break;
            case 10:
              localMethodVisitor.visitLdcInsn(readConst(arrayOfByte[(n + 1)] & 0xFF, arrayOfChar));
              n += 2;
              break;
            case 11:
              localMethodVisitor.visitLdcInsn(readConst(readUnsignedShort(n + 1), arrayOfChar));
              n += 3;
              break;
            case 6:
            case 7:
              int i36 = this.a[readUnsignedShort(n + 1)];
              String str11 = readClass(i36, arrayOfChar);
              i36 = this.a[readUnsignedShort(i36 + 2)];
              String str12 = readUTF8(i36, arrayOfChar);
              String str13 = readUTF8(i36 + 2, arrayOfChar);
              if (i31 < 182)
                localMethodVisitor.visitFieldInsn(i31, str11, str12, str13);
              else
                localMethodVisitor.visitMethodInsn(i31, str11, str12, str13);
              if (i31 == 185)
                n += 5;
              else
                n += 3;
              break;
            case 5:
              localMethodVisitor.visitTypeInsn(i31, readClass(n + 1, arrayOfChar));
              n += 3;
              break;
            case 12:
              localMethodVisitor.visitIincInsn(arrayOfByte[(n + 1)] & 0xFF, arrayOfByte[(n + 2)]);
              n += 3;
              break;
            case 15:
            default:
              localMethodVisitor.visitMultiANewArrayInsn(readClass(n + 1, arrayOfChar), arrayOfByte[(n + 3)] & 0xFF);
              n += 4;
            }
          }
          $Label localLabel4 = arrayOfLabel1[(i17 - i16)];
          if (localLabel4 != null)
            localMethodVisitor.visitLabel(localLabel4);
          if ((i4 == 0) && (i19 != 0))
          {
            int[] arrayOfInt = null;
            if (i20 != 0)
            {
              i9 = readUnsignedShort(i20) * 3;
              i1 = i20 + 2;
              arrayOfInt = new int[i9];
              while (i9 > 0)
              {
                arrayOfInt[(--i9)] = (i1 + 6);
                arrayOfInt[(--i9)] = readUnsignedShort(i1 + 8);
                arrayOfInt[(--i9)] = readUnsignedShort(i1);
                i1 += 10;
              }
            }
            i9 = readUnsignedShort(i19);
            i1 = i19 + 2;
            while (i9 > 0)
            {
              i32 = readUnsignedShort(i1);
              i33 = readUnsignedShort(i1 + 2);
              int i34 = readUnsignedShort(i1 + 8);
              localObject3 = null;
              if (arrayOfInt != null)
                for (int i35 = 0; i35 < arrayOfInt.length; i35 += 3)
                  if ((arrayOfInt[i35] == i32) && (arrayOfInt[(i35 + 1)] == i34))
                  {
                    localObject3 = readUTF8(arrayOfInt[(i35 + 2)], arrayOfChar);
                    break;
                  }
              localMethodVisitor.visitLocalVariable(readUTF8(i1 + 4, arrayOfChar), readUTF8(i1 + 6, arrayOfChar), (String)localObject3, arrayOfLabel1[i32], arrayOfLabel1[(i32 + i33)], i34);
              i1 += 10;
              i9--;
            }
          }
          while (localObject1 != null)
          {
            localAttribute = localObject1.a;
            localObject1.a = null;
            localMethodVisitor.visitAttribute(localObject1);
            localObject1 = localAttribute;
          }
          localMethodVisitor.visitMaxs(i13, i14);
        }
        if (localMethodVisitor != null)
          localMethodVisitor.visitEnd();
      }
      label5472: i2--;
    }
    paramClassVisitor.visitEnd();
  }

  private void a(int paramInt, String paramString, char[] paramArrayOfChar, boolean paramBoolean, .MethodVisitor paramMethodVisitor)
  {
    int i = this.b[(paramInt++)] & 0xFF;
    int j = $Type.getArgumentTypes(paramString).length - i;
    $AnnotationVisitor localAnnotationVisitor;
    for (int k = 0; k < j; k++)
    {
      localAnnotationVisitor = paramMethodVisitor.visitParameterAnnotation(k, "Ljava/lang/Synthetic;", false);
      if (localAnnotationVisitor != null)
        localAnnotationVisitor.visitEnd();
    }
    while (k < i + j)
    {
      int m = readUnsignedShort(paramInt);
      paramInt += 2;
      while (m > 0)
      {
        localAnnotationVisitor = paramMethodVisitor.visitParameterAnnotation(k, readUTF8(paramInt, paramArrayOfChar), paramBoolean);
        paramInt = a(paramInt + 2, paramArrayOfChar, true, localAnnotationVisitor);
        m--;
      }
      k++;
    }
  }

  private int a(int paramInt, char[] paramArrayOfChar, boolean paramBoolean, .AnnotationVisitor paramAnnotationVisitor)
  {
    int i = readUnsignedShort(paramInt);
    paramInt += 2;
    if (paramBoolean)
      while (i > 0)
      {
        paramInt = a(paramInt + 2, paramArrayOfChar, readUTF8(paramInt, paramArrayOfChar), paramAnnotationVisitor);
        i--;
      }
    while (i > 0)
    {
      paramInt = a(paramInt, paramArrayOfChar, null, paramAnnotationVisitor);
      i--;
    }
    if (paramAnnotationVisitor != null)
      paramAnnotationVisitor.visitEnd();
    return paramInt;
  }

  private int a(int paramInt, char[] paramArrayOfChar, String paramString, .AnnotationVisitor paramAnnotationVisitor)
  {
    if (paramAnnotationVisitor == null)
    {
      switch (this.b[paramInt] & 0xFF)
      {
      case 101:
        return paramInt + 5;
      case 64:
        return a(paramInt + 3, paramArrayOfChar, true, null);
      case 91:
        return a(paramInt + 1, paramArrayOfChar, false, null);
      }
      return paramInt + 3;
    }
    switch (this.b[(paramInt++)] & 0xFF)
    {
    case 68:
    case 70:
    case 73:
    case 74:
      paramAnnotationVisitor.visit(paramString, readConst(readUnsignedShort(paramInt), paramArrayOfChar));
      paramInt += 2;
      break;
    case 66:
      paramAnnotationVisitor.visit(paramString, new Byte((byte)readInt(this.a[readUnsignedShort(paramInt)])));
      paramInt += 2;
      break;
    case 90:
      paramAnnotationVisitor.visit(paramString, readInt(this.a[readUnsignedShort(paramInt)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
      paramInt += 2;
      break;
    case 83:
      paramAnnotationVisitor.visit(paramString, new Short((short)readInt(this.a[readUnsignedShort(paramInt)])));
      paramInt += 2;
      break;
    case 67:
      paramAnnotationVisitor.visit(paramString, new Character((char)readInt(this.a[readUnsignedShort(paramInt)])));
      paramInt += 2;
      break;
    case 115:
      paramAnnotationVisitor.visit(paramString, readUTF8(paramInt, paramArrayOfChar));
      paramInt += 2;
      break;
    case 101:
      paramAnnotationVisitor.visitEnum(paramString, readUTF8(paramInt, paramArrayOfChar), readUTF8(paramInt + 2, paramArrayOfChar));
      paramInt += 4;
      break;
    case 99:
      paramAnnotationVisitor.visit(paramString, .Type.getType(readUTF8(paramInt, paramArrayOfChar)));
      paramInt += 2;
      break;
    case 64:
      paramInt = a(paramInt + 2, paramArrayOfChar, true, paramAnnotationVisitor.visitAnnotation(paramString, readUTF8(paramInt, paramArrayOfChar)));
      break;
    case 91:
      int i = readUnsignedShort(paramInt);
      paramInt += 2;
      if (i == 0)
        return a(paramInt - 2, paramArrayOfChar, false, paramAnnotationVisitor.visitArray(paramString));
      int j;
      switch (this.b[(paramInt++)] & 0xFF)
      {
      case 66:
        byte[] arrayOfByte = new byte[i];
        for (j = 0; j < i; j++)
        {
          arrayOfByte[j] = ((byte)readInt(this.a[readUnsignedShort(paramInt)]));
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfByte);
        paramInt--;
        break;
      case 90:
        boolean[] arrayOfBoolean = new boolean[i];
        for (j = 0; j < i; j++)
        {
          arrayOfBoolean[j] = (readInt(this.a[readUnsignedShort(paramInt)]) != 0 ? 1 : false);
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfBoolean);
        paramInt--;
        break;
      case 83:
        short[] arrayOfShort = new short[i];
        for (j = 0; j < i; j++)
        {
          arrayOfShort[j] = ((short)readInt(this.a[readUnsignedShort(paramInt)]));
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfShort);
        paramInt--;
        break;
      case 67:
        char[] arrayOfChar = new char[i];
        for (j = 0; j < i; j++)
        {
          arrayOfChar[j] = ((char)readInt(this.a[readUnsignedShort(paramInt)]));
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfChar);
        paramInt--;
        break;
      case 73:
        int[] arrayOfInt = new int[i];
        for (j = 0; j < i; j++)
        {
          arrayOfInt[j] = readInt(this.a[readUnsignedShort(paramInt)]);
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfInt);
        paramInt--;
        break;
      case 74:
        long[] arrayOfLong = new long[i];
        for (j = 0; j < i; j++)
        {
          arrayOfLong[j] = readLong(this.a[readUnsignedShort(paramInt)]);
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfLong);
        paramInt--;
        break;
      case 70:
        float[] arrayOfFloat = new float[i];
        for (j = 0; j < i; j++)
        {
          arrayOfFloat[j] = Float.intBitsToFloat(readInt(this.a[readUnsignedShort(paramInt)]));
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfFloat);
        paramInt--;
        break;
      case 68:
        double[] arrayOfDouble = new double[i];
        for (j = 0; j < i; j++)
        {
          arrayOfDouble[j] = Double.longBitsToDouble(readLong(this.a[readUnsignedShort(paramInt)]));
          paramInt += 3;
        }
        paramAnnotationVisitor.visit(paramString, arrayOfDouble);
        paramInt--;
        break;
      case 69:
      case 71:
      case 72:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
      case 84:
      case 85:
      case 86:
      case 87:
      case 88:
      case 89:
      default:
        paramInt = a(paramInt - 3, paramArrayOfChar, false, paramAnnotationVisitor.visitArray(paramString));
      }
      break;
    case 65:
    case 69:
    case 71:
    case 72:
    case 75:
    case 76:
    case 77:
    case 78:
    case 79:
    case 80:
    case 81:
    case 82:
    case 84:
    case 85:
    case 86:
    case 87:
    case 88:
    case 89:
    case 92:
    case 93:
    case 94:
    case 95:
    case 96:
    case 97:
    case 98:
    case 100:
    case 102:
    case 103:
    case 104:
    case 105:
    case 106:
    case 107:
    case 108:
    case 109:
    case 110:
    case 111:
    case 112:
    case 113:
    case 114:
    }
    return paramInt;
  }

  private int a(Object[] paramArrayOfObject, int paramInt1, int paramInt2, char[] paramArrayOfChar, .Label[] paramArrayOfLabel)
  {
    int i = this.b[(paramInt2++)] & 0xFF;
    switch (i)
    {
    case 0:
      paramArrayOfObject[paramInt1] = $Opcodes.TOP;
      break;
    case 1:
      paramArrayOfObject[paramInt1] = $Opcodes.INTEGER;
      break;
    case 2:
      paramArrayOfObject[paramInt1] = $Opcodes.FLOAT;
      break;
    case 3:
      paramArrayOfObject[paramInt1] = $Opcodes.DOUBLE;
      break;
    case 4:
      paramArrayOfObject[paramInt1] = $Opcodes.LONG;
      break;
    case 5:
      paramArrayOfObject[paramInt1] = $Opcodes.NULL;
      break;
    case 6:
      paramArrayOfObject[paramInt1] = $Opcodes.UNINITIALIZED_THIS;
      break;
    case 7:
      paramArrayOfObject[paramInt1] = readClass(paramInt2, paramArrayOfChar);
      paramInt2 += 2;
      break;
    default:
      paramArrayOfObject[paramInt1] = readLabel(readUnsignedShort(paramInt2), paramArrayOfLabel);
      paramInt2 += 2;
    }
    return paramInt2;
  }

  protected $Label readLabel(int paramInt, .Label[] paramArrayOfLabel)
  {
    if (paramArrayOfLabel[paramInt] == null)
      paramArrayOfLabel[paramInt] = new $Label();
    return paramArrayOfLabel[paramInt];
  }

  private $Attribute a($Attribute[] paramArrayOfAttribute, String paramString, int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3, .Label[] paramArrayOfLabel)
  {
    for (int i = 0; i < paramArrayOfAttribute.length; i++)
      if (paramArrayOfAttribute[i].type.equals(paramString))
        return paramArrayOfAttribute[i].read(this, paramInt1, paramInt2, paramArrayOfChar, paramInt3, paramArrayOfLabel);
    return new $Attribute(paramString).read(this, paramInt1, paramInt2, null, -1, null);
  }

  public int getItem(int paramInt)
  {
    return this.a[paramInt];
  }

  public int readByte(int paramInt)
  {
    return this.b[paramInt] & 0xFF;
  }

  public int readUnsignedShort(int paramInt)
  {
    byte[] arrayOfByte = this.b;
    return (arrayOfByte[paramInt] & 0xFF) << 8 | arrayOfByte[(paramInt + 1)] & 0xFF;
  }

  public short readShort(int paramInt)
  {
    byte[] arrayOfByte = this.b;
    return (short)((arrayOfByte[paramInt] & 0xFF) << 8 | arrayOfByte[(paramInt + 1)] & 0xFF);
  }

  public int readInt(int paramInt)
  {
    byte[] arrayOfByte = this.b;
    return (arrayOfByte[paramInt] & 0xFF) << 24 | (arrayOfByte[(paramInt + 1)] & 0xFF) << 16 | (arrayOfByte[(paramInt + 2)] & 0xFF) << 8 | arrayOfByte[(paramInt + 3)] & 0xFF;
  }

  public long readLong(int paramInt)
  {
    long l1 = readInt(paramInt);
    long l2 = readInt(paramInt + 4) & 0xFFFFFFFF;
    return l1 << 32 | l2;
  }

  public String readUTF8(int paramInt, char[] paramArrayOfChar)
  {
    int i = readUnsignedShort(paramInt);
    String str = this.c[i];
    if (str != null)
      return str;
    paramInt = this.a[i];
    return this.c[i] =  = a(paramInt + 2, readUnsignedShort(paramInt), paramArrayOfChar);
  }

  private String a(int paramInt1, int paramInt2, char[] paramArrayOfChar)
  {
    int i = paramInt1 + paramInt2;
    byte[] arrayOfByte = this.b;
    int j = 0;
    while (paramInt1 < i)
    {
      int k = arrayOfByte[(paramInt1++)] & 0xFF;
      int m;
      switch (k >> 4)
      {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        paramArrayOfChar[(j++)] = ((char)k);
        break;
      case 12:
      case 13:
        m = arrayOfByte[(paramInt1++)];
        paramArrayOfChar[(j++)] = ((char)((k & 0x1F) << 6 | m & 0x3F));
        break;
      case 8:
      case 9:
      case 10:
      case 11:
      default:
        m = arrayOfByte[(paramInt1++)];
        int n = arrayOfByte[(paramInt1++)];
        paramArrayOfChar[(j++)] = ((char)((k & 0xF) << 12 | (m & 0x3F) << 6 | n & 0x3F));
      }
    }
    return new String(paramArrayOfChar, 0, j);
  }

  public String readClass(int paramInt, char[] paramArrayOfChar)
  {
    return readUTF8(this.a[readUnsignedShort(paramInt)], paramArrayOfChar);
  }

  public Object readConst(int paramInt, char[] paramArrayOfChar)
  {
    int i = this.a[paramInt];
    switch (this.b[(i - 1)])
    {
    case 3:
      return new Integer(readInt(i));
    case 4:
      return new Float(Float.intBitsToFloat(readInt(i)));
    case 5:
      return new Long(readLong(i));
    case 6:
      return new Double(Double.longBitsToDouble(readLong(i)));
    case 7:
      return .Type.getObjectType(readUTF8(i, paramArrayOfChar));
    }
    return readUTF8(i, paramArrayOfChar);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.asm..ClassReader
 * JD-Core Version:    0.6.2
 */