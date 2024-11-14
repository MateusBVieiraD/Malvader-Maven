package org.example.controller;

import org.example.dao.ContaCorrenteDAO;
import org.example.entity.ContaCorrente;
import org.example.entity.Relatorio;

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
}
