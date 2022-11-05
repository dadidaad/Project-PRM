package com.example.projectprm.model;

import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;

public class CartItem {
    private Book book;
    private int quantity;
    private Price oldPrice;
    private Price newPrice;
    private String authorName;
    private String categoryName;

    public CartItem() {
    }

    public CartItem(Book book, int quantity, Price oldPrice, Price newPrice, String authorName, String categoryName) {
        this.book = book;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.authorName = authorName;
        this.categoryName = categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Price getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Price oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Price getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Price newPrice) {
        this.newPrice = newPrice;
    }
}
