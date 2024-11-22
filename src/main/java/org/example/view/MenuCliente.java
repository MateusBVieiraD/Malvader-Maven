package org.example.view;

import org.example.controller.ClienteController;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuCliente extends JPanel {
    public MenuCliente(Frame frame){
        var clienteControl = new ClienteController();

        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var saldoCB = new JButton("Saldo");
        var depositoCB = new JButton("Deposito");
        var saqueCB = new JButton("Saque");
        var extratoCB = new JButton("Extrato");
        var consultarCB = new JButton("Consultar limite");
        var sairCB = new JButton("Sair");

        saldoCB.setBounds(150, 30, 200, 30);
        add(saldoCB);

        depositoCB.setBounds(150, 60, 200, 30);
        add(depositoCB);

        saqueCB.setBounds(150, 90, 200, 30);
        add(saqueCB);

        extratoCB.setBounds(150, 120, 200, 30);
        add(extratoCB);

        consultarCB.setBounds(150, 150, 200, 30);
        add(consultarCB);

        sairCB.setBounds(150, 180, 200, 30);
        add(sairCB);

        saldoCB.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Confirme sua senha:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                if (clienteControl.login(Sessao.getUser(), senha, TipoUsuario.CLIENTE)) {
                    System.out.println("Teste1");
                    String result = String.valueOf(clienteControl.saldo(Sessao.getUser(), senha));
                    JOptionPane.showMessageDialog(frame, result);
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        depositoCB.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            JTextField quantia = new JTextField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Confirme sua senha:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION){
                if(clienteControl.login(Sessao.getUser(), senha, TipoUsuario.CLIENTE)){
                    resultado = JOptionPane.showConfirmDialog(frame, quantia, "Adicione a quantia que deseja depositar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(resultado == JOptionPane.OK_OPTION){
                        double quantidade = Double.parseDouble(quantia.getText());
                        clienteControl.depositar(Sessao.getUser(), senha, quantidade);
                        JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        saqueCB.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            JTextField quantia = new JTextField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Confirme sua senha:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                if (clienteControl.login(Sessao.getUser(), senha, TipoUsuario.CLIENTE)) {
                    resultado = JOptionPane.showConfirmDialog(frame, quantia, "Adicione a quantia que deseja sacar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultado == JOptionPane.OK_OPTION) {
                        double quantidade = Double.parseDouble(quantia.getText());
                        if(clienteControl.saque(Sessao.getUser(), senha, quantidade)) {
                            JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
                        }else{
                            JOptionPane.showMessageDialog(frame, "O valor a ser sacado é maior que o saldo!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        extratoCB.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Confirme sua senha:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                if (clienteControl.login(Sessao.getUser(), senha, TipoUsuario.CLIENTE)) {
                    try {
                        ClienteController.extrato();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        consultarCB.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
        int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Confirme sua senha:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String senha = new String(senhaConfirm.getPassword());
        if(resultado == JOptionPane.OK_OPTION) {
            if (clienteControl.login(Sessao.getUser(), senha, TipoUsuario.CLIENTE)) {
                if(clienteControl.consultarLimite(Sessao.getUser(), senha) != -1){
                    String limite = String.valueOf(clienteControl.consultarLimite(Sessao.getUser(), senha));
                    JOptionPane.showMessageDialog(frame, limite);
                } else {
                  JOptionPane.showMessageDialog(frame, "Limite indisponível!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Senha incorreta!");
            }
        }
        });

        sairCB.addActionListener(e -> frame.Show("Home"));
    }
}
