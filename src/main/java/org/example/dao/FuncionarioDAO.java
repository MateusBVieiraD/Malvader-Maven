package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.config.EntityFactory;
import org.example.entity.Funcionario;
import org.example.entity.UsuarioEntity;

public class FuncionarioDAO {
    private final EntityManager entityManager;

    public FuncionarioDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public int salvar(Funcionario funcionario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(funcionario);
            transaction.commit();
            return 1;
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
            throw new RuntimeException("Falha ao atualizar o funcionário", e);
        }
    }

    public int criarFuncionario(String cargo, String codigo, int usuarioEntity){
        Funcionario funcionario = new Funcionario();

        funcionario.setCargo(cargo);
        funcionario.setCodigoFuncionario(codigo);
        UsuarioEntity usuarioEntity1 = entityManager.find(UsuarioEntity.class, usuarioEntity);
        funcionario.setUsuario(usuarioEntity1);

        int retornoStatus = salvar(funcionario);
        return retornoStatus;

    }

    public Funcionario buscarFuncionarioPorId(int id){
         return entityManager.createQuery("SELECT f FROM Funcionario f JOIN f.usuario u WHERE u.id = :id", Funcionario.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }



    public void fechar() {
        entityManager.close();
    }
}
