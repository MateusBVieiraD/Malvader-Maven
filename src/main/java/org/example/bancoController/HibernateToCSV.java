package org.example.bancoController;

import jakarta.persistence.EntityManager;
import org.example.config.EntityFactory;
import org.example.entity.ContaEntity;


import jakarta.persistence.TypedQuery;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class HibernateToCSV {
    private final EntityManager entityManager = EntityFactory.getEntityManager();

    // Método para exportar os dados do Hibernate (via JPA) para um arquivo CSV
    public void exportarParaCSV() throws IOException {


        try {
            // Começar uma transação e fazer a consulta
            entityManager.getTransaction().begin();

            TypedQuery<ContaEntity> query = entityManager.createQuery("from ContaEntity", ContaEntity.class);
            List<ContaEntity> contas = query.getResultList();

            entityManager.getTransaction().commit();

            // Criar o arquivo CSV
            FileWriter out = new FileWriter("contas.csv");
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Saldo", "Agencia"));

            // Escrever os dados no CSV
            for (ContaEntity conta : contas) {
                printer.printRecord(conta.getId(), conta.getSaldo(), conta.getAgencia());
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

