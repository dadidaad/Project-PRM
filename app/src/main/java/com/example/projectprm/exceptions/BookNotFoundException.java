package com.example.projectprm.exceptions;

public class BookNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 43L;

    private static final String DEFAULT_MESSAGE = "Book is not found in the cart.";

    public BookNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
