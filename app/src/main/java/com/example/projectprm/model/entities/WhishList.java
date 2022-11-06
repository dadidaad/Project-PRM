package com.example.projectprm.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WhishList", primaryKeys = {"acc_Id","book_id"})
public class WhishList {
    @NonNull
    @ColumnInfo(name = "acc_Id")
    private int accId;

    @NonNull
    @ColumnInfo(name = "book_id")
    private int bookId;

    @ColumnInfo(name = "desc")
    private String desc;

    public WhishList(){}
    public WhishList(int accId, int bookId, String desc) {
        this.accId = accId;
        this.bookId = bookId;
        this.desc = desc;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
