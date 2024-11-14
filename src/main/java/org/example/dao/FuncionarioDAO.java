package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.config.EntityFactory;
import org.example.entity.Funcionario;

public class FuncionarioDAO {
    private final EntityManager entityManager;

    public FuncionarioDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
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

    public Funcionario update(Funcionario funcionario){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Funcionario funcionarioAtualizado = entityManager.merge(funcionario);
            transaction.commit();
            return funcionarioAtualizado;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar o funcionário", e); // Re-throw exception
        }
    }




    public void fechar() {
        entityManager.close();
    }
}
