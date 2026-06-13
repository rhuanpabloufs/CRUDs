package com.crudnosql.nosql.objects;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "vinculo")
public class Vinculo {
    @Id
    private String id;
    private String matEstudante;
    private String curso;
    private LocalDateTime dataEntrada;
    private String status;
    private LocalDateTime dataSaida;
}
