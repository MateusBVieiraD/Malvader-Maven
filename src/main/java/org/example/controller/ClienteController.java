package org.example.controller;

import org.example.dao.ClienteDAO;
import org.example.dao.ContaDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.Cliente;
import org.example.entity.TipoUsuario;

import java.io.File;


import java.io.IOException;
import java.math.BigDecimal;

public class ClienteController {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    public void cadastroCliente(Cliente cliente){
        clienteDAO.salvar(cliente);
    }

    public boolean login(String user, String senha, TipoUsuario tipoUsuario){
        if(user.isEmpty() || user.equals(" ")){
            return false;
        }else if (senha.isEmpty() || senha.equals(" ")){
            return false;
        }else{
            return usuarioDao.validarUsuario(user, senha, tipoUsuario);
        }
    }

    public void fecharOperacao(){
        clienteDAO.fechar();
    }


    public void depositar(String user, String password, double valor){

        ClienteDAO clienteDAO1 = new ClienteDAO();
        int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user,password)));
        ContaDAO.atualizarSaldo(retorno3,valor);
    }

    public void saque(String user,String password,double valor){
        int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user,password)));
        ContaDAO.sacarSaldo(retorno3, valor);
    }

    public void saldo(String user, String password, double valor){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarUsuario(user, password, TipoUsuario.CLIENTE)){
            int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user,password)));
            BigDecimal retornoPraView = ContaDAO.verificarSaldo(retorno3);
        }else{
            Exception e = null;
            e.printStackTrace();
        }
        clienteDAO.fechar();
    }

    public static void extrato(String user, String password, TipoUsuario tipoUsuario ) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        File file1 = new File("extrato.csv");

        if (usuarioDAO.validarUsuario(user, password, tipoUsuario)) {
            try {

                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {

                    String command = "cmd /c start excel \"" + file1.getAbsolutePath() + "\"";
                    Runtime.getRuntime().exec(command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }

        usuarioDAO.fechar();
    }


}
