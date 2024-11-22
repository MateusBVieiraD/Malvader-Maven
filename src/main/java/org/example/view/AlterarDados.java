package org.example.view;

import org.example.controller.ClienteController;
import org.example.controller.ContaController;
import org.example.controller.FuncionarioController;
import org.example.controller.UsuarioController;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.text.ParseException;

public class AlterarDados extends JPanel {
    public AlterarDados(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var funcionarioController = new FuncionarioController();
        var clienteController = new ClienteController();
        var contaController = new ContaController();

        var AConta = new JButton("Alterar conta");
        var AFuncionario = new JButton("Alterar funcionário");
        var ACliente = new JButton("Alterar cliente");
        var Voltar = new JButton("Voltar");

        AConta.setBounds(175, 30, 150, 30);
        add(AConta);

        AFuncionario.setBounds(175, 60, 150, 30);
        add(AFuncionario);

        ACliente.setBounds(175, 90, 150, 30);
        add(ACliente);

        Voltar.setBounds(175, 120, 150, 30);
        add(Voltar);

        AConta.addActionListener(e -> {
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
                        if (contaController.buscarContaId(numero)) {
                            Sessao.setId(numero);
                            frame.Show("AlterarConta");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Nenhuma conta com esse número foi encontrado!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        AFuncionario.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Digite a senha de administrador:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                var usuarioController = new UsuarioController();
                if (usuarioController.login(Sessao.getUser(), senha, TipoUsuario.FUNCIONARIO)) {
                    var numeroFuncionario = new JTextField();
                    int result = JOptionPane.showConfirmDialog(frame, numeroFuncionario, "Digite o número do funcionario que deseja alterar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    String numero = numeroFuncionario.getText();
                    if(result == JOptionPane.OK_OPTION) {
                        if (funcionarioController.buscarFuncionarioId(numero)) {
                            Sessao.setId(numero);
                            frame.Show("AlterarFuncionario");
                            frame.resizeFrame(900, 900);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Nenhum funcionario com esse número foi encontrado!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        ACliente.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Digite a senha de administrador:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                var usuarioController = new UsuarioController();
                if (usuarioController.login(Sessao.getUser(), senha, TipoUsuario.FUNCIONARIO)) {
                    JFormattedTextField cpfCliente;
                    try {

                        MaskFormatter cpfmask = new MaskFormatter("###.###.###-##");
                        cpfCliente = new JFormattedTextField(cpfmask);
                        cpfCliente.setColumns(15);
                    } catch (ParseException a) {
                        throw new RuntimeException(a);
                    }
                    int result = JOptionPane.showConfirmDialog(frame, cpfCliente, "Digite o CPF do cliente que deseja alterar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    String numero = cpfCliente.getText();
                    if(result == JOptionPane.OK_OPTION) {
                        if (clienteController.validarUsuarioCpf(numero)) {
                            Sessao.setId(numero);
                            frame.Show("AlterarCliente");
                            frame.resizeFrame(900, 900);
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
