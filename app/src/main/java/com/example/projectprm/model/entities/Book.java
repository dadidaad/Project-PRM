package com.example.projectprm.model.entities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Book", foreignKeys = {
        @ForeignKey(entity = Author.class, parentColumns = "author_id", childColumns = "author_id"
        ),
        @ForeignKey(entity = Category.class, parentColumns = "cat_id", childColumns = "cat_id")})
public class Book implements Serializable {
    
    @PrimaryKey(autoGenerate = true)
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

    @ColumnInfo(name = "units_in_stock")
    @NonNull
    private int unitInStock;

    @ColumnInfo(name = "units_order")
    private Integer unitOrder;

    @ColumnInfo(name = "avg_stars")
    private Double avgStars;

    @ColumnInfo(name = "author_id")
    private Integer authorID;

    @ColumnInfo(name = "cat_id")
    private Integer catID;

    @ColumnInfo(name = "image")
    private String image;

    public Book() {
    }

    public Book(int bookID, @NonNull String bookName, @NonNull Date addDate, String description, int unitInStock, Integer unitOrder, Double avgStars, Integer authorID, Integer catID, String image) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.addDate = addDate;
        this.description = description;
        this.unitInStock = unitInStock;
        this.unitOrder = unitOrder;
        this.avgStars = avgStars;
        this.authorID = authorID;
        this.catID = catID;
        this.image = image;
    }

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

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public Integer getUnitOrder() {
        return unitOrder;
    }

    public void setUnitOrder(Integer unitOrder) {
        this.unitOrder = unitOrder;
    }

    public Double getAvgStars() {
        return avgStars;
    }

    public void setAvgStars(Double avgStars) {
        this.avgStars = avgStars;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public Integer getCatID() {
        return catID;
    }

    public void setCatID(Integer catID) {
        this.catID = catID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
