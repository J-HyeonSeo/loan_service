package com.jhsfully.api.exception;

import com.jhsfully.api.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> badRequestHandler(){
        return ResponseEntity.badRequest().body(new Response("400", "입력값이 잘못되었습니다."));
    }

    @ExceptionHandler(UserException.class)
    private ResponseEntity<?> userExceptionHandler(UserException exception){
        return ResponseEntity.status(404).body(new Response("404", exception.getMessage()));
    }

    @ExceptionHandler(ProductException.class)
    private ResponseEntity<?> userExceptionHandler(ProductException exception){
        return ResponseEntity.status(404).body(new Response("404", exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> extraExceptionHandler(Exception e){
        return ResponseEntity.status(500).body(new Response("500", "서버에 오류가 발생하였습니다."));
    }
}
