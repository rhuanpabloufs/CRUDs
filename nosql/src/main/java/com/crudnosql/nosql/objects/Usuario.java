package com.crudnosql.nosql.objects;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "usuario")
public class Usuario {
    @Id
    private String cpf;
    private String nome;
    private LocalDateTime data_nascimento;
    private List<String> email;
    private List<String>  telefone;
    private String login;
    private String senha;
}
