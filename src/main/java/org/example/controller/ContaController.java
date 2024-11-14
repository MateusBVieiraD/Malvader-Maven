package org.example.controller;

import org.example.dao.ContaDAO;
import org.example.entity.ContaEntity;


public class ContaController {
    private final ContaDAO contaDao = new ContaDAO();

    public void cadastroConta(ContaEntity conta){
        contaDao.salvar(conta);
    }

    public void fecharOperacao(){
        contaDao.fechar();
    }
}
