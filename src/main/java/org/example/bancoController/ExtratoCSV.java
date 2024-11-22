package org.example.bancoController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.config.EntityFactory;
import org.example.entity.Funcionario;
import org.example.entity.Transacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExtratoCSV {
    private final EntityManager entityManager = EntityFactory.getEntityManager();

    // Método para exportar os dados do Hibernate (via JPA) para um arquivo CSV
    public void extratoCSV() throws IOException {

        File arquivoCSV = new File("extrato.csv");

        if (arquivoCSV.exists()) {
            arquivoCSV.delete();
            System.out.println("Arquivo CSV anterior excluído!");
        }

        try {
            // Começar uma transação e fazer a consulta
            entityManager.getTransaction().begin();

            TypedQuery<Transacao> query = entityManager.createQuery("from Transacao", Transacao.class);
            List<Transacao> transacoes = query.getResultList();

            entityManager.getTransaction().commit();

            // Criar o arquivo CSV
            FileWriter out = new FileWriter("extrato.csv");
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Tipo-transacao", "Data-Hora", "Valor", "Conta"));

            // Escrever os dados no CSV
            for (Transacao transacao : transacoes) {
                printer.printRecord(transacao.getId(), transacao.getTipoTransacao(), transacao.getDataHora(), transacao.getValor(), transacao.getConta().getId());
            }

            printer.flush();
            printer.close();
            System.out.println("Arquivo CSV criado com sucesso!");

        } finally {
            // Fechar o EntityManager e o EntityManagerFactory
            entityManager.close();
        }
    }
}
