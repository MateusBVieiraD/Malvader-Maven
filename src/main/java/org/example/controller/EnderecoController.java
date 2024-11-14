package org.example.controller;

import org.example.dao.EnderecoDAO;
import org.example.entity.ContaPoupanca;
import org.example.entity.Endereco;

public class EnderecoController {
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    public void cadastroEndereco(Endereco endereco){
        enderecoDAO.salvar(endereco);
    }

    public Endereco atualizarEndereco(Endereco endereco){
        return enderecoDAO.update(endereco);
    }

    public void fecharOperacao(){
        enderecoDAO.fechar();
    }
}
