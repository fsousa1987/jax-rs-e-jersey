package com.francisco.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

    private long id;
    private String nome;
    private int anoDeInicio;

    public Projeto() {
    }

    public Projeto(long id, String nome, int anoDeInicio) {
        this.id = id;
        this.nome = nome;
        this.anoDeInicio = anoDeInicio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeInicio(int anoDeInicio) {
        this.anoDeInicio = anoDeInicio;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDeInicio() {
        return anoDeInicio;
    }

    public String toXML() {
        return new XStream().toXML(this);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
