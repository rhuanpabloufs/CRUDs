package com.engdados.arrocha.arrochadb.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engdados.arrocha.arrochadb.Entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    boolean existsBylogin(String login);
}