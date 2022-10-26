package com.example.projectprm.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Author")
public class Author {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    @NonNull
    private int authorId;

    @NonNull
    @ColumnInfo(name = "author_name")
    private String authorName;


    public Author() {

    }

    public Author(int authorId, @NonNull String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @NonNull
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(@NonNull String authorName) {
        this.authorName = authorName;
    }
}
