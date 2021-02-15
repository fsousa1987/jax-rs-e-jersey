package com.francisco.loja.resource;

import com.francisco.loja.dao.ProjetoDAO;
import com.francisco.loja.modelo.Projeto;
import com.thoughtworks.xstream.XStream;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("projetos")
public class ProjetoResource {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String busca(@PathParam("id") long id) {
        Projeto projeto = new ProjetoDAO().busca(id);
        return projeto.toXML();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(String conteudo) {
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        new ProjetoDAO().adiciona(projeto);
        URI uri = URI.create("/projetos/" + projeto.getId());
        return Response.created(uri).build();
    }
}
