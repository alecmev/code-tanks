package com.google.inject.internal;

import com.google.inject.TypeLiteral;
import com.google.inject.internal.util..SourceProvider;
import com.google.inject.internal.util..Strings;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeConverterBinding;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

final class TypeConverterBindingProcessor extends AbstractProcessor
{
  TypeConverterBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  void prepareBuiltInConverters(InjectorImpl paramInjectorImpl)
  {
    this.injector = paramInjectorImpl;
    try
    {
      convertToPrimitiveType(Integer.TYPE, Integer.class);
      convertToPrimitiveType(Long.TYPE, Long.class);
      convertToPrimitiveType(Boolean.TYPE, Boolean.class);
      convertToPrimitiveType(Byte.TYPE, Byte.class);
      convertToPrimitiveType(Short.TYPE, Short.class);
      convertToPrimitiveType(Float.TYPE, Float.class);
      convertToPrimitiveType(Double.TYPE, Double.class);
      convertToClass(Character.class, new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral paramAnonymousTypeLiteral)
        {
          paramAnonymousString = paramAnonymousString.trim();
          if (paramAnonymousString.length() != 1)
            throw new RuntimeException("Length != 1.");
          return Character.valueOf(paramAnonymousString.charAt(0));
        }

        public String toString()
        {
          return "TypeConverter<Character>";
        }
      });
      convertToClasses(Matchers.subclassesOf(Enum.class), new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral paramAnonymousTypeLiteral)
        {
          return Enum.valueOf(paramAnonymousTypeLiteral.getRawType(), paramAnonymousString);
        }

        public String toString()
        {
          return "TypeConverter<E extends Enum<E>>";
        }
      });
      internalConvertToTypes(new AbstractMatcher()
      {
        public boolean matches(TypeLiteral paramAnonymousTypeLiteral)
        {
          return paramAnonymousTypeLiteral.getRawType() == Class.class;
        }

        public String toString()
        {
          return "Class<?>";
        }
      }
      , new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral paramAnonymousTypeLiteral)
        {
          try
          {
            return Class.forName(paramAnonymousString);
          }
          catch (ClassNotFoundException localClassNotFoundException)
          {
            throw new RuntimeException(localClassNotFoundException.getMessage());
          }
        }

        public String toString()
        {
          return "TypeConverter<Class<?>>";
        }
      });
    }
    finally
    {
      this.injector = null;
    }
  }

  private void convertToPrimitiveType(Class paramClass1, final Class paramClass2)
  {
    try
    {
      final Method localMethod = paramClass2.getMethod("parse" + .Strings.capitalize(paramClass1.getName()), new Class[] { String.class });
      TypeConverter local5 = new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral paramAnonymousTypeLiteral)
        {
          try
          {
            return localMethod.invoke(null, new Object[] { paramAnonymousString });
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException(localInvocationTargetException.getTargetException().getMessage());
          }
        }

        public String toString()
        {
          return "TypeConverter<" + paramClass2.getSimpleName() + ">";
        }
      };
      convertToClass(paramClass2, local5);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new AssertionError(localNoSuchMethodException);
    }
  }

  private void convertToClass(Class paramClass, TypeConverter paramTypeConverter)
  {
    convertToClasses(Matchers.identicalTo(paramClass), paramTypeConverter);
  }

  private void convertToClasses(final Matcher paramMatcher, TypeConverter paramTypeConverter)
  {
    internalConvertToTypes(new AbstractMatcher()
    {
      public boolean matches(TypeLiteral paramAnonymousTypeLiteral)
      {
        Type localType = paramAnonymousTypeLiteral.getType();
        if (!(localType instanceof Class))
          return false;
        Class localClass = (Class)localType;
        return paramMatcher.matches(localClass);
      }

      public String toString()
      {
        return paramMatcher.toString();
      }
    }
    , paramTypeConverter);
  }

  private void internalConvertToTypes(Matcher paramMatcher, TypeConverter paramTypeConverter)
  {
    this.injector.state.addConverter(new TypeConverterBinding($SourceProvider.UNKNOWN_SOURCE, paramMatcher, paramTypeConverter));
  }

  public Boolean visit(TypeConverterBinding paramTypeConverterBinding)
  {
    this.injector.state.addConverter(new TypeConverterBinding(paramTypeConverterBinding.getSource(), paramTypeConverterBinding.getTypeMatcher(), paramTypeConverterBinding.getTypeConverter()));
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\stuff\work\random\CodeTanks\#local-runner\local-runner\
 * Qualified Name:     com.google.inject.internal.TypeConverterBindingProcessor
 * JD-Core Version:    0.6.2
 */