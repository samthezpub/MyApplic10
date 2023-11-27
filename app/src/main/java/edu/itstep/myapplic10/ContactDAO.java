package edu.itstep.myapplic10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE id = :id")
    Contact getById(long id);

    @Update
    void update(Contact... contacts);

    @Insert
    void insert(Contact... contacts);

    @Delete
    void delete(Contact... contacts);
}
