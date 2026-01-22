package com.oneiros.growapp.db.converters;

import androidx.room.TypeConverter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverters {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @TypeConverter
    public static String fromZonedDateTime(ZonedDateTime value) {
        return value == null ? null : value.format(formatter);
    }

    @TypeConverter
    public static ZonedDateTime toZonedDateTime(String value) {
        return value == null ? null : ZonedDateTime.parse(value, formatter);
    }
}