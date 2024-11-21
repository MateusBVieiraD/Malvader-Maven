package org.example.view;

import org.example.controller.UsuarioController;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import java.awt.*;

public class EncerrarContaConfirm extends JPanel {
    public EncerrarContaConfirm(Frame frame){
        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var usuariocontrol = new UsuarioController();

        var confirmLogin = new JLabel("Usuário:");
        var confirmSenha = new JLabel("Senha:");

        var usuario = new JTextField();
        var senha = new JPasswordField();

        var entrar = new JButton("Entrar");

        confirmLogin.setBounds(10, 25, 350, 25);
        add(confirmLogin);

        usuario.setBounds(10, 50, 165, 25);
        add(usuario);

        confirmSenha.setBounds(10, 75, 350, 25);
        add(confirmSenha);

        senha.setBounds(10, 100, 165, 25);
        add(senha);

        entrar.setBounds(10, 200, 80, 30);
        add(entrar);

        entrar.addActionListener(e -> {
            String userget = usuario.getText();
            String passwordget = new String(senha.getPassword());
            if(usuariocontrol.login(userget, passwordget, TipoUsuario.FUNCIONARIO)){
                frame.Show("EncerrarConta");
            }else{
                JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                frame.Show("MenuFuncionario");
            }
        });
    }
}
