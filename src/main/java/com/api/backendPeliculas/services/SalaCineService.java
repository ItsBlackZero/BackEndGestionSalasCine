package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.repository.SalaCineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaCineService {

    @Autowired
    private SalaCineRepository salaCineRepository;

    public SalaCineModel createSalaCine(SalaCineModel salaCine) {
        return salaCineRepository.save(salaCine);
    }

    public List<SalaCineModel> getAllSalaCineActiva() {
        return (List<SalaCineModel>) salaCineRepository.findActiveSalaCine();
    }
    public Optional<SalaCineModel> getSalaCineById(Long id) {
        return salaCineRepository.findById(id);
    }

    public SalaCineModel updateSalaCine(SalaCineModel salaCine) {
        return salaCineRepository.save(salaCine);
    }

    public void deleteSalaCine(Long id) {
        SalaCineModel salaCine = salaCineRepository.findById(id).orElse(null);
        if (salaCine != null) {
            salaCine.setEstado(0); // 0= Inactivo
            salaCineRepository.save(salaCine);
        } else {
            throw new RuntimeException("Sala Cine no encontrada con id: " + id);
        }
    }


}
