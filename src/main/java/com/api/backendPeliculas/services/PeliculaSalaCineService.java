package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.PeliculaSalaCineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PeliculaSalaCineService {

    @Autowired
    private PeliculaSalaCineRepository peliculaSalaCineRepository;

    public GenericResponse getAllPeliculasSalaCine() {
        return validarRequest(() -> {
            List<PeliculaSalaCineModel> peliculasSalaCine = peliculaSalaCineRepository.findAll();
            if(peliculasSalaCine == null || peliculasSalaCine.isEmpty()) {
                throw new LocalNotFountException("No se encontraron registros.");
            }
            return peliculasSalaCine;
        }, "Películas en salas de cine obtenidas exitosamente.");
    }

    public GenericResponse savePeliculaSalaCine(GenericRequest request) throws ParseException {

        PeliculaSalaCineModel peliculaSalaCine = mapearRequest(request);

        return validarRequest(() -> {

            if(peliculaSalaCine.getPelicula().getIdPelicula() == null || peliculaSalaCine.getPelicula().getIdPelicula() <= 0) {
                throw new NullOrBlankException("El id de la película no puede ser nulo o menor a 1.");
            }
            if(peliculaSalaCine.getSalaCine().getIdSalaCine() == null || peliculaSalaCine.getSalaCine().getIdSalaCine() <= 0) {
                throw new NullOrBlankException("El id de la sala de cine no puede ser nulo o menor a 1.");
            }
            if(peliculaSalaCine.getFechaPublicacion() == null || peliculaSalaCine.getFechaPublicacion().toString().isBlank()) {
                throw new NullOrBlankException("La fecha de publicación no puede ser nula o en blanco.");
            }
            if(peliculaSalaCine.getFechaFin() == null || peliculaSalaCine.getFechaFin().toString().isBlank()) {
                throw new NullOrBlankException("La fecha de fin no puede ser nula o en blanco.");
            }

            PeliculaSalaCineModel peliculaSalaCineGuardada = peliculaSalaCineRepository.save(peliculaSalaCine);
            return List.of(peliculaSalaCineGuardada);
        }, "Película en sala de cine guardada exitosamente.");
    }

    public GenericResponse getPeliculasPorFecha(Date fecha) {
        return validarRequest(() -> {
            if(fecha == null) {
                throw new NullOrBlankException("La fecha no puede ser nula.");
            }
            List<PeliculaSalaCineModel> peliculasSalaCine = peliculaSalaCineRepository.findByFechaPublicacion(fecha);
            if(peliculasSalaCine == null || peliculasSalaCine.isEmpty()) {
                throw new LocalNotFountException("No se encontraron registros.");
            }
            return peliculasSalaCine;
        }, "Películas en salas de cine obtenidas exitosamente.");
    }

    public GenericResponse getPeliculasPorNombreYIdSala(String nombrePelicula, Long idSalaCine) {
        return validarRequest(() -> {

            if(nombrePelicula == null || nombrePelicula.isBlank()) {
                throw new NullOrBlankException("El nombre de la película no puede ser nulo o estar en blanco.");
            }
            if(idSalaCine == null || idSalaCine <= 0) {
                throw new NullOrBlankException("El id de la sala de cine no puede ser nulo o menor a 1.");
            }
            List<PeliculaSalaCineModel> peliculasSalaCine = peliculaSalaCineRepository.findByPeliculaNombreAndSalaCineIdSalaCine(nombrePelicula, idSalaCine);
            if(peliculasSalaCine == null || peliculasSalaCine.isEmpty()) {
                throw new LocalNotFountException("No se encuentran peliculas asignadas.");
            }
            return peliculasSalaCine;
        }, "Películas en salas de cine obtenidas exitosamente.");
    }

    public GenericResponse getPeliculasPorNombreSala(String nombreSalaCine) {
        return validarRequest(() -> {
            if(nombreSalaCine == null || nombreSalaCine.isBlank()) {
                throw new NullOrBlankException("El nombre de la sala de cine no puede ser nulo o estar en blanco.");
            }
            List<PeliculaSalaCineModel> peliculasSalaCine = peliculaSalaCineRepository.findBySalaCineNombre(nombreSalaCine);
            if(peliculasSalaCine == null || peliculasSalaCine.isEmpty()) {
                throw new LocalNotFountException("No se encontraron registros.");
            }
            return peliculasSalaCine;
        }, "Películas en salas de cine obtenidas exitosamente.");
    }


    private <T> GenericResponse validarRequest(Supplier<T> operacion, String mensaje) {
        try {
            if (peliculaSalaCineRepository == null) {
                throw new LocalNotFountException("Repositorio de películas no disponible.");
            }
            T result = operacion.get();
            if (result instanceof List && ((List<?>) result).isEmpty()) {
                throw new LocalNotFountException("No se encontraron registros.");
            }
            return new GenericResponse("success", mensaje, result);

        } catch (LocalNotFountException ex) {
            return new GenericResponse("error", ex.getMessage(), null);
        } catch (Exception ex) {
            return new GenericResponse("error", "Error inesperado: " + ex.getMessage(), null);
        }
    }

    private PeliculaSalaCineModel mapearRequest (GenericRequest request) throws ParseException {
        Map<String,Object> body = request.getBody();
        PeliculaSalaCineModel peliculaSalaCine = new PeliculaSalaCineModel();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        PeliculaModel pelicula = objectMapper.convertValue(body.get("pelicula"), PeliculaModel.class);
        peliculaSalaCine.setPelicula(pelicula);

        SalaCineModel salaCine = objectMapper.convertValue(body.get("salaCine"), SalaCineModel.class);
        peliculaSalaCine.setSalaCine(salaCine);

        String fechaPublicacionStr = (String) body.get("fechaPublicacion");
        if (fechaPublicacionStr != null && !fechaPublicacionStr.isEmpty()) {
            Date fechaPublicacion = formatoFecha.parse(fechaPublicacionStr);
            peliculaSalaCine.setFechaPublicacion(fechaPublicacion);
        }

        String fechaFinStr = (String) body.get("fechaFin");
        if (fechaFinStr != null && !fechaFinStr.isEmpty()) {
            Date fechaFin = formatoFecha.parse(fechaFinStr);
            peliculaSalaCine.setFechaFin(fechaFin);
        }

        return peliculaSalaCine;
    }
}
