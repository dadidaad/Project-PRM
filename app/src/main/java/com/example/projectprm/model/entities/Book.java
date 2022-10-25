package com.example.projectprm.model.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "Book")
public class Book {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "book_id")
    private int bookID;

    @NonNull
    @ColumnInfo(name = "book_name")
    private String bookName;

    @NonNull
    @ColumnInfo(name = "add_date")
    private Date addDate;

    @ColumnInfo(name = "desc")
    private String description;

    @NonNull
    @ColumnInfo(name = "units_in_stock")
    private String unitInStock;

    @ColumnInfo(name = "units_order")
    private String unitOrder;

    @ColumnInfo(name = "avg_stars")
    private String avgStar;

    @NonNull
    @ColumnInfo(name = "author_id")
    private String authorID;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    @NonNull
    public String getBookName() {
        return bookName;
    }

    public void setBookName(@NonNull String bookName) {
        this.bookName = bookName;
    }

    @NonNull
    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(@NonNull Date addDate) {
        this.addDate = addDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(@NonNull String unitInStock) {
        this.unitInStock = unitInStock;
    }

    public String getUnitOrder() {
        return unitOrder;
    }

    public void setUnitOrder(String unitOrder) {
        this.unitOrder = unitOrder;
    }

    public String getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(String avgStar) {
        this.avgStar = avgStar;
    }

    @NonNull
    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(@NonNull String authorID) {
        this.authorID = authorID;
    }

    @NonNull
    public String getCatID() {
        return catID;
    }

    public void setCatID(@NonNull String catID) {
        this.catID = catID;
    }

    @NonNull
    @ColumnInfo(name = "cat_id")
    private String catID;

    public Book(int bookID, @NonNull String bookName, @NonNull Date addDate, String description, @NonNull String unitInStock, String unitOrder, String avgStar, @NonNull String authorID, @NonNull String catID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.addDate = addDate;
        this.description = description;
        this.unitInStock = unitInStock;
        this.unitOrder = unitOrder;
        this.avgStar = avgStar;
        this.authorID = authorID;
        this.catID = catID;
    }
}
