package org.example.view;

import org.example.controller.ControllerGeral;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;

public class CadastroFuncionarios extends JPanel {
    public CadastroFuncionarios(Frame frame){
        setLayout(null);
        setSize(900, 900);
        setBackground(new Color(255, 255, 255));

        var codigoFL = new JLabel("Código funcionário:");
        var cargoFL = new JLabel("Cargo:");
        var nomeFL = new JLabel("Nome do funcionário:");
        var cpfFL = new JLabel("CPF do funcionário:");
        var datanascimentoFL = new JLabel("Data de nascimento (YYYY-MM-DD):");
        var telefoneFL = new JLabel("Telefone de contato:");
        var enderecoFL = new JLabel("Endereço do funcionário:");
        var cepFL = new JLabel("CEP:");
        var localFL = new JLabel("Local:");
        var numerocasaFL = new JLabel("Número da casa:");
        var bairroFL = new JLabel("Bairro:");
        var cidadeFL = new JLabel("Cidade:");
        var estadoFL = new JLabel("Estado:");
        var senhaFL = new JLabel("Senha do funcionário:");

        var codigoFT = new JTextField();
        var cargoFT = new JTextField();
        var nomeFT = new JTextField();

        JFormattedTextField cpfFT;
        try {

            MaskFormatter cpfmask = new MaskFormatter("###.###.###-##");
            cpfFT = new JFormattedTextField(cpfmask);
            cpfFT.setColumns(15);

            PlainDocument doc = (PlainDocument) cpfFT.getDocument();
            doc.setDocumentFilter(new NumericDocumentFilter());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JFormattedTextField datanascimentoFT;
        try{

            MaskFormatter nascimentoMask = new MaskFormatter("####-##-##");
            datanascimentoFT = new JFormattedTextField(nascimentoMask);
            datanascimentoFT.setColumns(10);

            PlainDocument doc = (PlainDocument) datanascimentoFT.getDocument();
            doc.setDocumentFilter(new org.example.view.NumericDocumentFilter());
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

        JFormattedTextField telefoneFT;
        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
            telefoneFT = new JFormattedTextField(telefoneMask);
            telefoneFT.setColumns(14);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

        var enderecoFT = new JTextField();

        JFormattedTextField cepFT;
        try {
            MaskFormatter cepTextMask = new MaskFormatter("#####-###");
            cepFT = new JFormattedTextField(cepTextMask);
            cepFT.setColumns(15);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        var localFT = new JTextField();
        var numerocasaFT = new JTextField();
        var bairroFT = new JTextField();
        var cidadeFT = new JTextField();
        var estadoFT = new JTextField();
        var senhaFT = new JTextField();

        var criarFuncionarioB = new JButton("Criar");
        var Voltar = new JButton("<");

        Voltar.setBounds(5, 5, 30, 30);
        add(Voltar);

        codigoFL.setBounds(10, 25, 350, 25);
        add(codigoFL);

        codigoFT.setBounds(10, 50, 165, 25);
        add(codigoFT);

        cargoFL.setBounds(10, 75, 350, 25);
        add(cargoFL);

        cargoFT.setBounds(10, 100, 165, 25);
        add(cargoFT);

        nomeFL.setBounds(10, 125, 350, 25);
        add(nomeFL);

        nomeFT.setBounds(10, 150, 165, 25);
        add(nomeFT);

        cpfFL.setBounds(10, 175, 350, 25);
        add(cpfFL);

        cpfFT.setBounds(10, 200, 165, 25);
        add(cpfFT);

        datanascimentoFL.setBounds(10, 225, 350, 25);
        add(datanascimentoFL);

        datanascimentoFT.setBounds(10, 250, 165, 25);
        add(datanascimentoFT);

        telefoneFL.setBounds(10, 275, 350, 25);
        add(telefoneFL);

        telefoneFT.setBounds(10, 300, 165, 25);
        add(telefoneFT);

        enderecoFL.setBounds(10, 325, 350, 25);
        add(enderecoFL);

        enderecoFT.setBounds(10, 350, 165, 25);
        add(enderecoFT);

        cepFL.setBounds(10, 375, 350, 25);
        add(cepFL);

        cepFT.setBounds(10, 400, 165, 25);
        add(cepFT);

        localFL.setBounds(10, 425, 350, 25);
        add(localFL);

        localFT.setBounds(10, 450, 165, 25);
        add(localFT);

        numerocasaFL.setBounds(10, 475, 350, 25);
        add(numerocasaFL);

        numerocasaFT.setBounds(10, 500, 165, 25);
        add(numerocasaFT);

        bairroFL.setBounds(10, 525, 350, 25);
        add(bairroFL);

        bairroFT.setBounds(10, 550, 165, 25);
        add(bairroFT);

        cidadeFL.setBounds(10, 575, 350, 25);
        add(cidadeFL);

        cidadeFT.setBounds(10, 600, 165, 25);
        add(cidadeFT);

        estadoFL.setBounds(10, 625, 350, 25);
        add(estadoFL);

        estadoFT.setBounds(10, 650, 165, 25);
        add(estadoFT);

        senhaFL.setBounds(10, 675, 350, 25);
        add(senhaFL);

        senhaFT.setBounds(10, 700, 165, 25);
        add(senhaFT);

        criarFuncionarioB.setBounds(10, 800, 80, 30);
        add(criarFuncionarioB);

        var controlergeral = new ControllerGeral();

        Voltar.addActionListener(e -> {
            frame.Show("MenuFuncionario");
            frame.resizeFrame(500, 400);
        });

        criarFuncionarioB.addActionListener(e -> {
            String codigoFuncionario = codigoFT.getText();
            String cargo = cargoFT.getText();
            String nomeFuncionario = nomeFT.getText();
            String cpfFuncionario = cpfFT.getText();
            String dataNascimento = datanascimentoFT.getText();
            String telefone = telefoneFT.getText();
            String endereco = enderecoFT.getText();
            String cep = cepFT.getText();
            String local = localFT.getText();
            String numeroCasa = numerocasaFT.getText();
            String bairro = bairroFT.getText();
            String cidade = cidadeFT.getText();
            String estado = estadoFT.getText();
            String senhaFuncionario = senhaFT.getText();

            if(codigoFuncionario.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo código do funcionário não foi preenchido");
            } else if (cargo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo cargo não foi preenchido!");
            } else if (nomeFuncionario.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo nome do funcionário não foi preenchido!");
            } else if (cpfFuncionario.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo CPF do funcionário não foi preenchido!");
            } else if (dataNascimento.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo data de nascimento não foi preenchido!");
            } else if (telefone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo telefone não foi preenchido!");
            } else if (endereco.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo endereço não foi preenchido!");
            } else if (cep.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo cep não foi preenchido!");
            } else if (local.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo local não foi preenchido!");
            } else if (numeroCasa.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo número da casa não foi preenchido!");
            } else if (bairro.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo bairro não foi preenchido!");
            } else if (cidade.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo cidade não foi preenchido!");
            } else if (estado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo estado não foi preenchido!");
            } else if (senhaFuncionario.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo senha não foi preenchido!");
            } else {
                int verificacao = controlergeral.criarConta(cpfFuncionario, senhaFuncionario, nomeFuncionario, telefone, TipoUsuario.FUNCIONARIO, LocalDate.parse(dataNascimento), cep, local, Integer.parseInt(numeroCasa), bairro, cidade, estado, "", TipoConta.CORRENTE, cargo, codigoFuncionario, "");
                if(verificacao == 1){
                    JOptionPane.showMessageDialog(frame, "Conta cadastrada com sucesso!");
                    codigoFT.setText("");
                    cargoFT.setText("");
                    nomeFT.setText("");
                    cpfFT.setText("");
                    datanascimentoFT.setText("");
                    telefoneFT.setText("");
                    enderecoFT.setText("");
                    cepFT.setText("");
                    localFT.setText("");
                    numerocasaFT.setText("");
                    bairroFT.setText("");
                    cidadeFT.setText("");
                    estadoFT.setText("");
                    senhaFT.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error ao cadastrar o funcionário!");
                }
            }
        });
    }

}
