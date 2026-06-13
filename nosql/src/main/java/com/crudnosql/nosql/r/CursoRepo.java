package com.crudnosql.nosql.r;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Curso;
public interface CursoRepo extends MongoRepository<Curso, String> {
    Curso findByNome(String nome);
    boolean existsByNome(String nome);
    void deleteByNome(String nome);
}