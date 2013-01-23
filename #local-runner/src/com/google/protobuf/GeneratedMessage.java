package com.google.protobuf;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class GeneratedMessage extends AbstractMessage
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final UnknownFieldSet unknownFields;
  protected static boolean alwaysUseFieldBuilders = false;

  protected GeneratedMessage()
  {
    this.unknownFields = UnknownFieldSet.getDefaultInstance();
  }

  protected GeneratedMessage(Builder paramBuilder)
  {
    this.unknownFields = paramBuilder.getUnknownFields();
  }

  static void enableAlwaysUseFieldBuildersForTesting()
  {
    alwaysUseFieldBuilders = true;
  }

  protected abstract FieldAccessorTable internalGetFieldAccessorTable();

  public Descriptors.Descriptor getDescriptorForType()
  {
    return internalGetFieldAccessorTable().descriptor;
  }

  private Map getAllFieldsMutable()
  {
    TreeMap localTreeMap = new TreeMap();
    Descriptors.Descriptor localDescriptor = internalGetFieldAccessorTable().descriptor;
    Iterator localIterator = localDescriptor.getFields().iterator();
    while (localIterator.hasNext())
    {
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
      if (localFieldDescriptor.isRepeated())
      {
        List localList = (List)getField(localFieldDescriptor);
        if (!localList.isEmpty())
          localTreeMap.put(localFieldDescriptor, localList);
      }
      else if (hasField(localFieldDescriptor))
      {
        localTreeMap.put(localFieldDescriptor, getField(localFieldDescriptor));
      }
    }
    return localTreeMap;
  }

  public boolean isInitialized()
  {
    Iterator localIterator1 = getDescriptorForType().getFields().iterator();
    while (localIterator1.hasNext())
    {
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator1.next();
      if ((localFieldDescriptor.isRequired()) && (!hasField(localFieldDescriptor)))
        return false;
      if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        if (localFieldDescriptor.isRepeated())
        {
          List localList = (List)getField(localFieldDescriptor);
          Iterator localIterator2 = localList.iterator();
          while (localIterator2.hasNext())
          {
            Message localMessage = (Message)localIterator2.next();
            if (!localMessage.isInitialized())
              return false;
          }
        }
        else if ((hasField(localFieldDescriptor)) && (!((Message)getField(localFieldDescriptor)).isInitialized()))
        {
          return false;
        }
    }
    return true;
  }

  public Map getAllFields()
  {
    return Collections.unmodifiableMap(getAllFieldsMutable());
  }

  public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).has(this);
  }

  public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).get(this);
  }

  public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeatedCount(this);
  }

  public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeated(this, paramInt);
  }

  public final UnknownFieldSet getUnknownFields()
  {
    return this.unknownFields;
  }

  protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);

  public static GeneratedExtension newMessageScopedGeneratedExtension(Message paramMessage1, final int paramInt, Class paramClass, Message paramMessage2)
  {
    return new GeneratedExtension(new ExtensionDescriptorRetriever()
    {
      public Descriptors.FieldDescriptor getDescriptor()
      {
        return (Descriptors.FieldDescriptor)this.val$scope.getDescriptorForType().getExtensions().get(paramInt);
      }
    }
    , paramClass, paramMessage2, null);
  }

  public static GeneratedExtension newFileScopedGeneratedExtension(Class paramClass, Message paramMessage)
  {
    return new GeneratedExtension(null, paramClass, paramMessage, null);
  }

  private static Method getMethodOrDie(Class paramClass, String paramString, Class[] paramArrayOfClass)
  {
    try
    {
      return paramClass.getMethod(paramString, paramArrayOfClass);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new RuntimeException("Generated message class \"" + paramClass.getName() + "\" missing method \"" + paramString + "\".", localNoSuchMethodException);
    }
  }

  private static Object invokeOrDie(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
  {
    try
    {
      return paramMethod.invoke(paramObject, paramArrayOfObject);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Throwable localThrowable = localInvocationTargetException.getCause();
      if ((localThrowable instanceof RuntimeException))
        throw ((RuntimeException)localThrowable);
      if ((localThrowable instanceof Error))
        throw ((Error)localThrowable);
      throw new RuntimeException("Unexpected exception thrown by generated accessor method.", localThrowable);
    }
  }

  protected Object writeReplace()
    throws ObjectStreamException
  {
    return new GeneratedMessageLite.SerializedForm(this);
  }

  public static final class FieldAccessorTable
  {
    private final Descriptors.Descriptor descriptor;
    private final FieldAccessor[] fields;

    public FieldAccessorTable(Descriptors.Descriptor paramDescriptor, String[] paramArrayOfString, Class paramClass1, Class paramClass2)
    {
      this.descriptor = paramDescriptor;
      this.fields = new FieldAccessor[paramDescriptor.getFields().size()];
      for (int i = 0; i < this.fields.length; i++)
      {
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)paramDescriptor.getFields().get(i);
        if (localFieldDescriptor.isRepeated())
        {
          if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
            this.fields[i] = new RepeatedMessageFieldAccessor(localFieldDescriptor, paramArrayOfString[i], paramClass1, paramClass2);
          else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
            this.fields[i] = new RepeatedEnumFieldAccessor(localFieldDescriptor, paramArrayOfString[i], paramClass1, paramClass2);
          else
            this.fields[i] = new RepeatedFieldAccessor(localFieldDescriptor, paramArrayOfString[i], paramClass1, paramClass2);
        }
        else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          this.fields[i] = new SingularMessageFieldAccessor(localFieldDescriptor, paramArrayOfString[i], paramClass1, paramClass2);
        else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
          this.fields[i] = new SingularEnumFieldAccessor(localFieldDescriptor, paramArrayOfString[i], paramClass1, paramClass2);
        else
          this.fields[i] = new SingularFieldAccessor(localFieldDescriptor, paramArrayOfString[i], paramClass1, paramClass2);
      }
    }

    private FieldAccessor getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != this.descriptor)
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
      if (paramFieldDescriptor.isExtension())
        throw new IllegalArgumentException("This type does not have extensions.");
      return this.fields[paramFieldDescriptor.getIndex()];
    }

    private static final class RepeatedMessageFieldAccessor extends GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
    {
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);

      RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class paramClass1, Class paramClass2)
      {
        super(paramString, paramClass1, paramClass2);
      }

      private Object coerceType(Object paramObject)
      {
        if (this.type.isInstance(paramObject))
          return paramObject;
        return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)paramObject).build();
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        super.setRepeated(paramBuilder, paramInt, coerceType(paramObject));
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.addRepeated(paramBuilder, coerceType(paramObject));
      }

      public Message.Builder newBuilder()
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
    }

    private static final class SingularMessageFieldAccessor extends GeneratedMessage.FieldAccessorTable.SingularFieldAccessor
    {
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);

      SingularMessageFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class paramClass1, Class paramClass2)
      {
        super(paramString, paramClass1, paramClass2);
      }

      private Object coerceType(Object paramObject)
      {
        if (this.type.isInstance(paramObject))
          return paramObject;
        return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)paramObject).build();
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.set(paramBuilder, coerceType(paramObject));
      }

      public Message.Builder newBuilder()
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
    }

    private static final class RepeatedEnumFieldAccessor extends GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
    {
      private final Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      private final Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);

      RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class paramClass1, Class paramClass2)
      {
        super(paramString, paramClass1, paramClass2);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = ((List)super.get(paramGeneratedMessage)).iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          localArrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, localObject, new Object[0]));
        }
        return Collections.unmodifiableList(localArrayList);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = ((List)super.get(paramBuilder)).iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          localArrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, localObject, new Object[0]));
        }
        return Collections.unmodifiableList(localArrayList);
      }

      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(paramGeneratedMessage, paramInt), new Object[0]);
      }

      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(paramBuilder, paramInt), new Object[0]);
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        super.setRepeated(paramBuilder, paramInt, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.addRepeated(paramBuilder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
    }

    private static final class SingularEnumFieldAccessor extends GeneratedMessage.FieldAccessorTable.SingularFieldAccessor
    {
      private Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      private Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);

      SingularEnumFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class paramClass1, Class paramClass2)
      {
        super(paramString, paramClass1, paramClass2);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(paramGeneratedMessage), new Object[0]);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(paramBuilder), new Object[0]);
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.set(paramBuilder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
    }

    private static class RepeatedFieldAccessor
      implements GeneratedMessage.FieldAccessorTable.FieldAccessor
    {
      protected final Class type;
      protected final Method getMethod;
      protected final Method getMethodBuilder;
      protected final Method getRepeatedMethod;
      protected final Method getRepeatedMethodBuilder;
      protected final Method setRepeatedMethod;
      protected final Method addRepeatedMethod;
      protected final Method getCountMethod;
      protected final Method getCountMethodBuilder;
      protected final Method clearMethod;

      RepeatedFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class paramClass1, Class paramClass2)
      {
        this.getMethod = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString + "List", new Class[0]);
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass2, "get" + paramString + "List", new Class[0]);
        this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString, new Class[] { Integer.TYPE });
        this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass2, "get" + paramString, new Class[] { Integer.TYPE });
        this.type = this.getRepeatedMethod.getReturnType();
        this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass2, "set" + paramString, new Class[] { Integer.TYPE, this.type });
        this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass2, "add" + paramString, new Class[] { this.type });
        this.getCountMethod = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString + "Count", new Class[0]);
        this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass2, "get" + paramString + "Count", new Class[0]);
        this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass2, "clear" + paramString, new Class[0]);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getMethod, paramGeneratedMessage, new Object[0]);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, paramBuilder, new Object[0]);
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        clear(paramBuilder);
        Iterator localIterator = ((List)paramObject).iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          addRepeated(paramBuilder, localObject);
        }
      }

      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, paramGeneratedMessage, new Object[] { Integer.valueOf(paramInt) });
      }

      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, paramBuilder, new Object[] { Integer.valueOf(paramInt) });
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.setRepeatedMethod, paramBuilder, new Object[] { Integer.valueOf(paramInt), paramObject });
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.addRepeatedMethod, paramBuilder, new Object[] { paramObject });
      }

      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        throw new UnsupportedOperationException("hasField() called on a singular field.");
      }

      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("hasField() called on a singular field.");
      }

      public int getRepeatedCount(GeneratedMessage paramGeneratedMessage)
      {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, paramGeneratedMessage, new Object[0])).intValue();
      }

      public int getRepeatedCount(GeneratedMessage.Builder paramBuilder)
      {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, paramBuilder, new Object[0])).intValue();
      }

      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }

      public Message.Builder newBuilder()
      {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
    }

    private static class SingularFieldAccessor
      implements GeneratedMessage.FieldAccessorTable.FieldAccessor
    {
      protected final Class type;
      protected final Method getMethod;
      protected final Method getMethodBuilder;
      protected final Method setMethod;
      protected final Method hasMethod;
      protected final Method hasMethodBuilder;
      protected final Method clearMethod;

      SingularFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class paramClass1, Class paramClass2)
      {
        this.getMethod = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString, new Class[0]);
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass2, "get" + paramString, new Class[0]);
        this.type = this.getMethod.getReturnType();
        this.setMethod = GeneratedMessage.getMethodOrDie(paramClass2, "set" + paramString, new Class[] { this.type });
        this.hasMethod = GeneratedMessage.getMethodOrDie(paramClass1, "has" + paramString, new Class[0]);
        this.hasMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass2, "has" + paramString, new Class[0]);
        this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass2, "clear" + paramString, new Class[0]);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getMethod, paramGeneratedMessage, new Object[0]);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, paramBuilder, new Object[0]);
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.setMethod, paramBuilder, new Object[] { paramObject });
      }

      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }

      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
      }

      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, paramGeneratedMessage, new Object[0])).booleanValue();
      }

      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, paramBuilder, new Object[0])).booleanValue();
      }

      public int getRepeatedCount(GeneratedMessage paramGeneratedMessage)
      {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }

      public int getRepeatedCount(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }

      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }

      public Message.Builder newBuilder()
      {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
    }

    private static abstract interface FieldAccessor
    {
      public abstract Object get(GeneratedMessage paramGeneratedMessage);

      public abstract Object get(GeneratedMessage.Builder paramBuilder);

      public abstract void set(GeneratedMessage.Builder paramBuilder, Object paramObject);

      public abstract Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt);

      public abstract Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt);

      public abstract void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject);

      public abstract void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject);

      public abstract boolean has(GeneratedMessage paramGeneratedMessage);

      public abstract boolean has(GeneratedMessage.Builder paramBuilder);

      public abstract int getRepeatedCount(GeneratedMessage paramGeneratedMessage);

      public abstract int getRepeatedCount(GeneratedMessage.Builder paramBuilder);

      public abstract void clear(GeneratedMessage.Builder paramBuilder);

      public abstract Message.Builder newBuilder();
    }
  }

  public static final class GeneratedExtension
  {
    private GeneratedMessage.ExtensionDescriptorRetriever descriptorRetriever;
    private final Class singularType;
    private final Message messageDefaultInstance;
    private final Method enumValueOf;
    private final Method enumGetValueDescriptor;

    private GeneratedExtension(GeneratedMessage.ExtensionDescriptorRetriever paramExtensionDescriptorRetriever, Class paramClass, Message paramMessage)
    {
      if ((Message.class.isAssignableFrom(paramClass)) && (!paramClass.isInstance(paramMessage)))
        throw new IllegalArgumentException("Bad messageDefaultInstance for " + paramClass.getName());
      this.descriptorRetriever = paramExtensionDescriptorRetriever;
      this.singularType = paramClass;
      this.messageDefaultInstance = paramMessage;
      if (ProtocolMessageEnum.class.isAssignableFrom(paramClass))
      {
        this.enumValueOf = GeneratedMessage.getMethodOrDie(paramClass, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(paramClass, "getValueDescriptor", new Class[0]);
      }
      else
      {
        this.enumValueOf = null;
        this.enumGetValueDescriptor = null;
      }
    }

    public void internalInit(final Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (this.descriptorRetriever != null)
        throw new IllegalStateException("Already initialized.");
      this.descriptorRetriever = new GeneratedMessage.ExtensionDescriptorRetriever()
      {
        public Descriptors.FieldDescriptor getDescriptor()
        {
          return paramFieldDescriptor;
        }
      };
    }

    public Descriptors.FieldDescriptor getDescriptor()
    {
      if (this.descriptorRetriever == null)
        throw new IllegalStateException("getDescriptor() called before internalInit()");
      return this.descriptorRetriever.getDescriptor();
    }

    public Message getMessageDefaultInstance()
    {
      return this.messageDefaultInstance;
    }

    private Object fromReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      if (localFieldDescriptor.isRepeated())
      {
        if ((localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) || (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM))
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = ((List)paramObject).iterator();
          while (localIterator.hasNext())
          {
            Object localObject = localIterator.next();
            localArrayList.add(singularFromReflectionType(localObject));
          }
          return localArrayList;
        }
        return paramObject;
      }
      return singularFromReflectionType(paramObject);
    }

    private Object singularFromReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      switch (GeneratedMessage.2.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[localFieldDescriptor.getJavaType().ordinal()])
      {
      case 1:
        if (this.singularType.isInstance(paramObject))
          return paramObject;
        return this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)paramObject).build();
      case 2:
        return GeneratedMessage.invokeOrDie(this.enumValueOf, null, new Object[] { (Descriptors.EnumValueDescriptor)paramObject });
      }
      return paramObject;
    }

    private Object toReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      if (localFieldDescriptor.isRepeated())
      {
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = ((List)paramObject).iterator();
          while (localIterator.hasNext())
          {
            Object localObject = localIterator.next();
            localArrayList.add(singularToReflectionType(localObject));
          }
          return localArrayList;
        }
        return paramObject;
      }
      return singularToReflectionType(paramObject);
    }

    private Object singularToReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      switch (GeneratedMessage.2.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[localFieldDescriptor.getJavaType().ordinal()])
      {
      case 2:
        return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, paramObject, new Object[0]);
      }
      return paramObject;
    }
  }

  private static abstract interface ExtensionDescriptorRetriever
  {
    public abstract Descriptors.FieldDescriptor getDescriptor();
  }

  public static abstract class ExtendableBuilder extends GeneratedMessage.Builder
    implements GeneratedMessage.ExtendableMessageOrBuilder
  {
    private FieldSet extensions = FieldSet.emptySet();

    protected ExtendableBuilder()
    {
    }

    protected ExtendableBuilder(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      super();
    }

    public ExtendableBuilder clear()
    {
      this.extensions = FieldSet.emptySet();
      return (ExtendableBuilder)super.clear();
    }

    public ExtendableBuilder clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    private void ensureExtensionsIsMutable()
    {
      if (this.extensions.isImmutable())
        this.extensions = this.extensions.clone();
    }

    private void verifyExtensionContainingType(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getDescriptor().getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("Extension is for type \"" + paramGeneratedExtension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
    }

    public final boolean hasExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(paramGeneratedExtension.getDescriptor());
    }

    public final int getExtensionCount(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      return this.extensions.getRepeatedFieldCount(localFieldDescriptor);
    }

    public final Object getExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      Object localObject = this.extensions.getField(localFieldDescriptor);
      if (localObject == null)
      {
        if (localFieldDescriptor.isRepeated())
          return Collections.emptyList();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          return paramGeneratedExtension.getMessageDefaultInstance();
        return paramGeneratedExtension.fromReflectionType(localFieldDescriptor.getDefaultValue());
      }
      return paramGeneratedExtension.fromReflectionType(localObject);
    }

    public final Object getExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      return paramGeneratedExtension.singularFromReflectionType(this.extensions.getRepeatedField(localFieldDescriptor, paramInt));
    }

    public final ExtendableBuilder setExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension, Object paramObject)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      this.extensions.setField(localFieldDescriptor, paramGeneratedExtension.toReflectionType(paramObject));
      onChanged();
      return this;
    }

    public final ExtendableBuilder setExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension, int paramInt, Object paramObject)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      this.extensions.setRepeatedField(localFieldDescriptor, paramInt, paramGeneratedExtension.singularToReflectionType(paramObject));
      onChanged();
      return this;
    }

    public final ExtendableBuilder addExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension, Object paramObject)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      this.extensions.addRepeatedField(localFieldDescriptor, paramGeneratedExtension.singularToReflectionType(paramObject));
      onChanged();
      return this;
    }

    public final ExtendableBuilder clearExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.clearField(paramGeneratedExtension.getDescriptor());
      onChanged();
      return this;
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    private FieldSet buildExtensions()
    {
      this.extensions.makeImmutable();
      return this.extensions;
    }

    public boolean isInitialized()
    {
      return (super.isInitialized()) && (extensionsAreInitialized());
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return AbstractMessage.Builder.mergeFieldFrom(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, this, paramInt);
    }

    public Map getAllFields()
    {
      Map localMap = GeneratedMessage.Builder.access$1100(this);
      localMap.putAll(this.extensions.getAllFields());
      return Collections.unmodifiableMap(localMap);
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        Object localObject = this.extensions.getField(paramFieldDescriptor);
        if (localObject == null)
        {
          if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
            return DynamicMessage.getDefaultInstance(paramFieldDescriptor.getMessageType());
          return paramFieldDescriptor.getDefaultValue();
        }
        return localObject;
      }
      return super.getField(paramFieldDescriptor);
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedFieldCount(paramFieldDescriptor);
      }
      return super.getRepeatedFieldCount(paramFieldDescriptor);
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedField(paramFieldDescriptor, paramInt);
      }
      return super.getRepeatedField(paramFieldDescriptor, paramInt);
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.hasField(paramFieldDescriptor);
      }
      return super.hasField(paramFieldDescriptor);
    }

    public ExtendableBuilder setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setField(paramFieldDescriptor, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.setField(paramFieldDescriptor, paramObject);
    }

    public ExtendableBuilder clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.clearField(paramFieldDescriptor);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.clearField(paramFieldDescriptor);
    }

    public ExtendableBuilder setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
    }

    public ExtendableBuilder addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.addRepeatedField(paramFieldDescriptor, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.addRepeatedField(paramFieldDescriptor, paramObject);
    }

    protected final void mergeExtensionFields(GeneratedMessage.ExtendableMessage paramExtendableMessage)
    {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(GeneratedMessage.ExtendableMessage.access$600(paramExtendableMessage));
      onChanged();
    }

    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }
  }

  public static abstract class ExtendableMessage extends GeneratedMessage
    implements GeneratedMessage.ExtendableMessageOrBuilder
  {
    private final FieldSet extensions;

    protected ExtendableMessage()
    {
      this.extensions = FieldSet.newFieldSet();
    }

    protected ExtendableMessage(GeneratedMessage.ExtendableBuilder paramExtendableBuilder)
    {
      super();
      this.extensions = paramExtendableBuilder.buildExtensions();
    }

    private void verifyExtensionContainingType(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getDescriptor().getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("Extension is for type \"" + paramGeneratedExtension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
    }

    public final boolean hasExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(paramGeneratedExtension.getDescriptor());
    }

    public final int getExtensionCount(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      return this.extensions.getRepeatedFieldCount(localFieldDescriptor);
    }

    public final Object getExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      Object localObject = this.extensions.getField(localFieldDescriptor);
      if (localObject == null)
      {
        if (localFieldDescriptor.isRepeated())
          return Collections.emptyList();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          return paramGeneratedExtension.getMessageDefaultInstance();
        return paramGeneratedExtension.fromReflectionType(localFieldDescriptor.getDefaultValue());
      }
      return paramGeneratedExtension.fromReflectionType(localObject);
    }

    public final Object getExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      return paramGeneratedExtension.singularFromReflectionType(this.extensions.getRepeatedField(localFieldDescriptor, paramInt));
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    public boolean isInitialized()
    {
      return (super.isInitialized()) && (extensionsAreInitialized());
    }

    protected ExtensionWriter newExtensionWriter()
    {
      return new ExtensionWriter(false, null);
    }

    protected ExtensionWriter newMessageSetExtensionWriter()
    {
      return new ExtensionWriter(true, null);
    }

    protected int extensionsSerializedSize()
    {
      return this.extensions.getSerializedSize();
    }

    protected int extensionsSerializedSizeAsMessageSet()
    {
      return this.extensions.getMessageSetSerializedSize();
    }

    protected Map getExtensionFields()
    {
      return this.extensions.getAllFields();
    }

    public Map getAllFields()
    {
      Map localMap = super.getAllFieldsMutable();
      localMap.putAll(getExtensionFields());
      return Collections.unmodifiableMap(localMap);
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.hasField(paramFieldDescriptor);
      }
      return super.hasField(paramFieldDescriptor);
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        Object localObject = this.extensions.getField(paramFieldDescriptor);
        if (localObject == null)
        {
          if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
            return DynamicMessage.getDefaultInstance(paramFieldDescriptor.getMessageType());
          return paramFieldDescriptor.getDefaultValue();
        }
        return localObject;
      }
      return super.getField(paramFieldDescriptor);
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedFieldCount(paramFieldDescriptor);
      }
      return super.getRepeatedFieldCount(paramFieldDescriptor);
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedField(paramFieldDescriptor, paramInt);
      }
      return super.getRepeatedField(paramFieldDescriptor, paramInt);
    }

    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }

    protected class ExtensionWriter
    {
      private final Iterator iter = GeneratedMessage.ExtendableMessage.this.extensions.iterator();
      private Map.Entry next;
      private final boolean messageSetWireFormat;

      private ExtensionWriter(boolean arg2)
      {
        if (this.iter.hasNext())
          this.next = ((Map.Entry)this.iter.next());
        boolean bool;
        this.messageSetWireFormat = bool;
      }

      public void writeUntil(int paramInt, CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        while ((this.next != null) && (((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < paramInt))
        {
          Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)this.next.getKey();
          if ((this.messageSetWireFormat) && (localFieldDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptor.isRepeated()))
            paramCodedOutputStream.writeMessageSetExtension(localFieldDescriptor.getNumber(), (Message)this.next.getValue());
          else
            FieldSet.writeField(localFieldDescriptor, this.next.getValue(), paramCodedOutputStream);
          if (this.iter.hasNext())
            this.next = ((Map.Entry)this.iter.next());
          else
            this.next = null;
        }
      }
    }
  }

  public static abstract interface ExtendableMessageOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension);

    public abstract int getExtensionCount(GeneratedMessage.GeneratedExtension paramGeneratedExtension);

    public abstract Object getExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension);

    public abstract Object getExtension(GeneratedMessage.GeneratedExtension paramGeneratedExtension, int paramInt);
  }

  public static abstract class Builder extends AbstractMessage.Builder
  {
    private GeneratedMessage.BuilderParent builderParent;
    private BuilderParentImpl meAsParent;
    private boolean isClean;
    private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();

    protected Builder()
    {
      this(null);
    }

    protected Builder(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      this.builderParent = paramBuilderParent;
    }

    void dispose()
    {
      this.builderParent = null;
    }

    protected void onBuilt()
    {
      if (this.builderParent != null)
        markClean();
    }

    protected void markClean()
    {
      this.isClean = true;
    }

    protected boolean isClean()
    {
      return this.isClean;
    }

    public Builder clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public Builder clear()
    {
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
      onChanged();
      return this;
    }

    protected abstract GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable();

    public Descriptors.Descriptor getDescriptorForType()
    {
      return internalGetFieldAccessorTable().descriptor;
    }

    public Map getAllFields()
    {
      return Collections.unmodifiableMap(getAllFieldsMutable());
    }

    private Map getAllFieldsMutable()
    {
      TreeMap localTreeMap = new TreeMap();
      Descriptors.Descriptor localDescriptor = internalGetFieldAccessorTable().descriptor;
      Iterator localIterator = localDescriptor.getFields().iterator();
      while (localIterator.hasNext())
      {
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
        if (localFieldDescriptor.isRepeated())
        {
          List localList = (List)getField(localFieldDescriptor);
          if (!localList.isEmpty())
            localTreeMap.put(localFieldDescriptor, localList);
        }
        else if (hasField(localFieldDescriptor))
        {
          localTreeMap.put(localFieldDescriptor, getField(localFieldDescriptor));
        }
      }
      return localTreeMap;
    }

    public Message.Builder newBuilderForField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return internalGetFieldAccessorTable().getField(paramFieldDescriptor).newBuilder();
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return internalGetFieldAccessorTable().getField(paramFieldDescriptor).has(this);
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      Object localObject = internalGetFieldAccessorTable().getField(paramFieldDescriptor).get(this);
      if (paramFieldDescriptor.isRepeated())
        return Collections.unmodifiableList((List)localObject);
      return localObject;
    }

    public Builder setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      internalGetFieldAccessorTable().getField(paramFieldDescriptor).set(this, paramObject);
      return this;
    }

    public Builder clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      internalGetFieldAccessorTable().getField(paramFieldDescriptor).clear(this);
      return this;
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeatedCount(this);
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeated(this, paramInt);
    }

    public Builder setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      internalGetFieldAccessorTable().getField(paramFieldDescriptor).setRepeated(this, paramInt, paramObject);
      return this;
    }

    public Builder addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      internalGetFieldAccessorTable().getField(paramFieldDescriptor).addRepeated(this, paramObject);
      return this;
    }

    public final Builder setUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = paramUnknownFieldSet;
      onChanged();
      return this;
    }

    public final Builder mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(paramUnknownFieldSet).build();
      onChanged();
      return this;
    }

    public boolean isInitialized()
    {
      Iterator localIterator1 = getDescriptorForType().getFields().iterator();
      while (localIterator1.hasNext())
      {
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator1.next();
        if ((localFieldDescriptor.isRequired()) && (!hasField(localFieldDescriptor)))
          return false;
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          if (localFieldDescriptor.isRepeated())
          {
            List localList = (List)getField(localFieldDescriptor);
            Iterator localIterator2 = localList.iterator();
            while (localIterator2.hasNext())
            {
              Message localMessage = (Message)localIterator2.next();
              if (!localMessage.isInitialized())
                return false;
            }
          }
          else if ((hasField(localFieldDescriptor)) && (!((Message)getField(localFieldDescriptor)).isInitialized()))
          {
            return false;
          }
      }
      return true;
    }

    public final UnknownFieldSet getUnknownFields()
    {
      return this.unknownFields;
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
    }

    protected GeneratedMessage.BuilderParent getParentForChildren()
    {
      if (this.meAsParent == null)
        this.meAsParent = new BuilderParentImpl(null);
      return this.meAsParent;
    }

    protected final void onChanged()
    {
      if ((this.isClean) && (this.builderParent != null))
      {
        this.builderParent.markDirty();
        this.isClean = false;
      }
    }

    private class BuilderParentImpl
      implements GeneratedMessage.BuilderParent
    {
      private BuilderParentImpl()
      {
      }

      public void markDirty()
      {
        GeneratedMessage.Builder.this.onChanged();
      }
    }
  }

  protected static abstract interface BuilderParent
  {
    public abstract void markDirty();
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.GeneratedMessage
 * JD-Core Version:    0.6.2
 */