package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CadastroCliente extends JPanel {
    public CadastroCliente(Frame frame) {
        // Configurações
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        // Botões
        var botaoRegistrarCliente = new JButton("Cadastrar");

        // Textos
        var clienteCadastroTextLabel = new JLabel("Usuário");
        var clienteCadastroPassword = new JLabel("Senha");

        // Campos de login
        var clienteRegistroUsuario = new JTextField(20);
        var clienteRegistroSenha = new JPasswordField(20);

        clienteCadastroTextLabel.setBounds(10, 25, 80, 25);
        add(clienteCadastroTextLabel);

        clienteRegistroUsuario.setBounds(10, 50, 165, 25);
        add(clienteRegistroUsuario);

        clienteCadastroPassword.setBounds(10, 75, 80, 25);
        add(clienteCadastroPassword);

        clienteRegistroSenha.setBounds(10, 100, 165, 25);
        add(clienteRegistroSenha);

        botaoRegistrarCliente.setBounds(10, 150, 80, 30);
        add(botaoRegistrarCliente);
    }
}
