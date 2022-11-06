package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.WhishList;

import java.util.List;

@Dao
public interface WhishListDao {
    @Insert
    public void insert(WhishList whishList);

    @Update
    public void update(WhishList whishList);

    @Delete
    public void delete(WhishList whishList);

    @Query("SELECT * FROM WhishList")
    public List<WhishList> getAll();

    @Query("SELECT * FROM WhishList WHERE acc_Id = :accId")
    public List<WhishList> get(int accId);

    @Query("SELECT * FROM WhishList WHERE acc_Id = :accId and book_id =:bookId")
    public WhishList getByAccIdBookId(int accId, int bookId);
}
