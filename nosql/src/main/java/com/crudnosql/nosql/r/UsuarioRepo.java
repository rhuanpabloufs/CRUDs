package com.crudnosql.nosql.r;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.crudnosql.nosql.objects.Usuario;
public interface UsuarioRepo extends MongoRepository<Usuario,String>{

}
