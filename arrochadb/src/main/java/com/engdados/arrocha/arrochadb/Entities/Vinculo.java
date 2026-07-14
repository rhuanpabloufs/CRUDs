package com.engdados.arrocha.arrochadb.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="vinculo", schema="universidade")
@Data
@NoArgsConstructor
public class Vinculo{

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Integer idVinculo;

@ManyToOne
@JoinColumn(name="mat_estudante", referencedColumnName = "mat_estudante",
    foreignKey = @ForeignKey(name = "fk_vinculo_estudante",
     foreignKeyDefinition = "FOREIGN KEY (mat_estudante) REFERENCES universidade.estudante(mat_estudante) ON DELETE SET NULL ON UPDATE CASCADE"
    )
)
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private Estudante estudante;

@ManyToOne
@JoinColumn(name="curso", referencedColumnName = "idCurso",
    foreignKey = @ForeignKey(name = "fk_vinculo_curso",
     foreignKeyDefinition = "FOREIGN KEY (curso) REFERENCES universidade.curso(idCurso) ON DELETE SET NULL ON UPDATE CASCADE"
    )
)
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private Curso curso;

@JsonProperty("estudante")
public String getEstudanteMat() {
    return estudante != null ? estudante.getMat_estudante() : null;
}

@JsonProperty("curso")
public Integer getCursoId() {
    return curso != null ? curso.getIdCurso() : null;
}

@Past(message = "Data de entrada não pode ser no futuro")
@Column(name="data_entrada")
private LocalDate dataEntrada;

@Column(name="data_saida")
private LocalDate dataSaida;

@Column(name="status")
private String status;

}
