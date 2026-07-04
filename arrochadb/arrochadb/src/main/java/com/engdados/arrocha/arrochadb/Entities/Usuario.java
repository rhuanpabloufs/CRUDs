package com.engdados.arrocha.arrochadb.Entities;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import lombok.Data;
import java.util.*;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario", schema="universidade")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @Min(value = 10000000000L, message = "CPF inválido")
    @Max(value = 99999999999L, message = "CPF inválido")
    private Long cpf;

    @NotBlank(message = "Nome não pode ser vazio ou nulo")
    @Size(min = 1, max = 100, message = "Nome deve ter de 1 a 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column
    @Past(message = "Data deve ser no passado")
    private LocalDate data_nascimento; 

    @ElementCollection
    @CollectionTable(
        name = "usuario_emails", joinColumns = @JoinColumn(name = "cpf_por_emails", referencedColumnName = "cpf")
    )
    private Set<String> email; 

    @ElementCollection
    @CollectionTable(
        name = "usuario_telefones",
        joinColumns = @JoinColumn(name = "cpf_por_telefones", referencedColumnName = "cpf")

    )
    private Set<String> telefone;
    
    @Column(unique = true, length = 45)
    @Size(min = 1, max = 45, message = "Limite de 1 a 45 caracteres em login")
    private String login;

    @Column(length = 32)
    @Size(min = 1, max = 35, message = "Limite de 1 a 32 caracteres em senha")
    private String senha;


}
