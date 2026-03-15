package org.genilson.ufpb.br.GUI.persistencia;

import java.io.*;

public class GravadorDeDados {

    public void gravarDados(Object dados, String arquivo) throws IOException {

        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(arquivo));

        out.writeObject(dados);
        out.close();
    }

    public Object recuperarDados(String arquivo) throws IOException {

        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(arquivo));

            Object dados = in.readObject();
            in.close();

            return dados;

        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}