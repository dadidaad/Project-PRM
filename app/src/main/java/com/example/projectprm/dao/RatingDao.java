package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Rating;

import java.util.List;

@Dao
public interface RatingDao {
    @Insert
    public void insert(Rating rating);

    @Update
    public void update(Rating rating);

    @Delete
    public void delete(Rating rating);

    @Query("SELECT * FROM Rating")
    public List<Rating> getAll();
}
