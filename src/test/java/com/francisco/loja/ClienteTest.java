package com.francisco.loja;

import com.francisco.loja.modelo.Carrinho;
import com.francisco.loja.modelo.Produto;
import com.francisco.loja.modelo.Projeto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTest {

    private HttpServer server;
    private WebTarget target;
    private Client client;

    @BeforeEach
    public void startaServidor() {
        server = Servidor.inicializaServidor();
        ClientConfig config = new ClientConfig();
        config.register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.OFF, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192));
        this.client = ClientBuilder.newClient(config);
        this.target = client.target("http://localhost:8080");
    }

    @AfterEach
    public void mataServidor() {
        server.shutdownNow();
    }

    @Test
    public void testaQueAConexaoComOServidorFunciona() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.mocky.io");
        String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
        assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
    }

    @Test
    public void testaQueBuscarUmProjetoTrazOProjetoEsperado() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
        assertEquals("Minha loja", projeto.getNome());
    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {

        Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
        assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
    }

    @Test
    public void testaQueSuportaNovosCarrinhos() {

        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314, "Microfone", 37, 1));
        carrinho.setRua("Rua Vergueiro 3185");
        carrinho.setCidade("São Paulo");
        Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");

        Carrinho carrinhoCarregado = client.target(location).request().get(Carrinho.class);
        assertEquals("Microfone", carrinhoCarregado.getProdutos().get(0).getNome());
    }

    @Test
    public void testaQueSuportaNovosProjetos() {

        Projeto projeto = new Projeto();
        projeto.setNome("Correr");
        projeto.setAnoDeInicio(2021);
        Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);

        Response response = target.path("/projetos").request().post(entity);
        assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");
        Projeto projetoCarregado = client.target(location).request().get(Projeto.class);
        assertEquals("Correr", projetoCarregado.getNome());
    }
}
