package com.aluracursos.LiterAlura.principal;

import com.aluracursos.LiterAlura.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private final String URL = "https://gutendex.com/books";
    private final Scanner escaner = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
        var menu ="""
                -----------------------------
                Elija la opción a traves de su número:
                1- Buscar libro por titulo
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        String libroBuscado = escaner.nextLine();
        System.out.println(URL + "/search=" + libroBuscado.replaceAll(" ", "%20"));
        String resultado = consumoAPI.obtenerDatos(URL + "/search=" + libroBuscado.replaceAll(" ", "%20"));
        System.out.println(resultado);
    }
}
