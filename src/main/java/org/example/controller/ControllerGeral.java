package org.example.controller;
import org.example.dao.ClienteDAO;
import org.example.dao.ContaDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;

import java.time.LocalDate;


public class ControllerGeral {
    public void criarConta(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento, String cep,String local, int numeroCasa, String bairro, String cidade, String estado, String agencia, TipoConta tipoConta ) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int retornoIdUsuario = usuarioDAO.criarUsuario(cpf, senha, nome, telefone, tipoUsuario, dataNascimento);
        EnderecoController enderecoController = new EnderecoController();
        enderecoController.criarEndereco(retornoIdUsuario, cep, local, numeroCasa, bairro, cidade, estado);
        ClienteDAO clienteDAO = new ClienteDAO();
        int retornoIdCliente = clienteDAO.criarCliente(retornoIdUsuario);
        ContaDAO contaDAO = new ContaDAO();
        int retornoIdConta = contaDAO.criarConta(retornoIdCliente, agencia, tipoConta);
    }

    
}