package org.example.controller;

import org.example.dao.ContaDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.ContaEntity;
import org.example.entity.Relatorio;


public class ContaController {
    private final ContaDAO contaDao = new ContaDAO();

    public void cadastroConta(ContaEntity conta){
        contaDao.salvar(conta);
    }

    public ContaEntity atualizarConta(ContaEntity contaEntity){
        return contaDao.update(contaEntity);
    }

    public void ContaSacar(){}

    public void fecharOperacao(){
        contaDao.fechar();
    }

    public void criarContaCp(){

    }

}
