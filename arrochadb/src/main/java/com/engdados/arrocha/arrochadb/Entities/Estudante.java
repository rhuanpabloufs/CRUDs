package com.engdados.arrocha.arrochadb.Entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="estudante", schema="universidade")
@Data
@NoArgsConstructor

public class Estudante{

@Id
@Size(max = 7, message = "Matrícula deve ter no máximo 7 caracteres")
@Column(name= "mat_estudante", length = 7)
private String mat_estudante; 

@OneToOne
@JoinColumn(name = "cpf", unique = true, referencedColumnName = "cpf",
foreignKey = @ForeignKey(
    name = "fk_estudante_usuario",
    foreignKeyDefinition = "FOREIGN KEY (cpf) REFERENCES universidade.usuario(cpf) ON DELETE SET NULL ON UPDATE CASCADE "
) 
)
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private Usuario usuario;

@JsonProperty("usuario")
public Long getUsuarioCpf() {
    return usuario != null ? usuario.getCpf() : null;
}

@Column(precision = 2, scale = 0)
private BigDecimal MC; // BigDecimal

@Column
private int ano_ingresso;

}