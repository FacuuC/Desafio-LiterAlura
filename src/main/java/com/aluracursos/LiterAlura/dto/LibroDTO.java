package com.aluracursos.LiterAlura.dto;

import java.util.List;

public class LibroDTO {
    private String titulo;
    private List<String> autores;
    private List<String> lenguajes;
    private Integer descargas;

    public LibroDTO() {
    }

    public LibroDTO(String titulo, List<String> autores, List<String> lenguajes, Integer descargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.lenguajes = lenguajes;
        this.descargas = descargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
}
