package org.example.controller;

import org.example.dao.ClienteDAO;
import org.example.dao.ContaDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.Cliente;
import java.io.File;


import java.io.IOException;
import java.math.BigDecimal;

public class ClienteController {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    public void cadastroCliente(Cliente cliente){
        clienteDAO.salvar(cliente);
    }

    public boolean login(String user, String senha){
        if(user.isEmpty() || user.equals(" ")){
            return false;
        }else if (senha.isEmpty() || senha.equals(" ")){
            return false;
        }else{
            return usuarioDao.validarUsuario(user, senha);
        }
    }

    public void fecharOperacao(){
        clienteDAO.fechar();
    }

    public void depositar(String user, String password, double valor){
        int retorno3 = ContaDAO.verificarContaRelacionada(UsuarioDAO.consultarClienteRelacionado(ClienteDAO.consultarDados(user,password)));
        ContaDAO.atualizarSaldo(retorno3,valor);
    }

    public void saque(String user,String password,double valor){
        int retorno3 = ContaDAO.verificarContaRelacionada(UsuarioDAO.consultarClienteRelacionado(ClienteDAO.consultarDados(user,password)));
        ContaDAO.sacarSaldo(retorno3, valor);
    }

    public void saldo(String user, String password, double valor){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarUsuario(user, password)){
        int retorno3 = ContaDAO.verificarContaRelacionada(UsuarioDAO.consultarClienteRelacionado(ClienteDAO.consultarDados(user,password)));
        BigDecimal retorno = ContaDAO.verificarSaldo(retorno3);
        }else{
            Exception e = null;
            e.printStackTrace();
        }
    }

    public static void extrato(String user, String password ) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        File file1 = new File("extrato.csv");
        if (usuarioDAO.validarUsuario(user, password)) {
            try {
                // Verifica o sistema operacional para abrir com o comando adequado
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    // Para Windows, abre com Excel
                    String command = "cmd /c start excel \"" + file1.getAbsolutePath() + "\"";
                    Runtime.getRuntime().exec(command); // Executa o comando para abrir o arquivo no Excel
                }
            } catch (IOException e) {
                e.printStackTrace(); // Exibe o erro caso falhe ao executar o comando
            }
        } else {
            // Caso o usuário ou senha não sejam válidos
            System.out.println("Usuário ou senha inválidos.");
        }
    }


    }
