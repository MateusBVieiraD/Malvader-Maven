package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Frame extends JFrame {
    private CardLayout cardLayout;
    private JPanel container;

    public Frame(){
        Programa();
    }

    private void Programa(){


        // Alteração do tema do JOptionPane
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicializando uma imagem
        var image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/SimplistaPorIA.jpg")));
        final int largura = 200;
        final int altura = 200;
        var imagemRedimensao = image.getImage().getScaledInstance(largura, altura, Image.SCALE_AREA_AVERAGING);
        var imagemRedimension = new ImageIcon(imagemRedimensao);
        var label = new JLabel(imagemRedimension);
        label.setBounds(10, 10, 200, 200);

        // Inicialização dos componentes:

        // Instânciando Panels:
        var Home = new Home(this);
        var clienteLogin = new ClienteLogin(this);
        var funcionarioLogin = new FuncionarioLogin(this);
        var menuFuncionario = new MenuFuncionario(this);
        var menuCliente = new MenuCliente(this);
        var contaFuncionario = new ContaFuncionario(this);
        var contaCorrenteF = new ContaCorrenteF(this);
        var contaPoupancaF = new ContaPoupancaF(this);
        var confirmarLogin = new ConfirmarLogin(this);
        var deposito = new Depositar(this);
        var saque = new Saque(this);
        var encerrarConta = new EncerramentoConta(this);
        var cadastroFuncionarios = new CadastroFuncionarios(this);
        var consultarDados = new ConsultarDados(this);
        var alterarDados = new AlterarDados(this);
        var saldo = new Saldo(this);
        var extrato = new Extrato(this);
        var gerarRelatorio = new GerarRelatoriosLogin(this);
        var consultarConta = new ConsultarConta(this);
        var consultarFuncionario = new ConsultarFuncionario(this);
        var consultarCliente = new ConsultarCliente(this);
        var alterarConta = new AlterarConta(this);
        var alterarFuncionario = new AlterarFuncionario(this);
        var alterarCliente = new AlterarCliente(this);

        // CardLayout:
        cardLayout = new CardLayout();

        // Panels:
        container = new JPanel(cardLayout);

        // Configurando a tela
        setTitle("Malvader");
        setSize(500, 400);
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adicionando os paineis dentro do conteiner
        container.add(Home, "Home");
        container.add(clienteLogin, "LoginCliente");
        container.add(funcionarioLogin, "LoginFuncionario");
        container.add(menuFuncionario, "MenuFuncionario");
        container.add(menuCliente, "MenuCliente");
        container.add(contaFuncionario, "ContaFuncionario");
        container.add(contaPoupancaF, "ContaPoupancaF");
        container.add(contaCorrenteF, "ContaCorrenteF");
        container.add(confirmarLogin, "ConfirmarLogin");
        container.add(saldo, "Saldo");
        container.add(deposito, "Depositar");
        container.add(saque, "Saque");
        container.add(extrato, "Extrato");
        container.add(encerrarConta, "EncerrarConta");
        container.add(cadastroFuncionarios, "CadastroFuncionarios");
        container.add(consultarDados, "ConsultarDados");
        container.add(alterarDados, "AlterarDados");
        container.add(gerarRelatorio, "GerarRelatorio");
        container.add(consultarConta, "ConsultarConta");
        container.add(consultarFuncionario, "ConsultarFuncionario");
        container.add(consultarCliente, "ConsultarCliente");
        container.add(alterarConta, "AlterarConta");
        container.add(alterarFuncionario, "AlterarFuncionario");
        container.add(alterarCliente, "AlterarCliente");


        // Adicionando o Panel ao Frame
        add(container);

        cardLayout.show(container, "Home");
        // Criando uma regra para que as opções fiquem sequênciadas verticalmente
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Setando se a imagem está sendo carregada corretamente
        if (image.getIconWidth() == -1) {  // largura -1 indica que a imagem não foi carregada
            System.out.println("Erro: Imagem não encontrada ou caminho incorreto.");
        } else {
            System.out.println("Imagem carregada com sucesso!");
        }

        // Funcionabilidade dos botões
//        Home.addActionListener(e -> cardLayout.show(panelConteiner, "LoginCliente"));
//        Home.addActionListener(e -> System.exit(0));

        // Seta a visibilidade do Frame
        setVisible(true);
    }
    public void Show(String nomePainel){
        cardLayout.show(container, nomePainel);
    }

    public void resizeFrame(int width, int height){
        setSize(width, height);
        setLocationRelativeTo(null);
    }
}
