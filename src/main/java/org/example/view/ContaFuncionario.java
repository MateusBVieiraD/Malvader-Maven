package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ContaFuncionario extends JPanel {
    public ContaFuncionario(Frame frame){
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var contaPF = new JButton("Conta poupança");
        var contaCF = new JButton("Conta corrente");
        var sairCF = new JButton("Sair");

        contaPF.setBounds(150, 30, 200, 30);
        add(contaPF);

        contaCF.setBounds(150, 60, 200, 30);
        add(contaCF);

        sairCF.setBounds(150, 90, 200, 30);
        add(sairCF);

        sairCF.addActionListener(e -> frame.Show("MenuFuncionario"));
    }
}
