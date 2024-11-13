package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cep", length = 10)
    private String cep;
    @Column(name = "local", length = 100)
    private String local;
    @Column(name = "numero_casa")
    private int numeroCasa;
    @Column(name = "bairro", length = 50)
    private String bairro;
    @Column(name = "cidade", length = 50)
    private String cidade;
    @Column(name = "estado", length = 2)
    private String estado;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuario;

    public Endereco() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id='" + id + '\'' +
                ", cep='" + cep + '\'' +
                ", local='" + local + '\'' +
                ", numeroCasa=" + numeroCasa +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", usuario=" + usuario +
                '}';
    }

}