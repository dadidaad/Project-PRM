package com.example.projectprm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.model.entities.Order;

import java.util.Date;
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

    @Query("INSERT INTO `Order` (order_date, required_date, total, cus_id) VALUES (:order_date, :required_date, :total, :cus_id) ")
    public void insert(Date order_date, Date required_date, int total, int cus_id);

    @Query("SELECT * FROM `Order` where order_id = :orderId")
    public Order getById(int orderId);

    @Query("SELECT * FROM `Order` ORDER BY order_date DESC LIMIT 1 OFFSET 0")
    public Order getLastOrder();
}
