package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public GenericResponse getAllPeliculasActivas() {
        return validarRequest(() -> {
            List<PeliculaModel> peliculas = peliculaRepository.findActivePeliculas();
            return peliculas;
        }, "Películas obtenidas exitosamente.");
    }

    public GenericResponse savePelicula(GenericRequest request) {

        PeliculaModel pelicula = mapearRequest(request);

        return validarRequest(() -> {
            if (pelicula.getNombre() == null || pelicula.getNombre().isBlank()) {
                throw new NullOrBlankException("El nombre de la película no puede estar vacío.");
            }
            if (pelicula.getDuracion() == null || pelicula.getDuracion() <= 0 ) {
                throw new NullOrBlankException("La duración de la película debe ser un valor positivo.");
            }
            PeliculaModel peliculaGuardada = peliculaRepository.save(pelicula);
            return List.of(peliculaGuardada);
        }, "Película guardada exitosamente.");
    }

    public GenericResponse getPeliculaById(Long id) {
        try {
            if (peliculaRepository == null) {
                throw new LocalNotFountException("Repositorio de películas no disponible.");
            }
            PeliculaModel pelicula = peliculaRepository.findById(id).orElse(null);
            if (pelicula == null) {
                throw new LocalNotFountException("No se encontró la película con el id: " + id);
            }

            return new GenericResponse("success", "Película obtenida exitosamente.", List.of(pelicula));
        } catch (LocalNotFountException ex) {
            return new GenericResponse("error", ex.getMessage(), null);
        } catch (Exception ex) {
            return new GenericResponse("error", "Error al obtener la película: " + ex.getMessage(), null);
        }
    }
    public GenericResponse editPelicula(GenericRequest request, Long id) {

        PeliculaModel pelicula = mapearRequest(request);

        return validarRequest(() -> {
            if (id == null || id.toString().trim().isEmpty()) {
                throw new NullOrBlankException("El id de la película no puede estar vacío o no definido.");
            }
            if (pelicula.getNombre() == null || pelicula.getNombre().isBlank()) {
                throw new NullOrBlankException("El nombre de la película no puede estar vacío.");
            }
            if (pelicula.getDuracion() == null || pelicula.getDuracion() <= 0 ) {
                throw new NullOrBlankException("La duración de la película debe ser un valor positivo.");
            }
            Optional<PeliculaModel> existePelicula = peliculaRepository.findById(id);
            if (existePelicula.isEmpty()) {
                throw new LocalNotFountException("No se encontró la película con el id: " + id);
            }
            pelicula.setIdPelicula(id);
            PeliculaModel peliculaGuardada = peliculaRepository.save(pelicula);
            return List.of(peliculaGuardada);
        }, "Película editada exitosamente.");
    }

    public GenericResponse deletePelicula(Long id){
        return validarRequest(() -> {
            PeliculaModel pelicula = peliculaRepository.findById(id).orElse(null);

            if (pelicula != null) {
                pelicula.setEstado(0); // 0= Inactivo
                peliculaRepository.save(pelicula);
            } else {
                throw new LocalNotFountException("Pelicula no encontrada con id: " + id);
            }
            return List.of(pelicula);
        }, "Película eliminada exitosamente.");
    }

    private PeliculaModel mapearRequest (GenericRequest request) {
        Map<String,Object> body = request.getBody();
        PeliculaModel pelicula = new PeliculaModel();
        pelicula.setNombre((String) body.get("nombre"));
        pelicula.setDuracion(((Number) body.get("duracion")).doubleValue());
        pelicula.setEstado((Integer) body.get("estado"));
        return pelicula;
    }

    private <T> GenericResponse validarRequest(Supplier<T> operacion, String mensaje) {
        try {
            if (peliculaRepository == null) {
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


}
