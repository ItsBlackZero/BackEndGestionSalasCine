package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

public interface ValidarRequestHelper {
    <T> GenericResponse validarRequest(Supplier<T> operacion, String mensaje);
}
