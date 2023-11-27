package edu.itstep.myapplic10;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Address fromString(String value) {
        return Address.fromString(value);
    }

    @TypeConverter
    public static String toString(Address address) {
        return address.getCity();
    }
}
