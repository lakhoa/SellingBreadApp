package com.example.SellingBreadApp.exception;

public class NotFoundOrderException extends RuntimeException {

  public NotFoundOrderException(String message) {
    super(message);
  }

  public NotFoundOrderException() {
  }
}
