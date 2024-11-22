package org.example.view;

import org.example.controller.ContaController;
import org.example.controller.ControllerGeral;
import org.example.controller.FuncionarioController;
import org.example.controller.UsuarioController;
import org.example.entity.TipoUsuario;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MenuFuncionario extends JPanel {
    public MenuFuncionario(Frame frame) {
        var usuarioController = new UsuarioController();

        setLayout(null);
        setSize(500, 400);
        setBackground(new Color(255, 255, 255));

        var aberturaFB = new JButton("Abertura de conta");
        var encerramentoFB = new JButton("Encerramento de conta");
        var consultarFB = new JButton("Consultar dados");
        var alterarFB = new JButton("Alterar dados");
        var cadastroFB = new JButton("Cadastro de funcionários");
        var gerarFB = new JButton("Gerar relatórios");
        var sairFB = new JButton("Sair");

        aberturaFB.setBounds(150, 0, 200, 30);
        add(aberturaFB);

        encerramentoFB.setBounds(150, 30, 200, 30);
        add(encerramentoFB);

        consultarFB.setBounds(150, 60, 200, 30);
        add(consultarFB);

        alterarFB.setBounds(150, 90, 200, 30);
        add(alterarFB);

        cadastroFB.setBounds(150, 120, 200, 30);
        add(cadastroFB);

        gerarFB.setBounds(150, 150, 200, 30);
        add(gerarFB);

        sairFB.setBounds(150, 180, 200, 30);
        add(sairFB);

        aberturaFB.addActionListener(e -> {
            frame.Show("ContaFuncionario");
        });

        encerramentoFB.addActionListener(e -> {
            var contacontroller = new ContaController();
            var controlergeral = new ControllerGeral();
            JPasswordField senhaConfirm = new JPasswordField();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Digite a senha de administrador:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                if (usuarioController.login(Sessao.getUser(), senha, TipoUsuario.FUNCIONARIO)) {
                    var numeroConta = new JTextField();
                    int result = JOptionPane.showConfirmDialog(frame, numeroConta, "Digite o número da conta que deseja excluir:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    String numero = numeroConta.getText();
                    if(result == JOptionPane.OK_OPTION) {
                        if (Objects.equals(contacontroller.consultarContaParaRemover(String.valueOf(numero)), "essa conta não existe")) {
                            JOptionPane.showMessageDialog(frame, contacontroller.consultarContaParaRemover(String.valueOf(numero)));
                        } else {
                            result = JOptionPane.showConfirmDialog(frame, contacontroller.consultarContaParaRemover(numero), "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        }
                        if (result == JOptionPane.YES_OPTION) {
                            boolean certeza = controlergeral.removerConta(numero);
                            if (certeza) {
                                JOptionPane.showMessageDialog(frame, "Conta removida com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error ao remover a conta!");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        consultarFB.addActionListener(e -> {
            frame.Show("ConsultarDados");
        });

        alterarFB.addActionListener(e -> frame.Show("AlterarDados"));

        cadastroFB.addActionListener(e -> {
                frame.Show("CadastroFuncionarios");
                frame.resizeFrame(900, 900);
        });

        gerarFB.addActionListener(e -> {
            JPasswordField senhaConfirm = new JPasswordField();
            var funcionarioController = new FuncionarioController();
            int resultado = JOptionPane.showConfirmDialog(frame, senhaConfirm, "Confirme sua senha:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(senhaConfirm.getPassword());
            if(resultado == JOptionPane.OK_OPTION) {
                if (usuarioController.login(Sessao.getUser(), senha, TipoUsuario.FUNCIONARIO)) {
                    try {
                        funcionarioController.gerarRelatorio(Sessao.getUser(), senha, TipoUsuario.FUNCIONARIO);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Senha incorreta!");
                }
            }
        });

        sairFB.addActionListener(e -> frame.Show("Home"));

    }
}
