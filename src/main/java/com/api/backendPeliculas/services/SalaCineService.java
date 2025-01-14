package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.SalaCineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class SalaCineService {

    @Autowired
    private SalaCineRepository salaCineRepository;

    public GenericResponse createSalaCine(GenericRequest request) {

        SalaCineModel salaCine = mapearRequest(request);

        return validarRequest(() -> {
            if (salaCine.getNombre() == null || salaCine.getNombre().isBlank()) {
                throw new LocalNotFountException("El nombre de la sala de cine no puede estar vacío.");
            }
            if (salaCine.getEstado() == null || salaCine.getEstado() <= 0 ) {
                throw new LocalNotFountException("El estado de la sala de cine debe ser un valor positivo.");
            }
            SalaCineModel salaCineGuardada = salaCineRepository.save(salaCine);
            return List.of(salaCineGuardada);
        }, "Sala de cine guardada exitosamente.");
    }

    public GenericResponse getAllSalaCineActiva() {
        return validarRequest(() -> {
            List<SalaCineModel> salasCine = salaCineRepository.findActiveSalaCine();
            return salasCine;
        }, "Salas de cine obtenidas exitosamente.");
    }

    public GenericResponse getSalaCineById(Long id) {
        return validarRequest(() -> {
            Optional<SalaCineModel> salaCine = salaCineRepository.findById(id);
            return salaCine;
        }, "Sala de cine obtenida exitosamente.");
    }

    public GenericResponse updateSalaCine(GenericRequest request, Long id){

        SalaCineModel salaCine = mapearRequest(request);

        return validarRequest(() -> {
            if (salaCine.getNombre() == null || salaCine.getNombre().isBlank()) {
                throw new LocalNotFountException("El nombre de la sala de cine no puede estar vacío.");
            }
            if (salaCine.getEstado() == null || salaCine.getEstado() <= 0 ) {
                throw new LocalNotFountException("El estado de la sala de cine debe ser un valor positivo.");
            }

            salaCine.setIdSalaCine(id);
            Optional<SalaCineModel> existeSalaCine = salaCineRepository.findById(id);
            if (existeSalaCine.isEmpty()) {
                throw new LocalNotFountException("No se encontró la sala cine con el id: " + id);
            }

            SalaCineModel salaCineGuardada = salaCineRepository.save(salaCine);
            return List.of(salaCineGuardada);
        }, "Sala de cine actualizada exitosamente.");
    }

    public GenericResponse deleteSalaCine(Long id){
        return validarRequest(() -> {
            SalaCineModel salaCine = salaCineRepository.findById(id).orElse(null);

            if (salaCine != null) {
                salaCine.setEstado(0); // 0= Inactivo
                salaCineRepository.save(salaCine);
            } else {
                throw new LocalNotFountException("Sala Cine no encontrada con id: " + id);
            }
            return List.of(salaCine);
        }, "Sala de cine eliminada exitosamente.");
    }

    private <T> GenericResponse validarRequest(Supplier<T> operacion, String mensaje) {
        try {
            if (salaCineRepository == null) {
                throw new LocalNotFountException("Repositorio de salas cine no disponible.");
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

    private SalaCineModel mapearRequest (GenericRequest request) {
        Map<String,Object> body = request.getBody();
        SalaCineModel salaCine = new SalaCineModel();
        salaCine.setNombre((String) body.get("nombre"));
        salaCine.setEstado((Integer) body.get("estado"));
        return salaCine;
    }

}
