package com.crudnosql.nosql.objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "estudante")
public class Estudante {
    @Id
    private String matEstudante;
    private String cpf;
    private double mc;
    private int anoIngresso;
    
}
