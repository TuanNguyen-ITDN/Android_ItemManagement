package com.example.androiditemsmanagement;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Insert
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Update
    void updateOne(Item item);
}


