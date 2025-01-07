package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<PeliculaModel> getAllPeliculasActivas() {
        return (List<PeliculaModel>) peliculaRepository.findActivePeliculas();
    }

    public PeliculaModel savePelicula(PeliculaModel pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public PeliculaModel getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public PeliculaModel editPelicula(PeliculaModel pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        PeliculaModel pelicula = peliculaRepository.findById(id).orElse(null);
        if (pelicula != null) {
            pelicula.setEstado(0); // 0= Inactivo
            peliculaRepository.save(pelicula);
        } else {
            throw new RuntimeException("Pelicula no encontrada con id: " + id);
        }
    }
}
