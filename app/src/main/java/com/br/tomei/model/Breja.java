package com.br.tomei.model;

import java.io.Serializable;

public class Breja implements Serializable {

    private String id;
    private String nome;
    private String tipo;
    private String fabricante;
    private String descricao;
    private String valor;
    private float avaliacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Breja() {

    }

    public Breja(String id, String nome, String tipo, String fabricante, String descricao, String valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Breja(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Breja(String id, String nome, String tipo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Breja(String id, String nome, String tipo, String descricao, float avaliacao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Breja{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}