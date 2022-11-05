package com.example.projectprm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    public void insert(Category category);

    @Update
    public void update(Category category);

    @Delete
    public void delete(Category category);

    @Query("SELECT * FROM Category")
    public List<Category> getAll();

    @Query("SELECT * FROM Category WHERE cat_id = :categoryId")
    public Category get(int categoryId);
}
