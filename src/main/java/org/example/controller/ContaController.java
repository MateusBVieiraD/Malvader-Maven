package org.example.controller;

import org.example.dao.*;
import org.example.entity.*;


public class ContaController {
    private final ContaDAO contaDao = new ContaDAO();

    public void cadastroConta(ContaEntity conta){
        contaDao.salvar(conta);
    }

    public ContaEntity atualizarConta(ContaEntity contaEntity){
        return contaDao.update(contaEntity);
    }

    public void fecharOperacao(){
        contaDao.fechar();
    }

    public void consultarConta(String numeroConta){




    }

    public String consultarContaParaRemover(String numeroConta){

        ContaDAO contaDAO = new ContaDAO();


        ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);

        if (contaEntity != null) {
            Cliente cliente = contaEntity.getCliente();
            UsuarioEntity usuario = cliente.getUsuario();



            return "Informações do usuario a ser deletado " +
                ", nome='" + usuario.getNome() + '\'' +
                ", cpf='" + usuario.getCpf() + '\'' +
                ", dataNascimento=" + usuario.getDataNascimento() +
                ", telefone='" + usuario.getTelefone() + '\'' +
                ", tipoUsuario=" + usuario.getTipoUsuario() +
                ", saldo= " + contaEntity.getSaldo() +
                ", agencia= " + contaEntity.getAgencia() +
                ", numero da conta= " + contaEntity.getNumeroConta() +
                ", tipo da conta= " + contaEntity.getTipoconta();
        }else{
            return "essa conta não existe";
        }

    }

}
