package org.example.controller;
import org.example.dao.*;
import org.example.entity.*;
import org.example.modelo.Conta;

import java.time.LocalDate;
import java.util.List;


public class ControllerGeral {
    public int criarConta(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento, String cep,String local, int numeroCasa, String bairro, String cidade, String estado, String agencia, TipoConta tipoConta, String cargo, String codigo , String numeroConta) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaDAO contaDAO = new ContaDAO();
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();

        if(tipoUsuario == TipoUsuario.CLIENTE){
            if(usuarioDAO.verificarExistenciaCpf(cpf, nome) != null){
                UsuarioEntity usuarioEntity = usuarioDAO.verificarExistenciaCpf(cpf, nome);
                Cliente cliente = clienteDAO.buscarClienteIdUsuario(usuarioEntity.getId());
                ContaEntity contaEntity = contaDAO.buscarNumeroConta(cliente.getId());

                if (contaEntity.getTipoconta() == TipoConta.CORRENTE){
                    ContaEntity contaEntity1 = new ContaEntity();
                    contaEntity1.setTipoconta(TipoConta.POUPANCA);
                    contaEntity1.setAgencia(agencia);
                    contaEntity1.setCliente(cliente);
                    contaEntity1.setNumeroConta(numeroConta);
                    contaDAO.salvar(contaEntity1);


                    return contaEntity1.getId();

                }else if(contaEntity.getTipoconta() == TipoConta.POUPANCA){
                    ContaEntity contaEntity2 = new ContaEntity();
                    contaEntity2.setTipoconta(TipoConta.CORRENTE);
                    contaEntity2.setAgencia(agencia);
                    contaEntity2.setNumeroConta(numeroConta);
                    contaEntity2.setCliente(cliente);
                    contaDAO.salvar(contaEntity2);

                    return contaEntity2.getId();
                }

            }

            int retornoIdUsuario = usuarioDAO.criarUsuario(cpf, senha, nome, telefone, tipoUsuario, dataNascimento);
            EnderecoController enderecoController = new EnderecoController();
            enderecoController.criarEndereco(retornoIdUsuario, cep, local, numeroCasa, bairro, cidade, estado);

            int retornoIdCliente = clienteDAO.criarCliente(retornoIdUsuario);

            int retornoIdConta = contaDAO.criarConta(retornoIdCliente, agencia, tipoConta, numeroConta);

            return retornoIdConta;
        }else if (tipoUsuario == TipoUsuario.FUNCIONARIO){
            int retornoIdUsuario = usuarioDAO.criarUsuario(cpf, senha, nome, telefone, tipoUsuario, dataNascimento);
            EnderecoController enderecoController = new EnderecoController();
            enderecoController.criarEndereco(retornoIdUsuario, cep, local, numeroCasa, bairro, cidade, estado);
            FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
            int retornoStatus = funcionarioDAO.criarFuncionario(cargo, codigo, retornoIdUsuario);
            return retornoStatus;

        }

        return 0;
    }

    public Boolean removerConta(String numeroConta) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

            ContaDAO contaDAO = new ContaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();


            ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);

            if (contaEntity != null) {


                if (contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId()) != null){
                    ContaCorrente contaCorrente = contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId());
                    contaCorrenteDAO.remover(contaCorrente);
                    if(contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId()) != null){
                        ContaPoupanca contaPoupanca = contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId());
                        contaPoupancaDAO.remover(contaPoupanca);
                    }
                }else if(contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId()) != null){
                    ContaPoupanca contaPoupanca = contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId());
                    contaPoupancaDAO.remover(contaPoupanca);
                }

                List<Transacao> transacao = transacaoDAO.buscarTransacoes(contaEntity.getId());

                for (Transacao transacao1: transacao){
                    transacaoDAO.remover(transacao1);
                }

                contaDAO.remover(contaEntity);

                return true;

            }

        contaDAO.fechar();


        return false;
    }
}

