package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.ContaEntity;


public class ContaDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ContaDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(ContaEntity conta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(conta);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public ContaEntity update(ContaEntity conta){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ContaEntity contaAtualizada = entityManager.merge(conta);
            transaction.commit();
            return contaAtualizada;
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
