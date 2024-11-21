package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conta")
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_conta", length = 20)
    private String numeroConta;

    @Column(name = "agencia", length = 10)
    private String agencia;

    @Column(name = "saldo", precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;

    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoconta;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;





    /*public void addContaCorrente(ContaCorrente contaCorrente) {
        contasCorrente.add(contaCorrente);
        contaCorrente.setConta(this);
    }

    public void addContaPoupanca(ContaPoupanca contaPoupanca) {
        contasPoupanca.add(contaPoupanca);
        contaPoupanca.setConta(this);
    }

    public void removeContaCorrente(ContaCorrente contaCorrente) {
        contasCorrente.remove(contaCorrente);
        contaCorrente.setConta(null);
    }

    public void removeContaPoupanca(ContaPoupanca contaPoupanca) {
        contasPoupanca.remove(contaPoupanca);
        contaPoupanca.setConta(null);
    }*/







    public ContaEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo; // Retorna o saldo atual
    }

    public TipoConta getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(TipoConta tipoconta) {
        this.tipoconta = tipoconta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarSaldo(double saldo) {
        this.saldo = this.saldo.add(BigDecimal.valueOf(saldo));
    }

    public void sacarSaldo(double valor) {
        if (this.saldo.compareTo(BigDecimal.valueOf(valor)) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque.");
        }
        this.saldo = this.saldo.subtract(BigDecimal.valueOf(valor));
    }
}

