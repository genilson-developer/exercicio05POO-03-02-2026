package org.genilson.ufpb.br.GUI.test;

import org.genilson.ufpb.br.GUI.control.SistemaCardapioMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaCardapioMapTest {

    @Test
    public void testCadastroPesquisaRemocao(){

        SistemaCardapioMap sistema = new SistemaCardapioMap();

        sistema.cadastrarRefeicao("Pizza","Pizza de queijo",30);
        sistema.cadastrarRefeicao("Hamburguer","Artesanal",25);

        assertTrue(sistema.existeRefeicao("Pizza"));

        assertEquals(2,sistema.listarRefeicoes().size());

        sistema.removerRefeicao("Pizza");

        assertFalse(sistema.existeRefeicao("Pizza"));
    }
}