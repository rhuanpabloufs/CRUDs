package com.engdados.arrocha.arrochadb.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="estudante", schema="universidade")
@Data
@NoArgsConstructor

public class Estudante{

@Id
private String matEstudante; //porque ele é do tipo enum lá que você cria o tipo, aí botei string mesmo


}