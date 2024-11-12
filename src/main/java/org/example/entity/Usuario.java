package org.example.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
public abstract class Usuario {

    @Id //Pk db
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "data_nascimento")
    Date dataNascimento;
    @Column(name = "telefone")
    private String telefone;
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Usuario() {}

    public Usuario(String nome, String cpf, LocalDate dataNascimento, String telefone) {

        this.endereco = new Endereco();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    boolean login(String senha){
        return true;
    }

    void logout(){

    }

    String consultarDados(){
        return "";
    }
}
