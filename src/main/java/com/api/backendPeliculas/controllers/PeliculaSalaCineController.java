package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.services.PeliculaSalaCineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    private PeliculaSalaCineService peliculaSalaCineService;

    @GetMapping
    public List<PeliculaSalaCineModel> getAllPeliculasSalaCine() {
        return peliculaSalaCineService.getAllPeliculasSalaCine();
    }


    @PostMapping
    public ResponseEntity<PeliculaSalaCineModel> savePelicula(@RequestBody PeliculaSalaCineModel peliculaSalaCine) {
        logger.info("Cuerpo recibido: {}", peliculaSalaCine);
        return ResponseEntity.ok(peliculaSalaCineService.savePeliculaSalaCine(peliculaSalaCine));
    }





    @GetMapping("/porFecha")
    public ResponseEntity<Object> getPeliculasPorFecha(
            @RequestParam("fecha")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha
    ) {
        List<PeliculaSalaCineModel> peliculas = peliculaSalaCineService.getPeliculasPorFecha(fecha);
        if (peliculas.isEmpty()) {
            return ResponseEntity.status(200).body(
                    Map.of("message", "No hay películas asignadas a esta fecha", "peliculas", new ArrayList<>())
            );
        }
        return ResponseEntity.ok(Map.of("message", "Películas encontradas", "peliculas", peliculas));
    }


    @GetMapping("/porNombreSala")
    public ResponseEntity<Object> getPeliculasPorNombreSala(@Valid @RequestParam("nombreSalaCine") String nombreSalaCine) {
        List<PeliculaSalaCineModel> peliculas = peliculaSalaCineService.getPeliculasPorNombreSala(nombreSalaCine);
        if (peliculas.isEmpty()) {
            return ResponseEntity.status(200).body(
                    Map.of("message", "No hay películas asignadas a esta sala", "peliculas", new ArrayList<>())
            );
        }
        return ResponseEntity.ok(Map.of("message", "Películas encontradas", "peliculas", peliculas));
    }

    @GetMapping("/porNombreYIdSala")
    public ResponseEntity<Object> getPeliculasPorNombreYIdSala(
            @RequestParam("nombrePelicula") String nombrePelicula,
            @RequestParam("idSalaCine") Long idSalaCine) {
        List<PeliculaSalaCineModel> peliculas = peliculaSalaCineService.getPeliculasPorNombreYIdSala(nombrePelicula, idSalaCine);
        if (peliculas.isEmpty()) {
            return ResponseEntity.status(200).body(
                    Map.of("message", "No hay películas asignadas para ese nombre y sala de cine", "peliculas", new ArrayList<>())
            );
        }
        return ResponseEntity.ok(Map.of("message", "Películas encontradas", "peliculas", peliculas));
    }



}
