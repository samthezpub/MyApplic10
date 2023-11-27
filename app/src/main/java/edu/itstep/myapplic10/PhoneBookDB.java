package edu.itstep.myapplic10;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Contact.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class PhoneBookDB extends RoomDatabase {
    public abstract ContactDAO contactDAO();
}
