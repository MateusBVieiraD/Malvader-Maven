package org.example.controller;

import org.example.dao.*;
import org.example.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ContaController {
    private final ContaDAO contaDao = new ContaDAO();
    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
    ContaDAO contaDAO = new ContaDAO();

    public void cadastroConta(ContaEntity conta){
        contaDao.salvar(conta);
    }

    public ContaEntity atualizarConta(ContaEntity contaEntity){
        return contaDao.update(contaEntity);
    }

    public void fecharOperacao(){
        contaDao.fechar();
    }

    public String consultarConta(String numeroConta){

        return contaDao.consultarConta(numeroConta);

    }

    public String consultarContaParaRemover(String numeroConta){

        ContaDAO contaDAO = new ContaDAO();


        ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);

        if (contaEntity != null) {
            Cliente cliente = contaEntity.getCliente();
            UsuarioEntity usuario = cliente.getUsuario();



            return "Informações do usuario a ser deletado " +
                ", \nnome='" + usuario.getNome() + '\'' +
                ", \ncpf='" + usuario.getCpf() + '\'' +
                ", \ndataNascimento=" + usuario.getDataNascimento() +
                ", \ntelefone='" + usuario.getTelefone() + '\'' +
                ", \ntipoUsuario=" + usuario.getTipoUsuario() +
                ", \nsaldo= " + contaEntity.getSaldo() +
                ", \nagencia= " + contaEntity.getAgencia() +
                ", \nnumero da conta= " + contaEntity.getNumeroConta() +
                ", \ntipo da conta= " + contaEntity.getTipoconta();
        }else{
            return "essa conta não existe";
        }

    }

    public boolean buscarContaId(String numeroId){
        return contaDao.buscarporId(numeroId);
    }

    public boolean alterarConta(String numeroConta, TipoConta tipoConta, BigDecimal limite, LocalDate data){

        ContaEntity contaEntity = contaDao.buscarNumeroConta(numeroConta);

        if (contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId()) != null){
            ContaCorrente contaCorrente = contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId());
            contaEntity.setTipoconta(tipoConta);
            contaCorrente.setLimite(limite);
            contaCorrente.setData(data);
            contaCorrenteDAO.update(contaCorrente);
            contaDao.update(contaEntity);

            return true;

        }

        return false;
    }

}
