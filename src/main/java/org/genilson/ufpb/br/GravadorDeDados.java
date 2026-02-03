package org.genilson.ufpb.br;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class GravadorDeDados {

    private static final String ARQUIVO_CONTATOS = "contatos.dat";

    public void salvarContatos(Map<String, Contato> contatos)
            throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO_CONTATOS));
        oos.writeObject(contatos);
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Contato> recuperarContatos()
            throws IOException {

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_CONTATOS));
            Map<String, Contato> contatos =
                    (HashMap<String, Contato>) ois.readObject();
            ois.close();
            return contatos;

        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
