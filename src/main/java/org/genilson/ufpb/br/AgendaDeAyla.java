package org.genilson.ufpb.br;

import java.util.*;
import java.io.IOException;

public class AgendaDeAyla implements Agenda {

    private Map<String, Contato> contatos;
    private GravadorDeDados gravador;

    public AgendaDeAyla() {
        contatos = new HashMap<>();
        gravador = new GravadorDeDados();
    }

    @Override
    public boolean cadastraContato(String nome, int dia, int mes) {
        if (!contatos.containsKey(nome)) {
            contatos.put(nome, new Contato(nome, dia, mes));
            return true;
        }
        return false;
    }

    @Override
    public Collection<Contato> pesquisaAniversariantes(int dia, int mes) {
        Collection<Contato> resultado = new ArrayList<>();

        for (Contato c : contatos.values()) {
            if (c.getDiaAniversario() == dia &&
                    c.getMesAniversario() == mes) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    @Override
    public boolean removeContato(String nome)
            throws ContatoInexistenteException {

        if (!contatos.containsKey(nome)) {
            throw new ContatoInexistenteException(
                    "Contato não encontrado: " + nome);
        }
        contatos.remove(nome);
        return true;
    }

    @Override
    public void salvarDados() throws IOException {
        gravador.salvarContatos(contatos);
    }

    @Override
    public void recuperarDados() throws IOException {
        contatos = gravador.recuperarContatos();
    }

}
