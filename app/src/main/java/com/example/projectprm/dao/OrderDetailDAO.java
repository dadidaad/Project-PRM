package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.OrderDetail;
import com.example.projectprm.model.entities.Price;

import java.util.List;
@Dao
public interface OrderDetailDAO {
    @Insert
    public void insert(OrderDetail orderDetail);

    @Update
    public void update(OrderDetail orderDetail);

    @Delete
    public void delete(OrderDetail orderDetail);

    @Query("SELECT * FROM `Order Detail`")
    public List<OrderDetail> getAll();

    @Query("SELECT book_id from `Order Detail` Group by book_id order by Sum(quantity) DESC ")
    public List<Integer> getBestSellingBookID();
}
