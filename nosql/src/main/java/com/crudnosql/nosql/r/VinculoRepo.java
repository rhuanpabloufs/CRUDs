package com.crudnosql.nosql.r;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Vinculo;
public interface VinculoRepo  extends MongoRepository<Vinculo,String>{    
    public Vinculo findByMatEstudante(String m);
    public List<Vinculo> findByCurso(String idCurso);
    public boolean existsByCurso(String idCurso);
    public boolean existsByMatEstudante(String m);
    public void deleteByCurso(String curso);
    public void deleteByMatEstudante(String mat_estudante);
    
}
