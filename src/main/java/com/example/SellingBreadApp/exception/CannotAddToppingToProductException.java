package com.example.SellingBreadApp.exception;

public class CannotAddToppingToProductException extends RuntimeException{
  public CannotAddToppingToProductException(String message) {
    super(message);
  }

}
