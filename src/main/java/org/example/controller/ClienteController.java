package org.example.controller;

import org.example.dao.ClienteDAO;
import org.example.entity.Cliente;

public class ClienteController {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastroCliente(Cliente cliente){
        clienteDAO.salvar(cliente);
    }

    public void fecharOperacao(){
        clienteDAO.fechar();
    }
}
