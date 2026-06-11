package com.crudnosql.nosql.objects;
import java.time.LocalDateTime;
import java.util.List;
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
    private String cpf;
    private String mat_estudante;
    private double mc;
    private int ano_ingresso;
    
}
