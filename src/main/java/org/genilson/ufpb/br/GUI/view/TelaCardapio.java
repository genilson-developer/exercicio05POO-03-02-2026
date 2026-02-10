package org.genilson.ufpb.br.GUI.view;

import org.genilson.ufpb.br.GUI.control.Cardapio;
import org.genilson.ufpb.br.GUI.model.Refeicao;

import javax.swing.*;
import java.awt.*;

public class TelaCardapio extends JFrame {

    private JTextField txtNome, txtDescricao, txtPreco;
    private DefaultListModel<Refeicao> modeloLista;
    private Cardapio cardapio;

    public TelaCardapio() {
        cardapio = new Cardapio();

        setTitle("Cadastro de Refeições");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(4, 2));

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painelForm.add(txtDescricao);

        painelForm.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        painelForm.add(txtPreco);

        JButton btnCadastrar = new JButton("Cadastrar");
        painelForm.add(btnCadastrar);

        add(painelForm, BorderLayout.NORTH);

        // Lista de refeições
        modeloLista = new DefaultListModel<>();
        JList<Refeicao> lista = new JList<>(modeloLista);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        // Ação do botão
        btnCadastrar.addActionListener(e -> cadastrar());

        setVisible(true);
    }

    private void cadastrar() {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        double preco = Double.parseDouble(txtPreco.getText());

        Refeicao r = new Refeicao(nome, descricao, preco);
        cardapio.adicionarRefeicao(r);
        modeloLista.addElement(r);

        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
    }

    public static void main(String[] args) {
        new TelaCardapio();
    }
}
