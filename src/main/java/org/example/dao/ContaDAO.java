package org.example.dao;

import jakarta.persistence.*;
import org.example.config.EntityFactory;
import org.example.entity.Cliente;
import org.example.entity.ContaEntity;
import org.example.entity.TipoTransacao;
import org.example.entity.UsuarioEntity;

import java.math.BigDecimal;


public class ContaDAO {

    private final EntityManager entityManager;

    public ContaDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public void salvar(ContaEntity conta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(conta);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public static ContaEntity update(ContaEntity conta){
        var a = EntityFactory.getEntityManager();
        EntityTransaction transaction = a.getTransaction();
        try {
            transaction.begin();
            ContaEntity contaAtualizada = a.merge(conta);
            transaction.commit();
            return contaAtualizada;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Falha ao atualizar a conta", e); // Re-throw exception
        }
    }

    public void fechar() {
        entityManager.close();
    }

    public static int verificarContaRelacionada(int clienteId) {
        var a = EntityFactory.getEntityManager();

        TypedQuery<Integer> query = a.createQuery(
                "SELECT co.id FROM ContaEntity co JOIN co.cliente c WHERE c.id = :id",
                Integer.class
        );
        query.setParameter("id", clienteId);

        int c1 = query.getSingleResult();
        int result = c1;
        System.out.println(result);
        return result;

    }

    public static BigDecimal verificarSaldo(int clienteId) {

        try{
            var a = EntityFactory.getEntityManager();
            UsuarioDAO usuarioDao = new UsuarioDAO();

                TypedQuery<Integer> query = a.createQuery(
                        "SELECT co.saldo FROM ContaEntity co JOIN co.cliente c WHERE c.id = :id",
                        Integer.class
                );

                query.setParameter("id", clienteId);

                BigDecimal c1 = BigDecimal.valueOf(query.getSingleResult());


                return c1;


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    public static void atualizarSaldo(int id, double saldo){
        EntityManager a = EntityFactory.getEntityManager();
        EntityTransaction transaction = a.getTransaction();

        try {
            transaction.begin();

            // Busca a conta com o ID fornecido
            ContaEntity contaPraAtualizar = a.find(ContaEntity.class, id);

            // Atualiza o saldo
            contaPraAtualizar.adicionarSaldo(saldo);
            System.out.println(contaPraAtualizar.getSaldo());

            // Não é necessário persistir pois a entidade já está gerenciada
            transaction.commit();
            System.out.println("Saldo atualizado com sucesso!");

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar o saldo da conta: " + e.getMessage(), e);
        } finally {
            a.close(); // Fecha o EntityManager para liberar recursos
        }
    }

    public static void sacarSaldo(int id, double valor){
        EntityManager a = EntityFactory.getEntityManager();

        try{
            ContaEntity contaPraSacar = a.find(ContaEntity.class, id);
            if(valor < contaPraSacar.getSaldo().doubleValue()){
                contaPraSacar.sacarSaldo(valor);

                update(contaPraSacar);

                TransacaoDAO transacaoDAO = new TransacaoDAO();
                ContaEntity c1 = a.find(ContaEntity.class, id);
                transacaoDAO.criarTransacao(c1, valor, TipoTransacao.SAQUE);

            }else{
                Exception e = null;
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
