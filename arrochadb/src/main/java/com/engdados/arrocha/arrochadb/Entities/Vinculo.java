package com.engdados.arrocha.arrochadb.Entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vinculo", schema="universidade")
@Data 
@NoArgsConstructor
public class Vinculo{
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Long idVinculo;

@JoinColumn(name="mat_estudante")
@ManyToOne
private Estudante estudante;
@JoinColumn(name="curso")
@ManyToOne
private Curso curso;
@Column(name="data_entrada")
private LocalDate dataEntrada;
@Column(name="data_saida")
private LocalDate dataSaida;
@Column(name="status")
private Status status;
}