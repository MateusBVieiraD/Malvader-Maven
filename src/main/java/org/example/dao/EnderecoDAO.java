package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.config.EntityFactory;
import org.example.entity.Endereco;

public class EnderecoDAO {

    private final EntityManager entityManager;

    public EnderecoDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public void salvar(Endereco endereco) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(endereco);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Endereco update(Endereco endereco){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Endereco enderecoAtualizado = entityManager.merge(endereco);
            transaction.commit();
            return enderecoAtualizado;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar o endereco", e); // Re-throw exception
        }
    }


    public void fechar() {
        entityManager.close();
    }
}
