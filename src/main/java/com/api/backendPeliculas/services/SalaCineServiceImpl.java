package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.SalaCineRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class SalaCineServiceImpl implements SalaCineService{

    @Autowired
    private SalaCineRepository salaCineRepository;

    public GenericResponse createSalaCine(SalaCineModel salaCine) {
        SalaCineModel salaCineGuardada = salaCineRepository.save(salaCine);
        return new GenericResponse("success", "Sala de cine guardada exitosamente.", List.of(salaCineGuardada));
    }
    public ResponseEntity<GenericResponse> getAllSalaCineActiva() {
        List<SalaCineModel> salasCine = salaCineRepository.findActiveSalaCine();
        GenericResponse response = new GenericResponse("success", "Salas de cine obtenidas exitosamente.", salasCine);
        return ResponseEntity.ok(response);
    }
    public GenericResponse getSalaCineById(Long id) {
        SalaCineModel salaCine = salaCineRepository.findById(id).orElse(null);
        return new GenericResponse("success", "Sala de cine obtenida exitosamente.", salaCine);
    }
    public GenericResponse updateSalaCine(SalaCineModel salaCine){
        SalaCineModel salaEditada = salaCineRepository.save(salaCine);
        return new GenericResponse("success", "Sala de cine editada exitosamente.", List.of(salaEditada));
    }
    public GenericResponse deleteSalaCine(SalaCineModel salaCine){
        SalaCineModel salaCineEliminada = salaCineRepository.save(salaCine);
        return new GenericResponse("success", "Sala de cine eliminada exitosamente.", List.of(salaCineEliminada));
    }
}
