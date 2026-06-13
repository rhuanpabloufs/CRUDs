package com.crudnosql.nosql.r;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Estudante;
public interface EstudanteRepo extends MongoRepository<Estudante,String>{
    public Estudante findByMatEstudante(String mat_estudante);
    public List<Estudante> findByAnoIngresso(int ano);
    public boolean existsByMatEstudante(String mat);
    public void deleteByAnoIngresso(int ano);
}
