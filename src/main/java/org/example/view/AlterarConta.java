package org.example.view;

import org.example.controller.ContaController;
import org.example.entity.TipoConta;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlterarConta extends JPanel {
    public AlterarConta(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var contaController = new ContaController();

        var tipoContaL = new JLabel("Tipo de conta:");
        var limiteL = new JLabel("Limite disponível:");
        var dataVencimentoL = new JLabel("Data de vencimento:");

        var tipoContaT = new JTextField();
        var limiteT = new JTextField();
        var dataVencimentoT = new JTextField();

        var Alterar = new JButton("Alterar");
        var Voltar = new JButton("Voltar");

        Voltar.setBounds(5, 5, 45, 45);
        add(Voltar);

        tipoContaL.setBounds(10, 50, 350, 25);
        add(tipoContaL);

        tipoContaT.setBounds(10, 75, 165, 25);
        add(tipoContaT);

        limiteL.setBounds(10, 100, 350, 25);
        add(limiteL);

        limiteT.setBounds(10, 125, 165, 25);
        add(limiteT);

        dataVencimentoL.setBounds(10, 150, 350, 25);
        add(dataVencimentoL);

        dataVencimentoT.setBounds(10, 175, 165, 25);
        add(dataVencimentoT);

        Alterar.setBounds(10, 225, 80, 30);
        add(Alterar);

        Alterar.addActionListener(e -> {
            String tipoConta = tipoContaT.getText();
            String limite = limiteT.getText();
            String dataVencimento = dataVencimentoT.getText();

            if(contaController.alterarConta(Sessao.getId(), TipoConta.CORRENTE, BigDecimal.valueOf(Double.parseDouble(limite)) , LocalDate.parse(dataVencimento))){
                tipoContaT.setText("");
                dataVencimentoT.setText("");
                limiteT.setText("");
                JOptionPane.showMessageDialog(frame, "Conta atualizada com sucesso!");
            }else{
                JOptionPane.showMessageDialog(frame, "Error ao atualizar a conta!");
            }
        });

        Voltar.addActionListener(e-> frame.Show("MenuFuncionario"));
    }
}
