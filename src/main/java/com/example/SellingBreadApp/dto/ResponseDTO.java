package com.example.SellingBreadApp.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {

  private T data;
  private HttpStatus status;
  private String message;

  public ResponseDTO(T data, HttpStatus status, String message) {
    this.data = data;
    this.status = status;
    this.message = message;
  }


  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

