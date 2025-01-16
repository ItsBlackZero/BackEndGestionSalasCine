package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ValidacionByFecha {


    void validarFecha(List<?> lista);
}
