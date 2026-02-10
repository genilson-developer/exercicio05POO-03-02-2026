package org.genilson.ufpb.br.GUI.model;

public class Refeicao {

    private String nome;
    private String descricao;
    private double preco;

    public Refeicao(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
}

