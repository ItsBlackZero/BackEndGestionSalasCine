package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pelicula")
@CrossOrigin(origins = "http://localhost:4200")

public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    public static final String JSON_EJEMPLO = """
            {
              "body": {
                "nombre": "string",
                "duracion": "number",
                "estado": "number"
              }
            }
            """;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPeliculas() {
        GenericResponse response = peliculaService.getAllPeliculasActivas();
        return retornarResponse(response);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> savePelicula(@RequestBody GenericRequest request){

        GenericResponse response = peliculaService.savePelicula(request);
        return retornarResponse(response);
    }

    @PutMapping("/editar/{id}")
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = JSON_EJEMPLO
                            )
                    )
            )
    )
    public ResponseEntity<GenericResponse> editarPelicula(@RequestBody GenericRequest request, @PathVariable Long id){
        GenericResponse response = peliculaService.editPelicula(request, id);
        return retornarResponse(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse> eliminarPelicula(@PathVariable Long id){
        GenericResponse response = peliculaService.deletePelicula(id);
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
