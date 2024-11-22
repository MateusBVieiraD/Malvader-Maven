package org.example.view;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.controller.ClienteController;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class AlterarCliente extends JPanel {
    public AlterarCliente(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(900, 900);
        setLayout(null);

        var clienteController = new ClienteController();

        var telefone = new JLabel("Telefone:");
        var local = new JLabel("Local:");
        var numeroCasa = new JLabel("Número da casa:");
        var cep = new JLabel("CEP:");
        var bairro = new JLabel("Bairro:");
        var cidade = new JLabel("Cidade:");
        var estado = new JLabel("Estado:");

        JFormattedTextField telefoneT;
        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
            telefoneT = new JFormattedTextField(telefoneMask);
            telefoneT.setColumns(14);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

        var localT = new JTextField();
        var numeroCasaT = new JTextField();

        JFormattedTextField cepT;
        try {
            MaskFormatter cepTextMask = new MaskFormatter("#####-###");
            cepT = new JFormattedTextField(cepTextMask);
            cepT.setColumns(15);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        var bairroT = new JTextField();
        var cidadeT = new JTextField();
        var estadoT = new JTextField();

        var Voltar = new JButton("<");
        var Alterar = new JButton("Alterar");

        Voltar.setBounds(5, 5, 45, 45);
        add(Voltar);

        telefone.setBounds(10, 50, 350, 25);
        add(telefone);

        telefoneT.setBounds(10, 75, 165, 25);
        add(telefoneT);

        local.setBounds(10, 100, 350, 25);
        add(local);

        localT.setBounds(10, 125, 165, 25);
        add(localT);

        numeroCasa.setBounds(10, 150, 350, 25);
        add(numeroCasa);

        numeroCasaT.setBounds(10, 175, 165, 25);
        add(numeroCasaT);

        cep.setBounds(10, 200, 350, 25);
        add(cep);

        cepT.setBounds(10, 225, 165, 25);
        add(cepT);

        bairro.setBounds(10, 250, 350, 25);
        add(bairro);

        bairroT.setBounds(10, 275, 165, 25);
        add(bairroT);

        cidade.setBounds(10, 300, 350, 25);
        add(cidade);

        cidadeT.setBounds(10, 325, 165, 25);
        add(cidadeT);

        estado.setBounds(10, 350, 350, 25);
        add(estado);

        estadoT.setBounds(10, 375, 165, 25);
        add(estadoT);

        Alterar.setBounds(10, 425, 80, 30);
        add(Alterar);

        Alterar.addActionListener(e -> {
            String telefoneS = telefoneT.getText();
            String localS = localT.getText();
            String numeroCasaS = numeroCasaT.getText();
            String cepS = cepT.getText();
            String bairroS = bairroT.getText();
            String cidadeS = cidadeT.getText();
            String estadoS = estadoT.getText();

            if (telefoneS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo telefone está vazio!");
            } else if (localS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo local está vazio!");
            } else if (numeroCasaS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo número da casa está vazio!");
            } else if (cepS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo CEP está vazio!");
            } else if (bairroS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo bairro está vazio!");
            } else if (cidadeS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo cidade está vazio!");
            } else if (estadoS.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O campo estado está vazio!");
            } else {
                if(clienteController.alterarCliente(Sessao.getId(), telefoneS, localS, Integer.parseInt(numeroCasaS), cepS, bairroS, cidadeS, estadoS)){
                    telefoneT.setText("");
                    localT.setText("");
                    numeroCasaT.setText("");
                    cepT.setText("");
                    bairroT.setText("");
                    cidadeT.setText("");
                    estadoT.setText("");
                    JOptionPane.showMessageDialog(frame, "Os dados do cliente foram alterados com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Error ao alterar os dados do cliente!");
                }
            }
        });

        Voltar.addActionListener(e -> {
            frame.resizeFrame(500, 400);
            frame.Show("MenuFuncionario");
        });
    }
}
