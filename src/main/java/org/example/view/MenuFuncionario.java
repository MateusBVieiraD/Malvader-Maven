package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFuncionario extends JPanel {
    public MenuFuncionario(Frame frame) {

        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var aberturaFB = new JButton("Abertura de conta");
        var encerramentoFB = new JButton("Encerramento de conta");
        var consultarFB = new JButton("Consultar dados");
        var alterarFB = new JButton("Alterar dados");
        var cadastroFB = new JButton("Cadastro de funcionários");
        var gerarFB = new JButton("Gerar relatórios");
        var sairFB = new JButton("Sair");

        aberturaFB.setBounds(150, 0, 200, 30);
        add(aberturaFB);

        encerramentoFB.setBounds(150, 30, 200, 30);
        add(encerramentoFB);

        consultarFB.setBounds(150, 60, 200, 30);
        add(consultarFB);

        alterarFB.setBounds(150, 90, 200, 30);
        add(alterarFB);

        cadastroFB.setBounds(150, 120, 200, 30);
        add(cadastroFB);

        gerarFB.setBounds(150, 150, 200, 30);
        add(gerarFB);

        sairFB.setBounds(150, 180, 200, 30);
        add(sairFB);

        aberturaFB.addActionListener(e -> {
            frame.Show("ContaFuncionario");
        });

        encerramentoFB.addActionListener(e -> {
            frame.Show("EncerramentoContaConfirm");
        });

        consultarFB.addActionListener(e -> {
            frame.Show("ConsultarDados");
        });

        alterarFB.addActionListener(e -> {
            frame.Show("AlterarDados");
        });

        cadastroFB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.Show("CadastroFuncionarios");
                frame.resizeFrame(900, 900);
            }
        });

        sairFB.addActionListener(e -> frame.Show("Home"));

    }
}
