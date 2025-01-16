package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.helpers.SalaCineHelper;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.SalaCineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala-cine")
@CrossOrigin(origins = "http://localhost:4200")
public class SalaCineController {

    @Autowired
    private SalaCineService salaCineService;
    @Autowired
    private SalaCineHelper salaCineHelper;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllSalaCineActivas(){
       return salaCineService.getAllSalaCineActiva();
    }

    @PostMapping
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                            "nombre": "string",
                                            "estado": 1
                                            }
                                            """
                            )
                    )
            )
    )
    public ResponseEntity<GenericResponse> saveSalaCine(@Valid @RequestBody SalaCineModel salaCine) throws JsonProcessingException {
        return salaCineHelper.validarYGuardarSalaCine(salaCine);
    }

    @PutMapping("/editar/{id}")
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                            "nombre": "string",
                                            "estado": 1
                                            }
                                            """
                            )
                    )
            )
    )
    public ResponseEntity<GenericResponse> editarSalaCine(@Valid @RequestBody SalaCineModel salaCine, @PathVariable Long id) throws JsonProcessingException {
        return salaCineHelper.editarSalaCine(salaCine, id);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse> eliminarSalaCine(@PathVariable Long id){
        return salaCineHelper.eliminarSalaCine(id);
    }

}
