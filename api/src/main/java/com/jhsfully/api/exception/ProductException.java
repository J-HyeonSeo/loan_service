package com.jhsfully.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductException extends RuntimeException{
    private String message;
}
