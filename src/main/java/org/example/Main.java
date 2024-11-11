package org.example;

import org.example.dao.EnderecoDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.Endereco;
import org.example.entity.Funcionario;
import org.example.entity.Usuario;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Endereco endereco = new Endereco();
        endereco.setNome("ENDERECO TESTE");
        enderecoDAO.salvar(endereco);



        // Criar um novo usuário
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setCpf("123456789");
        funcionario.setEndereco(endereco);
        funcionario.setDataNascimento(new Date(2022,10,20));

        // Salvar o usuário no banco de dados
        usuarioDAO.salvar(funcionario);

        // Fechar a conexão com o banco
        usuarioDAO.fechar();
        enderecoDAO.fechar();
    }
}