package org.example.entity;

import jakarta.persistence.*;
import org.example.modelo.Conta;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "conta_corrente" )
public class ContaCorrente extends Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "limite", precision = 15, scale = 2)
    private BigDecimal limite;
    @Column(name = "data_vencimento")
    private LocalDate data;
    @OneToOne
    @JoinColumn
    private ContaEntity conta;

    public ContaCorrente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
    }
}
