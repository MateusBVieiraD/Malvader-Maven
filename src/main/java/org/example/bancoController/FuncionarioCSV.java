package org.example.bancoController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.config.EntityFactory;
import org.example.entity.Funcionario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FuncionarioCSV {

    private final EntityManager entityManager = EntityFactory.getEntityManager();

    // Método para exportar os dados do Hibernate (via JPA) para um arquivo CSV
    public void funcionarioCSV() throws IOException {


        try {
            // Começar uma transação e fazer a consulta
            entityManager.getTransaction().begin();

            TypedQuery<Funcionario> query = entityManager.createQuery("from Funcionario", Funcionario.class);
            List<Funcionario> funcionarios = query.getResultList();

            entityManager.getTransaction().commit();

            // Criar o arquivo CSV
            FileWriter out = new FileWriter("funcionario.csv");
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Codigo Funcionario", "Cargo", "Nome"));

            // Escrever os dados no CSV
            for (Funcionario funcionario : funcionarios) {
                printer.printRecord(funcionario.getId(), funcionario.getCodigoFuncionario(), funcionario.getCargo(), funcionario.getNome());
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
