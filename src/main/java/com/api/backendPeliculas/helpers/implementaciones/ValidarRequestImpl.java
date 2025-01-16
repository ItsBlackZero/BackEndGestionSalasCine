package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.exception.LocalNotFountException;
import com.api.backendPeliculas.helpers.ValidarRequestHelper;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ValidarRequestImpl implements ValidarRequestHelper {

    @Override
    public <T> GenericResponse validarRequest(Supplier<T> operacion, String mensaje) {
        try {
            T result = operacion.get();
            if (result instanceof List && ((List<?>) result).isEmpty()) {
                throw new LocalNotFountException("No se encontraron registros.");
            }
            return new GenericResponse("success", mensaje, result);
        } catch (LocalNotFountException ex) {
            return new GenericResponse("error", ex.getMessage(), null);
        } catch (Exception ex) {
            return new GenericResponse("error", "Error inesperado: " + ex.getMessage(), null);
        }
    }
}
