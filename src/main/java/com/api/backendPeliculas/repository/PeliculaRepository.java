package com.api.backendPeliculas.repository;

import com.api.backendPeliculas.entities.PeliculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<PeliculaModel, Long> {

    @Query("SELECT p FROM PeliculaModel p WHERE p.estado = 1")
    List<PeliculaModel> findActivePeliculas();

}
