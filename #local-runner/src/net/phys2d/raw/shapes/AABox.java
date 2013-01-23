package net.phys2d.raw.shapes;

public class AABox
{
  private float width;
  private float height;
  private float offsetx;
  private float offsety;

  public strictfp AABox(float paramFloat1, float paramFloat2)
  {
    this.width = paramFloat1;
    this.height = paramFloat2;
  }

  public strictfp AABox(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.width = paramFloat3;
    this.height = paramFloat4;
    this.offsetx = paramFloat1;
    this.offsety = paramFloat2;
  }

  public strictfp boolean touches(float paramFloat1, float paramFloat2, AABox paramAABox, float paramFloat3, float paramFloat4)
  {
    float f1 = (paramAABox.width + this.width) / 2.0F;
    float f2 = (paramAABox.height + this.height) / 2.0F;
    float f3 = Math.abs(paramFloat1 + this.offsetx - (paramFloat3 + paramAABox.offsetx));
    float f4 = Math.abs(paramFloat2 + this.offsety - (paramFloat4 + paramAABox.offsety));
    return (f1 > f3) && (f2 > f4);
  }

  public strictfp String toString()
  {
    return "[AABox " + this.width + "x" + this.height + "]";
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     net.phys2d.raw.shapes.AABox
 * JD-Core Version:    0.6.2
 */