package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CadastroFuncionarios extends JPanel {
    public CadastroFuncionarios(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var codigoFL = new JLabel("Código funcionário:");
        var cargoFL = new JLabel("Cargo:");
        var nomeFL = new JLabel("Nome do funcionário:");
        var cpfFL = new JLabel("CPF do funcionário:");
        var datanascimentoFL = new JLabel("Data de nascimento (YYYY/MM/DD):");
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
        var cpfFT = new JTextField();
        var datanascimentoFT = new JTextField();
        var telefoneFT = new JTextField();
        var enderecoFT = new JTextField();
        var cepFT = new JTextField();
        var localFT = new JTextField();
        var numerocasaFT = new JTextField();
        var bairroFT = new JTextField();
        var cidadeFT = new JTextField();
        var estadoFT = new JTextField();
        var senhaFT = new JTextField();

        var Voltar = new JButton("<");
        var criarFuncionarioB = new JButton("Criar");

        Voltar.setBounds(5, 5, 30, 30);

        codigoFL.setBounds(10, 25, 350, 25);
        add(codigoFL);

        codigoFL.setBounds(10, 50, 165, 25);
        add(codigoFT);

        codigoFL.setBounds(10, 75, 350, 25);
        add(cargoFL);

        codigoFL.setBounds(10, 100, 165, 25);
        add(cargoFT);

        codigoFL.setBounds(10, 125, 350, 25);
        add(nomeFL);

        codigoFL.setBounds(10, 150, 165, 25);
        add(nomeFT);

        codigoFL.setBounds(10, 175, 350, 25);
        add(cpfFL);

        codigoFL.setBounds(10, 200, 165, 25);
        add(cpfFT);

        codigoFL.setBounds(10, 225, 350, 25);
        add(datanascimentoFL);

        codigoFL.setBounds(10, 250, 165, 25);
        add(datanascimentoFT);

        codigoFL.setBounds(10, 275, 350, 25);
        add(telefoneFL);

        codigoFL.setBounds(10, 300, 165, 25);
        add(telefoneFT);

        codigoFL.setBounds(10, 325, 350, 25);
        add(enderecoFL);

        codigoFL.setBounds(10, 350, 165, 25);
        add(enderecoFT);

        codigoFL.setBounds(10, 375, 350, 25);
        add(cepFL);

        codigoFL.setBounds(10, 400, 165, 25);
        add(cepFT);

        codigoFL.setBounds(10, 425, 350, 25);
        add(localFL);

        codigoFL.setBounds(10, 450, 165, 25);
        add(localFT);

        codigoFL.setBounds(10, 475, 350, 25);
        add(numerocasaFL);

        codigoFL.setBounds(10, 500, 165, 25);
        add(numerocasaFT);

        codigoFL.setBounds(10, 525, 350, 25);
        add(bairroFL);

        codigoFL.setBounds(10, 550, 165, 25);
        add(bairroFT);

        codigoFL.setBounds(10, 575, 350, 25);
        add(cidadeFL);

        codigoFL.setBounds(10, 600, 165, 25);
        add(cidadeFT);

        codigoFL.setBounds(10, 625, 350, 25);
        add(estadoFL);

        codigoFL.setBounds(10, 650, 165, 25);
        add(estadoFT);

        codigoFL.setBounds(10, 675, 350, 25);
        add(senhaFL);

        codigoFL.setBounds(10, 700, 165, 25);
        add(senhaFT);

        criarFuncionarioB.setBounds(10, 800, 80, 30);
        add(criarFuncionarioB);

        Voltar.addActionListener(e -> {
            frame.Show("MenuFuncionario");
            frame.resizeFrame(500, 400);
        });

        criarFuncionarioB.addActionListener(e -> {

        });
    }

}
