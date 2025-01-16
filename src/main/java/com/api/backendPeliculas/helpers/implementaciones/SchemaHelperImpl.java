package com.api.backendPeliculas.helpers.implementaciones;

import com.api.backendPeliculas.exception.FileNotFoundException;
import com.api.backendPeliculas.exception.JsonValidationException;
import com.api.backendPeliculas.helpers.SchemaHelper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;

@Service
public class SchemaHelperImpl implements SchemaHelper {

    private ArrayList<String> errorMenssage = new ArrayList<>();

    @Override
    public void validateJson(String peliculaJson, String schemaName) {
        try (InputStream schemaStream = getClass().getClassLoader().getResourceAsStream("jsonSchemas/" + schemaName + ".json")) {
            if (schemaStream == null) {
                throw new FileNotFoundException("No se pudo cargar el esquema JSON desde el archivo.");
            }
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(rawSchema);
            JSONObject jsonObject = new JSONObject(peliculaJson);
            schema.validate(jsonObject);
        } catch (ValidationException e) {
            throw new JsonValidationException("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado de validación: " + e.getMessage(), e);
        }
    }
}
