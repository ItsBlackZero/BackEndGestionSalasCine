package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.helpers.ValidarRequestHelper;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PeliculaServiceImpl implements PeliculaService{


    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public ResponseEntity<GenericResponse> getAllPeliculasActivas() {
        List<PeliculaModel> peliculas = peliculaRepository.findActivePeliculas();
        GenericResponse response = new GenericResponse("success", "Películas obtenidas exitosamente.", peliculas);
        return ResponseEntity.ok(response);
    }
    @Override
    public GenericResponse savePelicula(PeliculaModel pelicula) {
        PeliculaModel peliculaGuardada = peliculaRepository.save(pelicula);
        return new GenericResponse("success", "Película guardada exitosamente.", List.of(peliculaGuardada));
    }
    @Override
    public GenericResponse getPeliculaById(Long id) {
        PeliculaModel peliculaObtenida = peliculaRepository.findById(id).orElse(null);
        return new GenericResponse("success", "Película obtenida exitosamente.", peliculaObtenida);
    }
    @Override
    public GenericResponse editPelicula(PeliculaModel pelicula) {
        PeliculaModel peliculaEditada = peliculaRepository.save(pelicula);
        return new GenericResponse("success", "Película editada exitosamente.", List.of(peliculaEditada));
    }

    @Override
    public GenericResponse deletePelicula(PeliculaModel pelicula){
        PeliculaModel peliculaEliminada = peliculaRepository.save(pelicula);
        return new GenericResponse("success", "Película eliminada exitosamente.", List.of(peliculaEliminada));
    }

}
