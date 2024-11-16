package org.example.view;

import javax.swing.*;
import java.awt.*;

public class FuncionarioLogin extends JPanel {
    public FuncionarioLogin(Frame frame){

        // Configurações
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        // Botões
        var botaoLoginFuncionario = new JButton("Login");

        // Textos
        var employeeTextLabel = new JLabel("Usuário");
        var employeeTextPassword = new JLabel("Senha");

        // Campos de login
        var funcionarioLogin = new JTextField(20);
        var funcionarioSenha = new JPasswordField(20);

        // Configurando as bounds
        employeeTextLabel.setBounds(10, 25, 80, 25);
        add(employeeTextLabel);

        funcionarioLogin.setBounds(10, 50, 165, 25);
        add(funcionarioLogin);

        employeeTextPassword.setBounds(10, 75, 80, 25);
        add(employeeTextPassword);

        funcionarioSenha.setBounds(10, 100, 165, 25);
        add(funcionarioSenha);

        botaoLoginFuncionario.setBounds(10, 150, 80, 30);
        add(botaoLoginFuncionario);

        botaoLoginFuncionario.addActionListener(e -> frame.Show("MenuFuncionario"));
    }
}
