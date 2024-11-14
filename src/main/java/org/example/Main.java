package org.example;

import org.example.config.EntityFactory;
import org.example.controller.*;
import org.example.entity.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

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

        conta2.setCliente(cliente);
        contaController.cadastroConta(conta2);

//conta corrente
        contaCorrente.setConta(conta);
        contaCorrenteController.cadastroContaCorrente(contaCorrente);
//conta poupanca
        contaPoupanca.setConta(conta2);
        contaPoupancaController.cadastroContaPoupanca(contaPoupanca);

//relatorio
        relatorio.setFuncionario(funcionario);
        relatorioController.cadastroRelatorio(relatorio);

//transacao
        transacao.setConta(conta);
        transacaoController.cadastroTransacao(transacao);

//teste update
        relatorio.setTipoRelatorio("SAQUE");
        relatorioController.atualizarRelatorio(relatorio);

        usuario.setNome("Mateus");
        usuarioController.atualizarUsuario(usuario);

        funcionario.setCargo("Gerente");
        funcionarioController.atualizarFuncionario(funcionario);

        conta.setSaldo(BigDecimal.valueOf(540));
        contaController.atualizarConta(conta);

        endereco.setCep("456.789-00");
        enderecoController.atualizarEndereco(endereco);

        contaPoupanca.setTaxaRendimento(BigDecimal.valueOf(200));
        contaPoupancaController.atualizarContaPoupanca(contaPoupanca);

        contaCorrente.setLimite(BigDecimal.valueOf(2000));
        contaCorrenteController.atualizarContaCorrente(contaCorrente);




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

        EntityFactory.fechar();
    }
}