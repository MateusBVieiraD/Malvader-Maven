package org.example.controller;

import org.example.bancoController.ExtratoCSV;
import org.example.dao.*;
import org.example.entity.*;

import java.io.File;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteController{
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
    ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();

    public boolean login(String user, String senha, TipoUsuario tipoUsuario) {
        if (user.isEmpty() || user.equals(" ")) {
            return false;
        } else if (senha.isEmpty() || senha.equals(" ")) {
            return false;
        } else {
            return usuarioDao.validarUsuario(user, senha, tipoUsuario);
        }
    }

    public void fecharOperacao() {
        clienteDAO.fechar();
    }

    //Acha os relacionamentos e chama o método de atualizar o saldo
    public void depositar(String user, String password, double valor) {

        int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user, password)));
        ContaDAO.atualizarSaldo(retorno3, valor);
    }
    //Acha as tabelas com chaves estrangeiras relacionadas e por fim faz o saque da conta
    public boolean saque(String user, String password, double valor) {
        int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user, password)));
        return ContaDAO.sacarSaldo(retorno3, valor);
    }
    //Acha as tabelas com as chaves estrangeiras relacionadas e por fim volta o saldo da conta
    public BigDecimal saldo(String user, String password) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarUsuario(user, password, TipoUsuario.CLIENTE)) {
            int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user, password)));
            return ContaDAO.verificarSaldo(retorno3);
        } else {
            Exception e = null;
            e.printStackTrace();
        }
        clienteDAO.fechar();
        return null;
    }

    //verifica os registros relacionados com o user e o password e retorna o limite caso existe uma conta corrente
    public double consultarLimite(String user, String password) {


        int retorno3 = ContaDAO.verificarContaRelacionada(usuarioDao.consultarClienteRelacionado(clienteDAO.buscarporId(user, password)));

        if (contaCorrenteDAO.buscarContaCorrenteConta(retorno3) == null) {
            return -1;

        }

        ContaCorrente contaCorrente = contaCorrenteDAO.buscarContaCorrenteConta(retorno3);

        return Double.parseDouble(String.valueOf(contaCorrente.getLimite()));

    }


    public boolean alterarCliente(String cpf,String telefone, String local, int numeroCasa, String cep, String bairro, String cidade, String estado){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();

        if (usuarioDAO.verificarExistenciaCpf(cpf) != null){
            UsuarioEntity usuarioEntity = usuarioDAO.verificarExistenciaCpf(cpf);

            Endereco endereco = enderecoDAO.buscarEnderecoUsuario(usuarioEntity.getId());
            clienteDAO.buscarClienteIdUsuario(usuarioEntity.getId());

            usuarioEntity.setTelefone(telefone);
            endereco.setLocal(local);
            endereco.setNumeroCasa(numeroCasa);
            endereco.setCep(cep);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);

            enderecoDAO.update(endereco);
            usuarioDAO.update(usuarioEntity);

            return true;

        }

        return false;
    }

    public boolean validarUsuarioCpf(String cpf){
        return clienteDAO.validarUsuarioCpf(cpf);
    }

    public String consultarCliente(String cpf){
        UsuarioDAO  usuarioDAO = new UsuarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();


        UsuarioEntity usuarioEntity = usuarioDAO.verificarExistenciaCpf(cpf);

        if (usuarioEntity != null) {
            Endereco endereco = enderecoDAO.buscarEnderecoUsuario(usuarioEntity.getId());

            return "Nome: " + usuarioEntity.getNome() +
                    "\nCpf: " + usuarioEntity.getCpf() +
                    "\nData de Nascimento: " + usuarioEntity.getDataNascimento() +
                    "\nNumero da casa: " + endereco.getNumeroCasa() +
                    "\nCEP: " + endereco.getCep() +
                    "\nBairro: " + endereco.getBairro() +
                    "\nCidade: " + endereco.getCidade() +
                    "\nEstado: " + endereco.getEstado();

        }

        return "Cliente não encontrado";

    }

    public static void extrato() throws IOException {
        ExtratoCSV extratoCSV = new ExtratoCSV();
        extratoCSV.extratoCSV();

        File file1 = new File("extrato.csv");

        try {

            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {

                String command = "cmd /c start excel \"" + file1.getAbsolutePath() + "\"";
                Runtime.getRuntime().exec(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
