package com.api.backendPeliculas.repository;

import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PeliculaSalaCineRepository extends JpaRepository<PeliculaSalaCineModel, Long> {

//    List<PeliculaSalaCineModel> buscarPeliculaByNombreBySala(String nombre, Long idSalaCine);

    List<PeliculaSalaCineModel> findByFechaPublicacion(Date fecha);

    List<PeliculaSalaCineModel> findByPeliculaNombreAndSalaCineIdSalaCine(String nombrePelicula, Long idSalaCine);

    List<PeliculaSalaCineModel> findBySalaCineNombre(String nombreSalaCine);
}

