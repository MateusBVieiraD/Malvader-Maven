package org.example;

import org.example.dao.ClienteDAO;
import org.example.dao.UsuarioDAO;
import org.example.dao.FuncionarioDAO;

import org.example.entity.Cliente;
import org.example.entity.Funcionario;
import org.example.entity.Usuario;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();


        //Criar um novo usuário
        // Criando um novo usuário
        Usuario usuario = new Usuario();
        usuario.setNome("admin");
        usuario.setSenha("1234");
        usuarioDAO.salvar(usuario);

// Criando um novo funcionário

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("gerente");
        funcionario.setUsuario(usuario);
        funcionarioDAO.salvar(funcionario);


        Usuario usuario2 = new Usuario();
        usuario2.setNome("leitinho");
        usuario2.setSenha("666-777");
        usuarioDAO.salvar(usuario2);


        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario2);
        clienteDAO.salvar(cliente);




        //Fechar a conexão com o banco
        usuarioDAO.fechar();
        funcionarioDAO.fechar();
        clienteDAO.fechar();

    }
}