package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import com.aluracursos.LiterAlura.model.Authors;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l from Libro l")
    List<Libro> listarLibrosRegistrados();

    @Query("SELECT l from Libro l WHERE l.titulo = :titulo")
    Optional<Libro> findByTitulo(@Param("titulo") String titulo);

    @Query("SELECT DISTINCT a FROM Authors a")
    List<Authors> findByAutores();

    @Query("SELECT l FROM Libro l JOIN l.lenguajes lang WHERE lang = :idioma")
    List<Libro> findByLenguajesContaining(@Param("idioma") String idioma);
}
