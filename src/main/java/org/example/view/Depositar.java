package org.example.view;

import javax.swing.*;
import java.awt.*;

public class Depositar extends JPanel {
    public Depositar(Frame frame){
        setLayout(null);
        setSize(900, 900);
        setBackground(new Color(255, 255, 255));

        var valorDepositar = new JLabel();

        var depositarValor = new JTextField();

        var depositar = new JButton();
        var voltarBotao = new JButton("<");

        voltarBotao.setBounds(5, 5, 30, 30);
        add(voltarBotao);

        valorDepositar.setBounds(10, 75, 350, 25);
        add(valorDepositar);

        depositarValor.setBounds(10, 100, 165, 25);
        add(depositarValor);

        depositar.setBounds(10, 150, 80, 30);
        add(depositar);

        voltarBotao.addActionListener(e -> frame.Show("MenuCliente"));

    }
}
