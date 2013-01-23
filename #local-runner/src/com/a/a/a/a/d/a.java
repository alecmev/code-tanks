package com.a.a.a.a.d;

import com.a.a.a.a.a.g;
import com.a.a.a.a.a.i;
import com.a.a.a.a.a.j;
import com.a.a.a.a.a.k;
import com.a.a.a.a.a.l;
import com.a.a.a.a.a.n;
import com.a.a.a.a.a.o;
import com.google.common.primitives.Ints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.vecmath.Vector2d;
import org.apache.commons.lang.StringUtils;

public class a
  implements h
{
  private final Panel a;
  private final BlockingQueue b = new LinkedBlockingQueue();
  private final AtomicBoolean c = new AtomicBoolean();

  public a(int paramInt1, int paramInt2, Map paramMap)
  {
    String str = (String)paramMap.get("render-to-screen-scale");
    if (StringUtils.isBlank(str))
      str = "1.0";
    double d = Double.parseDouble(str);
    if ((d < 0.1D) || (d > 10.0D))
      throw new IllegalArgumentException("Illegal scale of screen renderer: " + d + '.');
    paramInt1 = Ints.checkedCast(StrictMath.round(paramInt1 * d));
    paramInt2 = Ints.checkedCast(StrictMath.round(paramInt2 * d));
    this.a = new Panel();
    this.a.setSize(paramInt1, paramInt2);
    this.a.setPreferredSize(new Dimension(paramInt1, paramInt2));
    this.a.addKeyListener(new f(this));
    Frame localFrame = new Frame();
    localFrame.addWindowListener(new e(this));
    localFrame.add(this.a);
    localFrame.setVisible(true);
    localFrame.pack();
    localFrame.setResizable(false);
    a();
    new Thread(new d(this, paramMap)).start();
  }

  public void a(g paramg)
  {
    try
    {
      this.b.put(new a(paramg, null));
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }

  public void close()
  {
    this.b.add(new a(null, null));
  }

  private void a()
  {
    Graphics localGraphics = this.a.getGraphics();
    localGraphics.setFont(new Font("Times New Roman", 1, 30));
    FontMetrics localFontMetrics = localGraphics.getFontMetrics();
    String str1 = "Waiting for game client to connect...";
    int i = localFontMetrics.stringWidth(str1);
    localGraphics.drawString(str1, (this.a.getWidth() - i) / 2, this.a.getHeight() / 2 - localFontMetrics.getHeight());
    String str2 = "Ожидание подключения стратегии...";
    int j = localFontMetrics.stringWidth(str2);
    localGraphics.drawString(str2, (this.a.getWidth() - j) / 2, this.a.getHeight() / 2);
  }

  private void b(g paramg)
  {
    while (this.c.get())
      com.a.c.a.a.d.a(16L);
    BufferedImage localBufferedImage = new BufferedImage(this.a.getWidth(), this.a.getHeight(), 1);
    Graphics localGraphics = localBufferedImage.getGraphics();
    localGraphics.setColor(Color.WHITE);
    localGraphics.fillRect(0, 0, localBufferedImage.getWidth(), localBufferedImage.getHeight());
    localGraphics.setColor(Color.BLACK);
    b(paramg, localGraphics);
    c(paramg, localGraphics);
    d(paramg, localGraphics);
    this.a.getGraphics().drawImage(localBufferedImage, 0, 0, null);
  }

  private void a(g paramg, Graphics paramGraphics)
  {
    paramGraphics.setFont(new Font("Courier New", 1, 30));
    i[] arrayOfi = paramg.f();
    Arrays.sort(arrayOfi, new c(this));
    int i = 1;
    double d1 = paramg.d() / 2.0D - 250.0D;
    double d2 = paramg.e() / 2.0D - 150.0D;
    double d3 = 75.0D;
    int j = 0;
    int k = arrayOfi.length;
    while (j < k)
    {
      i locali = arrayOfi[j];
      int m;
      if ((j == 0) || (locali.b() != arrayOfi[(j - 1)].b()))
      {
        m = j + 1;
        i = m;
      }
      else
      {
        m = i;
      }
      Vector2d localVector2d = a(new Vector2d(d1, d2 + d3 * j), paramg);
      paramGraphics.drawString(String.format("%d. %-20s: %d", new Object[] { Integer.valueOf(m), locali.a(), Integer.valueOf(locali.b()) }), (int)localVector2d.x, (int)localVector2d.y);
      j++;
    }
  }

  private void b(g paramg, Graphics paramGraphics)
  {
    for (l locall : paramg.k())
    {
      Vector2d[] arrayOfVector2d = com.a.c.a.a.c.a(new Vector2d(locall.f(), locall.g()), new Vector2d(locall.d(), locall.e()), locall.j());
      int k = arrayOfVector2d.length;
      for (int m = 0; m < k; m++)
        arrayOfVector2d[m] = a(arrayOfVector2d[m], paramg);
      for (m = 0; m < k; m++)
      {
        int n = (m + 1) % k;
        paramGraphics.drawLine((int)arrayOfVector2d[m].x, (int)arrayOfVector2d[m].y, (int)arrayOfVector2d[n].x, (int)arrayOfVector2d[n].y);
      }
      if ((locall instanceof o))
        a(paramg, paramGraphics, (o)locall);
      if ((locall instanceof k))
        a(paramg, paramGraphics, (k)locall);
    }
  }

  private void a(g paramg, Graphics paramGraphics, o paramo)
  {
    double d1;
    switch (b.a[paramo.u().ordinal()])
    {
    case 1:
      d1 = 67.5D;
      break;
    case 2:
      d1 = 82.5D;
      break;
    case 3:
      d1 = 97.5D;
      break;
    default:
      throw new IllegalArgumentException("Unsupported tank type: " + paramo.u() + '.');
    }
    double d2 = StrictMath.min(paramo.d(), paramo.e());
    Vector2d localVector2d1 = com.a.c.a.a.c.a(new Vector2d(d1 - 0.5D * d2, 0.0D), paramo.j() + paramo.l());
    Vector2d localVector2d2 = com.a.c.a.a.c.a(new Vector2d(0.5D * d2, 0.0D), paramo.j() + paramo.l());
    localVector2d2.add(new Vector2d(paramo.f(), paramo.g()));
    Vector2d localVector2d3 = a(localVector2d2, paramg);
    Vector2d localVector2d4 = a(new Vector2d(localVector2d2.x + localVector2d1.x, localVector2d2.y + localVector2d1.y), paramg);
    paramGraphics.drawLine((int)localVector2d3.x, (int)localVector2d3.y, (int)localVector2d4.x, (int)localVector2d4.y);
    Vector2d localVector2d5 = a(new Vector2d(paramo.f() - 0.5D * d2, paramo.g() - 0.5D * d2), paramg);
    Vector2d localVector2d6 = a(new Vector2d(d2, d2), paramg);
    paramGraphics.drawArc((int)localVector2d5.x, (int)localVector2d5.y, (int)localVector2d6.x, (int)localVector2d6.y, 0, 360);
    double d3 = 0.5D * StrictMath.hypot(paramo.d(), paramo.e());
    double d4 = paramo.o() / paramo.p();
    double d5 = paramo.m() / paramo.n();
    double d6 = paramo.q() == 0 ? 0.0D : (paramo.q() - paramo.r()) / paramo.q();
    Vector2d localVector2d7 = a(new Vector2d(paramo.f() - d3, paramo.g() - d3 - 8.0D), paramg);
    Vector2d localVector2d8 = a(new Vector2d(paramo.f() - d3, paramo.g() - d3 - 5.0D), paramg);
    Vector2d localVector2d9 = a(new Vector2d(paramo.f() - d3, paramo.g() - d3 - 2.0D), paramg);
    Color localColor1 = paramGraphics.getColor();
    Color localColor2 = d5 > 0.3D ? Color.ORANGE : d5 > 0.7D ? Color.GREEN : Color.RED;
    paramGraphics.setColor(localColor2);
    a(paramg, paramGraphics, d3, d5, localVector2d7);
    paramGraphics.setColor(Color.BLUE);
    a(paramg, paramGraphics, d3, d4, localVector2d8);
    paramGraphics.setColor(Color.GRAY);
    a(paramg, paramGraphics, d3, d6, localVector2d9);
    paramGraphics.setColor(localColor1);
    Vector2d localVector2d10 = a(new Vector2d(paramo.f() - 0.25D * d2, paramo.g()), paramg);
    paramGraphics.drawString(paramo.a(), (int)localVector2d10.x, (int)localVector2d10.y);
  }

  private void a(g paramg, Graphics paramGraphics, double paramDouble1, double paramDouble2, Vector2d paramVector2d)
  {
    Vector2d localVector2d = a(new Vector2d(2.0D * paramDouble1 * paramDouble2, 3.0D), paramg);
    paramGraphics.fillRect((int)paramVector2d.x, (int)paramVector2d.y, (int)localVector2d.x, 3);
  }

  private void a(g paramg, Graphics paramGraphics, k paramk)
  {
    switch (b.b[paramk.a().ordinal()])
    {
    case 1:
      a(paramGraphics, paramg, paramk.f() - 0.25D * paramk.d(), paramk.g(), paramk.f() + 0.25D * paramk.d(), paramk.g());
      a(paramGraphics, paramg, paramk.f(), paramk.g() - 0.25D * paramk.e(), paramk.f(), paramk.g() + 0.25D * paramk.e());
      break;
    case 2:
      a(paramGraphics, paramg, paramk.f() - 0.5D * paramk.d(), paramk.g() - 0.5D * paramk.e(), paramk.f() + 0.5D * paramk.d(), paramk.g() + 0.5D * paramk.e());
      a(paramGraphics, paramg, paramk.f() - 0.5D * paramk.d(), paramk.g() + 0.5D * paramk.e(), paramk.f() + 0.5D * paramk.d(), paramk.g() - 0.5D * paramk.e());
      break;
    case 3:
      a(paramGraphics, paramg, paramk.f() - 0.25D * paramk.d(), paramk.g() - 0.5D * paramk.e(), paramk.f() - 0.25D * paramk.d(), paramk.g() + 0.5D * paramk.e());
      a(paramGraphics, paramg, paramk.f(), paramk.g() - 0.5D * paramk.e(), paramk.f(), paramk.g() + 0.5D * paramk.e());
      a(paramGraphics, paramg, paramk.f() + 0.25D * paramk.d(), paramk.g() - 0.5D * paramk.e(), paramk.f() + 0.25D * paramk.d(), paramk.g() + 0.5D * paramk.e());
    }
  }

  private void c(g paramg, Graphics paramGraphics)
  {
    for (j localj : paramg.a())
      switch (b.c[localj.c().ordinal()])
      {
      case 1:
      case 2:
      case 3:
        a(paramg, paramGraphics, localj.a(), localj.b(), localj.d());
        break;
      case 4:
        a(paramg, paramGraphics, localj);
      }
  }

  private void a(g paramg, Graphics paramGraphics, double paramDouble1, double paramDouble2, int paramInt)
  {
    if (paramInt >= 10)
      return;
    double d = 2.5D * paramInt;
    Vector2d[] arrayOfVector2d = { new Vector2d(paramDouble1 + 1.0D * d, paramDouble2), new Vector2d(paramDouble1 + 0.2D * d, paramDouble2 + 0.2D * d), new Vector2d(paramDouble1, paramDouble2 + 1.0D * d), new Vector2d(paramDouble1 - 0.2D * d, paramDouble2 + 0.2D * d), new Vector2d(paramDouble1 - 1.0D * d, paramDouble2), new Vector2d(paramDouble1 - 0.2D * d, paramDouble2 - 0.2D * d), new Vector2d(paramDouble1, paramDouble2 - 1.0D * d), new Vector2d(paramDouble1 + 0.2D * d, paramDouble2 - 0.2D * d) };
    int i = arrayOfVector2d.length;
    int[] arrayOfInt1 = new int[i];
    int[] arrayOfInt2 = new int[i];
    for (int j = 0; j < i; j++)
    {
      Vector2d localVector2d = a(arrayOfVector2d[j], paramg);
      arrayOfInt1[j] = ((int)localVector2d.x);
      arrayOfInt2[j] = ((int)localVector2d.y);
    }
    paramGraphics.drawPolygon(new Polygon(arrayOfInt1, arrayOfInt2, i));
  }

  private void a(g paramg, Graphics paramGraphics, j paramj)
  {
    if (paramj.d() >= 30)
      return;
    Color localColor = paramGraphics.getColor();
    Font localFont = new Font("Courier New", 1, 24 - paramj.d() / 3);
    paramGraphics.setFont(localFont);
    Integer localInteger1 = (Integer)paramj.a("crewHealthDelta");
    if ((localInteger1 != null) && (localInteger1.intValue() != 0))
    {
      double d1 = paramj.a() - 25.0D - 10.0D;
      double d3 = paramj.b() - 25.0D - 2.0D * paramj.d();
      Vector2d localVector2d1 = a(new Vector2d(d1, d3), paramg);
      if (localInteger1.intValue() > 0)
      {
        paramGraphics.setColor(Color.GREEN);
        paramGraphics.drawString("+" + localInteger1, (int)localVector2d1.x, (int)localVector2d1.y);
      }
      else
      {
        paramGraphics.setColor(Color.RED);
        paramGraphics.drawString(String.valueOf(localInteger1), (int)localVector2d1.x, (int)localVector2d1.y);
      }
    }
    Integer localInteger2 = (Integer)paramj.a("hullDurabilityDelta");
    if ((localInteger2 != null) && (localInteger2.intValue() != 0))
    {
      double d2 = paramj.a() + 25.0D - 10.0D;
      double d4 = paramj.b() - 25.0D - 2.0D * paramj.d();
      Vector2d localVector2d2 = a(new Vector2d(d2, d4), paramg);
      paramGraphics.setColor(Color.BLUE);
      if (localInteger2.intValue() > 0)
        paramGraphics.drawString("+" + localInteger2, (int)localVector2d2.x, (int)localVector2d2.y);
      else
        paramGraphics.drawString(String.valueOf(localInteger2), (int)localVector2d2.x, (int)localVector2d2.y);
    }
    paramGraphics.setColor(localColor);
  }

  private void d(g paramg, Graphics paramGraphics)
  {
    Vector2d localVector2d1 = a(new Vector2d(15.0D, 0.0D), paramg);
    paramGraphics.setFont(new Font("Courier New", 0, Ints.checkedCast(StrictMath.round(localVector2d1.x))));
    if (paramg.f().length > 0)
      a(paramg, paramGraphics, 20.0D, 30.0D, paramg.f()[0]);
    if (paramg.f().length > 1)
      a(paramg, paramGraphics, 20.0D, 60.0D, paramg.f()[1]);
    if (paramg.f().length > 2)
      a(paramg, paramGraphics, 235.0D, 30.0D, paramg.f()[2]);
    if (paramg.f().length > 3)
      a(paramg, paramGraphics, 235.0D, 60.0D, paramg.f()[3]);
    if (paramg.f().length > 4)
      a(paramg, paramGraphics, 450.0D, 30.0D, paramg.f()[4]);
    if (paramg.f().length > 5)
      a(paramg, paramGraphics, 450.0D, 60.0D, paramg.f()[5]);
    Vector2d localVector2d2 = a(new Vector2d(paramg.d() - 50.0D, paramg.e() - 30.0D), paramg);
    paramGraphics.drawString(String.valueOf(paramg.b()), (int)localVector2d2.x, (int)localVector2d2.y);
  }

  private void a(g paramg, Graphics paramGraphics, double paramDouble1, double paramDouble2, i parami)
  {
    Vector2d localVector2d = a(new Vector2d(paramDouble1, paramDouble2), paramg);
    paramGraphics.drawString(String.format("%-17s: %d", new Object[] { (parami.c() ? "? " : "") + parami.a(), Integer.valueOf(parami.b()) }), (int)localVector2d.x, (int)localVector2d.y);
  }

  private Vector2d a(Vector2d paramVector2d, n paramn)
  {
    return a(paramVector2d, 0.0D, 0.0D, paramn.d(), paramn.e(), this.a.getWidth(), this.a.getHeight());
  }

  private void a(Graphics paramGraphics, n paramn, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    Vector2d localVector2d1 = a(new Vector2d(paramDouble1, paramDouble2), paramn);
    Vector2d localVector2d2 = a(new Vector2d(paramDouble3, paramDouble4), paramn);
    paramGraphics.drawLine((int)localVector2d1.x, (int)localVector2d1.y, (int)localVector2d2.x, (int)localVector2d2.y);
  }

  private static Vector2d a(Vector2d paramVector2d, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    return new Vector2d((paramVector2d.x - paramDouble1) / (paramDouble3 - paramDouble1) * paramDouble5, (paramVector2d.y - paramDouble2) / (paramDouble4 - paramDouble2) * paramDouble6);
  }

  private static class a
  {
    private final g a;

    private a(g paramg)
    {
      this.a = paramg;
    }

    public g a()
    {
      return this.a;
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.d.a
 * JD-Core Version:    0.6.2
 */