package org.example.view;

import org.example.entity.ContaPoupanca;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

class NumericDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && string.matches("\\d+")) { // Permite apenas números
            super.insertString(fb, offset, string, attr);
        } else {
            showPopup();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text != null && text.matches("\\d+")) { // Permite apenas números
            super.replace(fb, offset, length, text, attrs);
        } else {
            showPopup();
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    // Método para exibir o popup
    private void showPopup() {
        SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira apenas números!",
                        "Entrada Inválida",
                        JOptionPane.WARNING_MESSAGE)
        );
    }
}

public class ContaPoupancaF extends JPanel {
    public ContaPoupancaF(Frame frame){
        setLayout(null);
        setSize(900, 900);
        setBackground(new Color(255, 255, 255));

        var titulo = new JLabel("Criar conta poupança");
        var agencia = new JLabel("Agência:");
        var numeroConta = new JLabel("Número da conta:");
        var taxaRendimento = new JLabel("Taxa de rendimento da conta:");
        var nomeCliente = new JLabel("Nome do cliente:");
        var cpfCliente = new JLabel("CPF do cliente:");
        var dataNascimento = new JLabel("Data de nascimento:");
        var telefoneContato = new JLabel("Telefone de contato:");
        var cepCliente = new JLabel("CEP:");
        var localCliente = new JLabel("Local:");
        var numeroCasa = new JLabel("Número da casa:");
        var bairroCliente = new JLabel("Bairro:");
        var cidadeCliente = new JLabel("Cidade:");
        var estadoCliente = new JLabel("Estado:");
        var senhaCliente = new JLabel("Senha:");

        var agenciaText = new JTextField();
        var numeroContaText = new JTextField();
        var rendimentoTaxa = new JTextField();
        var nomeText = new JTextField();

        JFormattedTextField cpfText;
        try {

            MaskFormatter cpfmask = new MaskFormatter("###.###.###-##");
            cpfText = new JFormattedTextField(cpfmask);
            cpfText.setColumns(15);

            PlainDocument doc = (PlainDocument) cpfText.getDocument();
            doc.setDocumentFilter(new org.example.view.NumericDocumentFilter());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        var nascimentoText = new JTextField();

        JFormattedTextField telefoneText;
        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
            telefoneText = new JFormattedTextField(telefoneMask);
            telefoneText.setColumns(14);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

        JFormattedTextField cepText;
        try {
            MaskFormatter cepTextMask = new MaskFormatter("#####-###");
            cepText = new JFormattedTextField(cepTextMask);
            cepText.setColumns(15);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        var localText = new JTextField();
        var numeroCasaText = new JTextField();
        var bairroText = new JTextField();
        var cidadeText = new JTextField();
        var estadoText = new JTextField();
        var senhaClienteText = new JTextField();

        var criarBotao = new JButton("Criar");
        var botaoVoltar = new JButton("<");

        botaoVoltar.setBounds(5, 5, 30, 30);
        add(botaoVoltar);

        titulo.setBounds(450, 10, 350, 25);
        add(titulo, BorderLayout.CENTER);

        agencia.setBounds(10, 25, 350, 25);
        add(agencia);

        agenciaText.setBounds(10, 50, 165, 25);
        add(agenciaText);

        numeroConta.setBounds(10, 75, 350, 25);
        add(numeroConta);

        numeroContaText.setBounds(10, 100, 165, 25);
        add(numeroContaText);

        taxaRendimento.setBounds(10, 125, 350, 25);
        add(taxaRendimento);

        rendimentoTaxa.setBounds(10, 150, 165, 25);
        add(rendimentoTaxa);

        nomeCliente.setBounds(10, 175, 350, 25);
        add(nomeCliente);

        nomeText.setBounds(10, 200, 165, 25);
        add(nomeText);

        cpfCliente.setBounds(10, 225, 350, 25);
        add(cpfCliente);

        cpfText.setBounds(10, 250, 165, 25);
        add(cpfText);

        dataNascimento.setBounds(10, 275, 350, 25);
        add(dataNascimento);

        nascimentoText.setBounds(10, 300, 165, 25);
        add(nascimentoText);

        telefoneContato.setBounds(10, 325, 350, 25);
        add(telefoneContato);

        telefoneText.setBounds(10, 350, 165, 25);
        add(telefoneText);

        cepCliente.setBounds(10, 375, 350, 25);
        add(cepCliente);

        cepText.setBounds(10, 400, 165, 25);
        add(cepText);

        localCliente.setBounds(10, 425, 350, 25);
        add(localCliente);

        localText.setBounds(10, 450, 165, 25);
        add(localText);

        numeroCasa.setBounds(10, 475, 350, 25);
        add(numeroCasa);

        numeroCasaText.setBounds(10, 500, 165, 25);
        add(numeroCasaText);

        bairroCliente.setBounds(10, 525, 350, 25);
        add(bairroCliente);

        bairroText.setBounds(10, 550, 165, 25);
        add(bairroText);

        cidadeCliente.setBounds(10, 575, 350, 25);
        add(cidadeCliente);

        cidadeText.setBounds(10, 600, 165, 25);
        add(cidadeText);

        estadoCliente.setBounds(10, 625, 350, 25);
        add(estadoCliente);

        estadoText.setBounds(10, 650, 165, 25);
        add(estadoText);

        senhaCliente.setBounds(10, 675, 350, 25);
        add(senhaCliente);

        senhaClienteText.setBounds(10, 700, 165, 25);
        add(senhaClienteText);

        criarBotao.setBounds(10, 800, 80, 30);
        add(criarBotao);

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.Show("ContaFuncionario");
                frame.resizeFrame(500, 400);
            }
        });
    }
}
