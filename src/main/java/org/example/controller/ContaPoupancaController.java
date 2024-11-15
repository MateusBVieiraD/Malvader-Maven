package org.example.controller;


import org.example.dao.ContaPoupancaDAO;

import org.example.entity.ContaCorrente;
import org.example.entity.ContaPoupanca;

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

}
