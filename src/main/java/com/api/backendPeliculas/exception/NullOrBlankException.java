package com.api.backendPeliculas.exception;

public class NullOrBlankException extends RuntimeException{

    public NullOrBlankException(String message) {
        super(message);
    }
}
