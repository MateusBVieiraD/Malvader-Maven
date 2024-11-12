package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ContaPoupanca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_conta_poupanca;
    @Column(name = "taxa_rendimento")
    private BigDecimal taxaRendimento;
    @OneToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public ContaPoupanca() {
    }

    public int getId_conta_poupanca() {
        return id_conta_poupanca;
    }

    public void setId_conta_poupanca(int id_conta_poupanca) {
        this.id_conta_poupanca = id_conta_poupanca;
    }

    public BigDecimal getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(BigDecimal taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }



}
