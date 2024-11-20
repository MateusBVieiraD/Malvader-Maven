package org.example.bancoController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.config.EntityFactory;
import org.example.entity.Relatorio;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioCSV {
    private final EntityManager entityManager = EntityFactory.getEntityManager();

    public void relatorioCSV() throws IOException {


        try {
            entityManager.getTransaction().begin();

            TypedQuery<Relatorio> query = entityManager.createQuery("from Relatorio", Relatorio.class);
            List<Relatorio> relatorios = query.getResultList();

            entityManager.getTransaction().commit();

            FileWriter out = new FileWriter("relatorio.csv");
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID","Conteudo", "Tipo relatorio","Timestamp","Funcionario-ID","Dados"));

            for (Relatorio relatorio : relatorios) {
                printer.printRecord(relatorio.getId(), relatorio.getConteudo(), relatorio.getTipoRelatorio(), relatorio.getTimestamp(), relatorio.getFuncionario().getId(), relatorio.getDados());
            }

            printer.flush();
            printer.close();
            System.out.println("Arquivo CSV criado com sucesso!");

        } finally {
            entityManager.close();
        }
    }
}
