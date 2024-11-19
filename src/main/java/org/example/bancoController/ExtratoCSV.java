package org.example.bancoController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.config.EntityFactory;
import org.example.entity.ContaEntity;
import org.example.entity.Transacao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExtratoCSV {
    private final EntityManager entityManager = EntityFactory.getEntityManager();

    public void extratoCSV() throws IOException {


        try {
            entityManager.getTransaction().begin();

            TypedQuery<Transacao> query = entityManager.createQuery("from Transacao", Transacao.class);
            List<Transacao> transacoes = query.getResultList();

            entityManager.getTransaction().commit();

            FileWriter out = new FileWriter("extrato.csv");
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID","Valor","Data","Tipo Transacao","Conta-ID"));

            for (Transacao transacao : transacoes) {
                printer.printRecord(transacao.getId(), transacao.getValor(), transacao.getDataHora(), transacao.getTipoTransacao(), transacao.getConta().getId());
            }

            printer.flush();
            printer.close();
            System.out.println("Arquivo CSV criado com sucesso!");

        } finally {
            entityManager.close();
        }
    }
}
