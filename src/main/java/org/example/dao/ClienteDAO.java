package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.example.entity.Cliente;

public class ClienteDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ClienteDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(Cliente cliente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(cliente);
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
