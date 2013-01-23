package com.google.protobuf;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Descriptors
{
  private static String computeFullName(FileDescriptor paramFileDescriptor, Descriptor paramDescriptor, String paramString)
  {
    if (paramDescriptor != null)
      return paramDescriptor.getFullName() + '.' + paramString;
    if (paramFileDescriptor.getPackage().length() > 0)
      return paramFileDescriptor.getPackage() + '.' + paramString;
    return paramString;
  }

  private static final class DescriptorPool
  {
    private final DescriptorPool[] dependencies;
    private final Map descriptorsByName = new HashMap();
    private final Map fieldsByNumber = new HashMap();
    private final Map enumValuesByNumber = new HashMap();

    DescriptorPool(Descriptors.FileDescriptor[] paramArrayOfFileDescriptor)
    {
      this.dependencies = new DescriptorPool[paramArrayOfFileDescriptor.length];
      for (int i = 0; i < paramArrayOfFileDescriptor.length; i++)
        this.dependencies[i] = Descriptors.FileDescriptor.access$1200(paramArrayOfFileDescriptor[i]);
      for (Descriptors.FileDescriptor localFileDescriptor : paramArrayOfFileDescriptor)
        try
        {
          addPackage(localFileDescriptor.getPackage(), localFileDescriptor);
        }
        catch (Descriptors.DescriptorValidationException localDescriptorValidationException)
        {
          if (!$assertionsDisabled)
            throw new AssertionError();
        }
    }

    Descriptors.GenericDescriptor findSymbol(String paramString)
    {
      Descriptors.GenericDescriptor localGenericDescriptor = (Descriptors.GenericDescriptor)this.descriptorsByName.get(paramString);
      if (localGenericDescriptor != null)
        return localGenericDescriptor;
      for (DescriptorPool localDescriptorPool : this.dependencies)
      {
        localGenericDescriptor = (Descriptors.GenericDescriptor)localDescriptorPool.descriptorsByName.get(paramString);
        if (localGenericDescriptor != null)
          return localGenericDescriptor;
      }
      return null;
    }

    Descriptors.GenericDescriptor lookupSymbol(String paramString, Descriptors.GenericDescriptor paramGenericDescriptor)
      throws Descriptors.DescriptorValidationException
    {
      Descriptors.GenericDescriptor localGenericDescriptor;
      if (paramString.startsWith("."))
      {
        localGenericDescriptor = findSymbol(paramString.substring(1));
      }
      else
      {
        int i = paramString.indexOf('.');
        String str;
        if (i == -1)
          str = paramString;
        else
          str = paramString.substring(0, i);
        StringBuilder localStringBuilder = new StringBuilder(paramGenericDescriptor.getFullName());
        while (true)
        {
          int j = localStringBuilder.lastIndexOf(".");
          if (j == -1)
          {
            localGenericDescriptor = findSymbol(paramString);
            break;
          }
          localStringBuilder.setLength(j + 1);
          localStringBuilder.append(str);
          localGenericDescriptor = findSymbol(localStringBuilder.toString());
          if (localGenericDescriptor != null)
          {
            if (i == -1)
              break;
            localStringBuilder.setLength(j + 1);
            localStringBuilder.append(paramString);
            localGenericDescriptor = findSymbol(localStringBuilder.toString());
            break;
          }
          localStringBuilder.setLength(j);
        }
      }
      if (localGenericDescriptor == null)
        throw new Descriptors.DescriptorValidationException(paramGenericDescriptor, '"' + paramString + "\" is not defined.", null);
      return localGenericDescriptor;
    }

    void addSymbol(Descriptors.GenericDescriptor paramGenericDescriptor)
      throws Descriptors.DescriptorValidationException
    {
      validateSymbolName(paramGenericDescriptor);
      String str = paramGenericDescriptor.getFullName();
      int i = str.lastIndexOf('.');
      Descriptors.GenericDescriptor localGenericDescriptor = (Descriptors.GenericDescriptor)this.descriptorsByName.put(str, paramGenericDescriptor);
      if (localGenericDescriptor != null)
      {
        this.descriptorsByName.put(str, localGenericDescriptor);
        if (paramGenericDescriptor.getFile() == localGenericDescriptor.getFile())
        {
          if (i == -1)
            throw new Descriptors.DescriptorValidationException(paramGenericDescriptor, '"' + str + "\" is already defined.", null);
          throw new Descriptors.DescriptorValidationException(paramGenericDescriptor, '"' + str.substring(i + 1) + "\" is already defined in \"" + str.substring(0, i) + "\".", null);
        }
        throw new Descriptors.DescriptorValidationException(paramGenericDescriptor, '"' + str + "\" is already defined in file \"" + localGenericDescriptor.getFile().getName() + "\".", null);
      }
    }

    void addPackage(String paramString, Descriptors.FileDescriptor paramFileDescriptor)
      throws Descriptors.DescriptorValidationException
    {
      int i = paramString.lastIndexOf('.');
      String str;
      if (i == -1)
      {
        str = paramString;
      }
      else
      {
        addPackage(paramString.substring(0, i), paramFileDescriptor);
        str = paramString.substring(i + 1);
      }
      Descriptors.GenericDescriptor localGenericDescriptor = (Descriptors.GenericDescriptor)this.descriptorsByName.put(paramString, new PackageDescriptor(str, paramString, paramFileDescriptor));
      if (localGenericDescriptor != null)
      {
        this.descriptorsByName.put(paramString, localGenericDescriptor);
        if (!(localGenericDescriptor instanceof PackageDescriptor))
          throw new Descriptors.DescriptorValidationException(paramFileDescriptor, '"' + str + "\" is already defined (as something other than a " + "package) in file \"" + localGenericDescriptor.getFile().getName() + "\".", null);
      }
    }

    void addFieldByNumber(Descriptors.FieldDescriptor paramFieldDescriptor)
      throws Descriptors.DescriptorValidationException
    {
      DescriptorIntPair localDescriptorIntPair = new DescriptorIntPair(paramFieldDescriptor.getContainingType(), paramFieldDescriptor.getNumber());
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)this.fieldsByNumber.put(localDescriptorIntPair, paramFieldDescriptor);
      if (localFieldDescriptor != null)
      {
        this.fieldsByNumber.put(localDescriptorIntPair, localFieldDescriptor);
        throw new Descriptors.DescriptorValidationException(paramFieldDescriptor, "Field number " + paramFieldDescriptor.getNumber() + "has already been used in \"" + paramFieldDescriptor.getContainingType().getFullName() + "\" by field \"" + localFieldDescriptor.getName() + "\".", null);
      }
    }

    void addEnumValueByNumber(Descriptors.EnumValueDescriptor paramEnumValueDescriptor)
    {
      DescriptorIntPair localDescriptorIntPair = new DescriptorIntPair(paramEnumValueDescriptor.getType(), paramEnumValueDescriptor.getNumber());
      Descriptors.EnumValueDescriptor localEnumValueDescriptor = (Descriptors.EnumValueDescriptor)this.enumValuesByNumber.put(localDescriptorIntPair, paramEnumValueDescriptor);
      if (localEnumValueDescriptor != null)
        this.enumValuesByNumber.put(localDescriptorIntPair, localEnumValueDescriptor);
    }

    static void validateSymbolName(Descriptors.GenericDescriptor paramGenericDescriptor)
      throws Descriptors.DescriptorValidationException
    {
      String str = paramGenericDescriptor.getName();
      if (str.length() == 0)
        throw new Descriptors.DescriptorValidationException(paramGenericDescriptor, "Missing name.", null);
      int i = 1;
      for (int j = 0; j < str.length(); j++)
      {
        char c = str.charAt(j);
        if (c >= '')
          i = 0;
        if ((!Character.isLetter(c)) && (c != '_') && ((!Character.isDigit(c)) || (j <= 0)))
          i = 0;
      }
      if (i == 0)
        throw new Descriptors.DescriptorValidationException(paramGenericDescriptor, '"' + str + "\" is not a valid identifier.", null);
    }

    private static final class DescriptorIntPair
    {
      private final Descriptors.GenericDescriptor descriptor;
      private final int number;

      DescriptorIntPair(Descriptors.GenericDescriptor paramGenericDescriptor, int paramInt)
      {
        this.descriptor = paramGenericDescriptor;
        this.number = paramInt;
      }

      public int hashCode()
      {
        return this.descriptor.hashCode() * 65535 + this.number;
      }

      public boolean equals(Object paramObject)
      {
        if (!(paramObject instanceof DescriptorIntPair))
          return false;
        DescriptorIntPair localDescriptorIntPair = (DescriptorIntPair)paramObject;
        return (this.descriptor == localDescriptorIntPair.descriptor) && (this.number == localDescriptorIntPair.number);
      }
    }

    private static final class PackageDescriptor
      implements Descriptors.GenericDescriptor
    {
      private final String name;
      private final String fullName;
      private final Descriptors.FileDescriptor file;

      public Message toProto()
      {
        return this.file.toProto();
      }

      public String getName()
      {
        return this.name;
      }

      public String getFullName()
      {
        return this.fullName;
      }

      public Descriptors.FileDescriptor getFile()
      {
        return this.file;
      }

      PackageDescriptor(String paramString1, String paramString2, Descriptors.FileDescriptor paramFileDescriptor)
      {
        this.file = paramFileDescriptor;
        this.fullName = paramString2;
        this.name = paramString1;
      }
    }
  }

  public static class DescriptorValidationException extends Exception
  {
    private static final long serialVersionUID = 5750205775490483148L;
    private final String name;
    private final Message proto;
    private final String description;

    public String getProblemSymbolName()
    {
      return this.name;
    }

    public Message getProblemProto()
    {
      return this.proto;
    }

    public String getDescription()
    {
      return this.description;
    }

    private DescriptorValidationException(Descriptors.GenericDescriptor paramGenericDescriptor, String paramString)
    {
      super();
      this.name = paramGenericDescriptor.getFullName();
      this.proto = paramGenericDescriptor.toProto();
      this.description = paramString;
    }

    private DescriptorValidationException(Descriptors.GenericDescriptor paramGenericDescriptor, String paramString, Throwable paramThrowable)
    {
      this(paramGenericDescriptor, paramString);
      initCause(paramThrowable);
    }

    private DescriptorValidationException(Descriptors.FileDescriptor paramFileDescriptor, String paramString)
    {
      super();
      this.name = paramFileDescriptor.getName();
      this.proto = paramFileDescriptor.toProto();
      this.description = paramString;
    }
  }

  private static abstract interface GenericDescriptor
  {
    public abstract Message toProto();

    public abstract String getName();

    public abstract String getFullName();

    public abstract Descriptors.FileDescriptor getFile();
  }

  public static final class MethodDescriptor
    implements Descriptors.GenericDescriptor
  {
    private final int index;
    private DescriptorProtos.MethodDescriptorProto proto;
    private final String fullName;
    private final Descriptors.FileDescriptor file;
    private final Descriptors.ServiceDescriptor service;
    private Descriptors.Descriptor inputType;
    private Descriptors.Descriptor outputType;

    public int getIndex()
    {
      return this.index;
    }

    public DescriptorProtos.MethodDescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public String getFullName()
    {
      return this.fullName;
    }

    public Descriptors.FileDescriptor getFile()
    {
      return this.file;
    }

    public Descriptors.ServiceDescriptor getService()
    {
      return this.service;
    }

    public Descriptors.Descriptor getInputType()
    {
      return this.inputType;
    }

    public Descriptors.Descriptor getOutputType()
    {
      return this.outputType;
    }

    public DescriptorProtos.MethodOptions getOptions()
    {
      return this.proto.getOptions();
    }

    private MethodDescriptor(DescriptorProtos.MethodDescriptorProto paramMethodDescriptorProto, Descriptors.FileDescriptor paramFileDescriptor, Descriptors.ServiceDescriptor paramServiceDescriptor, int paramInt)
      throws Descriptors.DescriptorValidationException
    {
      this.index = paramInt;
      this.proto = paramMethodDescriptorProto;
      this.file = paramFileDescriptor;
      this.service = paramServiceDescriptor;
      this.fullName = (paramServiceDescriptor.getFullName() + '.' + paramMethodDescriptorProto.getName());
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addSymbol(this);
    }

    private void crossLink()
      throws Descriptors.DescriptorValidationException
    {
      Descriptors.GenericDescriptor localGenericDescriptor1 = Descriptors.FileDescriptor.access$1200(this.file).lookupSymbol(this.proto.getInputType(), this);
      if (!(localGenericDescriptor1 instanceof Descriptors.Descriptor))
        throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getInputType() + "\" is not a message type.", null);
      this.inputType = ((Descriptors.Descriptor)localGenericDescriptor1);
      Descriptors.GenericDescriptor localGenericDescriptor2 = Descriptors.FileDescriptor.access$1200(this.file).lookupSymbol(this.proto.getOutputType(), this);
      if (!(localGenericDescriptor2 instanceof Descriptors.Descriptor))
        throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getOutputType() + "\" is not a message type.", null);
      this.outputType = ((Descriptors.Descriptor)localGenericDescriptor2);
    }

    private void setProto(DescriptorProtos.MethodDescriptorProto paramMethodDescriptorProto)
    {
      this.proto = paramMethodDescriptorProto;
    }
  }

  public static final class ServiceDescriptor
    implements Descriptors.GenericDescriptor
  {
    private final int index;
    private DescriptorProtos.ServiceDescriptorProto proto;
    private final String fullName;
    private final Descriptors.FileDescriptor file;
    private Descriptors.MethodDescriptor[] methods;

    public int getIndex()
    {
      return this.index;
    }

    public DescriptorProtos.ServiceDescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public String getFullName()
    {
      return this.fullName;
    }

    public Descriptors.FileDescriptor getFile()
    {
      return this.file;
    }

    public DescriptorProtos.ServiceOptions getOptions()
    {
      return this.proto.getOptions();
    }

    public List getMethods()
    {
      return Collections.unmodifiableList(Arrays.asList(this.methods));
    }

    public Descriptors.MethodDescriptor findMethodByName(String paramString)
    {
      Descriptors.GenericDescriptor localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).findSymbol(this.fullName + '.' + paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.MethodDescriptor)))
        return (Descriptors.MethodDescriptor)localGenericDescriptor;
      return null;
    }

    private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto paramServiceDescriptorProto, Descriptors.FileDescriptor paramFileDescriptor, int paramInt)
      throws Descriptors.DescriptorValidationException
    {
      this.index = paramInt;
      this.proto = paramServiceDescriptorProto;
      this.fullName = Descriptors.computeFullName(paramFileDescriptor, null, paramServiceDescriptorProto.getName());
      this.file = paramFileDescriptor;
      this.methods = new Descriptors.MethodDescriptor[paramServiceDescriptorProto.getMethodCount()];
      for (int i = 0; i < paramServiceDescriptorProto.getMethodCount(); i++)
        this.methods[i] = new Descriptors.MethodDescriptor(paramServiceDescriptorProto.getMethod(i), paramFileDescriptor, this, i, null);
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addSymbol(this);
    }

    private void crossLink()
      throws Descriptors.DescriptorValidationException
    {
      for (Descriptors.MethodDescriptor localMethodDescriptor : this.methods)
        localMethodDescriptor.crossLink();
    }

    private void setProto(DescriptorProtos.ServiceDescriptorProto paramServiceDescriptorProto)
    {
      this.proto = paramServiceDescriptorProto;
      for (int i = 0; i < this.methods.length; i++)
        this.methods[i].setProto(paramServiceDescriptorProto.getMethod(i));
    }
  }

  public static final class EnumValueDescriptor
    implements Descriptors.GenericDescriptor, Internal.EnumLite
  {
    private final int index;
    private DescriptorProtos.EnumValueDescriptorProto proto;
    private final String fullName;
    private final Descriptors.FileDescriptor file;
    private final Descriptors.EnumDescriptor type;

    public int getIndex()
    {
      return this.index;
    }

    public DescriptorProtos.EnumValueDescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public int getNumber()
    {
      return this.proto.getNumber();
    }

    public String getFullName()
    {
      return this.fullName;
    }

    public Descriptors.FileDescriptor getFile()
    {
      return this.file;
    }

    public Descriptors.EnumDescriptor getType()
    {
      return this.type;
    }

    public DescriptorProtos.EnumValueOptions getOptions()
    {
      return this.proto.getOptions();
    }

    private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto paramEnumValueDescriptorProto, Descriptors.FileDescriptor paramFileDescriptor, Descriptors.EnumDescriptor paramEnumDescriptor, int paramInt)
      throws Descriptors.DescriptorValidationException
    {
      this.index = paramInt;
      this.proto = paramEnumValueDescriptorProto;
      this.file = paramFileDescriptor;
      this.type = paramEnumDescriptor;
      this.fullName = (paramEnumDescriptor.getFullName() + '.' + paramEnumValueDescriptorProto.getName());
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addSymbol(this);
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addEnumValueByNumber(this);
    }

    private void setProto(DescriptorProtos.EnumValueDescriptorProto paramEnumValueDescriptorProto)
    {
      this.proto = paramEnumValueDescriptorProto;
    }
  }

  public static final class EnumDescriptor
    implements Descriptors.GenericDescriptor, Internal.EnumLiteMap
  {
    private final int index;
    private DescriptorProtos.EnumDescriptorProto proto;
    private final String fullName;
    private final Descriptors.FileDescriptor file;
    private final Descriptors.Descriptor containingType;
    private Descriptors.EnumValueDescriptor[] values;

    public int getIndex()
    {
      return this.index;
    }

    public DescriptorProtos.EnumDescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public String getFullName()
    {
      return this.fullName;
    }

    public Descriptors.FileDescriptor getFile()
    {
      return this.file;
    }

    public Descriptors.Descriptor getContainingType()
    {
      return this.containingType;
    }

    public DescriptorProtos.EnumOptions getOptions()
    {
      return this.proto.getOptions();
    }

    public List getValues()
    {
      return Collections.unmodifiableList(Arrays.asList(this.values));
    }

    public Descriptors.EnumValueDescriptor findValueByName(String paramString)
    {
      Descriptors.GenericDescriptor localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).findSymbol(this.fullName + '.' + paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.EnumValueDescriptor)))
        return (Descriptors.EnumValueDescriptor)localGenericDescriptor;
      return null;
    }

    public Descriptors.EnumValueDescriptor findValueByNumber(int paramInt)
    {
      return (Descriptors.EnumValueDescriptor)Descriptors.FileDescriptor.access$1200(this.file).enumValuesByNumber.get(new Descriptors.DescriptorPool.DescriptorIntPair(this, paramInt));
    }

    private EnumDescriptor(DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto, Descriptors.FileDescriptor paramFileDescriptor, Descriptors.Descriptor paramDescriptor, int paramInt)
      throws Descriptors.DescriptorValidationException
    {
      this.index = paramInt;
      this.proto = paramEnumDescriptorProto;
      this.fullName = Descriptors.computeFullName(paramFileDescriptor, paramDescriptor, paramEnumDescriptorProto.getName());
      this.file = paramFileDescriptor;
      this.containingType = paramDescriptor;
      if (paramEnumDescriptorProto.getValueCount() == 0)
        throw new Descriptors.DescriptorValidationException(this, "Enums must contain at least one value.", null);
      this.values = new Descriptors.EnumValueDescriptor[paramEnumDescriptorProto.getValueCount()];
      for (int i = 0; i < paramEnumDescriptorProto.getValueCount(); i++)
        this.values[i] = new Descriptors.EnumValueDescriptor(paramEnumDescriptorProto.getValue(i), paramFileDescriptor, this, i, null);
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addSymbol(this);
    }

    private void setProto(DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
    {
      this.proto = paramEnumDescriptorProto;
      for (int i = 0; i < this.values.length; i++)
        this.values[i].setProto(paramEnumDescriptorProto.getValue(i));
    }
  }

  public static final class FieldDescriptor
    implements Descriptors.GenericDescriptor, FieldSet.FieldDescriptorLite, Comparable
  {
    private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
    private final int index;
    private DescriptorProtos.FieldDescriptorProto proto;
    private final String fullName;
    private final Descriptors.FileDescriptor file;
    private final Descriptors.Descriptor extensionScope;
    private Type type;
    private Descriptors.Descriptor containingType;
    private Descriptors.Descriptor messageType;
    private Descriptors.EnumDescriptor enumType;
    private Object defaultValue;

    public int getIndex()
    {
      return this.index;
    }

    public DescriptorProtos.FieldDescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public int getNumber()
    {
      return this.proto.getNumber();
    }

    public String getFullName()
    {
      return this.fullName;
    }

    public JavaType getJavaType()
    {
      return this.type.getJavaType();
    }

    public WireFormat.JavaType getLiteJavaType()
    {
      return getLiteType().getJavaType();
    }

    public Descriptors.FileDescriptor getFile()
    {
      return this.file;
    }

    public Type getType()
    {
      return this.type;
    }

    public WireFormat.FieldType getLiteType()
    {
      return table[this.type.ordinal()];
    }

    public boolean isRequired()
    {
      return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
    }

    public boolean isOptional()
    {
      return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
    }

    public boolean isRepeated()
    {
      return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
    }

    public boolean isPacked()
    {
      return getOptions().getPacked();
    }

    public boolean isPackable()
    {
      return (isRepeated()) && (getLiteType().isPackable());
    }

    public boolean hasDefaultValue()
    {
      return this.proto.hasDefaultValue();
    }

    public Object getDefaultValue()
    {
      if (getJavaType() == JavaType.MESSAGE)
        throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
      return this.defaultValue;
    }

    public DescriptorProtos.FieldOptions getOptions()
    {
      return this.proto.getOptions();
    }

    public boolean isExtension()
    {
      return this.proto.hasExtendee();
    }

    public Descriptors.Descriptor getContainingType()
    {
      return this.containingType;
    }

    public Descriptors.Descriptor getExtensionScope()
    {
      if (!isExtension())
        throw new UnsupportedOperationException("This field is not an extension.");
      return this.extensionScope;
    }

    public Descriptors.Descriptor getMessageType()
    {
      if (getJavaType() != JavaType.MESSAGE)
        throw new UnsupportedOperationException("This field is not of message type.");
      return this.messageType;
    }

    public Descriptors.EnumDescriptor getEnumType()
    {
      if (getJavaType() != JavaType.ENUM)
        throw new UnsupportedOperationException("This field is not of enum type.");
      return this.enumType;
    }

    public int compareTo(FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.containingType != this.containingType)
        throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
      return getNumber() - paramFieldDescriptor.getNumber();
    }

    private FieldDescriptor(DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto, Descriptors.FileDescriptor paramFileDescriptor, Descriptors.Descriptor paramDescriptor, int paramInt, boolean paramBoolean)
      throws Descriptors.DescriptorValidationException
    {
      this.index = paramInt;
      this.proto = paramFieldDescriptorProto;
      this.fullName = Descriptors.computeFullName(paramFileDescriptor, paramDescriptor, paramFieldDescriptorProto.getName());
      this.file = paramFileDescriptor;
      if (paramFieldDescriptorProto.hasType())
        this.type = Type.valueOf(paramFieldDescriptorProto.getType());
      if (getNumber() <= 0)
        throw new Descriptors.DescriptorValidationException(this, "Field numbers must be positive integers.", null);
      if ((paramFieldDescriptorProto.getOptions().getPacked()) && (!isPackable()))
        throw new Descriptors.DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.", null);
      if (paramBoolean)
      {
        if (!paramFieldDescriptorProto.hasExtendee())
          throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.", null);
        this.containingType = null;
        if (paramDescriptor != null)
          this.extensionScope = paramDescriptor;
        else
          this.extensionScope = null;
      }
      else
      {
        if (paramFieldDescriptorProto.hasExtendee())
          throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.", null);
        this.containingType = paramDescriptor;
        this.extensionScope = null;
      }
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addSymbol(this);
    }

    private void crossLink()
      throws Descriptors.DescriptorValidationException
    {
      Descriptors.GenericDescriptor localGenericDescriptor;
      if (this.proto.hasExtendee())
      {
        localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).lookupSymbol(this.proto.getExtendee(), this);
        if (!(localGenericDescriptor instanceof Descriptors.Descriptor))
          throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getExtendee() + "\" is not a message type.", null);
        this.containingType = ((Descriptors.Descriptor)localGenericDescriptor);
        if (!getContainingType().isExtensionNumber(getNumber()))
          throw new Descriptors.DescriptorValidationException(this, '"' + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number.", null);
      }
      if (this.proto.hasTypeName())
      {
        localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).lookupSymbol(this.proto.getTypeName(), this);
        if (!this.proto.hasType())
          if ((localGenericDescriptor instanceof Descriptors.Descriptor))
            this.type = Type.MESSAGE;
          else if ((localGenericDescriptor instanceof Descriptors.EnumDescriptor))
            this.type = Type.ENUM;
          else
            throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not a type.", null);
        if (getJavaType() == JavaType.MESSAGE)
        {
          if (!(localGenericDescriptor instanceof Descriptors.Descriptor))
            throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not a message type.", null);
          this.messageType = ((Descriptors.Descriptor)localGenericDescriptor);
          if (this.proto.hasDefaultValue())
            throw new Descriptors.DescriptorValidationException(this, "Messages can't have default values.", null);
        }
        else if (getJavaType() == JavaType.ENUM)
        {
          if (!(localGenericDescriptor instanceof Descriptors.EnumDescriptor))
            throw new Descriptors.DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not an enum type.", null);
          this.enumType = ((Descriptors.EnumDescriptor)localGenericDescriptor);
        }
        else
        {
          throw new Descriptors.DescriptorValidationException(this, "Field with primitive type has type_name.", null);
        }
      }
      else if ((getJavaType() == JavaType.MESSAGE) || (getJavaType() == JavaType.ENUM))
      {
        throw new Descriptors.DescriptorValidationException(this, "Field with message or enum type missing type_name.", null);
      }
      if (this.proto.hasDefaultValue())
      {
        if (isRepeated())
          throw new Descriptors.DescriptorValidationException(this, "Repeated fields cannot have default values.", null);
        try
        {
          switch (Descriptors.1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[getType().ordinal()])
          {
          case 1:
          case 2:
          case 3:
            this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
            break;
          case 4:
          case 5:
            this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
            break;
          case 6:
          case 7:
          case 8:
            this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
            break;
          case 9:
          case 10:
            this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
            break;
          case 11:
            if (this.proto.getDefaultValue().equals("inf"))
              this.defaultValue = Float.valueOf((1.0F / 1.0F));
            else if (this.proto.getDefaultValue().equals("-inf"))
              this.defaultValue = Float.valueOf((1.0F / -1.0F));
            else if (this.proto.getDefaultValue().equals("nan"))
              this.defaultValue = Float.valueOf((0.0F / 0.0F));
            else
              this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
            break;
          case 12:
            if (this.proto.getDefaultValue().equals("inf"))
              this.defaultValue = Double.valueOf((1.0D / 0.0D));
            else if (this.proto.getDefaultValue().equals("-inf"))
              this.defaultValue = Double.valueOf((-1.0D / 0.0D));
            else if (this.proto.getDefaultValue().equals("nan"))
              this.defaultValue = Double.valueOf((0.0D / 0.0D));
            else
              this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
            break;
          case 13:
            this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
            break;
          case 14:
            this.defaultValue = this.proto.getDefaultValue();
            break;
          case 15:
            try
            {
              this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
            }
            catch (TextFormat.InvalidEscapeSequenceException localInvalidEscapeSequenceException)
            {
              throw new Descriptors.DescriptorValidationException(this, "Couldn't parse default value: " + localInvalidEscapeSequenceException.getMessage(), localInvalidEscapeSequenceException, null);
            }
          case 16:
            this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
            if (this.defaultValue == null)
              throw new Descriptors.DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '"', null);
            break;
          case 17:
          case 18:
            throw new Descriptors.DescriptorValidationException(this, "Message type had default value.", null);
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new Descriptors.DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '"', localNumberFormatException, null);
        }
      }
      else if (isRepeated())
      {
        this.defaultValue = Collections.emptyList();
      }
      else
      {
        switch (Descriptors.1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[getJavaType().ordinal()])
        {
        case 1:
          this.defaultValue = this.enumType.getValues().get(0);
          break;
        case 2:
          this.defaultValue = null;
          break;
        default:
          this.defaultValue = getJavaType().defaultDefault;
        }
      }
      if (!isExtension())
        Descriptors.FileDescriptor.access$1200(this.file).addFieldByNumber(this);
      if ((this.containingType != null) && (this.containingType.getOptions().getMessageSetWireFormat()))
        if (isExtension())
        {
          if ((!isOptional()) || (getType() != Type.MESSAGE))
            throw new Descriptors.DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.", null);
        }
        else
          throw new Descriptors.DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.", null);
    }

    private void setProto(DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
    {
      this.proto = paramFieldDescriptorProto;
    }

    public MessageLite.Builder internalMergeFrom(MessageLite.Builder paramBuilder, MessageLite paramMessageLite)
    {
      return ((Message.Builder)paramBuilder).mergeFrom((Message)paramMessageLite);
    }

    static
    {
      if (Type.values().length != DescriptorProtos.FieldDescriptorProto.Type.values().length)
        throw new RuntimeException("descriptor.proto has a new declared type but Desrciptors.java wasn't updated.");
    }

    public static enum JavaType
    {
      INT(Integer.valueOf(0)), LONG(Long.valueOf(0L)), FLOAT(Float.valueOf(0.0F)), DOUBLE(Double.valueOf(0.0D)), BOOLEAN(Boolean.valueOf(false)), STRING(""), BYTE_STRING(ByteString.EMPTY), ENUM(null), MESSAGE(null);

      private final Object defaultDefault;

      private JavaType(Object arg3)
      {
        Object localObject;
        this.defaultDefault = localObject;
      }
    }

    public static enum Type
    {
      DOUBLE(Descriptors.FieldDescriptor.JavaType.DOUBLE), FLOAT(Descriptors.FieldDescriptor.JavaType.FLOAT), INT64(Descriptors.FieldDescriptor.JavaType.LONG), UINT64(Descriptors.FieldDescriptor.JavaType.LONG), INT32(Descriptors.FieldDescriptor.JavaType.INT), FIXED64(Descriptors.FieldDescriptor.JavaType.LONG), FIXED32(Descriptors.FieldDescriptor.JavaType.INT), BOOL(Descriptors.FieldDescriptor.JavaType.BOOLEAN), STRING(Descriptors.FieldDescriptor.JavaType.STRING), GROUP(Descriptors.FieldDescriptor.JavaType.MESSAGE), MESSAGE(Descriptors.FieldDescriptor.JavaType.MESSAGE), BYTES(Descriptors.FieldDescriptor.JavaType.BYTE_STRING), UINT32(Descriptors.FieldDescriptor.JavaType.INT), ENUM(Descriptors.FieldDescriptor.JavaType.ENUM), SFIXED32(Descriptors.FieldDescriptor.JavaType.INT), SFIXED64(Descriptors.FieldDescriptor.JavaType.LONG), SINT32(Descriptors.FieldDescriptor.JavaType.INT), SINT64(Descriptors.FieldDescriptor.JavaType.LONG);

      private Descriptors.FieldDescriptor.JavaType javaType;

      private Type(Descriptors.FieldDescriptor.JavaType arg3)
      {
        Object localObject;
        this.javaType = localObject;
      }

      public DescriptorProtos.FieldDescriptorProto.Type toProto()
      {
        return DescriptorProtos.FieldDescriptorProto.Type.valueOf(ordinal() + 1);
      }

      public Descriptors.FieldDescriptor.JavaType getJavaType()
      {
        return this.javaType;
      }

      public static Type valueOf(DescriptorProtos.FieldDescriptorProto.Type paramType)
      {
        return values()[(paramType.getNumber() - 1)];
      }
    }
  }

  public static final class Descriptor
    implements Descriptors.GenericDescriptor
  {
    private final int index;
    private DescriptorProtos.DescriptorProto proto;
    private final String fullName;
    private final Descriptors.FileDescriptor file;
    private final Descriptor containingType;
    private final Descriptor[] nestedTypes;
    private final Descriptors.EnumDescriptor[] enumTypes;
    private final Descriptors.FieldDescriptor[] fields;
    private final Descriptors.FieldDescriptor[] extensions;

    public int getIndex()
    {
      return this.index;
    }

    public DescriptorProtos.DescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public String getFullName()
    {
      return this.fullName;
    }

    public Descriptors.FileDescriptor getFile()
    {
      return this.file;
    }

    public Descriptor getContainingType()
    {
      return this.containingType;
    }

    public DescriptorProtos.MessageOptions getOptions()
    {
      return this.proto.getOptions();
    }

    public List getFields()
    {
      return Collections.unmodifiableList(Arrays.asList(this.fields));
    }

    public List getExtensions()
    {
      return Collections.unmodifiableList(Arrays.asList(this.extensions));
    }

    public List getNestedTypes()
    {
      return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
    }

    public List getEnumTypes()
    {
      return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
    }

    public boolean isExtensionNumber(int paramInt)
    {
      Iterator localIterator = this.proto.getExtensionRangeList().iterator();
      while (localIterator.hasNext())
      {
        DescriptorProtos.DescriptorProto.ExtensionRange localExtensionRange = (DescriptorProtos.DescriptorProto.ExtensionRange)localIterator.next();
        if ((localExtensionRange.getStart() <= paramInt) && (paramInt < localExtensionRange.getEnd()))
          return true;
      }
      return false;
    }

    public Descriptors.FieldDescriptor findFieldByName(String paramString)
    {
      Descriptors.GenericDescriptor localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).findSymbol(this.fullName + '.' + paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.FieldDescriptor)))
        return (Descriptors.FieldDescriptor)localGenericDescriptor;
      return null;
    }

    public Descriptors.FieldDescriptor findFieldByNumber(int paramInt)
    {
      return (Descriptors.FieldDescriptor)Descriptors.FileDescriptor.access$1200(this.file).fieldsByNumber.get(new Descriptors.DescriptorPool.DescriptorIntPair(this, paramInt));
    }

    public Descriptor findNestedTypeByName(String paramString)
    {
      Descriptors.GenericDescriptor localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).findSymbol(this.fullName + '.' + paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptor)))
        return (Descriptor)localGenericDescriptor;
      return null;
    }

    public Descriptors.EnumDescriptor findEnumTypeByName(String paramString)
    {
      Descriptors.GenericDescriptor localGenericDescriptor = Descriptors.FileDescriptor.access$1200(this.file).findSymbol(this.fullName + '.' + paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.EnumDescriptor)))
        return (Descriptors.EnumDescriptor)localGenericDescriptor;
      return null;
    }

    private Descriptor(DescriptorProtos.DescriptorProto paramDescriptorProto, Descriptors.FileDescriptor paramFileDescriptor, Descriptor paramDescriptor, int paramInt)
      throws Descriptors.DescriptorValidationException
    {
      this.index = paramInt;
      this.proto = paramDescriptorProto;
      this.fullName = Descriptors.computeFullName(paramFileDescriptor, paramDescriptor, paramDescriptorProto.getName());
      this.file = paramFileDescriptor;
      this.containingType = paramDescriptor;
      this.nestedTypes = new Descriptor[paramDescriptorProto.getNestedTypeCount()];
      for (int i = 0; i < paramDescriptorProto.getNestedTypeCount(); i++)
        this.nestedTypes[i] = new Descriptor(paramDescriptorProto.getNestedType(i), paramFileDescriptor, this, i);
      this.enumTypes = new Descriptors.EnumDescriptor[paramDescriptorProto.getEnumTypeCount()];
      for (i = 0; i < paramDescriptorProto.getEnumTypeCount(); i++)
        this.enumTypes[i] = new Descriptors.EnumDescriptor(paramDescriptorProto.getEnumType(i), paramFileDescriptor, this, i, null);
      this.fields = new Descriptors.FieldDescriptor[paramDescriptorProto.getFieldCount()];
      for (i = 0; i < paramDescriptorProto.getFieldCount(); i++)
        this.fields[i] = new Descriptors.FieldDescriptor(paramDescriptorProto.getField(i), paramFileDescriptor, this, i, false, null);
      this.extensions = new Descriptors.FieldDescriptor[paramDescriptorProto.getExtensionCount()];
      for (i = 0; i < paramDescriptorProto.getExtensionCount(); i++)
        this.extensions[i] = new Descriptors.FieldDescriptor(paramDescriptorProto.getExtension(i), paramFileDescriptor, this, i, true, null);
      Descriptors.FileDescriptor.access$1200(paramFileDescriptor).addSymbol(this);
    }

    private void crossLink()
      throws Descriptors.DescriptorValidationException
    {
      Descriptors.FieldDescriptor localFieldDescriptor;
      for (localFieldDescriptor : this.nestedTypes)
        localFieldDescriptor.crossLink();
      for (localFieldDescriptor : this.fields)
        localFieldDescriptor.crossLink();
      for (localFieldDescriptor : this.extensions)
        localFieldDescriptor.crossLink();
    }

    private void setProto(DescriptorProtos.DescriptorProto paramDescriptorProto)
    {
      this.proto = paramDescriptorProto;
      for (int i = 0; i < this.nestedTypes.length; i++)
        this.nestedTypes[i].setProto(paramDescriptorProto.getNestedType(i));
      for (i = 0; i < this.enumTypes.length; i++)
        this.enumTypes[i].setProto(paramDescriptorProto.getEnumType(i));
      for (i = 0; i < this.fields.length; i++)
        this.fields[i].setProto(paramDescriptorProto.getField(i));
      for (i = 0; i < this.extensions.length; i++)
        this.extensions[i].setProto(paramDescriptorProto.getExtension(i));
    }
  }

  public static final class FileDescriptor
  {
    private DescriptorProtos.FileDescriptorProto proto;
    private final Descriptors.Descriptor[] messageTypes;
    private final Descriptors.EnumDescriptor[] enumTypes;
    private final Descriptors.ServiceDescriptor[] services;
    private final Descriptors.FieldDescriptor[] extensions;
    private final FileDescriptor[] dependencies;
    private final Descriptors.DescriptorPool pool;

    public DescriptorProtos.FileDescriptorProto toProto()
    {
      return this.proto;
    }

    public String getName()
    {
      return this.proto.getName();
    }

    public String getPackage()
    {
      return this.proto.getPackage();
    }

    public DescriptorProtos.FileOptions getOptions()
    {
      return this.proto.getOptions();
    }

    public List getMessageTypes()
    {
      return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
    }

    public List getEnumTypes()
    {
      return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
    }

    public List getServices()
    {
      return Collections.unmodifiableList(Arrays.asList(this.services));
    }

    public List getExtensions()
    {
      return Collections.unmodifiableList(Arrays.asList(this.extensions));
    }

    public List getDependencies()
    {
      return Collections.unmodifiableList(Arrays.asList(this.dependencies));
    }

    public Descriptors.Descriptor findMessageTypeByName(String paramString)
    {
      if (paramString.indexOf('.') != -1)
        return null;
      if (getPackage().length() > 0)
        paramString = getPackage() + '.' + paramString;
      Descriptors.GenericDescriptor localGenericDescriptor = this.pool.findSymbol(paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.Descriptor)) && (localGenericDescriptor.getFile() == this))
        return (Descriptors.Descriptor)localGenericDescriptor;
      return null;
    }

    public Descriptors.EnumDescriptor findEnumTypeByName(String paramString)
    {
      if (paramString.indexOf('.') != -1)
        return null;
      if (getPackage().length() > 0)
        paramString = getPackage() + '.' + paramString;
      Descriptors.GenericDescriptor localGenericDescriptor = this.pool.findSymbol(paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.EnumDescriptor)) && (localGenericDescriptor.getFile() == this))
        return (Descriptors.EnumDescriptor)localGenericDescriptor;
      return null;
    }

    public Descriptors.ServiceDescriptor findServiceByName(String paramString)
    {
      if (paramString.indexOf('.') != -1)
        return null;
      if (getPackage().length() > 0)
        paramString = getPackage() + '.' + paramString;
      Descriptors.GenericDescriptor localGenericDescriptor = this.pool.findSymbol(paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.ServiceDescriptor)) && (localGenericDescriptor.getFile() == this))
        return (Descriptors.ServiceDescriptor)localGenericDescriptor;
      return null;
    }

    public Descriptors.FieldDescriptor findExtensionByName(String paramString)
    {
      if (paramString.indexOf('.') != -1)
        return null;
      if (getPackage().length() > 0)
        paramString = getPackage() + '.' + paramString;
      Descriptors.GenericDescriptor localGenericDescriptor = this.pool.findSymbol(paramString);
      if ((localGenericDescriptor != null) && ((localGenericDescriptor instanceof Descriptors.FieldDescriptor)) && (localGenericDescriptor.getFile() == this))
        return (Descriptors.FieldDescriptor)localGenericDescriptor;
      return null;
    }

    public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto paramFileDescriptorProto, FileDescriptor[] paramArrayOfFileDescriptor)
      throws Descriptors.DescriptorValidationException
    {
      Descriptors.DescriptorPool localDescriptorPool = new Descriptors.DescriptorPool(paramArrayOfFileDescriptor);
      FileDescriptor localFileDescriptor = new FileDescriptor(paramFileDescriptorProto, paramArrayOfFileDescriptor, localDescriptorPool);
      if (paramArrayOfFileDescriptor.length != paramFileDescriptorProto.getDependencyCount())
        throw new Descriptors.DescriptorValidationException(localFileDescriptor, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto.", null);
      for (int i = 0; i < paramFileDescriptorProto.getDependencyCount(); i++)
        if (!paramArrayOfFileDescriptor[i].getName().equals(paramFileDescriptorProto.getDependency(i)))
          throw new Descriptors.DescriptorValidationException(localFileDescriptor, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto.", null);
      localFileDescriptor.crossLink();
      return localFileDescriptor;
    }

    public static void internalBuildGeneratedFileFrom(String[] paramArrayOfString, FileDescriptor[] paramArrayOfFileDescriptor, InternalDescriptorAssigner paramInternalDescriptorAssigner)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (String str : paramArrayOfString)
        localStringBuilder.append(str);
      try
      {
        ??? = localStringBuilder.toString().getBytes("ISO-8859-1");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new RuntimeException("Standard encoding ISO-8859-1 not supported by JVM.", localUnsupportedEncodingException);
      }
      DescriptorProtos.FileDescriptorProto localFileDescriptorProto;
      try
      {
        localFileDescriptorProto = DescriptorProtos.FileDescriptorProto.parseFrom((byte[])???);
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException1)
      {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", localInvalidProtocolBufferException1);
      }
      FileDescriptor localFileDescriptor;
      try
      {
        localFileDescriptor = buildFrom(localFileDescriptorProto, paramArrayOfFileDescriptor);
      }
      catch (Descriptors.DescriptorValidationException localDescriptorValidationException)
      {
        throw new IllegalArgumentException("Invalid embedded descriptor for \"" + localFileDescriptorProto.getName() + "\".", localDescriptorValidationException);
      }
      ExtensionRegistry localExtensionRegistry = paramInternalDescriptorAssigner.assignDescriptors(localFileDescriptor);
      if (localExtensionRegistry != null)
      {
        try
        {
          localFileDescriptorProto = DescriptorProtos.FileDescriptorProto.parseFrom((byte[])???, localExtensionRegistry);
        }
        catch (InvalidProtocolBufferException localInvalidProtocolBufferException2)
        {
          throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", localInvalidProtocolBufferException2);
        }
        localFileDescriptor.setProto(localFileDescriptorProto);
      }
    }

    private FileDescriptor(DescriptorProtos.FileDescriptorProto paramFileDescriptorProto, FileDescriptor[] paramArrayOfFileDescriptor, Descriptors.DescriptorPool paramDescriptorPool)
      throws Descriptors.DescriptorValidationException
    {
      this.pool = paramDescriptorPool;
      this.proto = paramFileDescriptorProto;
      this.dependencies = ((FileDescriptor[])paramArrayOfFileDescriptor.clone());
      paramDescriptorPool.addPackage(getPackage(), this);
      this.messageTypes = new Descriptors.Descriptor[paramFileDescriptorProto.getMessageTypeCount()];
      for (int i = 0; i < paramFileDescriptorProto.getMessageTypeCount(); i++)
        this.messageTypes[i] = new Descriptors.Descriptor(paramFileDescriptorProto.getMessageType(i), this, null, i, null);
      this.enumTypes = new Descriptors.EnumDescriptor[paramFileDescriptorProto.getEnumTypeCount()];
      for (i = 0; i < paramFileDescriptorProto.getEnumTypeCount(); i++)
        this.enumTypes[i] = new Descriptors.EnumDescriptor(paramFileDescriptorProto.getEnumType(i), this, null, i, null);
      this.services = new Descriptors.ServiceDescriptor[paramFileDescriptorProto.getServiceCount()];
      for (i = 0; i < paramFileDescriptorProto.getServiceCount(); i++)
        this.services[i] = new Descriptors.ServiceDescriptor(paramFileDescriptorProto.getService(i), this, i, null);
      this.extensions = new Descriptors.FieldDescriptor[paramFileDescriptorProto.getExtensionCount()];
      for (i = 0; i < paramFileDescriptorProto.getExtensionCount(); i++)
        this.extensions[i] = new Descriptors.FieldDescriptor(paramFileDescriptorProto.getExtension(i), this, null, i, true, null);
    }

    private void crossLink()
      throws Descriptors.DescriptorValidationException
    {
      Descriptors.Descriptor localDescriptor;
      for (localDescriptor : this.messageTypes)
        localDescriptor.crossLink();
      for (localDescriptor : this.services)
        localDescriptor.crossLink();
      for (localDescriptor : this.extensions)
        localDescriptor.crossLink();
    }

    private void setProto(DescriptorProtos.FileDescriptorProto paramFileDescriptorProto)
    {
      this.proto = paramFileDescriptorProto;
      for (int i = 0; i < this.messageTypes.length; i++)
        this.messageTypes[i].setProto(paramFileDescriptorProto.getMessageType(i));
      for (i = 0; i < this.enumTypes.length; i++)
        this.enumTypes[i].setProto(paramFileDescriptorProto.getEnumType(i));
      for (i = 0; i < this.services.length; i++)
        this.services[i].setProto(paramFileDescriptorProto.getService(i));
      for (i = 0; i < this.extensions.length; i++)
        this.extensions[i].setProto(paramFileDescriptorProto.getExtension(i));
    }

    public static abstract interface InternalDescriptorAssigner
    {
      public abstract ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor paramFileDescriptor);
    }
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.Descriptors
 * JD-Core Version:    0.6.2
 */