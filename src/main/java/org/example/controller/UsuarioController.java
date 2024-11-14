package org.example.controller;

import org.example.dao.UsuarioDAO;
import org.example.entity.UsuarioEntity;

public class UsuarioController {
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    public void cadastroUsuario(UsuarioEntity usuario){
        usuarioDao.salvar(usuario);
    }

    public void fecharOperacao(){
        usuarioDao.fechar();
    }
}
