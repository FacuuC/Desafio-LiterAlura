package com.aluracursos.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "titulo")
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_authors",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Authors> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "libro_lenguajes",
        joinColumns = @JoinColumn(name = "libro_id")
    )
    @Column(name = "lenguaje")
    private List<String> lenguajes = new ArrayList<>();

    @Column(name = "descargas")
    private Integer descargas;

    public Libro(){
        this.autores = new ArrayList<>();
        this.lenguajes = new ArrayList<>();
    }

    public Libro (DatosLibro d){
        this.titulo = d.titulo();
        this.autores = new ArrayList<>(d.autores());
        this.lenguajes = new ArrayList<>(d.lenguajes());
        this.descargas = d.descargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Authors> getAutores() {
        return autores;
    }

    public void setAutores(List<Authors> autores) {
        this.autores = autores != null ? autores : new ArrayList<>();
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = new ArrayList<>(lenguajes);
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
}
