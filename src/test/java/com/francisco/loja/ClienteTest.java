package com.francisco.loja;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTest {

    @Test
    public void testaQueAConexaoComOServidorFunciona() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.mocky.io");
        String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
        System.out.println(conteudo);
        assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
    }
}
