    package com.engdados.arrocha.arrochadb.Entities;

    import java.math.BigDecimal;

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
    @Column(name= "mat_Estudante", length = 7)
    private String mat_Estudante; 

    @OneToOne
    @JoinColumn(name = "cpf", unique = true, referencedColumnName = "cpf",
    foreignKey = @ForeignKey(
        name = "fk_estudante_usuario",
        foreignKeyDefinition = "FOREIGN KEY (cpf) REFERENCES universidade.usuario(cpf) ON DELETE SET NULL ON UPDATE CASCADE "
    ) 
    )
    private Usuario usuario;

    @Column(precision = 2, scale = 0)
    private BigDecimal MC; // BigDecimal

    @Column
    private int ano_ingresso;

    }