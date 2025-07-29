package com.example.demo.Errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GitControllerAdvice extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomException> handleNotFoundException()
    {
        CustomException ex = new CustomException(404, "User with given username does not exist" );
        return ResponseEntity.status(404).body(ex);
    }

}
