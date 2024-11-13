package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Transacao;
import org.example.entity.UsuarioEntity;

public class TransacaoDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public TransacaoDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(Transacao transacao) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(transacao);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
