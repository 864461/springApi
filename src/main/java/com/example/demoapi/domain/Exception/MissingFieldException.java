package com.example.demoapi.domain.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingFieldException  extends RuntimeException{
    public MissingFieldException(String message){
        super(message);
    }
}
