package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

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
    @Column(name = "saldo" ,precision = 15, scale = 2)
    private BigDecimal saldo;
    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoconta;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

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
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
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
        this.saldo = this.saldo.subtract(BigDecimal.valueOf(valor));
    }
}
