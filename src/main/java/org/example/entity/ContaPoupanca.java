package org.example.entity;

import jakarta.persistence.*;
import org.example.modelo.Conta;

import java.math.BigDecimal;

@Entity
@Table(name = "conta_poupanca")
public class ContaPoupanca extends Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "taxa_rendimento", precision = 5, scale = 2)
    private BigDecimal taxaRendimento;

    @OneToOne
    @JoinColumn(name = "id_conta", referencedColumnName = "id")
    private ContaEntity conta;

    public ContaPoupanca() {
    }

    public double calcularRendimento(){ return 0; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(BigDecimal taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
    }
}
