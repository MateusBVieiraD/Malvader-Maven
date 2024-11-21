package org.example.view;

import org.example.Main;
import org.example.controller.ClienteController;
import org.example.controller.UsuarioController;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteLogin extends JPanel {
    public ClienteLogin(Frame frame){

        // Configurações
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        // Botões
        var botaoLoginCliente = new JButton("Login");

        // Textos
        var clienteTextLabel = new JLabel("Usuário");
        var clienteTextPassword = new JLabel("Senha");
        var clienteTextLogin = new JLabel("Login");

        // Campos de login
        var clienteLogin = new JTextField(20);
        var clienteSenha = new JPasswordField(20);

        // Configurando as bounds
        clienteTextLogin.setBounds(250, 10, 350, 25);
        add(clienteTextLogin);

        clienteTextLabel.setBounds(10, 25, 350, 25);
        add(clienteTextLabel);

        clienteLogin.setBounds(10, 50, 165, 25);
        add(clienteLogin);

        clienteTextPassword.setBounds(10, 75, 350, 25);
        add(clienteTextPassword);

        clienteSenha.setBounds(10, 100, 165, 25);
        add(clienteSenha);

        botaoLoginCliente.setBounds(10, 150, 80, 30);
        add(botaoLoginCliente);

        botaoLoginCliente.addActionListener(e -> {
            String nome = clienteLogin.getText();
            String senha = new String(clienteSenha.getPassword());
            Sessao.setUser(nome);
            Sessao.setPassword(senha);

            var clienteControl = new ClienteController();
            if(clienteControl.login(nome, senha, TipoUsuario.CLIENTE)){
                frame.Show("MenuCliente");
            } else {
                JOptionPane.showMessageDialog(frame, "Usuário ou senha inválido!");
                frame.Show("Home");
            }
            clienteSenha.setText("");
        });
    }
}
