package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.config.EntityFactory;
import org.example.entity.Cliente;
import org.example.entity.ContaCorrente;
import org.example.entity.ContaEntity;
import org.example.entity.ContaPoupanca;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPoupancaDAO {
    ContaPoupanca contaPoupanca = new ContaPoupanca();

    private final EntityManager entityManager;

    public ContaPoupancaDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public boolean salvar(ContaPoupanca contaPoupanca) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(contaPoupanca);
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

    public boolean remover(ContaPoupanca contaPoupanca){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(contaPoupanca);
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

    public ContaPoupanca update(ContaPoupanca contaPoupanca){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ContaPoupanca contaPoupancaAtualizado = entityManager.merge(contaPoupanca);
            transaction.commit();
            return contaPoupancaAtualizado;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar a conta", e); // Re-throw exception
        }
    }

    public boolean criarContaPoupanca(int id, BigDecimal taxaRendimento){
        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
        contaPoupanca.setTaxaRendimento(taxaRendimento);
        ContaEntity contaEntity = entityManager.find(ContaEntity.class, id);
        contaPoupanca.setConta(contaEntity);
        boolean retornoCriacao =  contaPoupancaDAO.salvar(contaPoupanca);
        return retornoCriacao;
    }

    public ContaPoupanca buscarContaPoupancaConta(int id){
        try {
            return entityManager.createQuery("SELECT e FROM ContaPoupanca e WHERE e.conta = :id", ContaPoupanca.class)
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
