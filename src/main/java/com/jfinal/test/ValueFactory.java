package com.jfinal.test;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;

/*
    author: Timor
    date: 2020/4/1 0001
*/
public class ValueFactory {
    private static HashMap<String, Class<?>> mapping = new HashMap<String, Class<?>>(){{
        put("java.lang.String", java.lang.String.class);
        put("java.math.BigDecimal", java.math.BigDecimal.class);
        put("java.lang.Boolean", java.lang.Boolean.class);
        put("java.lang.Byte", java.lang.Byte.class);
        put("java.lang.Short", java.lang.Short.class);
        put("java.lang.Integer", java.lang.Integer.class);
        put("java.lang.Long", java.lang.Long.class);
        put("java.lang.Float", java.lang.Float.class);
        put("java.lang.Double", java.lang.Double.class);
        put("java.sql.Date", java.sql.Date.class);
        put("java.sql.Time", java.sql.Time.class);
        put("java.sql.Timestamp", java.sql.Timestamp.class);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        put("byte[]", byte[].class);
        put("java.lang.Clob", Clob.class);
        put("java.lang.Blob", Blob.class);
        put("Array", Array.class);
        put("Struct", Struct.class);
        put("Ref", Ref.class);
        put("java.net.URL", java.net.URL.class);
    }};

    private static HashMap<Class<?>, ValueGenerator<?>> generatorMapping = new HashMap<Class<?>, ValueGenerator<?>>(){{
        put(java.lang.String.class, new StringValueGenerator());
        put(java.math.BigDecimal.class, new BigDecimalValueGenerator());
        put(java.lang.Boolean.class, new BooleanValueGenerator());
        put(java.lang.Byte.class, new ByteValueGenerator());
        put(java.lang.Short.class, new ShortValueGenerator());
        put(java.lang.Integer.class, new IntegerValueGenerator());
        put(java.lang.Long.class, new LongValueGenerator());
        put(java.lang.Float.class, new FloatValueGenerator());
        put(java.lang.Double.class, new DoubleValueGenerator());
        put(java.sql.Date.class, new DateValueGenerator());
        put(java.sql.Time.class, new TimeValueGenerator());
        put(java.sql.Timestamp.class, new TimestampGenerator());
    }};

    @SneakyThrows
    public static <T> T getValue(String javaType){
        if(mapping.containsKey(javaType)){
            return (T) generatorMapping.get(mapping.get(javaType)).getRandomValue();
        }
        return null;
    }

    interface ValueGenerator<T>{
        T getRandomValue();
    }

    static class ByteValueGenerator implements ValueGenerator<Byte>{
        private static int value = 1;

        @Override
        public Byte getRandomValue() {
            return new Byte((byte) (value = (++value) % 2));
        }
    }

    static class ShortValueGenerator implements ValueGenerator<Short>{
        private static Short counter = 1;

        @Override
        public Short getRandomValue() {
            if(counter < Short.MAX_VALUE){
                return counter++;
            }
            return (counter = 1);
        }
    }

    static class IntegerValueGenerator implements ValueGenerator<Integer>{
        private static Integer counter = 1;

        @Override
        public Integer getRandomValue() {
            if(counter < Integer.MAX_VALUE){
                return counter++;
            }
            return (counter = 1);
        }
    }

    static class LongValueGenerator implements ValueGenerator<Long>{
        private static Long counter = 1l;

        @Override
        public Long getRandomValue() {
            if(counter < Long.MAX_VALUE){
                return counter++;
            }
            return (counter = 1l);
        }
    }

    static class BigDecimalValueGenerator implements ValueGenerator<BigDecimal>{
        private static BigDecimal counter = new BigDecimal(9999);

        @Override
        public BigDecimal getRandomValue() {
            return counter.add(new BigDecimal(1));
        }
    }

    static class FloatValueGenerator implements ValueGenerator<Float>{
        private static Float counter = 1.5f;

        @Override
        public Float getRandomValue() {
            if(counter < Float.MAX_VALUE){
                return counter++;
            }
            return (counter = 1.5f);
        }
    }

    static class DoubleValueGenerator implements ValueGenerator<Double>{
        private static Double counter = 1.5;

        @Override
        public Double getRandomValue() {
            return counter++;
        }
    }

    static class BooleanValueGenerator implements ValueGenerator<Boolean>{
        private static Boolean bool = false;

        @Override
        public Boolean getRandomValue() {
            return (bool = !bool);
        }
    }

    static class StringValueGenerator implements ValueGenerator<String>{
        private static int counter = 1;

        @Override
        public String getRandomValue() {
            return "demo" + counter++;
        }
    }

    static class DateValueGenerator implements ValueGenerator<Date>{
        @Override
        public Date getRandomValue() {
            return new Date(System.currentTimeMillis());
        }
    }

    static class TimeValueGenerator implements ValueGenerator<Time>{
        @Override
        public Time getRandomValue() {
            return new Time(System.currentTimeMillis());
        }
    }

    static class TimestampGenerator implements ValueGenerator<Timestamp>{
        @Override
        public Timestamp getRandomValue() {
            return new Timestamp(System.currentTimeMillis());
        }
    }
}
