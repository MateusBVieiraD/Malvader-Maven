package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class homeView extends JFrame {
    public homeView() {
        Programa();
    }

    private void Programa() {

        // Alteração do tema do JOptionPane
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Inicialização dos componentes:

        // CardLayout:
        var cardLayout = new CardLayout();

        // Panels:
        var panelConteiner = new JPanel(cardLayout);
        var home = new JPanel();
        var loginCliente = new JPanel();
        var loginFuncionario = new JPanel();

        // Buttons:
        var botaoCliente = new JButton("Cliente");
        var botaoFuncionario = new JButton("Funcionário");
        var botaoLoginCliente = new JButton("Login");
        var botaoLoginFuncionario = new JButton("Login");
        var botaoSair = new JButton("Sair");

        // Labels:
        var userTextLabel = new JLabel("Usuário");
        var userTextPassword = new JLabel("Senha");
        var employeeTextLabel = new JLabel("Usuário");
        var employeeTextPassword = new JLabel("Senha");

        // TextFild:
        var usuarioLogin = new JTextField(20);
        var funcionarioLogin = new JTextField(20);

        // PasswordFields:
        var usuarioSenha = new JPasswordField(20);
        var funcionarioSenha = new JPasswordField(20);

        // Adicionando os paineis dentro do conteiner
        panelConteiner.add(home, "Home");
        panelConteiner.add(loginCliente, "LoginCliente");
        panelConteiner.add(loginFuncionario, "LoginFuncionario");

        // Inicializando uma imagem
        var image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/SimplistaPorIA.jpg")));
        final int largura = 200;
        final int altura = 200;
        var imagemRedimensao = image.getImage().getScaledInstance(largura, altura, Image.SCALE_AREA_AVERAGING);
        var imagemRedimension = new ImageIcon(imagemRedimensao);
        var label = new JLabel(imagemRedimension);
        label.setBounds(10, 10, 200, 200);

        // Configurando a tela
        setTitle("Malvader");
        setSize(500, 400);
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adicionando o Panel ao Frame
        add(panelConteiner);

        // Configurando o layout das panels
        home.setLayout(null);
        home.setSize(500, 400);
        home.setBackground(new Color(255, 255, 255));

        loginCliente.setLayout(null);
        loginCliente.setSize(500, 400);
        loginCliente.setBackground(new Color(255, 255, 255));

        loginFuncionario.setLayout(null);
        loginFuncionario.setSize(500, 400);
        loginFuncionario.setBackground(new Color(255, 255, 255));

        // Criando uma regra para que as opções fiquem sequênciadas verticalmente
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Configurando o panel da home
        botaoCliente.setBounds(85, 250, 80, 30);
        home.add(botaoCliente);

        botaoFuncionario.setBounds(355, 250, 80, 30);
        home.add(botaoFuncionario);

        botaoSair.setBounds(220, 300, 80, 30);
        home.add(botaoSair);

        // Funcionabilidade dos botões
        botaoFuncionario.addActionListener(e -> cardLayout.show(panelConteiner, "LoginFuncionario"));
        botaoCliente.addActionListener(e -> cardLayout.show(panelConteiner, "LoginCliente"));
        botaoSair.addActionListener(e -> System.exit(0));

        // Configurando o panel de cliente
        userTextLabel.setBounds(10, 25, 80, 25);
        loginCliente.add(userTextLabel);

        usuarioLogin.setBounds(10, 50, 165, 25);
        loginCliente.add(usuarioLogin);

        userTextPassword.setBounds(10, 75, 80, 25);
        loginCliente.add(userTextPassword);

        usuarioSenha.setBounds(10, 100, 165, 25);
        loginCliente.add(usuarioSenha);

        botaoLoginCliente.setBounds(10, 150, 80, 30);
        loginCliente.add(botaoLoginCliente);

        // Configurando o panel de funcionário
        employeeTextLabel.setBounds(10, 25, 80, 25);
        loginFuncionario.add(employeeTextLabel);

        funcionarioLogin.setBounds(10, 50, 165, 25);
        loginFuncionario.add(funcionarioLogin);

        employeeTextPassword.setBounds(10, 75, 80, 25);
        loginFuncionario.add(employeeTextPassword);

        funcionarioSenha.setBounds(10, 100, 165, 25);
        loginFuncionario.add(funcionarioSenha);

        botaoLoginFuncionario.setBounds(10, 150, 80, 30);
        loginFuncionario.add(botaoLoginFuncionario);

        // Setando se a imagem está sendo carregada corretamente
        if (image.getIconWidth() == -1) {  // largura -1 indica que a imagem não foi carregada
            System.out.println("Erro: Imagem não encontrada ou caminho incorreto.");
        } else {
            System.out.println("Imagem carregada com sucesso!");
        }

        // Seta a visibilidade do Frame
        setVisible(true);
    }
}
