package org.example.controller;

import org.example.dao.UsuarioDAO;

import org.example.entity.UsuarioEntity;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsuarioController {
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    public void cadastroUsuario(UsuarioEntity usuario){
        usuarioDao.salvar(usuario);
    }

    public UsuarioEntity atualizarUsuario(UsuarioEntity usuario){
        return usuarioDao.update(usuario);
    }

    public void fecharOperacao(){
        usuarioDao.fechar();
    }

    public boolean login(String user, String senha){
        if(user.isEmpty() || user.equals(" ")){
            return false;
        }else if (senha.isEmpty() || senha.equals(" ")){
            return false;
        }else{
            return usuarioDao.validarUsuario(user, senha);
        }
    }


}
