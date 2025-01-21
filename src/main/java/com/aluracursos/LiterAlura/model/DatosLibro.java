package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonAlias("title") String titulo,
                    @JsonAlias("authors") List<Authors> autores,
                    @JsonAlias("languages") List<String> lenguajes,
                    @JsonAlias("download_count") int descargas) {

    public DatosLibro {
        lenguajes = lenguajes != null ? new ArrayList<>(lenguajes) : new ArrayList<>();
        autores = autores != null ? new ArrayList<>(autores) : new ArrayList<>();
    }

    // Override del getter para asegurar que devuelve una lista mutable
    @Override
    public List<String> lenguajes() {
        return new ArrayList<>(lenguajes);
    }

    @Override
    public List<Authors> autores() {
        return new ArrayList<>(autores);
    }
}
