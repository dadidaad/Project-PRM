package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Price;

import java.util.List;

@Dao
public interface PriceDAO {
    @Insert
    public void insert(Price price);

    @Update
    public void update(Price price);

    @Delete
    public void delete(Price price);

    @Query("SELECT * FROM Price")
    public List<Price> getAll();

    @Query("SELECT * FROM Price WHERE price_id = :priceId")
    public List<Price> get(int priceId);

    @Query("SELECT * FROM Price Order by price")
    public List<Price> getOrderByPrice();

    @Query("Select * from Price where book_id = :bookId and to_date is null")
    public Price getByBookID(int bookId);

    @Query("SELECT DISTINCT *\n" +
            "FROM Price\n" +
            "WHERE book_id = :bookId  \n" +
            "ORDER BY from_date DESC\n" +
            "LIMIT 1 OFFSET 1 ")
    public Price getBeforePriceOfBook(int bookId);
}
