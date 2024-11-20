package org.example.entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo_relatorio", length = 50)
    private String tipoRelatorio;
    @Column(name = "data_geracao")
    private Timestamp timestamp;
    @Column(name = "conteudo", length = 50)
    private String conteudo;
    @ManyToOne
    @JoinColumn
    private Funcionario funcionario;
    private List<String> dados;

    public Relatorio() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<String> getDados() {
        return dados;
    }

    public void setDados(List<String> dados) {
        this.dados = dados;
    }
}
