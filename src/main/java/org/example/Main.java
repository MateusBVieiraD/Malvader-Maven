package org.example;

import org.example.dao.UsuarioDAO;
import org.example.entity.Cliente;
import org.example.entity.Endereco;
import org.example.entity.Usuario;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();


        //Criar um novo usuário
        Usuario usuario = new Usuario();
        usuario.setNome("jorge");
        usuario.setCpf("45678913");
        usuario.setTelefone("456546564456456");
        usuario.setDataNascimento(new Date(2022 - 1900,2,20));
        usuario.setEndereco(new Endereco("123456", "Brasilia", 10, "Aguas", "Norte", "Df"));


        //Salvar o usuário no banco de dados
        usuarioDAO.salvar(usuario);
        Cliente cliente = new Cliente();



        //Fechar a conexão com o banco
        usuarioDAO.fechar();
    }
}