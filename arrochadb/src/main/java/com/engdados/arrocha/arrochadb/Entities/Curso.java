package com.engdados.arrocha.arrochadb.Entities;

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
private Integer idCurso;

@NotBlank(message = "Nome de curso não pode ser nulo")
@Size(max = 100, message = "Máximo de 100 caracteres em nome de curso")
@Column(name="nome", nullable = false, length = 100)
private String nome;

@Column(name = "grau")
private String grau;

@NotNull(message = "Turno não pode ser vazio ou nulo")
@Column(name = "turno", nullable = false)
private String turno;

@Size(max = 100, message = "Máximo de 100 caracteres em nome de curso")
@Column(name="campus", length = 100)
private String campus;

@Column(name = "nivel")
private String nivel;

}
