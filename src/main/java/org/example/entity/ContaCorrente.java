package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_conta_corrente;
    @Column(name = "limite")
    private BigDecimal limite;
    @Column(name = "data_vencimento")
    Date data_vencimento;
    @OneToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public ContaCorrente() {
    }

    public int getId_conta_corrente() {
        return id_conta_corrente;
    }

    public void setId_conta_corrente(int id_conta_corrente) {
        this.id_conta_corrente = id_conta_corrente;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
