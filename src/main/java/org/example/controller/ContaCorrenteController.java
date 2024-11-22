package org.example.controller;

import org.example.dao.ContaCorrenteDAO;
import org.example.entity.ContaCorrente;
import org.example.entity.Relatorio;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaCorrenteController {
    private final ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();

    public void cadastroContaCorrente(ContaCorrente contaCorrente){
        contaCorrenteDAO.salvar(contaCorrente);
    }

    public ContaCorrente atualizarContaCorrente(ContaCorrente contaCorrente){
        return contaCorrenteDAO.update(contaCorrente);
    }

    public void fecharOperacao(){
        contaCorrenteDAO.fechar();
    }

    public boolean criarContaC(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento, String cep, String local, int numeroCasa, String bairro, String cidade, String estado, String agencia, TipoConta tipoConta, LocalDate dataVencimento, BigDecimal limite, String cargo, String codigo, String numeroConta){

        ControllerGeral controllerGeral = new ControllerGeral();
        int idRetorno = controllerGeral.criarConta(cpf, senha, nome, telefone, tipoUsuario, dataNascimento, cep, local, numeroCasa, bairro, cidade, estado, agencia, tipoConta, cargo = "", codigo = "", numeroConta);
        boolean statusRetorno = contaCorrenteDAO.criarContaCorrente(idRetorno, dataVencimento, limite);
        return statusRetorno;
    }
}
