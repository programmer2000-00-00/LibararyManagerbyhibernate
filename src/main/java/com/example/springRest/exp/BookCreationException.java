package com.example.springRest.exp;

public class BookCreationException extends RuntimeException  {
    public BookCreationException(String message){
        super(message);
    }
}
