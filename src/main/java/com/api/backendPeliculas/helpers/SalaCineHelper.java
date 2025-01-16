package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface SalaCineHelper {

    ResponseEntity<GenericResponse> validarYGuardarSalaCine(SalaCineModel salaCine) throws JsonProcessingException;

    ResponseEntity<GenericResponse> editarSalaCine(SalaCineModel salaCine, Long id) throws JsonProcessingException;

    ResponseEntity<GenericResponse> eliminarSalaCine(Long id);
}
