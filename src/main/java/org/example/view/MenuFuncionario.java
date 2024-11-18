package org.example.view;

import javax.swing.*;
import java.awt.*;

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

        aberturaFB.setBounds(175, 0, 150, 30);
        add(aberturaFB);

        encerramentoFB.setBounds(175, 30, 150, 30);
        add(encerramentoFB);

        consultarFB.setBounds(175, 60, 150, 30);
        add(consultarFB);

        alterarFB.setBounds(175, 90, 150, 30);
        add(alterarFB);

        cadastroFB.setBounds(175, 120, 150, 30);
        add(cadastroFB);

        gerarFB.setBounds(175, 150, 150, 30);
        add(gerarFB);

        sairFB.setBounds(175, 180, 150, 30);
        add(sairFB);

        sairFB.addActionListener(e -> frame.Show("Home"));
    }
}
