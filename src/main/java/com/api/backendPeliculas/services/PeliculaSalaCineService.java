package com.api.backendPeliculas.services;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.jsonDynamic.GenericRequest;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.repository.PeliculaSalaCineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public interface PeliculaSalaCineService {



    GenericResponse savePeliculaSalaCine(PeliculaSalaCineModel peliculaSalaCine);

    ResponseEntity<GenericResponse> getAllPeliculasSalaCine();

    List<PeliculaSalaCineModel> getPeliculasPorFecha(Date fecha);

    List<PeliculaSalaCineModel> getPeliculasPorNombreYIdSala(String nombrePelicula, Long idSalaCine);

    List<PeliculaSalaCineModel> getPeliculasPorNombreSala(String nombreSalaCine);
}
