package com.francisco.loja.resource;

import com.francisco.loja.dao.CarrinhoDAO;
import com.francisco.loja.modelo.Carrinho;
import com.francisco.loja.modelo.Produto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("carrinhos")
public class CarrinhoResource {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Carrinho busca(@PathParam("id") long id) {
        return new CarrinhoDAO().busca(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Carrinho carrinho) {
        new CarrinhoDAO().adiciona(carrinho);
        URI uri = URI.create("/carrinhos/" + carrinho.getId());
        return Response.created(uri).build();
    }

    @Path("{id}/produtos/{produtoId}")
    @DELETE
    public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        carrinho.remove(produtoId);
        return Response.ok().build();
    }

    @Path("{id}/produtos/{produtoId}/quantidade")
    @PUT
    public Response alteraProduto(Produto produto, @PathParam("id") long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }
}
