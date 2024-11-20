package org.example.view;

import javax.swing.*;
import java.awt.*;

public class AlterarDados extends JPanel {
    public AlterarDados(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var AConta = new JButton("Alterar conta");
        var AFuncionario = new JButton("Alterar funcionário");
        var ACliente = new JButton("Alterar cliente");
        var Voltar = new JButton("Voltar");

        AConta.setBounds(150, 25, 175, 150);
        add(AConta);

        AFuncionario.setBounds(150, 50, 175, 150);
        add(AFuncionario);

        ACliente.setBounds(150, 75, 175, 150);
        add(ACliente);

        Voltar.setBounds(150, 100, 175, 150);
        add(Voltar);

        AConta.addActionListener(e -> {
            frame.Show("AlterarConta");
        });

        AFuncionario.addActionListener(e -> {
            frame.Show("AlterarFuncionario");
        });

        ACliente.addActionListener(e -> {
            frame.Show("AlterarCliente");
        });

        Voltar.addActionListener(e -> {
            frame.Show("MenuFuncionario");
        });

    }
}
