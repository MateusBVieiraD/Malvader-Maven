package org.example.dao;

import jakarta.persistence.*;
import org.example.config.EntityFactory;
import org.example.entity.*;
import org.example.modelo.Conta;

import java.math.BigDecimal;

public class ContaDAO {

    private final EntityManager entityManager;

    public ContaDAO() {
        // Cria o EntityManagerFactory com base no nome da unidade de persistência
        entityManager = EntityFactory.getEntityManager();
    }

    public int salvar(ContaEntity conta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(conta);
            transaction.commit();
            return conta.getId();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
                return -1;
            }
            throw e; // Re-throw exception
        }
    }

    public boolean remover(ContaEntity contaEntity){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(contaEntity);
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

    public ContaEntity update(ContaEntity conta){
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

    public boolean buscarporId(String contaId){

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Consulta nativa para remover a conta diretamente (sem verificar as dependências)
            String query = "SELECT u FROM ContaEntity u WHERE u.numeroConta = :numero";
            ContaEntity contaEntity = entityManager.createQuery(query, ContaEntity.class)
                    .setParameter("numero", contaId)
                    .getSingleResult();

            transaction.commit();

            return contaEntity != null;
        } catch (NoResultException e) {
            // Lida com o caso onde nenhuma conta é encontrado
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false; // Conta não encontrada
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-throw exception
        }
    }

    public static int verificarContaRelacionada(int clienteId) {
        try {
            var entityManager = EntityFactory.getEntityManager();

            // Consultar conta corrente
            TypedQuery<Integer> queryContaCorrente = entityManager.createQuery(
                    "SELECT co.id FROM ContaEntity co JOIN co.cliente c WHERE c.id = :id AND co.tipoconta = 'CORRENTE'",
                    Integer.class
            );
            queryContaCorrente.setParameter("id", clienteId);

            // Verifica se existe conta corrente
            Integer contaCorrente = null;
            try {
                contaCorrente = queryContaCorrente.getSingleResult();
            } catch (NoResultException ignored) {
                // Ignorar exceção para verificar conta poupança depois
            }

            // Se conta corrente foi encontrada, retorna
            if (contaCorrente != null) {
                return contaCorrente;
            }

            // Consultar conta poupança
            TypedQuery<Integer> queryContaPoupanca = entityManager.createQuery(
                    "SELECT co.id FROM ContaEntity co JOIN co.cliente c WHERE c.id = :id AND co.tipoconta = 'POUPANCA'",
                    Integer.class
            );
            queryContaPoupanca.setParameter("id", clienteId);

            try {
                return queryContaPoupanca.getSingleResult();
            } catch (NoResultException e) {
                // Caso nenhuma conta seja encontrada, retorna 0
                return 0;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao verificar conta relacionada", e);
        }
    }



    public int criarConta(int id, String agencia, TipoConta tipoConta, String numeroConta){
        ContaEntity conta = new ContaEntity();
        ContaDAO contaDAO = new ContaDAO();
        Cliente cliente = entityManager.find(Cliente.class, id);
        conta.setNumeroConta(numeroConta);
        conta.setAgencia(agencia);
        conta.setTipoconta(tipoConta);
        conta.setCliente(cliente);

        int retornoId = contaDAO.salvar(conta);
        return retornoId;

    }

    public static BigDecimal verificarSaldo(int clienteId) {
        try {
            var entityManager = EntityFactory.getEntityManager();

            // Verificar saldo da conta corrente
            TypedQuery<BigDecimal> queryContaCorrente = entityManager.createQuery(
                    "SELECT co.saldo FROM ContaEntity co JOIN co.cliente c WHERE c.id = :id AND co.tipoconta = 'CORRENTE'",
                    BigDecimal.class
            );
            queryContaCorrente.setParameter("id", clienteId);

            BigDecimal saldoCorrente;
            try {
                saldoCorrente = queryContaCorrente.getSingleResult();
                return saldoCorrente;
            } catch (NoResultException e) {
                // Caso não exista conta corrente, verifica saldo da poupança
                TypedQuery<BigDecimal> queryContaPoupanca = entityManager.createQuery(
                        "SELECT co.saldo FROM ContaEntity co JOIN co.cliente c WHERE c.id = :id AND co.tipoconta = 'POUPANCA'",
                        BigDecimal.class
                );
                queryContaPoupanca.setParameter("id", clienteId);

                try {
                    return queryContaPoupanca.getSingleResult();
                } catch (NoResultException ex) {
                    // Caso não tenha nenhuma das contas, retorna saldo zerado
                    return BigDecimal.ZERO;
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao verificar saldo", e);
        }
    }


    public static void atualizarSaldo(int id, double saldo){
        EntityManager a = EntityFactory.getEntityManager();
        EntityTransaction transaction = a.getTransaction();

        try {
            transaction.begin();

            ContaEntity contaPraAtualizar = a.find(ContaEntity.class, id);

            contaPraAtualizar.adicionarSaldo(saldo);
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            transacaoDAO.criarTransacao(contaPraAtualizar,saldo,TipoTransacao.DEPOSITO);

            transaction.commit();
            System.out.println("Saldo atualizado com sucesso!");

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar o saldo da conta: " + e.getMessage(), e);
        } finally {
            a.close();
        }
    }

    public static boolean sacarSaldo(int id, double valor){
        EntityManager a = EntityFactory.getEntityManager();
        ContaDAO contaDAO = new ContaDAO();

        try{

            ContaEntity contaPraSacar = a.find(ContaEntity.class, id);

            if(contaPraSacar != null && valor < contaPraSacar.getSaldo().doubleValue()){
                contaPraSacar.sacarSaldo(valor);

                contaDAO.update(contaPraSacar);

                TransacaoDAO transacaoDAO = new TransacaoDAO();

                transacaoDAO.criarTransacao(contaPraSacar, valor, TipoTransacao.SAQUE);

                return true;

            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException("Algo de errado ocorreu ao sacar o saldo");
        }


    }

    public ContaEntity buscarNumeroConta(String numeroConta){
        try {
            return entityManager.createQuery("SELECT c FROM ContaEntity c WHERE c.numeroConta = :numeroConta", ContaEntity.class)
                    .setParameter("numeroConta", numeroConta)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ContaEntity buscarNumeroConta(int id){
        try {
            return entityManager.createQuery("SELECT c FROM ContaEntity c WHERE c.numeroConta = :idConta", ContaEntity.class)
                    .setParameter("idConta", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String consultarConta(String numeroConta){
        ContaDAO contaDAO = new ContaDAO();
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();

        ContaEntity contaEntity = contaDAO.buscarNumeroConta(numeroConta);
        Cliente cliente = contaEntity.getCliente();
        UsuarioEntity usuarioEntity = cliente.getUsuario();


        if (contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId()) != null){
            ContaCorrente contaCorrente = contaCorrenteDAO.buscarContaCorrenteConta(contaEntity.getId());
            return "Tipo da conta:  " + contaEntity.getTipoconta()  +
                    "\nNome do cliente: " + usuarioEntity.getNome() +
                    "\nCpf do cliente: " + usuarioEntity.getCpf() +
                    "\nSaldo da conta: " + contaEntity.getSaldo() +
                    "\nLimite disponivel: " + contaCorrente.getLimite() +
                    "\nData de vencimento: " + contaCorrente.getData();
        }

        return "Essa conta não existe" ;
    }

}
