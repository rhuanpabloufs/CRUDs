package com.engdados.arrocha.arrochadb.Repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.engdados.arrocha.arrochadb.Entities.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, String> {
    boolean existsByUsuarioCpf(Long cpf);
}
