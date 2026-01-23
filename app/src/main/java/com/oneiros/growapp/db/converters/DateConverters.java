package com.oneiros.growapp.db.converters;

import androidx.room.TypeConverter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>Utility class to provide Date Type converters for {@link androidx.room}.</p>
 * Datetime objects are stored as text values in the database and automatically converted.
 */
public class DateConverters {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    private DateConverters(){
        throw new IllegalStateException("Utility class");
    }

    @TypeConverter
    public static String fromZonedDateTime(ZonedDateTime value) {
        return value == null ? null : value.format(formatter);
    }

    @TypeConverter
    public static ZonedDateTime toZonedDateTime(String value) {
        return value == null ? null : ZonedDateTime.parse(value, formatter);
    }
}