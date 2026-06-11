package com.crudnosql.nosql.r;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Estudante;
public interface EstudanteRepo extends MongoRepository<Estudante,String>{
    public Estudante getByMat_estudante(String mat_estudante);
    public List<Estudante> getByAno_ingresso(int ano);
    public boolean existsByMat_estudante(String mat);
    public void deleteByAno_ingresso(int ano);
}
