package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.services.PeliculaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pelicula")
@CrossOrigin(origins = "http://localhost:4200")

public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    //para obtener todas las peliculas
    @GetMapping
    public List<PeliculaModel> getAllPeliculas(){
        return peliculaService.getAllPeliculasActivas();
    }

    @PostMapping
    public PeliculaModel savePelicula(@Valid @RequestBody PeliculaModel pelicula){
        return peliculaService.savePelicula(pelicula);
    }

    @PutMapping("/editar/{id}")
    public PeliculaModel editarPelicula(@Valid @RequestBody PeliculaModel pelicula, @PathVariable Long id) {
        try {
            pelicula.setIdPelicula(id);
            PeliculaModel peliculaEditada = peliculaService.editPelicula(pelicula);
            if (peliculaEditada == null) {
                throw new EntityNotFoundException("No se encontró la película con el id: " + id);
            }
            return peliculaEditada;
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado al editar la película", ex);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPelicula(@PathVariable Long id){
        peliculaService.deletePelicula(id);
    }
}
