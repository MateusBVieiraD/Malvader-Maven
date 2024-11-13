package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.UsuarioEntity;

public class UsuarioDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public UsuarioDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(UsuarioEntity usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(usuario);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public UsuarioEntity update(UsuarioEntity usuario){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            UsuarioEntity usuarioAtualizado = entityManager.merge(usuario);
            transaction.commit();
            return usuarioAtualizado;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar o usuário", e); // Re-throw exception
        }
    }

    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
