package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.entities.PeliculaModel;
import com.api.backendPeliculas.entities.SalaCineModel;
import com.api.backendPeliculas.helpers.BaseCrudHelper;
import com.api.backendPeliculas.helpers.SalaCineHelper;
import com.api.backendPeliculas.helpers.SchemaHelper;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.SalaCineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SalaCineHelperImpl extends BaseCrudHelper<SalaCineModel> implements SalaCineHelper {

    @Autowired
    private SalaCineService salaCineService;
    @Autowired
    private SchemaHelper schemaHelper;


    @Override
    protected GenericResponse saveEntity(SalaCineModel entity) {
        return salaCineService.createSalaCine(entity);
    }

    @Override
    protected GenericResponse editEntity(SalaCineModel entity) {
        return salaCineService.updateSalaCine(entity);
    }

    @Override
    protected GenericResponse getEntityById(Long id) {
        return salaCineService.getSalaCineById(id);
    }

    @Override
    protected void deleteEntity(SalaCineModel entity) {

    }

    @Override
    protected void setEntityId(SalaCineModel entity, Long id) {
        entity.setIdSalaCine(id);
    }

    @Override
    protected void setEntityStateInactive(SalaCineModel entity) {

    }
    @Override
    public ResponseEntity<GenericResponse> validarYGuardarSalaCine(SalaCineModel salaCine) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String peliculaJson = objectMapper.writeValueAsString(salaCine);
            schemaHelper.validateJson(peliculaJson,"salaCineJsonSchema");
            return ResponseEntity.ok(saveEntity(salaCine));
        } catch (Exception e) {
            throw new RuntimeException("Error al validar o guardar la película: " + e.getMessage(), e);
        }
    }
    @Override
    public ResponseEntity<GenericResponse> editarSalaCine(SalaCineModel salaCine, Long id) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String salaCineJson = objectMapper.writeValueAsString(salaCine);
            schemaHelper.validateJson(salaCineJson,"salaCineJsonSchema");
            return ResponseEntity.ok(editar(salaCine,id).getBody());
        } catch (Exception e) {
            throw new RuntimeException("Error al validar o guardar la película: " + e.getMessage(), e);
        }
    }
    @Override
    public ResponseEntity<GenericResponse> eliminarSalaCine(Long id) {
        return eliminar(id);
    }

}
