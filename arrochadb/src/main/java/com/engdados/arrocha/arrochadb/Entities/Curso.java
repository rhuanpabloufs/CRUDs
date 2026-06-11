package com.engdados.arrocha.arrochadb.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="curso", schema="universidade")
public class Curso {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idCurso;
@Column(name="nome")
private String nome;
@Enumerated(EnumType.STRING)
private Grau grau;
@Enumerated(EnumType.STRING)
private Turno turno;
@Column(name="campus")
private String campus;
@Enumerated(EnumType.STRING)
private Nivel nivel;
}
