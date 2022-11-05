package com.example.projectprm.utils;

import android.app.Application;
import android.content.Context;

import com.example.projectprm.exceptions.BookNotFoundException;
import com.example.projectprm.exceptions.QuantityOutOfRangeException;
import com.example.projectprm.model.CartItem;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.repos.AuthorRepository;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.CategoryRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.session.Session;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartHelper implements Serializable {
    private static final long serialVersionUID = 1L;

    Application application;

    Session session;

    BookRepository bookRepository;
    PriceRepository priceRepository;
    CategoryRepository categoryRepository;
    AuthorRepository authorRepository;
    public CartHelper(Application application) {
        this.application = application;
        session = new Session(application.getApplicationContext());
        cartItemMap = session.getCart();
        historyPrice = session.getHistoryPrice();
        totalPrice = session.getTotalPrice();
        totalQuantity = session.getTotalQuantity();
        bookRepository = new BookRepository(application);
        priceRepository = new PriceRepository(application);
        categoryRepository = new CategoryRepository(application);
        authorRepository = new AuthorRepository(application);
    }


    private Map<Integer, Integer> cartItemMap;
    private List<Price> historyPrice;
    private BigInteger totalPrice;
    private int totalQuantity;

    public Map<Integer, Integer> getCartItemMap() {
        return cartItemMap;
    }


    public List<Price> getHistoryPrice() {
        return historyPrice;
    }

    public BigInteger getTotalPrice() {
        totalPrice = session.getTotalPrice();
        return totalPrice;
    }


    public int getTotalQuantity() {
        totalQuantity = session.getTotalQuantity();
        return totalQuantity;
    }

    public List<CartItem> convertFromMapToList(){
        cartItemMap = session.getCart();
        List<CartItem> cartItemList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cartItemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            Book book = bookRepository.getByID(entry.getKey());
            cartItem.setBook(book);
            cartItem.setQuantity(entry.getValue());
            cartItem.setAuthorName(authorRepository.get(book.getAuthorID()).getAuthorName());
            cartItem.setCategoryName(categoryRepository.get(book.getCatID()).getCategoryName());
            cartItem.setOldPrice(priceRepository.getBeforePriceOfBook(book.getBookID()));
            cartItem.setNewPrice(priceRepository.getPriceBookID(book.getBookID()));
            cartItemList.add(cartItem);
        }
        return cartItemList;
    }
    public void add(int bookId, int quantity, Price price) {
        boolean isExist = false;
        int oldQuantity = 0;
        Price oldPrice = null;
        if (cartItemMap.containsKey(bookId)) {
            oldQuantity = cartItemMap.get(bookId);
            cartItemMap.put(bookId, oldQuantity + quantity);
            if (!historyPrice.contains(price)) {
                oldPrice = getOldPrice(bookId);
                int index = historyPrice.indexOf(oldPrice);
                historyPrice.remove(index);
                historyPrice.add(price);
                isExist = true;
            }
        } else {
            cartItemMap.put(bookId, quantity);
            historyPrice.add(price);
        }
        if (isExist) {
            totalPrice = totalPrice.subtract(getPriceOfItem(oldPrice.getPrice(), oldQuantity));
            totalPrice = totalPrice.add(getPriceOfItem(price.getPrice(), oldQuantity + quantity));
        } else {
            totalPrice = totalPrice.add(getPriceOfItem(price.getPrice(), quantity));
        }
        totalQuantity += quantity;
        save();
    }

    private Price getOldPrice(int bookId) {
        return historyPrice.stream().filter(p -> p.getBookID() == bookId).findFirst().get();
    }

    private BigInteger getPriceOfItem(int price, int quantity) {
        return BigInteger.valueOf(price).multiply(BigInteger.valueOf(quantity));
    }

    public void update(int bookId, int quantity, Price price) throws BookNotFoundException, QuantityOutOfRangeException {
        if (!cartItemMap.containsKey(bookId)) throw new BookNotFoundException();
        if (quantity < 0) throw new QuantityOutOfRangeException("Quantity must larger than 0");
        int oldQuantity = cartItemMap.get(bookId);
        BigInteger oldPrice = getPriceOfItem(getOldPrice(bookId).getPrice(), oldQuantity);
        cartItemMap.put(bookId, quantity);
        totalQuantity = totalQuantity - oldQuantity + quantity;
        totalPrice = totalPrice.subtract(oldPrice).add(getPriceOfItem(price.getPrice(), totalQuantity));
        save();
    }

    public void remove(int bookId, int quantity, Price price) throws BookNotFoundException, QuantityOutOfRangeException {
        if (!cartItemMap.containsKey(bookId)) throw new BookNotFoundException();
        int bookQuantity = cartItemMap.get(bookId);
        if (quantity < 0 || quantity > bookQuantity)
            throw new QuantityOutOfRangeException(quantity + " is invalid. Must be non-negative and less than the current quantity of book in the cart.");
        if (quantity == bookQuantity) {
            cartItemMap.remove(bookId);
        } else {
            cartItemMap.put(bookId, bookQuantity - quantity);
        }
        if (!historyPrice.contains(price)) {
            Price oldPrice = getOldPrice(bookId);
            totalPrice = totalPrice.subtract(getPriceOfItem(oldPrice.getPrice(), bookQuantity)).add(getPriceOfItem(price.getPrice(), bookQuantity - quantity));
        } else {
            totalPrice = totalPrice.subtract(getPriceOfItem(price.getPrice(), quantity));
        }
        totalQuantity -= quantity;
        save();
    }

    public void remove(int bookId) throws BookNotFoundException {
        if(!cartItemMap.containsKey(bookId)) throw new BookNotFoundException();
        int quantity = cartItemMap.get(bookId);
        cartItemMap.remove(bookId);
        Price oldPrice = getOldPrice(bookId);
        totalPrice = totalPrice.subtract(getPriceOfItem(oldPrice.getPrice(), quantity));
        totalQuantity -= quantity;
        save();
    }

    public void clear(){
        cartItemMap.clear();
        totalQuantity = 0;
        totalPrice =  BigInteger.ZERO;
        save();
    }

    public int getQuantity(int bookId) throws BookNotFoundException{
        if(!cartItemMap.containsKey(bookId)) throw new BookNotFoundException();
        return cartItemMap.get(bookId);
    }


    private void save(){
        session.saveCart(cartItemMap, totalQuantity, totalPrice, historyPrice);
    }
}
