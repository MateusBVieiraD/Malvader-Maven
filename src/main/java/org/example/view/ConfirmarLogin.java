package org.example.view;

import org.example.controller.ClienteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmarLogin extends JPanel {
    public ConfirmarLogin(Frame frame){
        var clienteLog = new ClienteLogin(null);

        setLayout(null);
        setSize(900, 900);
        setBackground(new Color(255, 255, 255));

        // Botões
        var botaoLoginCliente = new JButton("Confirmar login");

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

        clienteTextLabel.setBounds(10, 25, 80, 25);
        add(clienteTextLabel);

        clienteLogin.setBounds(10, 50, 165, 25);
        clienteLogin.setEditable(false);
        add(clienteLogin);

        clienteTextPassword.setBounds(10, 75, 80, 25);
        add(clienteTextPassword);

        clienteSenha.setBounds(10, 100, 165, 25);
        add(clienteSenha);

        botaoLoginCliente.setBounds(10, 150, 80, 30);
        add(botaoLoginCliente);

        botaoLoginCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = clienteLogin.getText();
                String senha = new String(clienteSenha.getPassword());
                var clienteControl = new ClienteController();
                if(clienteControl.login(login, senha)){
                    frame.Show("Depositar");
                } else{
                    JOptionPane.showMessageDialog(frame, "Senha inválida!");
                    frame.Show("MenuCliente");
                }
                clienteSenha.setText("");
            }
        });
    }
}
