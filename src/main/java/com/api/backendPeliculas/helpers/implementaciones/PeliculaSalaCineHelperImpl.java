package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.entities.PeliculaSalaCineModel;
import com.api.backendPeliculas.helpers.BaseCrudHelper;
import com.api.backendPeliculas.helpers.PeliculaSalaCineHelper;
import com.api.backendPeliculas.helpers.SchemaHelper;
import com.api.backendPeliculas.helpers.ValidacionByFecha;
import com.api.backendPeliculas.jsonDynamic.GenericResponse;
import com.api.backendPeliculas.services.PeliculaSalaCineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PeliculaSalaCineHelperImpl extends BaseCrudHelper<PeliculaSalaCineModel> implements PeliculaSalaCineHelper {


    @Autowired
    private SchemaHelper schemaHelper;
    @Autowired
    private PeliculaSalaCineService peliculaSalaCineService;
    @Autowired
    private ValidacionByFecha validacionByFecha;

    @Override
    protected GenericResponse saveEntity(PeliculaSalaCineModel entity) {
        return peliculaSalaCineService.savePeliculaSalaCine(entity);
    }

    @Override
    protected GenericResponse editEntity(PeliculaSalaCineModel entity) {
        return null;
    }

    @Override
    protected GenericResponse getEntityById(Long id) {
        return null;
    }

    @Override
    protected void deleteEntity(PeliculaSalaCineModel entity) {

    }

    @Override
    protected void setEntityId(PeliculaSalaCineModel entity, Long id) {

    }

    @Override
    protected void setEntityStateInactive(PeliculaSalaCineModel entity) {

    }

    @Override
    public ResponseEntity<GenericResponse> validarYGuardarPelicula(PeliculaSalaCineModel peliculaSalaCineModel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String peliculaJson = objectMapper.writeValueAsString(peliculaSalaCineModel);
            schemaHelper.validateJson(peliculaJson, "salaCinePeliculaSchema");
            return ResponseEntity.ok(saveEntity(peliculaSalaCineModel));
        } catch (Exception e) {
            throw new RuntimeException("Error al validar o guardar la película: " + e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<GenericResponse> editarPelicula(PeliculaSalaCineModel peliculaSalaCine, Long id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            String peliculaJson = objectMapper.writeValueAsString(peliculaSalaCine);
            schemaHelper.validateJson(peliculaJson, "salaCinePeliculaSchema");
            return ResponseEntity.ok(editar(peliculaSalaCine,id).getBody());
        } catch (Exception e) {
            throw new RuntimeException("Error al validar o guardar la película: " + e.getMessage(), e);
        }
    }
    @Override
    public ResponseEntity<GenericResponse> eliminarPelicula(Long id) {
        return eliminar(id);
    }
    @Override
    public ResponseEntity<GenericResponse> obtenerPorFecha(Date fecha) {
        List<PeliculaSalaCineModel> peliculaSalaCineModel = peliculaSalaCineService.getPeliculasPorFecha(fecha);
        validacionByFecha.validarFecha(peliculaSalaCineModel);
        GenericResponse response = new GenericResponse("success", "Peliculas encontradas", peliculaSalaCineModel);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GenericResponse> obtenerPorNombreSala(String nombreSala) {
        List<PeliculaSalaCineModel> peliculaSalaCineModel = peliculaSalaCineService.getPeliculasPorNombreSala(nombreSala);
        validacionByFecha.validarFecha(peliculaSalaCineModel);
        GenericResponse response = new GenericResponse("success", "Peliculas encontradas", peliculaSalaCineModel);
        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<GenericResponse> obtenerPorNombreYIdSala(String nombreSala, Long idSalaCine){
        List<PeliculaSalaCineModel> peliculaSalaCineModel = peliculaSalaCineService.getPeliculasPorNombreYIdSala(nombreSala,idSalaCine);

        if(peliculaSalaCineModel.isEmpty()){
            GenericResponse response = new GenericResponse("error", "No se encontraron peliculas para esa sala", null);
            return ResponseEntity.status(404).body(response);
        }

        GenericResponse response = new GenericResponse("success", "Peliculas encontradas", peliculaSalaCineModel);
            return ResponseEntity.ok(response);
    }
}
