package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Author;

import java.util.List;

@Dao
public interface AuthorDao {
    @Insert
    public void insert(Author author);

    @Update
    public void update(Author author);

    @Delete
    public void delete(Author author);

    @Query("SELECT * FROM Author")
    public List<Author> getAll();

    @Query("SELECT * FROM Author WHERE author_id = :authorId")
    public List<Author> get(int authorId);
}
