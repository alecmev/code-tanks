package com.google.common.eventbus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.lang.reflect.Method;

class AnnotatedHandlerFinder
  implements HandlerFindingStrategy
{
  public Multimap findAllHandlers(Object paramObject)
  {
    HashMultimap localHashMultimap = HashMultimap.create();
    for (Class localClass1 = paramObject.getClass(); localClass1 != null; localClass1 = localClass1.getSuperclass())
      for (Method localMethod : localClass1.getMethods())
      {
        Subscribe localSubscribe = (Subscribe)localMethod.getAnnotation(Subscribe.class);
        if (localSubscribe != null)
        {
          Class[] arrayOfClass = localMethod.getParameterTypes();
          if (arrayOfClass.length != 1)
            throw new IllegalArgumentException("Method " + localMethod + " has @Subscribe annotation, but requires " + arrayOfClass.length + " arguments.  Event handler methods " + "must require a single argument.");
          Class localClass2 = arrayOfClass[0];
          EventHandler localEventHandler = makeHandler(paramObject, localMethod);
          localHashMultimap.put(localClass2, localEventHandler);
        }
      }
    return localHashMultimap;
  }

  private static EventHandler makeHandler(Object paramObject, Method paramMethod)
  {
    Object localObject;
    if (methodIsDeclaredThreadSafe(paramMethod))
      localObject = new EventHandler(paramObject, paramMethod);
    else
      localObject = new SynchronizedEventHandler(paramObject, paramMethod);
    return localObject;
  }

  private static boolean methodIsDeclaredThreadSafe(Method paramMethod)
  {
    return paramMethod.getAnnotation(AllowConcurrentEvents.class) != null;
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.eventbus.AnnotatedHandlerFinder
 * JD-Core Version:    0.6.2
 */