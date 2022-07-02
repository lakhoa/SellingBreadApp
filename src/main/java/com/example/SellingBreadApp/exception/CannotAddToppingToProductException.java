package com.example.SellingBreadApp.exception;

// TODO: Class name should be a NOUN, pls check Java coding convention
public class CannotAddToppingToProductException extends IllegalAccessException{
  public CannotAddToppingToProductException(String message) {
    super(message);
  }

}
