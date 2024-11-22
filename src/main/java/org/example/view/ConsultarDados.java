package org.example.view;

import org.example.controller.ClienteController;
import org.example.controller.ContaController;
import org.example.controller.FuncionarioController;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class ConsultarDados extends JPanel {
    public ConsultarDados(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var contaController = new ContaController();
        var clienteController = new ClienteController();
        var funcionarioController = new FuncionarioController();

        var CConta = new JButton("Consultar conta");
        var CFuncionario = new JButton("Consultar funcionário");
        var CCliente = new JButton("Consultar cliente");
        var Voltar = new JButton("Voltar");

        CConta.setBounds(175, 30, 150, 30);
        add(CConta);

        CFuncionario.setBounds(175, 60, 150, 30);
        add(CFuncionario);

        CCliente.setBounds(175, 90, 150, 30);
        add(CCliente);

        Voltar.setBounds(175, 120, 150, 30);
        add(Voltar);

        CConta.addActionListener(e -> {
            var numeroText = new JTextField();
            int confirmacao = JOptionPane.showConfirmDialog(frame, numeroText, "Digite o número da conta que deseja consultar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(confirmacao == JOptionPane.OK_OPTION) {
                String numero = numeroText.getText();
                JOptionPane.showMessageDialog(frame, contaController.consultarConta(numero));
            }
        });

        CFuncionario.addActionListener(e -> {
            var numeroText = new JTextField();
            int confirmacao = JOptionPane.showConfirmDialog(frame, numeroText, "Digite o número do funcionário que deseja consultar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(confirmacao == JOptionPane.OK_OPTION) {
                String numero = numeroText.getText();
                JOptionPane.showMessageDialog(frame, funcionarioController.consultarFuncionario(numero));
            }
        });

        CCliente.addActionListener(e -> {
            JFormattedTextField cpfCliente;
            try {
                MaskFormatter cpfmask = new MaskFormatter("###.###.###-##");
                cpfCliente = new JFormattedTextField(cpfmask);
                cpfCliente.setColumns(15);
            } catch (ParseException a) {
                throw new RuntimeException(a);
            }
            int confirmacao = JOptionPane.showConfirmDialog(frame, cpfCliente, "Digite o número do CPF do cliente que deseja consultar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(confirmacao == JOptionPane.OK_OPTION) {
                String numero = cpfCliente.getText();
                JOptionPane.showMessageDialog(frame, clienteController.consultarCliente(numero));
            }
        });

        Voltar.addActionListener(e -> frame.Show("MenuFuncionario"));
    }
}
