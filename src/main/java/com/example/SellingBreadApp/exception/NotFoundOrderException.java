package com.example.SellingBreadApp.exception;

public class NotFoundOrderException extends IllegalAccessException {

  public NotFoundOrderException(String message) {
    super(message);
  }

}
