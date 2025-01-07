package com.api.backendPeliculas.repository;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaCineRepository extends JpaRepository<SalaCineModel, Long> {

    @Query("SELECT s FROM SalaCineModel s WHERE s.estado = 1")
    List<SalaCineModel> findActiveSalaCine();
}
