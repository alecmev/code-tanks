package com.a.a.a.a.d;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

class f extends KeyAdapter
{
  f(a parama)
  {
  }

  public void keyPressed(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getKeyChar() == ' ')
    {
      boolean bool;
      do
        bool = a.a(this.a).get();
      while (!a.a(this.a).compareAndSet(bool, !bool));
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.a.a.a.a.d.f
 * JD-Core Version:    0.6.2
 */