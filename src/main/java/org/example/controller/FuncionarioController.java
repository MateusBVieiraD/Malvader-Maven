package org.example.controller;

import org.example.dao.FuncionarioDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.Funcionario;
import org.example.entity.Relatorio;
import org.example.entity.TipoUsuario;


public class FuncionarioController {
    private final FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    public void cadastroFuncionario(Funcionario funcionario){
        funcionarioDao.salvar(funcionario);
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario){
        return funcionarioDao.update(funcionario);
    }

    public boolean login(String user, String senha, TipoUsuario tipousuario){
        if(user.isEmpty() || user.equals(" ")){
            return false;
        }else if (senha.isEmpty() || senha.equals(" ")){
            return false;
        }else{
            return usuarioDao.validarUsuario(user, senha, tipousuario);
        }
    }



    public void fecharOperacao(){
        funcionarioDao.fechar();
    }
}
