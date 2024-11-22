package org.example.view;

import org.example.controller.FuncionarioController;
import org.example.controller.UsuarioController;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import java.awt.*;

public class AlterarDados extends JPanel {
    public AlterarDados(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var funcionarioController = new FuncionarioController();

        var AConta = new JButton("Alterar conta");
        var AFuncionario = new JButton("Alterar funcionário");
        var ACliente = new JButton("Alterar cliente");
        var Voltar = new JButton("Voltar");

        AConta.setBounds(150, 25, 175, 30);
        add(AConta);

        AFuncionario.setBounds(150, 50, 175, 30);
        add(AFuncionario);

        ACliente.setBounds(150, 75, 175, 30);
        add(ACliente);

        Voltar.setBounds(150, 100, 175, 30);
        add(Voltar);

        AConta.addActionListener(e -> {
            frame.Show("AlterarConta");
        });

        AFuncionario.addActionListener(e -> {
            frame.Show("AlterarFuncionario");
        });

        ACliente.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Digite a senha de administrador:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                var usuarioController = new UsuarioController();
                if (usuarioController.login(Sessao.getUser(), senha, TipoUsuario.FUNCIONARIO)) {
                    var numeroConta = new JTextField();
                    int result = JOptionPane.showConfirmDialog(frame, numeroConta, "Digite o número da conta que deseja alterar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    String numero = numeroConta.getText();
                    if(result == JOptionPane.OK_OPTION) {
                        if (funcionarioController.buscarClienteId(numero)) {
                            frame.Show("AlterarCliente");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Nenhum cliente com esse número foi encontrado!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        Voltar.addActionListener(e -> {
            frame.Show("MenuFuncionario");
        });

    }
}
