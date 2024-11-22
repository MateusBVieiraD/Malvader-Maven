package org.example.view;

import javax.swing.*;
import java.awt.*;

public class AlterarCliente extends JPanel {
    public AlterarCliente(Frame frame){
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setLayout(null);

        var telefone = new JLabel();
        var local = new JLabel();
        var numeroCasa = new JLabel();
        var cep = new JLabel();
        var bairro = new JLabel();
        var cidade = new JLabel();
        var estado = new JLabel();

        var telefoneT = new JTextField();
        var localT = new JTextField();
        var numeroCasaT = new JTextField();
        var cepT = new JTextField();
        var bairroT = new JTextField();
        var cidadeT = new JTextField();
        var estadoT = new JTextField();

        var Voltar = new JButton("<");
        var Alterar = new JButton("Alterar");

        Voltar.setBounds(5, 5, 30, 30);
        add(Voltar);

        telefone.setBounds(10, 25, 350, 25);
        add(telefone);

        telefoneT.setBounds(10, 50, 165, 25);
        add(telefoneT);

        local.setBounds(10, 75, 350, 25);
        add(local);

        localT.setBounds(10, 100, 165, 25);
        add(localT);

        numeroCasa.setBounds(10, 125, 350, 25);
        add(numeroCasa);

        numeroCasaT.setBounds(10, 150, 165, 25);
        add(numeroCasaT);

        cep.setBounds(10, 175, 350, 25);
        add(cep);

        cepT.setBounds(10, 200, 165, 25);
        add(cepT);

        bairro.setBounds(10, 225, 350, 25);
        add(bairro);

        bairroT.setBounds(10, 250, 165, 25);
        add(bairroT);

        cidade.setBounds(10, 275, 350, 25);
        add(cidade);

        cidadeT.setBounds(10, 300, 165, 25);
        add(cidadeT);

        estado.setBounds(10, 325, 350, 25);
        add(estado);

        estadoT.setBounds(10, 350, 165, 25);
        add(estadoT);

        Alterar.setBounds(10, 450, 80, 30);
        add(Alterar);

        Alterar.addActionListener(e -> {

        });
    }
}
