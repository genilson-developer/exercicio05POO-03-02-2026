package org.genilson.ufpb.br;

import java.io.Serializable;
import java.util.Objects;

public class Contato implements Serializable {

    private String nome;
    private int diaAniversario;
    private int mesAniversario;

    public Contato(String nome, int dia, int mes) {
        this.nome = nome;
        this.diaAniversario = dia;
        this.mesAniversario = mes;
    }

    public String getNome() {
        return nome;
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(int dia) {
        this.diaAniversario = dia;
    }

    public int getMesAniversario() {
        return mesAniversario;
    }

    public void setMesAniversario(int mes) {
        this.mesAniversario = mes;
    }

    @Override
    public String toString() {
        return nome + " - " + diaAniversario + "/" + mesAniversario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato)) return false;
        Contato contato = (Contato) o;
        return nome.equals(contato.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
