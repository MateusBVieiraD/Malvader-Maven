package org.example;

import org.example.bancoController.ExtratoCSV;
import org.example.bancoController.HibernateToCSV;
import org.example.config.EntityFactory;
import org.example.controller.*;
import org.example.dao.ClienteDAO;
import org.example.dao.TransacaoDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.*;
import org.example.view.*;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Main {

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


// Criando um novo funcionário

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Mateus");
        usuario.setSenha("oiteste123");
        usuario.setCpf("123456789");
        usuario.setTelefone("(61) 4002-8922");
        usuario.setTipoUsuario(TipoUsuario.FUNCIONARIO);
        usuario.setDataNascimento(LocalDate.parse("2004-10-09"));

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Administrador");
        funcionario.setCodigoFuncionario("123");

        Cliente cliente = new Cliente();

        ContaEntity conta = new ContaEntity();
        conta.setSaldo(BigDecimal.valueOf(2000));
        conta.setNumeroConta("12");
        conta.setAgencia("BB");
        conta.setTipoconta(TipoConta.CORRENTE);


        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setLimite(BigDecimal.valueOf(2050));
        contaCorrente.setData(LocalDate.parse("2024-11-18"));

        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setTaxaRendimento(BigDecimal.valueOf(1));

        Endereco endereco = new Endereco();
        endereco.setCep("71.200-300");
        endereco.setLocal("Plano Piloto");
        endereco.setEstado("DF");
        endereco.setCidade("Brasília");
        endereco.setNumeroCasa(52);
        endereco.setBairro("Águas Claras");

        Relatorio relatorio = new Relatorio();
        relatorio.setTipoRelatorio("SAQUE");
        relatorio.setConteudo("1250");
        relatorio.setTimestamp(Timestamp.valueOf("2024-10-09 10:30:00"));

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        transacao.setValor(BigDecimal.valueOf(200));
        transacao.setDataHora(Timestamp.valueOf("2012-11-30 12:10:11"));


//Start de usuario
        usuarioController.cadastroUsuario(usuario);

//Usuario ou é funcionario
        funcionario.setUsuario(usuario);
        funcionarioController.cadastroFuncionario(funcionario);
//Ou cliente
        cliente.setUsuario(usuario);
        clienteController.cadastroCliente(cliente);

//após processamento do endereço
        endereco.setUsuario(usuario);
        enderecoController.cadastroEndereco(endereco);

//depois acesso a conta
        conta.setCliente(cliente);
        contaController.cadastroConta(conta);


//conta corrente
        contaCorrente.setConta(conta);
        contaCorrenteController.cadastroContaCorrente(contaCorrente);
//conta poupanca


//relatorio
        relatorio.setFuncionario(funcionario);
        relatorioController.cadastroRelatorio(relatorio);

//transacao
        transacao.setConta(conta);
        transacaoController.cadastroTransacao(transacao);


        // Não sei o porque, mas ele tá dando error kkkkk
        HibernateToCSV hibernateToCSV = new HibernateToCSV();
        hibernateToCSV.exportarParaCSV();


        //Fechar a conexão com o banco

        funcionarioController.fecharOperacao();
        clienteController.fecharOperacao();
        usuarioController.fecharOperacao();
        enderecoController.fecharOperacao();
        contaController.fecharOperacao();
        contaCorrenteController.fecharOperacao();
        contaPoupancaController.fecharOperacao();
        relatorioController.fecharOperacao();
        transacaoController.fecharOperacao();


        boolean teste = usuarioDAO.validarUsuario("Mateus","oiteste123");
        System.out.println(teste);

        clienteController.depositar("Mateus","oiteste123",100);

        clienteController.saque("Mateus","oiteste123",600);

        ExtratoCSV extratoCSV = new ExtratoCSV();
        extratoCSV.extratoCSV();

        ClienteController.extrato("Mateus","oiteste123");
    }
}