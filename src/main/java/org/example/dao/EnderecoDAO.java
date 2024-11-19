package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.config.EntityFactory;
import org.example.entity.ContaEntity;
import org.example.entity.Endereco;
import org.example.entity.UsuarioEntity;
import org.example.modelo.Usuario;

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

    public void criarEndereco(String cep,String local, int numeroCasa, String bairro, String cidade, String estado, int id){

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setNumeroCasa(numeroCasa);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLocal(local);
        UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, id);
        endereco.setUsuario(usuarioEntity);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.salvar(endereco);
    }


    public void fechar() {
        entityManager.close();
    }
}
