package com.crudnosql.nosql.r;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Usuario;
import java.util.List;
public interface UsuarioRepo extends MongoRepository<Usuario,String>{
    public List<Usuario> findByNome(String nome);
    public boolean existsByLogin(String login);
    public void deleteByLogin(String login);
}
