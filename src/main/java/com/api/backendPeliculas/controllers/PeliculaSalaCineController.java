package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.helpers.PeliculaSalaCineHelper;
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
              
                "pelicula": {
                  "idPelicula": 0
                },
                "salaCine": {
                  "idSalaCine": 0
                },
                "fechaPublicacion": "2025-01-14",
                "fechaFin": "2025-01-14"
              
            }
            """;

    @Autowired
    private PeliculaSalaCineService peliculaSalaCineService;

    @Autowired
    private PeliculaSalaCineHelper peliculaSalaCineHelper;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllPeliculasSalaCine() {
        return peliculaSalaCineService.getAllPeliculasSalaCine();
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
    public ResponseEntity<GenericResponse> savePelicula(@Valid @RequestBody PeliculaSalaCineModel peliculaSalaCineModel) throws ParseException {
        return peliculaSalaCineHelper.validarYGuardarPelicula(peliculaSalaCineModel);
    }

    @GetMapping("/porFecha")
    public ResponseEntity<GenericResponse> getPeliculasPorFecha(@Valid
            @RequestParam("fecha")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha
    ) {
        return peliculaSalaCineHelper.obtenerPorFecha(fecha);
    }
    @GetMapping("/porNombreSala")
    public ResponseEntity<GenericResponse> getPeliculasPorNombreSala(@Valid @RequestParam("nombreSalaCine") String nombreSalaCine) {
        return peliculaSalaCineHelper.obtenerPorNombreSala(nombreSalaCine);
    }
    @GetMapping("/porNombreYIdSala")
    public ResponseEntity<GenericResponse> getPeliculasPorNombreYIdSala(
            @RequestParam("nombrePelicula") String nombrePelicula,
            @RequestParam("idSalaCine") Long idSalaCine) {
        return peliculaSalaCineHelper.obtenerPorNombreYIdSala(nombrePelicula, idSalaCine);
    }
}
