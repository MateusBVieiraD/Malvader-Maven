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

    public boolean removerConta(String user, String password, TipoUsuario tipoUsuario, String numeroConta) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.validarUsuario(user, password, tipoUsuario)) {

            ContaDAO contaDAO = new ContaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            EnderecoDAO enderecoDAO = new EnderecoDAO();


            ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);
            if (contaEntity != null) {
                Cliente cliente = contaEntity.getCliente();
                UsuarioEntity usuario = cliente.getUsuario();

                // 1. Remover o cliente
                contaDAO.remover(contaEntity);

                Endereco endereco = enderecoDAO.buscarEnderecoUsuario(usuario.getId());

                    enderecoDAO.remover(endereco);

                clienteDAO.remover(cliente.getId());


                usuarioDAO.remover(usuario.getId());

                return true;
            }
        }
    }


}