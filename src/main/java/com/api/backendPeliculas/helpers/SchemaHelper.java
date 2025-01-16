package com.api.backendPeliculas.helpers;


import org.springframework.stereotype.Service;

public interface SchemaHelper {
    void validateJson(String peliculaJson, String schemaName);
}
