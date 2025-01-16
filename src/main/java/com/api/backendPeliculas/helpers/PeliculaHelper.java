package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface PeliculaHelper {

    ResponseEntity<GenericResponse> validarYGuardarPelicula(PeliculaModel peliculaModel) throws JsonProcessingException;

    ResponseEntity<GenericResponse> editarPelicula(PeliculaModel pelicula, Long id) throws JsonProcessingException;

    ResponseEntity<GenericResponse> eliminarPelicula(Long id);

}

