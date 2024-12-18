package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.config.EntityFactory;
import org.example.entity.Relatorio;

public class RelatorioDAO {
    private final EntityManager entityManager;

    public RelatorioDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public void salvar(Relatorio relatorio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(relatorio);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public Relatorio update(Relatorio relatorio){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Relatorio relatorioAtualizado = entityManager.merge(relatorio);
            transaction.commit();
            return relatorioAtualizado;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar o relatório", e); // Re-throw exception
        }
    }



    public void fechar() {
        entityManager.close();
    }
}
