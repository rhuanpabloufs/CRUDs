package com.engdados.arrocha.arrochadb.Entities;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.validation.constraints.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="curso", schema="universidade"
, uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "turno", "campus", "nivel"}))

public class Curso {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idCurso;

@NotBlank(message="Nome não pode ser nulo") // o nome nao pode ser vazio nem nulo
@Size(min=1, max=100,message="Vá se fuder, meu pai, vá!")
@Column(name="nome", nullable = false, length = 100)
private String nome;

@Enumerated(EnumType.STRING)
@JdbcTypeCode(SqlTypes.NAMED_ENUM)
@Column(name = "grau")
private Grau grau;

@Enumerated(EnumType.STRING)
@JdbcTypeCode(SqlTypes.NAMED_ENUM)
@Column(name = "turno", nullable = false)
private Turno turno;
@Size(min=0, max=100)
@Column(name="campus", length = 100)
private String campus;

@Enumerated(EnumType.STRING)
@JdbcTypeCode(SqlTypes.NAMED_ENUM)
@Column(name = "nivel")
private Nivel nivel;

}
