package org.example;

import org.example.bancoController.RelatorioCSV;
import org.example.bancoController.HibernateToCSV;
import org.example.controller.*;
import org.example.dao.UsuarioDAO;
import org.example.entity.*;
import org.example.view.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Main {
    public static String nome = "A";
    public static void main(String[] args) throws IOException {

        var view = new Frame();


        UsuarioDAO usuarioDAO = new UsuarioDAO();

        FuncionarioController funcionarioController = new FuncionarioController();
        ClienteController clienteController = new ClienteController();
        UsuarioController usuarioController = new UsuarioController();
        EnderecoController enderecoController = new EnderecoController();
        ContaController contaController = new ContaController();
        ContaCorrenteController contaCorrenteController = new ContaCorrenteController();
        ContaPoupancaController contaPoupancaController = new ContaPoupancaController();
        RelatorioController relatorioController = new RelatorioController();
        TransacaoController transacaoController = new TransacaoController();


        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Luis");
        usuario.setSenha("teste123");
        usuario.setCpf("123456789");
        usuario.setTelefone("(61) 4002-8922");
        usuario.setTipoUsuario(TipoUsuario.FUNCIONARIO);
        usuario.setDataNascimento(LocalDate.parse("2003-09-01"));

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Administrador");
        funcionario.setCodigoFuncionario("456");


        Endereco endereco = new Endereco();
        endereco.setCep("71.400-500");
        endereco.setLocal("QNL");
        endereco.setEstado("DF");
        endereco.setCidade("Brasília");
        endereco.setNumeroCasa(52);
        endereco.setBairro("Taguatinga");

        Relatorio relatorio = new Relatorio();
        relatorio.setTipoRelatorio("SAQUE");
        relatorio.setConteudo("1250");
        relatorio.setTimestamp(Timestamp.valueOf("2024-10-09 10:30:00"));

        Relatorio relatorio2 = new Relatorio();
        relatorio2.setTipoRelatorio("SAQUE");
        relatorio2.setConteudo("1250");
        relatorio2.setTimestamp(Timestamp.valueOf("2024-10-09 10:30:00"));




//Start de usuario
        usuarioController.cadastroUsuario(usuario);

//Usuario ou é funcionario
        funcionario.setUsuario(usuario);
        funcionarioController.cadastroFuncionario(funcionario);
//Ou cliente

//após processamento do endereço
        endereco.setUsuario(usuario);
        enderecoController.cadastroEndereco(endereco);

//depois acesso a conta


//conta corrente

//conta poupanca


//relatorio
        relatorio.setFuncionario(funcionario);
        relatorioController.cadastroRelatorio(relatorio);

//transacao


        // Não sei o porque, mas ele tá dando error kkkkk
        HibernateToCSV hibernateToCSV = new HibernateToCSV();
        hibernateToCSV.exportarParaCSV();


        //Fechar a conexão com o banco

        ControllerGeral controllerGeral = new ControllerGeral();



        funcionarioController.fecharOperacao();
        clienteController.fecharOperacao();
        usuarioController.fecharOperacao();
        enderecoController.fecharOperacao();
        contaController.fecharOperacao();
        contaCorrenteController.fecharOperacao();
        contaPoupancaController.fecharOperacao();
        relatorioController.fecharOperacao();
        transacaoController.fecharOperacao();
    }
}
