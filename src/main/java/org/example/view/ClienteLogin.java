package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ClienteLogin extends JPanel {
    public ClienteLogin(Frame frame){

        // Configurações
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        // Botões
        var botaoLoginCliente = new JButton("Login");
        var botaoCadastroCliente = new JButton("Registrar");

        // Textos
        var clienteTextLabel = new JLabel("Usuário");
        var clienteTextPassword = new JLabel("Senha");
        var clienteTextLogin = new JLabel("Login");

        // Campos de login
        var clienteLogin = new JTextField(20);
        var clienteSenha = new JPasswordField(20);

        // Configurando as bounds
        clienteTextLogin.setBounds(250, 10, 80, 25);
        add(clienteTextLogin);

        clienteTextLabel.setBounds(10, 25, 80, 25);
        add(clienteTextLabel);

        clienteLogin.setBounds(10, 50, 165, 25);
        add(clienteLogin);

        clienteTextPassword.setBounds(10, 75, 80, 25);
        add(clienteTextPassword);

        clienteSenha.setBounds(10, 100, 165, 25);
        add(clienteSenha);

        botaoLoginCliente.setBounds(10, 150, 80, 30);
        add(botaoLoginCliente);

        botaoCadastroCliente.setBounds(10, 200, 80, 30);
        add(botaoCadastroCliente);

        botaoLoginCliente.addActionListener(e -> frame.Show("MenuCliente"));

        botaoCadastroCliente.addActionListener(e -> frame.Show("CadastroCliente"));
    }
}
