package com.aluracursos.LiterAlura.principal;

import com.aluracursos.LiterAlura.model.Authors;
import com.aluracursos.LiterAlura.model.DatosLibro;
import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.model.LibroResponse;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.ConsumoAPI;
import com.aluracursos.LiterAlura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class Principal {
    private final String URL = "https://gutendex.com/books";
    private final Scanner escaner = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    @Autowired
    private LibroRepository repositorio;

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
        var menu ="""
                -----------------------------
                Elija la opción a traves de su número:
                1- Buscar libro por titulo
                2- Buscar libro por autor
                3- Listar libros registrados
                4- Listar autores registrados
                5- Listar autores vivos en un determinado año
                6- Listar libros por idioma
                0- Salir
                """;

            System.out.println(menu);
            int opcionElegida = escaner.nextInt();
            escaner.nextLine();

            switch (opcionElegida) {
                case 0:
                    System.out.println("Finalizando programa");
                    opcion = 0;
                    break;
                case 1:
                    buscarLibroPorNombre();
                    break;
                case 2:
                    buscarLibroPorAutor();
                    break;
                case 3:
                    listarNombresRegistrados();
                    break;
                case 4:
                    listarAutoresRegistrados();
                    break;
                case 5:
                    listarAutoresVivosEnDeterminadosAños();
                    break;
                case 6: 
                    listarLibrosPorIdioma();
                    break;
            }
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        String libroBuscado = escaner.nextLine();
        var json = consumoAPI.obtenerDatos(URL + "?search=" + libroBuscado.replaceAll(" ", "%20"));
        if (json == null || json.trim().isEmpty()) {
            System.out.println("No se encontraron resultados para el libro: " + libroBuscado);
            return;
        }
        LibroResponse datos = conversor.obtenerDatos(json, LibroResponse.class);
        DatosLibro primerLibro = datos.getResults().get(0);

        Libro libro = new Libro(primerLibro);

        //Comprobar si existe el libro en la base de datos
        if (repositorio.findByTitulo(libro.getTitulo()).isPresent()) {
            System.out.println("El libro ya existe en la base de datos");
            mostrarDatosLibro(repositorio.findByTitulo(libro.getTitulo()).get());
            return;
        }
        
        //Buscar autores existentes en la base de datos
    List<Authors> autoresExistentes = repositorio.findByAutores();
    Map<String, Authors> mapaAutoresExistentes = autoresExistentes.stream()
        .collect(Collectors.toMap(
            Authors::getName,
            author -> author,
            (autor1, autor2) -> autor1  // En caso de duplicados, mantener el primer autor
        ));

        // Actualizar la lista de autores del libro
        List<Authors> autoresActualizados = libro.getAutores().stream()
            .map(autor -> mapaAutoresExistentes.getOrDefault(autor.getName(), autor))
            .collect(toList());
    
        libro.setAutores(autoresActualizados);

        mostrarDatosLibro(libro);
        repositorio.save(libro);
        }
        
    private void buscarLibroPorAutor(){
        System.out.println("Ingresa el nombre del autor cuyos libros deseas buscar: ");
        String autorBuscado = escaner.nextLine();
        var json = consumoAPI.obtenerDatos(URL + "?search=" + autorBuscado.replaceAll(" ", "%20"));
        List <DatosLibro> datosLibros = conversor.obtenerDatos(json, LibroResponse.class).getResults();

        List<Libro> libros = datosLibros.stream()
        .map(datos -> new Libro(datos))
        .collect(toList());

        for(Libro libro : libros){
            mostrarDatosLibro(libro);
        }
    }
    
    private void listarNombresRegistrados(){
        List<Libro> libros = repositorio.findAll();
        libros.stream().forEach(l -> mostrarDatosLibro(l));
    }

    private void mostrarDatosLibro(Libro libro){
        System.out.println("\n-----------------------------------------\n");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autores: " + libro.getAutores().stream().map(Authors::getName).toList());
        System.out.println("Lenguajes: " + libro.getLenguajes());
        System.out.println("Total descargas: " + libro.getDescargas());
        System.out.println("\n-----------------------------------------");
    }

    private void listarAutoresRegistrados(){
        List<Authors> autores = repositorio.findByAutores();
        
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos");
            return;
        }

        autores.forEach(a ->{
            System.out.println("\n------AUTORES REGISTRADOS------");
            System.out.println("Nombre: " + a.getName());
            System.out.println("Año de nacimiento: " + a.getBirth_year());
            System.out.println("Año de fallecimiento: " + a.getDeath_year());
            System.out.println("\n-------------------------------");
        });
    }

    private void listarAutoresVivosEnDeterminadosAños(){
        System.out.println("Ingresa el año de inicio: ");
        int añoInicio = escaner.nextInt();
        System.out.println("Ingresa el año de fin: ");
        int añoFin = escaner.nextInt();
        List<Authors> autores = repositorio.findByAutores();
        autores.stream().filter(a -> a.getBirth_year() >= añoInicio && a.getDeath_year() <= añoFin).forEach(a -> {
            System.out.println("\n------AUTORES VIVOS EN EL PERIODO " + añoInicio + " - " + añoFin + "------");
            System.out.println("Nombre: " + a.getName());
            System.out.println("Año de nacimiento: " + a.getBirth_year());
            System.out.println("Año de fallecimiento: " + a.getDeath_year());
            System.out.println("\n-------------------------------");
        });
    };

    private void listarLibrosPorIdioma(){
        System.out.println("Ingresa el idioma del libro que deseas buscar: ");
        System.out.println("""
                1- es - Español
                2- en - Inglés
                3- fr - Francés
                4- de - Alemán
                5- it - Italiano
                6- pt - Portugués
                7- ru - Ruso
                8- zh - Chino
                """);
        int idiomaElegido = escaner.nextInt();
        String idioma = "";
        switch(idiomaElegido){
            case 1: idioma = "es"; break;
            case 2: idioma = "en"; break;
            case 3: idioma = "fr"; break;
            case 4: idioma = "de"; break;
            case 5: idioma = "it"; break;
            case 6: idioma = "pt"; break;
            case 7: idioma = "ru"; break;
            case 8: idioma = "zh"; break;
        }

        List<Libro> libros = repositorio.findByLenguajesContaining(idioma);
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados en la base de datos en ese idioma");
            return;
        }
        libros.forEach(l -> mostrarDatosLibro(l));
    };
}


