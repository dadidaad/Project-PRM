package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    public void insert(Order order);

    @Update
    public void update(Order order);

    @Delete
    public void delete(Order order);

    @Query("SELECT * FROM `Order`")
    public List<Order> getAll();
}
