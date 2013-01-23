package org.apache.log4j.or;

import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.RendererSupport;

public class RendererMap
{
  Hashtable map = new Hashtable();
  static ObjectRenderer defaultRenderer = new DefaultRenderer();

  public static void addRenderer(RendererSupport paramRendererSupport, String paramString1, String paramString2)
  {
    LogLog.debug("Rendering class: [" + paramString2 + "], Rendered class: [" + paramString1 + "].");
    ObjectRenderer localObjectRenderer = (ObjectRenderer)OptionConverter.instantiateByClassName(paramString2, ObjectRenderer.class, null);
    if (localObjectRenderer == null)
    {
      LogLog.error("Could not instantiate renderer [" + paramString2 + "].");
      return;
    }
    try
    {
      Class localClass = Loader.loadClass(paramString1);
      paramRendererSupport.setRenderer(localClass, localObjectRenderer);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      LogLog.error("Could not find class [" + paramString1 + "].", localClassNotFoundException);
    }
  }

  public void clear()
  {
    this.map.clear();
  }

  public void put(Class paramClass, ObjectRenderer paramObjectRenderer)
  {
    this.map.put(paramClass, paramObjectRenderer);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     org.apache.log4j.or.RendererMap
 * JD-Core Version:    0.6.2
 */