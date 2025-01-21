package com.aluracursos.LiterAlura.controller;

import com.aluracursos.LiterAlura.dto.LibroDTO;
import com.aluracursos.LiterAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibroController {

    @Autowired
    private LibroService servicio;

    @GetMapping("/libros")
    public List<LibroDTO> listarLibrosRegistrados(){
        return servicio.listarLibrosRegistrados();
    }


}
