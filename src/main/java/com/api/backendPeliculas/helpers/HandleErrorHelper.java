package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface HandleErrorHelper {

    ResponseEntity<GenericResponse> handleError(String message, HttpStatus status);
}
