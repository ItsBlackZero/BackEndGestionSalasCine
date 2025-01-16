package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.PeliculaSalaCineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PeliculaSalaCineServiceImpl  implements  PeliculaSalaCineService{

    @Autowired
    private PeliculaSalaCineRepository peliculaSalaCineRepository;

    @Override
    public ResponseEntity<GenericResponse> getAllPeliculasSalaCine() {
        List<PeliculaSalaCineModel> peliculaSalaCine = peliculaSalaCineRepository.findAll();
        GenericResponse response = new GenericResponse("success", "Películas obtenidas exitosamente.", peliculaSalaCine);
        return ResponseEntity.ok(response);
    }

    @Override
    public GenericResponse savePeliculaSalaCine(PeliculaSalaCineModel peliculaSalaCineModel) {
        PeliculaSalaCineModel peliculaGuardada = peliculaSalaCineRepository.save(peliculaSalaCineModel);
        return new GenericResponse("success", "Película guardada exitosamente.", List.of(peliculaGuardada));
    }

    @Override
    public List<PeliculaSalaCineModel> getPeliculasPorFecha(Date fecha) {
        List<PeliculaSalaCineModel> peliculaSalaCine = peliculaSalaCineRepository.findByFechaPublicacion(fecha);
        return (peliculaSalaCine);
    }

    @Override
    public List<PeliculaSalaCineModel> getPeliculasPorNombreYIdSala(String nombrePelicula, Long idSalaCine) {
        List<PeliculaSalaCineModel> peliculasSalaCine = peliculaSalaCineRepository.findByPeliculaNombreAndSalaCineIdSalaCine(nombrePelicula, idSalaCine);
        return peliculasSalaCine;
    }

    @Override
    public List<PeliculaSalaCineModel> getPeliculasPorNombreSala(String nombreSalaCine) {
        List<PeliculaSalaCineModel> peliculasSalaCine = peliculaSalaCineRepository.findBySalaCineNombre(nombreSalaCine);
        return peliculasSalaCine;
    }

}
