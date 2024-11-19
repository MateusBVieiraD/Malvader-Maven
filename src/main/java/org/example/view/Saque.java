package org.example.view;

import javax.swing.*;
import java.awt.*;

public class Saque extends JPanel {
    public Saque(Frame frame){
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var saqueText = new JLabel("Digite o valor a ser Debitado:");

        var saqueField = new JTextField();

        var saqueButton = new JButton("Sacar");

        saqueText.setBounds(10, 25, 350, 25);
        add(saqueText);

        saqueField.setBounds(10, 50, 165, 25);
        add(saqueField);

        saqueButton.setBounds(10, 100, 80, 30);
        add(saqueButton);

        saqueButton.addActionListener(e-> {

        });

    }
}
