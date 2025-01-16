package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;


public interface PeliculaService {

    ResponseEntity<GenericResponse> getAllPeliculasActivas();

    GenericResponse savePelicula(PeliculaModel pelicula);

    GenericResponse editPelicula(PeliculaModel pelicula);

    GenericResponse getPeliculaById(Long id);

    GenericResponse deletePelicula(PeliculaModel pelicula);
}
