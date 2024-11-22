package org.example.bancoController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.config.EntityFactory;
import org.example.entity.Relatorio;
import org.example.entity.Transacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioCSV {
    private final EntityManager entityManager = EntityFactory.getEntityManager();

    public void relatorioCSV() throws IOException {

        // Caminho do arquivo CSV
        File arquivoCSV = new File("relatorio.csv");

        // Se o arquivo já existir, exclua-o
        if (arquivoCSV.exists()) {
            arquivoCSV.delete();
            System.out.println("Arquivo CSV anterior excluído!");
        }

        // Cria o novo arquivo CSV
        try {
            entityManager.getTransaction().begin();

            // Consulta para buscar os relatórios
            TypedQuery<Transacao> queryTransacao = entityManager.createQuery("from Transacao", Transacao.class);
            List<Transacao> transacoes = queryTransacao.getResultList();
            entityManager.getTransaction().commit();

            // Criação do novo arquivo CSV
            FileWriter out = new FileWriter(arquivoCSV);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Valores", "Tipo relatorio", "Timestamp", "Conta-ID"));

            // Preenche o CSV com os dados dos relatórios
            for (Transacao transacao : transacoes) {
                printer.printRecord(transacao.getId(), transacao.getValor(), transacao.getTipoTransacao(), transacao.getDataHora(), transacao.getConta().getId());
            }



            printer.flush();
            printer.close();
            System.out.println("Novo arquivo CSV criado com sucesso!");

        } finally {
            // Fecha o EntityManager
            entityManager.close();
        }
    }
}
