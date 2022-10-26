package com.example.projectprm.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Price", foreignKeys = @ForeignKey(entity = Book.class, parentColumns = "book_id", childColumns = "book_id"))
public class Price {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "price_id")
    private int priceID;

    @NonNull
    @ColumnInfo(name = "price")
    private int price;

    public Price(int priceID, int price, @NonNull Date fromDate, Date toDate, int bookID) {
        this.priceID = priceID;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.bookID = bookID;
    }

    @NonNull
    @ColumnInfo(name = "from_date")
    private Date fromDate;

    @ColumnInfo(name = "to_date")
    private Date toDate;

    @NonNull
    @ColumnInfo(name = "book_id")
    private int bookID;

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NonNull
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(@NonNull Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }


    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
