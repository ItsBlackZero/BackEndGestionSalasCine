package com.api.backendPeliculas.helpers;

import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseCrudHelper<T>  implements  CrudHelper<T>{

    protected abstract GenericResponse saveEntity(T entity);


    protected abstract GenericResponse editEntity(T entity);

    protected abstract GenericResponse getEntityById(Long id);

    protected abstract void deleteEntity(T entity);

    @Override
    public ResponseEntity<GenericResponse> guardar(T entity) {
        GenericResponse response = saveEntity(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GenericResponse> editar(T entity, Long id) {
        GenericResponse response = getEntityById(id);

        if (response == null || response.getData() == null) {
            throw new NullOrBlankException("El recurso con el id: " + id + " no existe.");
        }
        Object data = response.getData();
        if (data instanceof List && ((List<?>) data).isEmpty()) {
            throw new NullOrBlankException("El recurso con el id: " + id + " no existe.");
        } else if (!(data instanceof List)) {
            if (data == null) {
                throw new NullOrBlankException("El recurso con el id: " + id + " no existe.");
            }
        }
        setEntityId(entity, id);
        GenericResponse editResponse = editEntity(entity);
        return ResponseEntity.ok(editResponse);
    }

    @Override
    public ResponseEntity<GenericResponse> eliminar(Long id) {
        GenericResponse response = getEntityById(id);

        if (response.getData() == null) {
            throw new NullOrBlankException("El recurso con el id: " + id + " no existe.");
        }
        T entity = (T) response.getData();
        setEntityStateInactive(entity);
        deleteEntity(entity);

        GenericResponse deleteResponse = new GenericResponse("success", "Recurso eliminado exitosamente.", null);
        return ResponseEntity.ok(deleteResponse);
    }

    protected abstract void setEntityId(T entity, Long id);

    protected abstract void setEntityStateInactive(T entity);
}
