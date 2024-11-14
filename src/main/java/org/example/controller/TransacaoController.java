package org.example.controller;

import org.example.dao.TransacaoDAO;
import org.example.entity.Transacao;

public class TransacaoController {
    private final TransacaoDAO transacaoDao = new TransacaoDAO();

    public void cadastroTransacao(Transacao transacao){
        transacaoDao.salvar(transacao);
    }

    public void fecharOperacao(){
        transacaoDao.fechar();
    }
}
