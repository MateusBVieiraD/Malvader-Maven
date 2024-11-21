package org.example.controller;
import org.example.Main;
import org.example.dao.*;
import org.example.entity.*;

import java.time.LocalDate;



public class ControllerGeral {
    public int criarConta(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento, String cep,String local, int numeroCasa, String bairro, String cidade, String estado, String agencia, TipoConta tipoConta, String cargo, String codigo ) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(tipoUsuario == TipoUsuario.CLIENTE){
            int retornoIdUsuario = usuarioDAO.criarUsuario(cpf, senha, nome, telefone, tipoUsuario, dataNascimento);
            if (usuarioDAO.verificarExistenciaCpf(cpf) != null){
                UsuarioEntity usuarioEntity = usuarioDAO.verificarExistenciaCpf(cpf);
                int retornoId = usuarioEntity.getId();

            }
            EnderecoController enderecoController = new EnderecoController();
            enderecoController.criarEndereco(retornoIdUsuario, cep, local, numeroCasa, bairro, cidade, estado);
            ClienteDAO clienteDAO = new ClienteDAO();
            int retornoIdCliente = clienteDAO.criarCliente(retornoIdUsuario);
            ContaDAO contaDAO = new ContaDAO();
            int retornoIdConta = contaDAO.criarConta(retornoIdCliente, agencia, tipoConta);
            return retornoIdConta;
        }else if (tipoUsuario == TipoUsuario.FUNCIONARIO){
            int retornoIdUsuario = usuarioDAO.criarUsuario(cpf, senha, nome, telefone, tipoUsuario, dataNascimento);
            EnderecoController enderecoController = new EnderecoController();
            enderecoController.criarEndereco(retornoIdUsuario, cep, local, numeroCasa, bairro, cidade, estado);
            FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
            int retornoStatus = funcionarioDAO.criarFuncionario(cargo, codigo, retornoIdUsuario);
            return retornoStatus;

        }else{
            System.out.println("Algo deu errado");
        }
        return 0;
    }

    public Boolean removerConta(String numeroConta) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

            ContaDAO contaDAO = new ContaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();


            ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);
            if (contaEntity != null) {

                Cliente cliente = contaEntity.getCliente();
                UsuarioEntity usuario = cliente.getUsuario();


                if (contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId()) != null){
                    ContaCorrente contaCorrente = contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId());
                    contaCorrenteDAO.remover(contaCorrente);
                }else if(contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId()) != null){
                    ContaPoupanca contaPoupanca = contaPoupancaDAO.buscarContaPoupancaConta(contaEntity.getId());
                    contaPoupancaDAO.remover(contaPoupanca);
                }

                contaDAO.remover(contaEntity);

                Endereco endereco = enderecoDAO.buscarEnderecoUsuario(usuario.getId());

                enderecoDAO.remover(endereco);

                clienteDAO.remover(cliente.getId());


                usuarioDAO.remover(usuario.getId());

                return true;

            }

        contaDAO.fechar();


        return false;
    }
}

