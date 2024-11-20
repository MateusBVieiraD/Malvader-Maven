package org.example.dao;

import jakarta.persistence.*;
import org.example.config.EntityFactory;
import org.example.entity.ContaEntity;
import org.example.entity.TipoConta;
import org.example.entity.TipoUsuario;
import org.example.entity.UsuarioEntity;
import java.time.LocalDate;

public class UsuarioDAO {

    private final EntityManager entityManager;

    public UsuarioDAO() {

        entityManager = EntityFactory.getEntityManager();
    }

    public int salvar(UsuarioEntity usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(usuario);  // Persiste a entidade no banco
            transaction.commit();
            return usuario.getId();

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public boolean remover(UsuarioEntity usuario){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(usuario);  // Persiste a entidade no banco
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

    public boolean validarUsuario(String user, String password, TipoUsuario tipoUsuario) {
        try {
            String query = "SELECT u FROM UsuarioEntity u WHERE u.senha = :senha AND u.nome = :nome AND u.tipoUsuario = :tipoUsuario";
            return entityManager.createQuery(query, UsuarioEntity.class)
                    .setParameter("nome", user) // Deve corresponder ao nome da propriedade na entidade
                    .setParameter("senha", password)
                    .setParameter("tipoUsuario", tipoUsuario)
                    .getResultList()
                    .size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean validarCpfParaConta(String cpf, TipoConta tipoConta) {
        try {
            String query = "SELECT u FROM UsuarioEntity u " +
                    "JOIN Cliente c ON c.usuario.id = u.id " +
                    "JOIN ContaEntity ce ON ce.cliente.id = c.id " +
                    "WHERE u.cpf = :cpf AND ce.tipoconta = :tipoconta";

            return entityManager.createQuery(query, UsuarioEntity.class)
                    .setParameter("cpf", cpf) // Parâmetro para CPF
                    .setParameter("tipoconta", tipoConta) // Parâmetro para TipoConta
                    .getResultList()
                    .size() > 0; // Verifica se há registros encontrados
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna falso em caso de erro
        }
    }



    public UsuarioEntity consultarDados(String user, String password){

        TypedQuery<UsuarioEntity> query = entityManager.createQuery("SELECT u FROM UsuarioEntity u WHERE u.senha = :senha AND u.nome = :nome" , UsuarioEntity.class);
        query.setParameter("senha", password);
        query.setParameter("nome", user);

        UsuarioEntity usuarioEntity = query.getSingleResult();

        return usuarioEntity;



    }

    public int consultarClienteRelacionado(int id) {

        TypedQuery<Integer> query = entityManager.createQuery("SELECT cl.id FROM Cliente cl JOIN cl.usuario u WHERE u.id = :id", Integer.class);
        query.setParameter("id", id);
        int cl = query.getSingleResult();
        System.out.println(cl);
        return cl;

    }

    public int criarUsuario(String cpf, String senha, String nome, String telefone, TipoUsuario tipoUsuario, LocalDate dataNascimento) {

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (!usuarioDAO.validarUsuario(nome, senha, tipoUsuario)) {
            usuarioEntity.setCpf(cpf);
            usuarioEntity.setNome(nome);
            usuarioEntity.setSenha(senha);
            usuarioEntity.setTelefone(telefone);
            usuarioEntity.setTipoUsuario(tipoUsuario);
            usuarioEntity.setDataNascimento(dataNascimento);

            int retornoId = usuarioDAO.salvar(usuarioEntity);

            return retornoId;
        } else {
            Exception e = new Exception();
            e.printStackTrace();
            return -1;
        }
    }


    public void fechar(){
        entityManager.close();
    }


}
