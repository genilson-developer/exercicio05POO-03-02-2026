package org.genilson.ufpb.br.GUI.control;

import org.genilson.ufpb.br.GUI.model.Refeicao;

import java.util.*;
import java.util.stream.Collectors;

public class SistemaCardapioMap implements SistemaCardapio {

    private Map<String, Refeicao> refeicoes;

    public SistemaCardapioMap() {
        this.refeicoes = new HashMap<>();
    }

    @Override
    public void cadastrarRefeicao(String nome, String descricao, double preco) {
        Refeicao r = new Refeicao(nome, descricao, preco);
        refeicoes.put(nome, r);
    }

    @Override
    public Collection<Refeicao> pesquisarRefeicaoPorNome(String nome) {

        return refeicoes.values()
                .stream()
                .filter(r -> r.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Refeicao> pesquisarRefeicoesBaratas(double precoMax) {

        return refeicoes.values()
                .stream()
                .filter(r -> r.getPreco() <= precoMax)
                .collect(Collectors.toList());
    }

    @Override
    public void removerRefeicao(String nome) {
        refeicoes.remove(nome);
    }

    @Override
    public Collection<Refeicao> listarRefeicoes() {
        return refeicoes.values();
    }

    @Override
    public double calcularPrecoMedio() {

        return refeicoes.values()
                .stream()
                .mapToDouble(Refeicao::getPreco)
                .average()
                .orElse(0);
    }

    @Override
    public boolean existeRefeicao(String nome) {
        return refeicoes.containsKey(nome);
    }

    public Map<String, Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(Map<String, Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }
}