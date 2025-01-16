package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.helpers.ValidacionByFecha;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ValidacionByFechaImpl implements ValidacionByFecha {

    @Override
    public void validarFecha(List<?> lista) {
        if (lista == null || lista.isEmpty()) {
            throw new LocalNotFountException("No se encontraron registros con la fecha indicada.");
        }
    }

}
