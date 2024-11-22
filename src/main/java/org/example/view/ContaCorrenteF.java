package org.example.view;


import org.example.controller.ContaCorrenteController;
import org.example.controller.UsuarioController;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

public class ContaCorrenteF extends JPanel {
    public ContaCorrenteF(Frame frame) {
        var contaCorrenteController = new ContaCorrenteController();
        var UserControl = new UsuarioController();

        setLayout(null);
        setSize(900, 1000);
        setBackground(new Color(255, 255, 255));

        var titulo = new JLabel("Criar conta corrente");
        var agencia = new JLabel("Agência:");
        var numeroConta = new JLabel("Número da conta:");
        var limiteConta = new JLabel("Limite da conta:");
        var dataVencimento = new JLabel("Data de vencimento (YYYY-MM-DD):");
        var nomeCliente = new JLabel("Nome do cliente:");
        var cpfCliente = new JLabel("CPF do cliente:");
        var dataNascimento = new JLabel("Data de nascimento (YYYY-MM-DD):");
        var telefoneContato = new JLabel("Telefone de contato:");
        var enderecoCliente = new JLabel("Endereço do cliente:");
        var cepCliente = new JLabel("CEP:");
        var localCliente = new JLabel("Local:");
        var numeroCasa = new JLabel("Número da casa:");
        var bairroCliente = new JLabel("Bairro:");
        var cidadeCliente = new JLabel("Cidade:");
        var estadoCliente = new JLabel("Estado:");
        var senhaCliente = new JLabel("Senha:");

        var agenciaText = new JTextField();
        var numeroContaText = new JTextField();
        var limiteC = new JTextField();
        JFormattedTextField vencimento;
        try {
            MaskFormatter vencimentoMask = new MaskFormatter("####-##-##");
            vencimento = new JFormattedTextField(vencimentoMask);
            vencimento.setColumns(10);
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }

        var nomeText = new JTextField();

        JFormattedTextField cpfText;
        try {

            MaskFormatter cpfmask = new MaskFormatter("###.###.###-##");
            cpfText = new JFormattedTextField(cpfmask);
            cpfText.setColumns(15);

            PlainDocument doc = (PlainDocument) cpfText.getDocument();
            doc.setDocumentFilter(new NumericDocumentFilter());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JFormattedTextField nascimentoText;
        try{

            MaskFormatter nascimentoMask = new MaskFormatter("####-##-##");
            nascimentoText = new JFormattedTextField(nascimentoMask);
            nascimentoText.setColumns(10);

            PlainDocument doc = (PlainDocument) nascimentoText.getDocument();
            doc.setDocumentFilter(new org.example.view.NumericDocumentFilter());
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

            JFormattedTextField telefoneText;
        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
            telefoneText = new JFormattedTextField(telefoneMask);
            telefoneText.setColumns(14);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

        var enderecoText = new JTextField();

            JFormattedTextField cepText;
        try {
            MaskFormatter cepTextMask = new MaskFormatter("#####-###");
            cepText = new JFormattedTextField(cepTextMask);
            cepText.setColumns(15);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        var localText = new JTextField();
        var numeroCasaText = new JTextField();
        var bairroText = new JTextField();
        var cidadeText = new JTextField();
        var estadoText = new JTextField();
        var senhaClienteText = new JTextField();

        var criarBotao = new JButton("Criar");
        var botaoVoltar = new JButton("<");

        botaoVoltar.setBounds(5, 5, 45, 45);
        add(botaoVoltar);

        titulo.setBounds(450, 10, 350, 25);
        add(titulo, BorderLayout.CENTER);

        agencia.setBounds(10, 50, 350, 25);
        add(agencia);

        agenciaText.setBounds(10, 75, 165, 25);
        add(agenciaText);

        numeroConta.setBounds(10, 100, 350, 25);
        add(numeroConta);

        numeroContaText.setBounds(10, 125, 165, 25);
        add(numeroContaText);

        limiteConta.setBounds(10, 150, 350, 25);
        add(limiteConta);

        limiteC.setBounds(10, 175, 165, 25);
        add(limiteC);

        dataVencimento.setBounds(10, 200, 350, 25);
        add(dataVencimento);

        vencimento.setBounds(10, 225, 165, 25);
        add(vencimento);

        nomeCliente.setBounds(10, 250, 350, 25);
        add(nomeCliente);

        nomeText.setBounds(10, 275, 165, 25);
        add(nomeText);

        cpfCliente.setBounds(10, 300, 350, 25);
        add(cpfCliente);

        cpfText.setBounds(10, 325, 165, 25);
        add(cpfText);

        dataNascimento.setBounds(10, 350, 350, 25);
        add(dataNascimento);

        nascimentoText.setBounds(10, 375, 165, 25);
        add(nascimentoText);

        telefoneContato.setBounds(10, 400, 350, 25);
        add(telefoneContato);

        telefoneText.setBounds(10, 425, 165, 25);
        add(telefoneText);

        enderecoCliente.setBounds(10, 450, 165, 25);
        add(enderecoCliente);

        enderecoText.setBounds(10, 475, 165, 25);
        add(enderecoText);

        cepCliente.setBounds(10, 500, 350, 25);
        add(cepCliente);

        cepText.setBounds(10, 525, 165, 25);
        add(cepText);

        localCliente.setBounds(10, 550, 350, 25);
        add(localCliente);

        localText.setBounds(10, 575, 165, 25);
        add(localText);

        numeroCasa.setBounds(10, 600, 350, 25);
        add(numeroCasa);

        numeroCasaText.setBounds(10, 625, 165, 25);
        add(numeroCasaText);

        bairroCliente.setBounds(10, 650, 350, 25);
        add(bairroCliente);

        bairroText.setBounds(10, 675, 165, 25);
        add(bairroText);

        cidadeCliente.setBounds(10, 700, 350, 25);
        add(cidadeCliente);

        cidadeText.setBounds(10, 725, 165, 25);
        add(cidadeText);

        estadoCliente.setBounds(10, 750, 350, 25);
        add(estadoCliente);

        estadoText.setBounds(10, 775, 165, 25);
        add(estadoText);

        senhaCliente.setBounds(10, 800, 350, 25);
        add(senhaCliente);

        senhaClienteText.setBounds(10, 825, 165, 25);
        add(senhaClienteText);

        criarBotao.setBounds(10, 900, 80, 30);
        add(criarBotao);

        criarBotao.addActionListener(e -> {
            String agenciaT = agenciaText.getText();
            String numeroC = numeroContaText.getText();
            String username = nomeText.getText();
            String cpf = cpfText.getText();
            String nascimento = nascimentoText.getText();
            String telefoneC = telefoneText.getText();
            String endereco = enderecoText.getText();
            String cep = cepText.getText();
            String local = localText.getText();
            String numerocasa = numeroCasaText.getText();
            String bairro = bairroText.getText();
            String cidade = cidadeText.getText();
            String estado = estadoText.getText();
            String senha = senhaClienteText.getText();
            String datavencimento = vencimento.getText();
            String limite = limiteC.getText();

            if(agenciaT.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo agencia não foi preenchido!");
            } else if(numeroC.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo número da conta não foi preenchida!");
            } else if(username.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo nome não foi preenchido!");
            } else if(UserControl.ValidarCpfConta(cpf, TipoConta.CORRENTE)){
                JOptionPane.showMessageDialog(frame, "Já existe uma conta corrente com este cpf!");
            } else if(cpf.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo cpf não foi preenchido!");
            } else if (nascimento.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo data de nascimento não foi preenchido!");
            } else if(telefoneC.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo telefone não foi preenchido!");
            } else if (endereco.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo endereço não foi preenchido!");
            } else if(cep.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo CEP não foi preenchido!");
            } else if (local.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo local não foi preenchido!");
            } else if (numerocasa.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo número da casa não foi preenchido!");
            } else if(bairro.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo bairro não foi preenchido!");
            } else if (cidade.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo cidade não foi preenchido!");
            } else if (estado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo estado não foi preenchido!");
            } else if (senha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo senha não foi preenchido!");
            } else if (datavencimento.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo data de vencimento não foi preenchido!");
            } else if (limite.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo limite da conta não foi preenchido!");
            } else {
                boolean verificar = contaCorrenteController.criarContaC(cpf, senha, username, telefoneC, TipoUsuario.CLIENTE, LocalDate.parse(nascimento), cep, local, Integer.parseInt(numerocasa), bairro, cidade, estado, agenciaT, TipoConta.CORRENTE, LocalDate.parse(datavencimento), BigDecimal.valueOf(Double.parseDouble(limite)), "", "");
                agenciaText.setText("");
                numeroContaText.setText("");
                numeroCasaText.setText("");
                nomeText.setText("");
                cpfText.setText("");
                nascimentoText.setText("");
                telefoneText.setText("");
                enderecoText.setText("");
                cepText.setText("");
                localText.setText("");
                numeroCasaText.setText("");
                bairroText.setText("");
                cidadeText.setText("");
                estadoText.setText("");
                senhaClienteText.setText("");
                vencimento.setText("");
                limiteC.setText("");
                if(verificar){
                    JOptionPane.showMessageDialog(frame, "Conta criada com sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Error ao criar a conta!");
                }
            }



        });

        botaoVoltar.addActionListener(e -> {
            frame.Show("ContaFuncionario");
            frame.resizeFrame(500, 400);
        });



    }
}
