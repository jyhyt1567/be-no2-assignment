package org.example.beno2assignment.schedule.controller;

import org.example.beno2assignment.schedule.exception.CustomErrorResponse;
import org.example.beno2assignment.schedule.exception.CustomException;
import org.example.beno2assignment.schedule.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleException(CustomException e){
        ErrorCode errorCode = e.getErrorCode();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getErrorCode().getStatus(), e.getMessage());
        return new ResponseEntity<>(customErrorResponse, e.getErrorCode().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleException(MethodArgumentNotValidException e){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
