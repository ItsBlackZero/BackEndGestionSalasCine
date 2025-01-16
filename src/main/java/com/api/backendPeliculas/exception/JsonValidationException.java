package com.api.backendPeliculas.exception;

public class JsonValidationException extends RuntimeException{

    public JsonValidationException(String message){
        super(message);
    }
}
