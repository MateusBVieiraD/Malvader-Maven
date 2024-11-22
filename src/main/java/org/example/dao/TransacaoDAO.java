package org.example.dao;

import jakarta.persistence.*;
import org.example.config.EntityFactory;
import org.example.entity.ContaEntity;
import org.example.entity.Endereco;
import org.example.entity.TipoTransacao;
import org.example.entity.Transacao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class TransacaoDAO {
    private final EntityManager entityManager;
    public TransacaoDAO() {
        entityManager = EntityFactory.getEntityManager();
    }



    public void salvar(Transacao transacao) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(transacao);  // Persiste a entidade no banco
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public boolean remover(Transacao transacao){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(transacao);
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

    public void criarTransacao(ContaEntity conta,double valor, TipoTransacao tipo){
        Instant agora = Instant.now();
        Timestamp momentoDaTransacao = Timestamp.from(agora);
        TransacaoDAO transacaoDao = new TransacaoDAO();

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(tipo);
        transacao.setValor(BigDecimal.valueOf(valor));
        transacao.setDataHora(momentoDaTransacao);
        transacao.setConta(conta);

        transacaoDao.salvar(transacao);




    }

    public List<Transacao> buscarTransacoes(int idConta) {
        TypedQuery<Transacao> query = entityManager.createQuery(
                "SELECT t FROM Transacao t WHERE t.conta.id = :id ", Transacao.class);
        query.setParameter("id", idConta);

        return query.getResultList();
    }

    public void fechar() {
        entityManager.close();
    }
}
