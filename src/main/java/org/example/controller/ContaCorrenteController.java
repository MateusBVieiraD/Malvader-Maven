package org.example.controller;

import org.example.dao.ContaCorrenteDAO;
import org.example.entity.ContaCorrente;

public class ContaCorrenteController {
    private final ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();

    public void cadastroContaCorrente(ContaCorrente contaCorrente){
        contaCorrenteDAO.salvar(contaCorrente);
    }

    public void fecharOperacao(){
        contaCorrenteDAO.fechar();
    }
}
