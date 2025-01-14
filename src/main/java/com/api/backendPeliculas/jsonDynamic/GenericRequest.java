package com.api.backendPeliculas.jsonDynamic;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public class GenericRequest {

    @Schema(description = "Cuerpo dinámico de la solicitud",
            example = "{ \"nombre\": \"Inception\", \"duracion\": 148, \"estado\": 1 }")
    private Map<String, Object> body;
    public GenericRequest() {
    }

    public GenericRequest(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
