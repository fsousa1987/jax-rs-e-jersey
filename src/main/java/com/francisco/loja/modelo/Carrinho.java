package com.francisco.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private List<Produto> produtos = new ArrayList<>();
    private String rua;
    private String cidade;
    private long id;

    public Carrinho adiciona(Produto produto) {
        produtos.add(produto);
        return this;
    }

    public Carrinho para(String rua, String cidade) {
        this.rua = rua;
        this.cidade = cidade;
        return this;
    }

    public long getId() {
        return id;
    }

    public Carrinho setId(long id) {
        this.id = id;
        return this;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void remove(long id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public void troca(Produto produto) {
        remove(produto.getId());
        adiciona(produto);
    }

    public void trocaQuantidade(Produto produto) {
        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                p.setQuantidade(produto.getQuantidade());
                return;
            }
        }
    }

    public String toXML() {
        return new XStream().toXML(this);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
