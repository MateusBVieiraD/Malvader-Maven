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

        entityManager = EntityFactory.getEntityManager();
    }

    public int salvar(Cliente cliente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(cliente);
            transaction.commit();
            return cliente.getId();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
                return -1;
            }
            throw e;
        }
    }

    public boolean remover(Cliente cliente){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(cliente);
            transaction.commit();
            return true;

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
                return false;
            }
            throw e; // Re-throw exception
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




    public int criarCliente(int id){
        Cliente cliente = new Cliente();
        UsuarioEntity usuario = entityManager.find(UsuarioEntity.class, id);
        cliente.setUsuario(usuario);
        ClienteDAO clienteDAO = new ClienteDAO();
        int retornoId = clienteDAO.salvar(cliente);
        return retornoId;
    }

    public int buscarporId(String user, String password){

        TypedQuery<UsuarioEntity> query = entityManager.createQuery("SELECT u FROM UsuarioEntity u WHERE u.senha = :senha AND u.nome = :nome" , UsuarioEntity.class);
        query.setParameter("senha", password);
        query.setParameter("nome", user);

        UsuarioEntity test = query.getSingleResult();

        int id = test.getId();

        return id;



    }

    public void buscarUsuario(){

    }



    public void fechar() {
        entityManager.close();
    }
}
