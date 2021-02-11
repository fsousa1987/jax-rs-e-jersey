package com.francisco.loja.resource;

import com.francisco.loja.dao.CarrinhoDAO;
import com.francisco.loja.modelo.Carrinho;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("carrinhos")
public class CarrinhoResource {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String busca(@PathParam("id") long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        return carrinho.toXML();
    }
}
