package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCliente extends JPanel {
    public MenuCliente(Frame frame){

        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var saldoCB = new JButton("Saldo");
        var depositoCB = new JButton("Deposito");
        var saqueCB = new JButton("Saque");
        var extratoCB = new JButton("Extrato");
        var consultarCB = new JButton("Consultar limite");
        var sairCB = new JButton("Sair");

        saldoCB.setBounds(150, 30, 200, 30);
        add(saldoCB);

        depositoCB.setBounds(150, 60, 200, 30);
        add(depositoCB);

        saqueCB.setBounds(150, 90, 200, 30);
        add(saqueCB);

        extratoCB.setBounds(150, 120, 200, 30);
        add(extratoCB);

        consultarCB.setBounds(150, 150, 200, 30);
        add(consultarCB);

        sairCB.setBounds(150, 180, 200, 30);
        add(sairCB);

        saldoCB.addActionListener(e -> frame.Show("Saldo"));

        depositoCB.addActionListener(e -> {
            JPasswordField
        });

        saqueCB.addActionListener(e -> {

        });

        extratoCB.addActionListener(e -> frame.Show("Extrato"));

        consultarCB.addActionListener(e -> frame.Show("Consultar"));

        sairCB.addActionListener(e -> frame.Show("Home"));
    }
}
