package com.example.SellingBreadApp.exception;

public class InvalidSumToppingQuantityException extends RuntimeException{
    public InvalidSumToppingQuantityException(String message) {
        super(message);
    }
    public InvalidSumToppingQuantityException(){

    }
}
