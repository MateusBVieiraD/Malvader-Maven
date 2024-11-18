package org.example.dao;

import jakarta.persistence.*;

import org.example.config.EntityFactory;
import org.example.entity.Cliente;
import org.example.entity.ContaEntity;
import org.example.entity.UsuarioEntity;
import org.example.modelo.Usuario;

import java.util.List;

public class ClienteDAO {

    private final EntityManager entityManager;

    public ClienteDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
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

    public Cliente update(Cliente cliente){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Cliente clienteAtualizado = entityManager.merge(cliente);
            transaction.commit();
            return clienteAtualizado;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar o cliente", e); // Re-throw exception
        }
    }

    public Cliente buscarPorId(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    public static int consultarDados(String user, String password){

        var a = EntityFactory.getEntityManager();

        TypedQuery<UsuarioEntity> query = a.createQuery("SELECT u FROM UsuarioEntity u WHERE u.senha = :senha AND u.nome = :nome" , UsuarioEntity.class);
        query.setParameter("senha", password);
        query.setParameter("nome", user);

        UsuarioEntity test = query.getSingleResult();
        int id = test.getId();

        System.out.println(id);

        return id;



    }



    public void fechar() {
        entityManager.close();
        }
}
