package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo_transacao")
    @Enumerated(EnumType.STRING)
    private TipoTransacao transacao;

    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_hora")
    private Timestamp dataHora;

    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private ContaEntity conta;

    public Transacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoTransacao getTipoTransacao() {
        return transacao;
    }

    public void setTipoTransacao(TipoTransacao transacao) {
        this.transacao = transacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
    }
}
