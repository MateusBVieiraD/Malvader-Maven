package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.config.EntityFactory;
import org.example.entity.ContaCorrente;
import org.example.entity.ContaEntity;
import org.example.entity.ContaPoupanca;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaCorrenteDAO {
    ContaCorrente contaCorrente = new ContaCorrente();

    private final EntityManager entityManager;
    public ContaCorrenteDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public boolean salvar(ContaCorrente contaCorrente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(contaCorrente);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
                return false;
            }
            throw e; // Re-throw exception
        }
    }

    public boolean remover(ContaCorrente contaCorrente){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(contaCorrente);
            transaction.commit();
            return true;

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
                return false;
            }
            throw e; // Re-throw exception
        }
    }


    public ContaCorrente update(ContaCorrente contaCorrente){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ContaCorrente contaCorrenteAtualizada = entityManager.merge(contaCorrente);
            transaction.commit();
            return contaCorrenteAtualizada;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar a conta", e); // Re-throw exception
        }
    }

    public boolean criarContaCorrente(int id, LocalDate dataVencimento, BigDecimal limite){
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrente.setData(dataVencimento);
        contaCorrente.setLimite(limite);
        ContaEntity contaEntity = entityManager.find(ContaEntity.class, id);
        contaCorrente.setConta(contaEntity);
        boolean retornoStatus = contaCorrenteDAO.salvar(contaCorrente);
        return retornoStatus;
    }

    public ContaCorrente buscarContaCorrenteConta(int id){

        try {
            return entityManager.createQuery("SELECT e FROM ContaCorrente e WHERE e.conta.id = :id", ContaCorrente.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }



    public void fechar() {
        entityManager.close();
    }
}
