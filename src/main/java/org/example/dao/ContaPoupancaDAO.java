package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.ContaPoupanca;
import jakarta.persistence.Entity;

public class ContaPoupancaDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ContaPoupancaDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(ContaPoupanca contaPoupanca) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(contaPoupanca);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
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

    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
