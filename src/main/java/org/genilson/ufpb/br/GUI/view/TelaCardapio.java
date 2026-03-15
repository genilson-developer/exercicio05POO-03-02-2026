package org.genilson.ufpb.br.GUI.view;

import org.genilson.ufpb.br.GUI.control.SistemaCardapioMap;
import org.genilson.ufpb.br.GUI.model.Refeicao;
import org.genilson.ufpb.br.GUI.persistencia.GravadorDeDados;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class TelaCardapio extends JFrame {

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private JTextField txtPesquisa;

    private DefaultListModel<Refeicao> modeloLista;
    private JList<Refeicao> listaRefeicoes;

    private SistemaCardapioMap sistema;
    private GravadorDeDados gravador;

    private static final String ARQUIVO = "cardapio.dat";

    public TelaCardapio() {
        sistema = new SistemaCardapioMap();
        gravador = new GravadorDeDados();

        setTitle("Sistema de Cardápio");
        setSize(700, 500); // Aumentei um pouco o tamanho para caber os novos botões
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        criarInterface();
        setVisible(true);
    }

    private void criarInterface(){
        // --- Formulário ---
        JPanel painelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painelForm.add(txtDescricao);

        painelForm.add(new JLabel("Preço (R$):"));
        txtPreco = new JTextField();
        painelForm.add(txtPreco);

        JButton btnCadastrar = new JButton("Cadastrar");
        painelForm.add(btnCadastrar);

        JButton btnRemover = new JButton("Remover Selecionado");
        painelForm.add(btnRemover);

        add(painelForm, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (Lista) ---
        modeloLista = new DefaultListModel<>();
        listaRefeicoes = new JList<>(modeloLista);
        add(new JScrollPane(listaRefeicoes), BorderLayout.CENTER);

        // --- PAINEL INFERIOR (Pesquisas e Arquivos) ---
        JPanel painelSul = new JPanel(new GridLayout(2, 1)); // Divide em duas linhas

        //Pesquisas e Média
        JPanel painelPesquisas = new JPanel(new FlowLayout());
        txtPesquisa = new JTextField(10);
        JButton btnPesquisar = new JButton("Pesquisar Nome");
        JButton btnPesquisarBaratas = new JButton("Pesquisar Baratas");
        JButton btnPrecoMedio = new JButton("Preço Médio");

        painelPesquisas.add(new JLabel("Nome:"));
        painelPesquisas.add(txtPesquisa);
        painelPesquisas.add(btnPesquisar);
        painelPesquisas.add(btnPesquisarBaratas);
        painelPesquisas.add(btnPrecoMedio);

        //Ações Gerais
        JPanel painelAcoes = new JPanel(new FlowLayout());
        JButton btnListar = new JButton("Listar Todos");
        JButton btnSalvar = new JButton("Salvar Dados");
        JButton btnCarregar = new JButton("Carregar Dados");

        painelAcoes.add(btnListar);
        painelAcoes.add(btnSalvar);
        painelAcoes.add(btnCarregar);

        painelSul.add(painelPesquisas);
        painelSul.add(painelAcoes);

        add(painelSul, BorderLayout.SOUTH);

        // --- EVENTOS DOS BOTÕES ---
        btnCadastrar.addActionListener(e -> cadastrar());
        btnRemover.addActionListener(e -> remover());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnPesquisarBaratas.addActionListener(e -> pesquisarBaratas());
        btnPrecoMedio.addActionListener(e -> calcularMedia());
        btnListar.addActionListener(e -> listar());
        btnSalvar.addActionListener(e -> salvar());
        btnCarregar.addActionListener(e -> carregar());
    }

    private void cadastrar(){
        try {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();

            if (nome.isEmpty() || descricao.isEmpty() || txtPreco.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));

            sistema.cadastrarRefeicao(nome, descricao, preco);
            atualizarLista(sistema.listarRefeicoes());
            limparCampos();

            JOptionPane.showMessageDialog(this, "Refeição cadastrada com sucesso!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um valor numérico válido para o preço.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remover(){
        Refeicao selecionada = listaRefeicoes.getSelectedValue();

        if(selecionada != null){
            sistema.removerRefeicao(selecionada.getNome());
            atualizarLista(sistema.listarRefeicoes());
            JOptionPane.showMessageDialog(this, "Refeição removida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, clique em uma refeição na lista abaixo para selecioná-la e depois clique em Remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void pesquisar(){
        String nome = txtPesquisa.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome para pesquisar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Collection<Refeicao> resultado = sistema.pesquisarRefeicaoPorNome(nome);
        atualizarLista(resultado);

        if(resultado.isEmpty()){
            JOptionPane.showMessageDialog(this, "Nenhuma refeição encontrada com esse nome.");
        }
    }

    //Pesquisar Refeições Baratas
    private void pesquisarBaratas(){
        String input = JOptionPane.showInputDialog(this, "Digite o preço máximo (Ex: 20.00):", "Pesquisar Baratas", JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                double precoMax = Double.parseDouble(input.replace(",", "."));
                Collection<Refeicao> resultado = sistema.pesquisarRefeicoesBaratas(precoMax);
                atualizarLista(resultado);

                if(resultado.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Nenhuma refeição encontrada abaixo de R$ " + String.format("%.2f", precoMax));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Calcular Preço Médio
    private void calcularMedia(){
        if(sistema.listarRefeicoes().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O cardápio está vazio. Não há média para calcular.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double media = sistema.calcularPrecoMedio();
        JOptionPane.showMessageDialog(this, "O preço médio das refeições cadastradas é:\nR$ " + String.format("%.2f", media), "Preço Médio", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listar(){
        atualizarLista(sistema.listarRefeicoes());
    }

    private void salvar(){
        try {
            gravador.gravarDados(sistema.getRefeicoes(), ARQUIVO);
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso no arquivo 'cardapio.dat'!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregar(){
        File arquivo = new File(ARQUIVO);
        if(!arquivo.exists()){
            JOptionPane.showMessageDialog(this, "Nenhum arquivo de dados encontrado. O sistema começará vazio.", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            @SuppressWarnings("unchecked")
            Map<String,Refeicao> dados = (Map<String,Refeicao>) gravador.recuperarDados(ARQUIVO);
            sistema.setRefeicoes(dados);
            atualizarLista(sistema.listarRefeicoes());
            JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarLista(Collection<Refeicao> refeicoes){
        modeloLista.clear();
        for(Refeicao r : refeicoes){
            modeloLista.addElement(r);
        }
    }

    private void limparCampos(){
        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtNome.requestFocus();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new TelaCardapio());
    }
}