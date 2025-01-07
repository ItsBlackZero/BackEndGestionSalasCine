package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.repository.PeliculaSalaCineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaSalaCineService {

    @Autowired
    private PeliculaSalaCineRepository peliculaSalaCineRepository;

    public List<PeliculaSalaCineModel> getAllPeliculasSalaCine() {
        return (List<PeliculaSalaCineModel>) peliculaSalaCineRepository.findAll();
    }

    public PeliculaSalaCineModel savePeliculaSalaCine(PeliculaSalaCineModel peliculaSalaCine) {
        return peliculaSalaCineRepository.save(peliculaSalaCine);
    }

    public List<PeliculaSalaCineModel> getPeliculasPorFecha(Date fecha) {
        return peliculaSalaCineRepository.findByFechaPublicacion(fecha);
    }

    public List<PeliculaSalaCineModel> getPeliculasPorNombreSala(String nombreSalaCine) {
        return peliculaSalaCineRepository.findBySalaCineNombre(nombreSalaCine);
    }

    public List<PeliculaSalaCineModel> getPeliculasPorNombreYIdSala(String nombrePelicula, Long idSalaCine) {
        return peliculaSalaCineRepository.findByPeliculaNombreAndSalaCineIdSalaCine(nombrePelicula, idSalaCine);
    }
}
