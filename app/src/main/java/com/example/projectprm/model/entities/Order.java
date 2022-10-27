package com.example.projectprm.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Order", foreignKeys = {
        @ForeignKey(entity = Account.class, parentColumns = "acc_id", childColumns = "cus_id"
        ),
        @ForeignKey(entity = Account.class, parentColumns = "acc_id", childColumns = "emp_id")})
public class Order {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "order_id")
    private int orderId;

    @NonNull
    @ColumnInfo(name = "order_date")
    private Date orderDate;

    @ColumnInfo(name = "required_date")
    private Date requiredDate;

    @ColumnInfo(name = "shipped_date")
    private Date shippedDate;

    @ColumnInfo(name = "total")
    private Integer total;

    @ColumnInfo(name = "cus_id")
    @NonNull
    private int customerId;

    @ColumnInfo(name = "emp_id")
    private Integer employeeId;

    public Order() {
    }

    public Order(int orderId, @NonNull Date orderDate, Date requiredDate, Date shippedDate, Integer total, int customerId, Integer employeeId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.total = total;
        this.customerId = customerId;
        this.employeeId = employeeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @NonNull
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NonNull Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
