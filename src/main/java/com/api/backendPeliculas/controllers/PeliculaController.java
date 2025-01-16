package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.helpers.HandleErrorHelper;
import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.helpers.PeliculaHelper;
import com.api.backendPeliculas.helpers.SchemaHelper;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.PeliculaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private PeliculaHelper peliculaHelper;

    public static final String JSON_EJEMPLO = """
            {
                "nombre": "string",
                "duracion": "number",
                "estado": "number"
            }
            """;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPeliculas() {
        return peliculaService.getAllPeliculasActivas();
    }
    @PostMapping
    public ResponseEntity<GenericResponse> savePelicula(@Valid @RequestBody PeliculaModel pelicula) throws JsonProcessingException {
        return peliculaHelper.validarYGuardarPelicula(pelicula);
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
    public ResponseEntity<GenericResponse> editarPelicula(@Valid @RequestBody PeliculaModel pelicula,@PathVariable Long id) throws JsonProcessingException {
        return peliculaHelper.editarPelicula(pelicula, id);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<GenericResponse> eliminarPelicula(@PathVariable Long id){
        return peliculaHelper.eliminarPelicula(id);
    }
}
