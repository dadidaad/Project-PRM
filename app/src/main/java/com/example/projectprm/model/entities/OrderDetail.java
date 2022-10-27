package com.example.projectprm.model.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Order Detail", foreignKeys = {
        @ForeignKey(entity = Book.class, parentColumns = "book_id", childColumns = "book_id"
        ),
        @ForeignKey(entity = Order.class, parentColumns = "order_id", childColumns = "order_id")
})
public class OrderDetail {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "order_detail_id")
    private int orderDetailID;

    @NonNull
    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "discount")
    private Double discount;

    @ColumnInfo(name = "price")
    private Integer price;

    @NonNull
    @ColumnInfo(name = "book_id")
    private int book_id;

    @NonNull
    @ColumnInfo(name = "order_id")
    private int order_id;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailID, int quantity, Double discount, int price, int book_id, int order_id) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
        this.book_id = book_id;
        this.order_id = order_id;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
