package com.engdados.arrocha.arrochadb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.engdados.arrocha.arrochadb.Entities.Turno;
import com.engdados.arrocha.arrochadb.Entities.Nivel;
import com.engdados.arrocha.arrochadb.Entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository <Curso, Long>{
    boolean existsByNomeAndTurnoAndCampusAndNivel(String nome, Turno turno, String Campus, Nivel nivel);
    Curso findByNomeAndTurnoAndCampusAndNivel(String nome, Turno turno, String Campus, Nivel nivel);
}
