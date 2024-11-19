package org.example.dao;

import jakarta.persistence.*;
import org.example.config.EntityFactory;
import org.example.entity.ContaEntity;
import org.example.entity.TipoTransacao;
import org.example.entity.Transacao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

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

    public void fechar() {
        entityManager.close();
    }
}
