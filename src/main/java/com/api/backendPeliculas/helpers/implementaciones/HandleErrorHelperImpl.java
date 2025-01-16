package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.helpers.HandleErrorHelper;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HandleErrorHelperImpl implements HandleErrorHelper {

    @Override
    public ResponseEntity<GenericResponse> handleError(String message, HttpStatus status) {
        GenericResponse errorResponse = new GenericResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus("ERROR");
        return ResponseEntity.status(status).body(errorResponse);
    }
}
