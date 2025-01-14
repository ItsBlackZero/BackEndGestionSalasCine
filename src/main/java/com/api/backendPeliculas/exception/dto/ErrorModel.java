package com.api.backendPeliculas.exception.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(description = "Modelo de error de la API")
public class ErrorModel {

    private HttpStatus status;
    private String message;

    public ErrorModel() {
    }

    public ErrorModel(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
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
