package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos
{
  private static Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorSet_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_FieldDescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_EnumDescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_MethodDescriptorProto_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_FileOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_MessageOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_FieldOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_EnumOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_EnumValueOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_ServiceOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_MethodOptions_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
  private static Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
  private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
  private static Descriptors.FileDescriptor descriptor;

  public static void registerAllExtensions(ExtensionRegistry paramExtensionRegistry)
  {
  }

  public static Descriptors.FileDescriptor getDescriptor()
  {
    return descriptor;
  }

  static
  {
    String[] arrayOfString = { "\n google/protobuf/descriptor.proto\022\017google.protobuf\"G\n\021FileDescriptorSet\0222\n\004file\030\001 \003(\0132$.google.protobuf.FileDescriptorProto\"\003\n\023FileDescriptorProto\022\f\n\004name\030\001 \001(\t\022\017\n\007package\030\002 \001(\t\022\022\n\ndependency\030\003 \003(\t\0226\n\fmessage_type\030\004 \003(\0132 .google.protobuf.DescriptorProto\0227\n\tenum_type\030\005 \003(\0132$.google.protobuf.EnumDescriptorProto\0228\n\007service\030\006 \003(\0132'.google.protobuf.ServiceDescriptorProto\0228\n\textension\030\007 \003(\0132%.google.p", "rotobuf.FieldDescriptorProto\022-\n\007options\030\b \001(\0132\034.google.protobuf.FileOptions\0229\n\020source_code_info\030\t \001(\0132\037.google.protobuf.SourceCodeInfo\"©\003\n\017DescriptorProto\022\f\n\004name\030\001 \001(\t\0224\n\005field\030\002 \003(\0132%.google.protobuf.FieldDescriptorProto\0228\n\textension\030\006 \003(\0132%.google.protobuf.FieldDescriptorProto\0225\n\013nested_type\030\003 \003(\0132 .google.protobuf.DescriptorProto\0227\n\tenum_type\030\004 \003(\0132$.google.protobuf.EnumDescriptorProto\022H\n\017exte", "nsion_range\030\005 \003(\0132/.google.protobuf.DescriptorProto.ExtensionRange\0220\n\007options\030\007 \001(\0132\037.google.protobuf.MessageOptions\032,\n\016ExtensionRange\022\r\n\005start\030\001 \001(\005\022\013\n\003end\030\002 \001(\005\"\005\n\024FieldDescriptorProto\022\f\n\004name\030\001 \001(\t\022\016\n\006number\030\003 \001(\005\022:\n\005label\030\004 \001(\0162+.google.protobuf.FieldDescriptorProto.Label\0228\n\004type\030\005 \001(\0162*.google.protobuf.FieldDescriptorProto.Type\022\021\n\ttype_name\030\006 \001(\t\022\020\n\bextendee\030\002 \001(\t\022\025\n\rdefault_value\030\007 \001(\t\022.\n\007o", "ptions\030\b \001(\0132\035.google.protobuf.FieldOptions\"¶\002\n\004Type\022\017\n\013TYPE_DOUBLE\020\001\022\016\n\nTYPE_FLOAT\020\002\022\016\n\nTYPE_INT64\020\003\022\017\n\013TYPE_UINT64\020\004\022\016\n\nTYPE_INT32\020\005\022\020\n\fTYPE_FIXED64\020\006\022\020\n\fTYPE_FIXED32\020\007\022\r\n\tTYPE_BOOL\020\b\022\017\n\013TYPE_STRING\020\t\022\016\n\nTYPE_GROUP\020\n\022\020\n\fTYPE_MESSAGE\020\013\022\016\n\nTYPE_BYTES\020\f\022\017\n\013TYPE_UINT32\020\r\022\r\n\tTYPE_ENUM\020\016\022\021\n\rTYPE_SFIXED32\020\017\022\021\n\rTYPE_SFIXED64\020\020\022\017\n\013TYPE_SINT32\020\021\022\017\n\013TYPE_SINT64\020\022\"C\n\005Label\022\022\n\016LABEL_OPTIONAL\020\001\022\022\n\016LABEL_REQUI", "RED\020\002\022\022\n\016LABEL_REPEATED\020\003\"\001\n\023EnumDescriptorProto\022\f\n\004name\030\001 \001(\t\0228\n\005value\030\002 \003(\0132).google.protobuf.EnumValueDescriptorProto\022-\n\007options\030\003 \001(\0132\034.google.protobuf.EnumOptions\"l\n\030EnumValueDescriptorProto\022\f\n\004name\030\001 \001(\t\022\016\n\006number\030\002 \001(\005\0222\n\007options\030\003 \001(\0132!.google.protobuf.EnumValueOptions\"\001\n\026ServiceDescriptorProto\022\f\n\004name\030\001 \001(\t\0226\n\006method\030\002 \003(\0132&.google.protobuf.MethodDescriptorProto\0220\n\007options\030\003 \001(\0132\037.googl", "e.protobuf.ServiceOptions\"\n\025MethodDescriptorProto\022\f\n\004name\030\001 \001(\t\022\022\n\ninput_type\030\002 \001(\t\022\023\n\013output_type\030\003 \001(\t\022/\n\007options\030\004 \001(\0132\036.google.protobuf.MethodOptions\"Õ\003\n\013FileOptions\022\024\n\fjava_package\030\001 \001(\t\022\034\n\024java_outer_classname\030\b \001(\t\022\"\n\023java_multiple_files\030\n \001(\b:\005false\022,\n\035java_generate_equals_and_hash\030\024 \001(\b:\005false\022F\n\foptimize_for\030\t \001(\0162).google.protobuf.FileOptions.OptimizeMode:\005SPEED\022\"\n\023cc_generic_services\030", "\020 \001(\b:\005false\022$\n\025java_generic_services\030\021 \001(\b:\005false\022\"\n\023py_generic_services\030\022 \001(\b:\005false\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption\":\n\fOptimizeMode\022\t\n\005SPEED\020\001\022\r\n\tCODE_SIZE\020\002\022\020\n\fLITE_RUNTIME\020\003*\t\bè\007\020\002\"¸\001\n\016MessageOptions\022&\n\027message_set_wire_format\030\001 \001(\b:\005false\022.\n\037no_standard_descriptor_accessor\030\002 \001(\b:\005false\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOpti", "on*\t\bè\007\020\002\"\002\n\fFieldOptions\022:\n\005ctype\030\001 \001(\0162#.google.protobuf.FieldOptions.CType:\006STRING\022\016\n\006packed\030\002 \001(\b\022\031\n\ndeprecated\030\003 \001(\b:\005false\022\034\n\024experimental_map_key\030\t \001(\t\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption\"/\n\005CType\022\n\n\006STRING\020��\022\b\n\004CORD\020\001\022\020\n\fSTRING_PIECE\020\002*\t\bè\007\020\002\"]\n\013EnumOptions\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"b\n\020EnumValue", "Options\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"`\n\016ServiceOptions\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"_\n\rMethodOptions\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"\002\n\023UninterpretedOption\022;\n\004name\030\002 \003(\0132-.google.protobuf.UninterpretedOption.NamePart\022\030\n\020identifier_value\030\003 \001(\t\022\032\n\022pos", "itive_int_value\030\004 \001(\004\022\032\n\022negative_int_value\030\005 \001(\003\022\024\n\fdouble_value\030\006 \001(\001\022\024\n\fstring_value\030\007 \001(\f\022\027\n\017aggregate_value\030\b \001(\t\0323\n\bNamePart\022\021\n\tname_part\030\001 \002(\t\022\024\n\fis_extension\030\002 \002(\b\"|\n\016SourceCodeInfo\022:\n\blocation\030\001 \003(\0132($google.protobuf.SourceCodeInfo.Location\032.\n\bLocation\022\020\n\004path\030\001 \003(\005B\002\020\001\022\020\n\004span\030\002 \003(\005B\002\020\001B)\n\023com.google.protobufB\020DescriptorProtosH\001" };
    Descriptors.FileDescriptor.InternalDescriptorAssigner local1 = new Descriptors.FileDescriptor.InternalDescriptorAssigner()
    {
      public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor paramAnonymousFileDescriptor)
      {
        DescriptorProtos.access$20602(paramAnonymousFileDescriptor);
        DescriptorProtos.access$002((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(0));
        DescriptorProtos.access$102(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor, new String[] { "File" }, DescriptorProtos.FileDescriptorSet.class, DescriptorProtos.FileDescriptorSet.Builder.class));
        DescriptorProtos.access$702((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(1));
        DescriptorProtos.access$802(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor, new String[] { "Name", "Package", "Dependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo" }, DescriptorProtos.FileDescriptorProto.class, DescriptorProtos.FileDescriptorProto.Builder.class));
        DescriptorProtos.access$2302((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(2));
        DescriptorProtos.access$2402(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor, new String[] { "Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "Options" }, DescriptorProtos.DescriptorProto.class, DescriptorProtos.DescriptorProto.Builder.class));
        DescriptorProtos.access$2502((Descriptors.Descriptor)DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor.getNestedTypes().get(0));
        DescriptorProtos.access$2602(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor, new String[] { "Start", "End" }, DescriptorProtos.DescriptorProto.ExtensionRange.class, DescriptorProtos.DescriptorProto.ExtensionRange.Builder.class));
        DescriptorProtos.access$4602((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(3));
        DescriptorProtos.access$4702(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor, new String[] { "Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "Options" }, DescriptorProtos.FieldDescriptorProto.class, DescriptorProtos.FieldDescriptorProto.Builder.class));
        DescriptorProtos.access$6102((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(4));
        DescriptorProtos.access$6202(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor, new String[] { "Name", "Value", "Options" }, DescriptorProtos.EnumDescriptorProto.class, DescriptorProtos.EnumDescriptorProto.Builder.class));
        DescriptorProtos.access$7102((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(5));
        DescriptorProtos.access$7202(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor, new String[] { "Name", "Number", "Options" }, DescriptorProtos.EnumValueDescriptorProto.class, DescriptorProtos.EnumValueDescriptorProto.Builder.class));
        DescriptorProtos.access$8102((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(6));
        DescriptorProtos.access$8202(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor, new String[] { "Name", "Method", "Options" }, DescriptorProtos.ServiceDescriptorProto.class, DescriptorProtos.ServiceDescriptorProto.Builder.class));
        DescriptorProtos.access$9102((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(7));
        DescriptorProtos.access$9202(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor, new String[] { "Name", "InputType", "OutputType", "Options" }, DescriptorProtos.MethodDescriptorProto.class, DescriptorProtos.MethodDescriptorProto.Builder.class));
        DescriptorProtos.access$10202((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(8));
        DescriptorProtos.access$10302(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor, new String[] { "JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "OptimizeFor", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "UninterpretedOption" }, DescriptorProtos.FileOptions.class, DescriptorProtos.FileOptions.Builder.class));
        DescriptorProtos.access$11802((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(9));
        DescriptorProtos.access$11902(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor, new String[] { "MessageSetWireFormat", "NoStandardDescriptorAccessor", "UninterpretedOption" }, DescriptorProtos.MessageOptions.class, DescriptorProtos.MessageOptions.Builder.class));
        DescriptorProtos.access$12802((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(10));
        DescriptorProtos.access$12902(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor, new String[] { "Ctype", "Packed", "Deprecated", "ExperimentalMapKey", "UninterpretedOption" }, DescriptorProtos.FieldOptions.class, DescriptorProtos.FieldOptions.Builder.class));
        DescriptorProtos.access$14002((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(11));
        DescriptorProtos.access$14102(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor, new String[] { "UninterpretedOption" }, DescriptorProtos.EnumOptions.class, DescriptorProtos.EnumOptions.Builder.class));
        DescriptorProtos.access$14702((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(12));
        DescriptorProtos.access$14802(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor, new String[] { "UninterpretedOption" }, DescriptorProtos.EnumValueOptions.class, DescriptorProtos.EnumValueOptions.Builder.class));
        DescriptorProtos.access$15402((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(13));
        DescriptorProtos.access$15502(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor, new String[] { "UninterpretedOption" }, DescriptorProtos.ServiceOptions.class, DescriptorProtos.ServiceOptions.Builder.class));
        DescriptorProtos.access$16102((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(14));
        DescriptorProtos.access$16202(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor, new String[] { "UninterpretedOption" }, DescriptorProtos.MethodOptions.class, DescriptorProtos.MethodOptions.Builder.class));
        DescriptorProtos.access$16802((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(15));
        DescriptorProtos.access$16902(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor, new String[] { "Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue" }, DescriptorProtos.UninterpretedOption.class, DescriptorProtos.UninterpretedOption.Builder.class));
        DescriptorProtos.access$17002((Descriptors.Descriptor)DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor.getNestedTypes().get(0));
        DescriptorProtos.access$17102(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor, new String[] { "NamePart", "IsExtension" }, DescriptorProtos.UninterpretedOption.NamePart.class, DescriptorProtos.UninterpretedOption.NamePart.Builder.class));
        DescriptorProtos.access$19102((Descriptors.Descriptor)DescriptorProtos.getDescriptor().getMessageTypes().get(16));
        DescriptorProtos.access$19202(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor, new String[] { "Location" }, DescriptorProtos.SourceCodeInfo.class, DescriptorProtos.SourceCodeInfo.Builder.class));
        DescriptorProtos.access$19302((Descriptors.Descriptor)DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor.getNestedTypes().get(0));
        DescriptorProtos.access$19402(new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor, new String[] { "Path", "Span" }, DescriptorProtos.SourceCodeInfo.Location.class, DescriptorProtos.SourceCodeInfo.Location.Builder.class));
        return null;
      }
    };
    Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(arrayOfString, new Descriptors.FileDescriptor[0], local1);
  }

  public static final class SourceCodeInfo extends GeneratedMessage
    implements DescriptorProtos.SourceCodeInfoOrBuilder
  {
    private static final SourceCodeInfo defaultInstance = new SourceCodeInfo(true);
    public static final int LOCATION_FIELD_NUMBER = 1;
    private List location_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private SourceCodeInfo(Builder paramBuilder)
    {
      super();
    }

    private SourceCodeInfo(boolean paramBoolean)
    {
    }

    public static SourceCodeInfo getDefaultInstance()
    {
      return defaultInstance;
    }

    public SourceCodeInfo getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
    }

    public List getLocationList()
    {
      return this.location_;
    }

    public List getLocationOrBuilderList()
    {
      return this.location_;
    }

    public int getLocationCount()
    {
      return this.location_.size();
    }

    public Location getLocation(int paramInt)
    {
      return (Location)this.location_.get(paramInt);
    }

    public LocationOrBuilder getLocationOrBuilder(int paramInt)
    {
      return (LocationOrBuilder)this.location_.get(paramInt);
    }

    private void initFields()
    {
      this.location_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      for (int i = 0; i < this.location_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.location_.get(i));
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.location_.size(); j++)
        i += CodedOutputStream.computeMessageSize(1, (MessageLite)this.location_.get(j));
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static SourceCodeInfo parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static SourceCodeInfo parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static SourceCodeInfo parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static SourceCodeInfo parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static SourceCodeInfo parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static SourceCodeInfo parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static SourceCodeInfo parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static SourceCodeInfo parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static SourceCodeInfo parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static SourceCodeInfo parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$20200();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(SourceCodeInfo paramSourceCodeInfo)
    {
      return newBuilder().mergeFrom(paramSourceCodeInfo);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.SourceCodeInfoOrBuilder
    {
      private int bitField0_;
      private List location_ = Collections.emptyList();
      private RepeatedFieldBuilder locationBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getLocationFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.locationBuilder_ == null)
        {
          this.location_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.locationBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.SourceCodeInfo.getDescriptor();
      }

      public DescriptorProtos.SourceCodeInfo getDefaultInstanceForType()
      {
        return DescriptorProtos.SourceCodeInfo.getDefaultInstance();
      }

      public DescriptorProtos.SourceCodeInfo build()
      {
        DescriptorProtos.SourceCodeInfo localSourceCodeInfo = buildPartial();
        if (!localSourceCodeInfo.isInitialized())
          throw newUninitializedMessageException(localSourceCodeInfo);
        return localSourceCodeInfo;
      }

      private DescriptorProtos.SourceCodeInfo buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.SourceCodeInfo localSourceCodeInfo = buildPartial();
        if (!localSourceCodeInfo.isInitialized())
          throw newUninitializedMessageException(localSourceCodeInfo).asInvalidProtocolBufferException();
        return localSourceCodeInfo;
      }

      public DescriptorProtos.SourceCodeInfo buildPartial()
      {
        DescriptorProtos.SourceCodeInfo localSourceCodeInfo = new DescriptorProtos.SourceCodeInfo(this, null);
        int i = this.bitField0_;
        if (this.locationBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.location_ = Collections.unmodifiableList(this.location_);
            this.bitField0_ &= -2;
          }
          localSourceCodeInfo.location_ = this.location_;
        }
        else
        {
          localSourceCodeInfo.location_ = this.locationBuilder_.build();
        }
        onBuilt();
        return localSourceCodeInfo;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.SourceCodeInfo))
          return mergeFrom((DescriptorProtos.SourceCodeInfo)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.SourceCodeInfo paramSourceCodeInfo)
      {
        if (paramSourceCodeInfo == DescriptorProtos.SourceCodeInfo.getDefaultInstance())
          return this;
        if (this.locationBuilder_ == null)
        {
          if (!paramSourceCodeInfo.location_.isEmpty())
          {
            if (this.location_.isEmpty())
            {
              this.location_ = paramSourceCodeInfo.location_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureLocationIsMutable();
              this.location_.addAll(paramSourceCodeInfo.location_);
            }
            onChanged();
          }
        }
        else if (!paramSourceCodeInfo.location_.isEmpty())
          if (this.locationBuilder_.isEmpty())
          {
            this.locationBuilder_.dispose();
            this.locationBuilder_ = null;
            this.location_ = paramSourceCodeInfo.location_;
            this.bitField0_ &= -2;
            this.locationBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getLocationFieldBuilder() : null);
          }
          else
          {
            this.locationBuilder_.addAllMessages(paramSourceCodeInfo.location_);
          }
        mergeUnknownFields(paramSourceCodeInfo.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            DescriptorProtos.SourceCodeInfo.Location.Builder localBuilder1 = DescriptorProtos.SourceCodeInfo.Location.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addLocation(localBuilder1.buildPartial());
          }
        }
      }

      private void ensureLocationIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.location_ = new ArrayList(this.location_);
          this.bitField0_ |= 1;
        }
      }

      public List getLocationList()
      {
        if (this.locationBuilder_ == null)
          return Collections.unmodifiableList(this.location_);
        return this.locationBuilder_.getMessageList();
      }

      public int getLocationCount()
      {
        if (this.locationBuilder_ == null)
          return this.location_.size();
        return this.locationBuilder_.getCount();
      }

      public DescriptorProtos.SourceCodeInfo.Location getLocation(int paramInt)
      {
        if (this.locationBuilder_ == null)
          return (DescriptorProtos.SourceCodeInfo.Location)this.location_.get(paramInt);
        return (DescriptorProtos.SourceCodeInfo.Location)this.locationBuilder_.getMessage(paramInt);
      }

      public Builder setLocation(int paramInt, DescriptorProtos.SourceCodeInfo.Location paramLocation)
      {
        if (this.locationBuilder_ == null)
        {
          if (paramLocation == null)
            throw new NullPointerException();
          ensureLocationIsMutable();
          this.location_.set(paramInt, paramLocation);
          onChanged();
        }
        else
        {
          this.locationBuilder_.setMessage(paramInt, paramLocation);
        }
        return this;
      }

      public Builder setLocation(int paramInt, DescriptorProtos.SourceCodeInfo.Location.Builder paramBuilder)
      {
        if (this.locationBuilder_ == null)
        {
          ensureLocationIsMutable();
          this.location_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.locationBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addLocation(DescriptorProtos.SourceCodeInfo.Location paramLocation)
      {
        if (this.locationBuilder_ == null)
        {
          if (paramLocation == null)
            throw new NullPointerException();
          ensureLocationIsMutable();
          this.location_.add(paramLocation);
          onChanged();
        }
        else
        {
          this.locationBuilder_.addMessage(paramLocation);
        }
        return this;
      }

      public Builder addLocation(int paramInt, DescriptorProtos.SourceCodeInfo.Location paramLocation)
      {
        if (this.locationBuilder_ == null)
        {
          if (paramLocation == null)
            throw new NullPointerException();
          ensureLocationIsMutable();
          this.location_.add(paramInt, paramLocation);
          onChanged();
        }
        else
        {
          this.locationBuilder_.addMessage(paramInt, paramLocation);
        }
        return this;
      }

      public Builder addLocation(DescriptorProtos.SourceCodeInfo.Location.Builder paramBuilder)
      {
        if (this.locationBuilder_ == null)
        {
          ensureLocationIsMutable();
          this.location_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.locationBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addLocation(int paramInt, DescriptorProtos.SourceCodeInfo.Location.Builder paramBuilder)
      {
        if (this.locationBuilder_ == null)
        {
          ensureLocationIsMutable();
          this.location_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.locationBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllLocation(Iterable paramIterable)
      {
        if (this.locationBuilder_ == null)
        {
          ensureLocationIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.location_);
          onChanged();
        }
        else
        {
          this.locationBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearLocation()
      {
        if (this.locationBuilder_ == null)
        {
          this.location_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.locationBuilder_.clear();
        }
        return this;
      }

      public Builder removeLocation(int paramInt)
      {
        if (this.locationBuilder_ == null)
        {
          ensureLocationIsMutable();
          this.location_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.locationBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.SourceCodeInfo.Location.Builder getLocationBuilder(int paramInt)
      {
        return (DescriptorProtos.SourceCodeInfo.Location.Builder)getLocationFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.SourceCodeInfo.LocationOrBuilder getLocationOrBuilder(int paramInt)
      {
        if (this.locationBuilder_ == null)
          return (DescriptorProtos.SourceCodeInfo.LocationOrBuilder)this.location_.get(paramInt);
        return (DescriptorProtos.SourceCodeInfo.LocationOrBuilder)this.locationBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getLocationOrBuilderList()
      {
        if (this.locationBuilder_ != null)
          return this.locationBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.location_);
      }

      public DescriptorProtos.SourceCodeInfo.Location.Builder addLocationBuilder()
      {
        return (DescriptorProtos.SourceCodeInfo.Location.Builder)getLocationFieldBuilder().addBuilder(DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance());
      }

      public DescriptorProtos.SourceCodeInfo.Location.Builder addLocationBuilder(int paramInt)
      {
        return (DescriptorProtos.SourceCodeInfo.Location.Builder)getLocationFieldBuilder().addBuilder(paramInt, DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance());
      }

      public List getLocationBuilderList()
      {
        return getLocationFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getLocationFieldBuilder()
      {
        if (this.locationBuilder_ == null)
        {
          this.locationBuilder_ = new RepeatedFieldBuilder(this.location_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.location_ = null;
        }
        return this.locationBuilder_;
      }
    }

    public static final class Location extends GeneratedMessage
      implements DescriptorProtos.SourceCodeInfo.LocationOrBuilder
    {
      private static final Location defaultInstance = new Location(true);
      public static final int PATH_FIELD_NUMBER = 1;
      private List path_;
      private int pathMemoizedSerializedSize = -1;
      public static final int SPAN_FIELD_NUMBER = 2;
      private List span_;
      private int spanMemoizedSerializedSize = -1;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private static final long serialVersionUID = 0L;

      private Location(Builder paramBuilder)
      {
        super();
      }

      private Location(boolean paramBoolean)
      {
      }

      public static Location getDefaultInstance()
      {
        return defaultInstance;
      }

      public Location getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
      }

      public List getPathList()
      {
        return this.path_;
      }

      public int getPathCount()
      {
        return this.path_.size();
      }

      public int getPath(int paramInt)
      {
        return ((Integer)this.path_.get(paramInt)).intValue();
      }

      public List getSpanList()
      {
        return this.span_;
      }

      public int getSpanCount()
      {
        return this.span_.size();
      }

      public int getSpan(int paramInt)
      {
        return ((Integer)this.span_.get(paramInt)).intValue();
      }

      private void initFields()
      {
        this.path_ = Collections.emptyList();
        this.span_ = Collections.emptyList();
      }

      public final boolean isInitialized()
      {
        int i = this.memoizedIsInitialized;
        if (i != -1)
          return i == 1;
        this.memoizedIsInitialized = 1;
        return true;
      }

      public void writeTo(CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        getSerializedSize();
        if (getPathList().size() > 0)
        {
          paramCodedOutputStream.writeRawVarint32(10);
          paramCodedOutputStream.writeRawVarint32(this.pathMemoizedSerializedSize);
        }
        for (int i = 0; i < this.path_.size(); i++)
          paramCodedOutputStream.writeInt32NoTag(((Integer)this.path_.get(i)).intValue());
        if (getSpanList().size() > 0)
        {
          paramCodedOutputStream.writeRawVarint32(18);
          paramCodedOutputStream.writeRawVarint32(this.spanMemoizedSerializedSize);
        }
        for (i = 0; i < this.span_.size(); i++)
          paramCodedOutputStream.writeInt32NoTag(((Integer)this.span_.get(i)).intValue());
        getUnknownFields().writeTo(paramCodedOutputStream);
      }

      public int getSerializedSize()
      {
        int i = this.memoizedSerializedSize;
        if (i != -1)
          return i;
        i = 0;
        int j = 0;
        for (int k = 0; k < this.path_.size(); k++)
          j += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.path_.get(k)).intValue());
        i += j;
        if (!getPathList().isEmpty())
        {
          i++;
          i += CodedOutputStream.computeInt32SizeNoTag(j);
        }
        this.pathMemoizedSerializedSize = j;
        j = 0;
        for (k = 0; k < this.span_.size(); k++)
          j += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.span_.get(k)).intValue());
        i += j;
        if (!getSpanList().isEmpty())
        {
          i++;
          i += CodedOutputStream.computeInt32SizeNoTag(j);
        }
        this.spanMemoizedSerializedSize = j;
        i += getUnknownFields().getSerializedSize();
        this.memoizedSerializedSize = i;
        return i;
      }

      protected Object writeReplace()
        throws ObjectStreamException
      {
        return super.writeReplace();
      }

      public static Location parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static Location parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static Location parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static Location parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public static Location parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static Location parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static Location parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static Location parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static Location parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static Location parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static Builder newBuilder()
      {
        return Builder.access$19600();
      }

      public Builder newBuilderForType()
      {
        return newBuilder();
      }

      public static Builder newBuilder(Location paramLocation)
      {
        return newBuilder().mergeFrom(paramLocation);
      }

      public Builder toBuilder()
      {
        return newBuilder(this);
      }

      protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        Builder localBuilder = new Builder(paramBuilderParent, null);
        return localBuilder;
      }

      static
      {
        defaultInstance.initFields();
      }

      public static final class Builder extends GeneratedMessage.Builder
        implements DescriptorProtos.SourceCodeInfo.LocationOrBuilder
      {
        private int bitField0_;
        private List path_ = Collections.emptyList();
        private List span_ = Collections.emptyList();

        public static final Descriptors.Descriptor getDescriptor()
        {
          return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
        }

        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
        {
          return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
        }

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
        {
          super();
          maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization()
        {
          if (GeneratedMessage.alwaysUseFieldBuilders);
        }

        private static Builder create()
        {
          return new Builder();
        }

        public Builder clear()
        {
          super.clear();
          this.path_ = Collections.emptyList();
          this.bitField0_ &= -2;
          this.span_ = Collections.emptyList();
          this.bitField0_ &= -3;
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public Descriptors.Descriptor getDescriptorForType()
        {
          return DescriptorProtos.SourceCodeInfo.Location.getDescriptor();
        }

        public DescriptorProtos.SourceCodeInfo.Location getDefaultInstanceForType()
        {
          return DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance();
        }

        public DescriptorProtos.SourceCodeInfo.Location build()
        {
          DescriptorProtos.SourceCodeInfo.Location localLocation = buildPartial();
          if (!localLocation.isInitialized())
            throw newUninitializedMessageException(localLocation);
          return localLocation;
        }

        private DescriptorProtos.SourceCodeInfo.Location buildParsed()
          throws InvalidProtocolBufferException
        {
          DescriptorProtos.SourceCodeInfo.Location localLocation = buildPartial();
          if (!localLocation.isInitialized())
            throw newUninitializedMessageException(localLocation).asInvalidProtocolBufferException();
          return localLocation;
        }

        public DescriptorProtos.SourceCodeInfo.Location buildPartial()
        {
          DescriptorProtos.SourceCodeInfo.Location localLocation = new DescriptorProtos.SourceCodeInfo.Location(this, null);
          int i = this.bitField0_;
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.path_ = Collections.unmodifiableList(this.path_);
            this.bitField0_ &= -2;
          }
          localLocation.path_ = this.path_;
          if ((this.bitField0_ & 0x2) == 2)
          {
            this.span_ = Collections.unmodifiableList(this.span_);
            this.bitField0_ &= -3;
          }
          localLocation.span_ = this.span_;
          onBuilt();
          return localLocation;
        }

        public Builder mergeFrom(Message paramMessage)
        {
          if ((paramMessage instanceof DescriptorProtos.SourceCodeInfo.Location))
            return mergeFrom((DescriptorProtos.SourceCodeInfo.Location)paramMessage);
          super.mergeFrom(paramMessage);
          return this;
        }

        public Builder mergeFrom(DescriptorProtos.SourceCodeInfo.Location paramLocation)
        {
          if (paramLocation == DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance())
            return this;
          if (!paramLocation.path_.isEmpty())
          {
            if (this.path_.isEmpty())
            {
              this.path_ = paramLocation.path_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensurePathIsMutable();
              this.path_.addAll(paramLocation.path_);
            }
            onChanged();
          }
          if (!paramLocation.span_.isEmpty())
          {
            if (this.span_.isEmpty())
            {
              this.span_ = paramLocation.span_;
              this.bitField0_ &= -3;
            }
            else
            {
              ensureSpanIsMutable();
              this.span_.addAll(paramLocation.span_);
            }
            onChanged();
          }
          mergeUnknownFields(paramLocation.getUnknownFields());
          return this;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
          throws IOException
        {
          UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
          while (true)
          {
            int i = paramCodedInputStream.readTag();
            int j;
            int k;
            switch (i)
            {
            case 0:
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            default:
              if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
              {
                setUnknownFields(localBuilder.build());
                onChanged();
                return this;
              }
              break;
            case 8:
              ensurePathIsMutable();
              this.path_.add(Integer.valueOf(paramCodedInputStream.readInt32()));
              break;
            case 10:
              j = paramCodedInputStream.readRawVarint32();
              k = paramCodedInputStream.pushLimit(j);
              while (paramCodedInputStream.getBytesUntilLimit() > 0)
                addPath(paramCodedInputStream.readInt32());
              paramCodedInputStream.popLimit(k);
              break;
            case 16:
              ensureSpanIsMutable();
              this.span_.add(Integer.valueOf(paramCodedInputStream.readInt32()));
              break;
            case 18:
              j = paramCodedInputStream.readRawVarint32();
              k = paramCodedInputStream.pushLimit(j);
              while (paramCodedInputStream.getBytesUntilLimit() > 0)
                addSpan(paramCodedInputStream.readInt32());
              paramCodedInputStream.popLimit(k);
            }
          }
        }

        private void ensurePathIsMutable()
        {
          if ((this.bitField0_ & 0x1) != 1)
          {
            this.path_ = new ArrayList(this.path_);
            this.bitField0_ |= 1;
          }
        }

        public List getPathList()
        {
          return Collections.unmodifiableList(this.path_);
        }

        public int getPathCount()
        {
          return this.path_.size();
        }

        public int getPath(int paramInt)
        {
          return ((Integer)this.path_.get(paramInt)).intValue();
        }

        public Builder setPath(int paramInt1, int paramInt2)
        {
          ensurePathIsMutable();
          this.path_.set(paramInt1, Integer.valueOf(paramInt2));
          onChanged();
          return this;
        }

        public Builder addPath(int paramInt)
        {
          ensurePathIsMutable();
          this.path_.add(Integer.valueOf(paramInt));
          onChanged();
          return this;
        }

        public Builder addAllPath(Iterable paramIterable)
        {
          ensurePathIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.path_);
          onChanged();
          return this;
        }

        public Builder clearPath()
        {
          this.path_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
          return this;
        }

        private void ensureSpanIsMutable()
        {
          if ((this.bitField0_ & 0x2) != 2)
          {
            this.span_ = new ArrayList(this.span_);
            this.bitField0_ |= 2;
          }
        }

        public List getSpanList()
        {
          return Collections.unmodifiableList(this.span_);
        }

        public int getSpanCount()
        {
          return this.span_.size();
        }

        public int getSpan(int paramInt)
        {
          return ((Integer)this.span_.get(paramInt)).intValue();
        }

        public Builder setSpan(int paramInt1, int paramInt2)
        {
          ensureSpanIsMutable();
          this.span_.set(paramInt1, Integer.valueOf(paramInt2));
          onChanged();
          return this;
        }

        public Builder addSpan(int paramInt)
        {
          ensureSpanIsMutable();
          this.span_.add(Integer.valueOf(paramInt));
          onChanged();
          return this;
        }

        public Builder addAllSpan(Iterable paramIterable)
        {
          ensureSpanIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.span_);
          onChanged();
          return this;
        }

        public Builder clearSpan()
        {
          this.span_ = Collections.emptyList();
          this.bitField0_ &= -3;
          onChanged();
          return this;
        }
      }
    }

    public static abstract interface LocationOrBuilder extends MessageOrBuilder
    {
      public abstract List getPathList();

      public abstract int getPathCount();

      public abstract int getPath(int paramInt);

      public abstract List getSpanList();

      public abstract int getSpanCount();

      public abstract int getSpan(int paramInt);
    }
  }

  public static abstract interface SourceCodeInfoOrBuilder extends MessageOrBuilder
  {
    public abstract List getLocationList();

    public abstract DescriptorProtos.SourceCodeInfo.Location getLocation(int paramInt);

    public abstract int getLocationCount();

    public abstract List getLocationOrBuilderList();

    public abstract DescriptorProtos.SourceCodeInfo.LocationOrBuilder getLocationOrBuilder(int paramInt);
  }

  public static final class UninterpretedOption extends GeneratedMessage
    implements DescriptorProtos.UninterpretedOptionOrBuilder
  {
    private static final UninterpretedOption defaultInstance = new UninterpretedOption(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 2;
    private List name_;
    public static final int IDENTIFIER_VALUE_FIELD_NUMBER = 3;
    private Object identifierValue_;
    public static final int POSITIVE_INT_VALUE_FIELD_NUMBER = 4;
    private long positiveIntValue_;
    public static final int NEGATIVE_INT_VALUE_FIELD_NUMBER = 5;
    private long negativeIntValue_;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 6;
    private double doubleValue_;
    public static final int STRING_VALUE_FIELD_NUMBER = 7;
    private ByteString stringValue_;
    public static final int AGGREGATE_VALUE_FIELD_NUMBER = 8;
    private Object aggregateValue_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private UninterpretedOption(Builder paramBuilder)
    {
      super();
    }

    private UninterpretedOption(boolean paramBoolean)
    {
    }

    public static UninterpretedOption getDefaultInstance()
    {
      return defaultInstance;
    }

    public UninterpretedOption getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
    }

    public List getNameList()
    {
      return this.name_;
    }

    public List getNameOrBuilderList()
    {
      return this.name_;
    }

    public int getNameCount()
    {
      return this.name_.size();
    }

    public NamePart getName(int paramInt)
    {
      return (NamePart)this.name_.get(paramInt);
    }

    public NamePartOrBuilder getNameOrBuilder(int paramInt)
    {
      return (NamePartOrBuilder)this.name_.get(paramInt);
    }

    public boolean hasIdentifierValue()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getIdentifierValue()
    {
      Object localObject = this.identifierValue_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.identifierValue_ = str;
      return str;
    }

    private ByteString getIdentifierValueBytes()
    {
      Object localObject = this.identifierValue_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.identifierValue_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasPositiveIntValue()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public long getPositiveIntValue()
    {
      return this.positiveIntValue_;
    }

    public boolean hasNegativeIntValue()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public long getNegativeIntValue()
    {
      return this.negativeIntValue_;
    }

    public boolean hasDoubleValue()
    {
      return (this.bitField0_ & 0x8) == 8;
    }

    public double getDoubleValue()
    {
      return this.doubleValue_;
    }

    public boolean hasStringValue()
    {
      return (this.bitField0_ & 0x10) == 16;
    }

    public ByteString getStringValue()
    {
      return this.stringValue_;
    }

    public boolean hasAggregateValue()
    {
      return (this.bitField0_ & 0x20) == 32;
    }

    public String getAggregateValue()
    {
      Object localObject = this.aggregateValue_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.aggregateValue_ = str;
      return str;
    }

    private ByteString getAggregateValueBytes()
    {
      Object localObject = this.aggregateValue_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.aggregateValue_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.name_ = Collections.emptyList();
      this.identifierValue_ = "";
      this.positiveIntValue_ = 0L;
      this.negativeIntValue_ = 0L;
      this.doubleValue_ = 0.0D;
      this.stringValue_ = ByteString.EMPTY;
      this.aggregateValue_ = "";
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getNameCount(); j++)
        if (!getName(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      for (int i = 0; i < this.name_.size(); i++)
        paramCodedOutputStream.writeMessage(2, (MessageLite)this.name_.get(i));
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(3, getIdentifierValueBytes());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeUInt64(4, this.positiveIntValue_);
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeInt64(5, this.negativeIntValue_);
      if ((this.bitField0_ & 0x8) == 8)
        paramCodedOutputStream.writeDouble(6, this.doubleValue_);
      if ((this.bitField0_ & 0x10) == 16)
        paramCodedOutputStream.writeBytes(7, this.stringValue_);
      if ((this.bitField0_ & 0x20) == 32)
        paramCodedOutputStream.writeBytes(8, getAggregateValueBytes());
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.name_.size(); j++)
        i += CodedOutputStream.computeMessageSize(2, (MessageLite)this.name_.get(j));
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(3, getIdentifierValueBytes());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeUInt64Size(4, this.positiveIntValue_);
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeInt64Size(5, this.negativeIntValue_);
      if ((this.bitField0_ & 0x8) == 8)
        i += CodedOutputStream.computeDoubleSize(6, this.doubleValue_);
      if ((this.bitField0_ & 0x10) == 16)
        i += CodedOutputStream.computeBytesSize(7, this.stringValue_);
      if ((this.bitField0_ & 0x20) == 32)
        i += CodedOutputStream.computeBytesSize(8, getAggregateValueBytes());
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static UninterpretedOption parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static UninterpretedOption parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static UninterpretedOption parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static UninterpretedOption parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static UninterpretedOption parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static UninterpretedOption parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static UninterpretedOption parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static UninterpretedOption parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static UninterpretedOption parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static UninterpretedOption parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$18000();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(UninterpretedOption paramUninterpretedOption)
    {
      return newBuilder().mergeFrom(paramUninterpretedOption);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.UninterpretedOptionOrBuilder
    {
      private int bitField0_;
      private List name_ = Collections.emptyList();
      private RepeatedFieldBuilder nameBuilder_;
      private Object identifierValue_ = "";
      private long positiveIntValue_;
      private long negativeIntValue_;
      private double doubleValue_;
      private ByteString stringValue_ = ByteString.EMPTY;
      private Object aggregateValue_ = "";

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getNameFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.nameBuilder_ == null)
        {
          this.name_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.nameBuilder_.clear();
        }
        this.identifierValue_ = "";
        this.bitField0_ &= -3;
        this.positiveIntValue_ = 0L;
        this.bitField0_ &= -5;
        this.negativeIntValue_ = 0L;
        this.bitField0_ &= -9;
        this.doubleValue_ = 0.0D;
        this.bitField0_ &= -17;
        this.stringValue_ = ByteString.EMPTY;
        this.bitField0_ &= -33;
        this.aggregateValue_ = "";
        this.bitField0_ &= -65;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.UninterpretedOption.getDescriptor();
      }

      public DescriptorProtos.UninterpretedOption getDefaultInstanceForType()
      {
        return DescriptorProtos.UninterpretedOption.getDefaultInstance();
      }

      public DescriptorProtos.UninterpretedOption build()
      {
        DescriptorProtos.UninterpretedOption localUninterpretedOption = buildPartial();
        if (!localUninterpretedOption.isInitialized())
          throw newUninitializedMessageException(localUninterpretedOption);
        return localUninterpretedOption;
      }

      private DescriptorProtos.UninterpretedOption buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.UninterpretedOption localUninterpretedOption = buildPartial();
        if (!localUninterpretedOption.isInitialized())
          throw newUninitializedMessageException(localUninterpretedOption).asInvalidProtocolBufferException();
        return localUninterpretedOption;
      }

      public DescriptorProtos.UninterpretedOption buildPartial()
      {
        DescriptorProtos.UninterpretedOption localUninterpretedOption = new DescriptorProtos.UninterpretedOption(this, null);
        int i = this.bitField0_;
        int j = 0;
        if (this.nameBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.name_ = Collections.unmodifiableList(this.name_);
            this.bitField0_ &= -2;
          }
          localUninterpretedOption.name_ = this.name_;
        }
        else
        {
          localUninterpretedOption.name_ = this.nameBuilder_.build();
        }
        if ((i & 0x2) == 2)
          j |= 1;
        localUninterpretedOption.identifierValue_ = this.identifierValue_;
        if ((i & 0x4) == 4)
          j |= 2;
        localUninterpretedOption.positiveIntValue_ = this.positiveIntValue_;
        if ((i & 0x8) == 8)
          j |= 4;
        localUninterpretedOption.negativeIntValue_ = this.negativeIntValue_;
        if ((i & 0x10) == 16)
          j |= 8;
        localUninterpretedOption.doubleValue_ = this.doubleValue_;
        if ((i & 0x20) == 32)
          j |= 16;
        localUninterpretedOption.stringValue_ = this.stringValue_;
        if ((i & 0x40) == 64)
          j |= 32;
        localUninterpretedOption.aggregateValue_ = this.aggregateValue_;
        localUninterpretedOption.bitField0_ = j;
        onBuilt();
        return localUninterpretedOption;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.UninterpretedOption))
          return mergeFrom((DescriptorProtos.UninterpretedOption)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (paramUninterpretedOption == DescriptorProtos.UninterpretedOption.getDefaultInstance())
          return this;
        if (this.nameBuilder_ == null)
        {
          if (!paramUninterpretedOption.name_.isEmpty())
          {
            if (this.name_.isEmpty())
            {
              this.name_ = paramUninterpretedOption.name_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureNameIsMutable();
              this.name_.addAll(paramUninterpretedOption.name_);
            }
            onChanged();
          }
        }
        else if (!paramUninterpretedOption.name_.isEmpty())
          if (this.nameBuilder_.isEmpty())
          {
            this.nameBuilder_.dispose();
            this.nameBuilder_ = null;
            this.name_ = paramUninterpretedOption.name_;
            this.bitField0_ &= -2;
            this.nameBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getNameFieldBuilder() : null);
          }
          else
          {
            this.nameBuilder_.addAllMessages(paramUninterpretedOption.name_);
          }
        if (paramUninterpretedOption.hasIdentifierValue())
          setIdentifierValue(paramUninterpretedOption.getIdentifierValue());
        if (paramUninterpretedOption.hasPositiveIntValue())
          setPositiveIntValue(paramUninterpretedOption.getPositiveIntValue());
        if (paramUninterpretedOption.hasNegativeIntValue())
          setNegativeIntValue(paramUninterpretedOption.getNegativeIntValue());
        if (paramUninterpretedOption.hasDoubleValue())
          setDoubleValue(paramUninterpretedOption.getDoubleValue());
        if (paramUninterpretedOption.hasStringValue())
          setStringValue(paramUninterpretedOption.getStringValue());
        if (paramUninterpretedOption.hasAggregateValue())
          setAggregateValue(paramUninterpretedOption.getAggregateValue());
        mergeUnknownFields(paramUninterpretedOption.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getNameCount(); i++)
          if (!getName(i).isInitialized())
            return false;
        return true;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 18:
            DescriptorProtos.UninterpretedOption.NamePart.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.NamePart.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addName(localBuilder1.buildPartial());
            break;
          case 26:
            this.bitField0_ |= 2;
            this.identifierValue_ = paramCodedInputStream.readBytes();
            break;
          case 32:
            this.bitField0_ |= 4;
            this.positiveIntValue_ = paramCodedInputStream.readUInt64();
            break;
          case 40:
            this.bitField0_ |= 8;
            this.negativeIntValue_ = paramCodedInputStream.readInt64();
            break;
          case 49:
            this.bitField0_ |= 16;
            this.doubleValue_ = paramCodedInputStream.readDouble();
            break;
          case 58:
            this.bitField0_ |= 32;
            this.stringValue_ = paramCodedInputStream.readBytes();
            break;
          case 66:
            this.bitField0_ |= 64;
            this.aggregateValue_ = paramCodedInputStream.readBytes();
          }
        }
      }

      private void ensureNameIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.name_ = new ArrayList(this.name_);
          this.bitField0_ |= 1;
        }
      }

      public List getNameList()
      {
        if (this.nameBuilder_ == null)
          return Collections.unmodifiableList(this.name_);
        return this.nameBuilder_.getMessageList();
      }

      public int getNameCount()
      {
        if (this.nameBuilder_ == null)
          return this.name_.size();
        return this.nameBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption.NamePart getName(int paramInt)
      {
        if (this.nameBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption.NamePart)this.name_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption.NamePart)this.nameBuilder_.getMessage(paramInt);
      }

      public Builder setName(int paramInt, DescriptorProtos.UninterpretedOption.NamePart paramNamePart)
      {
        if (this.nameBuilder_ == null)
        {
          if (paramNamePart == null)
            throw new NullPointerException();
          ensureNameIsMutable();
          this.name_.set(paramInt, paramNamePart);
          onChanged();
        }
        else
        {
          this.nameBuilder_.setMessage(paramInt, paramNamePart);
        }
        return this;
      }

      public Builder setName(int paramInt, DescriptorProtos.UninterpretedOption.NamePart.Builder paramBuilder)
      {
        if (this.nameBuilder_ == null)
        {
          ensureNameIsMutable();
          this.name_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.nameBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addName(DescriptorProtos.UninterpretedOption.NamePart paramNamePart)
      {
        if (this.nameBuilder_ == null)
        {
          if (paramNamePart == null)
            throw new NullPointerException();
          ensureNameIsMutable();
          this.name_.add(paramNamePart);
          onChanged();
        }
        else
        {
          this.nameBuilder_.addMessage(paramNamePart);
        }
        return this;
      }

      public Builder addName(int paramInt, DescriptorProtos.UninterpretedOption.NamePart paramNamePart)
      {
        if (this.nameBuilder_ == null)
        {
          if (paramNamePart == null)
            throw new NullPointerException();
          ensureNameIsMutable();
          this.name_.add(paramInt, paramNamePart);
          onChanged();
        }
        else
        {
          this.nameBuilder_.addMessage(paramInt, paramNamePart);
        }
        return this;
      }

      public Builder addName(DescriptorProtos.UninterpretedOption.NamePart.Builder paramBuilder)
      {
        if (this.nameBuilder_ == null)
        {
          ensureNameIsMutable();
          this.name_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.nameBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addName(int paramInt, DescriptorProtos.UninterpretedOption.NamePart.Builder paramBuilder)
      {
        if (this.nameBuilder_ == null)
        {
          ensureNameIsMutable();
          this.name_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.nameBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllName(Iterable paramIterable)
      {
        if (this.nameBuilder_ == null)
        {
          ensureNameIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.name_);
          onChanged();
        }
        else
        {
          this.nameBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearName()
      {
        if (this.nameBuilder_ == null)
        {
          this.name_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.nameBuilder_.clear();
        }
        return this;
      }

      public Builder removeName(int paramInt)
      {
        if (this.nameBuilder_ == null)
        {
          ensureNameIsMutable();
          this.name_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.nameBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.NamePart.Builder getNameBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.NamePart.Builder)getNameFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOption.NamePartOrBuilder getNameOrBuilder(int paramInt)
      {
        if (this.nameBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption.NamePartOrBuilder)this.name_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption.NamePartOrBuilder)this.nameBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getNameOrBuilderList()
      {
        if (this.nameBuilder_ != null)
          return this.nameBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.name_);
      }

      public DescriptorProtos.UninterpretedOption.NamePart.Builder addNameBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.NamePart.Builder)getNameFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.NamePart.Builder addNameBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.NamePart.Builder)getNameFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance());
      }

      public List getNameBuilderList()
      {
        return getNameFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getNameFieldBuilder()
      {
        if (this.nameBuilder_ == null)
        {
          this.nameBuilder_ = new RepeatedFieldBuilder(this.name_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.name_ = null;
        }
        return this.nameBuilder_;
      }

      public boolean hasIdentifierValue()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public String getIdentifierValue()
      {
        Object localObject = this.identifierValue_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.identifierValue_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setIdentifierValue(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 2;
        this.identifierValue_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearIdentifierValue()
      {
        this.bitField0_ &= -3;
        this.identifierValue_ = DescriptorProtos.UninterpretedOption.getDefaultInstance().getIdentifierValue();
        onChanged();
        return this;
      }

      void setIdentifierValue(ByteString paramByteString)
      {
        this.bitField0_ |= 2;
        this.identifierValue_ = paramByteString;
        onChanged();
      }

      public boolean hasPositiveIntValue()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public long getPositiveIntValue()
      {
        return this.positiveIntValue_;
      }

      public Builder setPositiveIntValue(long paramLong)
      {
        this.bitField0_ |= 4;
        this.positiveIntValue_ = paramLong;
        onChanged();
        return this;
      }

      public Builder clearPositiveIntValue()
      {
        this.bitField0_ &= -5;
        this.positiveIntValue_ = 0L;
        onChanged();
        return this;
      }

      public boolean hasNegativeIntValue()
      {
        return (this.bitField0_ & 0x8) == 8;
      }

      public long getNegativeIntValue()
      {
        return this.negativeIntValue_;
      }

      public Builder setNegativeIntValue(long paramLong)
      {
        this.bitField0_ |= 8;
        this.negativeIntValue_ = paramLong;
        onChanged();
        return this;
      }

      public Builder clearNegativeIntValue()
      {
        this.bitField0_ &= -9;
        this.negativeIntValue_ = 0L;
        onChanged();
        return this;
      }

      public boolean hasDoubleValue()
      {
        return (this.bitField0_ & 0x10) == 16;
      }

      public double getDoubleValue()
      {
        return this.doubleValue_;
      }

      public Builder setDoubleValue(double paramDouble)
      {
        this.bitField0_ |= 16;
        this.doubleValue_ = paramDouble;
        onChanged();
        return this;
      }

      public Builder clearDoubleValue()
      {
        this.bitField0_ &= -17;
        this.doubleValue_ = 0.0D;
        onChanged();
        return this;
      }

      public boolean hasStringValue()
      {
        return (this.bitField0_ & 0x20) == 32;
      }

      public ByteString getStringValue()
      {
        return this.stringValue_;
      }

      public Builder setStringValue(ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        this.bitField0_ |= 32;
        this.stringValue_ = paramByteString;
        onChanged();
        return this;
      }

      public Builder clearStringValue()
      {
        this.bitField0_ &= -33;
        this.stringValue_ = DescriptorProtos.UninterpretedOption.getDefaultInstance().getStringValue();
        onChanged();
        return this;
      }

      public boolean hasAggregateValue()
      {
        return (this.bitField0_ & 0x40) == 64;
      }

      public String getAggregateValue()
      {
        Object localObject = this.aggregateValue_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.aggregateValue_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setAggregateValue(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 64;
        this.aggregateValue_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearAggregateValue()
      {
        this.bitField0_ &= -65;
        this.aggregateValue_ = DescriptorProtos.UninterpretedOption.getDefaultInstance().getAggregateValue();
        onChanged();
        return this;
      }

      void setAggregateValue(ByteString paramByteString)
      {
        this.bitField0_ |= 64;
        this.aggregateValue_ = paramByteString;
        onChanged();
      }
    }

    public static final class NamePart extends GeneratedMessage
      implements DescriptorProtos.UninterpretedOption.NamePartOrBuilder
    {
      private static final NamePart defaultInstance = new NamePart(true);
      private int bitField0_;
      public static final int NAME_PART_FIELD_NUMBER = 1;
      private Object namePart_;
      public static final int IS_EXTENSION_FIELD_NUMBER = 2;
      private boolean isExtension_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private static final long serialVersionUID = 0L;

      private NamePart(Builder paramBuilder)
      {
        super();
      }

      private NamePart(boolean paramBoolean)
      {
      }

      public static NamePart getDefaultInstance()
      {
        return defaultInstance;
      }

      public NamePart getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
      }

      public boolean hasNamePart()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getNamePart()
      {
        Object localObject = this.namePart_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.namePart_ = str;
        return str;
      }

      private ByteString getNamePartBytes()
      {
        Object localObject = this.namePart_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.namePart_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      public boolean hasIsExtension()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public boolean getIsExtension()
      {
        return this.isExtension_;
      }

      private void initFields()
      {
        this.namePart_ = "";
        this.isExtension_ = false;
      }

      public final boolean isInitialized()
      {
        int i = this.memoizedIsInitialized;
        if (i != -1)
          return i == 1;
        if (!hasNamePart())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
        if (!hasIsExtension())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
      }

      public void writeTo(CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        getSerializedSize();
        if ((this.bitField0_ & 0x1) == 1)
          paramCodedOutputStream.writeBytes(1, getNamePartBytes());
        if ((this.bitField0_ & 0x2) == 2)
          paramCodedOutputStream.writeBool(2, this.isExtension_);
        getUnknownFields().writeTo(paramCodedOutputStream);
      }

      public int getSerializedSize()
      {
        int i = this.memoizedSerializedSize;
        if (i != -1)
          return i;
        i = 0;
        if ((this.bitField0_ & 0x1) == 1)
          i += CodedOutputStream.computeBytesSize(1, getNamePartBytes());
        if ((this.bitField0_ & 0x2) == 2)
          i += CodedOutputStream.computeBoolSize(2, this.isExtension_);
        i += getUnknownFields().getSerializedSize();
        this.memoizedSerializedSize = i;
        return i;
      }

      protected Object writeReplace()
        throws ObjectStreamException
      {
        return super.writeReplace();
      }

      public static NamePart parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static NamePart parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static NamePart parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static NamePart parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public static NamePart parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static NamePart parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static NamePart parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static NamePart parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static NamePart parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static NamePart parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static Builder newBuilder()
      {
        return Builder.access$17300();
      }

      public Builder newBuilderForType()
      {
        return newBuilder();
      }

      public static Builder newBuilder(NamePart paramNamePart)
      {
        return newBuilder().mergeFrom(paramNamePart);
      }

      public Builder toBuilder()
      {
        return newBuilder(this);
      }

      protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        Builder localBuilder = new Builder(paramBuilderParent, null);
        return localBuilder;
      }

      static
      {
        defaultInstance.initFields();
      }

      public static final class Builder extends GeneratedMessage.Builder
        implements DescriptorProtos.UninterpretedOption.NamePartOrBuilder
      {
        private int bitField0_;
        private Object namePart_ = "";
        private boolean isExtension_;

        public static final Descriptors.Descriptor getDescriptor()
        {
          return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
        }

        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
        {
          return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
        }

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
        {
          super();
          maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization()
        {
          if (GeneratedMessage.alwaysUseFieldBuilders);
        }

        private static Builder create()
        {
          return new Builder();
        }

        public Builder clear()
        {
          super.clear();
          this.namePart_ = "";
          this.bitField0_ &= -2;
          this.isExtension_ = false;
          this.bitField0_ &= -3;
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public Descriptors.Descriptor getDescriptorForType()
        {
          return DescriptorProtos.UninterpretedOption.NamePart.getDescriptor();
        }

        public DescriptorProtos.UninterpretedOption.NamePart getDefaultInstanceForType()
        {
          return DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance();
        }

        public DescriptorProtos.UninterpretedOption.NamePart build()
        {
          DescriptorProtos.UninterpretedOption.NamePart localNamePart = buildPartial();
          if (!localNamePart.isInitialized())
            throw newUninitializedMessageException(localNamePart);
          return localNamePart;
        }

        private DescriptorProtos.UninterpretedOption.NamePart buildParsed()
          throws InvalidProtocolBufferException
        {
          DescriptorProtos.UninterpretedOption.NamePart localNamePart = buildPartial();
          if (!localNamePart.isInitialized())
            throw newUninitializedMessageException(localNamePart).asInvalidProtocolBufferException();
          return localNamePart;
        }

        public DescriptorProtos.UninterpretedOption.NamePart buildPartial()
        {
          DescriptorProtos.UninterpretedOption.NamePart localNamePart = new DescriptorProtos.UninterpretedOption.NamePart(this, null);
          int i = this.bitField0_;
          int j = 0;
          if ((i & 0x1) == 1)
            j |= 1;
          localNamePart.namePart_ = this.namePart_;
          if ((i & 0x2) == 2)
            j |= 2;
          localNamePart.isExtension_ = this.isExtension_;
          localNamePart.bitField0_ = j;
          onBuilt();
          return localNamePart;
        }

        public Builder mergeFrom(Message paramMessage)
        {
          if ((paramMessage instanceof DescriptorProtos.UninterpretedOption.NamePart))
            return mergeFrom((DescriptorProtos.UninterpretedOption.NamePart)paramMessage);
          super.mergeFrom(paramMessage);
          return this;
        }

        public Builder mergeFrom(DescriptorProtos.UninterpretedOption.NamePart paramNamePart)
        {
          if (paramNamePart == DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance())
            return this;
          if (paramNamePart.hasNamePart())
            setNamePart(paramNamePart.getNamePart());
          if (paramNamePart.hasIsExtension())
            setIsExtension(paramNamePart.getIsExtension());
          mergeUnknownFields(paramNamePart.getUnknownFields());
          return this;
        }

        public final boolean isInitialized()
        {
          if (!hasNamePart())
            return false;
          return hasIsExtension();
        }

        public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
          throws IOException
        {
          UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
          while (true)
          {
            int i = paramCodedInputStream.readTag();
            switch (i)
            {
            case 0:
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            default:
              if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
              {
                setUnknownFields(localBuilder.build());
                onChanged();
                return this;
              }
              break;
            case 10:
              this.bitField0_ |= 1;
              this.namePart_ = paramCodedInputStream.readBytes();
              break;
            case 16:
              this.bitField0_ |= 2;
              this.isExtension_ = paramCodedInputStream.readBool();
            }
          }
        }

        public boolean hasNamePart()
        {
          return (this.bitField0_ & 0x1) == 1;
        }

        public String getNamePart()
        {
          Object localObject = this.namePart_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.namePart_ = str;
            return str;
          }
          return (String)localObject;
        }

        public Builder setNamePart(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ |= 1;
          this.namePart_ = paramString;
          onChanged();
          return this;
        }

        public Builder clearNamePart()
        {
          this.bitField0_ &= -2;
          this.namePart_ = DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance().getNamePart();
          onChanged();
          return this;
        }

        void setNamePart(ByteString paramByteString)
        {
          this.bitField0_ |= 1;
          this.namePart_ = paramByteString;
          onChanged();
        }

        public boolean hasIsExtension()
        {
          return (this.bitField0_ & 0x2) == 2;
        }

        public boolean getIsExtension()
        {
          return this.isExtension_;
        }

        public Builder setIsExtension(boolean paramBoolean)
        {
          this.bitField0_ |= 2;
          this.isExtension_ = paramBoolean;
          onChanged();
          return this;
        }

        public Builder clearIsExtension()
        {
          this.bitField0_ &= -3;
          this.isExtension_ = false;
          onChanged();
          return this;
        }
      }
    }

    public static abstract interface NamePartOrBuilder extends MessageOrBuilder
    {
      public abstract boolean hasNamePart();

      public abstract String getNamePart();

      public abstract boolean hasIsExtension();

      public abstract boolean getIsExtension();
    }
  }

  public static abstract interface UninterpretedOptionOrBuilder extends MessageOrBuilder
  {
    public abstract List getNameList();

    public abstract DescriptorProtos.UninterpretedOption.NamePart getName(int paramInt);

    public abstract int getNameCount();

    public abstract List getNameOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOption.NamePartOrBuilder getNameOrBuilder(int paramInt);

    public abstract boolean hasIdentifierValue();

    public abstract String getIdentifierValue();

    public abstract boolean hasPositiveIntValue();

    public abstract long getPositiveIntValue();

    public abstract boolean hasNegativeIntValue();

    public abstract long getNegativeIntValue();

    public abstract boolean hasDoubleValue();

    public abstract double getDoubleValue();

    public abstract boolean hasStringValue();

    public abstract ByteString getStringValue();

    public abstract boolean hasAggregateValue();

    public abstract String getAggregateValue();
  }

  public static final class MethodOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.MethodOptionsOrBuilder
  {
    private static final MethodOptions defaultInstance = new MethodOptions(true);
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private MethodOptions(Builder paramBuilder)
    {
      super();
    }

    private MethodOptions(boolean paramBoolean)
    {
    }

    public static MethodOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public MethodOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static MethodOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static MethodOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static MethodOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static MethodOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static MethodOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static MethodOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static MethodOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static MethodOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static MethodOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static MethodOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$16400();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(MethodOptions paramMethodOptions)
    {
      return newBuilder().mergeFrom(paramMethodOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.MethodOptionsOrBuilder
    {
      private int bitField0_;
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.MethodOptions.getDescriptor();
      }

      public DescriptorProtos.MethodOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.MethodOptions.getDefaultInstance();
      }

      public DescriptorProtos.MethodOptions build()
      {
        DescriptorProtos.MethodOptions localMethodOptions = buildPartial();
        if (!localMethodOptions.isInitialized())
          throw newUninitializedMessageException(localMethodOptions);
        return localMethodOptions;
      }

      private DescriptorProtos.MethodOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.MethodOptions localMethodOptions = buildPartial();
        if (!localMethodOptions.isInitialized())
          throw newUninitializedMessageException(localMethodOptions).asInvalidProtocolBufferException();
        return localMethodOptions;
      }

      public DescriptorProtos.MethodOptions buildPartial()
      {
        DescriptorProtos.MethodOptions localMethodOptions = new DescriptorProtos.MethodOptions(this, null);
        int i = this.bitField0_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -2;
          }
          localMethodOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localMethodOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        onBuilt();
        return localMethodOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.MethodOptions))
          return mergeFrom((DescriptorProtos.MethodOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.MethodOptions paramMethodOptions)
      {
        if (paramMethodOptions == DescriptorProtos.MethodOptions.getDefaultInstance())
          return this;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramMethodOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramMethodOptions.uninterpretedOption_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramMethodOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramMethodOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramMethodOptions.uninterpretedOption_;
            this.bitField0_ &= -2;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramMethodOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramMethodOptions);
        mergeUnknownFields(paramMethodOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 1;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }
  }

  public static abstract interface MethodOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class ServiceOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.ServiceOptionsOrBuilder
  {
    private static final ServiceOptions defaultInstance = new ServiceOptions(true);
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private ServiceOptions(Builder paramBuilder)
    {
      super();
    }

    private ServiceOptions(boolean paramBoolean)
    {
    }

    public static ServiceOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public ServiceOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static ServiceOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ServiceOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ServiceOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ServiceOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static ServiceOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ServiceOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ServiceOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ServiceOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ServiceOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ServiceOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$15700();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(ServiceOptions paramServiceOptions)
    {
      return newBuilder().mergeFrom(paramServiceOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.ServiceOptionsOrBuilder
    {
      private int bitField0_;
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.ServiceOptions.getDescriptor();
      }

      public DescriptorProtos.ServiceOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.ServiceOptions.getDefaultInstance();
      }

      public DescriptorProtos.ServiceOptions build()
      {
        DescriptorProtos.ServiceOptions localServiceOptions = buildPartial();
        if (!localServiceOptions.isInitialized())
          throw newUninitializedMessageException(localServiceOptions);
        return localServiceOptions;
      }

      private DescriptorProtos.ServiceOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.ServiceOptions localServiceOptions = buildPartial();
        if (!localServiceOptions.isInitialized())
          throw newUninitializedMessageException(localServiceOptions).asInvalidProtocolBufferException();
        return localServiceOptions;
      }

      public DescriptorProtos.ServiceOptions buildPartial()
      {
        DescriptorProtos.ServiceOptions localServiceOptions = new DescriptorProtos.ServiceOptions(this, null);
        int i = this.bitField0_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -2;
          }
          localServiceOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localServiceOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        onBuilt();
        return localServiceOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.ServiceOptions))
          return mergeFrom((DescriptorProtos.ServiceOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.ServiceOptions paramServiceOptions)
      {
        if (paramServiceOptions == DescriptorProtos.ServiceOptions.getDefaultInstance())
          return this;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramServiceOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramServiceOptions.uninterpretedOption_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramServiceOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramServiceOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramServiceOptions.uninterpretedOption_;
            this.bitField0_ &= -2;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramServiceOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramServiceOptions);
        mergeUnknownFields(paramServiceOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 1;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }
  }

  public static abstract interface ServiceOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class EnumValueOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.EnumValueOptionsOrBuilder
  {
    private static final EnumValueOptions defaultInstance = new EnumValueOptions(true);
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private EnumValueOptions(Builder paramBuilder)
    {
      super();
    }

    private EnumValueOptions(boolean paramBoolean)
    {
    }

    public static EnumValueOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public EnumValueOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static EnumValueOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static EnumValueOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumValueOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static EnumValueOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumValueOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static EnumValueOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumValueOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumValueOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumValueOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static EnumValueOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$15000();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(EnumValueOptions paramEnumValueOptions)
    {
      return newBuilder().mergeFrom(paramEnumValueOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.EnumValueOptionsOrBuilder
    {
      private int bitField0_;
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.EnumValueOptions.getDescriptor();
      }

      public DescriptorProtos.EnumValueOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.EnumValueOptions.getDefaultInstance();
      }

      public DescriptorProtos.EnumValueOptions build()
      {
        DescriptorProtos.EnumValueOptions localEnumValueOptions = buildPartial();
        if (!localEnumValueOptions.isInitialized())
          throw newUninitializedMessageException(localEnumValueOptions);
        return localEnumValueOptions;
      }

      private DescriptorProtos.EnumValueOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.EnumValueOptions localEnumValueOptions = buildPartial();
        if (!localEnumValueOptions.isInitialized())
          throw newUninitializedMessageException(localEnumValueOptions).asInvalidProtocolBufferException();
        return localEnumValueOptions;
      }

      public DescriptorProtos.EnumValueOptions buildPartial()
      {
        DescriptorProtos.EnumValueOptions localEnumValueOptions = new DescriptorProtos.EnumValueOptions(this, null);
        int i = this.bitField0_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -2;
          }
          localEnumValueOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localEnumValueOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        onBuilt();
        return localEnumValueOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.EnumValueOptions))
          return mergeFrom((DescriptorProtos.EnumValueOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.EnumValueOptions paramEnumValueOptions)
      {
        if (paramEnumValueOptions == DescriptorProtos.EnumValueOptions.getDefaultInstance())
          return this;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramEnumValueOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramEnumValueOptions.uninterpretedOption_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramEnumValueOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramEnumValueOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramEnumValueOptions.uninterpretedOption_;
            this.bitField0_ &= -2;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramEnumValueOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramEnumValueOptions);
        mergeUnknownFields(paramEnumValueOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 1;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }
  }

  public static abstract interface EnumValueOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class EnumOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.EnumOptionsOrBuilder
  {
    private static final EnumOptions defaultInstance = new EnumOptions(true);
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private EnumOptions(Builder paramBuilder)
    {
      super();
    }

    private EnumOptions(boolean paramBoolean)
    {
    }

    public static EnumOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public EnumOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static EnumOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static EnumOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static EnumOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static EnumOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static EnumOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$14300();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(EnumOptions paramEnumOptions)
    {
      return newBuilder().mergeFrom(paramEnumOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.EnumOptionsOrBuilder
    {
      private int bitField0_;
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.EnumOptions.getDescriptor();
      }

      public DescriptorProtos.EnumOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.EnumOptions.getDefaultInstance();
      }

      public DescriptorProtos.EnumOptions build()
      {
        DescriptorProtos.EnumOptions localEnumOptions = buildPartial();
        if (!localEnumOptions.isInitialized())
          throw newUninitializedMessageException(localEnumOptions);
        return localEnumOptions;
      }

      private DescriptorProtos.EnumOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.EnumOptions localEnumOptions = buildPartial();
        if (!localEnumOptions.isInitialized())
          throw newUninitializedMessageException(localEnumOptions).asInvalidProtocolBufferException();
        return localEnumOptions;
      }

      public DescriptorProtos.EnumOptions buildPartial()
      {
        DescriptorProtos.EnumOptions localEnumOptions = new DescriptorProtos.EnumOptions(this, null);
        int i = this.bitField0_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -2;
          }
          localEnumOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localEnumOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        onBuilt();
        return localEnumOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.EnumOptions))
          return mergeFrom((DescriptorProtos.EnumOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.EnumOptions paramEnumOptions)
      {
        if (paramEnumOptions == DescriptorProtos.EnumOptions.getDefaultInstance())
          return this;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramEnumOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramEnumOptions.uninterpretedOption_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramEnumOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramEnumOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramEnumOptions.uninterpretedOption_;
            this.bitField0_ &= -2;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramEnumOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramEnumOptions);
        mergeUnknownFields(paramEnumOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 1;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }
  }

  public static abstract interface EnumOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class FieldOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.FieldOptionsOrBuilder
  {
    private static final FieldOptions defaultInstance = new FieldOptions(true);
    private int bitField0_;
    public static final int CTYPE_FIELD_NUMBER = 1;
    private CType ctype_;
    public static final int PACKED_FIELD_NUMBER = 2;
    private boolean packed_;
    public static final int DEPRECATED_FIELD_NUMBER = 3;
    private boolean deprecated_;
    public static final int EXPERIMENTAL_MAP_KEY_FIELD_NUMBER = 9;
    private Object experimentalMapKey_;
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private FieldOptions(Builder paramBuilder)
    {
      super();
    }

    private FieldOptions(boolean paramBoolean)
    {
    }

    public static FieldOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public FieldOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
    }

    public boolean hasCtype()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public CType getCtype()
    {
      return this.ctype_;
    }

    public boolean hasPacked()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public boolean getPacked()
    {
      return this.packed_;
    }

    public boolean hasDeprecated()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public boolean getDeprecated()
    {
      return this.deprecated_;
    }

    public boolean hasExperimentalMapKey()
    {
      return (this.bitField0_ & 0x8) == 8;
    }

    public String getExperimentalMapKey()
    {
      Object localObject = this.experimentalMapKey_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.experimentalMapKey_ = str;
      return str;
    }

    private ByteString getExperimentalMapKeyBytes()
    {
      Object localObject = this.experimentalMapKey_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.experimentalMapKey_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.ctype_ = CType.STRING;
      this.packed_ = false;
      this.deprecated_ = false;
      this.experimentalMapKey_ = "";
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeEnum(1, this.ctype_.getNumber());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeBool(2, this.packed_);
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeBool(3, this.deprecated_);
      if ((this.bitField0_ & 0x8) == 8)
        paramCodedOutputStream.writeBytes(9, getExperimentalMapKeyBytes());
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeEnumSize(1, this.ctype_.getNumber());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeBoolSize(2, this.packed_);
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeBoolSize(3, this.deprecated_);
      if ((this.bitField0_ & 0x8) == 8)
        i += CodedOutputStream.computeBytesSize(9, getExperimentalMapKeyBytes());
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static FieldOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FieldOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FieldOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FieldOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static FieldOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FieldOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FieldOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FieldOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FieldOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FieldOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$13100();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(FieldOptions paramFieldOptions)
    {
      return newBuilder().mergeFrom(paramFieldOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.FieldOptionsOrBuilder
    {
      private int bitField0_;
      private DescriptorProtos.FieldOptions.CType ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
      private boolean packed_;
      private boolean deprecated_;
      private Object experimentalMapKey_ = "";
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
        this.bitField0_ &= -2;
        this.packed_ = false;
        this.bitField0_ &= -3;
        this.deprecated_ = false;
        this.bitField0_ &= -5;
        this.experimentalMapKey_ = "";
        this.bitField0_ &= -9;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -17;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.FieldOptions.getDescriptor();
      }

      public DescriptorProtos.FieldOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.FieldOptions.getDefaultInstance();
      }

      public DescriptorProtos.FieldOptions build()
      {
        DescriptorProtos.FieldOptions localFieldOptions = buildPartial();
        if (!localFieldOptions.isInitialized())
          throw newUninitializedMessageException(localFieldOptions);
        return localFieldOptions;
      }

      private DescriptorProtos.FieldOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.FieldOptions localFieldOptions = buildPartial();
        if (!localFieldOptions.isInitialized())
          throw newUninitializedMessageException(localFieldOptions).asInvalidProtocolBufferException();
        return localFieldOptions;
      }

      public DescriptorProtos.FieldOptions buildPartial()
      {
        DescriptorProtos.FieldOptions localFieldOptions = new DescriptorProtos.FieldOptions(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localFieldOptions.ctype_ = this.ctype_;
        if ((i & 0x2) == 2)
          j |= 2;
        localFieldOptions.packed_ = this.packed_;
        if ((i & 0x4) == 4)
          j |= 4;
        localFieldOptions.deprecated_ = this.deprecated_;
        if ((i & 0x8) == 8)
          j |= 8;
        localFieldOptions.experimentalMapKey_ = this.experimentalMapKey_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x10) == 16)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -17;
          }
          localFieldOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localFieldOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        localFieldOptions.bitField0_ = j;
        onBuilt();
        return localFieldOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.FieldOptions))
          return mergeFrom((DescriptorProtos.FieldOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.FieldOptions paramFieldOptions)
      {
        if (paramFieldOptions == DescriptorProtos.FieldOptions.getDefaultInstance())
          return this;
        if (paramFieldOptions.hasCtype())
          setCtype(paramFieldOptions.getCtype());
        if (paramFieldOptions.hasPacked())
          setPacked(paramFieldOptions.getPacked());
        if (paramFieldOptions.hasDeprecated())
          setDeprecated(paramFieldOptions.getDeprecated());
        if (paramFieldOptions.hasExperimentalMapKey())
          setExperimentalMapKey(paramFieldOptions.getExperimentalMapKey());
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramFieldOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramFieldOptions.uninterpretedOption_;
              this.bitField0_ &= -17;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramFieldOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramFieldOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramFieldOptions.uninterpretedOption_;
            this.bitField0_ &= -17;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramFieldOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramFieldOptions);
        mergeUnknownFields(paramFieldOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 8:
            int j = paramCodedInputStream.readEnum();
            DescriptorProtos.FieldOptions.CType localCType = DescriptorProtos.FieldOptions.CType.valueOf(j);
            if (localCType == null)
            {
              localBuilder.mergeVarintField(1, j);
            }
            else
            {
              this.bitField0_ |= 1;
              this.ctype_ = localCType;
            }
            break;
          case 16:
            this.bitField0_ |= 2;
            this.packed_ = paramCodedInputStream.readBool();
            break;
          case 24:
            this.bitField0_ |= 4;
            this.deprecated_ = paramCodedInputStream.readBool();
            break;
          case 74:
            this.bitField0_ |= 8;
            this.experimentalMapKey_ = paramCodedInputStream.readBytes();
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      public boolean hasCtype()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public DescriptorProtos.FieldOptions.CType getCtype()
      {
        return this.ctype_;
      }

      public Builder setCtype(DescriptorProtos.FieldOptions.CType paramCType)
      {
        if (paramCType == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.ctype_ = paramCType;
        onChanged();
        return this;
      }

      public Builder clearCtype()
      {
        this.bitField0_ &= -2;
        this.ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
        onChanged();
        return this;
      }

      public boolean hasPacked()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public boolean getPacked()
      {
        return this.packed_;
      }

      public Builder setPacked(boolean paramBoolean)
      {
        this.bitField0_ |= 2;
        this.packed_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearPacked()
      {
        this.bitField0_ &= -3;
        this.packed_ = false;
        onChanged();
        return this;
      }

      public boolean hasDeprecated()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public boolean getDeprecated()
      {
        return this.deprecated_;
      }

      public Builder setDeprecated(boolean paramBoolean)
      {
        this.bitField0_ |= 4;
        this.deprecated_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearDeprecated()
      {
        this.bitField0_ &= -5;
        this.deprecated_ = false;
        onChanged();
        return this;
      }

      public boolean hasExperimentalMapKey()
      {
        return (this.bitField0_ & 0x8) == 8;
      }

      public String getExperimentalMapKey()
      {
        Object localObject = this.experimentalMapKey_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.experimentalMapKey_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setExperimentalMapKey(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 8;
        this.experimentalMapKey_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearExperimentalMapKey()
      {
        this.bitField0_ &= -9;
        this.experimentalMapKey_ = DescriptorProtos.FieldOptions.getDefaultInstance().getExperimentalMapKey();
        onChanged();
        return this;
      }

      void setExperimentalMapKey(ByteString paramByteString)
      {
        this.bitField0_ |= 8;
        this.experimentalMapKey_ = paramByteString;
        onChanged();
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x10) != 16)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 16;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -17;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x10) == 16, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }

    public static enum CType
      implements ProtocolMessageEnum
    {
      STRING(0, 0), CORD(1, 1), STRING_PIECE(2, 2);

      public static final int STRING_VALUE = 0;
      public static final int CORD_VALUE = 1;
      public static final int STRING_PIECE_VALUE = 2;
      private static Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap()
      {
        public DescriptorProtos.FieldOptions.CType findValueByNumber(int paramAnonymousInt)
        {
          return DescriptorProtos.FieldOptions.CType.valueOf(paramAnonymousInt);
        }
      };
      private static final CType[] VALUES = { STRING, CORD, STRING_PIECE };
      private final int index;
      private final int value;

      public final int getNumber()
      {
        return this.value;
      }

      public static CType valueOf(int paramInt)
      {
        switch (paramInt)
        {
        case 0:
          return STRING;
        case 1:
          return CORD;
        case 2:
          return STRING_PIECE;
        }
        return null;
      }

      public static Internal.EnumLiteMap internalGetValueMap()
      {
        return internalValueMap;
      }

      public final Descriptors.EnumValueDescriptor getValueDescriptor()
      {
        return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
      }

      public final Descriptors.EnumDescriptor getDescriptorForType()
      {
        return getDescriptor();
      }

      public static final Descriptors.EnumDescriptor getDescriptor()
      {
        return (Descriptors.EnumDescriptor)DescriptorProtos.FieldOptions.getDescriptor().getEnumTypes().get(0);
      }

      public static CType valueOf(Descriptors.EnumValueDescriptor paramEnumValueDescriptor)
      {
        if (paramEnumValueDescriptor.getType() != getDescriptor())
          throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        return VALUES[paramEnumValueDescriptor.getIndex()];
      }

      private CType(int arg3, int arg4)
      {
        int i;
        this.index = i;
        int j;
        this.value = j;
      }
    }
  }

  public static abstract interface FieldOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract boolean hasCtype();

    public abstract DescriptorProtos.FieldOptions.CType getCtype();

    public abstract boolean hasPacked();

    public abstract boolean getPacked();

    public abstract boolean hasDeprecated();

    public abstract boolean getDeprecated();

    public abstract boolean hasExperimentalMapKey();

    public abstract String getExperimentalMapKey();

    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class MessageOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.MessageOptionsOrBuilder
  {
    private static final MessageOptions defaultInstance = new MessageOptions(true);
    private int bitField0_;
    public static final int MESSAGE_SET_WIRE_FORMAT_FIELD_NUMBER = 1;
    private boolean messageSetWireFormat_;
    public static final int NO_STANDARD_DESCRIPTOR_ACCESSOR_FIELD_NUMBER = 2;
    private boolean noStandardDescriptorAccessor_;
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private MessageOptions(Builder paramBuilder)
    {
      super();
    }

    private MessageOptions(boolean paramBoolean)
    {
    }

    public static MessageOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public MessageOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
    }

    public boolean hasMessageSetWireFormat()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public boolean getMessageSetWireFormat()
    {
      return this.messageSetWireFormat_;
    }

    public boolean hasNoStandardDescriptorAccessor()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public boolean getNoStandardDescriptorAccessor()
    {
      return this.noStandardDescriptorAccessor_;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.messageSetWireFormat_ = false;
      this.noStandardDescriptorAccessor_ = false;
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBool(1, this.messageSetWireFormat_);
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeBool(2, this.noStandardDescriptorAccessor_);
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBoolSize(1, this.messageSetWireFormat_);
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeBoolSize(2, this.noStandardDescriptorAccessor_);
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static MessageOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static MessageOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static MessageOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static MessageOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static MessageOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static MessageOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static MessageOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static MessageOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static MessageOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static MessageOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$12100();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(MessageOptions paramMessageOptions)
    {
      return newBuilder().mergeFrom(paramMessageOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.MessageOptionsOrBuilder
    {
      private int bitField0_;
      private boolean messageSetWireFormat_;
      private boolean noStandardDescriptorAccessor_;
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.messageSetWireFormat_ = false;
        this.bitField0_ &= -2;
        this.noStandardDescriptorAccessor_ = false;
        this.bitField0_ &= -3;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -5;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.MessageOptions.getDescriptor();
      }

      public DescriptorProtos.MessageOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.MessageOptions.getDefaultInstance();
      }

      public DescriptorProtos.MessageOptions build()
      {
        DescriptorProtos.MessageOptions localMessageOptions = buildPartial();
        if (!localMessageOptions.isInitialized())
          throw newUninitializedMessageException(localMessageOptions);
        return localMessageOptions;
      }

      private DescriptorProtos.MessageOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.MessageOptions localMessageOptions = buildPartial();
        if (!localMessageOptions.isInitialized())
          throw newUninitializedMessageException(localMessageOptions).asInvalidProtocolBufferException();
        return localMessageOptions;
      }

      public DescriptorProtos.MessageOptions buildPartial()
      {
        DescriptorProtos.MessageOptions localMessageOptions = new DescriptorProtos.MessageOptions(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localMessageOptions.messageSetWireFormat_ = this.messageSetWireFormat_;
        if ((i & 0x2) == 2)
          j |= 2;
        localMessageOptions.noStandardDescriptorAccessor_ = this.noStandardDescriptorAccessor_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x4) == 4)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -5;
          }
          localMessageOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localMessageOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        localMessageOptions.bitField0_ = j;
        onBuilt();
        return localMessageOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.MessageOptions))
          return mergeFrom((DescriptorProtos.MessageOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.MessageOptions paramMessageOptions)
      {
        if (paramMessageOptions == DescriptorProtos.MessageOptions.getDefaultInstance())
          return this;
        if (paramMessageOptions.hasMessageSetWireFormat())
          setMessageSetWireFormat(paramMessageOptions.getMessageSetWireFormat());
        if (paramMessageOptions.hasNoStandardDescriptorAccessor())
          setNoStandardDescriptorAccessor(paramMessageOptions.getNoStandardDescriptorAccessor());
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramMessageOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramMessageOptions.uninterpretedOption_;
              this.bitField0_ &= -5;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramMessageOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramMessageOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramMessageOptions.uninterpretedOption_;
            this.bitField0_ &= -5;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramMessageOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramMessageOptions);
        mergeUnknownFields(paramMessageOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 8:
            this.bitField0_ |= 1;
            this.messageSetWireFormat_ = paramCodedInputStream.readBool();
            break;
          case 16:
            this.bitField0_ |= 2;
            this.noStandardDescriptorAccessor_ = paramCodedInputStream.readBool();
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      public boolean hasMessageSetWireFormat()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public boolean getMessageSetWireFormat()
      {
        return this.messageSetWireFormat_;
      }

      public Builder setMessageSetWireFormat(boolean paramBoolean)
      {
        this.bitField0_ |= 1;
        this.messageSetWireFormat_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearMessageSetWireFormat()
      {
        this.bitField0_ &= -2;
        this.messageSetWireFormat_ = false;
        onChanged();
        return this;
      }

      public boolean hasNoStandardDescriptorAccessor()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public boolean getNoStandardDescriptorAccessor()
      {
        return this.noStandardDescriptorAccessor_;
      }

      public Builder setNoStandardDescriptorAccessor(boolean paramBoolean)
      {
        this.bitField0_ |= 2;
        this.noStandardDescriptorAccessor_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearNoStandardDescriptorAccessor()
      {
        this.bitField0_ &= -3;
        this.noStandardDescriptorAccessor_ = false;
        onChanged();
        return this;
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x4) != 4)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 4;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -5;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x4) == 4, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }
  }

  public static abstract interface MessageOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract boolean hasMessageSetWireFormat();

    public abstract boolean getMessageSetWireFormat();

    public abstract boolean hasNoStandardDescriptorAccessor();

    public abstract boolean getNoStandardDescriptorAccessor();

    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class FileOptions extends GeneratedMessage.ExtendableMessage
    implements DescriptorProtos.FileOptionsOrBuilder
  {
    private static final FileOptions defaultInstance = new FileOptions(true);
    private int bitField0_;
    public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
    private Object javaPackage_;
    public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
    private Object javaOuterClassname_;
    public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
    private boolean javaMultipleFiles_;
    public static final int JAVA_GENERATE_EQUALS_AND_HASH_FIELD_NUMBER = 20;
    private boolean javaGenerateEqualsAndHash_;
    public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
    private OptimizeMode optimizeFor_;
    public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
    private boolean ccGenericServices_;
    public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
    private boolean javaGenericServices_;
    public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
    private boolean pyGenericServices_;
    public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
    private List uninterpretedOption_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private FileOptions(Builder paramBuilder)
    {
      super();
    }

    private FileOptions(boolean paramBoolean)
    {
    }

    public static FileOptions getDefaultInstance()
    {
      return defaultInstance;
    }

    public FileOptions getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable;
    }

    public boolean hasJavaPackage()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getJavaPackage()
    {
      Object localObject = this.javaPackage_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.javaPackage_ = str;
      return str;
    }

    private ByteString getJavaPackageBytes()
    {
      Object localObject = this.javaPackage_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.javaPackage_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasJavaOuterClassname()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public String getJavaOuterClassname()
    {
      Object localObject = this.javaOuterClassname_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.javaOuterClassname_ = str;
      return str;
    }

    private ByteString getJavaOuterClassnameBytes()
    {
      Object localObject = this.javaOuterClassname_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.javaOuterClassname_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasJavaMultipleFiles()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public boolean getJavaMultipleFiles()
    {
      return this.javaMultipleFiles_;
    }

    public boolean hasJavaGenerateEqualsAndHash()
    {
      return (this.bitField0_ & 0x8) == 8;
    }

    public boolean getJavaGenerateEqualsAndHash()
    {
      return this.javaGenerateEqualsAndHash_;
    }

    public boolean hasOptimizeFor()
    {
      return (this.bitField0_ & 0x10) == 16;
    }

    public OptimizeMode getOptimizeFor()
    {
      return this.optimizeFor_;
    }

    public boolean hasCcGenericServices()
    {
      return (this.bitField0_ & 0x20) == 32;
    }

    public boolean getCcGenericServices()
    {
      return this.ccGenericServices_;
    }

    public boolean hasJavaGenericServices()
    {
      return (this.bitField0_ & 0x40) == 64;
    }

    public boolean getJavaGenericServices()
    {
      return this.javaGenericServices_;
    }

    public boolean hasPyGenericServices()
    {
      return (this.bitField0_ & 0x80) == 128;
    }

    public boolean getPyGenericServices()
    {
      return this.pyGenericServices_;
    }

    public List getUninterpretedOptionList()
    {
      return this.uninterpretedOption_;
    }

    public List getUninterpretedOptionOrBuilderList()
    {
      return this.uninterpretedOption_;
    }

    public int getUninterpretedOptionCount()
    {
      return this.uninterpretedOption_.size();
    }

    public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
    }

    public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
    }

    private void initFields()
    {
      this.javaPackage_ = "";
      this.javaOuterClassname_ = "";
      this.javaMultipleFiles_ = false;
      this.javaGenerateEqualsAndHash_ = false;
      this.optimizeFor_ = OptimizeMode.SPEED;
      this.ccGenericServices_ = false;
      this.javaGenericServices_ = false;
      this.pyGenericServices_ = false;
      this.uninterpretedOption_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUninterpretedOptionCount(); j++)
        if (!getUninterpretedOption(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      GeneratedMessage.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getJavaPackageBytes());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeBytes(8, getJavaOuterClassnameBytes());
      if ((this.bitField0_ & 0x10) == 16)
        paramCodedOutputStream.writeEnum(9, this.optimizeFor_.getNumber());
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeBool(10, this.javaMultipleFiles_);
      if ((this.bitField0_ & 0x20) == 32)
        paramCodedOutputStream.writeBool(16, this.ccGenericServices_);
      if ((this.bitField0_ & 0x40) == 64)
        paramCodedOutputStream.writeBool(17, this.javaGenericServices_);
      if ((this.bitField0_ & 0x80) == 128)
        paramCodedOutputStream.writeBool(18, this.pyGenericServices_);
      if ((this.bitField0_ & 0x8) == 8)
        paramCodedOutputStream.writeBool(20, this.javaGenerateEqualsAndHash_);
      for (int i = 0; i < this.uninterpretedOption_.size(); i++)
        paramCodedOutputStream.writeMessage(999, (MessageLite)this.uninterpretedOption_.get(i));
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getJavaPackageBytes());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeBytesSize(8, getJavaOuterClassnameBytes());
      if ((this.bitField0_ & 0x10) == 16)
        i += CodedOutputStream.computeEnumSize(9, this.optimizeFor_.getNumber());
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeBoolSize(10, this.javaMultipleFiles_);
      if ((this.bitField0_ & 0x20) == 32)
        i += CodedOutputStream.computeBoolSize(16, this.ccGenericServices_);
      if ((this.bitField0_ & 0x40) == 64)
        i += CodedOutputStream.computeBoolSize(17, this.javaGenericServices_);
      if ((this.bitField0_ & 0x80) == 128)
        i += CodedOutputStream.computeBoolSize(18, this.pyGenericServices_);
      if ((this.bitField0_ & 0x8) == 8)
        i += CodedOutputStream.computeBoolSize(20, this.javaGenerateEqualsAndHash_);
      for (int j = 0; j < this.uninterpretedOption_.size(); j++)
        i += CodedOutputStream.computeMessageSize(999, (MessageLite)this.uninterpretedOption_.get(j));
      i += extensionsSerializedSize();
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static FileOptions parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FileOptions parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileOptions parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FileOptions parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileOptions parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FileOptions parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileOptions parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FileOptions parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FileOptions parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FileOptions parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$10500();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(FileOptions paramFileOptions)
    {
      return newBuilder().mergeFrom(paramFileOptions);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.ExtendableBuilder
      implements DescriptorProtos.FileOptionsOrBuilder
    {
      private int bitField0_;
      private Object javaPackage_ = "";
      private Object javaOuterClassname_ = "";
      private boolean javaMultipleFiles_;
      private boolean javaGenerateEqualsAndHash_;
      private DescriptorProtos.FileOptions.OptimizeMode optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
      private boolean ccGenericServices_;
      private boolean javaGenericServices_;
      private boolean pyGenericServices_;
      private List uninterpretedOption_ = Collections.emptyList();
      private RepeatedFieldBuilder uninterpretedOptionBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getUninterpretedOptionFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.javaPackage_ = "";
        this.bitField0_ &= -2;
        this.javaOuterClassname_ = "";
        this.bitField0_ &= -3;
        this.javaMultipleFiles_ = false;
        this.bitField0_ &= -5;
        this.javaGenerateEqualsAndHash_ = false;
        this.bitField0_ &= -9;
        this.optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
        this.bitField0_ &= -17;
        this.ccGenericServices_ = false;
        this.bitField0_ &= -33;
        this.javaGenericServices_ = false;
        this.bitField0_ &= -65;
        this.pyGenericServices_ = false;
        this.bitField0_ &= -129;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -257;
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.FileOptions.getDescriptor();
      }

      public DescriptorProtos.FileOptions getDefaultInstanceForType()
      {
        return DescriptorProtos.FileOptions.getDefaultInstance();
      }

      public DescriptorProtos.FileOptions build()
      {
        DescriptorProtos.FileOptions localFileOptions = buildPartial();
        if (!localFileOptions.isInitialized())
          throw newUninitializedMessageException(localFileOptions);
        return localFileOptions;
      }

      private DescriptorProtos.FileOptions buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.FileOptions localFileOptions = buildPartial();
        if (!localFileOptions.isInitialized())
          throw newUninitializedMessageException(localFileOptions).asInvalidProtocolBufferException();
        return localFileOptions;
      }

      public DescriptorProtos.FileOptions buildPartial()
      {
        DescriptorProtos.FileOptions localFileOptions = new DescriptorProtos.FileOptions(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localFileOptions.javaPackage_ = this.javaPackage_;
        if ((i & 0x2) == 2)
          j |= 2;
        localFileOptions.javaOuterClassname_ = this.javaOuterClassname_;
        if ((i & 0x4) == 4)
          j |= 4;
        localFileOptions.javaMultipleFiles_ = this.javaMultipleFiles_;
        if ((i & 0x8) == 8)
          j |= 8;
        localFileOptions.javaGenerateEqualsAndHash_ = this.javaGenerateEqualsAndHash_;
        if ((i & 0x10) == 16)
          j |= 16;
        localFileOptions.optimizeFor_ = this.optimizeFor_;
        if ((i & 0x20) == 32)
          j |= 32;
        localFileOptions.ccGenericServices_ = this.ccGenericServices_;
        if ((i & 0x40) == 64)
          j |= 64;
        localFileOptions.javaGenericServices_ = this.javaGenericServices_;
        if ((i & 0x80) == 128)
          j |= 128;
        localFileOptions.pyGenericServices_ = this.pyGenericServices_;
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x100) == 256)
          {
            this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            this.bitField0_ &= -257;
          }
          localFileOptions.uninterpretedOption_ = this.uninterpretedOption_;
        }
        else
        {
          localFileOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
        }
        localFileOptions.bitField0_ = j;
        onBuilt();
        return localFileOptions;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.FileOptions))
          return mergeFrom((DescriptorProtos.FileOptions)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.FileOptions paramFileOptions)
      {
        if (paramFileOptions == DescriptorProtos.FileOptions.getDefaultInstance())
          return this;
        if (paramFileOptions.hasJavaPackage())
          setJavaPackage(paramFileOptions.getJavaPackage());
        if (paramFileOptions.hasJavaOuterClassname())
          setJavaOuterClassname(paramFileOptions.getJavaOuterClassname());
        if (paramFileOptions.hasJavaMultipleFiles())
          setJavaMultipleFiles(paramFileOptions.getJavaMultipleFiles());
        if (paramFileOptions.hasJavaGenerateEqualsAndHash())
          setJavaGenerateEqualsAndHash(paramFileOptions.getJavaGenerateEqualsAndHash());
        if (paramFileOptions.hasOptimizeFor())
          setOptimizeFor(paramFileOptions.getOptimizeFor());
        if (paramFileOptions.hasCcGenericServices())
          setCcGenericServices(paramFileOptions.getCcGenericServices());
        if (paramFileOptions.hasJavaGenericServices())
          setJavaGenericServices(paramFileOptions.getJavaGenericServices());
        if (paramFileOptions.hasPyGenericServices())
          setPyGenericServices(paramFileOptions.getPyGenericServices());
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (!paramFileOptions.uninterpretedOption_.isEmpty())
          {
            if (this.uninterpretedOption_.isEmpty())
            {
              this.uninterpretedOption_ = paramFileOptions.uninterpretedOption_;
              this.bitField0_ &= -257;
            }
            else
            {
              ensureUninterpretedOptionIsMutable();
              this.uninterpretedOption_.addAll(paramFileOptions.uninterpretedOption_);
            }
            onChanged();
          }
        }
        else if (!paramFileOptions.uninterpretedOption_.isEmpty())
          if (this.uninterpretedOptionBuilder_.isEmpty())
          {
            this.uninterpretedOptionBuilder_.dispose();
            this.uninterpretedOptionBuilder_ = null;
            this.uninterpretedOption_ = paramFileOptions.uninterpretedOption_;
            this.bitField0_ &= -257;
            this.uninterpretedOptionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null);
          }
          else
          {
            this.uninterpretedOptionBuilder_.addAllMessages(paramFileOptions.uninterpretedOption_);
          }
        mergeExtensionFields(paramFileOptions);
        mergeUnknownFields(paramFileOptions.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUninterpretedOptionCount(); i++)
          if (!getUninterpretedOption(i).isInitialized())
            return false;
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.javaPackage_ = paramCodedInputStream.readBytes();
            break;
          case 66:
            this.bitField0_ |= 2;
            this.javaOuterClassname_ = paramCodedInputStream.readBytes();
            break;
          case 72:
            int j = paramCodedInputStream.readEnum();
            DescriptorProtos.FileOptions.OptimizeMode localOptimizeMode = DescriptorProtos.FileOptions.OptimizeMode.valueOf(j);
            if (localOptimizeMode == null)
            {
              localBuilder.mergeVarintField(9, j);
            }
            else
            {
              this.bitField0_ |= 16;
              this.optimizeFor_ = localOptimizeMode;
            }
            break;
          case 80:
            this.bitField0_ |= 4;
            this.javaMultipleFiles_ = paramCodedInputStream.readBool();
            break;
          case 128:
            this.bitField0_ |= 32;
            this.ccGenericServices_ = paramCodedInputStream.readBool();
            break;
          case 136:
            this.bitField0_ |= 64;
            this.javaGenericServices_ = paramCodedInputStream.readBool();
            break;
          case 144:
            this.bitField0_ |= 128;
            this.pyGenericServices_ = paramCodedInputStream.readBool();
            break;
          case 160:
            this.bitField0_ |= 8;
            this.javaGenerateEqualsAndHash_ = paramCodedInputStream.readBool();
            break;
          case 7994:
            DescriptorProtos.UninterpretedOption.Builder localBuilder1 = DescriptorProtos.UninterpretedOption.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addUninterpretedOption(localBuilder1.buildPartial());
          }
        }
      }

      public boolean hasJavaPackage()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getJavaPackage()
      {
        Object localObject = this.javaPackage_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.javaPackage_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setJavaPackage(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.javaPackage_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearJavaPackage()
      {
        this.bitField0_ &= -2;
        this.javaPackage_ = DescriptorProtos.FileOptions.getDefaultInstance().getJavaPackage();
        onChanged();
        return this;
      }

      void setJavaPackage(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.javaPackage_ = paramByteString;
        onChanged();
      }

      public boolean hasJavaOuterClassname()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public String getJavaOuterClassname()
      {
        Object localObject = this.javaOuterClassname_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.javaOuterClassname_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setJavaOuterClassname(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 2;
        this.javaOuterClassname_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearJavaOuterClassname()
      {
        this.bitField0_ &= -3;
        this.javaOuterClassname_ = DescriptorProtos.FileOptions.getDefaultInstance().getJavaOuterClassname();
        onChanged();
        return this;
      }

      void setJavaOuterClassname(ByteString paramByteString)
      {
        this.bitField0_ |= 2;
        this.javaOuterClassname_ = paramByteString;
        onChanged();
      }

      public boolean hasJavaMultipleFiles()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public boolean getJavaMultipleFiles()
      {
        return this.javaMultipleFiles_;
      }

      public Builder setJavaMultipleFiles(boolean paramBoolean)
      {
        this.bitField0_ |= 4;
        this.javaMultipleFiles_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearJavaMultipleFiles()
      {
        this.bitField0_ &= -5;
        this.javaMultipleFiles_ = false;
        onChanged();
        return this;
      }

      public boolean hasJavaGenerateEqualsAndHash()
      {
        return (this.bitField0_ & 0x8) == 8;
      }

      public boolean getJavaGenerateEqualsAndHash()
      {
        return this.javaGenerateEqualsAndHash_;
      }

      public Builder setJavaGenerateEqualsAndHash(boolean paramBoolean)
      {
        this.bitField0_ |= 8;
        this.javaGenerateEqualsAndHash_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearJavaGenerateEqualsAndHash()
      {
        this.bitField0_ &= -9;
        this.javaGenerateEqualsAndHash_ = false;
        onChanged();
        return this;
      }

      public boolean hasOptimizeFor()
      {
        return (this.bitField0_ & 0x10) == 16;
      }

      public DescriptorProtos.FileOptions.OptimizeMode getOptimizeFor()
      {
        return this.optimizeFor_;
      }

      public Builder setOptimizeFor(DescriptorProtos.FileOptions.OptimizeMode paramOptimizeMode)
      {
        if (paramOptimizeMode == null)
          throw new NullPointerException();
        this.bitField0_ |= 16;
        this.optimizeFor_ = paramOptimizeMode;
        onChanged();
        return this;
      }

      public Builder clearOptimizeFor()
      {
        this.bitField0_ &= -17;
        this.optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
        onChanged();
        return this;
      }

      public boolean hasCcGenericServices()
      {
        return (this.bitField0_ & 0x20) == 32;
      }

      public boolean getCcGenericServices()
      {
        return this.ccGenericServices_;
      }

      public Builder setCcGenericServices(boolean paramBoolean)
      {
        this.bitField0_ |= 32;
        this.ccGenericServices_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearCcGenericServices()
      {
        this.bitField0_ &= -33;
        this.ccGenericServices_ = false;
        onChanged();
        return this;
      }

      public boolean hasJavaGenericServices()
      {
        return (this.bitField0_ & 0x40) == 64;
      }

      public boolean getJavaGenericServices()
      {
        return this.javaGenericServices_;
      }

      public Builder setJavaGenericServices(boolean paramBoolean)
      {
        this.bitField0_ |= 64;
        this.javaGenericServices_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearJavaGenericServices()
      {
        this.bitField0_ &= -65;
        this.javaGenericServices_ = false;
        onChanged();
        return this;
      }

      public boolean hasPyGenericServices()
      {
        return (this.bitField0_ & 0x80) == 128;
      }

      public boolean getPyGenericServices()
      {
        return this.pyGenericServices_;
      }

      public Builder setPyGenericServices(boolean paramBoolean)
      {
        this.bitField0_ |= 128;
        this.pyGenericServices_ = paramBoolean;
        onChanged();
        return this;
      }

      public Builder clearPyGenericServices()
      {
        this.bitField0_ &= -129;
        this.pyGenericServices_ = false;
        onChanged();
        return this;
      }

      private void ensureUninterpretedOptionIsMutable()
      {
        if ((this.bitField0_ & 0x100) != 256)
        {
          this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
          this.bitField0_ |= 256;
        }
      }

      public List getUninterpretedOptionList()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return Collections.unmodifiableList(this.uninterpretedOption_);
        return this.uninterpretedOptionBuilder_.getMessageList();
      }

      public int getUninterpretedOptionCount()
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return this.uninterpretedOption_.size();
        return this.uninterpretedOptionBuilder_.getCount();
      }

      public DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOption)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOption)this.uninterpretedOptionBuilder_.getMessage(paramInt);
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder setUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption paramUninterpretedOption)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          if (paramUninterpretedOption == null)
            throw new NullPointerException();
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramUninterpretedOption);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramUninterpretedOption);
        }
        return this;
      }

      public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addUninterpretedOption(int paramInt, DescriptorProtos.UninterpretedOption.Builder paramBuilder)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllUninterpretedOption(Iterable paramIterable)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          GeneratedMessage.ExtendableBuilder.addAll(paramIterable, this.uninterpretedOption_);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearUninterpretedOption()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOption_ = Collections.emptyList();
          this.bitField0_ &= -257;
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.clear();
        }
        return this;
      }

      public Builder removeUninterpretedOption(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          ensureUninterpretedOptionIsMutable();
          this.uninterpretedOption_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.uninterpretedOptionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt)
      {
        if (this.uninterpretedOptionBuilder_ == null)
          return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOption_.get(paramInt);
        return (DescriptorProtos.UninterpretedOptionOrBuilder)this.uninterpretedOptionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getUninterpretedOptionOrBuilderList()
      {
        if (this.uninterpretedOptionBuilder_ != null)
          return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.uninterpretedOption_);
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder()
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int paramInt)
      {
        return (DescriptorProtos.UninterpretedOption.Builder)getUninterpretedOptionFieldBuilder().addBuilder(paramInt, DescriptorProtos.UninterpretedOption.getDefaultInstance());
      }

      public List getUninterpretedOptionBuilderList()
      {
        return getUninterpretedOptionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder()
      {
        if (this.uninterpretedOptionBuilder_ == null)
        {
          this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x100) == 256, getParentForChildren(), isClean());
          this.uninterpretedOption_ = null;
        }
        return this.uninterpretedOptionBuilder_;
      }
    }

    public static enum OptimizeMode
      implements ProtocolMessageEnum
    {
      SPEED(0, 1), CODE_SIZE(1, 2), LITE_RUNTIME(2, 3);

      public static final int SPEED_VALUE = 1;
      public static final int CODE_SIZE_VALUE = 2;
      public static final int LITE_RUNTIME_VALUE = 3;
      private static Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap()
      {
        public DescriptorProtos.FileOptions.OptimizeMode findValueByNumber(int paramAnonymousInt)
        {
          return DescriptorProtos.FileOptions.OptimizeMode.valueOf(paramAnonymousInt);
        }
      };
      private static final OptimizeMode[] VALUES = { SPEED, CODE_SIZE, LITE_RUNTIME };
      private final int index;
      private final int value;

      public final int getNumber()
      {
        return this.value;
      }

      public static OptimizeMode valueOf(int paramInt)
      {
        switch (paramInt)
        {
        case 1:
          return SPEED;
        case 2:
          return CODE_SIZE;
        case 3:
          return LITE_RUNTIME;
        }
        return null;
      }

      public static Internal.EnumLiteMap internalGetValueMap()
      {
        return internalValueMap;
      }

      public final Descriptors.EnumValueDescriptor getValueDescriptor()
      {
        return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
      }

      public final Descriptors.EnumDescriptor getDescriptorForType()
      {
        return getDescriptor();
      }

      public static final Descriptors.EnumDescriptor getDescriptor()
      {
        return (Descriptors.EnumDescriptor)DescriptorProtos.FileOptions.getDescriptor().getEnumTypes().get(0);
      }

      public static OptimizeMode valueOf(Descriptors.EnumValueDescriptor paramEnumValueDescriptor)
      {
        if (paramEnumValueDescriptor.getType() != getDescriptor())
          throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        return VALUES[paramEnumValueDescriptor.getIndex()];
      }

      private OptimizeMode(int arg3, int arg4)
      {
        int i;
        this.index = i;
        int j;
        this.value = j;
      }
    }
  }

  public static abstract interface FileOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder
  {
    public abstract boolean hasJavaPackage();

    public abstract String getJavaPackage();

    public abstract boolean hasJavaOuterClassname();

    public abstract String getJavaOuterClassname();

    public abstract boolean hasJavaMultipleFiles();

    public abstract boolean getJavaMultipleFiles();

    public abstract boolean hasJavaGenerateEqualsAndHash();

    public abstract boolean getJavaGenerateEqualsAndHash();

    public abstract boolean hasOptimizeFor();

    public abstract DescriptorProtos.FileOptions.OptimizeMode getOptimizeFor();

    public abstract boolean hasCcGenericServices();

    public abstract boolean getCcGenericServices();

    public abstract boolean hasJavaGenericServices();

    public abstract boolean getJavaGenericServices();

    public abstract boolean hasPyGenericServices();

    public abstract boolean getPyGenericServices();

    public abstract List getUninterpretedOptionList();

    public abstract DescriptorProtos.UninterpretedOption getUninterpretedOption(int paramInt);

    public abstract int getUninterpretedOptionCount();

    public abstract List getUninterpretedOptionOrBuilderList();

    public abstract DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int paramInt);
  }

  public static final class MethodDescriptorProto extends GeneratedMessage
    implements DescriptorProtos.MethodDescriptorProtoOrBuilder
  {
    private static final MethodDescriptorProto defaultInstance = new MethodDescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int INPUT_TYPE_FIELD_NUMBER = 2;
    private Object inputType_;
    public static final int OUTPUT_TYPE_FIELD_NUMBER = 3;
    private Object outputType_;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    private DescriptorProtos.MethodOptions options_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private MethodDescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private MethodDescriptorProto(boolean paramBoolean)
    {
    }

    public static MethodDescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public MethodDescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasInputType()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public String getInputType()
    {
      Object localObject = this.inputType_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.inputType_ = str;
      return str;
    }

    private ByteString getInputTypeBytes()
    {
      Object localObject = this.inputType_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.inputType_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasOutputType()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public String getOutputType()
    {
      Object localObject = this.outputType_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.outputType_ = str;
      return str;
    }

    private ByteString getOutputTypeBytes()
    {
      Object localObject = this.outputType_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.outputType_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x8) == 8;
    }

    public DescriptorProtos.MethodOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.MethodOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.inputType_ = "";
      this.outputType_ = "";
      this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeBytes(2, getInputTypeBytes());
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeBytes(3, getOutputTypeBytes());
      if ((this.bitField0_ & 0x8) == 8)
        paramCodedOutputStream.writeMessage(4, this.options_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeBytesSize(2, getInputTypeBytes());
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeBytesSize(3, getOutputTypeBytes());
      if ((this.bitField0_ & 0x8) == 8)
        i += CodedOutputStream.computeMessageSize(4, this.options_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static MethodDescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static MethodDescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static MethodDescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static MethodDescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static MethodDescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static MethodDescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static MethodDescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static MethodDescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static MethodDescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static MethodDescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$9400();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(MethodDescriptorProto paramMethodDescriptorProto)
    {
      return newBuilder().mergeFrom(paramMethodDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.MethodDescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private Object inputType_ = "";
      private Object outputType_ = "";
      private DescriptorProtos.MethodOptions options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getOptionsFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.inputType_ = "";
        this.bitField0_ &= -3;
        this.outputType_ = "";
        this.bitField0_ &= -5;
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -9;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.MethodDescriptorProto.getDescriptor();
      }

      public DescriptorProtos.MethodDescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.MethodDescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.MethodDescriptorProto build()
      {
        DescriptorProtos.MethodDescriptorProto localMethodDescriptorProto = buildPartial();
        if (!localMethodDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localMethodDescriptorProto);
        return localMethodDescriptorProto;
      }

      private DescriptorProtos.MethodDescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.MethodDescriptorProto localMethodDescriptorProto = buildPartial();
        if (!localMethodDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localMethodDescriptorProto).asInvalidProtocolBufferException();
        return localMethodDescriptorProto;
      }

      public DescriptorProtos.MethodDescriptorProto buildPartial()
      {
        DescriptorProtos.MethodDescriptorProto localMethodDescriptorProto = new DescriptorProtos.MethodDescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localMethodDescriptorProto.name_ = this.name_;
        if ((i & 0x2) == 2)
          j |= 2;
        localMethodDescriptorProto.inputType_ = this.inputType_;
        if ((i & 0x4) == 4)
          j |= 4;
        localMethodDescriptorProto.outputType_ = this.outputType_;
        if ((i & 0x8) == 8)
          j |= 8;
        if (this.optionsBuilder_ == null)
          localMethodDescriptorProto.options_ = this.options_;
        else
          localMethodDescriptorProto.options_ = ((DescriptorProtos.MethodOptions)this.optionsBuilder_.build());
        localMethodDescriptorProto.bitField0_ = j;
        onBuilt();
        return localMethodDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.MethodDescriptorProto))
          return mergeFrom((DescriptorProtos.MethodDescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.MethodDescriptorProto paramMethodDescriptorProto)
      {
        if (paramMethodDescriptorProto == DescriptorProtos.MethodDescriptorProto.getDefaultInstance())
          return this;
        if (paramMethodDescriptorProto.hasName())
          setName(paramMethodDescriptorProto.getName());
        if (paramMethodDescriptorProto.hasInputType())
          setInputType(paramMethodDescriptorProto.getInputType());
        if (paramMethodDescriptorProto.hasOutputType())
          setOutputType(paramMethodDescriptorProto.getOutputType());
        if (paramMethodDescriptorProto.hasOptions())
          mergeOptions(paramMethodDescriptorProto.getOptions());
        mergeUnknownFields(paramMethodDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ |= 2;
            this.inputType_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            this.bitField0_ |= 4;
            this.outputType_ = paramCodedInputStream.readBytes();
            break;
          case 34:
            DescriptorProtos.MethodOptions.Builder localBuilder1 = DescriptorProtos.MethodOptions.newBuilder();
            if (hasOptions())
              localBuilder1.mergeFrom(getOptions());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setOptions(localBuilder1.buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.MethodDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      public boolean hasInputType()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public String getInputType()
      {
        Object localObject = this.inputType_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.inputType_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setInputType(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 2;
        this.inputType_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearInputType()
      {
        this.bitField0_ &= -3;
        this.inputType_ = DescriptorProtos.MethodDescriptorProto.getDefaultInstance().getInputType();
        onChanged();
        return this;
      }

      void setInputType(ByteString paramByteString)
      {
        this.bitField0_ |= 2;
        this.inputType_ = paramByteString;
        onChanged();
      }

      public boolean hasOutputType()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public String getOutputType()
      {
        Object localObject = this.outputType_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.outputType_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setOutputType(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 4;
        this.outputType_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearOutputType()
      {
        this.bitField0_ &= -5;
        this.outputType_ = DescriptorProtos.MethodDescriptorProto.getDefaultInstance().getOutputType();
        onChanged();
        return this;
      }

      void setOutputType(ByteString paramByteString)
      {
        this.bitField0_ |= 4;
        this.outputType_ = paramByteString;
        onChanged();
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x8) == 8;
      }

      public DescriptorProtos.MethodOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.MethodOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.MethodOptions paramMethodOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramMethodOptions == null)
            throw new NullPointerException();
          this.options_ = paramMethodOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramMethodOptions);
        }
        this.bitField0_ |= 8;
        return this;
      }

      public Builder setOptions(DescriptorProtos.MethodOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 8;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.MethodOptions paramMethodOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x8) == 8) && (this.options_ != DescriptorProtos.MethodOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.MethodOptions.newBuilder(this.options_).mergeFrom(paramMethodOptions).buildPartial();
          else
            this.options_ = paramMethodOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramMethodOptions);
        }
        this.bitField0_ |= 8;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -9;
        return this;
      }

      public DescriptorProtos.MethodOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 8;
        onChanged();
        return (DescriptorProtos.MethodOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.MethodOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.MethodOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }
    }
  }

  public static abstract interface MethodDescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract boolean hasInputType();

    public abstract String getInputType();

    public abstract boolean hasOutputType();

    public abstract String getOutputType();

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.MethodOptions getOptions();

    public abstract DescriptorProtos.MethodOptionsOrBuilder getOptionsOrBuilder();
  }

  public static final class ServiceDescriptorProto extends GeneratedMessage
    implements DescriptorProtos.ServiceDescriptorProtoOrBuilder
  {
    private static final ServiceDescriptorProto defaultInstance = new ServiceDescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int METHOD_FIELD_NUMBER = 2;
    private List method_;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private DescriptorProtos.ServiceOptions options_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private ServiceDescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private ServiceDescriptorProto(boolean paramBoolean)
    {
    }

    public static ServiceDescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public ServiceDescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public List getMethodList()
    {
      return this.method_;
    }

    public List getMethodOrBuilderList()
    {
      return this.method_;
    }

    public int getMethodCount()
    {
      return this.method_.size();
    }

    public DescriptorProtos.MethodDescriptorProto getMethod(int paramInt)
    {
      return (DescriptorProtos.MethodDescriptorProto)this.method_.get(paramInt);
    }

    public DescriptorProtos.MethodDescriptorProtoOrBuilder getMethodOrBuilder(int paramInt)
    {
      return (DescriptorProtos.MethodDescriptorProtoOrBuilder)this.method_.get(paramInt);
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public DescriptorProtos.ServiceOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.ServiceOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.method_ = Collections.emptyList();
      this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getMethodCount(); j++)
        if (!getMethod(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      for (int i = 0; i < this.method_.size(); i++)
        paramCodedOutputStream.writeMessage(2, (MessageLite)this.method_.get(i));
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeMessage(3, this.options_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      for (int j = 0; j < this.method_.size(); j++)
        i += CodedOutputStream.computeMessageSize(2, (MessageLite)this.method_.get(j));
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeMessageSize(3, this.options_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static ServiceDescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ServiceDescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ServiceDescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ServiceDescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static ServiceDescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ServiceDescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ServiceDescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ServiceDescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ServiceDescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ServiceDescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$8400();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(ServiceDescriptorProto paramServiceDescriptorProto)
    {
      return newBuilder().mergeFrom(paramServiceDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.ServiceDescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private List method_ = Collections.emptyList();
      private RepeatedFieldBuilder methodBuilder_;
      private DescriptorProtos.ServiceOptions options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
        {
          getMethodFieldBuilder();
          getOptionsFieldBuilder();
        }
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.methodBuilder_ == null)
        {
          this.method_ = Collections.emptyList();
          this.bitField0_ &= -3;
        }
        else
        {
          this.methodBuilder_.clear();
        }
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -5;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.ServiceDescriptorProto.getDescriptor();
      }

      public DescriptorProtos.ServiceDescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.ServiceDescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.ServiceDescriptorProto build()
      {
        DescriptorProtos.ServiceDescriptorProto localServiceDescriptorProto = buildPartial();
        if (!localServiceDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localServiceDescriptorProto);
        return localServiceDescriptorProto;
      }

      private DescriptorProtos.ServiceDescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.ServiceDescriptorProto localServiceDescriptorProto = buildPartial();
        if (!localServiceDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localServiceDescriptorProto).asInvalidProtocolBufferException();
        return localServiceDescriptorProto;
      }

      public DescriptorProtos.ServiceDescriptorProto buildPartial()
      {
        DescriptorProtos.ServiceDescriptorProto localServiceDescriptorProto = new DescriptorProtos.ServiceDescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localServiceDescriptorProto.name_ = this.name_;
        if (this.methodBuilder_ == null)
        {
          if ((this.bitField0_ & 0x2) == 2)
          {
            this.method_ = Collections.unmodifiableList(this.method_);
            this.bitField0_ &= -3;
          }
          localServiceDescriptorProto.method_ = this.method_;
        }
        else
        {
          localServiceDescriptorProto.method_ = this.methodBuilder_.build();
        }
        if ((i & 0x4) == 4)
          j |= 2;
        if (this.optionsBuilder_ == null)
          localServiceDescriptorProto.options_ = this.options_;
        else
          localServiceDescriptorProto.options_ = ((DescriptorProtos.ServiceOptions)this.optionsBuilder_.build());
        localServiceDescriptorProto.bitField0_ = j;
        onBuilt();
        return localServiceDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.ServiceDescriptorProto))
          return mergeFrom((DescriptorProtos.ServiceDescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.ServiceDescriptorProto paramServiceDescriptorProto)
      {
        if (paramServiceDescriptorProto == DescriptorProtos.ServiceDescriptorProto.getDefaultInstance())
          return this;
        if (paramServiceDescriptorProto.hasName())
          setName(paramServiceDescriptorProto.getName());
        if (this.methodBuilder_ == null)
        {
          if (!paramServiceDescriptorProto.method_.isEmpty())
          {
            if (this.method_.isEmpty())
            {
              this.method_ = paramServiceDescriptorProto.method_;
              this.bitField0_ &= -3;
            }
            else
            {
              ensureMethodIsMutable();
              this.method_.addAll(paramServiceDescriptorProto.method_);
            }
            onChanged();
          }
        }
        else if (!paramServiceDescriptorProto.method_.isEmpty())
          if (this.methodBuilder_.isEmpty())
          {
            this.methodBuilder_.dispose();
            this.methodBuilder_ = null;
            this.method_ = paramServiceDescriptorProto.method_;
            this.bitField0_ &= -3;
            this.methodBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getMethodFieldBuilder() : null);
          }
          else
          {
            this.methodBuilder_.addAllMessages(paramServiceDescriptorProto.method_);
          }
        if (paramServiceDescriptorProto.hasOptions())
          mergeOptions(paramServiceDescriptorProto.getOptions());
        mergeUnknownFields(paramServiceDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getMethodCount(); i++)
          if (!getMethod(i).isInitialized())
            return false;
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          Object localObject;
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            localObject = DescriptorProtos.MethodDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addMethod(((DescriptorProtos.MethodDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 26:
            localObject = DescriptorProtos.ServiceOptions.newBuilder();
            if (hasOptions())
              ((DescriptorProtos.ServiceOptions.Builder)localObject).mergeFrom(getOptions());
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            setOptions(((DescriptorProtos.ServiceOptions.Builder)localObject).buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.ServiceDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      private void ensureMethodIsMutable()
      {
        if ((this.bitField0_ & 0x2) != 2)
        {
          this.method_ = new ArrayList(this.method_);
          this.bitField0_ |= 2;
        }
      }

      public List getMethodList()
      {
        if (this.methodBuilder_ == null)
          return Collections.unmodifiableList(this.method_);
        return this.methodBuilder_.getMessageList();
      }

      public int getMethodCount()
      {
        if (this.methodBuilder_ == null)
          return this.method_.size();
        return this.methodBuilder_.getCount();
      }

      public DescriptorProtos.MethodDescriptorProto getMethod(int paramInt)
      {
        if (this.methodBuilder_ == null)
          return (DescriptorProtos.MethodDescriptorProto)this.method_.get(paramInt);
        return (DescriptorProtos.MethodDescriptorProto)this.methodBuilder_.getMessage(paramInt);
      }

      public Builder setMethod(int paramInt, DescriptorProtos.MethodDescriptorProto paramMethodDescriptorProto)
      {
        if (this.methodBuilder_ == null)
        {
          if (paramMethodDescriptorProto == null)
            throw new NullPointerException();
          ensureMethodIsMutable();
          this.method_.set(paramInt, paramMethodDescriptorProto);
          onChanged();
        }
        else
        {
          this.methodBuilder_.setMessage(paramInt, paramMethodDescriptorProto);
        }
        return this;
      }

      public Builder setMethod(int paramInt, DescriptorProtos.MethodDescriptorProto.Builder paramBuilder)
      {
        if (this.methodBuilder_ == null)
        {
          ensureMethodIsMutable();
          this.method_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.methodBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addMethod(DescriptorProtos.MethodDescriptorProto paramMethodDescriptorProto)
      {
        if (this.methodBuilder_ == null)
        {
          if (paramMethodDescriptorProto == null)
            throw new NullPointerException();
          ensureMethodIsMutable();
          this.method_.add(paramMethodDescriptorProto);
          onChanged();
        }
        else
        {
          this.methodBuilder_.addMessage(paramMethodDescriptorProto);
        }
        return this;
      }

      public Builder addMethod(int paramInt, DescriptorProtos.MethodDescriptorProto paramMethodDescriptorProto)
      {
        if (this.methodBuilder_ == null)
        {
          if (paramMethodDescriptorProto == null)
            throw new NullPointerException();
          ensureMethodIsMutable();
          this.method_.add(paramInt, paramMethodDescriptorProto);
          onChanged();
        }
        else
        {
          this.methodBuilder_.addMessage(paramInt, paramMethodDescriptorProto);
        }
        return this;
      }

      public Builder addMethod(DescriptorProtos.MethodDescriptorProto.Builder paramBuilder)
      {
        if (this.methodBuilder_ == null)
        {
          ensureMethodIsMutable();
          this.method_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.methodBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addMethod(int paramInt, DescriptorProtos.MethodDescriptorProto.Builder paramBuilder)
      {
        if (this.methodBuilder_ == null)
        {
          ensureMethodIsMutable();
          this.method_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.methodBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllMethod(Iterable paramIterable)
      {
        if (this.methodBuilder_ == null)
        {
          ensureMethodIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.method_);
          onChanged();
        }
        else
        {
          this.methodBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearMethod()
      {
        if (this.methodBuilder_ == null)
        {
          this.method_ = Collections.emptyList();
          this.bitField0_ &= -3;
          onChanged();
        }
        else
        {
          this.methodBuilder_.clear();
        }
        return this;
      }

      public Builder removeMethod(int paramInt)
      {
        if (this.methodBuilder_ == null)
        {
          ensureMethodIsMutable();
          this.method_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.methodBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.MethodDescriptorProto.Builder getMethodBuilder(int paramInt)
      {
        return (DescriptorProtos.MethodDescriptorProto.Builder)getMethodFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.MethodDescriptorProtoOrBuilder getMethodOrBuilder(int paramInt)
      {
        if (this.methodBuilder_ == null)
          return (DescriptorProtos.MethodDescriptorProtoOrBuilder)this.method_.get(paramInt);
        return (DescriptorProtos.MethodDescriptorProtoOrBuilder)this.methodBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getMethodOrBuilderList()
      {
        if (this.methodBuilder_ != null)
          return this.methodBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.method_);
      }

      public DescriptorProtos.MethodDescriptorProto.Builder addMethodBuilder()
      {
        return (DescriptorProtos.MethodDescriptorProto.Builder)getMethodFieldBuilder().addBuilder(DescriptorProtos.MethodDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.MethodDescriptorProto.Builder addMethodBuilder(int paramInt)
      {
        return (DescriptorProtos.MethodDescriptorProto.Builder)getMethodFieldBuilder().addBuilder(paramInt, DescriptorProtos.MethodDescriptorProto.getDefaultInstance());
      }

      public List getMethodBuilderList()
      {
        return getMethodFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getMethodFieldBuilder()
      {
        if (this.methodBuilder_ == null)
        {
          this.methodBuilder_ = new RepeatedFieldBuilder(this.method_, (this.bitField0_ & 0x2) == 2, getParentForChildren(), isClean());
          this.method_ = null;
        }
        return this.methodBuilder_;
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public DescriptorProtos.ServiceOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.ServiceOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.ServiceOptions paramServiceOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramServiceOptions == null)
            throw new NullPointerException();
          this.options_ = paramServiceOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramServiceOptions);
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder setOptions(DescriptorProtos.ServiceOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.ServiceOptions paramServiceOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x4) == 4) && (this.options_ != DescriptorProtos.ServiceOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.ServiceOptions.newBuilder(this.options_).mergeFrom(paramServiceOptions).buildPartial();
          else
            this.options_ = paramServiceOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramServiceOptions);
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
      }

      public DescriptorProtos.ServiceOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos.ServiceOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.ServiceOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.ServiceOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }
    }
  }

  public static abstract interface ServiceDescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract List getMethodList();

    public abstract DescriptorProtos.MethodDescriptorProto getMethod(int paramInt);

    public abstract int getMethodCount();

    public abstract List getMethodOrBuilderList();

    public abstract DescriptorProtos.MethodDescriptorProtoOrBuilder getMethodOrBuilder(int paramInt);

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.ServiceOptions getOptions();

    public abstract DescriptorProtos.ServiceOptionsOrBuilder getOptionsOrBuilder();
  }

  public static final class EnumValueDescriptorProto extends GeneratedMessage
    implements DescriptorProtos.EnumValueDescriptorProtoOrBuilder
  {
    private static final EnumValueDescriptorProto defaultInstance = new EnumValueDescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int NUMBER_FIELD_NUMBER = 2;
    private int number_;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private DescriptorProtos.EnumValueOptions options_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private EnumValueDescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private EnumValueDescriptorProto(boolean paramBoolean)
    {
    }

    public static EnumValueDescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public EnumValueDescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasNumber()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public int getNumber()
    {
      return this.number_;
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public DescriptorProtos.EnumValueOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.EnumValueOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.number_ = 0;
      this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeInt32(2, this.number_);
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeMessage(3, this.options_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeInt32Size(2, this.number_);
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeMessageSize(3, this.options_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static EnumValueDescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static EnumValueDescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumValueDescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static EnumValueDescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumValueDescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static EnumValueDescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumValueDescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumValueDescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumValueDescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static EnumValueDescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$7400();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(EnumValueDescriptorProto paramEnumValueDescriptorProto)
    {
      return newBuilder().mergeFrom(paramEnumValueDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.EnumValueDescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private int number_;
      private DescriptorProtos.EnumValueOptions options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getOptionsFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.number_ = 0;
        this.bitField0_ &= -3;
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -5;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.EnumValueDescriptorProto.getDescriptor();
      }

      public DescriptorProtos.EnumValueDescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.EnumValueDescriptorProto build()
      {
        DescriptorProtos.EnumValueDescriptorProto localEnumValueDescriptorProto = buildPartial();
        if (!localEnumValueDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localEnumValueDescriptorProto);
        return localEnumValueDescriptorProto;
      }

      private DescriptorProtos.EnumValueDescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.EnumValueDescriptorProto localEnumValueDescriptorProto = buildPartial();
        if (!localEnumValueDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localEnumValueDescriptorProto).asInvalidProtocolBufferException();
        return localEnumValueDescriptorProto;
      }

      public DescriptorProtos.EnumValueDescriptorProto buildPartial()
      {
        DescriptorProtos.EnumValueDescriptorProto localEnumValueDescriptorProto = new DescriptorProtos.EnumValueDescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localEnumValueDescriptorProto.name_ = this.name_;
        if ((i & 0x2) == 2)
          j |= 2;
        localEnumValueDescriptorProto.number_ = this.number_;
        if ((i & 0x4) == 4)
          j |= 4;
        if (this.optionsBuilder_ == null)
          localEnumValueDescriptorProto.options_ = this.options_;
        else
          localEnumValueDescriptorProto.options_ = ((DescriptorProtos.EnumValueOptions)this.optionsBuilder_.build());
        localEnumValueDescriptorProto.bitField0_ = j;
        onBuilt();
        return localEnumValueDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.EnumValueDescriptorProto))
          return mergeFrom((DescriptorProtos.EnumValueDescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.EnumValueDescriptorProto paramEnumValueDescriptorProto)
      {
        if (paramEnumValueDescriptorProto == DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance())
          return this;
        if (paramEnumValueDescriptorProto.hasName())
          setName(paramEnumValueDescriptorProto.getName());
        if (paramEnumValueDescriptorProto.hasNumber())
          setNumber(paramEnumValueDescriptorProto.getNumber());
        if (paramEnumValueDescriptorProto.hasOptions())
          mergeOptions(paramEnumValueDescriptorProto.getOptions());
        mergeUnknownFields(paramEnumValueDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 16:
            this.bitField0_ |= 2;
            this.number_ = paramCodedInputStream.readInt32();
            break;
          case 26:
            DescriptorProtos.EnumValueOptions.Builder localBuilder1 = DescriptorProtos.EnumValueOptions.newBuilder();
            if (hasOptions())
              localBuilder1.mergeFrom(getOptions());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setOptions(localBuilder1.buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      public boolean hasNumber()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public int getNumber()
      {
        return this.number_;
      }

      public Builder setNumber(int paramInt)
      {
        this.bitField0_ |= 2;
        this.number_ = paramInt;
        onChanged();
        return this;
      }

      public Builder clearNumber()
      {
        this.bitField0_ &= -3;
        this.number_ = 0;
        onChanged();
        return this;
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public DescriptorProtos.EnumValueOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.EnumValueOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.EnumValueOptions paramEnumValueOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramEnumValueOptions == null)
            throw new NullPointerException();
          this.options_ = paramEnumValueOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramEnumValueOptions);
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder setOptions(DescriptorProtos.EnumValueOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.EnumValueOptions paramEnumValueOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x4) == 4) && (this.options_ != DescriptorProtos.EnumValueOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.EnumValueOptions.newBuilder(this.options_).mergeFrom(paramEnumValueOptions).buildPartial();
          else
            this.options_ = paramEnumValueOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramEnumValueOptions);
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
      }

      public DescriptorProtos.EnumValueOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos.EnumValueOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.EnumValueOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.EnumValueOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }
    }
  }

  public static abstract interface EnumValueDescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract boolean hasNumber();

    public abstract int getNumber();

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.EnumValueOptions getOptions();

    public abstract DescriptorProtos.EnumValueOptionsOrBuilder getOptionsOrBuilder();
  }

  public static final class EnumDescriptorProto extends GeneratedMessage
    implements DescriptorProtos.EnumDescriptorProtoOrBuilder
  {
    private static final EnumDescriptorProto defaultInstance = new EnumDescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int VALUE_FIELD_NUMBER = 2;
    private List value_;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private DescriptorProtos.EnumOptions options_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private EnumDescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private EnumDescriptorProto(boolean paramBoolean)
    {
    }

    public static EnumDescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public EnumDescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public List getValueList()
    {
      return this.value_;
    }

    public List getValueOrBuilderList()
    {
      return this.value_;
    }

    public int getValueCount()
    {
      return this.value_.size();
    }

    public DescriptorProtos.EnumValueDescriptorProto getValue(int paramInt)
    {
      return (DescriptorProtos.EnumValueDescriptorProto)this.value_.get(paramInt);
    }

    public DescriptorProtos.EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int paramInt)
    {
      return (DescriptorProtos.EnumValueDescriptorProtoOrBuilder)this.value_.get(paramInt);
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public DescriptorProtos.EnumOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.EnumOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.value_ = Collections.emptyList();
      this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getValueCount(); j++)
        if (!getValue(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      for (int i = 0; i < this.value_.size(); i++)
        paramCodedOutputStream.writeMessage(2, (MessageLite)this.value_.get(i));
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeMessage(3, this.options_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      for (int j = 0; j < this.value_.size(); j++)
        i += CodedOutputStream.computeMessageSize(2, (MessageLite)this.value_.get(j));
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeMessageSize(3, this.options_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static EnumDescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static EnumDescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumDescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static EnumDescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumDescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static EnumDescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static EnumDescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumDescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static EnumDescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static EnumDescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$6400();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(EnumDescriptorProto paramEnumDescriptorProto)
    {
      return newBuilder().mergeFrom(paramEnumDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.EnumDescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private List value_ = Collections.emptyList();
      private RepeatedFieldBuilder valueBuilder_;
      private DescriptorProtos.EnumOptions options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
        {
          getValueFieldBuilder();
          getOptionsFieldBuilder();
        }
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.valueBuilder_ == null)
        {
          this.value_ = Collections.emptyList();
          this.bitField0_ &= -3;
        }
        else
        {
          this.valueBuilder_.clear();
        }
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -5;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.EnumDescriptorProto.getDescriptor();
      }

      public DescriptorProtos.EnumDescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.EnumDescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.EnumDescriptorProto build()
      {
        DescriptorProtos.EnumDescriptorProto localEnumDescriptorProto = buildPartial();
        if (!localEnumDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localEnumDescriptorProto);
        return localEnumDescriptorProto;
      }

      private DescriptorProtos.EnumDescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.EnumDescriptorProto localEnumDescriptorProto = buildPartial();
        if (!localEnumDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localEnumDescriptorProto).asInvalidProtocolBufferException();
        return localEnumDescriptorProto;
      }

      public DescriptorProtos.EnumDescriptorProto buildPartial()
      {
        DescriptorProtos.EnumDescriptorProto localEnumDescriptorProto = new DescriptorProtos.EnumDescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localEnumDescriptorProto.name_ = this.name_;
        if (this.valueBuilder_ == null)
        {
          if ((this.bitField0_ & 0x2) == 2)
          {
            this.value_ = Collections.unmodifiableList(this.value_);
            this.bitField0_ &= -3;
          }
          localEnumDescriptorProto.value_ = this.value_;
        }
        else
        {
          localEnumDescriptorProto.value_ = this.valueBuilder_.build();
        }
        if ((i & 0x4) == 4)
          j |= 2;
        if (this.optionsBuilder_ == null)
          localEnumDescriptorProto.options_ = this.options_;
        else
          localEnumDescriptorProto.options_ = ((DescriptorProtos.EnumOptions)this.optionsBuilder_.build());
        localEnumDescriptorProto.bitField0_ = j;
        onBuilt();
        return localEnumDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.EnumDescriptorProto))
          return mergeFrom((DescriptorProtos.EnumDescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (paramEnumDescriptorProto == DescriptorProtos.EnumDescriptorProto.getDefaultInstance())
          return this;
        if (paramEnumDescriptorProto.hasName())
          setName(paramEnumDescriptorProto.getName());
        if (this.valueBuilder_ == null)
        {
          if (!paramEnumDescriptorProto.value_.isEmpty())
          {
            if (this.value_.isEmpty())
            {
              this.value_ = paramEnumDescriptorProto.value_;
              this.bitField0_ &= -3;
            }
            else
            {
              ensureValueIsMutable();
              this.value_.addAll(paramEnumDescriptorProto.value_);
            }
            onChanged();
          }
        }
        else if (!paramEnumDescriptorProto.value_.isEmpty())
          if (this.valueBuilder_.isEmpty())
          {
            this.valueBuilder_.dispose();
            this.valueBuilder_ = null;
            this.value_ = paramEnumDescriptorProto.value_;
            this.bitField0_ &= -3;
            this.valueBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getValueFieldBuilder() : null);
          }
          else
          {
            this.valueBuilder_.addAllMessages(paramEnumDescriptorProto.value_);
          }
        if (paramEnumDescriptorProto.hasOptions())
          mergeOptions(paramEnumDescriptorProto.getOptions());
        mergeUnknownFields(paramEnumDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getValueCount(); i++)
          if (!getValue(i).isInitialized())
            return false;
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          Object localObject;
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            localObject = DescriptorProtos.EnumValueDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addValue(((DescriptorProtos.EnumValueDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 26:
            localObject = DescriptorProtos.EnumOptions.newBuilder();
            if (hasOptions())
              ((DescriptorProtos.EnumOptions.Builder)localObject).mergeFrom(getOptions());
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            setOptions(((DescriptorProtos.EnumOptions.Builder)localObject).buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.EnumDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      private void ensureValueIsMutable()
      {
        if ((this.bitField0_ & 0x2) != 2)
        {
          this.value_ = new ArrayList(this.value_);
          this.bitField0_ |= 2;
        }
      }

      public List getValueList()
      {
        if (this.valueBuilder_ == null)
          return Collections.unmodifiableList(this.value_);
        return this.valueBuilder_.getMessageList();
      }

      public int getValueCount()
      {
        if (this.valueBuilder_ == null)
          return this.value_.size();
        return this.valueBuilder_.getCount();
      }

      public DescriptorProtos.EnumValueDescriptorProto getValue(int paramInt)
      {
        if (this.valueBuilder_ == null)
          return (DescriptorProtos.EnumValueDescriptorProto)this.value_.get(paramInt);
        return (DescriptorProtos.EnumValueDescriptorProto)this.valueBuilder_.getMessage(paramInt);
      }

      public Builder setValue(int paramInt, DescriptorProtos.EnumValueDescriptorProto paramEnumValueDescriptorProto)
      {
        if (this.valueBuilder_ == null)
        {
          if (paramEnumValueDescriptorProto == null)
            throw new NullPointerException();
          ensureValueIsMutable();
          this.value_.set(paramInt, paramEnumValueDescriptorProto);
          onChanged();
        }
        else
        {
          this.valueBuilder_.setMessage(paramInt, paramEnumValueDescriptorProto);
        }
        return this;
      }

      public Builder setValue(int paramInt, DescriptorProtos.EnumValueDescriptorProto.Builder paramBuilder)
      {
        if (this.valueBuilder_ == null)
        {
          ensureValueIsMutable();
          this.value_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.valueBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addValue(DescriptorProtos.EnumValueDescriptorProto paramEnumValueDescriptorProto)
      {
        if (this.valueBuilder_ == null)
        {
          if (paramEnumValueDescriptorProto == null)
            throw new NullPointerException();
          ensureValueIsMutable();
          this.value_.add(paramEnumValueDescriptorProto);
          onChanged();
        }
        else
        {
          this.valueBuilder_.addMessage(paramEnumValueDescriptorProto);
        }
        return this;
      }

      public Builder addValue(int paramInt, DescriptorProtos.EnumValueDescriptorProto paramEnumValueDescriptorProto)
      {
        if (this.valueBuilder_ == null)
        {
          if (paramEnumValueDescriptorProto == null)
            throw new NullPointerException();
          ensureValueIsMutable();
          this.value_.add(paramInt, paramEnumValueDescriptorProto);
          onChanged();
        }
        else
        {
          this.valueBuilder_.addMessage(paramInt, paramEnumValueDescriptorProto);
        }
        return this;
      }

      public Builder addValue(DescriptorProtos.EnumValueDescriptorProto.Builder paramBuilder)
      {
        if (this.valueBuilder_ == null)
        {
          ensureValueIsMutable();
          this.value_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.valueBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addValue(int paramInt, DescriptorProtos.EnumValueDescriptorProto.Builder paramBuilder)
      {
        if (this.valueBuilder_ == null)
        {
          ensureValueIsMutable();
          this.value_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.valueBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllValue(Iterable paramIterable)
      {
        if (this.valueBuilder_ == null)
        {
          ensureValueIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.value_);
          onChanged();
        }
        else
        {
          this.valueBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearValue()
      {
        if (this.valueBuilder_ == null)
        {
          this.value_ = Collections.emptyList();
          this.bitField0_ &= -3;
          onChanged();
        }
        else
        {
          this.valueBuilder_.clear();
        }
        return this;
      }

      public Builder removeValue(int paramInt)
      {
        if (this.valueBuilder_ == null)
        {
          ensureValueIsMutable();
          this.value_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.valueBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.EnumValueDescriptorProto.Builder getValueBuilder(int paramInt)
      {
        return (DescriptorProtos.EnumValueDescriptorProto.Builder)getValueFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int paramInt)
      {
        if (this.valueBuilder_ == null)
          return (DescriptorProtos.EnumValueDescriptorProtoOrBuilder)this.value_.get(paramInt);
        return (DescriptorProtos.EnumValueDescriptorProtoOrBuilder)this.valueBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getValueOrBuilderList()
      {
        if (this.valueBuilder_ != null)
          return this.valueBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.value_);
      }

      public DescriptorProtos.EnumValueDescriptorProto.Builder addValueBuilder()
      {
        return (DescriptorProtos.EnumValueDescriptorProto.Builder)getValueFieldBuilder().addBuilder(DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.EnumValueDescriptorProto.Builder addValueBuilder(int paramInt)
      {
        return (DescriptorProtos.EnumValueDescriptorProto.Builder)getValueFieldBuilder().addBuilder(paramInt, DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance());
      }

      public List getValueBuilderList()
      {
        return getValueFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getValueFieldBuilder()
      {
        if (this.valueBuilder_ == null)
        {
          this.valueBuilder_ = new RepeatedFieldBuilder(this.value_, (this.bitField0_ & 0x2) == 2, getParentForChildren(), isClean());
          this.value_ = null;
        }
        return this.valueBuilder_;
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public DescriptorProtos.EnumOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.EnumOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.EnumOptions paramEnumOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramEnumOptions == null)
            throw new NullPointerException();
          this.options_ = paramEnumOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramEnumOptions);
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder setOptions(DescriptorProtos.EnumOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.EnumOptions paramEnumOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x4) == 4) && (this.options_ != DescriptorProtos.EnumOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.EnumOptions.newBuilder(this.options_).mergeFrom(paramEnumOptions).buildPartial();
          else
            this.options_ = paramEnumOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramEnumOptions);
        }
        this.bitField0_ |= 4;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -5;
        return this;
      }

      public DescriptorProtos.EnumOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 4;
        onChanged();
        return (DescriptorProtos.EnumOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.EnumOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.EnumOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }
    }
  }

  public static abstract interface EnumDescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract List getValueList();

    public abstract DescriptorProtos.EnumValueDescriptorProto getValue(int paramInt);

    public abstract int getValueCount();

    public abstract List getValueOrBuilderList();

    public abstract DescriptorProtos.EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int paramInt);

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.EnumOptions getOptions();

    public abstract DescriptorProtos.EnumOptionsOrBuilder getOptionsOrBuilder();
  }

  public static final class FieldDescriptorProto extends GeneratedMessage
    implements DescriptorProtos.FieldDescriptorProtoOrBuilder
  {
    private static final FieldDescriptorProto defaultInstance = new FieldDescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int NUMBER_FIELD_NUMBER = 3;
    private int number_;
    public static final int LABEL_FIELD_NUMBER = 4;
    private Label label_;
    public static final int TYPE_FIELD_NUMBER = 5;
    private Type type_;
    public static final int TYPE_NAME_FIELD_NUMBER = 6;
    private Object typeName_;
    public static final int EXTENDEE_FIELD_NUMBER = 2;
    private Object extendee_;
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 7;
    private Object defaultValue_;
    public static final int OPTIONS_FIELD_NUMBER = 8;
    private DescriptorProtos.FieldOptions options_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private FieldDescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private FieldDescriptorProto(boolean paramBoolean)
    {
    }

    public static FieldDescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public FieldDescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasNumber()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public int getNumber()
    {
      return this.number_;
    }

    public boolean hasLabel()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public Label getLabel()
    {
      return this.label_;
    }

    public boolean hasType()
    {
      return (this.bitField0_ & 0x8) == 8;
    }

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasTypeName()
    {
      return (this.bitField0_ & 0x10) == 16;
    }

    public String getTypeName()
    {
      Object localObject = this.typeName_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.typeName_ = str;
      return str;
    }

    private ByteString getTypeNameBytes()
    {
      Object localObject = this.typeName_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.typeName_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasExtendee()
    {
      return (this.bitField0_ & 0x20) == 32;
    }

    public String getExtendee()
    {
      Object localObject = this.extendee_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.extendee_ = str;
      return str;
    }

    private ByteString getExtendeeBytes()
    {
      Object localObject = this.extendee_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.extendee_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasDefaultValue()
    {
      return (this.bitField0_ & 0x40) == 64;
    }

    public String getDefaultValue()
    {
      Object localObject = this.defaultValue_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.defaultValue_ = str;
      return str;
    }

    private ByteString getDefaultValueBytes()
    {
      Object localObject = this.defaultValue_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.defaultValue_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x80) == 128;
    }

    public DescriptorProtos.FieldOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.FieldOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.number_ = 0;
      this.label_ = Label.LABEL_OPTIONAL;
      this.type_ = Type.TYPE_DOUBLE;
      this.typeName_ = "";
      this.extendee_ = "";
      this.defaultValue_ = "";
      this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      if ((this.bitField0_ & 0x20) == 32)
        paramCodedOutputStream.writeBytes(2, getExtendeeBytes());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeInt32(3, this.number_);
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeEnum(4, this.label_.getNumber());
      if ((this.bitField0_ & 0x8) == 8)
        paramCodedOutputStream.writeEnum(5, this.type_.getNumber());
      if ((this.bitField0_ & 0x10) == 16)
        paramCodedOutputStream.writeBytes(6, getTypeNameBytes());
      if ((this.bitField0_ & 0x40) == 64)
        paramCodedOutputStream.writeBytes(7, getDefaultValueBytes());
      if ((this.bitField0_ & 0x80) == 128)
        paramCodedOutputStream.writeMessage(8, this.options_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      if ((this.bitField0_ & 0x20) == 32)
        i += CodedOutputStream.computeBytesSize(2, getExtendeeBytes());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeInt32Size(3, this.number_);
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeEnumSize(4, this.label_.getNumber());
      if ((this.bitField0_ & 0x8) == 8)
        i += CodedOutputStream.computeEnumSize(5, this.type_.getNumber());
      if ((this.bitField0_ & 0x10) == 16)
        i += CodedOutputStream.computeBytesSize(6, getTypeNameBytes());
      if ((this.bitField0_ & 0x40) == 64)
        i += CodedOutputStream.computeBytesSize(7, getDefaultValueBytes());
      if ((this.bitField0_ & 0x80) == 128)
        i += CodedOutputStream.computeMessageSize(8, this.options_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static FieldDescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FieldDescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FieldDescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FieldDescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static FieldDescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FieldDescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FieldDescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FieldDescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FieldDescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FieldDescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$4900();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(FieldDescriptorProto paramFieldDescriptorProto)
    {
      return newBuilder().mergeFrom(paramFieldDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.FieldDescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private int number_;
      private DescriptorProtos.FieldDescriptorProto.Label label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
      private DescriptorProtos.FieldDescriptorProto.Type type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
      private Object typeName_ = "";
      private Object extendee_ = "";
      private Object defaultValue_ = "";
      private DescriptorProtos.FieldOptions options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getOptionsFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.number_ = 0;
        this.bitField0_ &= -3;
        this.label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        this.bitField0_ &= -5;
        this.type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
        this.bitField0_ &= -9;
        this.typeName_ = "";
        this.bitField0_ &= -17;
        this.extendee_ = "";
        this.bitField0_ &= -33;
        this.defaultValue_ = "";
        this.bitField0_ &= -65;
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -129;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.FieldDescriptorProto.getDescriptor();
      }

      public DescriptorProtos.FieldDescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.FieldDescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.FieldDescriptorProto build()
      {
        DescriptorProtos.FieldDescriptorProto localFieldDescriptorProto = buildPartial();
        if (!localFieldDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localFieldDescriptorProto);
        return localFieldDescriptorProto;
      }

      private DescriptorProtos.FieldDescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.FieldDescriptorProto localFieldDescriptorProto = buildPartial();
        if (!localFieldDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localFieldDescriptorProto).asInvalidProtocolBufferException();
        return localFieldDescriptorProto;
      }

      public DescriptorProtos.FieldDescriptorProto buildPartial()
      {
        DescriptorProtos.FieldDescriptorProto localFieldDescriptorProto = new DescriptorProtos.FieldDescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localFieldDescriptorProto.name_ = this.name_;
        if ((i & 0x2) == 2)
          j |= 2;
        localFieldDescriptorProto.number_ = this.number_;
        if ((i & 0x4) == 4)
          j |= 4;
        localFieldDescriptorProto.label_ = this.label_;
        if ((i & 0x8) == 8)
          j |= 8;
        localFieldDescriptorProto.type_ = this.type_;
        if ((i & 0x10) == 16)
          j |= 16;
        localFieldDescriptorProto.typeName_ = this.typeName_;
        if ((i & 0x20) == 32)
          j |= 32;
        localFieldDescriptorProto.extendee_ = this.extendee_;
        if ((i & 0x40) == 64)
          j |= 64;
        localFieldDescriptorProto.defaultValue_ = this.defaultValue_;
        if ((i & 0x80) == 128)
          j |= 128;
        if (this.optionsBuilder_ == null)
          localFieldDescriptorProto.options_ = this.options_;
        else
          localFieldDescriptorProto.options_ = ((DescriptorProtos.FieldOptions)this.optionsBuilder_.build());
        localFieldDescriptorProto.bitField0_ = j;
        onBuilt();
        return localFieldDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.FieldDescriptorProto))
          return mergeFrom((DescriptorProtos.FieldDescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (paramFieldDescriptorProto == DescriptorProtos.FieldDescriptorProto.getDefaultInstance())
          return this;
        if (paramFieldDescriptorProto.hasName())
          setName(paramFieldDescriptorProto.getName());
        if (paramFieldDescriptorProto.hasNumber())
          setNumber(paramFieldDescriptorProto.getNumber());
        if (paramFieldDescriptorProto.hasLabel())
          setLabel(paramFieldDescriptorProto.getLabel());
        if (paramFieldDescriptorProto.hasType())
          setType(paramFieldDescriptorProto.getType());
        if (paramFieldDescriptorProto.hasTypeName())
          setTypeName(paramFieldDescriptorProto.getTypeName());
        if (paramFieldDescriptorProto.hasExtendee())
          setExtendee(paramFieldDescriptorProto.getExtendee());
        if (paramFieldDescriptorProto.hasDefaultValue())
          setDefaultValue(paramFieldDescriptorProto.getDefaultValue());
        if (paramFieldDescriptorProto.hasOptions())
          mergeOptions(paramFieldDescriptorProto.getOptions());
        mergeUnknownFields(paramFieldDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          int j;
          Object localObject;
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ |= 32;
            this.extendee_ = paramCodedInputStream.readBytes();
            break;
          case 24:
            this.bitField0_ |= 2;
            this.number_ = paramCodedInputStream.readInt32();
            break;
          case 32:
            j = paramCodedInputStream.readEnum();
            localObject = DescriptorProtos.FieldDescriptorProto.Label.valueOf(j);
            if (localObject == null)
            {
              localBuilder.mergeVarintField(4, j);
            }
            else
            {
              this.bitField0_ |= 4;
              this.label_ = ((DescriptorProtos.FieldDescriptorProto.Label)localObject);
            }
            break;
          case 40:
            j = paramCodedInputStream.readEnum();
            localObject = DescriptorProtos.FieldDescriptorProto.Type.valueOf(j);
            if (localObject == null)
            {
              localBuilder.mergeVarintField(5, j);
            }
            else
            {
              this.bitField0_ |= 8;
              this.type_ = ((DescriptorProtos.FieldDescriptorProto.Type)localObject);
            }
            break;
          case 50:
            this.bitField0_ |= 16;
            this.typeName_ = paramCodedInputStream.readBytes();
            break;
          case 58:
            this.bitField0_ |= 64;
            this.defaultValue_ = paramCodedInputStream.readBytes();
            break;
          case 66:
            DescriptorProtos.FieldOptions.Builder localBuilder1 = DescriptorProtos.FieldOptions.newBuilder();
            if (hasOptions())
              localBuilder1.mergeFrom(getOptions());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setOptions(localBuilder1.buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      public boolean hasNumber()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public int getNumber()
      {
        return this.number_;
      }

      public Builder setNumber(int paramInt)
      {
        this.bitField0_ |= 2;
        this.number_ = paramInt;
        onChanged();
        return this;
      }

      public Builder clearNumber()
      {
        this.bitField0_ &= -3;
        this.number_ = 0;
        onChanged();
        return this;
      }

      public boolean hasLabel()
      {
        return (this.bitField0_ & 0x4) == 4;
      }

      public DescriptorProtos.FieldDescriptorProto.Label getLabel()
      {
        return this.label_;
      }

      public Builder setLabel(DescriptorProtos.FieldDescriptorProto.Label paramLabel)
      {
        if (paramLabel == null)
          throw new NullPointerException();
        this.bitField0_ |= 4;
        this.label_ = paramLabel;
        onChanged();
        return this;
      }

      public Builder clearLabel()
      {
        this.bitField0_ &= -5;
        this.label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        onChanged();
        return this;
      }

      public boolean hasType()
      {
        return (this.bitField0_ & 0x8) == 8;
      }

      public DescriptorProtos.FieldDescriptorProto.Type getType()
      {
        return this.type_;
      }

      public Builder setType(DescriptorProtos.FieldDescriptorProto.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ |= 8;
        this.type_ = paramType;
        onChanged();
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ &= -9;
        this.type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
        onChanged();
        return this;
      }

      public boolean hasTypeName()
      {
        return (this.bitField0_ & 0x10) == 16;
      }

      public String getTypeName()
      {
        Object localObject = this.typeName_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.typeName_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setTypeName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 16;
        this.typeName_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearTypeName()
      {
        this.bitField0_ &= -17;
        this.typeName_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getTypeName();
        onChanged();
        return this;
      }

      void setTypeName(ByteString paramByteString)
      {
        this.bitField0_ |= 16;
        this.typeName_ = paramByteString;
        onChanged();
      }

      public boolean hasExtendee()
      {
        return (this.bitField0_ & 0x20) == 32;
      }

      public String getExtendee()
      {
        Object localObject = this.extendee_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.extendee_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setExtendee(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 32;
        this.extendee_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearExtendee()
      {
        this.bitField0_ &= -33;
        this.extendee_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getExtendee();
        onChanged();
        return this;
      }

      void setExtendee(ByteString paramByteString)
      {
        this.bitField0_ |= 32;
        this.extendee_ = paramByteString;
        onChanged();
      }

      public boolean hasDefaultValue()
      {
        return (this.bitField0_ & 0x40) == 64;
      }

      public String getDefaultValue()
      {
        Object localObject = this.defaultValue_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.defaultValue_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setDefaultValue(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 64;
        this.defaultValue_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearDefaultValue()
      {
        this.bitField0_ &= -65;
        this.defaultValue_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getDefaultValue();
        onChanged();
        return this;
      }

      void setDefaultValue(ByteString paramByteString)
      {
        this.bitField0_ |= 64;
        this.defaultValue_ = paramByteString;
        onChanged();
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x80) == 128;
      }

      public DescriptorProtos.FieldOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.FieldOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.FieldOptions paramFieldOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramFieldOptions == null)
            throw new NullPointerException();
          this.options_ = paramFieldOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramFieldOptions);
        }
        this.bitField0_ |= 128;
        return this;
      }

      public Builder setOptions(DescriptorProtos.FieldOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 128;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.FieldOptions paramFieldOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x80) == 128) && (this.options_ != DescriptorProtos.FieldOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.FieldOptions.newBuilder(this.options_).mergeFrom(paramFieldOptions).buildPartial();
          else
            this.options_ = paramFieldOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramFieldOptions);
        }
        this.bitField0_ |= 128;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -129;
        return this;
      }

      public DescriptorProtos.FieldOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 128;
        onChanged();
        return (DescriptorProtos.FieldOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.FieldOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.FieldOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }
    }

    public static enum Label
      implements ProtocolMessageEnum
    {
      LABEL_OPTIONAL(0, 1), LABEL_REQUIRED(1, 2), LABEL_REPEATED(2, 3);

      public static final int LABEL_OPTIONAL_VALUE = 1;
      public static final int LABEL_REQUIRED_VALUE = 2;
      public static final int LABEL_REPEATED_VALUE = 3;
      private static Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap()
      {
        public DescriptorProtos.FieldDescriptorProto.Label findValueByNumber(int paramAnonymousInt)
        {
          return DescriptorProtos.FieldDescriptorProto.Label.valueOf(paramAnonymousInt);
        }
      };
      private static final Label[] VALUES = { LABEL_OPTIONAL, LABEL_REQUIRED, LABEL_REPEATED };
      private final int index;
      private final int value;

      public final int getNumber()
      {
        return this.value;
      }

      public static Label valueOf(int paramInt)
      {
        switch (paramInt)
        {
        case 1:
          return LABEL_OPTIONAL;
        case 2:
          return LABEL_REQUIRED;
        case 3:
          return LABEL_REPEATED;
        }
        return null;
      }

      public static Internal.EnumLiteMap internalGetValueMap()
      {
        return internalValueMap;
      }

      public final Descriptors.EnumValueDescriptor getValueDescriptor()
      {
        return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
      }

      public final Descriptors.EnumDescriptor getDescriptorForType()
      {
        return getDescriptor();
      }

      public static final Descriptors.EnumDescriptor getDescriptor()
      {
        return (Descriptors.EnumDescriptor)DescriptorProtos.FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
      }

      public static Label valueOf(Descriptors.EnumValueDescriptor paramEnumValueDescriptor)
      {
        if (paramEnumValueDescriptor.getType() != getDescriptor())
          throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        return VALUES[paramEnumValueDescriptor.getIndex()];
      }

      private Label(int arg3, int arg4)
      {
        int i;
        this.index = i;
        int j;
        this.value = j;
      }
    }

    public static enum Type
      implements ProtocolMessageEnum
    {
      TYPE_DOUBLE(0, 1), TYPE_FLOAT(1, 2), TYPE_INT64(2, 3), TYPE_UINT64(3, 4), TYPE_INT32(4, 5), TYPE_FIXED64(5, 6), TYPE_FIXED32(6, 7), TYPE_BOOL(7, 8), TYPE_STRING(8, 9), TYPE_GROUP(9, 10), TYPE_MESSAGE(10, 11), TYPE_BYTES(11, 12), TYPE_UINT32(12, 13), TYPE_ENUM(13, 14), TYPE_SFIXED32(14, 15), TYPE_SFIXED64(15, 16), TYPE_SINT32(16, 17), TYPE_SINT64(17, 18);

      public static final int TYPE_DOUBLE_VALUE = 1;
      public static final int TYPE_FLOAT_VALUE = 2;
      public static final int TYPE_INT64_VALUE = 3;
      public static final int TYPE_UINT64_VALUE = 4;
      public static final int TYPE_INT32_VALUE = 5;
      public static final int TYPE_FIXED64_VALUE = 6;
      public static final int TYPE_FIXED32_VALUE = 7;
      public static final int TYPE_BOOL_VALUE = 8;
      public static final int TYPE_STRING_VALUE = 9;
      public static final int TYPE_GROUP_VALUE = 10;
      public static final int TYPE_MESSAGE_VALUE = 11;
      public static final int TYPE_BYTES_VALUE = 12;
      public static final int TYPE_UINT32_VALUE = 13;
      public static final int TYPE_ENUM_VALUE = 14;
      public static final int TYPE_SFIXED32_VALUE = 15;
      public static final int TYPE_SFIXED64_VALUE = 16;
      public static final int TYPE_SINT32_VALUE = 17;
      public static final int TYPE_SINT64_VALUE = 18;
      private static Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap()
      {
        public DescriptorProtos.FieldDescriptorProto.Type findValueByNumber(int paramAnonymousInt)
        {
          return DescriptorProtos.FieldDescriptorProto.Type.valueOf(paramAnonymousInt);
        }
      };
      private static final Type[] VALUES = { TYPE_DOUBLE, TYPE_FLOAT, TYPE_INT64, TYPE_UINT64, TYPE_INT32, TYPE_FIXED64, TYPE_FIXED32, TYPE_BOOL, TYPE_STRING, TYPE_GROUP, TYPE_MESSAGE, TYPE_BYTES, TYPE_UINT32, TYPE_ENUM, TYPE_SFIXED32, TYPE_SFIXED64, TYPE_SINT32, TYPE_SINT64 };
      private final int index;
      private final int value;

      public final int getNumber()
      {
        return this.value;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        case 1:
          return TYPE_DOUBLE;
        case 2:
          return TYPE_FLOAT;
        case 3:
          return TYPE_INT64;
        case 4:
          return TYPE_UINT64;
        case 5:
          return TYPE_INT32;
        case 6:
          return TYPE_FIXED64;
        case 7:
          return TYPE_FIXED32;
        case 8:
          return TYPE_BOOL;
        case 9:
          return TYPE_STRING;
        case 10:
          return TYPE_GROUP;
        case 11:
          return TYPE_MESSAGE;
        case 12:
          return TYPE_BYTES;
        case 13:
          return TYPE_UINT32;
        case 14:
          return TYPE_ENUM;
        case 15:
          return TYPE_SFIXED32;
        case 16:
          return TYPE_SFIXED64;
        case 17:
          return TYPE_SINT32;
        case 18:
          return TYPE_SINT64;
        }
        return null;
      }

      public static Internal.EnumLiteMap internalGetValueMap()
      {
        return internalValueMap;
      }

      public final Descriptors.EnumValueDescriptor getValueDescriptor()
      {
        return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
      }

      public final Descriptors.EnumDescriptor getDescriptorForType()
      {
        return getDescriptor();
      }

      public static final Descriptors.EnumDescriptor getDescriptor()
      {
        return (Descriptors.EnumDescriptor)DescriptorProtos.FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
      }

      public static Type valueOf(Descriptors.EnumValueDescriptor paramEnumValueDescriptor)
      {
        if (paramEnumValueDescriptor.getType() != getDescriptor())
          throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        return VALUES[paramEnumValueDescriptor.getIndex()];
      }

      private Type(int arg3, int arg4)
      {
        int i;
        this.index = i;
        int j;
        this.value = j;
      }
    }
  }

  public static abstract interface FieldDescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract boolean hasNumber();

    public abstract int getNumber();

    public abstract boolean hasLabel();

    public abstract DescriptorProtos.FieldDescriptorProto.Label getLabel();

    public abstract boolean hasType();

    public abstract DescriptorProtos.FieldDescriptorProto.Type getType();

    public abstract boolean hasTypeName();

    public abstract String getTypeName();

    public abstract boolean hasExtendee();

    public abstract String getExtendee();

    public abstract boolean hasDefaultValue();

    public abstract String getDefaultValue();

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.FieldOptions getOptions();

    public abstract DescriptorProtos.FieldOptionsOrBuilder getOptionsOrBuilder();
  }

  public static final class DescriptorProto extends GeneratedMessage
    implements DescriptorProtos.DescriptorProtoOrBuilder
  {
    private static final DescriptorProto defaultInstance = new DescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int FIELD_FIELD_NUMBER = 2;
    private List field_;
    public static final int EXTENSION_FIELD_NUMBER = 6;
    private List extension_;
    public static final int NESTED_TYPE_FIELD_NUMBER = 3;
    private List nestedType_;
    public static final int ENUM_TYPE_FIELD_NUMBER = 4;
    private List enumType_;
    public static final int EXTENSION_RANGE_FIELD_NUMBER = 5;
    private List extensionRange_;
    public static final int OPTIONS_FIELD_NUMBER = 7;
    private DescriptorProtos.MessageOptions options_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private DescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private DescriptorProto(boolean paramBoolean)
    {
    }

    public static DescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public DescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public List getFieldList()
    {
      return this.field_;
    }

    public List getFieldOrBuilderList()
    {
      return this.field_;
    }

    public int getFieldCount()
    {
      return this.field_.size();
    }

    public DescriptorProtos.FieldDescriptorProto getField(int paramInt)
    {
      return (DescriptorProtos.FieldDescriptorProto)this.field_.get(paramInt);
    }

    public DescriptorProtos.FieldDescriptorProtoOrBuilder getFieldOrBuilder(int paramInt)
    {
      return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.field_.get(paramInt);
    }

    public List getExtensionList()
    {
      return this.extension_;
    }

    public List getExtensionOrBuilderList()
    {
      return this.extension_;
    }

    public int getExtensionCount()
    {
      return this.extension_.size();
    }

    public DescriptorProtos.FieldDescriptorProto getExtension(int paramInt)
    {
      return (DescriptorProtos.FieldDescriptorProto)this.extension_.get(paramInt);
    }

    public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.extension_.get(paramInt);
    }

    public List getNestedTypeList()
    {
      return this.nestedType_;
    }

    public List getNestedTypeOrBuilderList()
    {
      return this.nestedType_;
    }

    public int getNestedTypeCount()
    {
      return this.nestedType_.size();
    }

    public DescriptorProto getNestedType(int paramInt)
    {
      return (DescriptorProto)this.nestedType_.get(paramInt);
    }

    public DescriptorProtos.DescriptorProtoOrBuilder getNestedTypeOrBuilder(int paramInt)
    {
      return (DescriptorProtos.DescriptorProtoOrBuilder)this.nestedType_.get(paramInt);
    }

    public List getEnumTypeList()
    {
      return this.enumType_;
    }

    public List getEnumTypeOrBuilderList()
    {
      return this.enumType_;
    }

    public int getEnumTypeCount()
    {
      return this.enumType_.size();
    }

    public DescriptorProtos.EnumDescriptorProto getEnumType(int paramInt)
    {
      return (DescriptorProtos.EnumDescriptorProto)this.enumType_.get(paramInt);
    }

    public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int paramInt)
    {
      return (DescriptorProtos.EnumDescriptorProtoOrBuilder)this.enumType_.get(paramInt);
    }

    public List getExtensionRangeList()
    {
      return this.extensionRange_;
    }

    public List getExtensionRangeOrBuilderList()
    {
      return this.extensionRange_;
    }

    public int getExtensionRangeCount()
    {
      return this.extensionRange_.size();
    }

    public ExtensionRange getExtensionRange(int paramInt)
    {
      return (ExtensionRange)this.extensionRange_.get(paramInt);
    }

    public ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int paramInt)
    {
      return (ExtensionRangeOrBuilder)this.extensionRange_.get(paramInt);
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public DescriptorProtos.MessageOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.MessageOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.field_ = Collections.emptyList();
      this.extension_ = Collections.emptyList();
      this.nestedType_ = Collections.emptyList();
      this.enumType_ = Collections.emptyList();
      this.extensionRange_ = Collections.emptyList();
      this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getFieldCount(); j++)
        if (!getField(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (j = 0; j < getExtensionCount(); j++)
        if (!getExtension(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (j = 0; j < getNestedTypeCount(); j++)
        if (!getNestedType(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (j = 0; j < getEnumTypeCount(); j++)
        if (!getEnumType(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      for (int i = 0; i < this.field_.size(); i++)
        paramCodedOutputStream.writeMessage(2, (MessageLite)this.field_.get(i));
      for (i = 0; i < this.nestedType_.size(); i++)
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.nestedType_.get(i));
      for (i = 0; i < this.enumType_.size(); i++)
        paramCodedOutputStream.writeMessage(4, (MessageLite)this.enumType_.get(i));
      for (i = 0; i < this.extensionRange_.size(); i++)
        paramCodedOutputStream.writeMessage(5, (MessageLite)this.extensionRange_.get(i));
      for (i = 0; i < this.extension_.size(); i++)
        paramCodedOutputStream.writeMessage(6, (MessageLite)this.extension_.get(i));
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeMessage(7, this.options_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      for (int j = 0; j < this.field_.size(); j++)
        i += CodedOutputStream.computeMessageSize(2, (MessageLite)this.field_.get(j));
      for (j = 0; j < this.nestedType_.size(); j++)
        i += CodedOutputStream.computeMessageSize(3, (MessageLite)this.nestedType_.get(j));
      for (j = 0; j < this.enumType_.size(); j++)
        i += CodedOutputStream.computeMessageSize(4, (MessageLite)this.enumType_.get(j));
      for (j = 0; j < this.extensionRange_.size(); j++)
        i += CodedOutputStream.computeMessageSize(5, (MessageLite)this.extensionRange_.get(j));
      for (j = 0; j < this.extension_.size(); j++)
        i += CodedOutputStream.computeMessageSize(6, (MessageLite)this.extension_.get(j));
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeMessageSize(7, this.options_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static DescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static DescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static DescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static DescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static DescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static DescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static DescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static DescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static DescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static DescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$3500();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(DescriptorProto paramDescriptorProto)
    {
      return newBuilder().mergeFrom(paramDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.DescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private List field_ = Collections.emptyList();
      private RepeatedFieldBuilder fieldBuilder_;
      private List extension_ = Collections.emptyList();
      private RepeatedFieldBuilder extensionBuilder_;
      private List nestedType_ = Collections.emptyList();
      private RepeatedFieldBuilder nestedTypeBuilder_;
      private List enumType_ = Collections.emptyList();
      private RepeatedFieldBuilder enumTypeBuilder_;
      private List extensionRange_ = Collections.emptyList();
      private RepeatedFieldBuilder extensionRangeBuilder_;
      private DescriptorProtos.MessageOptions options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
        {
          getFieldFieldBuilder();
          getExtensionFieldBuilder();
          getNestedTypeFieldBuilder();
          getEnumTypeFieldBuilder();
          getExtensionRangeFieldBuilder();
          getOptionsFieldBuilder();
        }
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        if (this.fieldBuilder_ == null)
        {
          this.field_ = Collections.emptyList();
          this.bitField0_ &= -3;
        }
        else
        {
          this.fieldBuilder_.clear();
        }
        if (this.extensionBuilder_ == null)
        {
          this.extension_ = Collections.emptyList();
          this.bitField0_ &= -5;
        }
        else
        {
          this.extensionBuilder_.clear();
        }
        if (this.nestedTypeBuilder_ == null)
        {
          this.nestedType_ = Collections.emptyList();
          this.bitField0_ &= -9;
        }
        else
        {
          this.nestedTypeBuilder_.clear();
        }
        if (this.enumTypeBuilder_ == null)
        {
          this.enumType_ = Collections.emptyList();
          this.bitField0_ &= -17;
        }
        else
        {
          this.enumTypeBuilder_.clear();
        }
        if (this.extensionRangeBuilder_ == null)
        {
          this.extensionRange_ = Collections.emptyList();
          this.bitField0_ &= -33;
        }
        else
        {
          this.extensionRangeBuilder_.clear();
        }
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -65;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.DescriptorProto.getDescriptor();
      }

      public DescriptorProtos.DescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.DescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.DescriptorProto build()
      {
        DescriptorProtos.DescriptorProto localDescriptorProto = buildPartial();
        if (!localDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localDescriptorProto);
        return localDescriptorProto;
      }

      private DescriptorProtos.DescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.DescriptorProto localDescriptorProto = buildPartial();
        if (!localDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localDescriptorProto).asInvalidProtocolBufferException();
        return localDescriptorProto;
      }

      public DescriptorProtos.DescriptorProto buildPartial()
      {
        DescriptorProtos.DescriptorProto localDescriptorProto = new DescriptorProtos.DescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localDescriptorProto.name_ = this.name_;
        if (this.fieldBuilder_ == null)
        {
          if ((this.bitField0_ & 0x2) == 2)
          {
            this.field_ = Collections.unmodifiableList(this.field_);
            this.bitField0_ &= -3;
          }
          localDescriptorProto.field_ = this.field_;
        }
        else
        {
          localDescriptorProto.field_ = this.fieldBuilder_.build();
        }
        if (this.extensionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x4) == 4)
          {
            this.extension_ = Collections.unmodifiableList(this.extension_);
            this.bitField0_ &= -5;
          }
          localDescriptorProto.extension_ = this.extension_;
        }
        else
        {
          localDescriptorProto.extension_ = this.extensionBuilder_.build();
        }
        if (this.nestedTypeBuilder_ == null)
        {
          if ((this.bitField0_ & 0x8) == 8)
          {
            this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
            this.bitField0_ &= -9;
          }
          localDescriptorProto.nestedType_ = this.nestedType_;
        }
        else
        {
          localDescriptorProto.nestedType_ = this.nestedTypeBuilder_.build();
        }
        if (this.enumTypeBuilder_ == null)
        {
          if ((this.bitField0_ & 0x10) == 16)
          {
            this.enumType_ = Collections.unmodifiableList(this.enumType_);
            this.bitField0_ &= -17;
          }
          localDescriptorProto.enumType_ = this.enumType_;
        }
        else
        {
          localDescriptorProto.enumType_ = this.enumTypeBuilder_.build();
        }
        if (this.extensionRangeBuilder_ == null)
        {
          if ((this.bitField0_ & 0x20) == 32)
          {
            this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
            this.bitField0_ &= -33;
          }
          localDescriptorProto.extensionRange_ = this.extensionRange_;
        }
        else
        {
          localDescriptorProto.extensionRange_ = this.extensionRangeBuilder_.build();
        }
        if ((i & 0x40) == 64)
          j |= 2;
        if (this.optionsBuilder_ == null)
          localDescriptorProto.options_ = this.options_;
        else
          localDescriptorProto.options_ = ((DescriptorProtos.MessageOptions)this.optionsBuilder_.build());
        localDescriptorProto.bitField0_ = j;
        onBuilt();
        return localDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.DescriptorProto))
          return mergeFrom((DescriptorProtos.DescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (paramDescriptorProto == DescriptorProtos.DescriptorProto.getDefaultInstance())
          return this;
        if (paramDescriptorProto.hasName())
          setName(paramDescriptorProto.getName());
        if (this.fieldBuilder_ == null)
        {
          if (!paramDescriptorProto.field_.isEmpty())
          {
            if (this.field_.isEmpty())
            {
              this.field_ = paramDescriptorProto.field_;
              this.bitField0_ &= -3;
            }
            else
            {
              ensureFieldIsMutable();
              this.field_.addAll(paramDescriptorProto.field_);
            }
            onChanged();
          }
        }
        else if (!paramDescriptorProto.field_.isEmpty())
          if (this.fieldBuilder_.isEmpty())
          {
            this.fieldBuilder_.dispose();
            this.fieldBuilder_ = null;
            this.field_ = paramDescriptorProto.field_;
            this.bitField0_ &= -3;
            this.fieldBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getFieldFieldBuilder() : null);
          }
          else
          {
            this.fieldBuilder_.addAllMessages(paramDescriptorProto.field_);
          }
        if (this.extensionBuilder_ == null)
        {
          if (!paramDescriptorProto.extension_.isEmpty())
          {
            if (this.extension_.isEmpty())
            {
              this.extension_ = paramDescriptorProto.extension_;
              this.bitField0_ &= -5;
            }
            else
            {
              ensureExtensionIsMutable();
              this.extension_.addAll(paramDescriptorProto.extension_);
            }
            onChanged();
          }
        }
        else if (!paramDescriptorProto.extension_.isEmpty())
          if (this.extensionBuilder_.isEmpty())
          {
            this.extensionBuilder_.dispose();
            this.extensionBuilder_ = null;
            this.extension_ = paramDescriptorProto.extension_;
            this.bitField0_ &= -5;
            this.extensionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null);
          }
          else
          {
            this.extensionBuilder_.addAllMessages(paramDescriptorProto.extension_);
          }
        if (this.nestedTypeBuilder_ == null)
        {
          if (!paramDescriptorProto.nestedType_.isEmpty())
          {
            if (this.nestedType_.isEmpty())
            {
              this.nestedType_ = paramDescriptorProto.nestedType_;
              this.bitField0_ &= -9;
            }
            else
            {
              ensureNestedTypeIsMutable();
              this.nestedType_.addAll(paramDescriptorProto.nestedType_);
            }
            onChanged();
          }
        }
        else if (!paramDescriptorProto.nestedType_.isEmpty())
          if (this.nestedTypeBuilder_.isEmpty())
          {
            this.nestedTypeBuilder_.dispose();
            this.nestedTypeBuilder_ = null;
            this.nestedType_ = paramDescriptorProto.nestedType_;
            this.bitField0_ &= -9;
            this.nestedTypeBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getNestedTypeFieldBuilder() : null);
          }
          else
          {
            this.nestedTypeBuilder_.addAllMessages(paramDescriptorProto.nestedType_);
          }
        if (this.enumTypeBuilder_ == null)
        {
          if (!paramDescriptorProto.enumType_.isEmpty())
          {
            if (this.enumType_.isEmpty())
            {
              this.enumType_ = paramDescriptorProto.enumType_;
              this.bitField0_ &= -17;
            }
            else
            {
              ensureEnumTypeIsMutable();
              this.enumType_.addAll(paramDescriptorProto.enumType_);
            }
            onChanged();
          }
        }
        else if (!paramDescriptorProto.enumType_.isEmpty())
          if (this.enumTypeBuilder_.isEmpty())
          {
            this.enumTypeBuilder_.dispose();
            this.enumTypeBuilder_ = null;
            this.enumType_ = paramDescriptorProto.enumType_;
            this.bitField0_ &= -17;
            this.enumTypeBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null);
          }
          else
          {
            this.enumTypeBuilder_.addAllMessages(paramDescriptorProto.enumType_);
          }
        if (this.extensionRangeBuilder_ == null)
        {
          if (!paramDescriptorProto.extensionRange_.isEmpty())
          {
            if (this.extensionRange_.isEmpty())
            {
              this.extensionRange_ = paramDescriptorProto.extensionRange_;
              this.bitField0_ &= -33;
            }
            else
            {
              ensureExtensionRangeIsMutable();
              this.extensionRange_.addAll(paramDescriptorProto.extensionRange_);
            }
            onChanged();
          }
        }
        else if (!paramDescriptorProto.extensionRange_.isEmpty())
          if (this.extensionRangeBuilder_.isEmpty())
          {
            this.extensionRangeBuilder_.dispose();
            this.extensionRangeBuilder_ = null;
            this.extensionRange_ = paramDescriptorProto.extensionRange_;
            this.bitField0_ &= -33;
            this.extensionRangeBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getExtensionRangeFieldBuilder() : null);
          }
          else
          {
            this.extensionRangeBuilder_.addAllMessages(paramDescriptorProto.extensionRange_);
          }
        if (paramDescriptorProto.hasOptions())
          mergeOptions(paramDescriptorProto.getOptions());
        mergeUnknownFields(paramDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getFieldCount(); i++)
          if (!getField(i).isInitialized())
            return false;
        for (i = 0; i < getExtensionCount(); i++)
          if (!getExtension(i).isInitialized())
            return false;
        for (i = 0; i < getNestedTypeCount(); i++)
          if (!getNestedType(i).isInitialized())
            return false;
        for (i = 0; i < getEnumTypeCount(); i++)
          if (!getEnumType(i).isInitialized())
            return false;
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          Object localObject;
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            localObject = DescriptorProtos.FieldDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addField(((DescriptorProtos.FieldDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 26:
            localObject = DescriptorProtos.DescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addNestedType(((Builder)localObject).buildPartial());
            break;
          case 34:
            localObject = DescriptorProtos.EnumDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addEnumType(((DescriptorProtos.EnumDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 42:
            localObject = DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addExtensionRange(((DescriptorProtos.DescriptorProto.ExtensionRange.Builder)localObject).buildPartial());
            break;
          case 50:
            localObject = DescriptorProtos.FieldDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addExtension(((DescriptorProtos.FieldDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 58:
            localObject = DescriptorProtos.MessageOptions.newBuilder();
            if (hasOptions())
              ((DescriptorProtos.MessageOptions.Builder)localObject).mergeFrom(getOptions());
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            setOptions(((DescriptorProtos.MessageOptions.Builder)localObject).buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.DescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      private void ensureFieldIsMutable()
      {
        if ((this.bitField0_ & 0x2) != 2)
        {
          this.field_ = new ArrayList(this.field_);
          this.bitField0_ |= 2;
        }
      }

      public List getFieldList()
      {
        if (this.fieldBuilder_ == null)
          return Collections.unmodifiableList(this.field_);
        return this.fieldBuilder_.getMessageList();
      }

      public int getFieldCount()
      {
        if (this.fieldBuilder_ == null)
          return this.field_.size();
        return this.fieldBuilder_.getCount();
      }

      public DescriptorProtos.FieldDescriptorProto getField(int paramInt)
      {
        if (this.fieldBuilder_ == null)
          return (DescriptorProtos.FieldDescriptorProto)this.field_.get(paramInt);
        return (DescriptorProtos.FieldDescriptorProto)this.fieldBuilder_.getMessage(paramInt);
      }

      public Builder setField(int paramInt, DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.fieldBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureFieldIsMutable();
          this.field_.set(paramInt, paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.fieldBuilder_.setMessage(paramInt, paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder setField(int paramInt, DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.fieldBuilder_ == null)
        {
          ensureFieldIsMutable();
          this.field_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.fieldBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addField(DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.fieldBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureFieldIsMutable();
          this.field_.add(paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.fieldBuilder_.addMessage(paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder addField(int paramInt, DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.fieldBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureFieldIsMutable();
          this.field_.add(paramInt, paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.fieldBuilder_.addMessage(paramInt, paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder addField(DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.fieldBuilder_ == null)
        {
          ensureFieldIsMutable();
          this.field_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.fieldBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addField(int paramInt, DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.fieldBuilder_ == null)
        {
          ensureFieldIsMutable();
          this.field_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.fieldBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllField(Iterable paramIterable)
      {
        if (this.fieldBuilder_ == null)
        {
          ensureFieldIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.field_);
          onChanged();
        }
        else
        {
          this.fieldBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearField()
      {
        if (this.fieldBuilder_ == null)
        {
          this.field_ = Collections.emptyList();
          this.bitField0_ &= -3;
          onChanged();
        }
        else
        {
          this.fieldBuilder_.clear();
        }
        return this;
      }

      public Builder removeField(int paramInt)
      {
        if (this.fieldBuilder_ == null)
        {
          ensureFieldIsMutable();
          this.field_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.fieldBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.FieldDescriptorProto.Builder getFieldBuilder(int paramInt)
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getFieldFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.FieldDescriptorProtoOrBuilder getFieldOrBuilder(int paramInt)
      {
        if (this.fieldBuilder_ == null)
          return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.field_.get(paramInt);
        return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.fieldBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getFieldOrBuilderList()
      {
        if (this.fieldBuilder_ != null)
          return this.fieldBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.field_);
      }

      public DescriptorProtos.FieldDescriptorProto.Builder addFieldBuilder()
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getFieldFieldBuilder().addBuilder(DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.FieldDescriptorProto.Builder addFieldBuilder(int paramInt)
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getFieldFieldBuilder().addBuilder(paramInt, DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
      }

      public List getFieldBuilderList()
      {
        return getFieldFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getFieldFieldBuilder()
      {
        if (this.fieldBuilder_ == null)
        {
          this.fieldBuilder_ = new RepeatedFieldBuilder(this.field_, (this.bitField0_ & 0x2) == 2, getParentForChildren(), isClean());
          this.field_ = null;
        }
        return this.fieldBuilder_;
      }

      private void ensureExtensionIsMutable()
      {
        if ((this.bitField0_ & 0x4) != 4)
        {
          this.extension_ = new ArrayList(this.extension_);
          this.bitField0_ |= 4;
        }
      }

      public List getExtensionList()
      {
        if (this.extensionBuilder_ == null)
          return Collections.unmodifiableList(this.extension_);
        return this.extensionBuilder_.getMessageList();
      }

      public int getExtensionCount()
      {
        if (this.extensionBuilder_ == null)
          return this.extension_.size();
        return this.extensionBuilder_.getCount();
      }

      public DescriptorProtos.FieldDescriptorProto getExtension(int paramInt)
      {
        if (this.extensionBuilder_ == null)
          return (DescriptorProtos.FieldDescriptorProto)this.extension_.get(paramInt);
        return (DescriptorProtos.FieldDescriptorProto)this.extensionBuilder_.getMessage(paramInt);
      }

      public Builder setExtension(int paramInt, DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.extensionBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureExtensionIsMutable();
          this.extension_.set(paramInt, paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.setMessage(paramInt, paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder setExtension(int paramInt, DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addExtension(DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.extensionBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureExtensionIsMutable();
          this.extension_.add(paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder addExtension(int paramInt, DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.extensionBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureExtensionIsMutable();
          this.extension_.add(paramInt, paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramInt, paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder addExtension(DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addExtension(int paramInt, DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllExtension(Iterable paramIterable)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.extension_);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearExtension()
      {
        if (this.extensionBuilder_ == null)
        {
          this.extension_ = Collections.emptyList();
          this.bitField0_ &= -5;
          onChanged();
        }
        else
        {
          this.extensionBuilder_.clear();
        }
        return this;
      }

      public Builder removeExtension(int paramInt)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.FieldDescriptorProto.Builder getExtensionBuilder(int paramInt)
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getExtensionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int paramInt)
      {
        if (this.extensionBuilder_ == null)
          return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.extension_.get(paramInt);
        return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.extensionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getExtensionOrBuilderList()
      {
        if (this.extensionBuilder_ != null)
          return this.extensionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.extension_);
      }

      public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder()
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getExtensionFieldBuilder().addBuilder(DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder(int paramInt)
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getExtensionFieldBuilder().addBuilder(paramInt, DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
      }

      public List getExtensionBuilderList()
      {
        return getExtensionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getExtensionFieldBuilder()
      {
        if (this.extensionBuilder_ == null)
        {
          this.extensionBuilder_ = new RepeatedFieldBuilder(this.extension_, (this.bitField0_ & 0x4) == 4, getParentForChildren(), isClean());
          this.extension_ = null;
        }
        return this.extensionBuilder_;
      }

      private void ensureNestedTypeIsMutable()
      {
        if ((this.bitField0_ & 0x8) != 8)
        {
          this.nestedType_ = new ArrayList(this.nestedType_);
          this.bitField0_ |= 8;
        }
      }

      public List getNestedTypeList()
      {
        if (this.nestedTypeBuilder_ == null)
          return Collections.unmodifiableList(this.nestedType_);
        return this.nestedTypeBuilder_.getMessageList();
      }

      public int getNestedTypeCount()
      {
        if (this.nestedTypeBuilder_ == null)
          return this.nestedType_.size();
        return this.nestedTypeBuilder_.getCount();
      }

      public DescriptorProtos.DescriptorProto getNestedType(int paramInt)
      {
        if (this.nestedTypeBuilder_ == null)
          return (DescriptorProtos.DescriptorProto)this.nestedType_.get(paramInt);
        return (DescriptorProtos.DescriptorProto)this.nestedTypeBuilder_.getMessage(paramInt);
      }

      public Builder setNestedType(int paramInt, DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          if (paramDescriptorProto == null)
            throw new NullPointerException();
          ensureNestedTypeIsMutable();
          this.nestedType_.set(paramInt, paramDescriptorProto);
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.setMessage(paramInt, paramDescriptorProto);
        }
        return this;
      }

      public Builder setNestedType(int paramInt, Builder paramBuilder)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          ensureNestedTypeIsMutable();
          this.nestedType_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addNestedType(DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          if (paramDescriptorProto == null)
            throw new NullPointerException();
          ensureNestedTypeIsMutable();
          this.nestedType_.add(paramDescriptorProto);
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.addMessage(paramDescriptorProto);
        }
        return this;
      }

      public Builder addNestedType(int paramInt, DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          if (paramDescriptorProto == null)
            throw new NullPointerException();
          ensureNestedTypeIsMutable();
          this.nestedType_.add(paramInt, paramDescriptorProto);
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.addMessage(paramInt, paramDescriptorProto);
        }
        return this;
      }

      public Builder addNestedType(Builder paramBuilder)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          ensureNestedTypeIsMutable();
          this.nestedType_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addNestedType(int paramInt, Builder paramBuilder)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          ensureNestedTypeIsMutable();
          this.nestedType_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllNestedType(Iterable paramIterable)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          ensureNestedTypeIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.nestedType_);
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearNestedType()
      {
        if (this.nestedTypeBuilder_ == null)
        {
          this.nestedType_ = Collections.emptyList();
          this.bitField0_ &= -9;
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.clear();
        }
        return this;
      }

      public Builder removeNestedType(int paramInt)
      {
        if (this.nestedTypeBuilder_ == null)
        {
          ensureNestedTypeIsMutable();
          this.nestedType_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.nestedTypeBuilder_.remove(paramInt);
        }
        return this;
      }

      public Builder getNestedTypeBuilder(int paramInt)
      {
        return (Builder)getNestedTypeFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.DescriptorProtoOrBuilder getNestedTypeOrBuilder(int paramInt)
      {
        if (this.nestedTypeBuilder_ == null)
          return (DescriptorProtos.DescriptorProtoOrBuilder)this.nestedType_.get(paramInt);
        return (DescriptorProtos.DescriptorProtoOrBuilder)this.nestedTypeBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getNestedTypeOrBuilderList()
      {
        if (this.nestedTypeBuilder_ != null)
          return this.nestedTypeBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.nestedType_);
      }

      public Builder addNestedTypeBuilder()
      {
        return (Builder)getNestedTypeFieldBuilder().addBuilder(DescriptorProtos.DescriptorProto.getDefaultInstance());
      }

      public Builder addNestedTypeBuilder(int paramInt)
      {
        return (Builder)getNestedTypeFieldBuilder().addBuilder(paramInt, DescriptorProtos.DescriptorProto.getDefaultInstance());
      }

      public List getNestedTypeBuilderList()
      {
        return getNestedTypeFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getNestedTypeFieldBuilder()
      {
        if (this.nestedTypeBuilder_ == null)
        {
          this.nestedTypeBuilder_ = new RepeatedFieldBuilder(this.nestedType_, (this.bitField0_ & 0x8) == 8, getParentForChildren(), isClean());
          this.nestedType_ = null;
        }
        return this.nestedTypeBuilder_;
      }

      private void ensureEnumTypeIsMutable()
      {
        if ((this.bitField0_ & 0x10) != 16)
        {
          this.enumType_ = new ArrayList(this.enumType_);
          this.bitField0_ |= 16;
        }
      }

      public List getEnumTypeList()
      {
        if (this.enumTypeBuilder_ == null)
          return Collections.unmodifiableList(this.enumType_);
        return this.enumTypeBuilder_.getMessageList();
      }

      public int getEnumTypeCount()
      {
        if (this.enumTypeBuilder_ == null)
          return this.enumType_.size();
        return this.enumTypeBuilder_.getCount();
      }

      public DescriptorProtos.EnumDescriptorProto getEnumType(int paramInt)
      {
        if (this.enumTypeBuilder_ == null)
          return (DescriptorProtos.EnumDescriptorProto)this.enumType_.get(paramInt);
        return (DescriptorProtos.EnumDescriptorProto)this.enumTypeBuilder_.getMessage(paramInt);
      }

      public Builder setEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (this.enumTypeBuilder_ == null)
        {
          if (paramEnumDescriptorProto == null)
            throw new NullPointerException();
          ensureEnumTypeIsMutable();
          this.enumType_.set(paramInt, paramEnumDescriptorProto);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.setMessage(paramInt, paramEnumDescriptorProto);
        }
        return this;
      }

      public Builder setEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto.Builder paramBuilder)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addEnumType(DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (this.enumTypeBuilder_ == null)
        {
          if (paramEnumDescriptorProto == null)
            throw new NullPointerException();
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramEnumDescriptorProto);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramEnumDescriptorProto);
        }
        return this;
      }

      public Builder addEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (this.enumTypeBuilder_ == null)
        {
          if (paramEnumDescriptorProto == null)
            throw new NullPointerException();
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramInt, paramEnumDescriptorProto);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramInt, paramEnumDescriptorProto);
        }
        return this;
      }

      public Builder addEnumType(DescriptorProtos.EnumDescriptorProto.Builder paramBuilder)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto.Builder paramBuilder)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllEnumType(Iterable paramIterable)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.enumType_);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearEnumType()
      {
        if (this.enumTypeBuilder_ == null)
        {
          this.enumType_ = Collections.emptyList();
          this.bitField0_ &= -17;
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.clear();
        }
        return this;
      }

      public Builder removeEnumType(int paramInt)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.EnumDescriptorProto.Builder getEnumTypeBuilder(int paramInt)
      {
        return (DescriptorProtos.EnumDescriptorProto.Builder)getEnumTypeFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int paramInt)
      {
        if (this.enumTypeBuilder_ == null)
          return (DescriptorProtos.EnumDescriptorProtoOrBuilder)this.enumType_.get(paramInt);
        return (DescriptorProtos.EnumDescriptorProtoOrBuilder)this.enumTypeBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getEnumTypeOrBuilderList()
      {
        if (this.enumTypeBuilder_ != null)
          return this.enumTypeBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.enumType_);
      }

      public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder()
      {
        return (DescriptorProtos.EnumDescriptorProto.Builder)getEnumTypeFieldBuilder().addBuilder(DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder(int paramInt)
      {
        return (DescriptorProtos.EnumDescriptorProto.Builder)getEnumTypeFieldBuilder().addBuilder(paramInt, DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
      }

      public List getEnumTypeBuilderList()
      {
        return getEnumTypeFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getEnumTypeFieldBuilder()
      {
        if (this.enumTypeBuilder_ == null)
        {
          this.enumTypeBuilder_ = new RepeatedFieldBuilder(this.enumType_, (this.bitField0_ & 0x10) == 16, getParentForChildren(), isClean());
          this.enumType_ = null;
        }
        return this.enumTypeBuilder_;
      }

      private void ensureExtensionRangeIsMutable()
      {
        if ((this.bitField0_ & 0x20) != 32)
        {
          this.extensionRange_ = new ArrayList(this.extensionRange_);
          this.bitField0_ |= 32;
        }
      }

      public List getExtensionRangeList()
      {
        if (this.extensionRangeBuilder_ == null)
          return Collections.unmodifiableList(this.extensionRange_);
        return this.extensionRangeBuilder_.getMessageList();
      }

      public int getExtensionRangeCount()
      {
        if (this.extensionRangeBuilder_ == null)
          return this.extensionRange_.size();
        return this.extensionRangeBuilder_.getCount();
      }

      public DescriptorProtos.DescriptorProto.ExtensionRange getExtensionRange(int paramInt)
      {
        if (this.extensionRangeBuilder_ == null)
          return (DescriptorProtos.DescriptorProto.ExtensionRange)this.extensionRange_.get(paramInt);
        return (DescriptorProtos.DescriptorProto.ExtensionRange)this.extensionRangeBuilder_.getMessage(paramInt);
      }

      public Builder setExtensionRange(int paramInt, DescriptorProtos.DescriptorProto.ExtensionRange paramExtensionRange)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          if (paramExtensionRange == null)
            throw new NullPointerException();
          ensureExtensionRangeIsMutable();
          this.extensionRange_.set(paramInt, paramExtensionRange);
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.setMessage(paramInt, paramExtensionRange);
        }
        return this;
      }

      public Builder setExtensionRange(int paramInt, DescriptorProtos.DescriptorProto.ExtensionRange.Builder paramBuilder)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          ensureExtensionRangeIsMutable();
          this.extensionRange_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange paramExtensionRange)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          if (paramExtensionRange == null)
            throw new NullPointerException();
          ensureExtensionRangeIsMutable();
          this.extensionRange_.add(paramExtensionRange);
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.addMessage(paramExtensionRange);
        }
        return this;
      }

      public Builder addExtensionRange(int paramInt, DescriptorProtos.DescriptorProto.ExtensionRange paramExtensionRange)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          if (paramExtensionRange == null)
            throw new NullPointerException();
          ensureExtensionRangeIsMutable();
          this.extensionRange_.add(paramInt, paramExtensionRange);
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.addMessage(paramInt, paramExtensionRange);
        }
        return this;
      }

      public Builder addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.Builder paramBuilder)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          ensureExtensionRangeIsMutable();
          this.extensionRange_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addExtensionRange(int paramInt, DescriptorProtos.DescriptorProto.ExtensionRange.Builder paramBuilder)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          ensureExtensionRangeIsMutable();
          this.extensionRange_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllExtensionRange(Iterable paramIterable)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          ensureExtensionRangeIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.extensionRange_);
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearExtensionRange()
      {
        if (this.extensionRangeBuilder_ == null)
        {
          this.extensionRange_ = Collections.emptyList();
          this.bitField0_ &= -33;
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.clear();
        }
        return this;
      }

      public Builder removeExtensionRange(int paramInt)
      {
        if (this.extensionRangeBuilder_ == null)
        {
          ensureExtensionRangeIsMutable();
          this.extensionRange_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.extensionRangeBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.DescriptorProto.ExtensionRange.Builder getExtensionRangeBuilder(int paramInt)
      {
        return (DescriptorProtos.DescriptorProto.ExtensionRange.Builder)getExtensionRangeFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int paramInt)
      {
        if (this.extensionRangeBuilder_ == null)
          return (DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder)this.extensionRange_.get(paramInt);
        return (DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder)this.extensionRangeBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getExtensionRangeOrBuilderList()
      {
        if (this.extensionRangeBuilder_ != null)
          return this.extensionRangeBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.extensionRange_);
      }

      public DescriptorProtos.DescriptorProto.ExtensionRange.Builder addExtensionRangeBuilder()
      {
        return (DescriptorProtos.DescriptorProto.ExtensionRange.Builder)getExtensionRangeFieldBuilder().addBuilder(DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance());
      }

      public DescriptorProtos.DescriptorProto.ExtensionRange.Builder addExtensionRangeBuilder(int paramInt)
      {
        return (DescriptorProtos.DescriptorProto.ExtensionRange.Builder)getExtensionRangeFieldBuilder().addBuilder(paramInt, DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance());
      }

      public List getExtensionRangeBuilderList()
      {
        return getExtensionRangeFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getExtensionRangeFieldBuilder()
      {
        if (this.extensionRangeBuilder_ == null)
        {
          this.extensionRangeBuilder_ = new RepeatedFieldBuilder(this.extensionRange_, (this.bitField0_ & 0x20) == 32, getParentForChildren(), isClean());
          this.extensionRange_ = null;
        }
        return this.extensionRangeBuilder_;
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x40) == 64;
      }

      public DescriptorProtos.MessageOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.MessageOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.MessageOptions paramMessageOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramMessageOptions == null)
            throw new NullPointerException();
          this.options_ = paramMessageOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramMessageOptions);
        }
        this.bitField0_ |= 64;
        return this;
      }

      public Builder setOptions(DescriptorProtos.MessageOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 64;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.MessageOptions paramMessageOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x40) == 64) && (this.options_ != DescriptorProtos.MessageOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.MessageOptions.newBuilder(this.options_).mergeFrom(paramMessageOptions).buildPartial();
          else
            this.options_ = paramMessageOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramMessageOptions);
        }
        this.bitField0_ |= 64;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -65;
        return this;
      }

      public DescriptorProtos.MessageOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 64;
        onChanged();
        return (DescriptorProtos.MessageOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.MessageOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.MessageOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }
    }

    public static final class ExtensionRange extends GeneratedMessage
      implements DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder
    {
      private static final ExtensionRange defaultInstance = new ExtensionRange(true);
      private int bitField0_;
      public static final int START_FIELD_NUMBER = 1;
      private int start_;
      public static final int END_FIELD_NUMBER = 2;
      private int end_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private static final long serialVersionUID = 0L;

      private ExtensionRange(Builder paramBuilder)
      {
        super();
      }

      private ExtensionRange(boolean paramBoolean)
      {
      }

      public static ExtensionRange getDefaultInstance()
      {
        return defaultInstance;
      }

      public ExtensionRange getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
      }

      public boolean hasStart()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public int getStart()
      {
        return this.start_;
      }

      public boolean hasEnd()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public int getEnd()
      {
        return this.end_;
      }

      private void initFields()
      {
        this.start_ = 0;
        this.end_ = 0;
      }

      public final boolean isInitialized()
      {
        int i = this.memoizedIsInitialized;
        if (i != -1)
          return i == 1;
        this.memoizedIsInitialized = 1;
        return true;
      }

      public void writeTo(CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        getSerializedSize();
        if ((this.bitField0_ & 0x1) == 1)
          paramCodedOutputStream.writeInt32(1, this.start_);
        if ((this.bitField0_ & 0x2) == 2)
          paramCodedOutputStream.writeInt32(2, this.end_);
        getUnknownFields().writeTo(paramCodedOutputStream);
      }

      public int getSerializedSize()
      {
        int i = this.memoizedSerializedSize;
        if (i != -1)
          return i;
        i = 0;
        if ((this.bitField0_ & 0x1) == 1)
          i += CodedOutputStream.computeInt32Size(1, this.start_);
        if ((this.bitField0_ & 0x2) == 2)
          i += CodedOutputStream.computeInt32Size(2, this.end_);
        i += getUnknownFields().getSerializedSize();
        this.memoizedSerializedSize = i;
        return i;
      }

      protected Object writeReplace()
        throws ObjectStreamException
      {
        return super.writeReplace();
      }

      public static ExtensionRange parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static ExtensionRange parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static ExtensionRange parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static ExtensionRange parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public static ExtensionRange parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static ExtensionRange parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static ExtensionRange parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static ExtensionRange parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static ExtensionRange parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static ExtensionRange parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static Builder newBuilder()
      {
        return Builder.access$2800();
      }

      public Builder newBuilderForType()
      {
        return newBuilder();
      }

      public static Builder newBuilder(ExtensionRange paramExtensionRange)
      {
        return newBuilder().mergeFrom(paramExtensionRange);
      }

      public Builder toBuilder()
      {
        return newBuilder(this);
      }

      protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        Builder localBuilder = new Builder(paramBuilderParent, null);
        return localBuilder;
      }

      static
      {
        defaultInstance.initFields();
      }

      public static final class Builder extends GeneratedMessage.Builder
        implements DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder
      {
        private int bitField0_;
        private int start_;
        private int end_;

        public static final Descriptors.Descriptor getDescriptor()
        {
          return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
        }

        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
        {
          return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
        }

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
        {
          super();
          maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization()
        {
          if (GeneratedMessage.alwaysUseFieldBuilders);
        }

        private static Builder create()
        {
          return new Builder();
        }

        public Builder clear()
        {
          super.clear();
          this.start_ = 0;
          this.bitField0_ &= -2;
          this.end_ = 0;
          this.bitField0_ &= -3;
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public Descriptors.Descriptor getDescriptorForType()
        {
          return DescriptorProtos.DescriptorProto.ExtensionRange.getDescriptor();
        }

        public DescriptorProtos.DescriptorProto.ExtensionRange getDefaultInstanceForType()
        {
          return DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance();
        }

        public DescriptorProtos.DescriptorProto.ExtensionRange build()
        {
          DescriptorProtos.DescriptorProto.ExtensionRange localExtensionRange = buildPartial();
          if (!localExtensionRange.isInitialized())
            throw newUninitializedMessageException(localExtensionRange);
          return localExtensionRange;
        }

        private DescriptorProtos.DescriptorProto.ExtensionRange buildParsed()
          throws InvalidProtocolBufferException
        {
          DescriptorProtos.DescriptorProto.ExtensionRange localExtensionRange = buildPartial();
          if (!localExtensionRange.isInitialized())
            throw newUninitializedMessageException(localExtensionRange).asInvalidProtocolBufferException();
          return localExtensionRange;
        }

        public DescriptorProtos.DescriptorProto.ExtensionRange buildPartial()
        {
          DescriptorProtos.DescriptorProto.ExtensionRange localExtensionRange = new DescriptorProtos.DescriptorProto.ExtensionRange(this, null);
          int i = this.bitField0_;
          int j = 0;
          if ((i & 0x1) == 1)
            j |= 1;
          localExtensionRange.start_ = this.start_;
          if ((i & 0x2) == 2)
            j |= 2;
          localExtensionRange.end_ = this.end_;
          localExtensionRange.bitField0_ = j;
          onBuilt();
          return localExtensionRange;
        }

        public Builder mergeFrom(Message paramMessage)
        {
          if ((paramMessage instanceof DescriptorProtos.DescriptorProto.ExtensionRange))
            return mergeFrom((DescriptorProtos.DescriptorProto.ExtensionRange)paramMessage);
          super.mergeFrom(paramMessage);
          return this;
        }

        public Builder mergeFrom(DescriptorProtos.DescriptorProto.ExtensionRange paramExtensionRange)
        {
          if (paramExtensionRange == DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance())
            return this;
          if (paramExtensionRange.hasStart())
            setStart(paramExtensionRange.getStart());
          if (paramExtensionRange.hasEnd())
            setEnd(paramExtensionRange.getEnd());
          mergeUnknownFields(paramExtensionRange.getUnknownFields());
          return this;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
          throws IOException
        {
          UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
          while (true)
          {
            int i = paramCodedInputStream.readTag();
            switch (i)
            {
            case 0:
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            default:
              if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
              {
                setUnknownFields(localBuilder.build());
                onChanged();
                return this;
              }
              break;
            case 8:
              this.bitField0_ |= 1;
              this.start_ = paramCodedInputStream.readInt32();
              break;
            case 16:
              this.bitField0_ |= 2;
              this.end_ = paramCodedInputStream.readInt32();
            }
          }
        }

        public boolean hasStart()
        {
          return (this.bitField0_ & 0x1) == 1;
        }

        public int getStart()
        {
          return this.start_;
        }

        public Builder setStart(int paramInt)
        {
          this.bitField0_ |= 1;
          this.start_ = paramInt;
          onChanged();
          return this;
        }

        public Builder clearStart()
        {
          this.bitField0_ &= -2;
          this.start_ = 0;
          onChanged();
          return this;
        }

        public boolean hasEnd()
        {
          return (this.bitField0_ & 0x2) == 2;
        }

        public int getEnd()
        {
          return this.end_;
        }

        public Builder setEnd(int paramInt)
        {
          this.bitField0_ |= 2;
          this.end_ = paramInt;
          onChanged();
          return this;
        }

        public Builder clearEnd()
        {
          this.bitField0_ &= -3;
          this.end_ = 0;
          onChanged();
          return this;
        }
      }
    }

    public static abstract interface ExtensionRangeOrBuilder extends MessageOrBuilder
    {
      public abstract boolean hasStart();

      public abstract int getStart();

      public abstract boolean hasEnd();

      public abstract int getEnd();
    }
  }

  public static abstract interface DescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract List getFieldList();

    public abstract DescriptorProtos.FieldDescriptorProto getField(int paramInt);

    public abstract int getFieldCount();

    public abstract List getFieldOrBuilderList();

    public abstract DescriptorProtos.FieldDescriptorProtoOrBuilder getFieldOrBuilder(int paramInt);

    public abstract List getExtensionList();

    public abstract DescriptorProtos.FieldDescriptorProto getExtension(int paramInt);

    public abstract int getExtensionCount();

    public abstract List getExtensionOrBuilderList();

    public abstract DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int paramInt);

    public abstract List getNestedTypeList();

    public abstract DescriptorProtos.DescriptorProto getNestedType(int paramInt);

    public abstract int getNestedTypeCount();

    public abstract List getNestedTypeOrBuilderList();

    public abstract DescriptorProtoOrBuilder getNestedTypeOrBuilder(int paramInt);

    public abstract List getEnumTypeList();

    public abstract DescriptorProtos.EnumDescriptorProto getEnumType(int paramInt);

    public abstract int getEnumTypeCount();

    public abstract List getEnumTypeOrBuilderList();

    public abstract DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int paramInt);

    public abstract List getExtensionRangeList();

    public abstract DescriptorProtos.DescriptorProto.ExtensionRange getExtensionRange(int paramInt);

    public abstract int getExtensionRangeCount();

    public abstract List getExtensionRangeOrBuilderList();

    public abstract DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int paramInt);

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.MessageOptions getOptions();

    public abstract DescriptorProtos.MessageOptionsOrBuilder getOptionsOrBuilder();
  }

  public static final class FileDescriptorProto extends GeneratedMessage
    implements DescriptorProtos.FileDescriptorProtoOrBuilder
  {
    private static final FileDescriptorProto defaultInstance = new FileDescriptorProto(true);
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int PACKAGE_FIELD_NUMBER = 2;
    private Object package_;
    public static final int DEPENDENCY_FIELD_NUMBER = 3;
    private LazyStringList dependency_;
    public static final int MESSAGE_TYPE_FIELD_NUMBER = 4;
    private List messageType_;
    public static final int ENUM_TYPE_FIELD_NUMBER = 5;
    private List enumType_;
    public static final int SERVICE_FIELD_NUMBER = 6;
    private List service_;
    public static final int EXTENSION_FIELD_NUMBER = 7;
    private List extension_;
    public static final int OPTIONS_FIELD_NUMBER = 8;
    private DescriptorProtos.FileOptions options_;
    public static final int SOURCE_CODE_INFO_FIELD_NUMBER = 9;
    private DescriptorProtos.SourceCodeInfo sourceCodeInfo_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private FileDescriptorProto(Builder paramBuilder)
    {
      super();
    }

    private FileDescriptorProto(boolean paramBoolean)
    {
    }

    public static FileDescriptorProto getDefaultInstance()
    {
      return defaultInstance;
    }

    public FileDescriptorProto getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
    }

    public boolean hasName()
    {
      return (this.bitField0_ & 0x1) == 1;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
      return str;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public boolean hasPackage()
    {
      return (this.bitField0_ & 0x2) == 2;
    }

    public String getPackage()
    {
      Object localObject = this.package_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.package_ = str;
      return str;
    }

    private ByteString getPackageBytes()
    {
      Object localObject = this.package_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.package_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public List getDependencyList()
    {
      return this.dependency_;
    }

    public int getDependencyCount()
    {
      return this.dependency_.size();
    }

    public String getDependency(int paramInt)
    {
      return (String)this.dependency_.get(paramInt);
    }

    public List getMessageTypeList()
    {
      return this.messageType_;
    }

    public List getMessageTypeOrBuilderList()
    {
      return this.messageType_;
    }

    public int getMessageTypeCount()
    {
      return this.messageType_.size();
    }

    public DescriptorProtos.DescriptorProto getMessageType(int paramInt)
    {
      return (DescriptorProtos.DescriptorProto)this.messageType_.get(paramInt);
    }

    public DescriptorProtos.DescriptorProtoOrBuilder getMessageTypeOrBuilder(int paramInt)
    {
      return (DescriptorProtos.DescriptorProtoOrBuilder)this.messageType_.get(paramInt);
    }

    public List getEnumTypeList()
    {
      return this.enumType_;
    }

    public List getEnumTypeOrBuilderList()
    {
      return this.enumType_;
    }

    public int getEnumTypeCount()
    {
      return this.enumType_.size();
    }

    public DescriptorProtos.EnumDescriptorProto getEnumType(int paramInt)
    {
      return (DescriptorProtos.EnumDescriptorProto)this.enumType_.get(paramInt);
    }

    public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int paramInt)
    {
      return (DescriptorProtos.EnumDescriptorProtoOrBuilder)this.enumType_.get(paramInt);
    }

    public List getServiceList()
    {
      return this.service_;
    }

    public List getServiceOrBuilderList()
    {
      return this.service_;
    }

    public int getServiceCount()
    {
      return this.service_.size();
    }

    public DescriptorProtos.ServiceDescriptorProto getService(int paramInt)
    {
      return (DescriptorProtos.ServiceDescriptorProto)this.service_.get(paramInt);
    }

    public DescriptorProtos.ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int paramInt)
    {
      return (DescriptorProtos.ServiceDescriptorProtoOrBuilder)this.service_.get(paramInt);
    }

    public List getExtensionList()
    {
      return this.extension_;
    }

    public List getExtensionOrBuilderList()
    {
      return this.extension_;
    }

    public int getExtensionCount()
    {
      return this.extension_.size();
    }

    public DescriptorProtos.FieldDescriptorProto getExtension(int paramInt)
    {
      return (DescriptorProtos.FieldDescriptorProto)this.extension_.get(paramInt);
    }

    public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int paramInt)
    {
      return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.extension_.get(paramInt);
    }

    public boolean hasOptions()
    {
      return (this.bitField0_ & 0x4) == 4;
    }

    public DescriptorProtos.FileOptions getOptions()
    {
      return this.options_;
    }

    public DescriptorProtos.FileOptionsOrBuilder getOptionsOrBuilder()
    {
      return this.options_;
    }

    public boolean hasSourceCodeInfo()
    {
      return (this.bitField0_ & 0x8) == 8;
    }

    public DescriptorProtos.SourceCodeInfo getSourceCodeInfo()
    {
      return this.sourceCodeInfo_;
    }

    public DescriptorProtos.SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder()
    {
      return this.sourceCodeInfo_;
    }

    private void initFields()
    {
      this.name_ = "";
      this.package_ = "";
      this.dependency_ = LazyStringArrayList.EMPTY;
      this.messageType_ = Collections.emptyList();
      this.enumType_ = Collections.emptyList();
      this.service_ = Collections.emptyList();
      this.extension_ = Collections.emptyList();
      this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
      this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getMessageTypeCount(); j++)
        if (!getMessageType(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (j = 0; j < getEnumTypeCount(); j++)
        if (!getEnumType(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (j = 0; j < getServiceCount(); j++)
        if (!getService(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (j = 0; j < getExtensionCount(); j++)
        if (!getExtension(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasOptions()) && (!getOptions().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((this.bitField0_ & 0x1) == 1)
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      if ((this.bitField0_ & 0x2) == 2)
        paramCodedOutputStream.writeBytes(2, getPackageBytes());
      for (int i = 0; i < this.dependency_.size(); i++)
        paramCodedOutputStream.writeBytes(3, this.dependency_.getByteString(i));
      for (i = 0; i < this.messageType_.size(); i++)
        paramCodedOutputStream.writeMessage(4, (MessageLite)this.messageType_.get(i));
      for (i = 0; i < this.enumType_.size(); i++)
        paramCodedOutputStream.writeMessage(5, (MessageLite)this.enumType_.get(i));
      for (i = 0; i < this.service_.size(); i++)
        paramCodedOutputStream.writeMessage(6, (MessageLite)this.service_.get(i));
      for (i = 0; i < this.extension_.size(); i++)
        paramCodedOutputStream.writeMessage(7, (MessageLite)this.extension_.get(i));
      if ((this.bitField0_ & 0x4) == 4)
        paramCodedOutputStream.writeMessage(8, this.options_);
      if ((this.bitField0_ & 0x8) == 8)
        paramCodedOutputStream.writeMessage(9, this.sourceCodeInfo_);
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      if ((this.bitField0_ & 0x1) == 1)
        i += CodedOutputStream.computeBytesSize(1, getNameBytes());
      if ((this.bitField0_ & 0x2) == 2)
        i += CodedOutputStream.computeBytesSize(2, getPackageBytes());
      int j = 0;
      for (int k = 0; k < this.dependency_.size(); k++)
        j += CodedOutputStream.computeBytesSizeNoTag(this.dependency_.getByteString(k));
      i += j;
      i += 1 * getDependencyList().size();
      for (j = 0; j < this.messageType_.size(); j++)
        i += CodedOutputStream.computeMessageSize(4, (MessageLite)this.messageType_.get(j));
      for (j = 0; j < this.enumType_.size(); j++)
        i += CodedOutputStream.computeMessageSize(5, (MessageLite)this.enumType_.get(j));
      for (j = 0; j < this.service_.size(); j++)
        i += CodedOutputStream.computeMessageSize(6, (MessageLite)this.service_.get(j));
      for (j = 0; j < this.extension_.size(); j++)
        i += CodedOutputStream.computeMessageSize(7, (MessageLite)this.extension_.get(j));
      if ((this.bitField0_ & 0x4) == 4)
        i += CodedOutputStream.computeMessageSize(8, this.options_);
      if ((this.bitField0_ & 0x8) == 8)
        i += CodedOutputStream.computeMessageSize(9, this.sourceCodeInfo_);
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static FileDescriptorProto parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FileDescriptorProto parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileDescriptorProto parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FileDescriptorProto parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileDescriptorProto parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FileDescriptorProto parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileDescriptorProto parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FileDescriptorProto parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FileDescriptorProto parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FileDescriptorProto parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$1000();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(FileDescriptorProto paramFileDescriptorProto)
    {
      return newBuilder().mergeFrom(paramFileDescriptorProto);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.FileDescriptorProtoOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private Object package_ = "";
      private LazyStringList dependency_ = LazyStringArrayList.EMPTY;
      private List messageType_ = Collections.emptyList();
      private RepeatedFieldBuilder messageTypeBuilder_;
      private List enumType_ = Collections.emptyList();
      private RepeatedFieldBuilder enumTypeBuilder_;
      private List service_ = Collections.emptyList();
      private RepeatedFieldBuilder serviceBuilder_;
      private List extension_ = Collections.emptyList();
      private RepeatedFieldBuilder extensionBuilder_;
      private DescriptorProtos.FileOptions options_ = DescriptorProtos.FileOptions.getDefaultInstance();
      private SingleFieldBuilder optionsBuilder_;
      private DescriptorProtos.SourceCodeInfo sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
      private SingleFieldBuilder sourceCodeInfoBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
        {
          getMessageTypeFieldBuilder();
          getEnumTypeFieldBuilder();
          getServiceFieldBuilder();
          getExtensionFieldBuilder();
          getOptionsFieldBuilder();
          getSourceCodeInfoFieldBuilder();
        }
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= -2;
        this.package_ = "";
        this.bitField0_ &= -3;
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -5;
        if (this.messageTypeBuilder_ == null)
        {
          this.messageType_ = Collections.emptyList();
          this.bitField0_ &= -9;
        }
        else
        {
          this.messageTypeBuilder_.clear();
        }
        if (this.enumTypeBuilder_ == null)
        {
          this.enumType_ = Collections.emptyList();
          this.bitField0_ &= -17;
        }
        else
        {
          this.enumTypeBuilder_.clear();
        }
        if (this.serviceBuilder_ == null)
        {
          this.service_ = Collections.emptyList();
          this.bitField0_ &= -33;
        }
        else
        {
          this.serviceBuilder_.clear();
        }
        if (this.extensionBuilder_ == null)
        {
          this.extension_ = Collections.emptyList();
          this.bitField0_ &= -65;
        }
        else
        {
          this.extensionBuilder_.clear();
        }
        if (this.optionsBuilder_ == null)
          this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
        else
          this.optionsBuilder_.clear();
        this.bitField0_ &= -129;
        if (this.sourceCodeInfoBuilder_ == null)
          this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
        else
          this.sourceCodeInfoBuilder_.clear();
        this.bitField0_ &= -257;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.FileDescriptorProto.getDescriptor();
      }

      public DescriptorProtos.FileDescriptorProto getDefaultInstanceForType()
      {
        return DescriptorProtos.FileDescriptorProto.getDefaultInstance();
      }

      public DescriptorProtos.FileDescriptorProto build()
      {
        DescriptorProtos.FileDescriptorProto localFileDescriptorProto = buildPartial();
        if (!localFileDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localFileDescriptorProto);
        return localFileDescriptorProto;
      }

      private DescriptorProtos.FileDescriptorProto buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.FileDescriptorProto localFileDescriptorProto = buildPartial();
        if (!localFileDescriptorProto.isInitialized())
          throw newUninitializedMessageException(localFileDescriptorProto).asInvalidProtocolBufferException();
        return localFileDescriptorProto;
      }

      public DescriptorProtos.FileDescriptorProto buildPartial()
      {
        DescriptorProtos.FileDescriptorProto localFileDescriptorProto = new DescriptorProtos.FileDescriptorProto(this, null);
        int i = this.bitField0_;
        int j = 0;
        if ((i & 0x1) == 1)
          j |= 1;
        localFileDescriptorProto.name_ = this.name_;
        if ((i & 0x2) == 2)
          j |= 2;
        localFileDescriptorProto.package_ = this.package_;
        if ((this.bitField0_ & 0x4) == 4)
        {
          this.dependency_ = new UnmodifiableLazyStringList(this.dependency_);
          this.bitField0_ &= -5;
        }
        localFileDescriptorProto.dependency_ = this.dependency_;
        if (this.messageTypeBuilder_ == null)
        {
          if ((this.bitField0_ & 0x8) == 8)
          {
            this.messageType_ = Collections.unmodifiableList(this.messageType_);
            this.bitField0_ &= -9;
          }
          localFileDescriptorProto.messageType_ = this.messageType_;
        }
        else
        {
          localFileDescriptorProto.messageType_ = this.messageTypeBuilder_.build();
        }
        if (this.enumTypeBuilder_ == null)
        {
          if ((this.bitField0_ & 0x10) == 16)
          {
            this.enumType_ = Collections.unmodifiableList(this.enumType_);
            this.bitField0_ &= -17;
          }
          localFileDescriptorProto.enumType_ = this.enumType_;
        }
        else
        {
          localFileDescriptorProto.enumType_ = this.enumTypeBuilder_.build();
        }
        if (this.serviceBuilder_ == null)
        {
          if ((this.bitField0_ & 0x20) == 32)
          {
            this.service_ = Collections.unmodifiableList(this.service_);
            this.bitField0_ &= -33;
          }
          localFileDescriptorProto.service_ = this.service_;
        }
        else
        {
          localFileDescriptorProto.service_ = this.serviceBuilder_.build();
        }
        if (this.extensionBuilder_ == null)
        {
          if ((this.bitField0_ & 0x40) == 64)
          {
            this.extension_ = Collections.unmodifiableList(this.extension_);
            this.bitField0_ &= -65;
          }
          localFileDescriptorProto.extension_ = this.extension_;
        }
        else
        {
          localFileDescriptorProto.extension_ = this.extensionBuilder_.build();
        }
        if ((i & 0x80) == 128)
          j |= 4;
        if (this.optionsBuilder_ == null)
          localFileDescriptorProto.options_ = this.options_;
        else
          localFileDescriptorProto.options_ = ((DescriptorProtos.FileOptions)this.optionsBuilder_.build());
        if ((i & 0x100) == 256)
          j |= 8;
        if (this.sourceCodeInfoBuilder_ == null)
          localFileDescriptorProto.sourceCodeInfo_ = this.sourceCodeInfo_;
        else
          localFileDescriptorProto.sourceCodeInfo_ = ((DescriptorProtos.SourceCodeInfo)this.sourceCodeInfoBuilder_.build());
        localFileDescriptorProto.bitField0_ = j;
        onBuilt();
        return localFileDescriptorProto;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.FileDescriptorProto))
          return mergeFrom((DescriptorProtos.FileDescriptorProto)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.FileDescriptorProto paramFileDescriptorProto)
      {
        if (paramFileDescriptorProto == DescriptorProtos.FileDescriptorProto.getDefaultInstance())
          return this;
        if (paramFileDescriptorProto.hasName())
          setName(paramFileDescriptorProto.getName());
        if (paramFileDescriptorProto.hasPackage())
          setPackage(paramFileDescriptorProto.getPackage());
        if (!paramFileDescriptorProto.dependency_.isEmpty())
        {
          if (this.dependency_.isEmpty())
          {
            this.dependency_ = paramFileDescriptorProto.dependency_;
            this.bitField0_ &= -5;
          }
          else
          {
            ensureDependencyIsMutable();
            this.dependency_.addAll(paramFileDescriptorProto.dependency_);
          }
          onChanged();
        }
        if (this.messageTypeBuilder_ == null)
        {
          if (!paramFileDescriptorProto.messageType_.isEmpty())
          {
            if (this.messageType_.isEmpty())
            {
              this.messageType_ = paramFileDescriptorProto.messageType_;
              this.bitField0_ &= -9;
            }
            else
            {
              ensureMessageTypeIsMutable();
              this.messageType_.addAll(paramFileDescriptorProto.messageType_);
            }
            onChanged();
          }
        }
        else if (!paramFileDescriptorProto.messageType_.isEmpty())
          if (this.messageTypeBuilder_.isEmpty())
          {
            this.messageTypeBuilder_.dispose();
            this.messageTypeBuilder_ = null;
            this.messageType_ = paramFileDescriptorProto.messageType_;
            this.bitField0_ &= -9;
            this.messageTypeBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getMessageTypeFieldBuilder() : null);
          }
          else
          {
            this.messageTypeBuilder_.addAllMessages(paramFileDescriptorProto.messageType_);
          }
        if (this.enumTypeBuilder_ == null)
        {
          if (!paramFileDescriptorProto.enumType_.isEmpty())
          {
            if (this.enumType_.isEmpty())
            {
              this.enumType_ = paramFileDescriptorProto.enumType_;
              this.bitField0_ &= -17;
            }
            else
            {
              ensureEnumTypeIsMutable();
              this.enumType_.addAll(paramFileDescriptorProto.enumType_);
            }
            onChanged();
          }
        }
        else if (!paramFileDescriptorProto.enumType_.isEmpty())
          if (this.enumTypeBuilder_.isEmpty())
          {
            this.enumTypeBuilder_.dispose();
            this.enumTypeBuilder_ = null;
            this.enumType_ = paramFileDescriptorProto.enumType_;
            this.bitField0_ &= -17;
            this.enumTypeBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null);
          }
          else
          {
            this.enumTypeBuilder_.addAllMessages(paramFileDescriptorProto.enumType_);
          }
        if (this.serviceBuilder_ == null)
        {
          if (!paramFileDescriptorProto.service_.isEmpty())
          {
            if (this.service_.isEmpty())
            {
              this.service_ = paramFileDescriptorProto.service_;
              this.bitField0_ &= -33;
            }
            else
            {
              ensureServiceIsMutable();
              this.service_.addAll(paramFileDescriptorProto.service_);
            }
            onChanged();
          }
        }
        else if (!paramFileDescriptorProto.service_.isEmpty())
          if (this.serviceBuilder_.isEmpty())
          {
            this.serviceBuilder_.dispose();
            this.serviceBuilder_ = null;
            this.service_ = paramFileDescriptorProto.service_;
            this.bitField0_ &= -33;
            this.serviceBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getServiceFieldBuilder() : null);
          }
          else
          {
            this.serviceBuilder_.addAllMessages(paramFileDescriptorProto.service_);
          }
        if (this.extensionBuilder_ == null)
        {
          if (!paramFileDescriptorProto.extension_.isEmpty())
          {
            if (this.extension_.isEmpty())
            {
              this.extension_ = paramFileDescriptorProto.extension_;
              this.bitField0_ &= -65;
            }
            else
            {
              ensureExtensionIsMutable();
              this.extension_.addAll(paramFileDescriptorProto.extension_);
            }
            onChanged();
          }
        }
        else if (!paramFileDescriptorProto.extension_.isEmpty())
          if (this.extensionBuilder_.isEmpty())
          {
            this.extensionBuilder_.dispose();
            this.extensionBuilder_ = null;
            this.extension_ = paramFileDescriptorProto.extension_;
            this.bitField0_ &= -65;
            this.extensionBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null);
          }
          else
          {
            this.extensionBuilder_.addAllMessages(paramFileDescriptorProto.extension_);
          }
        if (paramFileDescriptorProto.hasOptions())
          mergeOptions(paramFileDescriptorProto.getOptions());
        if (paramFileDescriptorProto.hasSourceCodeInfo())
          mergeSourceCodeInfo(paramFileDescriptorProto.getSourceCodeInfo());
        mergeUnknownFields(paramFileDescriptorProto.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getMessageTypeCount(); i++)
          if (!getMessageType(i).isInitialized())
            return false;
        for (i = 0; i < getEnumTypeCount(); i++)
          if (!getEnumType(i).isInitialized())
            return false;
        for (i = 0; i < getServiceCount(); i++)
          if (!getService(i).isInitialized())
            return false;
        for (i = 0; i < getExtensionCount(); i++)
          if (!getExtension(i).isInitialized())
            return false;
        return (!hasOptions()) || (getOptions().isInitialized());
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          Object localObject;
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            this.bitField0_ |= 1;
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ |= 2;
            this.package_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            ensureDependencyIsMutable();
            this.dependency_.add(paramCodedInputStream.readBytes());
            break;
          case 34:
            localObject = DescriptorProtos.DescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addMessageType(((DescriptorProtos.DescriptorProto.Builder)localObject).buildPartial());
            break;
          case 42:
            localObject = DescriptorProtos.EnumDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addEnumType(((DescriptorProtos.EnumDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 50:
            localObject = DescriptorProtos.ServiceDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addService(((DescriptorProtos.ServiceDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 58:
            localObject = DescriptorProtos.FieldDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addExtension(((DescriptorProtos.FieldDescriptorProto.Builder)localObject).buildPartial());
            break;
          case 66:
            localObject = DescriptorProtos.FileOptions.newBuilder();
            if (hasOptions())
              ((DescriptorProtos.FileOptions.Builder)localObject).mergeFrom(getOptions());
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            setOptions(((DescriptorProtos.FileOptions.Builder)localObject).buildPartial());
            break;
          case 74:
            localObject = DescriptorProtos.SourceCodeInfo.newBuilder();
            if (hasSourceCodeInfo())
              ((DescriptorProtos.SourceCodeInfo.Builder)localObject).mergeFrom(getSourceCodeInfo());
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            setSourceCodeInfo(((DescriptorProtos.SourceCodeInfo.Builder)localObject).buildPartial());
          }
        }
      }

      public boolean hasName()
      {
        return (this.bitField0_ & 0x1) == 1;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 1;
        this.name_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ &= -2;
        this.name_ = DescriptorProtos.FileDescriptorProto.getDefaultInstance().getName();
        onChanged();
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ |= 1;
        this.name_ = paramByteString;
        onChanged();
      }

      public boolean hasPackage()
      {
        return (this.bitField0_ & 0x2) == 2;
      }

      public String getPackage()
      {
        Object localObject = this.package_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.package_ = str;
          return str;
        }
        return (String)localObject;
      }

      public Builder setPackage(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ |= 2;
        this.package_ = paramString;
        onChanged();
        return this;
      }

      public Builder clearPackage()
      {
        this.bitField0_ &= -3;
        this.package_ = DescriptorProtos.FileDescriptorProto.getDefaultInstance().getPackage();
        onChanged();
        return this;
      }

      void setPackage(ByteString paramByteString)
      {
        this.bitField0_ |= 2;
        this.package_ = paramByteString;
        onChanged();
      }

      private void ensureDependencyIsMutable()
      {
        if ((this.bitField0_ & 0x4) != 4)
        {
          this.dependency_ = new LazyStringArrayList(this.dependency_);
          this.bitField0_ |= 4;
        }
      }

      public List getDependencyList()
      {
        return Collections.unmodifiableList(this.dependency_);
      }

      public int getDependencyCount()
      {
        return this.dependency_.size();
      }

      public String getDependency(int paramInt)
      {
        return (String)this.dependency_.get(paramInt);
      }

      public Builder setDependency(int paramInt, String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureDependencyIsMutable();
        this.dependency_.set(paramInt, paramString);
        onChanged();
        return this;
      }

      public Builder addDependency(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureDependencyIsMutable();
        this.dependency_.add(paramString);
        onChanged();
        return this;
      }

      public Builder addAllDependency(Iterable paramIterable)
      {
        ensureDependencyIsMutable();
        GeneratedMessage.Builder.addAll(paramIterable, this.dependency_);
        onChanged();
        return this;
      }

      public Builder clearDependency()
      {
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= -5;
        onChanged();
        return this;
      }

      void addDependency(ByteString paramByteString)
      {
        ensureDependencyIsMutable();
        this.dependency_.add(paramByteString);
        onChanged();
      }

      private void ensureMessageTypeIsMutable()
      {
        if ((this.bitField0_ & 0x8) != 8)
        {
          this.messageType_ = new ArrayList(this.messageType_);
          this.bitField0_ |= 8;
        }
      }

      public List getMessageTypeList()
      {
        if (this.messageTypeBuilder_ == null)
          return Collections.unmodifiableList(this.messageType_);
        return this.messageTypeBuilder_.getMessageList();
      }

      public int getMessageTypeCount()
      {
        if (this.messageTypeBuilder_ == null)
          return this.messageType_.size();
        return this.messageTypeBuilder_.getCount();
      }

      public DescriptorProtos.DescriptorProto getMessageType(int paramInt)
      {
        if (this.messageTypeBuilder_ == null)
          return (DescriptorProtos.DescriptorProto)this.messageType_.get(paramInt);
        return (DescriptorProtos.DescriptorProto)this.messageTypeBuilder_.getMessage(paramInt);
      }

      public Builder setMessageType(int paramInt, DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (this.messageTypeBuilder_ == null)
        {
          if (paramDescriptorProto == null)
            throw new NullPointerException();
          ensureMessageTypeIsMutable();
          this.messageType_.set(paramInt, paramDescriptorProto);
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.setMessage(paramInt, paramDescriptorProto);
        }
        return this;
      }

      public Builder setMessageType(int paramInt, DescriptorProtos.DescriptorProto.Builder paramBuilder)
      {
        if (this.messageTypeBuilder_ == null)
        {
          ensureMessageTypeIsMutable();
          this.messageType_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addMessageType(DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (this.messageTypeBuilder_ == null)
        {
          if (paramDescriptorProto == null)
            throw new NullPointerException();
          ensureMessageTypeIsMutable();
          this.messageType_.add(paramDescriptorProto);
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.addMessage(paramDescriptorProto);
        }
        return this;
      }

      public Builder addMessageType(int paramInt, DescriptorProtos.DescriptorProto paramDescriptorProto)
      {
        if (this.messageTypeBuilder_ == null)
        {
          if (paramDescriptorProto == null)
            throw new NullPointerException();
          ensureMessageTypeIsMutable();
          this.messageType_.add(paramInt, paramDescriptorProto);
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.addMessage(paramInt, paramDescriptorProto);
        }
        return this;
      }

      public Builder addMessageType(DescriptorProtos.DescriptorProto.Builder paramBuilder)
      {
        if (this.messageTypeBuilder_ == null)
        {
          ensureMessageTypeIsMutable();
          this.messageType_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addMessageType(int paramInt, DescriptorProtos.DescriptorProto.Builder paramBuilder)
      {
        if (this.messageTypeBuilder_ == null)
        {
          ensureMessageTypeIsMutable();
          this.messageType_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllMessageType(Iterable paramIterable)
      {
        if (this.messageTypeBuilder_ == null)
        {
          ensureMessageTypeIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.messageType_);
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearMessageType()
      {
        if (this.messageTypeBuilder_ == null)
        {
          this.messageType_ = Collections.emptyList();
          this.bitField0_ &= -9;
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.clear();
        }
        return this;
      }

      public Builder removeMessageType(int paramInt)
      {
        if (this.messageTypeBuilder_ == null)
        {
          ensureMessageTypeIsMutable();
          this.messageType_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.messageTypeBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.DescriptorProto.Builder getMessageTypeBuilder(int paramInt)
      {
        return (DescriptorProtos.DescriptorProto.Builder)getMessageTypeFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.DescriptorProtoOrBuilder getMessageTypeOrBuilder(int paramInt)
      {
        if (this.messageTypeBuilder_ == null)
          return (DescriptorProtos.DescriptorProtoOrBuilder)this.messageType_.get(paramInt);
        return (DescriptorProtos.DescriptorProtoOrBuilder)this.messageTypeBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getMessageTypeOrBuilderList()
      {
        if (this.messageTypeBuilder_ != null)
          return this.messageTypeBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.messageType_);
      }

      public DescriptorProtos.DescriptorProto.Builder addMessageTypeBuilder()
      {
        return (DescriptorProtos.DescriptorProto.Builder)getMessageTypeFieldBuilder().addBuilder(DescriptorProtos.DescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.DescriptorProto.Builder addMessageTypeBuilder(int paramInt)
      {
        return (DescriptorProtos.DescriptorProto.Builder)getMessageTypeFieldBuilder().addBuilder(paramInt, DescriptorProtos.DescriptorProto.getDefaultInstance());
      }

      public List getMessageTypeBuilderList()
      {
        return getMessageTypeFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getMessageTypeFieldBuilder()
      {
        if (this.messageTypeBuilder_ == null)
        {
          this.messageTypeBuilder_ = new RepeatedFieldBuilder(this.messageType_, (this.bitField0_ & 0x8) == 8, getParentForChildren(), isClean());
          this.messageType_ = null;
        }
        return this.messageTypeBuilder_;
      }

      private void ensureEnumTypeIsMutable()
      {
        if ((this.bitField0_ & 0x10) != 16)
        {
          this.enumType_ = new ArrayList(this.enumType_);
          this.bitField0_ |= 16;
        }
      }

      public List getEnumTypeList()
      {
        if (this.enumTypeBuilder_ == null)
          return Collections.unmodifiableList(this.enumType_);
        return this.enumTypeBuilder_.getMessageList();
      }

      public int getEnumTypeCount()
      {
        if (this.enumTypeBuilder_ == null)
          return this.enumType_.size();
        return this.enumTypeBuilder_.getCount();
      }

      public DescriptorProtos.EnumDescriptorProto getEnumType(int paramInt)
      {
        if (this.enumTypeBuilder_ == null)
          return (DescriptorProtos.EnumDescriptorProto)this.enumType_.get(paramInt);
        return (DescriptorProtos.EnumDescriptorProto)this.enumTypeBuilder_.getMessage(paramInt);
      }

      public Builder setEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (this.enumTypeBuilder_ == null)
        {
          if (paramEnumDescriptorProto == null)
            throw new NullPointerException();
          ensureEnumTypeIsMutable();
          this.enumType_.set(paramInt, paramEnumDescriptorProto);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.setMessage(paramInt, paramEnumDescriptorProto);
        }
        return this;
      }

      public Builder setEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto.Builder paramBuilder)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addEnumType(DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (this.enumTypeBuilder_ == null)
        {
          if (paramEnumDescriptorProto == null)
            throw new NullPointerException();
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramEnumDescriptorProto);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramEnumDescriptorProto);
        }
        return this;
      }

      public Builder addEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto paramEnumDescriptorProto)
      {
        if (this.enumTypeBuilder_ == null)
        {
          if (paramEnumDescriptorProto == null)
            throw new NullPointerException();
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramInt, paramEnumDescriptorProto);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramInt, paramEnumDescriptorProto);
        }
        return this;
      }

      public Builder addEnumType(DescriptorProtos.EnumDescriptorProto.Builder paramBuilder)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addEnumType(int paramInt, DescriptorProtos.EnumDescriptorProto.Builder paramBuilder)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllEnumType(Iterable paramIterable)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.enumType_);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearEnumType()
      {
        if (this.enumTypeBuilder_ == null)
        {
          this.enumType_ = Collections.emptyList();
          this.bitField0_ &= -17;
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.clear();
        }
        return this;
      }

      public Builder removeEnumType(int paramInt)
      {
        if (this.enumTypeBuilder_ == null)
        {
          ensureEnumTypeIsMutable();
          this.enumType_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.enumTypeBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.EnumDescriptorProto.Builder getEnumTypeBuilder(int paramInt)
      {
        return (DescriptorProtos.EnumDescriptorProto.Builder)getEnumTypeFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int paramInt)
      {
        if (this.enumTypeBuilder_ == null)
          return (DescriptorProtos.EnumDescriptorProtoOrBuilder)this.enumType_.get(paramInt);
        return (DescriptorProtos.EnumDescriptorProtoOrBuilder)this.enumTypeBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getEnumTypeOrBuilderList()
      {
        if (this.enumTypeBuilder_ != null)
          return this.enumTypeBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.enumType_);
      }

      public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder()
      {
        return (DescriptorProtos.EnumDescriptorProto.Builder)getEnumTypeFieldBuilder().addBuilder(DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder(int paramInt)
      {
        return (DescriptorProtos.EnumDescriptorProto.Builder)getEnumTypeFieldBuilder().addBuilder(paramInt, DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
      }

      public List getEnumTypeBuilderList()
      {
        return getEnumTypeFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getEnumTypeFieldBuilder()
      {
        if (this.enumTypeBuilder_ == null)
        {
          this.enumTypeBuilder_ = new RepeatedFieldBuilder(this.enumType_, (this.bitField0_ & 0x10) == 16, getParentForChildren(), isClean());
          this.enumType_ = null;
        }
        return this.enumTypeBuilder_;
      }

      private void ensureServiceIsMutable()
      {
        if ((this.bitField0_ & 0x20) != 32)
        {
          this.service_ = new ArrayList(this.service_);
          this.bitField0_ |= 32;
        }
      }

      public List getServiceList()
      {
        if (this.serviceBuilder_ == null)
          return Collections.unmodifiableList(this.service_);
        return this.serviceBuilder_.getMessageList();
      }

      public int getServiceCount()
      {
        if (this.serviceBuilder_ == null)
          return this.service_.size();
        return this.serviceBuilder_.getCount();
      }

      public DescriptorProtos.ServiceDescriptorProto getService(int paramInt)
      {
        if (this.serviceBuilder_ == null)
          return (DescriptorProtos.ServiceDescriptorProto)this.service_.get(paramInt);
        return (DescriptorProtos.ServiceDescriptorProto)this.serviceBuilder_.getMessage(paramInt);
      }

      public Builder setService(int paramInt, DescriptorProtos.ServiceDescriptorProto paramServiceDescriptorProto)
      {
        if (this.serviceBuilder_ == null)
        {
          if (paramServiceDescriptorProto == null)
            throw new NullPointerException();
          ensureServiceIsMutable();
          this.service_.set(paramInt, paramServiceDescriptorProto);
          onChanged();
        }
        else
        {
          this.serviceBuilder_.setMessage(paramInt, paramServiceDescriptorProto);
        }
        return this;
      }

      public Builder setService(int paramInt, DescriptorProtos.ServiceDescriptorProto.Builder paramBuilder)
      {
        if (this.serviceBuilder_ == null)
        {
          ensureServiceIsMutable();
          this.service_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.serviceBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addService(DescriptorProtos.ServiceDescriptorProto paramServiceDescriptorProto)
      {
        if (this.serviceBuilder_ == null)
        {
          if (paramServiceDescriptorProto == null)
            throw new NullPointerException();
          ensureServiceIsMutable();
          this.service_.add(paramServiceDescriptorProto);
          onChanged();
        }
        else
        {
          this.serviceBuilder_.addMessage(paramServiceDescriptorProto);
        }
        return this;
      }

      public Builder addService(int paramInt, DescriptorProtos.ServiceDescriptorProto paramServiceDescriptorProto)
      {
        if (this.serviceBuilder_ == null)
        {
          if (paramServiceDescriptorProto == null)
            throw new NullPointerException();
          ensureServiceIsMutable();
          this.service_.add(paramInt, paramServiceDescriptorProto);
          onChanged();
        }
        else
        {
          this.serviceBuilder_.addMessage(paramInt, paramServiceDescriptorProto);
        }
        return this;
      }

      public Builder addService(DescriptorProtos.ServiceDescriptorProto.Builder paramBuilder)
      {
        if (this.serviceBuilder_ == null)
        {
          ensureServiceIsMutable();
          this.service_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.serviceBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addService(int paramInt, DescriptorProtos.ServiceDescriptorProto.Builder paramBuilder)
      {
        if (this.serviceBuilder_ == null)
        {
          ensureServiceIsMutable();
          this.service_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.serviceBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllService(Iterable paramIterable)
      {
        if (this.serviceBuilder_ == null)
        {
          ensureServiceIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.service_);
          onChanged();
        }
        else
        {
          this.serviceBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearService()
      {
        if (this.serviceBuilder_ == null)
        {
          this.service_ = Collections.emptyList();
          this.bitField0_ &= -33;
          onChanged();
        }
        else
        {
          this.serviceBuilder_.clear();
        }
        return this;
      }

      public Builder removeService(int paramInt)
      {
        if (this.serviceBuilder_ == null)
        {
          ensureServiceIsMutable();
          this.service_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.serviceBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.ServiceDescriptorProto.Builder getServiceBuilder(int paramInt)
      {
        return (DescriptorProtos.ServiceDescriptorProto.Builder)getServiceFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int paramInt)
      {
        if (this.serviceBuilder_ == null)
          return (DescriptorProtos.ServiceDescriptorProtoOrBuilder)this.service_.get(paramInt);
        return (DescriptorProtos.ServiceDescriptorProtoOrBuilder)this.serviceBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getServiceOrBuilderList()
      {
        if (this.serviceBuilder_ != null)
          return this.serviceBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.service_);
      }

      public DescriptorProtos.ServiceDescriptorProto.Builder addServiceBuilder()
      {
        return (DescriptorProtos.ServiceDescriptorProto.Builder)getServiceFieldBuilder().addBuilder(DescriptorProtos.ServiceDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.ServiceDescriptorProto.Builder addServiceBuilder(int paramInt)
      {
        return (DescriptorProtos.ServiceDescriptorProto.Builder)getServiceFieldBuilder().addBuilder(paramInt, DescriptorProtos.ServiceDescriptorProto.getDefaultInstance());
      }

      public List getServiceBuilderList()
      {
        return getServiceFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getServiceFieldBuilder()
      {
        if (this.serviceBuilder_ == null)
        {
          this.serviceBuilder_ = new RepeatedFieldBuilder(this.service_, (this.bitField0_ & 0x20) == 32, getParentForChildren(), isClean());
          this.service_ = null;
        }
        return this.serviceBuilder_;
      }

      private void ensureExtensionIsMutable()
      {
        if ((this.bitField0_ & 0x40) != 64)
        {
          this.extension_ = new ArrayList(this.extension_);
          this.bitField0_ |= 64;
        }
      }

      public List getExtensionList()
      {
        if (this.extensionBuilder_ == null)
          return Collections.unmodifiableList(this.extension_);
        return this.extensionBuilder_.getMessageList();
      }

      public int getExtensionCount()
      {
        if (this.extensionBuilder_ == null)
          return this.extension_.size();
        return this.extensionBuilder_.getCount();
      }

      public DescriptorProtos.FieldDescriptorProto getExtension(int paramInt)
      {
        if (this.extensionBuilder_ == null)
          return (DescriptorProtos.FieldDescriptorProto)this.extension_.get(paramInt);
        return (DescriptorProtos.FieldDescriptorProto)this.extensionBuilder_.getMessage(paramInt);
      }

      public Builder setExtension(int paramInt, DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.extensionBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureExtensionIsMutable();
          this.extension_.set(paramInt, paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.setMessage(paramInt, paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder setExtension(int paramInt, DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addExtension(DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.extensionBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureExtensionIsMutable();
          this.extension_.add(paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder addExtension(int paramInt, DescriptorProtos.FieldDescriptorProto paramFieldDescriptorProto)
      {
        if (this.extensionBuilder_ == null)
        {
          if (paramFieldDescriptorProto == null)
            throw new NullPointerException();
          ensureExtensionIsMutable();
          this.extension_.add(paramInt, paramFieldDescriptorProto);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramInt, paramFieldDescriptorProto);
        }
        return this;
      }

      public Builder addExtension(DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addExtension(int paramInt, DescriptorProtos.FieldDescriptorProto.Builder paramBuilder)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllExtension(Iterable paramIterable)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.extension_);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearExtension()
      {
        if (this.extensionBuilder_ == null)
        {
          this.extension_ = Collections.emptyList();
          this.bitField0_ &= -65;
          onChanged();
        }
        else
        {
          this.extensionBuilder_.clear();
        }
        return this;
      }

      public Builder removeExtension(int paramInt)
      {
        if (this.extensionBuilder_ == null)
        {
          ensureExtensionIsMutable();
          this.extension_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.extensionBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.FieldDescriptorProto.Builder getExtensionBuilder(int paramInt)
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getExtensionFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int paramInt)
      {
        if (this.extensionBuilder_ == null)
          return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.extension_.get(paramInt);
        return (DescriptorProtos.FieldDescriptorProtoOrBuilder)this.extensionBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getExtensionOrBuilderList()
      {
        if (this.extensionBuilder_ != null)
          return this.extensionBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.extension_);
      }

      public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder()
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getExtensionFieldBuilder().addBuilder(DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder(int paramInt)
      {
        return (DescriptorProtos.FieldDescriptorProto.Builder)getExtensionFieldBuilder().addBuilder(paramInt, DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
      }

      public List getExtensionBuilderList()
      {
        return getExtensionFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getExtensionFieldBuilder()
      {
        if (this.extensionBuilder_ == null)
        {
          this.extensionBuilder_ = new RepeatedFieldBuilder(this.extension_, (this.bitField0_ & 0x40) == 64, getParentForChildren(), isClean());
          this.extension_ = null;
        }
        return this.extensionBuilder_;
      }

      public boolean hasOptions()
      {
        return (this.bitField0_ & 0x80) == 128;
      }

      public DescriptorProtos.FileOptions getOptions()
      {
        if (this.optionsBuilder_ == null)
          return this.options_;
        return (DescriptorProtos.FileOptions)this.optionsBuilder_.getMessage();
      }

      public Builder setOptions(DescriptorProtos.FileOptions paramFileOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (paramFileOptions == null)
            throw new NullPointerException();
          this.options_ = paramFileOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramFileOptions);
        }
        this.bitField0_ |= 128;
        return this;
      }

      public Builder setOptions(DescriptorProtos.FileOptions.Builder paramBuilder)
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 128;
        return this;
      }

      public Builder mergeOptions(DescriptorProtos.FileOptions paramFileOptions)
      {
        if (this.optionsBuilder_ == null)
        {
          if (((this.bitField0_ & 0x80) == 128) && (this.options_ != DescriptorProtos.FileOptions.getDefaultInstance()))
            this.options_ = DescriptorProtos.FileOptions.newBuilder(this.options_).mergeFrom(paramFileOptions).buildPartial();
          else
            this.options_ = paramFileOptions;
          onChanged();
        }
        else
        {
          this.optionsBuilder_.mergeFrom(paramFileOptions);
        }
        this.bitField0_ |= 128;
        return this;
      }

      public Builder clearOptions()
      {
        if (this.optionsBuilder_ == null)
        {
          this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.optionsBuilder_.clear();
        }
        this.bitField0_ &= -129;
        return this;
      }

      public DescriptorProtos.FileOptions.Builder getOptionsBuilder()
      {
        this.bitField0_ |= 128;
        onChanged();
        return (DescriptorProtos.FileOptions.Builder)getOptionsFieldBuilder().getBuilder();
      }

      public DescriptorProtos.FileOptionsOrBuilder getOptionsOrBuilder()
      {
        if (this.optionsBuilder_ != null)
          return (DescriptorProtos.FileOptionsOrBuilder)this.optionsBuilder_.getMessageOrBuilder();
        return this.options_;
      }

      private SingleFieldBuilder getOptionsFieldBuilder()
      {
        if (this.optionsBuilder_ == null)
        {
          this.optionsBuilder_ = new SingleFieldBuilder(this.options_, getParentForChildren(), isClean());
          this.options_ = null;
        }
        return this.optionsBuilder_;
      }

      public boolean hasSourceCodeInfo()
      {
        return (this.bitField0_ & 0x100) == 256;
      }

      public DescriptorProtos.SourceCodeInfo getSourceCodeInfo()
      {
        if (this.sourceCodeInfoBuilder_ == null)
          return this.sourceCodeInfo_;
        return (DescriptorProtos.SourceCodeInfo)this.sourceCodeInfoBuilder_.getMessage();
      }

      public Builder setSourceCodeInfo(DescriptorProtos.SourceCodeInfo paramSourceCodeInfo)
      {
        if (this.sourceCodeInfoBuilder_ == null)
        {
          if (paramSourceCodeInfo == null)
            throw new NullPointerException();
          this.sourceCodeInfo_ = paramSourceCodeInfo;
          onChanged();
        }
        else
        {
          this.sourceCodeInfoBuilder_.setMessage(paramSourceCodeInfo);
        }
        this.bitField0_ |= 256;
        return this;
      }

      public Builder setSourceCodeInfo(DescriptorProtos.SourceCodeInfo.Builder paramBuilder)
      {
        if (this.sourceCodeInfoBuilder_ == null)
        {
          this.sourceCodeInfo_ = paramBuilder.build();
          onChanged();
        }
        else
        {
          this.sourceCodeInfoBuilder_.setMessage(paramBuilder.build());
        }
        this.bitField0_ |= 256;
        return this;
      }

      public Builder mergeSourceCodeInfo(DescriptorProtos.SourceCodeInfo paramSourceCodeInfo)
      {
        if (this.sourceCodeInfoBuilder_ == null)
        {
          if (((this.bitField0_ & 0x100) == 256) && (this.sourceCodeInfo_ != DescriptorProtos.SourceCodeInfo.getDefaultInstance()))
            this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.newBuilder(this.sourceCodeInfo_).mergeFrom(paramSourceCodeInfo).buildPartial();
          else
            this.sourceCodeInfo_ = paramSourceCodeInfo;
          onChanged();
        }
        else
        {
          this.sourceCodeInfoBuilder_.mergeFrom(paramSourceCodeInfo);
        }
        this.bitField0_ |= 256;
        return this;
      }

      public Builder clearSourceCodeInfo()
      {
        if (this.sourceCodeInfoBuilder_ == null)
        {
          this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
          onChanged();
        }
        else
        {
          this.sourceCodeInfoBuilder_.clear();
        }
        this.bitField0_ &= -257;
        return this;
      }

      public DescriptorProtos.SourceCodeInfo.Builder getSourceCodeInfoBuilder()
      {
        this.bitField0_ |= 256;
        onChanged();
        return (DescriptorProtos.SourceCodeInfo.Builder)getSourceCodeInfoFieldBuilder().getBuilder();
      }

      public DescriptorProtos.SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder()
      {
        if (this.sourceCodeInfoBuilder_ != null)
          return (DescriptorProtos.SourceCodeInfoOrBuilder)this.sourceCodeInfoBuilder_.getMessageOrBuilder();
        return this.sourceCodeInfo_;
      }

      private SingleFieldBuilder getSourceCodeInfoFieldBuilder()
      {
        if (this.sourceCodeInfoBuilder_ == null)
        {
          this.sourceCodeInfoBuilder_ = new SingleFieldBuilder(this.sourceCodeInfo_, getParentForChildren(), isClean());
          this.sourceCodeInfo_ = null;
        }
        return this.sourceCodeInfoBuilder_;
      }
    }
  }

  public static abstract interface FileDescriptorProtoOrBuilder extends MessageOrBuilder
  {
    public abstract boolean hasName();

    public abstract String getName();

    public abstract boolean hasPackage();

    public abstract String getPackage();

    public abstract List getDependencyList();

    public abstract int getDependencyCount();

    public abstract String getDependency(int paramInt);

    public abstract List getMessageTypeList();

    public abstract DescriptorProtos.DescriptorProto getMessageType(int paramInt);

    public abstract int getMessageTypeCount();

    public abstract List getMessageTypeOrBuilderList();

    public abstract DescriptorProtos.DescriptorProtoOrBuilder getMessageTypeOrBuilder(int paramInt);

    public abstract List getEnumTypeList();

    public abstract DescriptorProtos.EnumDescriptorProto getEnumType(int paramInt);

    public abstract int getEnumTypeCount();

    public abstract List getEnumTypeOrBuilderList();

    public abstract DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int paramInt);

    public abstract List getServiceList();

    public abstract DescriptorProtos.ServiceDescriptorProto getService(int paramInt);

    public abstract int getServiceCount();

    public abstract List getServiceOrBuilderList();

    public abstract DescriptorProtos.ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int paramInt);

    public abstract List getExtensionList();

    public abstract DescriptorProtos.FieldDescriptorProto getExtension(int paramInt);

    public abstract int getExtensionCount();

    public abstract List getExtensionOrBuilderList();

    public abstract DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int paramInt);

    public abstract boolean hasOptions();

    public abstract DescriptorProtos.FileOptions getOptions();

    public abstract DescriptorProtos.FileOptionsOrBuilder getOptionsOrBuilder();

    public abstract boolean hasSourceCodeInfo();

    public abstract DescriptorProtos.SourceCodeInfo getSourceCodeInfo();

    public abstract DescriptorProtos.SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder();
  }

  public static final class FileDescriptorSet extends GeneratedMessage
    implements DescriptorProtos.FileDescriptorSetOrBuilder
  {
    private static final FileDescriptorSet defaultInstance = new FileDescriptorSet(true);
    public static final int FILE_FIELD_NUMBER = 1;
    private List file_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private static final long serialVersionUID = 0L;

    private FileDescriptorSet(Builder paramBuilder)
    {
      super();
    }

    private FileDescriptorSet(boolean paramBoolean)
    {
    }

    public static FileDescriptorSet getDefaultInstance()
    {
      return defaultInstance;
    }

    public FileDescriptorSet getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public static final Descriptors.Descriptor getDescriptor()
    {
      return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
    }

    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
    {
      return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
    }

    public List getFileList()
    {
      return this.file_;
    }

    public List getFileOrBuilderList()
    {
      return this.file_;
    }

    public int getFileCount()
    {
      return this.file_.size();
    }

    public DescriptorProtos.FileDescriptorProto getFile(int paramInt)
    {
      return (DescriptorProtos.FileDescriptorProto)this.file_.get(paramInt);
    }

    public DescriptorProtos.FileDescriptorProtoOrBuilder getFileOrBuilder(int paramInt)
    {
      return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.file_.get(paramInt);
    }

    private void initFields()
    {
      this.file_ = Collections.emptyList();
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getFileCount(); j++)
        if (!getFile(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      for (int i = 0; i < this.file_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.file_.get(i));
      getUnknownFields().writeTo(paramCodedOutputStream);
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      i = 0;
      for (int j = 0; j < this.file_.size(); j++)
        i += CodedOutputStream.computeMessageSize(1, (MessageLite)this.file_.get(j));
      i += getUnknownFields().getSerializedSize();
      this.memoizedSerializedSize = i;
      return i;
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public static FileDescriptorSet parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FileDescriptorSet parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileDescriptorSet parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FileDescriptorSet parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileDescriptorSet parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FileDescriptorSet parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FileDescriptorSet parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FileDescriptorSet parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FileDescriptorSet parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FileDescriptorSet parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Builder newBuilder()
    {
      return Builder.access$300();
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public static Builder newBuilder(FileDescriptorSet paramFileDescriptorSet)
    {
      return newBuilder().mergeFrom(paramFileDescriptorSet);
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Builder newBuilderForType(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      Builder localBuilder = new Builder(paramBuilderParent, null);
      return localBuilder;
    }

    static
    {
      defaultInstance.initFields();
    }

    public static final class Builder extends GeneratedMessage.Builder
      implements DescriptorProtos.FileDescriptorSetOrBuilder
    {
      private int bitField0_;
      private List file_ = Collections.emptyList();
      private RepeatedFieldBuilder fileBuilder_;

      public static final Descriptors.Descriptor getDescriptor()
      {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable()
      {
        return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
      }

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessage.BuilderParent paramBuilderParent)
      {
        super();
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization()
      {
        if (GeneratedMessage.alwaysUseFieldBuilders)
          getFileFieldBuilder();
      }

      private static Builder create()
      {
        return new Builder();
      }

      public Builder clear()
      {
        super.clear();
        if (this.fileBuilder_ == null)
        {
          this.file_ = Collections.emptyList();
          this.bitField0_ &= -2;
        }
        else
        {
          this.fileBuilder_.clear();
        }
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public Descriptors.Descriptor getDescriptorForType()
      {
        return DescriptorProtos.FileDescriptorSet.getDescriptor();
      }

      public DescriptorProtos.FileDescriptorSet getDefaultInstanceForType()
      {
        return DescriptorProtos.FileDescriptorSet.getDefaultInstance();
      }

      public DescriptorProtos.FileDescriptorSet build()
      {
        DescriptorProtos.FileDescriptorSet localFileDescriptorSet = buildPartial();
        if (!localFileDescriptorSet.isInitialized())
          throw newUninitializedMessageException(localFileDescriptorSet);
        return localFileDescriptorSet;
      }

      private DescriptorProtos.FileDescriptorSet buildParsed()
        throws InvalidProtocolBufferException
      {
        DescriptorProtos.FileDescriptorSet localFileDescriptorSet = buildPartial();
        if (!localFileDescriptorSet.isInitialized())
          throw newUninitializedMessageException(localFileDescriptorSet).asInvalidProtocolBufferException();
        return localFileDescriptorSet;
      }

      public DescriptorProtos.FileDescriptorSet buildPartial()
      {
        DescriptorProtos.FileDescriptorSet localFileDescriptorSet = new DescriptorProtos.FileDescriptorSet(this, null);
        int i = this.bitField0_;
        if (this.fileBuilder_ == null)
        {
          if ((this.bitField0_ & 0x1) == 1)
          {
            this.file_ = Collections.unmodifiableList(this.file_);
            this.bitField0_ &= -2;
          }
          localFileDescriptorSet.file_ = this.file_;
        }
        else
        {
          localFileDescriptorSet.file_ = this.fileBuilder_.build();
        }
        onBuilt();
        return localFileDescriptorSet;
      }

      public Builder mergeFrom(Message paramMessage)
      {
        if ((paramMessage instanceof DescriptorProtos.FileDescriptorSet))
          return mergeFrom((DescriptorProtos.FileDescriptorSet)paramMessage);
        super.mergeFrom(paramMessage);
        return this;
      }

      public Builder mergeFrom(DescriptorProtos.FileDescriptorSet paramFileDescriptorSet)
      {
        if (paramFileDescriptorSet == DescriptorProtos.FileDescriptorSet.getDefaultInstance())
          return this;
        if (this.fileBuilder_ == null)
        {
          if (!paramFileDescriptorSet.file_.isEmpty())
          {
            if (this.file_.isEmpty())
            {
              this.file_ = paramFileDescriptorSet.file_;
              this.bitField0_ &= -2;
            }
            else
            {
              ensureFileIsMutable();
              this.file_.addAll(paramFileDescriptorSet.file_);
            }
            onChanged();
          }
        }
        else if (!paramFileDescriptorSet.file_.isEmpty())
          if (this.fileBuilder_.isEmpty())
          {
            this.fileBuilder_.dispose();
            this.fileBuilder_ = null;
            this.file_ = paramFileDescriptorSet.file_;
            this.bitField0_ &= -2;
            this.fileBuilder_ = (GeneratedMessage.alwaysUseFieldBuilders ? getFileFieldBuilder() : null);
          }
          else
          {
            this.fileBuilder_.addAllMessages(paramFileDescriptorSet.file_);
          }
        mergeUnknownFields(paramFileDescriptorSet.getUnknownFields());
        return this;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getFileCount(); i++)
          if (!getFile(i).isInitialized())
            return false;
        return true;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          case 0:
            setUnknownFields(localBuilder.build());
            onChanged();
            return this;
          default:
            if (!parseUnknownField(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, i))
            {
              setUnknownFields(localBuilder.build());
              onChanged();
              return this;
            }
            break;
          case 10:
            DescriptorProtos.FileDescriptorProto.Builder localBuilder1 = DescriptorProtos.FileDescriptorProto.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addFile(localBuilder1.buildPartial());
          }
        }
      }

      private void ensureFileIsMutable()
      {
        if ((this.bitField0_ & 0x1) != 1)
        {
          this.file_ = new ArrayList(this.file_);
          this.bitField0_ |= 1;
        }
      }

      public List getFileList()
      {
        if (this.fileBuilder_ == null)
          return Collections.unmodifiableList(this.file_);
        return this.fileBuilder_.getMessageList();
      }

      public int getFileCount()
      {
        if (this.fileBuilder_ == null)
          return this.file_.size();
        return this.fileBuilder_.getCount();
      }

      public DescriptorProtos.FileDescriptorProto getFile(int paramInt)
      {
        if (this.fileBuilder_ == null)
          return (DescriptorProtos.FileDescriptorProto)this.file_.get(paramInt);
        return (DescriptorProtos.FileDescriptorProto)this.fileBuilder_.getMessage(paramInt);
      }

      public Builder setFile(int paramInt, DescriptorProtos.FileDescriptorProto paramFileDescriptorProto)
      {
        if (this.fileBuilder_ == null)
        {
          if (paramFileDescriptorProto == null)
            throw new NullPointerException();
          ensureFileIsMutable();
          this.file_.set(paramInt, paramFileDescriptorProto);
          onChanged();
        }
        else
        {
          this.fileBuilder_.setMessage(paramInt, paramFileDescriptorProto);
        }
        return this;
      }

      public Builder setFile(int paramInt, DescriptorProtos.FileDescriptorProto.Builder paramBuilder)
      {
        if (this.fileBuilder_ == null)
        {
          ensureFileIsMutable();
          this.file_.set(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.fileBuilder_.setMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addFile(DescriptorProtos.FileDescriptorProto paramFileDescriptorProto)
      {
        if (this.fileBuilder_ == null)
        {
          if (paramFileDescriptorProto == null)
            throw new NullPointerException();
          ensureFileIsMutable();
          this.file_.add(paramFileDescriptorProto);
          onChanged();
        }
        else
        {
          this.fileBuilder_.addMessage(paramFileDescriptorProto);
        }
        return this;
      }

      public Builder addFile(int paramInt, DescriptorProtos.FileDescriptorProto paramFileDescriptorProto)
      {
        if (this.fileBuilder_ == null)
        {
          if (paramFileDescriptorProto == null)
            throw new NullPointerException();
          ensureFileIsMutable();
          this.file_.add(paramInt, paramFileDescriptorProto);
          onChanged();
        }
        else
        {
          this.fileBuilder_.addMessage(paramInt, paramFileDescriptorProto);
        }
        return this;
      }

      public Builder addFile(DescriptorProtos.FileDescriptorProto.Builder paramBuilder)
      {
        if (this.fileBuilder_ == null)
        {
          ensureFileIsMutable();
          this.file_.add(paramBuilder.build());
          onChanged();
        }
        else
        {
          this.fileBuilder_.addMessage(paramBuilder.build());
        }
        return this;
      }

      public Builder addFile(int paramInt, DescriptorProtos.FileDescriptorProto.Builder paramBuilder)
      {
        if (this.fileBuilder_ == null)
        {
          ensureFileIsMutable();
          this.file_.add(paramInt, paramBuilder.build());
          onChanged();
        }
        else
        {
          this.fileBuilder_.addMessage(paramInt, paramBuilder.build());
        }
        return this;
      }

      public Builder addAllFile(Iterable paramIterable)
      {
        if (this.fileBuilder_ == null)
        {
          ensureFileIsMutable();
          GeneratedMessage.Builder.addAll(paramIterable, this.file_);
          onChanged();
        }
        else
        {
          this.fileBuilder_.addAllMessages(paramIterable);
        }
        return this;
      }

      public Builder clearFile()
      {
        if (this.fileBuilder_ == null)
        {
          this.file_ = Collections.emptyList();
          this.bitField0_ &= -2;
          onChanged();
        }
        else
        {
          this.fileBuilder_.clear();
        }
        return this;
      }

      public Builder removeFile(int paramInt)
      {
        if (this.fileBuilder_ == null)
        {
          ensureFileIsMutable();
          this.file_.remove(paramInt);
          onChanged();
        }
        else
        {
          this.fileBuilder_.remove(paramInt);
        }
        return this;
      }

      public DescriptorProtos.FileDescriptorProto.Builder getFileBuilder(int paramInt)
      {
        return (DescriptorProtos.FileDescriptorProto.Builder)getFileFieldBuilder().getBuilder(paramInt);
      }

      public DescriptorProtos.FileDescriptorProtoOrBuilder getFileOrBuilder(int paramInt)
      {
        if (this.fileBuilder_ == null)
          return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.file_.get(paramInt);
        return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.fileBuilder_.getMessageOrBuilder(paramInt);
      }

      public List getFileOrBuilderList()
      {
        if (this.fileBuilder_ != null)
          return this.fileBuilder_.getMessageOrBuilderList();
        return Collections.unmodifiableList(this.file_);
      }

      public DescriptorProtos.FileDescriptorProto.Builder addFileBuilder()
      {
        return (DescriptorProtos.FileDescriptorProto.Builder)getFileFieldBuilder().addBuilder(DescriptorProtos.FileDescriptorProto.getDefaultInstance());
      }

      public DescriptorProtos.FileDescriptorProto.Builder addFileBuilder(int paramInt)
      {
        return (DescriptorProtos.FileDescriptorProto.Builder)getFileFieldBuilder().addBuilder(paramInt, DescriptorProtos.FileDescriptorProto.getDefaultInstance());
      }

      public List getFileBuilderList()
      {
        return getFileFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getFileFieldBuilder()
      {
        if (this.fileBuilder_ == null)
        {
          this.fileBuilder_ = new RepeatedFieldBuilder(this.file_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
          this.file_ = null;
        }
        return this.fileBuilder_;
      }
    }
  }

  public static abstract interface FileDescriptorSetOrBuilder extends MessageOrBuilder
  {
    public abstract List getFileList();

    public abstract DescriptorProtos.FileDescriptorProto getFile(int paramInt);

    public abstract int getFileCount();

    public abstract List getFileOrBuilderList();

    public abstract DescriptorProtos.FileDescriptorProtoOrBuilder getFileOrBuilder(int paramInt);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.protobuf.DescriptorProtos
 * JD-Core Version:    0.6.2
 */