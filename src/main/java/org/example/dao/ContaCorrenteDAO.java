package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.ContaCorrente;
import org.example.entity.UsuarioEntity;

public class ContaCorrenteDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ContaCorrenteDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(ContaCorrente contaCorrente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(contaCorrente);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
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

    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
