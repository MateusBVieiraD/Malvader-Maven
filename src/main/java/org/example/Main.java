package org.example;

import org.example.dao.*;
import org.example.entity.*;
import org.example.modelo.Conta;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ContaDAO contaDAO = new ContaDAO();
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        TransacaoDAO transacaoDAO = new TransacaoDAO();


// Criando um novo funcionário
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCpf("123456789");
        usuario.setNome("Miguel");
        usuario.setSenha("oiteste123");
        usuario.setDataNascimento(LocalDate.parse("2022-02-23"));
        usuario.setTipoUsuario(TipoUsuario.FUNCIONARIO);

        Endereco endereco = new Endereco();
        endereco.setBairro("A/C");
        endereco.setCep("123.123-70");
        endereco.setCidade("Brasilia");
        endereco.setEstado("DF");
        endereco.setLocal("algum");

        ContaEntity conta = new ContaEntity();
        conta.setNumeroConta("232515151");
        conta.setSaldo(BigDecimal.valueOf(123.45));
        conta.setAgencia("MALVADER");

        ContaEntity conta2 = new ContaEntity();
        conta2.setNumeroConta("231");
        conta2.setSaldo(BigDecimal.valueOf(100));
        conta2.setAgencia("BB");

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setLimite(BigDecimal.valueOf(123.4));
        contaCorrente.setData(LocalDate.parse("2022-12-04"));

        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setTaxaRendimento(BigDecimal.valueOf(30));

        Relatorio relatorio = new Relatorio();
        relatorio.setTipoRelatorio("Extrato");
        relatorio.setTimestamp(Timestamp.valueOf("2024-11-13 15:30:45"));
        relatorio.setConteudo("50 reais");

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        transacao.setValor(BigDecimal.valueOf(144));
        transacao.setDataHora(Timestamp.valueOf("2024-11-10 10:30:45"));




//Start de usuario
        usuarioDAO.salvar(usuario);

//Usuario ou é funcionario
        funcionario.setUsuario(usuario);
        funcionarioDAO.salvar(funcionario);
//Ou cliente
        cliente.setUsuario(usuario);
        clienteDAO.salvar(cliente);

//após processamento do endereço
        endereco.setUsuario(usuario);
        enderecoDAO.salvar(endereco);

//depois acesso a conta
        conta.setCliente(cliente);
        contaDAO.salvar(conta);

        conta2.setCliente(cliente);
        contaDAO.salvar(conta2);

//conta corrente
        contaCorrente.setConta(conta);
        contaCorrenteDAO.salvar(contaCorrente);
//conta poupanca
        contaPoupanca.setConta(conta2);
        contaPoupancaDAO.salvar(contaPoupanca);

//relatorio
        relatorio.setFuncionario(funcionario);
        relatorioDAO.salvar(relatorio);

//transacao
        transacao.setConta(conta);
        transacaoDAO.salvar(transacao);


        //Fechar a conexão com o banco

        funcionarioDAO.fechar();
        clienteDAO.fechar();
        usuarioDAO.fechar();
        enderecoDAO.fechar();
        contaDAO.fechar();
        contaCorrenteDAO.fechar();
        contaPoupancaDAO.fechar();
        relatorioDAO.fechar();
        transacaoDAO.fechar();
    }
}