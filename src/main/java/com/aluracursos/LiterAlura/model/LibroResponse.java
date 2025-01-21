package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroResponse {
    private List<DatosLibro> results;

    public List<DatosLibro> getResults() {
        return results;
    }
}
