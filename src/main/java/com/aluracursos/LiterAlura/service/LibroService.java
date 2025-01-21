package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.dto.LibroDTO;
import com.aluracursos.LiterAlura.model.Authors;
import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public List<LibroDTO> listarLibrosRegistrados(){
        List<Libro> libros = libroRepository.findAll();
        // Asegúrate de inicializar las colecciones relacionadas
        libros.forEach(libro -> libro.getLenguajes().size());
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public void guardarLibro(Libro libro){
        libroRepository.save(libro);
    }

    public List<LibroDTO> convertirListaADTO(List<Libro> libros) {
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public LibroDTO convertirADTO(Libro libro) {
        List<String> nombresAutores = libro.getAutores().stream()
                .map(Authors::getName)
                .collect(Collectors.toList());

        return new LibroDTO(
                libro.getTitulo(),
                nombresAutores,
                libro.getLenguajes(),
                libro.getDescargas()
        );
    }
}
