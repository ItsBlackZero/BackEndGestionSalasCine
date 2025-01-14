package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.SalaCineService;
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

    @GetMapping
    public ResponseEntity<GenericResponse> getAllSalaCineActivas(){
        GenericResponse response = salaCineService.getAllSalaCineActiva();
        return retornarResponse(response);
    }

    @PostMapping
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {"body": {"nombre": "string","estado": 1}}
                                            """
                            )
                    )
            )
    )
    public ResponseEntity<GenericResponse> saveSalaCine(@RequestBody GenericRequest request){
        GenericResponse response = salaCineService.createSalaCine(request);
        return retornarResponse(response);
    }

    @PutMapping("/editar/{id}")
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {"body": {"nombre": "string","estado": 1}}
                                            """
                            )
                    )
            )
    )
    public ResponseEntity<GenericResponse> editarSalaCine(@RequestBody GenericRequest request, @PathVariable Long id){

        GenericResponse response = salaCineService.updateSalaCine(request,id);
        return retornarResponse(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse> eliminarSalaCine(@PathVariable Long id){
        GenericResponse response = salaCineService.deleteSalaCine(id);
        return retornarResponse(response);
    }


    private ResponseEntity<GenericResponse> retornarResponse (GenericResponse response) {
        if ("error".equals(response.getStatus())) {
            return ResponseEntity.status(500).body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }



}
