package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface SalaCineService {


    GenericResponse createSalaCine(SalaCineModel salaCine);

    ResponseEntity<GenericResponse> getAllSalaCineActiva();

    GenericResponse getSalaCineById(Long id);

    GenericResponse updateSalaCine(SalaCineModel salaCine);

    GenericResponse deleteSalaCine(SalaCineModel salaCine);

}
