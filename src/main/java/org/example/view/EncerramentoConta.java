package org.example.view;

import org.example.controller.ContaController;
import org.example.controller.ControllerGeral;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EncerramentoConta extends JPanel {
    public EncerramentoConta(Frame frame) {
        var controlergeral = new ControllerGeral();
        var contacontroller = new ContaController();

        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var digitarConta = new JLabel("Digite o número da conta que deseja finalizar:");

        var contaCodigo = new JTextField();

        var Voltar = new JButton("<");
        var encerrar = new JButton("Encerrar");

        Voltar.setBounds(5, 5, 25, 25);
        add(Voltar);

        digitarConta.setBounds(10, 75, 350, 25);
        add(digitarConta);

        contaCodigo.setBounds(10, 100, 165, 25);
        add(contaCodigo);

        encerrar.setBounds(10, 150, 80, 30);
        add(encerrar);

        Voltar.addActionListener(e -> {
            frame.Show("MenuFuncionario");
        });

        encerrar.addActionListener(e -> {
            String numero = contaCodigo.getText();
            int result = -1;
            if(Objects.equals(contacontroller.consultarContaParaRemover(String.valueOf(numero)), "essa conta não existe")) {
                JOptionPane.showMessageDialog(frame, contacontroller.consultarContaParaRemover(String.valueOf(numero)));
            }else {
                result = JOptionPane.showConfirmDialog(frame, contacontroller.consultarContaParaRemover(numero), "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if(result == JOptionPane.YES_OPTION) {
                boolean certeza = controlergeral.removerConta(numero);
                if (certeza) {
                    JOptionPane.showMessageDialog(frame, "Conta removida com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Error ao remover a conta!");
                }
            }
        });

    }
}
