package com.api.backendPeliculas.exception;


import com.api.backendPeliculas.exception.dto.ErrorModel;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LocalNotFountException.class)
    public ResponseEntity<GenericResponse> localNotFoundException(LocalNotFountException ex){
        GenericResponse response = new GenericResponse("VALIDATION_ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullOrBlankException.class)
    public ResponseEntity<GenericResponse> nullOrBlankException(NullOrBlankException ex){
        GenericResponse response = new GenericResponse("VALIDATION_ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
