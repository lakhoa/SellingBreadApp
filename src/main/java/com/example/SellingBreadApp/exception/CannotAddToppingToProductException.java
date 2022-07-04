package com.example.SellingBreadApp.exception;

public class CannotAddToppingToProductException extends IllegalAccessException {

  public CannotAddToppingToProductException(String message) {
    super(message);
  }

}
