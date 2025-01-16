package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface PeliculaSalaCineHelper {

    ResponseEntity<GenericResponse> validarYGuardarPelicula(PeliculaSalaCineModel peliculaSalaCineModel);


    ResponseEntity<GenericResponse> editarPelicula(PeliculaSalaCineModel peliculaSalaCine, Long id);

    ResponseEntity<GenericResponse> eliminarPelicula(Long id);

    ResponseEntity<GenericResponse> obtenerPorFecha(Date fecha);


    ResponseEntity<GenericResponse> obtenerPorNombreSala(String nombreSala);


    ResponseEntity<GenericResponse> obtenerPorNombreYIdSala(String nombreSala, Long idSalaCine);
}
