package com.aluracursos.LiterAlura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos (String url){
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)  // Habilitamos seguimiento de redirecciones
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Si el código de estado es 200 (OK), devolver el cuerpo de la respuesta
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Error en la respuesta de la API: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al realizar la solicitud: " + e.getMessage(), e);
        }
    }
}
