package com.google.inject.internal.asm;

final class $Item
{
  int a;
  int b;
  int c;
  long d;
  String g;
  String h;
  String i;
  int j;
  Item k;

  $Item()
  {
  }

  $Item(int paramInt)
  {
    this.a = paramInt;
  }

  $Item(int paramInt, Item paramItem)
  {
    this.a = paramInt;
    this.b = paramItem.b;
    this.c = paramItem.c;
    this.d = paramItem.d;
    this.g = paramItem.g;
    this.h = paramItem.h;
    this.i = paramItem.i;
    this.j = paramItem.j;
  }

  void a(int paramInt)
  {
    this.b = 3;
    this.c = paramInt;
    this.j = (0x7FFFFFFF & this.b + paramInt);
  }

  void a(long paramLong)
  {
    this.b = 5;
    this.d = paramLong;
    this.j = (0x7FFFFFFF & this.b + (int)paramLong);
  }

  void a(float paramFloat)
  {
    this.b = 4;
    this.c = Float.floatToRawIntBits(paramFloat);
    this.j = (0x7FFFFFFF & this.b + (int)paramFloat);
  }

  void a(double paramDouble)
  {
    this.b = 6;
    this.d = Double.doubleToRawLongBits(paramDouble);
    this.j = (0x7FFFFFFF & this.b + (int)paramDouble);
  }

  void a(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.b = paramInt;
    this.g = paramString1;
    this.h = paramString2;
    this.i = paramString3;
    switch (paramInt)
    {
    case 1:
    case 7:
    case 8:
    case 13:
      this.j = (0x7FFFFFFF & paramInt + paramString1.hashCode());
      return;
    case 12:
      this.j = (0x7FFFFFFF & paramInt + paramString1.hashCode() * paramString2.hashCode());
      return;
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 9:
    case 10:
    case 11:
    }
    this.j = (0x7FFFFFFF & paramInt + paramString1.hashCode() * paramString2.hashCode() * paramString3.hashCode());
  }

  boolean a(Item paramItem)
  {
    if (paramItem.b == this.b)
    {
      switch (this.b)
      {
      case 3:
      case 4:
        return paramItem.c == this.c;
      case 5:
      case 6:
      case 15:
        return paramItem.d == this.d;
      case 1:
      case 7:
      case 8:
      case 13:
        return paramItem.g.equals(this.g);
      case 14:
        return (paramItem.c == this.c) && (paramItem.g.equals(this.g));
      case 12:
        return (paramItem.g.equals(this.g)) && (paramItem.h.equals(this.h));
      case 2:
      case 9:
      case 10:
      case 11:
      }
      return (paramItem.g.equals(this.g)) && (paramItem.h.equals(this.h)) && (paramItem.i.equals(this.i));
    }
    return false;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.asm..Item
 * JD-Core Version:    0.6.2
 */