package org.example.controller;

import org.example.dao.EnderecoDAO;
import org.example.dao.UsuarioDAO;
import org.example.entity.ContaEntity;
import org.example.entity.ContaPoupanca;
import org.example.entity.Endereco;
import org.example.entity.UsuarioEntity;

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

    public void criarEndereco(int retornoId, String cep, String local, int numeroCasa,String  bairro,String  cidade, String estado){
        enderecoDAO.criarEndereco(cep,local,numeroCasa,bairro,cidade,estado, retornoId);
    }

}
