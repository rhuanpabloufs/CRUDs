package com.engdados.arrocha.arrochadb.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.JdbcTypeCode; // Lida com tipos Enums para com o Postgress
import org.hibernate.type.SqlTypes; // Enum não é originalmente nativo do sql e nem universal entre bancos, então não tem jeito padrão no JPA de tratar

@Entity
@Table(name="vinculo", schema="universidade")
@Data 
@NoArgsConstructor

public class Vinculo{

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Long idVinculo;

@ManyToOne
@JoinColumn(name="mat_estudante", referencedColumnName = "mat_estudante",
    foreignKey = @ForeignKey(name = "fk_vinculo_estudante",
     foreignKeyDefinition = "FOREIGN KEY (mat_estudante) REFERENCES universidade.estudante(mat_estudante) ON DELETE SET NULL ON UPDATE CASCADE"
    )
)
private Estudante t_estudante;

@ManyToOne
@JoinColumn(name="curso", referencedColumnName = "idCurso",
    foreignKey = @ForeignKey(name = "fk_vinculo_curso",
     foreignKeyDefinition = "FOREIGN KEY (curso) REFERENCES universidade.curso(idCurso) ON DELETE SET NULL ON UPDATE CASCADE"
    )
)
private Curso t_curso;

@Past(message = "Data de entrada deve ser no passado")
@Column(name="data_entrada")
private LocalDate dataEntrada;


@Column(name="data_saida")
private LocalDate dataSaida;

@Enumerated(EnumType.STRING)
@JdbcTypeCode(SqlTypes.NAMED_ENUM)
@Column(name="status")
private Status status;

}