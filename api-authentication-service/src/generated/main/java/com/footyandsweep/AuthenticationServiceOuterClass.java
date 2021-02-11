// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AuthenticationService.proto

package com.footyandsweep;

public final class AuthenticationServiceOuterClass {
  private AuthenticationServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface UserOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.footyandsweep.User)
      com.google.protobuf.MessageOrBuilder {
  }
  /**
   * Protobuf type {@code com.footyandsweep.User}
   */
  public  static final class User extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.footyandsweep.User)
      UserOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use User.newBuilder() to construct.
    private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private User() {
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private User(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_User_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_User_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.footyandsweep.AuthenticationServiceOuterClass.User.class, com.footyandsweep.AuthenticationServiceOuterClass.User.Builder.class);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.footyandsweep.AuthenticationServiceOuterClass.User)) {
        return super.equals(obj);
      }
      com.footyandsweep.AuthenticationServiceOuterClass.User other = (com.footyandsweep.AuthenticationServiceOuterClass.User) obj;

      boolean result = true;
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.User parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.footyandsweep.AuthenticationServiceOuterClass.User prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.footyandsweep.User}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.footyandsweep.User)
        com.footyandsweep.AuthenticationServiceOuterClass.UserOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_User_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_User_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.footyandsweep.AuthenticationServiceOuterClass.User.class, com.footyandsweep.AuthenticationServiceOuterClass.User.Builder.class);
      }

      // Construct using com.footyandsweep.AuthenticationServiceOuterClass.User.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_User_descriptor;
      }

      @java.lang.Override
      public com.footyandsweep.AuthenticationServiceOuterClass.User getDefaultInstanceForType() {
        return com.footyandsweep.AuthenticationServiceOuterClass.User.getDefaultInstance();
      }

      @java.lang.Override
      public com.footyandsweep.AuthenticationServiceOuterClass.User build() {
        com.footyandsweep.AuthenticationServiceOuterClass.User result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.footyandsweep.AuthenticationServiceOuterClass.User buildPartial() {
        com.footyandsweep.AuthenticationServiceOuterClass.User result = new com.footyandsweep.AuthenticationServiceOuterClass.User(this);
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.footyandsweep.AuthenticationServiceOuterClass.User) {
          return mergeFrom((com.footyandsweep.AuthenticationServiceOuterClass.User)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.footyandsweep.AuthenticationServiceOuterClass.User other) {
        if (other == com.footyandsweep.AuthenticationServiceOuterClass.User.getDefaultInstance()) return this;
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.footyandsweep.AuthenticationServiceOuterClass.User parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.footyandsweep.AuthenticationServiceOuterClass.User) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.footyandsweep.User)
    }

    // @@protoc_insertion_point(class_scope:com.footyandsweep.User)
    private static final com.footyandsweep.AuthenticationServiceOuterClass.User DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.footyandsweep.AuthenticationServiceOuterClass.User();
    }

    public static com.footyandsweep.AuthenticationServiceOuterClass.User getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<User>
        PARSER = new com.google.protobuf.AbstractParser<User>() {
      @java.lang.Override
      public User parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new User(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<User> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<User> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.footyandsweep.AuthenticationServiceOuterClass.User getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface findUserByIdRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.footyandsweep.findUserByIdRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string userId = 1;</code>
     */
    java.lang.String getUserId();
    /**
     * <code>string userId = 1;</code>
     */
    com.google.protobuf.ByteString
        getUserIdBytes();
  }
  /**
   * Protobuf type {@code com.footyandsweep.findUserByIdRequest}
   */
  public  static final class findUserByIdRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.footyandsweep.findUserByIdRequest)
      findUserByIdRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use findUserByIdRequest.newBuilder() to construct.
    private findUserByIdRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private findUserByIdRequest() {
      userId_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private findUserByIdRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              userId_ = s;
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_findUserByIdRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_findUserByIdRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.class, com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.Builder.class);
    }

    public static final int USERID_FIELD_NUMBER = 1;
    private volatile java.lang.Object userId_;
    /**
     * <code>string userId = 1;</code>
     */
    public java.lang.String getUserId() {
      java.lang.Object ref = userId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userId_ = s;
        return s;
      }
    }
    /**
     * <code>string userId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUserIdBytes() {
      java.lang.Object ref = userId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getUserIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userId_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getUserIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userId_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest)) {
        return super.equals(obj);
      }
      com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest other = (com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest) obj;

      boolean result = true;
      result = result && getUserId()
          .equals(other.getUserId());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + USERID_FIELD_NUMBER;
      hash = (53 * hash) + getUserId().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.footyandsweep.findUserByIdRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.footyandsweep.findUserByIdRequest)
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_findUserByIdRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_findUserByIdRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.class, com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.Builder.class);
      }

      // Construct using com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        userId_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.footyandsweep.AuthenticationServiceOuterClass.internal_static_com_footyandsweep_findUserByIdRequest_descriptor;
      }

      @java.lang.Override
      public com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest getDefaultInstanceForType() {
        return com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest build() {
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest buildPartial() {
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest result = new com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest(this);
        result.userId_ = userId_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest) {
          return mergeFrom((com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest other) {
        if (other == com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.getDefaultInstance()) return this;
        if (!other.getUserId().isEmpty()) {
          userId_ = other.userId_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object userId_ = "";
      /**
       * <code>string userId = 1;</code>
       */
      public java.lang.String getUserId() {
        java.lang.Object ref = userId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          userId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string userId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUserIdBytes() {
        java.lang.Object ref = userId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string userId = 1;</code>
       */
      public Builder setUserId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string userId = 1;</code>
       */
      public Builder clearUserId() {
        
        userId_ = getDefaultInstance().getUserId();
        onChanged();
        return this;
      }
      /**
       * <code>string userId = 1;</code>
       */
      public Builder setUserIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        userId_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.footyandsweep.findUserByIdRequest)
    }

    // @@protoc_insertion_point(class_scope:com.footyandsweep.findUserByIdRequest)
    private static final com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest();
    }

    public static com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<findUserByIdRequest>
        PARSER = new com.google.protobuf.AbstractParser<findUserByIdRequest>() {
      @java.lang.Override
      public findUserByIdRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new findUserByIdRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<findUserByIdRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<findUserByIdRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_footyandsweep_User_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_footyandsweep_User_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_footyandsweep_findUserByIdRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_footyandsweep_findUserByIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033AuthenticationService.proto\022\021com.footy" +
      "andsweep\032\026google/type/date.proto\032\027google" +
      "/rpc/status.proto\"\006\n\004User\"%\n\023findUserByI" +
      "dRequest\022\016\n\006userId\030\001 \001(\t2l\n\025Authenticati" +
      "onService\022S\n\020findUserByUserId\022&.com.foot" +
      "yandsweep.findUserByIdRequest\032\027.com.foot" +
      "yandsweep.Userb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.type.DateProto.getDescriptor(),
          com.google.rpc.StatusProto.getDescriptor(),
        }, assigner);
    internal_static_com_footyandsweep_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_footyandsweep_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_footyandsweep_User_descriptor,
        new java.lang.String[] { });
    internal_static_com_footyandsweep_findUserByIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_footyandsweep_findUserByIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_footyandsweep_findUserByIdRequest_descriptor,
        new java.lang.String[] { "UserId", });
    com.google.type.DateProto.getDescriptor();
    com.google.rpc.StatusProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
