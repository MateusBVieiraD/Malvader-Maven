package org.example.controller;
import org.example.dao.*;
import org.example.entity.*;
import org.example.modelo.Conta;

import java.time.LocalDate;


public class ControllerGeral {
    public int criarConta(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento, String cep,String local, int numeroCasa, String bairro, String cidade, String estado, String agencia, TipoConta tipoConta ) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int retornoIdUsuario = usuarioDAO.criarUsuario(cpf, senha, nome, telefone, tipoUsuario, dataNascimento);
        EnderecoController enderecoController = new EnderecoController();
        enderecoController.criarEndereco(retornoIdUsuario, cep, local, numeroCasa, bairro, cidade, estado);
        ClienteDAO clienteDAO = new ClienteDAO();
        int retornoIdCliente = clienteDAO.criarCliente(retornoIdUsuario);
        ContaDAO contaDAO = new ContaDAO();
        int retornoIdConta = contaDAO.criarConta(retornoIdCliente, agencia, tipoConta);
        return retornoIdConta;
    }

    public void removerConta(String user, String password, TipoUsuario tipoUsuario, String numeroConta) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.validarUsuario(user, password, tipoUsuario)) {

            ContaDAO contaDAO = new ContaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();


            ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);
            Cliente cliente = contaEntity.getCliente();
            UsuarioEntity usuarioEntity = cliente.getUsuario();

            int retornoIdUsuario = usuarioEntity.getId();
            Endereco endereco = enderecoDAO.buscarEnderecoUsuario(retornoIdUsuario);
            Transacao transacao = transacaoDAO.buscarTransacaoConta(contaEntity.getId());
            ContaCorrente contaCorrente = new ContaCorrente();

            ContaCorrente contaCorrente1 = contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId());
            ContaPoupanca contaPoupanca = contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId());

            if (contaCorrente1 != null){
                contaCorrenteDAO.remover(contaCorrente1);
            }

            if (contaPoupanca != null){
                contaPoupancaDAO.remover(contaPoupanca);
            }

            enderecoDAO.remover(endereco);
            transacaoDAO.remover(transacao);


            contaDAO.remover(contaEntity);
            clienteDAO.remover(cliente);
            usuarioDAO.remover(usuarioEntity);


        }
    }


}