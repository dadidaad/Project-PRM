package com.example.projectprm.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Rating", foreignKeys = {
        @ForeignKey(entity = Account.class, parentColumns = "acc_id", childColumns = "acc_id"
        ),
        @ForeignKey(entity = Book.class, parentColumns = "book_id", childColumns = "book_id"),
        @ForeignKey(entity = Order.class, parentColumns = "order_id", childColumns = "order_id")})
public class Rating {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "rate_id")
    private int rateId;

    @NonNull
    @ColumnInfo(name = "stars")
    private int stars;

    @NonNull
    @ColumnInfo(name = "acc_id")
    private int accountId;

    @NonNull
    @ColumnInfo(name = "book_id")
    private int bookId;

    @NonNull
    @ColumnInfo(name = "order_id")
    private int orderId;

    @ColumnInfo(name = "rate_time")
    private Date rateTime;

    @ColumnInfo(name = "rate_content")
    private String content;

    public Rating() {

    }

    public Rating(int rateId, int stars, int accountId, int bookId, int orderId, Date rateTime, String content) {
        this.rateId = rateId;
        this.stars = stars;
        this.accountId = accountId;
        this.bookId = bookId;
        this.orderId = orderId;
        this.rateTime = rateTime;
        this.content = content;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getRateTime() {
        return rateTime;
    }

    public void setRateTime(Date rateTime) {
        this.rateTime = rateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
