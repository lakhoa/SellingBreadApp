package com.example.SellingBreadApp.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = InvalidSumToppingQuantityException.class)
    public final ResponseEntity<String> handleInvalidSumToppingQuantityException(InvalidSumToppingQuantityException exception){
        log.error("error: Invalid Sum Topping Quantity Exception");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NotFoundOrderException.class)
    public final ResponseEntity<String> handleNotFoundOrderException(NotFoundOrderException exception){
        log.error("error: Not Found Order Exception");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleCustomException(CustomException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
