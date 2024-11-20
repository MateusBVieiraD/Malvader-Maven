package org.example.view;

import org.example.controller.FuncionarioController;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerarRelatoriosLogin extends JPanel {
    public GerarRelatoriosLogin(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        // Botões
        var botaoLoginFuncionario = new JButton("Login");

        // Textos
        var employeeTextLabel = new JLabel("Usuário");
        var employeeTextPassword = new JLabel("Senha");

        // Campos de login
        var funcionarioLogin = new JTextField(20);
        var funcionarioSenha = new JPasswordField(20);

        // Configurando as bounds
        employeeTextLabel.setBounds(10, 25, 350, 25);
        add(employeeTextLabel);

        funcionarioLogin.setBounds(10, 50, 165, 25);
        add(funcionarioLogin);

        employeeTextPassword.setBounds(10, 75, 350, 25);
        add(employeeTextPassword);

        funcionarioSenha.setBounds(10, 100, 165, 25);
        add(funcionarioSenha);

        botaoLoginFuncionario.setBounds(10, 150, 80, 30);
        add(botaoLoginFuncionario);

        botaoLoginFuncionario.addActionListener( e -> {
                String login = funcionarioLogin.getText();
                String senha = new String(funcionarioSenha.getPassword());
                var funcionarioControl = new FuncionarioController();
                if(funcionarioControl.login(login, senha, TipoUsuario.FUNCIONARIO)){
                    frame.Show("MenuFuncionario");
                } else{
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválido!");
                    frame.Show("Home");
                }
                funcionarioSenha.setText("");
        });
    }
}
