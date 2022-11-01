package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Category;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert
    public void insert(Book book);

    @Update
    public void update(Book book);

    @Delete
    public void delete(Book book);

    @Query("SELECT * FROM Book")
    public List<Book> getAll();

    @Query("SELECT * FROM book WHERE book_id = :bookId")
    public Book get(int bookId);

    @Query("SELECT * FROM book Order by avg_stars desc")
    public List<Book> getBookOrderRate();

    @Query("SELECT * FROM book WHERE cat_id = :catId")
    public List<Book> getBookByCategory(int catId);
}
