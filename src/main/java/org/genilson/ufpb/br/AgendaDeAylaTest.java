package org.genilson.ufpb.br;

public class AgendaDeAylaTest {

    public static void main(String[] args) {

        Agenda agenda = new AgendaDeAyla();

        // Cadastro
        agenda.cadastraContato("Ana", 10, 5);
        agenda.cadastraContato("João", 10, 5);
        agenda.cadastraContato("Maria", 20, 8);

        // Pesquisa
        System.out.println("Aniversariantes em 10/5:");
        for (Contato c : agenda.pesquisaAniversariantes(10, 5)) {
            System.out.println(c);
        }

        // Remoção
        try {
            agenda.removeContato("Ana");
        } catch (ContatoInexistenteException e) {
            e.printStackTrace();
        }
    }
}

