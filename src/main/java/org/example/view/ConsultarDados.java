package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ConsultarDados extends JPanel {
    public ConsultarDados(Frame frame){
        setBackground(new Color(248, 233, 233));
        setSize(500, 400);
        setLayout(null);

        var CConta = new JButton("Consultar conta:");
        var CFuncionario = new JButton("Consultar funcionário:");
        var CCliente = new JButton("Consultar cliente:");
        var Voltar = new JButton("Voltar");

        CConta.setBounds(175, 25, 150, 30);
        add(CConta);

        CFuncionario.setBounds(175, 50, 150, 30);
        add(CFuncionario);

        CCliente.setBounds(175, 75, 150, 30);
        add(CCliente);

        Voltar.setBounds(175, 100, 150, 30);
        add(Voltar);

        CConta.addActionListener(e -> {
            frame.Show("ConsultarConta");
        });

        CFuncionario.addActionListener(e -> {
            frame.Show("ConsultarFuncionario");
        });

        CCliente.addActionListener(e -> {
            frame.Show("ConsultarCliente");
        });

        Voltar.addActionListener(e -> {
            frame.Show("MenuFuncionario");
        });
    }
}
