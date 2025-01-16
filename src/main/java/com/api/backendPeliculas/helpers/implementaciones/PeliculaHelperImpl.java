package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.exception.NullOrBlankException;
import com.api.backendPeliculas.helpers.BaseCrudHelper;
import com.api.backendPeliculas.helpers.PeliculaHelper;
import com.api.backendPeliculas.helpers.SchemaHelper;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.PeliculaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaHelperImpl extends BaseCrudHelper<PeliculaModel> implements PeliculaHelper{

    @Autowired
    private  PeliculaService peliculaService;
    @Autowired
    private  SchemaHelper schemaHelper;


    @Override
    protected GenericResponse saveEntity(PeliculaModel entity) {
        return peliculaService.savePelicula(entity);
    }

    @Override
    protected GenericResponse editEntity(PeliculaModel entity) {
        return peliculaService.editPelicula(entity);
    }

    @Override
    protected GenericResponse getEntityById(Long id) {
        return peliculaService.getPeliculaById(id);
    }

    @Override
    protected void deleteEntity(PeliculaModel entity) {
        peliculaService.deletePelicula(entity);
    }

    @Override
    protected void setEntityId(PeliculaModel entity, Long id) {
        entity.setIdPelicula(id);
    }

    @Override
    protected void setEntityStateInactive(PeliculaModel entity) {
        entity.setEstado(0);
    }

    @Override
    public ResponseEntity<GenericResponse> validarYGuardarPelicula(PeliculaModel pelicula) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String peliculaJson = objectMapper.writeValueAsString(pelicula);
            schemaHelper.validateJson(peliculaJson, "peliculaJsonSchema");
            return ResponseEntity.ok(saveEntity(pelicula));
        } catch (Exception e) {
            throw new RuntimeException("Error al validar o guardar la película: " + e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<GenericResponse> editarPelicula(PeliculaModel pelicula, Long id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String peliculaJson = objectMapper.writeValueAsString(pelicula);
            schemaHelper.validateJson(peliculaJson,"peliculaJsonSchema");
            return ResponseEntity.ok(editar(pelicula,id).getBody());
        } catch (Exception e) {
            throw new RuntimeException("Error al validar o guardar la película: " + e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<GenericResponse> eliminarPelicula(Long id) {
        return eliminar(id);
    }

}
