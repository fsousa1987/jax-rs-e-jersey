package com.francisco.loja;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Servidor {

    public static void main(String[] args) throws IOException {
        HttpServer server = inicializaServidor();
        System.out.println("Servidor rodando");
        //noinspection ResultOfMethodCallIgnored
        System.in.read();
        server.shutdownNow();
    }

    static HttpServer inicializaServidor() {
        ResourceConfig config = new ResourceConfig().packages("com.francisco.loja");
        URI uri = URI.create("http://localhost:8080/");
        return GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }
}
