package org.example.controller;

import org.example.dao.FuncionarioDAO;
import org.example.entity.Funcionario;
import org.example.entity.Relatorio;


public class FuncionarioController {
    private final FuncionarioDAO funcionarioDao = new FuncionarioDAO();

    public void cadastroFuncionario(Funcionario funcionario){
        funcionarioDao.salvar(funcionario);
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario){
        return funcionarioDao.update(funcionario);
    }

    public void fecharOperacao(){
        funcionarioDao.fechar();
    }
}
