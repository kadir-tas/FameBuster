package com.famebuster.util;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Double stringToDouble(String s) {
        return (s == null || s.isEmpty()) ? 0 : Double.parseDouble(s);
    }

    @TypeConverter
    public static String doubleToString(Double d) {
        return d == null ? "" : d.toString();
    }

}