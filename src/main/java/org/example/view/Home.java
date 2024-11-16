package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Home extends JPanel {

    public Home(Frame frame) {

        // Inicialização dos componentes:

        // Buttons:
        var botaoCliente = new JButton("Cliente");
        var botaoFuncionario = new JButton("Funcionário");
        var botaoSair = new JButton("Sair");

        // Configurando o layout das panels
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        // Criando uma regra para que as opções fiquem sequênciadas verticalmente
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Configurando os elementos
        botaoCliente.setBounds(200, 50, 100, 30);
        add(botaoCliente);

        botaoFuncionario.setBounds(200, 150, 100, 30);
        add(botaoFuncionario);

        botaoSair.setBounds(200, 250, 100, 30);
        add(botaoSair);

        // Funcionabilidade dos botões
        botaoFuncionario.addActionListener(e -> frame.Show("LoginFuncionario"));
        botaoCliente.addActionListener(e -> frame.Show("LoginCliente"));
        botaoSair.addActionListener(e -> System.exit(0));
    }
}
