package com.crudnosql.nosql.r;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Vinculo;
public interface VinculoRepo  extends MongoRepository<Vinculo,String>{    
    public Vinculo findByMat_estudante(String m);
    public List<Vinculo> findByCurso(String idCurso);
    public boolean existsByMat_estudante(String m);
    public void deleteByCurso(String curso);
    public void deleteByMat_Estudante(String mat_estudante);
    
}
