package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Cliente extends Usuario{

    @Column(name = "senha", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "conta")
    private List<Cliente> clientes;

    public Cliente() {
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
