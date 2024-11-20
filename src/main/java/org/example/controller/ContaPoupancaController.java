package org.example.controller;


import org.example.dao.ContaPoupancaDAO;

import org.example.entity.ContaCorrente;
import org.example.entity.ContaPoupanca;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPoupancaController {

    private final ContaPoupancaDAO contaPoupancaDao = new ContaPoupancaDAO();
    public void cadastroContaPoupanca(ContaPoupanca contaPoupanca){
        contaPoupancaDao.salvar(contaPoupanca);
    }

    public ContaPoupanca atualizarContaPoupanca(ContaPoupanca contaPoupanca){
        return contaPoupancaDao.update(contaPoupanca);
    }



    public void fecharOperacao(){
        contaPoupancaDao.fechar();
    }

    public boolean criarContaP(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento, String cep, String local, int numeroCasa, String bairro, String cidade, String estado, String agencia, TipoConta tipoConta, BigDecimal taxa){
        ControllerGeral controllerGeral = new ControllerGeral();
        int idRetorno = controllerGeral.criarConta(cpf, senha, nome, telefone, tipoUsuario, dataNascimento, cep, local, numeroCasa, bairro, cidade, estado, agencia, tipoConta);
        boolean retornoStatus = contaPoupancaDao.criarContaPoupanca(idRetorno, taxa);
        return retornoStatus;
    }

}
