package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface CrudHelper<T> {

    ResponseEntity<GenericResponse> guardar(T entity);
    ResponseEntity<GenericResponse> editar(T entity, Long id);
    ResponseEntity<GenericResponse> eliminar(Long id);
}
