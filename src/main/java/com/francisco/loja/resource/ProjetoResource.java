package com.francisco.loja.resource;

import com.francisco.loja.dao.ProjetoDAO;
import com.francisco.loja.modelo.Projeto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("projetos")
public class ProjetoResource {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Projeto busca(@PathParam("id") long id) {
        return new ProjetoDAO().busca(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Projeto projeto) {
        new ProjetoDAO().adiciona(projeto);
        URI uri = URI.create("/projetos/" + projeto.getId());
        return Response.created(uri).build();
    }

    @Path("{id}")
    @DELETE
    public Response removeProjeto(@PathParam("id") long id) {
        new ProjetoDAO().remove(id);
        return Response.ok().build();
    }
}
