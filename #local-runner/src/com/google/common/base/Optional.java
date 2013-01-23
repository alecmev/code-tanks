package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Beta
@GwtCompatible(serializable=true)
public abstract class Optional
  implements Serializable
{
  private static final long serialVersionUID = 0L;

  public static Optional absent()
  {
    return Absent.INSTANCE;
  }

  public static Optional of(Object paramObject)
  {
    return new Present(Preconditions.checkNotNull(paramObject));
  }

  public static Optional fromNullable(Object paramObject)
  {
    return paramObject == null ? absent() : new Present(paramObject);
  }

  public abstract boolean isPresent();

  public abstract Object get();

  public abstract Object or(Object paramObject);

  public abstract Optional or(Optional paramOptional);

  public abstract Object or(Supplier paramSupplier);

  public abstract Object orNull();

  public abstract Set asSet();

  public abstract Optional transform(Function paramFunction);

  public abstract boolean equals(Object paramObject);

  public abstract int hashCode();

  public abstract String toString();

  public static Iterable presentInstances(Iterable paramIterable)
  {
    Preconditions.checkNotNull(paramIterable);
    return new Iterable()
    {
      public Iterator iterator()
      {
        return new AbstractIterator()
        {
          private final Iterator iterator = (Iterator)Preconditions.checkNotNull(Optional.1.this.val$optionals.iterator());

          protected Object computeNext()
          {
            while (this.iterator.hasNext())
            {
              Optional localOptional = (Optional)this.iterator.next();
              if (localOptional.isPresent())
                return localOptional.get();
            }
            return endOfData();
          }
        };
      }
    };
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.common.base.Optional
 * JD-Core Version:    0.6.2
 */