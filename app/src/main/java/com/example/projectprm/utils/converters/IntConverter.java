package com.example.projectprm.utils.converters;

import androidx.room.TypeConverter;

public class IntConverter {
    @TypeConverter
    public static String toString(Integer number) {
        return number == null ? null : Integer.toString(number);
    }

    @TypeConverter
    public static int toInt(String str) {
        return str == null ? null : Integer.parseInt(str);
    }
}