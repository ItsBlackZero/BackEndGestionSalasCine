package com.api.backendPeliculas.controllers;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.services.SalaCineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala-cine")
@CrossOrigin(origins = "http://localhost:4200")
public class SalaCineController {

    @Autowired
    private SalaCineService salaCineService;

    @GetMapping
    public List<SalaCineModel> getAllSalaCineActivas(){
        return salaCineService.getAllSalaCineActiva();
    }

    @PostMapping
    public SalaCineModel saveSalaCine(@Valid @RequestBody SalaCineModel salaCine){
        return salaCineService.createSalaCine(salaCine);
    }

    @PutMapping("/editar/{id}")
    public SalaCineModel editarSalaCine(@Valid @RequestBody SalaCineModel salaCine, @PathVariable Long id){
        salaCine.setIdSalaCine(id);
        return salaCineService.updateSalaCine(salaCine);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarSalaCine(@PathVariable Long id){
        salaCineService.deleteSalaCine(id);
    }
}
