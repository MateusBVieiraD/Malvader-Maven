package org.example.view;

import org.example.controller.FuncionarioController;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AlterarFuncionario extends JPanel {
    public AlterarFuncionario(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(900, 900);
        setLayout(null);

        var funcionarioController = new FuncionarioController();

        var codigoFL = new JLabel("Código do funcionário:");
        var cargoFL = new JLabel("Cargo:");
        var telefoneFL = new JLabel("Telefone:");
        var localFL = new JLabel("Local:");
        var numeroCasaFL = new JLabel("Número da casa:");
        var cepFL = new JLabel("CEP:");
        var bairroFL = new JLabel("Bairro:");
        var cidadeFL = new JLabel("Cidade:");
        var estadoFL = new JLabel("Estado:");

        var codigoFT = new JTextField();
        var cargoFT = new JTextField();

        JFormattedTextField telefoneFT;
        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
            telefoneFT = new JFormattedTextField(telefoneMask);
            telefoneFT.setColumns(14);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

        var localFT = new JTextField();
        var numeroCasaFT = new JTextField();

        JFormattedTextField cepFT;
        try {
            MaskFormatter cepTextMask = new MaskFormatter("#####-###");
            cepFT = new JFormattedTextField(cepTextMask);
            cepFT.setColumns(15);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        var bairroFT = new JTextField();
        var cidadeFT = new JTextField();
        var estadoFT = new JTextField();

        var Voltar = new JButton("<");
        var Alterar = new JButton("Alterar");

        Voltar.setBounds(5, 5, 45, 45);
        add(Voltar);

        codigoFL.setBounds(10, 50, 350, 25);
        add(codigoFL);

        codigoFT.setBounds(10, 75, 165, 25);
        add(codigoFT);

        cargoFL.setBounds(10, 100, 350, 25);
        add(cargoFL);

        cargoFT.setBounds(10, 125, 165, 25);
        add(cargoFT);

        telefoneFL.setBounds(10, 150, 350, 25);
        add(telefoneFL);

        telefoneFT.setBounds(10, 175, 165, 25);
        add(telefoneFT);

        localFL.setBounds(10, 200, 350, 25);
        add(localFL);

        localFT.setBounds(10, 225, 165, 25);
        add(localFT);

        numeroCasaFL.setBounds(10, 250, 350, 25);
        add(numeroCasaFL);

        numeroCasaFT.setBounds(10, 275, 165, 25);
        add(numeroCasaFT);

        cepFL.setBounds(10, 300, 350, 25);
        add(cepFL);

        cepFT.setBounds(10, 325, 165, 25);
        add(cepFT);

        bairroFL.setBounds(10, 350, 350, 25);
        add(bairroFL);

        bairroFT.setBounds(10, 375, 165, 25);
        add(bairroFT);

        cidadeFL.setBounds(10, 400, 350, 25);
        add(cidadeFL);

        cidadeFT.setBounds(10, 425, 165, 25);
        add(cidadeFT);

        estadoFL.setBounds(10, 450, 350, 25);
        add(estadoFL);

        estadoFT.setBounds(10, 475, 165, 25);
        add(estadoFT);

        Alterar.setBounds(10, 525, 80, 30);
        add(Alterar);

        Alterar.addActionListener(e -> {
            String codigo = codigoFT.getText();
            String cargo = cargoFT.getText();
            String telefone = telefoneFT.getText();
            String local = localFT.getText();
            String numeroCasa = numeroCasaFT.getText();
            String cep = cepFT.getText();
            String bairro = bairroFT.getText();
            String cidade = cidadeFT.getText();
            String estado = estadoFT.getText();

            if(codigo.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo código do funcionário não foi preenchido!");
            }else if(cargo.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo cargo não foi preenchido!");
            }else if(telefone.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo telefone não foi preenchido!");
            }else if(local.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo local não foi preenchido!");
            }else if(numeroCasa.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo número da casa não foi preenchido!");
            }else if(cep.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo CEP não foi preenchido!");
            }else if(bairro.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo bairro não foi preenchido!");
            }else if(cidade.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo cidade não foi preenchido!");
            }else if(estado.isEmpty()){
                JOptionPane.showMessageDialog(frame, "O campo estado não foi preenchido!");
            }else {
                if(funcionarioController.alterarFuncionario(codigo, cargo, telefone, local, Integer.parseInt(numeroCasa), cep, bairro, cidade, estado)){
                    codigoFT.setText("");
                    cargoFT.setText("");
                    telefoneFT.setText("");
                    localFT.setText("");
                    numeroCasaFT.setText("");
                    cepFT.setText("");
                    bairroFT.setText("");
                    cidadeFT.setText("");
                    estadoFT.setText("");
                    JOptionPane.showMessageDialog(frame, "Os dados do funcionário foi alterado com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Error ao alterar os atributos do funcionário!");
                }
            }
        });

        Voltar.addActionListener(e -> {
            frame.resizeFrame(500, 400);
            frame.Show("MenuFuncionario");
        });
    }
}
