package org.genilson.ufpb.br.GUI.control;

import org.genilson.ufpb.br.GUI.model.Refeicao;

import java.util.Collection;

public interface SistemaCardapio {

    void cadastrarRefeicao(String nome, String descricao, double preco);

    Collection<Refeicao> pesquisarRefeicaoPorNome(String nome);

    Collection<Refeicao> pesquisarRefeicoesBaratas(double precoMax);

    void removerRefeicao(String nome);

    Collection<Refeicao> listarRefeicoes();

    double calcularPrecoMedio();

    boolean existeRefeicao(String nome);
}