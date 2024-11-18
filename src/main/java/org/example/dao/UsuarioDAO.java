package org.example.dao;


import jakarta.persistence.*;
import org.example.config.EntityFactory;

import org.example.entity.Cliente;
import org.example.entity.UsuarioEntity;
import org.example.modelo.Usuario;

import java.util.List;

public class UsuarioDAO {

    private EntityManager entityManager;

    public UsuarioDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
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

    public boolean validarUsuario(String user, String password) {
        try {
            String query = "SELECT u FROM UsuarioEntity u WHERE u.senha = :senha AND u.nome = :nome";
            return entityManager.createQuery(query, UsuarioEntity.class)
                    .setParameter("nome", user)
                    .setParameter("senha", password)
                    .getResultList()
                    .size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public static UsuarioEntity consultarDados(String user, String password){

        var a = EntityFactory.getEntityManager();

        TypedQuery<UsuarioEntity> query = a.createQuery("SELECT u FROM UsuarioEntity u WHERE u.senha = :senha AND u.nome = :nome" , UsuarioEntity.class);
        query.setParameter("senha", password);
        query.setParameter("nome", user);

        UsuarioEntity usuarioEntity = query.getSingleResult();

        return usuarioEntity;



    }

    public static int consultarClienteRelacionado(int id) {
        var a = EntityFactory.getEntityManager();

        TypedQuery<Integer> query = a.createQuery("SELECT cl.id FROM Cliente cl JOIN cl.usuario u WHERE u.id = :id", Integer.class);
        query.setParameter("id", id);
        int cl = query.getSingleResult();
        System.out.println(cl);
        return cl;

    }


    public void fechar(){
        entityManager.close();
    }


}
