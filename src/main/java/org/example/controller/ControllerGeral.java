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


            ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);
            System.out.println(contaEntity.getId());
            if (contaEntity != null) {
                Cliente cliente = contaEntity.getCliente();
                UsuarioEntity usuario = cliente.getUsuario();

                // 1. Remover o cliente
                contaDAO.remover(contaEntity);

                Endereco endereco = enderecoDAO.buscarEnderecoUsuario(usuario.getId());
                if (endereco != null) {

                    enderecoDAO.remover(endereco); // Remove o endereço, se existir
                }
                System.out.println(clienteDAO.removerClienteForcado(cliente.getId())); // Remove o cliente, removendo a chave estrangeira do usuário na tabela cliente

                // 2. Remover o endereço associado ao usuário


                // 3. Remover a conta// Remove a conta

                // 4. Finalmente, agora podemos remover o usuário
                usuarioDAO.removerUsuarioForcado(usuario.getId()); // Remove o usuário
            }
        }
    }


}