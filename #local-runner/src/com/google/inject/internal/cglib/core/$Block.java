package com.google.inject.internal.cglib.core;

import com.google.inject.internal.asm..Label;

public class $Block
{
  private $CodeEmitter e;
  private $Label start;
  private $Label end;

  public $Block($CodeEmitter paramCodeEmitter)
  {
    this.e = paramCodeEmitter;
    this.start = paramCodeEmitter.mark();
  }

  public .CodeEmitter getCodeEmitter()
  {
    return this.e;
  }

  public void end()
  {
    if (this.end != null)
      throw new IllegalStateException("end of label already set");
    this.end = this.e.mark();
  }

  public .Label getStart()
  {
    return this.start;
  }

  public .Label getEnd()
  {
    return this.end;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.cglib.core..Block
 * JD-Core Version:    0.6.2
 */