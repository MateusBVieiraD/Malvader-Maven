package org.example.controller;

import org.example.dao.RelatorioDAO;
import org.example.entity.Relatorio;

public class RelatorioController {
    private final RelatorioDAO relatorioDao = new RelatorioDAO();

    public void cadastroRelatorio(Relatorio relatorio){
        relatorioDao.salvar(relatorio);
    }

    public void fecharOperacao(){
        relatorioDao.fechar();
    }
}
