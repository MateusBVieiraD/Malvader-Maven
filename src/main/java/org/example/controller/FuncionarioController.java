package org.example.controller;

import org.example.dao.FuncionarioDAO;
import org.example.entity.Funcionario;


public class FuncionarioController {
    private final FuncionarioDAO funcionarioDao = new FuncionarioDAO();

    public void cadastroFuncionario(Funcionario funcionario){
        funcionarioDao.salvar(funcionario);
    }

    public void fecharOperacao(){
        funcionarioDao.fechar();
    }
}
