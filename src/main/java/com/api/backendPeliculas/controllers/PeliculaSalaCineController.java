package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.PeliculaSalaCineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pelicula-sala-cine")
@CrossOrigin(origins = "http://localhost:4200")

public class PeliculaSalaCineController {

    private static final Logger logger = LoggerFactory.getLogger(PeliculaController.class);

    public static final String JSON_EJEMPLO = """
            {
              "body": {
                "pelicula": {
                  "idPelicula": 0
                },
                "salaCine": {
                  "idSalaCine": 0
                },
                "fechaPublicacion": "2025-01-14",
                "fechaFin": "2025-01-14"
              }
            }
            """;

    @Autowired
    private PeliculaSalaCineService peliculaSalaCineService;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPeliculasSalaCine() {
        GenericResponse response = peliculaSalaCineService.getAllPeliculasSalaCine();
        return retornarResponse(response);
    }

    @PostMapping
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
    public ResponseEntity<GenericResponse> savePelicula(@RequestBody GenericRequest request) throws ParseException {
        logger.info("Cuerpo recibido: {}", request);
        GenericResponse response = peliculaSalaCineService.savePeliculaSalaCine(request);
        return retornarResponse(response);
    }

    @GetMapping("/porFecha")
    public ResponseEntity<GenericResponse> getPeliculasPorFecha(
            @RequestParam("fecha")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha
    ) {
        GenericResponse response = peliculaSalaCineService.getPeliculasPorFecha(fecha);
        return retornarResponse(response);
    }

    @GetMapping("/porNombreSala")
    public ResponseEntity<GenericResponse> getPeliculasPorNombreSala( @RequestParam("nombreSalaCine") String nombreSalaCine) {
        GenericResponse response = peliculaSalaCineService.getPeliculasPorNombreSala(nombreSalaCine);
        return retornarResponse(response);
    }

    @GetMapping("/porNombreYIdSala")
    public ResponseEntity<GenericResponse> getPeliculasPorNombreYIdSala(
            @RequestParam("nombrePelicula") String nombrePelicula,
            @RequestParam("idSalaCine") Long idSalaCine) {
        GenericResponse response = peliculaSalaCineService.getPeliculasPorNombreYIdSala(nombrePelicula, idSalaCine);
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
