package org.genilson.ufpb.br.GUI.control;
import org.genilson.ufpb.br.GUI.model.Refeicao;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {

    private List<Refeicao> refeicoes = new ArrayList<>();

    public void adicionarRefeicao(Refeicao r) {
        refeicoes.add(r);
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }
}
