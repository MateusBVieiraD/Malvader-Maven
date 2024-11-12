package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Funcionario extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_funcionario;
    @Column(name = "codigo_funcionario")
    private String codigoFuncionario;
    @Column(name = "cargo")
    private String cargo;









}
