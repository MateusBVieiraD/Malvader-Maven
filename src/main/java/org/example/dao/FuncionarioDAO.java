package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Funcionario;

public class FuncionarioDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public FuncionarioDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(Funcionario funcionario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(funcionario);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
